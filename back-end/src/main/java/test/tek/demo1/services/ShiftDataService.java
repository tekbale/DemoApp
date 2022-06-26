package test.tek.demo1.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test.tek.demo1.db.entity.ShiftData;
import test.tek.demo1.db.repo.ShiftDataRepository;
import test.tek.demo1.dto.UserDto;
import test.tek.demo1.dto.request.ShiftDataRequestDto;
import test.tek.demo1.dto.response.ShiftDataDto;
import test.tek.demo1.enums.ShiftStatusEnum;
import test.tek.demo1.exceptions.InvalidShiftChangeActionException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShiftDataService {

    @Autowired
    private ShiftDataRepository shiftDataRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ShiftValidationService shiftValidationService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ShiftDataDto> getShiftData(String userId) {
        Pageable shiftDataPage = PageRequest.of(0, 100);
        return shiftDataRepository.findByUserId(userId, shiftDataPage).stream()
                .map(shiftData -> modelMapper.map(shiftData, ShiftDataDto.class)).collect(Collectors.toList());
    }

    public UserDto updateUserShift(ShiftDataRequestDto shiftDataRequestDto) {
        UserDto userDto = userService.getUserDto(shiftDataRequestDto.getUserId());
        ShiftStatusEnum validShiftAction = shiftValidationService.validateShitChangeAction(userDto.getShiftStatus(), shiftDataRequestDto.getShiftStatus())
                .orElseThrow(() -> new InvalidShiftChangeActionException("Invalid shift change action"));

        ShiftData shiftData = modelMapper.map(shiftDataRequestDto, ShiftData.class);
        shiftData.setCreatedOn(Timestamp.from(Instant.now()));
        shiftDataRepository.save(shiftData);

        userDto.setShiftStatus(validShiftAction);
        return userService.saveUser(userDto);
    }
}

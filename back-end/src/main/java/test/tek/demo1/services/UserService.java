package test.tek.demo1.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import test.tek.demo1.db.entity.User;
import test.tek.demo1.db.repo.UserRepository;
import test.tek.demo1.dto.UserDto;
import test.tek.demo1.dto.request.UserCreds;
import test.tek.demo1.dto.response.UserValidityDto;
import test.tek.demo1.enums.ShiftStatusEnum;
import test.tek.demo1.exceptions.InvalidUserException;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserValidityDto isValidUser(UserCreds userCreds) {
        User user = userRepository.findById(userCreds.getUserId()).orElseThrow(() -> new InvalidUserException("Invalid User/Password"));
        return new UserValidityDto(userCreds.getUserId(), user!=null);
    }

    public UserDto getUserDto(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceAccessException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto saveUser(UserDto userDto) {
        User user  = modelMapper.map(userDto, User.class);
        return modelMapper.map(userRepository.save(user) , UserDto.class);
    }

    public UserDto registerUser(UserDto userDto) {
        userDto.setShiftStatus(ShiftStatusEnum.NEW_USER);
        User user  = modelMapper.map(userDto, User.class);
        user.setCreatedOn(Timestamp.from(Instant.now()));
        return modelMapper.map(userRepository.save(user) , UserDto.class);
    }
}

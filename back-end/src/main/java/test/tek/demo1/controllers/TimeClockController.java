package test.tek.demo1.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.tek.demo1.dto.UserDto;
import test.tek.demo1.dto.request.ShiftDataRequestDto;
import test.tek.demo1.dto.request.UserCreds;
import test.tek.demo1.dto.response.ShiftDataDto;
import test.tek.demo1.dto.response.UserValidityDto;
import test.tek.demo1.services.ShiftDataService;
import test.tek.demo1.services.UserService;

import java.util.List;

@RestController
public class TimeClockController {
    Logger logger = LoggerFactory.getLogger(TimeClockController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ShiftDataService shiftDataService;

    @PostMapping(value = "/user/check", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserValidityDto> isValidUser(@RequestBody UserCreds userCreds) {
        return ResponseEntity.ok(userService.isValidUser(userCreds));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.getUserDto(userId));
    }

    @PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @GetMapping("/shift/{userId}")
    public ResponseEntity<List<ShiftDataDto>> getShiftData(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(shiftDataService.getShiftData(userId));
    }

    @PutMapping(value = "/shift", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateShift(@RequestBody ShiftDataRequestDto shiftDataRequestDto) {
        return ResponseEntity.ok(shiftDataService.updateUserShift(shiftDataRequestDto));
    }
}

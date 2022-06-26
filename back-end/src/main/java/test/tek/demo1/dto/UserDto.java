package test.tek.demo1.dto;

import lombok.Data;
import test.tek.demo1.enums.ShiftStatusEnum;

@Data
public class UserDto {
    private String userId;
    private ShiftStatusEnum shiftStatus;
}

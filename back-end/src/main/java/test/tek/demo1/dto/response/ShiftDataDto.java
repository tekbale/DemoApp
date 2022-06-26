package test.tek.demo1.dto.response;

import lombok.Data;
import test.tek.demo1.enums.ShiftStatusEnum;

import java.sql.Timestamp;

@Data
public class ShiftDataDto {
    private Long index;
    private ShiftStatusEnum shiftStatus;
    private Timestamp createdOn;
}

package test.tek.demo1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.tek.demo1.enums.ShiftStatusEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftDataRequestDto {
    private String userId;
    private ShiftStatusEnum shiftStatus;
}

package test.tek.demo1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserValidityDto {
    private String userId;
    private Boolean isValidUser;

}

package com.biterush.user_service.model.DTO;

import com.biterush.user_service.model.enums.GENDER;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private GENDER gender;
}

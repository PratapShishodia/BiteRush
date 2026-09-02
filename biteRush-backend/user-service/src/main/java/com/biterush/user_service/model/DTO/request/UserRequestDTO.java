package com.biterush.user_service.model.dto.request;

import com.biterush.user_service.model.enums.GENDER;
import com.biterush.user_service.model.enums.USER_STATUS;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profileImage;
    private Date dateOfBirth;
    private GENDER gender;
    private USER_STATUS status;
}

package com.mp.appusermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOV2 {
    private String name;
    private String lastName;
    private String email;
    private String password;
}

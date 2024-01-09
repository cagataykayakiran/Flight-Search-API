package com.amadeus.dto;

import com.amadeus.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRegisterRequest {

    private String username;
    private String password;
    private Role role;
}

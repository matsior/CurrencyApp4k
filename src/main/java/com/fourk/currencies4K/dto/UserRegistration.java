package com.fourk.currencies4K.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserRegistration {
    private String username;
    private String email;
    private String password;
}

package com.dyneeasy.binding;

import lombok.Data;

@Data
public class AuthenticationForm {
	private String userType;
    private String email;
    private String mobile;
    private String password;
    private String confirmPassword;
}
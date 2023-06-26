package com.sparta.springlv2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Pattern(regexp = "[a-z0-9]")
    @NotBlank
    private String username;
    @Pattern(regexp = "[a-zA-z0-9]")
    @NotBlank
    private String password;
}
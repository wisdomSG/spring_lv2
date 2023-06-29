package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.LoginRequestDto;
import com.sparta.springlv2.dto.SignupRequestDto;
import com.sparta.springlv2.dto.StatusResponseDto;
import com.sparta.springlv2.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<StatusResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);


        String msg = "회원가입 성공";
        StatusResponseDto responseDto = new StatusResponseDto(msg, HttpStatus.OK.value());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/user/login")
    public ResponseEntity<StatusResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String msg = "로그인 성공";
        StatusResponseDto responseDto = new StatusResponseDto(msg, HttpStatus.OK.value());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}

package com.sparta.springlv2.controller;

import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products")
    public String getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // Authentication의 Principal에 저장된 UserDetailsImpl을 가져옴
        User user = userDetails.getUser();
        System.out.println("user.getUsername() = " + user.getUsername());

        String msg = "관리자 사용중";
        return msg;
    }
}
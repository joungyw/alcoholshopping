package com.three.alcoholshoppingmall.project.login.token;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TokenController {

    @PostMapping("valid")
    public ResponseEntity<String> vaildToken(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        System.out.println(token);

        return ResponseEntity.status(HttpStatus.OK).body("aaaa");
    }
}

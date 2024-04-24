package com.three.alcoholshoppingmall.project.login.token;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    private String access_token;
    private String refresh_token;
}

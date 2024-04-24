package com.three.alcoholshoppingmall.project.login.kakao;


import com.three.alcoholshoppingmall.project.login.token.Token;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoLoginService {

    // 인가코드로 accesstoken 받는 함수
    public Token userAuthToken(String token) {

        MultiValueMap<String,String> bodys = new LinkedMultiValueMap<>();

        bodys.add("grant_type","authorization_code");
        bodys.add("client_id","94b443da7db84c565579d43ba563dd3f");
        bodys.add("redirect_uri","http://localhost:3000/auth");
        bodys.add("code",token);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type","application/x-www-form-urlencoded");

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(bodys,headers);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> responseEntity =
                template.exchange(
                        "https://kauth.kakao.com/oauth/token",
                        HttpMethod.POST,
                        entity, String.class);

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());

        userAccessToken(jsonObject.getString("access_token"));

        Token tokens = Token.builder()
                .access_token(jsonObject.getString("access_token"))
                .refresh_token(jsonObject.getString("refresh_token"))
                .build();

        return tokens;
    }

    // accesstoken으로 이메일/닉네임 받는 함수
    public void userAccessToken(String accessToken){

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization","Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity(headers);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> responseEntity =
        template.exchange("https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                entity, String.class);

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        String kakaoAccount = jsonObject.get("kakao_account").toString();
        jsonObject = new JSONObject(kakaoAccount);
        String profile = jsonObject.get("profile").toString();
        JSONObject jsonProfile = new JSONObject(profile);

        System.out.println(jsonProfile.get("nickname"));
        System.out.println("=============================================");
        System.out.println(jsonObject.get("email"));
        System.out.println("=============================================");
    }
}

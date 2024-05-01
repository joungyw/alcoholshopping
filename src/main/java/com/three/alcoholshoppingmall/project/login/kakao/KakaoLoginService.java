package com.three.alcoholshoppingmall.project.login.kakao;


import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
import com.three.alcoholshoppingmall.project.login.token.Token;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class KakaoLoginService {

    private final UserRepository userRepository;

    // 인가코드로 accesstoken 받는 함수
    public Token userAuthToken(String token) {

        MultiValueMap<String,String> bodys = new LinkedMultiValueMap<>();

        bodys.add("grant_type","authorization_code");
        bodys.add("client_id","94b443da7db84c565579d43ba563dd3f");
        bodys.add("redirect_uri","http://localhost:3000/kakaoinfo");
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

        Token tokens = Token.builder()
                .access_token(jsonObject.getString("access_token"))
                .refresh_token(jsonObject.getString("refresh_token"))
                .kakaoUser(null)
                .build();

        Token checkToken = userAccessToken(tokens);

        return checkToken;
    }

    // accesstoken으로 이메일/닉네임 받는 함수
    public Token userAccessToken(Token tokens){

        HttpHeaders headers = new HttpHeaders();

        headers.add("Authorization","Bearer " + tokens.getAccess_token());

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

        User dbuser = userRepository.findByEmail(jsonObject.get("email").toString());

        if(dbuser != null){
            Token token = Token.builder()
                    .access_token(tokens.getAccess_token())
                    .refresh_token(tokens.getRefresh_token())
                    .kakaoUser(KakaoUser.builder()
                            .nickname(dbuser.getNickname())
                            .phone(dbuser.getPhone())
                            .birthdate(dbuser.getBirthdate())
                            .gender(dbuser.getGender())
                            .address(dbuser.getAddress())
                            .address2(dbuser.getAddress2())
                            .build())
                    .build();
            return token;
        }

        return tokens;
    }
}

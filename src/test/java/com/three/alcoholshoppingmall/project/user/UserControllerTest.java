package com.three.alcoholshoppingmall.project.user;

import static com.three.alcoholshoppingmall.project.exception.ErrorCode.DUPLPHONE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void UpdateUserSuccess() {
        // Given
        UserUpdate userUpdate = new UserUpdate("newNickname",  "010-1234-5678", "newAddress", "newAddress2");
        when(userService.updateUser("test@example.com", userUpdate)).thenReturn("회원정보가 수정되었습니다");

        // When
        ResponseEntity<String> response = userController.updateUser(userUpdate);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("회원정보가 수정되었습니다", response.getBody());
    }

    @Test
    public void UpdateUserPhoneAlreadyExists() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        UserUpdate userUpdate = new UserUpdate("newNickname", "01032037533", "newAddress", "newAddress2");
        String jwtToken = "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJsb2dpblRva2VuIiwiZW1haWwiOiJkZGRAbmF2ZXIuY29tIiwicGFzc3dvcmQiOiIkMmEkMTAkUVBUMjNFaHlvVFpTM2YuV0dSTk9ZdUd5cXlCUm1PWkVWSm1KVmtwMjlNOFp6MWcxbUtIdEsiLCJuaWNrbmFtZSI6ImRmZmYiLCJhZGRyZXNzIjoi7KO87IaMIiwiYWRkcmVzczIiOiLsg4HshLjso7zshowiLCJnZW5kZXIiOiJGRU1BTEUiLCJiaXJ0aGRhdGUiOiIxOTk4MDYyMyIsInBob25lIjoiMDEwNTYyMTU2MjEiLCJleHAiOjE3MTY0MzY4MDd9.TxhyrJTr4xv1uR3t1CelhTim97rykY6aRIAIXYipgv6Ui16Plbg0O0hMd1hf7E64";

        //when
        ResultActions resultActions = mockMvc.perform(put("/user/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(userUpdate))
                .header("Authorization", jwtToken)); // JWT 토큰
        // 을 헤더에 추가
        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("DUPLPHONE"))
                .andExpect(jsonPath("$.errorMessage").value("이미 가입된 번호입니다."))
                .andDo(print());
    }
}

package com.three.alcoholshoppingmall.project.login;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Tag(name = "이메일 클래스",description = "이메일 인증용 클래스입니다.")
public class Email {

    @Pattern(regexp = "(^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)",
            message = "이메일 형식을 맞춰주세요")
    private String email;
}

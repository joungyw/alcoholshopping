package com.three.alcoholshoppingmall.project.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    @JsonIgnore
    private Long id;

    private Long alcohol;

    @JsonIgnore
    private User user;

    @NotBlank
    private String writing;

    @NotBlank
    @Min(value = 0, message = "최소 숫자는 0 입니다.")
    @Max(value = 5, message = "최대 숫자는 5 입니다.")
    private int grade;

    private String picture;
}

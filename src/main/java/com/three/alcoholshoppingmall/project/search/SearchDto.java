package com.three.alcoholshoppingmall.project.search;

import com.three.alcoholshoppingmall.project.user.User;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

    private Long id;

    private String email;

    private String searchcontents;


}
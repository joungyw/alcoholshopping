package com.three.alcoholshoppingmall.project.search;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

    private Long id;


    private String searchcontents;

    private User user;

}
package com.three.alcoholshoppingmall.project.search;

import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
    private final AlcoholRepository alcoholRepository;
    private final UserRepository userRepository;


    @Transactional
    public List<Alcohol> searchAlcoholByName(String searchDto, String email) {
        List<Alcohol> list = alcoholRepository.findByNameContaining(searchDto);
        // 이메일이 유효한 경우에만 검색 기록 저장
        searchRepository.searchsave(email, searchDto);
        return list;

    }

    public List<Search> recentSearch(String email) {
        User dbUser = userRepository.findByEmail(email);

        List<Search> list = searchRepository.findAllByUserOrderById(dbUser, PageRequest.of(0, 5));
        if (list == null) {
            return null;
        } else {
            return list;
        }
    }
}





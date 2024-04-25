package com.three.alcoholshoppingmall.project.search;

import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
    private final AlcoholRepository alcoholRepository;
    private final UserRepository userRepository;


    @Transactional
    public List<Alcohol> searchAlcoholByName(String email, String searchcontents) {

        List<Alcohol> list = alcoholRepository.findByNameContaining(searchcontents);

        searchRepository.searchsave(email, searchcontents);

        return list;
    }

    public List<Search> recentSearch(SearchDto searchDto) {
        List<Search> list = searchRepository.recentSearch(searchDto.getUser().getEmail());
        if (list == null) {
            return null;
        } else {
            return list;
        }
    }

}











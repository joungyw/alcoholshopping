package com.three.alcoholshoppingmall.project.search;

import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.three.alcoholshoppingmall.project.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
    private final AlcoholRepository alcoholRepository;
    private final UserRepository userRepository;


    @Transactional
    public List<Alcohol> memberSearch(String searchcontents, String email) {
        System.out.println("여기로 오나");
        List<Alcohol> list = alcoholRepository.findByNameContaining(searchcontents);
        System.out.println(searchcontents);
        searchRepository.searchsave(email, searchcontents);
        System.out.println(list);
        if (searchcontents.isEmpty()) {
            throw new BizException(NULLSEARCH);
        }
        if (searchcontents.length() <= 1) {
            throw new BizException(SEARCHCLENGTH);
        } else {

            return list;
        }
    }

    public List<Search> recentSearch(String email) {
        User dbUser = userRepository.findByEmail(email);

        List<Search> list = searchRepository.findAllByUserOrderByIdDesc(dbUser, PageRequest.of(0, 5));
        if (list == null) {
            throw new BizException(NULLRECENT);
        } else {
            return list;
        }
    }
}












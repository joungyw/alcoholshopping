package com.three.alcoholshoppingmall.project.search;

import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.review.ReviewRepository;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.three.alcoholshoppingmall.project.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
    private final AlcoholRepository alcoholRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<SearchDetail> memberSearch(String searchcontents, String email) {
        System.out.println("여기로 오나");
        Double ratings = alcoholRepository.Rating(searchcontents);
        List<Alcohol> list = alcoholRepository.findByNameContaining(searchcontents);
        System.out.println(searchcontents);
        searchRepository.searchsave(email, searchcontents);
        System.out.println(list);
        if (searchcontents.isEmpty()) {
            throw new BizException(NULLSEARCH);
        }
        if (list.isEmpty()) {
            throw new BizException(NOTFOUNDALCOHOL);
        }
        if (searchcontents.length() <= 1) {
            throw new BizException(SEARCHCLENGTH);
        } else {
            List<SearchDetail> searchDetails = new ArrayList<>();

            for (Alcohol alcohol : list) {

                SearchDetail searchDetail = SearchDetail.builder()
                        .code(alcohol.getCode())
                        .name(alcohol.getName())
                        .price(alcohol.getPrice())
                        .ratingaverage(ratings)
                        .build();
                searchDetails.add(searchDetail);


            }
            return searchDetails;
        }
    }

    public List<Search> recentSearch(String email) {
        User dbUser = userRepository.findByEmail(email);

        List<Search> list = searchRepository.findAllByUserOrderByIdDesc(dbUser, PageRequest.of(0, 5));
        if (list.isEmpty()) {
            throw new BizException(NULLRECENT);
        } else {
            return list;
        }

    }

    @Transactional
    public List<Search> searchDelete(String email, Long id) {
        Optional<Search> optionalSearch = searchRepository.findByUser_EmailAndId(email, id);
        if (optionalSearch.isPresent()) {
            searchRepository.deleteByUser_EmailAndId(email, id);
        } else {
            throw new BizException(NOTFOUNDRECENT);
        }
        return null;
    }
}
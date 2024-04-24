package com.three.alcoholshoppingmall.project.search;

import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
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
    public List<Alcohol> searchAlcoholByName(SearchDto searchDto) {
        Search search = new Search();
        List<Alcohol> list = alcoholRepository.findByNameContaining(searchDto.getSearchcontents());
        // 이메일이 유효한 경우에만 검색 기록 저장
        if (searchDto.getUser() != null) {
            search = search.builder()
                    .searchcontents(searchDto.getSearchcontents())
                    .user(searchDto.getUser())
                    .build();
                    searchRepository.save(search);


            } else {
                    search.setSearchcontents(searchDto.getSearchcontents());
                    searchRepository.save(search);

                }
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











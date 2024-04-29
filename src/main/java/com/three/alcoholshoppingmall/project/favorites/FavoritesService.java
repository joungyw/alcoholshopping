package com.three.alcoholshoppingmall.project.favorites;


import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoritesFRepository;
    private final AlcoholRepository alcoholRepository;
    private final UserRepository userRepository;

    public List<Favoritesalcohol> Favoriteslist(String email) {
        List<String> names = alcoholRepository.MyFavorites(email);

        List<Favoritesalcohol> list = new ArrayList<>();
        for (String name : names) {
            Favoritesalcohol favoritesalcohol = Favoritesalcohol
                    .builder()
                    .name(name)
                    .build();
            list.add(favoritesalcohol);
        }

        return list;
    }



    @Transactional
    public List<Favoritesalcohol> Favorites(Long code, String email) {
        Optional<Favorites> favor = favoritesFRepository.findByUser_EmailAndAlcohol_Code(email,code);
       Alcohol alcohol = alcoholRepository.findByCode(code);
        User user = userRepository.findByEmail(email);

        List<Favoritesalcohol> list = new ArrayList<>();

        if (favor.isPresent()) {
            favoritesFRepository.deleteByUser_EmailAndAlcohol_Code(email,code);
        } else {
            String alcoholname = alcoholRepository.name(code);

            Favorites favorites = new Favorites();
            favorites.setUser(user);
            favorites.setAlcohol(alcohol);
             favoritesFRepository.save(favorites);

             Favoritesalcohol favoritesalcohol = Favoritesalcohol.builder()
                     .name(alcoholname)
                     .build();

             list.add(favoritesalcohol);
        }
        return list;
    }
    @Transactional
    public List<Favorites> FavoritesDelete(Long code, String email) {
        Optional<Favorites> favor = favoritesFRepository.findByUser_EmailAndAlcohol_Code(email,code);
        if (favor.isPresent()) {
            favoritesFRepository.deleteByUser_EmailAndAlcohol_Code(email,code);
        } else {
            throw new NoSuchElementException("해당 즐겨 찾기는 목록에 없습니다.");
        }
        return null;
    }
}



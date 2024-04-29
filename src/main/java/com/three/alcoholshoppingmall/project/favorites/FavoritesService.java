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

    public List<Favorites> Favoriteslist(String email) {

        List<Favorites> list = favoritesFRepository.MyFavorites(email);

        return list;
    }

    @Transactional
    public List<Favorites> Favorites(Long code, String email) {
        Optional<Favorites> favor = favoritesFRepository.findByUser_EmailAndAlcohol_Code(email,code);
       Alcohol alcohol = alcoholRepository.findByCode(code);
        User user = userRepository.findByEmail(email);

        List<Favorites> list = new ArrayList<>();

        if (favor.isPresent()) {
            favoritesFRepository.deleteByUser_EmailAndAlcohol_Code(email,code);
        } else {
            Favorites favorites = new Favorites();
            favorites.setUser(user);
            favorites.setAlcohol(alcohol);
            Favorites savedFavorites = favoritesFRepository.save(favorites);
            list.add(savedFavorites);
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



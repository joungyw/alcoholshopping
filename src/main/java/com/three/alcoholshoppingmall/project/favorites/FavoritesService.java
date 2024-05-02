package com.three.alcoholshoppingmall.project.favorites;


import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
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
        List<String> names = alcoholRepository.MyFavoritesname(email);
        List<Long> codes = alcoholRepository.MyFavoritescode(email);
        List<String> pictures = alcoholRepository.MyFavoritespicture(email);

        List<Favoritesalcohol> list = new ArrayList<>();
        int size = Math.min(names.size(), Math.min(codes.size(), pictures.size()));
        for (int i = 0; i < size; i++) {
            Favoritesalcohol favoritesalcohol = Favoritesalcohol
                    .builder()
                    .code(codes.get(i))
                    .name(names.get(i))
                    .picture(pictures.get(i))
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
            Favorites favorites = new Favorites();
            favorites.setUser(user);
            favorites.setAlcohol(alcohol);
             favoritesFRepository.save(favorites);

             Favoritesalcohol favoritesalcohol = Favoritesalcohol.builder()
                     .code(alcohol.getCode())
                     .name(alcohol.getName())
                     .picture(alcohol.getPicture())
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
            throw new BizException(ErrorCode.NOTFOUNDFAVORITES);
        }
        return null;
    }
}



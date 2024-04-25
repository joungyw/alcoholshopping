package com.three.alcoholshoppingmall.project.favorites;


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


    public List<Favorites> Favoriteslist(String email) {

        List<Favorites> list = favoritesFRepository.MyFavorites(email);

        return list;
    }

    @Transactional
    public List<Favorites> Favorites(FavoritesDTO favoritesDTO) {
        Optional<Favorites> favor = favoritesFRepository.findByEmailAndName(favoritesDTO.getEmail(), favoritesDTO.getName());
        List<Favorites> list = new ArrayList<>();

        if (favor.isPresent()) {
            favoritesFRepository.deleteByEmailAndAndName(favoritesDTO.getEmail(), favoritesDTO.getName());
        } else {
            Favorites favorites = new Favorites();
            favorites.setEmail(favoritesDTO.getEmail());
            favorites.setName(favoritesDTO.getName());
            Favorites savedFavorites = favoritesFRepository.save(favorites);
            list.add(savedFavorites);
        }
        return list;
    }
    @Transactional
    public List<Favorites> FavoritesDelete(FavoritesDTO favoritesDTO) {
        Optional<Favorites> favor = favoritesFRepository.findByEmailAndName(favoritesDTO.getEmail(), favoritesDTO.getName());
        if (favor.isPresent()) {
            favoritesFRepository.deleteByEmailAndAndName(favoritesDTO.getEmail(), favoritesDTO.getName());
        } else {
            throw new NoSuchElementException("해당 즐겨 찾기는 목록에 없습니다.");
        }
        return null;
    }
}



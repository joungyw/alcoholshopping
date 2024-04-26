package com.three.alcoholshoppingmall.project.favorites;


import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorites")

@Tag(name = "favorites", description = "즐겨찾기 페이지 입니다.")
public class FavoritesController {

private final FavoritesService favoritesService;
    @GetMapping("/list")
    @Operation(summary = "즐겨찾기 목록",
            description = "해당 회원이 즐겨찾기에 등록한 정보를 모두 보여 줍니다." +
                    "email 입력이 필요 합니다.")
    public ResponseEntity<List<Favorites>> FavoritesList(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();


        List<Favorites> list = favoritesService.Favoriteslist(user.getEmail());

        return  ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("")
    @Operation(summary = "즐겨찾기 등록",
            description = "선택한 술을 즐겨찾기 목록에 등록 하는 기능으로 이미 있는 술일 경우 즐겨 찾기 목록에서 삭제 됩니다." +
                    "email 과 name에 술이름의 입력이 필요 합니다.")
    public ResponseEntity<List<Favorites>> Favorites(@RequestBody FavoritesDTO favoritesDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        favoritesDTO.setEmail(user.getEmail());

        List<Favorites> list = favoritesService.Favorites(favoritesDTO);

        return  ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("")
    @Operation(summary = "즐겨찾기 삭제",
            description = "즐겨 찾기 목록에서 선택한 술을 지우는 기능입니다." +
                    "email 과 name에 술이름의 입력이 필요 합니다.")
    public ResponseEntity<List<Favorites>> FavoritesDelete(@RequestBody FavoritesDTO favoritesDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        favoritesDTO.setEmail(user.getEmail());

        List<Favorites> list = favoritesService.FavoritesDelete(favoritesDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}

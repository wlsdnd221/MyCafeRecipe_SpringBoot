package com.jw.mycaferecipe.repository.menu;

import com.jw.mycaferecipe.entity.MenuDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuDTO, Long> {

    //메뉴등록
    @Override
    MenuDTO save(MenuDTO menuDTO);

    //메뉴리스트
    @Override
    List<MenuDTO> findAll();
}

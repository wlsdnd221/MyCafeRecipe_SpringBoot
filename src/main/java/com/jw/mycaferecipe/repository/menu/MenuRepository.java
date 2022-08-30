package com.jw.mycaferecipe.repository.menu;

import com.jw.mycaferecipe.entity.MenuDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface MenuRepository extends JpaRepository<MenuDTO, Long> {

    //메뉴등록
    @Override
    MenuDTO save(MenuDTO menuDTO);

    //메뉴리스트
    @Override
    List<MenuDTO> findAll();

    //메뉴상세
    @Override
    Optional<MenuDTO> findById(Long num);

    //메뉴삭제

    @Override
    void deleteById(Long aLong);
}

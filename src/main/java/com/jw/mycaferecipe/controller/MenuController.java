package com.jw.mycaferecipe.controller;

import com.jw.mycaferecipe.entity.MenuDTO;
import com.jw.mycaferecipe.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class MenuController {

    private final MenuService  menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    //메뉴등록 폼으로 이동
    @GetMapping("/menu/enroll")
    public String menuEnrollForm() {
        return "/menu/menuEnrollForm";
    }

    //등록된 메뉴 DB저장
    @PostMapping("/menu/enroll")
    public String menuEnroll(MenuDTO menuDTO, MultipartFile file) throws Exception{
        menuService.enroll(menuDTO, file);
        return "redirect:/";
    }

    //메뉴 리스트
    @GetMapping("/menu/list")
    public String menuList(Model model) {
        List<MenuDTO> menuList = menuService.menuList();
        model.addAttribute("menuList", menuList);
        return "/menu/menuList";
    }

    @GetMapping("/menu/detail")
    public String menuDetail(@RequestParam("num") Long num, Model model) {
        Optional<MenuDTO> mdto = menuService.menuDetail(num);
        model.addAttribute("mdto", mdto.orElse(null));
        return "/menu/menuDetail";
    }
}

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

    //메뉴 폼으로 이동(등록, 수정)
    @GetMapping("/menu/enroll")
    public String menuEnrollForm(@RequestParam(required = false, name = "num") Long num, Model model) {
        // num값이 없으면 신규등록, 있으면 수정
        if(num == null) {
            model.addAttribute("mdto", new MenuDTO());
        }
        else {
            MenuDTO mdto = menuService.menuDetail(num).orElse(null);
            model.addAttribute("mdto", mdto);
        }

        return "/menu/menuEnrollForm";
    }

    //등록, 수정
    @PostMapping("/menu/enroll")
    public String menuEnroll(MenuDTO menuDTO, MultipartFile file) throws Exception{
        menuService.enroll(menuDTO, file);

        return "redirect:/menu/list";
    }

    //메뉴 리스트
    @GetMapping("/menu/list")
    public String menuList(Model model) {
        List<MenuDTO> menuList = menuService.menuList();
        model.addAttribute("menuList", menuList);

        return "/menu/menuList";
    }

    //메뉴 상세
    @GetMapping("/menu/detail")
    public String menuDetail(@RequestParam("num") Long num, Model model) {
        MenuDTO mdto = menuService.menuDetail(num).orElse(null);
        model.addAttribute("mdto", mdto);

        return "/menu/menuDetail";
    }

    //메뉴 삭제
    @GetMapping("/menu/delete")
    public String menuDelete(@RequestParam("num") Long num) {
        menuService.menuDelete(num);

        return "redirect:/menu/list";
    }
}

package com.jw.mycaferecipe.controller;

import com.jw.mycaferecipe.entity.MenuDTO;
import com.jw.mycaferecipe.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class MenuController {

    private final MenuService  menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu/enroll")
    public String menuEnrollForm() {
        return "/menu/menuEnrollForm";
    }

    @PostMapping("/menu/enroll")
    public String menuEnroll(MenuDTO menuDTO, MultipartFile file) throws Exception{
        menuService.enroll(menuDTO, file);
        return "redirect:/";
    }

    @GetMapping("/menu/list")
    public String menuList(Model model) {
        List<MenuDTO> menuList = menuService.menuList();
        model.addAttribute("menuList", menuList);
        return "/menu/menuList";
    }
}

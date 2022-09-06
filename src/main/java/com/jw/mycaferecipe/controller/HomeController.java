package com.jw.mycaferecipe.controller;

import com.jw.mycaferecipe.entity.MenuDTO;
import com.jw.mycaferecipe.service.menu.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final MenuService menuService;

    public HomeController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/")
    public String home(Model model) {
        MenuDTO beverage = menuService.menuList().stream()
                .filter(s -> s.getSort().equals("beverage"))
                .sorted()
                .findFirst()
                .orElse(null);


        MenuDTO food = menuService.menuList().stream()
                .filter(s -> s.getSort().equals("food"))
                .findAny()
                .orElse(null);

        model.addAttribute("beverage", beverage);
        model.addAttribute("food", food);
        return "home";
    }

}

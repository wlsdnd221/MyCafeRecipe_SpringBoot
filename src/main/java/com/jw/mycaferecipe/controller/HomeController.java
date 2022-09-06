package com.jw.mycaferecipe.controller;

import com.jw.mycaferecipe.entity.MenuDTO;
import com.jw.mycaferecipe.service.menu.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;

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
                .sorted(Comparator.comparing(MenuDTO::getNum).reversed())
                .findFirst()
                .orElse(null);

        MenuDTO food = menuService.menuList().stream()
                .filter(s -> s.getSort().equals("food"))
                .sorted(Comparator.comparing(MenuDTO::getNum).reversed())
                .findFirst()
                .orElse(null);

        model.addAttribute("beverage", beverage);
        model.addAttribute("food", food);
        return "home";
    }

    @GetMapping("/map")
    public String map() {
        return "map";
    }
}

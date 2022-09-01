package com.jw.mycaferecipe.controller;

import com.jw.mycaferecipe.entity.BoardDTO;
import com.jw.mycaferecipe.entity.MenuDTO;
import com.jw.mycaferecipe.service.board.BoardService;
import com.jw.mycaferecipe.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final MenuService menuService;

    @Autowired
    public BoardController(BoardService boardService, MenuService menuService) {
        this.boardService = boardService;
        this.menuService = menuService;
    }



    //레시피목록
    @GetMapping("/board/list")
    public String boardList(Model model) {
        List<BoardDTO> boardList = boardService.boardList();
        model.addAttribute("boardList", boardList);
        return "/board/boardList";
    }


    //레시피등록
    @GetMapping("/board/enroll")
    public String boardEnrollForm(Model model) {
        List<MenuDTO> menuList = menuService.menuList();
        model.addAttribute("menuList", menuList);
        return "/board/boardEnrollForm";
    }

    @PostMapping("/board/enroll")
    public String boardEnroll() {
        return "redirect:/board/list";
    }
}

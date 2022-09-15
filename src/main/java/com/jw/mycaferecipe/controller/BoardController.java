package com.jw.mycaferecipe.controller;

import com.jw.mycaferecipe.entity.BoardDTO;
import com.jw.mycaferecipe.entity.MenuDTO;
import com.jw.mycaferecipe.service.board.BoardService;
import com.jw.mycaferecipe.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    public String boardList(Model model
                            ,@PageableDefault(page = 0, size = 5, sort = "num", direction = Sort.Direction.DESC) Pageable pageable
                            ,@RequestParam(required = false, name = "category") String category
                            ,String search) {
        Page<BoardDTO> boardList = null;

        if(search == null) {
            boardList = boardService.boardList(pageable);
        }
        else {
            boardList = boardService.boardSearchList(category, search, pageable);
        }

        //페이징처리
        List<Integer> pageList = paging(boardList, model);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);
        return "/board/boardList";
    }

    //페이징처리
    public List<Integer> paging(Page<BoardDTO> boardList, Model model) {
        int nowPage = boardList.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage-4, 1);
        int endPage = Math.min(nowPage+5, boardList.getTotalPages());

        List<Integer> pageList = new ArrayList<Integer>();
        pageList.add(nowPage);
        pageList.add(startPage);
        pageList.add(endPage);

        return pageList;
    }


    //레시피 폼으로 이동(등록, 수정)
    @GetMapping("/board/enroll")
    public String boardEnrollForm(@RequestParam(required = false, name = "num") Long num , Model model) {
        List<MenuDTO> menuList = menuService.menuList();
        model.addAttribute("menuList", menuList);

        // num값이 없으면 신규등록, 있으면 수정
        if(num == null) {
            model.addAttribute("bdto", new BoardDTO());
        }
        else {
            BoardDTO bdto = boardService.boardDetail(num).orElse(null);
            model.addAttribute("bdto", bdto);
        }
        
        return "/board/boardEnrollForm";
    }

    @PostMapping("/board/enroll")
    public String boardEnroll(BoardDTO boardDTO) {
        boardService.boardEnroll(boardDTO);

        return "redirect:/board/list";
    }

    //레시피상세
    @GetMapping("/board/detail")
    public String boardDetail(@RequestParam("num") Long num, Model model) {

        boardService.readCount(num); //조회수증가

        BoardDTO boardDTO = boardService.boardDetail(num).orElse(null);
        model.addAttribute("bdto", boardDTO);

        return "/board/boardDetail";
    }

    //레시피삭제
    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("num") Long num) {
        boardService.boardDelete(num);
        return "redirect:/board/list";
    }
}

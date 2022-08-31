package com.jw.mycaferecipe.controller;

import com.jw.mycaferecipe.entity.MemberDTO;
import com.jw.mycaferecipe.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원등록 폼으로 이동
    @GetMapping("/join/new")
    public String joinFrom() {
        return "members/joinForm";
    }

    //DB에 회원정보 저장
    @PostMapping("/join/new")
    public String join(MemberDTO memberDTO) {
        memberService.join(memberDTO);

        return "redirect:/";
    }

    /*회원목록
    @GetMapping("/members")
    public String allMember(Model model) {
        List<MemberDTO> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
     */

    //로그인
    @PostMapping("/login")
    public String login(@RequestParam(value = "id", required = false, defaultValue = "") String id,
                        @RequestParam(value = "pw", required = false, defaultValue = "") String pw) {
        return "/";
    }
}

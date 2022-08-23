package com.jw.mycaferecipe.controller;

import com.jw.mycaferecipe.entity.MemberDTO;
import com.jw.mycaferecipe.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join/new")
    public String joinFrom() {
        return "members/joinForm";
    }

    @PostMapping("/join/new")
    public String join(MemberDTO memberDTO) {
        memberService.join(memberDTO);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String allMember(Model model) {
        List<MemberDTO> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

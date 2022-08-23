package com.jw.mycaferecipe.service;

import com.jw.mycaferecipe.entity.MemberDTO;
import com.jw.mycaferecipe.repository.member.MemberRepository;
import com.jw.mycaferecipe.service.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("홍길동");
        memberDTO.setId("admin");
        memberDTO.setPw("1234");
        memberDTO.setAddress("경기도 수원시");
        memberDTO.setEmail("admin@naver.com");
        memberDTO.setPhone("010-0000-0000");

        // when
        Long saveNum = memberService.join(memberDTO);

        // then
        MemberDTO findMember = memberService.findOne(saveNum).get();
        assertThat(memberDTO.getId()).isEqualTo(findMember.getId());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId("admin");

        MemberDTO memberDTO2 = new MemberDTO();
        memberDTO2.setId("admin");

        //when
        memberService.join(memberDTO1);

        /*
        try {
            memberService.join(memberDTO2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
        }
        */

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(memberDTO2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }

}
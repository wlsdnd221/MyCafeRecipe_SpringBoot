package com.jw.mycaferecipe.service.member;

import com.jw.mycaferecipe.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JpaMemberRepository(em);
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);

    }



}

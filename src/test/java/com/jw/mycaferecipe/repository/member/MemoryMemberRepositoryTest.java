package com.jw.mycaferecipe.repository.member;

import com.jw.mycaferecipe.entity.MemberDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("홍길동");
        memberDTO.setId("admin");
        memberDTO.setPw("1234");
        memberDTO.setAddress("경기도 수원시");
        memberDTO.setEmail("admin@naver.com");
        memberDTO.setPhone("010-0000-0000");

        memoryMemberRepository.save(memberDTO);

        MemberDTO result = memoryMemberRepository.findByNum(memberDTO.getNum()).get();
        assertThat(memberDTO).isEqualTo(result);
    }

    @Test
    public void findById() {
        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId("admin1");
        memoryMemberRepository.save(memberDTO1);

        MemberDTO memberDTO2 = new MemberDTO();
        memberDTO2.setId("admin2");
        memoryMemberRepository.save(memberDTO2);

        MemberDTO result = memoryMemberRepository.findById("admin1").get();

        assertThat(result).isEqualTo(memberDTO1);
    }

    @Test
    public void findAll() {
        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setId("admin1");
        memoryMemberRepository.save(memberDTO1);

        MemberDTO memberDTO2 = new MemberDTO();
        memberDTO2.setId("admin2");
        memoryMemberRepository.save(memberDTO2);

        List<MemberDTO> result = memoryMemberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}

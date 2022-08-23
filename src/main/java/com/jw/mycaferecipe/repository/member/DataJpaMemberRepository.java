package com.jw.mycaferecipe.repository.member;

import com.jw.mycaferecipe.entity.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaMemberRepository extends JpaRepository<MemberDTO, Long>, MemberRepository {

    @Override
    MemberDTO save(MemberDTO memberDTO);
}

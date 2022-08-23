package com.jw.mycaferecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jw.mycaferecipe.domain.MemberDTO;

public interface MemberRepository extends JpaRepository<MemberDTO, Long> {

    MemberDTO save(MemberDTO memberDTO);


}

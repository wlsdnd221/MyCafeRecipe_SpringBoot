package com.jw.mycaferecipe.repository;

import com.jw.mycaferecipe.entity.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jw.mycaferecipe.entity.MenuDTO;

public interface MemberRepository extends JpaRepository<MemberDTO, Long> {

    MemberDTO save(MemberDTO memberDTO);


}

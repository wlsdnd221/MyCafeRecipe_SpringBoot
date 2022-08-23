package com.jw.mycaferecipe.repository.member;

import com.jw.mycaferecipe.entity.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    MemberDTO save(MemberDTO memberDTO);
    Optional<MemberDTO> findByNum(Long num);
    Optional<MemberDTO> findById(String id);
    List<MemberDTO> findAll();
}

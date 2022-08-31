package com.jw.mycaferecipe.repository.member;

import com.jw.mycaferecipe.entity.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataJpaMemberRepository extends JpaRepository<MemberDTO, Long>, MemberRepository {

    @Override
    MemberDTO save(MemberDTO memberDTO);

    @Override
    Optional<MemberDTO> findById(String id);
}

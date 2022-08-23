package com.jw.mycaferecipe.service.member;

import com.jw.mycaferecipe.entity.MemberDTO;
import com.jw.mycaferecipe.repository.member.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(MemberDTO memberDTO) {
        // 같은 아이디 중복x
        duplicateId(memberDTO);

        memberRepository.save(memberDTO);
        return memberDTO.getNum();
    }

    private void duplicateId(MemberDTO memberDTO) {
        memberRepository.findById(memberDTO.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<MemberDTO> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<MemberDTO> findOne(Long memberNum) {
        return memberRepository.findByNum(memberNum);
    }

}

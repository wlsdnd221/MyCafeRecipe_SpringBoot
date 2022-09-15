package com.jw.mycaferecipe.service.member;

import com.jw.mycaferecipe.entity.MemberDTO;
import com.jw.mycaferecipe.entity.Role;
import com.jw.mycaferecipe.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입
     */
    public Long join(MemberDTO memberDTO) {

        duplicateId(memberDTO); // 같은 아이디 중복체크

        String encodedPassword = passwordEncoder.encode(memberDTO.getPw()); // 비밀번호 암호화
        memberDTO.setPw(encodedPassword);

        memberDTO.setEnabled(true); // 권한설정

        Role role = new Role();
        role.setNum(1l); // 하드코딩(원래는 db에서 받아와야 됨)
        memberDTO.getRoleList().add(role);
        memberRepository.save(memberDTO);
        return memberDTO.getNum();
    }

    /**
     * 중복아이디 체크
     */
    private void duplicateId(MemberDTO memberDTO) {
        memberRepository.findById(memberDTO.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

//    /**
//     * 전체 회원 조회
//     */
//    public List<MemberDTO> findMembers() {
//        return memberRepository.findAll();
//    }

    /**
     * 회원 조회
     */
    public Optional<MemberDTO> findOne(Long memberNum) {
        return memberRepository.findByNum(memberNum);
    }

}

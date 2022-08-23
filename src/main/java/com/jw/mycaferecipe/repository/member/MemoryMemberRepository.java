package com.jw.mycaferecipe.repository.member;

import com.jw.mycaferecipe.entity.MemberDTO;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, MemberDTO> store= new HashMap<>();
    private static long sequence = 0L;

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        memberDTO.setNum(++sequence);
        store.put(memberDTO.getNum(), memberDTO);
        return memberDTO;

    }

    @Override
    public Optional<MemberDTO> findByNum(Long num) {
        return  Optional.ofNullable(store.get(num));
    }

    @Override
    public Optional<MemberDTO> findById(String id) {
        return store.values().stream()
                .filter(memberDTO -> memberDTO.getId().equals(id))
                .findAny();
    }

    @Override
    public List<MemberDTO> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

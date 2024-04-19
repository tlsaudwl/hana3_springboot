package com.study.Ex14ReadDB.service;

import com.study.Ex14ReadDB.domain.member.Member;
import com.study.Ex14ReadDB.domain.member.MemberRepository;
import com.study.Ex14ReadDB.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long save(final MemberSaveRequestDto dto){
        Member member = memberRepository.save(dto.toSaveEntity());
        return member.getMemberIdx();
    }

    @Transactional
    public boolean findByMemberId(String memberId){
        Optional<Member> optional = memberRepository.findByMemberId(memberId);
        if(optional.isPresent()){
            return true;
        } else{
            return false;
        }
    }

}

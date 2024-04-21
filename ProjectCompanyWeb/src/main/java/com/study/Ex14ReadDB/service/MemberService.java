package com.study.Ex14ReadDB.service;

import com.study.Ex14ReadDB.domain.member.Member;
import com.study.Ex14ReadDB.domain.member.MemberRepository;
import com.study.Ex14ReadDB.dto.MemberResponseDto;
import com.study.Ex14ReadDB.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public enum LoginResult {
        SUCCESS,
        ID_NOT_FOUND,
        INVALID_PASSWORD
    }

    @Transactional(readOnly = true)
    public LoginResult login(String loginId, String loginPw) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(loginId);
        if (optionalMember.isEmpty()) {
            return LoginResult.ID_NOT_FOUND;
        }

        Member member = optionalMember.get();
        if (!member.getMemberPw().equals(loginPw)) {
            return LoginResult.INVALID_PASSWORD;
        }

        return LoginResult.SUCCESS;
    }

    @Transactional(readOnly = true)
    public String findIdByMemberNameAndMemberEmail(String memberName, String memberEmail) {
        Optional<Member> optional = memberRepository.findIdByMemberNameAndMemberEmail(memberName, memberEmail);
        if (optional.isPresent()) {
            return optional.get().getMemberId();
        } else {
            return "회원정보가 없습니다";
        }
    }

    @Transactional(readOnly = true)
    public String findPwByMemberNameAndMemberEmailAndMemberId(String memberName, String memberEmail, String memberId){
        Optional<Member> optional = memberRepository.findPwByMemberNameAndMemberEmailAndMemberId(memberName, memberEmail, memberId);
        if(optional.isPresent()){
            return optional.get().getMemberPw();
        } else {
            return "회원정보가 없습니다";
        }
    }

    @Transactional(readOnly = true)
    public long memberNum() {
        return memberRepository.count();
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> list = memberRepository.findAll();
        return list.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findMembersByKeyword(String searchKeyword) {
        List<Member> list = memberRepository.findMembersByKeyword(searchKeyword.toLowerCase());
        return list.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findMembersByMemberId(String searchKeyword) {
        List<Member> list = memberRepository.findMembersByMemberId(searchKeyword.toLowerCase());
        return list.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findMembersByMemberName(String searchKeyword) {
        List<Member> list = memberRepository.findMembersByMemberName(searchKeyword.toLowerCase());
        return list.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findMembersByMemberEmail(String searchKeyword) {
        List<Member> list = memberRepository.findMembersByMemberEmail(searchKeyword.toLowerCase());
        return list.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }


}

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

//    @Transactional(readOnly = true)
//    public MemberResponseDto findByMemberIdAndMemberPw(String memberId, String memberPw) {
//        Optional<Member> optional = memberRepository.findByMemberIdAndMemberPw(memberId, memberPw);
//        if (optional.isPresent()) {
//            return MemberResponseDto.builder()
//                    .memberId(optional.get().getMemberId())
//                    .build();
//        } else {
//            return null;
//        }
//
//    }
//
//    @Transactional(readOnly = true)
//    public boolean findByMemberPw(String memberPw) {
//        Optional<Member> optional = memberRepository.findByMemberPw(memberPw);
//        if (optional.isPresent()) {
//            return true;
//        } else {
//            return false;
//        }
//    }

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

}

package com.study.Ex14ReadDB.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findIdByMemberNameAndMemberEmail(String memberName, String memberEmail);

    Optional<Member> findPwByMemberNameAndMemberEmailAndMemberId(String memberName, String memberEmail, String memberId);


}

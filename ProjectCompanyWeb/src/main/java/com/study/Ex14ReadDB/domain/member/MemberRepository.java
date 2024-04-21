package com.study.Ex14ReadDB.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findIdByMemberNameAndMemberEmail(String memberName, String memberEmail);

    Optional<Member> findPwByMemberNameAndMemberEmailAndMemberId(String memberName, String memberEmail, String memberId);

    @Query(value = "SELECT * FROM company_member m WHERE m.member_id LIKE %:keyword% OR m.member_name LIKE %:keyword% OR m.member_email LIKE %:keyword%", nativeQuery = true)
    List<Member> findMembersByKeyword(String keyword);
    @Query(value = "SELECT * FROM company_member m WHERE m.member_id LIKE %:keyword%", nativeQuery = true)
    List<Member> findMembersByMemberId(String keyword);
    @Query(value = "SELECT * FROM company_member m WHERE m.member_name LIKE %:keyword%", nativeQuery = true)
    List<Member> findMembersByMemberName(String keyword);
    @Query(value = "SELECT * FROM company_member m WHERE m.member_email LIKE %:keyword%", nativeQuery = true)
    List<Member> findMembersByMemberEmail(String keyword);

}

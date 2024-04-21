package com.study.Ex14ReadDB.domain.admin;

import com.study.Ex14ReadDB.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByMemberId(String memberId);

}

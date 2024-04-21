package com.study.Ex14ReadDB.service;

import com.study.Ex14ReadDB.domain.admin.Admin;
import com.study.Ex14ReadDB.domain.admin.AdminRepository;
import com.study.Ex14ReadDB.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public enum LoginResult {
        SUCCESS,
        ID_NOT_FOUND,
        INVALID_PASSWORD
    }

    @Transactional(readOnly = true)
    public AdminService.LoginResult login(String memberId, String memberPw) {
        Optional<Admin> optional = adminRepository.findByMemberId(memberId);
        if (optional.isEmpty()) {
            return AdminService.LoginResult.ID_NOT_FOUND;
        }

        Admin admin = optional.get();
        if (!admin.getMemberPw().equals(memberPw)) {
            return AdminService.LoginResult.INVALID_PASSWORD;
        }

        return AdminService.LoginResult.SUCCESS;
    }
}

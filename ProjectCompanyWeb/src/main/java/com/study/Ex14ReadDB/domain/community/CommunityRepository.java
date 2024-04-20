package com.study.Ex14ReadDB.domain.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    List<Community> findByNoticeTitleContaining(String title);
    List<Community> findByNoticeContentContaining(String content);
}

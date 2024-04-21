package com.study.Ex14ReadDB.domain.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    List<Community> findByNoticeTitleContaining(String title);
    List<Community> findByNoticeContentContaining(String content);

    @Query(value = "SELECT * FROM company_notice n WHERE n.notice_title LIKE %:keyword% OR n.notice_content LIKE %:keyword% OR n.notice_member_id LIKE %:keyword%", nativeQuery = true)
    List<Community> findNoticesByKeyword(String keyword);

    @Query(value = "SELECT * FROM company_notice n WHERE n.notice_title LIKE %:keyword%", nativeQuery = true)
    List<Community> findNoticesByNoticeTitle(String keyword);

    @Query(value = "SELECT * FROM company_notice n WHERE n.notice_content LIKE %:keyword%", nativeQuery = true)
    List<Community> findNoticesByNoticeContent(String keyword);

    @Query(value = "SELECT * FROM company_notice n WHERE n.notice_member_id LIKE %:keyword%", nativeQuery = true)
    List<Community> findNoticesByNoticeId(String keyword);

    Community save(Community community);
}

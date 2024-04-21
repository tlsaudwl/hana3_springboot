package com.study.Ex14ReadDB.domain.customerqna;

import com.study.Ex14ReadDB.domain.community.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerQnaRepository extends JpaRepository<CustomerQna, Long> {
    @Query(value = "SELECT * FROM company_qna q WHERE q.qna_title LIKE %:keyword%", nativeQuery = true)
    List<CustomerQna> searchByQnaTitle(String keyword);

    @Query(value = "SELECT * FROM company_qna q WHERE q.qna_content LIKE %:keyword%", nativeQuery = true)
    List<CustomerQna> searchByQnaContent(String keyword);

    @Query(value = "SELECT * FROM company_qna q WHERE q.qna_name LIKE %:keyword%", nativeQuery = true)
    List<CustomerQna> searchByQnaName(String keyword);
}

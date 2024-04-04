package com.study.Ex14ReadDB.entity;

import com.study.Ex14ReadDB.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository
        extends JpaRepository<MemberEntity, Long> {
    //기본메소드
    //1.findAll  : select *
    //2.findById : select *  where id = :id
    //3.count() : select count(*)
    //4.save() : update, insert
    //5.delete() : delete

    // 쿼리 메소드, JPQL, NativeSQL 아래에 등록한다.
}
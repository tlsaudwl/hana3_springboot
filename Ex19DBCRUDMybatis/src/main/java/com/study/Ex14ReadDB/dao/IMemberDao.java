package com.study.Ex14ReadDB.dao;

import com.study.Ex14ReadDB.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// @Mapper : 인터페이스 DAO와 MyBatis XML와 연결하는 용도
@Mapper
public interface IMemberDao {
    // list
    public List<MemberDto> list();
    // select count(*)
    public int count();
    // insert
    public int insert(MemberDto dto);
    public int insertMap(Map map);
    // select where id=10
    public Optional<MemberDto> findById(int id);
    // update
    public int update(MemberDto dto);
    // delete
    public int delete(int id);
    // map.put("id", id)
    // map.put("userId", userId);
    public int deleteMap(int id, String userId);

}

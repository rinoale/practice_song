package com.rinoale.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rinoale.vo.WritesVo;

@Repository(value = "writesMapper")
public interface WritesMapper {
    List<WritesVo> select();

    WritesVo selectOne(int seq);
    void insert(WritesVo writesVo);
    void update(WritesVo writesVo);
    void delete(int seq);
}

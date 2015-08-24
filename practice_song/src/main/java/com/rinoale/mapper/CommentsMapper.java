package com.rinoale.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rinoale.vo.CommentsVo;

@Repository(value = "commentsMapper")
public interface CommentsMapper {
    List<CommentsVo> select(CommentsVo commentsVo);

    CommentsVo selectOne(int seq);
    void insert(CommentsVo commentsVo);
    void update(CommentsVo commentsVo);
    void delete(int seq);
}

package com.rinoale.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rinoale.mapper.CommentsMapper;
import com.rinoale.vo.CommentsVo;

@Service("commentsDao")
public class CommentsDao {
    @Autowired
    private CommentsMapper commentsMapper;

    public List<CommentsVo> getSelect(CommentsVo commentsVo) {
        return this.commentsMapper.select(commentsVo);
    }

    public CommentsVo getSelectOne(int seq) {
        return this.commentsMapper.selectOne(seq);
    }

    public void insert(CommentsVo commentsVo) {
         this.commentsMapper.insert(commentsVo);
    }

    public void update(CommentsVo commentsVo) {
         this.commentsMapper.update(commentsVo);
    }

    public void delete(int seq) {
         this.commentsMapper.delete(seq);
    }
}

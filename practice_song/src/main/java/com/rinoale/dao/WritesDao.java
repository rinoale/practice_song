package com.rinoale.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rinoale.mapper.WritesMapper;
import com.rinoale.vo.IndexVo;
import com.rinoale.vo.WritesVo;

@Service("writesDao")
public class WritesDao {
    @Autowired
    private WritesMapper writesMapper;

    public List<WritesVo> getSelect(IndexVo indexVo) {
        return this.writesMapper.select(indexVo);
    }
    
    public WritesVo getSelectOne(int seq) {
        return this.writesMapper.selectOne(seq);
    }

    public void insert(WritesVo writesVo) {
         this.writesMapper.insert(writesVo);
    }

    public void update(WritesVo writesVo) {
         this.writesMapper.update(writesVo);
    }

    public void delete(int seq) {
         this.writesMapper.delete(seq);
    }
}

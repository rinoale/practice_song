package com.systran.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systran.mapper.TbPatternDataMapper;
import com.systran.vo.DBSearchVo;
import com.systran.vo.IndexVo;
import com.systran.vo.TbPatternDataVo;

@Service("tb_pattern_data_Dao")
public class TbPatternDataDao {
	@Autowired
	private TbPatternDataMapper tb_pattern_data_Mapper;
	
	public List<TbPatternDataVo> getSelect(IndexVo indexVo) {
        return this.tb_pattern_data_Mapper.select(indexVo);
    }

    public TbPatternDataVo getSelectOne(int seq) {
        return this.tb_pattern_data_Mapper.selectOne(seq);
    }

    public void insert(TbPatternDataVo tb_pattern_data_Vo) {
         this.tb_pattern_data_Mapper.insert(tb_pattern_data_Vo);
    }

    public void update(TbPatternDataVo tb_pattern_data_Vo) {
         this.tb_pattern_data_Mapper.update(tb_pattern_data_Vo);
    }

    public void delete(List<Integer> seq_list) {
         this.tb_pattern_data_Mapper.delete(seq_list);
    }
    
    public int getTotalCount()
    {
    	return this.tb_pattern_data_Mapper.totalCount();
    }
    
    public List<TbPatternDataVo> getSearch(DBSearchVo dbSearchVo)
    {
    	return this.tb_pattern_data_Mapper.search(dbSearchVo);
    }
    
    @SuppressWarnings("rawtypes")
	public HashMap getCheckDuplication(TbPatternDataVo tb_pattern_data_Vo)
    {
    	return this.tb_pattern_data_Mapper.checkDuplication(tb_pattern_data_Vo);
    }
    
    public int getSearchCount(DBSearchVo dbSearchVo)
    {
    	return this.tb_pattern_data_Mapper.searchCount(dbSearchVo);
    }
    
    public void deleteAll()
    {
    	this.tb_pattern_data_Mapper.deleteAll();
    }
}

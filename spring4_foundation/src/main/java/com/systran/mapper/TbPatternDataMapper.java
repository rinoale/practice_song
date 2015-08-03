package com.systran.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.systran.vo.DBSearchVo;
import com.systran.vo.IndexVo;
import com.systran.vo.TbPatternDataVo;


@Repository(value="tb_pattern_data_Mapper")
public interface TbPatternDataMapper {
	List<TbPatternDataVo> select(IndexVo indexVo);
	
	TbPatternDataVo selectOne(int SEQ);
	void insert(TbPatternDataVo tb_pattern_data_Vo);
	void update(TbPatternDataVo tb_pattern_data_Vo);
	void delete(List<Integer> seq_list);
	int totalCount();
	
	List<TbPatternDataVo> search(DBSearchVo dbSearchVo);
	
	@SuppressWarnings("rawtypes")
	HashMap checkDuplication(TbPatternDataVo tb_pattern_data_Vo);
	
	int searchCount(DBSearchVo dbSearchVo);
	
	void deleteAll();
}

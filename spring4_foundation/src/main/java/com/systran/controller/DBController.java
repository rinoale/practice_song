package com.systran.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.systran.dao.TbPatternDataDao;
import com.systran.parser.JsonStringParser;
import com.systran.vo.DBSearchVo;
import com.systran.vo.IndexVo;
import com.systran.vo.TbPatternDataVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DBController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TbPatternDataDao tb_pattern_data_Dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "dbAccess", method = RequestMethod.GET)
	public String DBAccess(Locale locale, HttpServletRequest req, HttpServletResponse res, Model model) {
		int fromIndex=Integer.parseInt(req.getParameter("fromIndex"));
		int howMany=Integer.parseInt(req.getParameter("howMany"));
		String sortBy=(String)req.getParameter("sortBy");
		String ASC_or_DESC=(String)req.getParameter("ASC_or_DESC");
				
		IndexVo indexVo=new IndexVo();
		
		indexVo.setFromIndex(fromIndex);
		indexVo.setHowMany(howMany);
		indexVo.setSortBy(sortBy);
		indexVo.setASC_or_DESC(ASC_or_DESC);
		
		List<TbPatternDataVo> tb_pattern_data_list=tb_pattern_data_Dao.getSelect(indexVo);
		
		int totalCount=tb_pattern_data_Dao.getTotalCount();
		
		model.addAttribute("TB_PATTERN_DATA_LIST", tb_pattern_data_list);
		model.addAttribute("totalCount", totalCount);
		
		return "jsonView";
	}
	
	@RequestMapping(value = "dbSearch", method = RequestMethod.GET)
	public String DBSearch(Locale locale, HttpServletRequest req, HttpServletResponse res, Model model) {
		String chosen_column=(String)req.getParameter("chosen_column");
		String text_to_search=(String)req.getParameter("text_to_search");
		int fromIndex=Integer.parseInt(req.getParameter("fromIndex"));
		int howMany=Integer.parseInt(req.getParameter("howMany"));
		String ASC_or_DESC=(String)req.getParameter("ASC_or_DESC");
		String sortBy=(String)req.getParameter("sortBy");
		
		DBSearchVo dbSearchVo=new DBSearchVo();
		
		dbSearchVo.setChosen_column(chosen_column);
		dbSearchVo.setText_to_search(text_to_search);
		
		IndexVo indexVo=new IndexVo();
		indexVo.setHowMany(howMany);
		indexVo.setFromIndex(fromIndex);
		indexVo.setASC_or_DESC(ASC_or_DESC);
		indexVo.setSortBy(sortBy);
		
		dbSearchVo.setIndexVo(indexVo);
		
		
		List<TbPatternDataVo> tb_pattern_data_list=tb_pattern_data_Dao.getSearch(dbSearchVo);
		
		int totalCount=tb_pattern_data_Dao.getSearchCount(dbSearchVo);

		model.addAttribute("TB_PATTERN_DATA_LIST", tb_pattern_data_list);
		model.addAttribute("totalCount", totalCount);
		
		return "jsonView";
	}
	
	@RequestMapping(value = "dbEdit", method = RequestMethod.GET)
	public String DBEdit(Locale locale, HttpServletRequest req, HttpServletResponse res, Model model) {
		int SEQ=Integer.parseInt(req.getParameter("SEQ"));
		String ORG_PTTRN=(String)req.getParameter("ORG_PTTRN");
		String TRNST_PTTRN=(String)req.getParameter("TRNST_PTTRN");
		
		System.out.println(SEQ);
		
		TbPatternDataVo tb_pattern_data_Vo=new TbPatternDataVo();
		tb_pattern_data_Vo.setSEQ(SEQ);
		tb_pattern_data_Vo.setORG_PTTRN(ORG_PTTRN);
		tb_pattern_data_Vo.setTRNST_PTTRN(TRNST_PTTRN);
		
		
		
		if(tb_pattern_data_Dao.getCheckDuplication(tb_pattern_data_Vo)!=null)
		{
			if(tb_pattern_data_Dao.getCheckDuplication(tb_pattern_data_Vo).get("COUNTOFB").toString().equals("1"))
			{
				model.addAttribute("result", "이미 존재하는 패턴입니다.");
			}
			else
			{
				tb_pattern_data_Dao.update(tb_pattern_data_Vo);
	
				model.addAttribute("result", "입력 완료");
			}
		}
		else
		{
			tb_pattern_data_Dao.update(tb_pattern_data_Vo);
			
			model.addAttribute("result", "입력 완료");
		}
		
		
		return "jsonView";
	}
	
	@RequestMapping(value = "dbInsert", method = RequestMethod.GET)
	public String DBInsert(Locale locale, HttpServletRequest req, HttpServletResponse res, Model model) {
		String ORG_PTTRN=(String)req.getParameter("ORG_PTTRN");
		String TRNST_PTTRN=(String)req.getParameter("TRNST_PTTRN");
		
		
		TbPatternDataVo tb_pattern_data_Vo=new TbPatternDataVo();
		tb_pattern_data_Vo.setORG_PTTRN(ORG_PTTRN);
		tb_pattern_data_Vo.setTRNST_PTTRN(TRNST_PTTRN);
		
		@SuppressWarnings("rawtypes")
		HashMap resultMap=tb_pattern_data_Dao.getCheckDuplication(tb_pattern_data_Vo);
		if(resultMap!=null)
		{
			if(resultMap.get("COUNTOFB").toString().equals("1"))
			{
				model.addAttribute("result", "이미 존재하는 패턴쌍입니다.");
			}
			else
			{
				model.addAttribute("result", "원문패턴이 이미 존재하는 패턴입니다.");
				model.addAttribute("TRNST_PTTRN_in_DB", resultMap.get("TRNST_PTTRN").toString());
				model.addAttribute("SEQ", resultMap.get("SEQ").toString());
			}
		}
		else
		{
			
			tb_pattern_data_Dao.insert(tb_pattern_data_Vo);
			
			model.addAttribute("result", "입력 완료");
		}
		
		
		return "jsonView";
	}
	
	@RequestMapping(value = "dbDelete", method = RequestMethod.GET)
	public String DBDelete(@RequestParam(value="SEQ_LIST") String[] seq_list_array,Locale locale, HttpServletRequest req, HttpServletResponse res, Model model) {

		
		System.out.println(seq_list_array.toString());
		
		JsonStringParser jsonStringParser=new JsonStringParser();
		
		List<String> string_list=jsonStringParser.JsonStringifyParser(seq_list_array);
		
		List<Integer> seq_list=new ArrayList<Integer>();
		
		for(int i=0;i<string_list.size();i++)
		{
			seq_list.add(Integer.parseInt(string_list.get(i)));
		}

		
		
		tb_pattern_data_Dao.delete(seq_list);


		model.addAttribute("result", "삭제 완료");
		
		return "jsonView";
	}
	
	@RequestMapping(value = "dbExport", method = RequestMethod.GET)
	public String DBExport(Locale locale, HttpServletRequest req, HttpServletResponse res, Model model) {

		
		IndexVo indexVo=new IndexVo();

		List<TbPatternDataVo> tb_pattern_data_list=tb_pattern_data_Dao.getSelect(indexVo);
		
		model.addAttribute("TB_PATTERN_DATA_LIST", tb_pattern_data_list);
		
		return "jsonView";
	}
	
	@RequestMapping(value = "dbTruncate", method = RequestMethod.GET)
	public String DBTruncate(Locale locale, HttpServletRequest req, HttpServletResponse res, Model model) {

		
		tb_pattern_data_Dao.deleteAll();
		
		model.addAttribute("result", "삭제완료");
		
		return "jsonView";
	}
}

package com.systran.process;

import java.util.Vector;

import kr.systran.trans.pattern.app.PatternTranslator;
import kr.systran.trans.pattern.vo.TransPatternRequestVO;
import kr.systran.trans.pattern.vo.TransPatternResultVO;
import kr.systran.trans.pattern.vo.TransPatternVO;
import net.siriussoft.trans.k2e.TransClientK2E;

public class InputOrgContGetTrans {
	private String trnst_cont;
	private String org_pttrn;
	private String trnst_pttrn;
	private boolean isApplyPattern;
	private Vector<TransPatternVO> patternReturnList;
	private Vector<String> transPatternExtMsgList;
	
	public String UsePatternTranslator(String org_cont) throws Exception{
		PatternTranslator patternTranslator = new PatternTranslator();
        TransPatternRequestVO transPatternRequestVO = new TransPatternRequestVO();
        String orgText = org_cont;
        transPatternRequestVO.setOrgText(orgText);
        if(org_pttrn!=null)
        {
        	transPatternRequestVO.setOrgPattern(org_pttrn);
        }
        if(trnst_pttrn!=null)
        {
        	transPatternRequestVO.setTransPattern(trnst_pttrn);
        }
        int transType = TransClientK2E.TYPE_ABS_KE;
        
        TransPatternResultVO  transPatternResultVO = new TransPatternResultVO();
        patternTranslator.setTransType(transType);
        patternTranslator.setTransGMTServerHost("192.168.0.200");
        patternTranslator.setTransGMTServerPort(9889);
        patternTranslator.setTransOLDServerHost("192.168.0.100");
        patternTranslator.setTransOLDServerPort(6800);
         patternTranslator.setPatternDataType(PatternTranslator.PATTERN_DATA_TYPE_DB);

		transPatternResultVO = patternTranslator.patternTranslator(transPatternRequestVO);

        
        trnst_cont = transPatternResultVO.getTransText();
        org_pttrn = transPatternResultVO.getOriginalPattern();
		trnst_pttrn = transPatternResultVO.getTransPattern();
		isApplyPattern = transPatternResultVO.isApplyPattern();
		patternReturnList=transPatternResultVO.getPatternReturnList();
		transPatternExtMsgList=transPatternResultVO.getTransPatternExtMsgList();
        
        System.out.println("패턴적용여부: " + isApplyPattern);
		System.out.println("번역TEXT: " + trnst_cont);
		System.out.println("원문 패턴: " + org_pttrn);
		System.out.println("번역 패턴: " + trnst_pttrn);
		
		return trnst_cont;
	}

	public Vector<TransPatternVO> getPatternReturnList() {
		return patternReturnList;
	}

	public void setPatternReturnList(Vector<TransPatternVO> patternReturnList) {
		this.patternReturnList = patternReturnList;
	}

	public Vector<String> getTransPatternExtMsgList() {
		return transPatternExtMsgList;
	}

	public void setTransPatternExtMsgList(Vector<String> transPatternExtMsgList) {
		this.transPatternExtMsgList = transPatternExtMsgList;
	}

	public String getTrnst_cont() {
		return trnst_cont;
	}

	public void setTrnst_cont(String trnst_cont) {
		this.trnst_cont = trnst_cont;
	}

	public String getOrg_pttrn() {
		return org_pttrn;
	}

	public void setOrg_pttrn(String org_pttrn) {
		this.org_pttrn = org_pttrn;
	}

	public String getTrnst_pttrn() {
		return trnst_pttrn;
	}

	public void setTrnst_pttrn(String trnst_pttrn) {
		this.trnst_pttrn = trnst_pttrn;
	}

	public boolean isApplyPattern() {
		return isApplyPattern;
	}

	public void setApplyPattern(boolean isApplyPattern) {
		this.isApplyPattern = isApplyPattern;
	}
}

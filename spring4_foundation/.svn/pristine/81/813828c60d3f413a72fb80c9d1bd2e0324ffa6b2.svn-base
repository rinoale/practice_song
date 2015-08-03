package com.systran.controller;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.systran.trans.pattern.vo.TransPatternVO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.systran.process.InputOrgContGetTrans;

@Controller
public class PatternTransController {
	
	@RequestMapping(value="pttrnTransAccess", method=RequestMethod.GET)
	public String PttrnTransAccess(HttpServletRequest req,HttpServletResponse res, Model model)
	{
		String new_org_pttrn=(String)req.getParameter("new_org_pttrn");
		String new_trnst_pttrn=(String)req.getParameter("new_trnst_pttrn");
		String org_cont=(String)req.getParameter("org_cont");
		
		InputOrgContGetTrans inputOrgContGetTrans=new InputOrgContGetTrans();
		
		if(!new_org_pttrn.equals("") && !new_trnst_pttrn.equals(""))
		{
			System.out.println("입력된 패턴 적용");
			inputOrgContGetTrans.setOrg_pttrn(new_org_pttrn);
			inputOrgContGetTrans.setTrnst_pttrn(new_trnst_pttrn);
		}
		else
		{
			System.out.println("입력된 패턴 없음");
		}
		try {
			inputOrgContGetTrans.UsePatternTranslator(org_cont);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String trnst_cont=inputOrgContGetTrans.getTrnst_cont();
		boolean isApplyPattern = inputOrgContGetTrans.isApplyPattern();
        String org_pttrn = inputOrgContGetTrans.getOrg_pttrn();
		String trnst_pttrn = inputOrgContGetTrans.getTrnst_pttrn();
		Vector<TransPatternVO> patternReturnList=inputOrgContGetTrans.getPatternReturnList();
		Vector<String> transPatternExtMsgList=inputOrgContGetTrans.getTransPatternExtMsgList();
		
		model.addAttribute("result", trnst_cont);
		model.addAttribute("isApplyPattern", isApplyPattern);
		model.addAttribute("org_pttrn", org_pttrn);
		model.addAttribute("trnst_pttrn", trnst_pttrn);
		model.addAttribute("patternReturnList", patternReturnList);
		model.addAttribute("transPatternExtMsgList", transPatternExtMsgList);
		
		
		
		return "jsonView";
	}
}

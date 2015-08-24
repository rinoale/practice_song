package com.rinoale.controller;

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

import com.rinoale.dao.CommentsDao;
import com.rinoale.dao.WritesDao;
import com.rinoale.vo.CommentsVo;
import com.rinoale.vo.IndexVo;
import com.rinoale.vo.WritesVo;

@Controller
public class BodyController {
	@Autowired
	private CommentsDao commentsDao;
	
	@Autowired
	private WritesDao writesDao;
	
	private static final Logger logger = LoggerFactory.getLogger(BodyController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/contents/main_content", method = RequestMethod.GET)
	public String mainContent(Locale locale,HttpServletRequest req, HttpServletResponse res, Model model) {
		
		return "contents/main_content.jsp";
	}
	
	@RequestMapping(value = "/feed_content", method = RequestMethod.GET)
	public String feedContent(Locale locale,HttpServletRequest req, HttpServletResponse res, Model model) {

		IndexVo indexVo=new IndexVo();
		indexVo.setFromIndex(0);
		indexVo.setHowMany(3);
		List<WritesVo> writes=writesDao.getSelect(indexVo);

		model.addAttribute("writes", writes);
		
		return "pages/feed_content.jsp";
	}
}

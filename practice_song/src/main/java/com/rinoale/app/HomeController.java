package com.rinoale.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

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

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private CommentsDao commentsDao;
	
	@Autowired
	private WritesDao writesDao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "static/index.html";
	}

	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public String index2(Locale locale, Model model) {		
		return "static/index2.html";
	}
	
	@RequestMapping(value = "/feed", method = RequestMethod.GET)
	public String feed(Model model) {
		List<CommentsVo> comments=commentsDao.getSelect();

		IndexVo indexVo=new IndexVo();
		indexVo.setFromIndex(0);
		indexVo.setHowMany(3);
		List<WritesVo> writes=writesDao.getSelect(indexVo);

		
		model.addAttribute("comments", comments);
		model.addAttribute("writes", writes);
		
		return "static/feed.jsp";
	}
}

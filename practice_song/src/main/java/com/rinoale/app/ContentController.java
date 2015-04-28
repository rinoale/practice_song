package com.rinoale.app;

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
import com.rinoale.vo.WritesVo;

@Controller
public class ContentController {
	@Autowired
	private CommentsDao commentsDao;
	
	@Autowired
	private WritesDao writesDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ContentController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "writes/getcontent.do", method = RequestMethod.GET)
	public String getWrites(Locale locale,HttpServletRequest req, HttpServletResponse res, Model model) {
		System.out.println(req.getParameter("seq"));

		
		model.addAttribute("seq", req.getParameter("seq"));
		
		return "jsonView";
	}
	
	@RequestMapping(value = "writes/write.do", method = RequestMethod.GET)
	public String putWrites(Locale locale,HttpServletRequest req, HttpServletResponse res, Model model) {
		System.out.println(req.getParameter("write"));
		
		String WRITES_CONTENT=req.getParameter("write");
				
		WritesVo writesVo=new WritesVo();
		writesVo.setWRITES_CONTENT(WRITES_CONTENT);
		
		writesDao.insert(writesVo);
		
		model.addAttribute("write", WRITES_CONTENT);
		
		return "jsonView";
	}
}

package com.syaku.bbs.controller;

import java.util.List;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.syaku.bbs.service.Service;
import com.syaku.bbs.dao.*;

@Controller(value = "viewController")
public class ViewController {
	private Service service;
	
	public void setService(Service service)
	{
		this.service=service;
	}
	

	
    private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

    // Resource ������̼��� �̿��Ͽ� BbsDao ����.
    @Resource(name = "bbsDao")
    private BbsDao bbsDao;

    // �Խ��� ���
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String dispBbsList(Model model) {
    	
    	
    	
        logger.info("display view BBS list");
        List<BbsVo> list = this.bbsDao.getSelect();
        model.addAttribute("list", list);
        service.serviceMethod();

        logger.info("total count" + list.size() );

        return "bbs.list";
    }

    // �Խ��� �󼼺�
    // PathVariable ������̼��� �̿��Ͽ� RESTful ��� ����
    // bbs/1 -> id = 1; id = �Խù� ��ȣ�� �ν���.
    // �Ϲ� ������ (@ReuqstParam(value = "bbsVo", required = false, defaultValue = "0"), int idx, Model model)
    @RequestMapping("/{idx}")
    public String dispBbsView(@PathVariable int idx, Model model) {
        logger.info("display view BBS view idx = {}", idx);
        BbsVo object = this.bbsDao.getSelectOne(idx);
        service.serviceMethod(idx);
        model.addAttribute("object", object);
        return "bbs.view";
    }

    // �Խ��� ����
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String dispBbsWrite(@RequestParam(value="idx", defaultValue="0") int idx, Model model) {
        logger.info("display view BBS write");

        if (idx > 0) {
            BbsVo object = this.bbsDao.getSelectOne(idx);
            service.serviceMethod(idx);
            model.addAttribute("object", object);
        }

        return "bbs.write";
    }

    @RequestMapping(value = "/write_ok", method = RequestMethod.POST)
    public String procBbsWrite(@ModelAttribute("bbsVo") BbsVo bbsVo, RedirectAttributes redirectAttributes) {
        Integer idx = bbsVo.getIdx();

        if (idx == null || idx == 0) {
            this.bbsDao.insert(bbsVo);
            redirectAttributes.addFlashAttribute("message", "�߰��Ǿ����ϴ�.");
            return "redirect:/";
        } else {
            this.bbsDao.update(bbsVo);
            redirectAttributes.addFlashAttribute("message", "�����Ǿ����ϴ�.");
            return "redirect:/write?idx=" + idx;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String procBbsDelete(@RequestParam(value = "idx", required = false) int idx) {
        this.bbsDao.delete(idx);
        return "redirect:/";
    }

}

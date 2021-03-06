package com.systran.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.systran.thread.BackgroundThreadWrapper;
import com.systran.dao.TbPatternDataDao;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private boolean isRunning = true;
	
	private SimpMessagingTemplate template;
	
	@Autowired
    public HomeController(SimpMessagingTemplate template) {
        this.template = template;
    }
	
	@Inject
	BackgroundThreadWrapper thread;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	@Qualifier("brokerMessagingTemplate")
	private MessageSendingOperations brokerMessagingTemplate;
	
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TbPatternDataDao tb_pattern_data_Dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
        
		return "main.jsp";
	}
	
	@RequestMapping("/pause")
	public ModelAndView pause () throws Exception{
		ModelAndView mav = new ModelAndView();
		try{
			thread.pause();
			System.out.println("pause Thread");
			isRunning = false;
		}catch(Exception e){e.printStackTrace();}
		mav.setViewName("main.jsp");		
		return mav;
	}
	
	@RequestMapping("/revive")
	public ModelAndView revive () throws Exception {
		ModelAndView mav = new ModelAndView();
		if(!isRunning){
			thread.revive();
			isRunning = true;
		}
		mav.setViewName("main.jsp");
		return mav;
	}
	
	public static List<Object> getInstantiatedSigletons(ApplicationContext ctx) {
        List<Object> singletons = new ArrayList<Object>();

        String[] all = ctx.getBeanDefinitionNames();
        
        Map<String,SimpMessagingTemplate> msgtem=ctx.getBeansOfType(SimpMessagingTemplate.class);
        
        Set<String> keyset=msgtem.keySet();
        
        Iterator<String> iter=keyset.iterator();
        
        while(iter.hasNext())
        {
        	String name= iter.next();
        	System.out.println(name);
        }

        ConfigurableListableBeanFactory clbf = ((AbstractApplicationContext) ctx).getBeanFactory();
        for (String name : all) {
            Object s = clbf.getSingleton(name);
            if (s != null)
            	System.out.println(name);
                singletons.add(s);
        }

        return singletons;

}
	
}

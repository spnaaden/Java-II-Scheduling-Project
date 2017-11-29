package edu.dmacc.spring.schedulingproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {
	@Autowired EventDao dao;
	
	@RequestMapping(value = "/form")
	public ModelAndView event( ){
		ModelAndView modelAndView = new ModelAndView( );
		
		modelAndView.setViewName("scheduleForm");
		modelAndView.addObject("event", new Event( ));
		//modelAndView.addObject("countries", countries);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/add")
	public ModelAndView processEvent(Event event){
		ModelAndView modelAndView = new ModelAndView();
		dao.insertEvent(event);
		modelAndView.setViewName("scheduleForm");
		return modelAndView;
	}

	
	@Bean
	public EventDao dao(){
		EventDao bean = new EventDao();
		return bean;
	}



}

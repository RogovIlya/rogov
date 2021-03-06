package ru.rogov.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ru.rogov.entity.User;
import ru.rogov.service.ServiceFasade;

@Controller
@SessionAttributes(value = {"user"},types = {User.class})
@RequestMapping(value = { "/anime" })
public class AnimeController extends AuthenticationUserSession
{
	@Autowired
	ServiceFasade fasade;

	private static final Logger	logger	= LoggerFactory.getLogger(AnimeController.class);
	
	@Autowired
	ServletContext context;

	@RequestMapping(value = { "" },  method = RequestMethod.GET)
	public ModelAndView animePage(@ModelAttribute User user)
	{	
		ModelAndView model = new ModelAndView();
		
		model.addObject("user", user);
		model.setViewName("anime");
		
		return model;
	}
}

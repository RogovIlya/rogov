package ru.rogov.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import ru.rogov.commons.Commons;
import ru.rogov.entity.User;
import ru.rogov.service.ServiceFasade;

@Controller
public class AuthenticationController extends AuthenticationUserSession
{
	@Autowired
	ServiceFasade fasade;

	private static final Logger	logger	= LoggerFactory.getLogger(AuthenticationController.class);
	
	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	public ModelAndView authentication(@RequestParam(value="error",required=false) String error,HttpServletRequest request)
	{
		String referer = request.getHeader("Referer");

		if(error != null && !error.isEmpty())
		{
			logger.info("Произошла ошибка при авторизации пользователя! Тип ошибки = "+error);
		}
		
		ModelAndView mv = new ModelAndView();
		User user = fasade.getUserService().getUser(Commons.getPrincipal());
		if(user == null)
		{
			logger.info("Пользователь не найден!");
			user = new User();
		}
		else
		{
			logger.info("Авторизовался пользователь = "+user);
		}
		
		mv.addObject("user", user);
		mv.setViewName("redirect:"+referer);
		
		return mv;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response,SessionStatus status)
	{
		String referer = request.getHeader("Referer");
		
		ModelAndView mv = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
		{
			System.out.println("*************удалил user");
			status.setComplete();
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		mv.setViewName("redirect:"+referer);
		
		return mv;
	}

	
	@RequestMapping(value = "/registr" )
	public ModelAndView registrUser(@ModelAttribute User user,HttpServletRequest request)
	{	
		
		String referer = request.getHeader("Referer");
		
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
		
		model.setViewName("redirect:"+referer);
		
		return model;
	}
}

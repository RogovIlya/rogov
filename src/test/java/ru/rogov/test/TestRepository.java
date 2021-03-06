package ru.rogov.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ru.rogov.commons.Commons;
import ru.rogov.configuration.WebApplicationInitializer;
import ru.rogov.configuration.SpringConfiguration;
import ru.rogov.entity.User;
import ru.rogov.service.ServiceFasade;
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class, WebApplicationInitializer.class})
@WebAppConfiguration
public class TestRepository
{
	@Autowired
	private ServiceFasade fasade; 
	 
	@Test
	public void test() throws Exception
	{
		User u = new User();
		u.setRole_id(2L);
		u.setUsername("dfgdgdfgdfg");
		u.setPassword("ccccc");
		u.setId(5L);
		User user  = fasade.getUserService().getUser(2L);
		System.out.println("****************1");
		System.out.println(user);
		System.out.println("****************2");
	}
}

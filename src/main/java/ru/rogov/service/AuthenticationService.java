package ru.rogov.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.rogov.entity.User;

@Service("AuthenticationService")
public class AuthenticationService implements UserDetailsService
{
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
	
	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
	{
		User user = userService.getUser(login);
		if (user != null)
		{
			GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
			UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
			return userDetails;
		} else
		{
			logger.error("Не найден пользователь по логину = "+login);
			throw new UsernameNotFoundException(login);
		}
	}

}

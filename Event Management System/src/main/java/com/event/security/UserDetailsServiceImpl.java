package com.event.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.event.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException
	{
		// TODO Auto-generated method stub
		return repo.findByUserMail(userMail).orElseThrow();
	}

}

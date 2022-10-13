package com.gorillaz.core.service.impl;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gorillaz.core.model.entity.UserDTO;
import com.gorillaz.core.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userRepository.findByName(username);
		Set<GrantedAuthority> authorities = user.getRoles().stream()
											.map(role -> new SimpleGrantedAuthority(role.getName()))
											.peek( auth -> log.info("Role" + auth.getAuthority()))
											.collect(Collectors.toSet());
		return new User(user.getName(),user.getPassword(),user.getStatus(),true,true,true,authorities);
	}

	public UserDTO findByName(String name) {
		return userRepository.findByName(name);
	}

}

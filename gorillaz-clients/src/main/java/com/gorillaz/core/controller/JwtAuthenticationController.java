package com.gorillaz.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gorillaz.core.request.JwtRequest;
import com.gorillaz.core.response.JwtResponse;
import com.gorillaz.core.service.JwtUserDetailsService;
import com.gorillaz.core.utils.JwtTokenUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		log.info(":::::: /authenticate - Init  :::  ");
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		log.info(":::::: /authenticate - userDetailsService.loadUserByUsername  :::  ");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		log.info(":::::: /authenticate - jwtTokenUtil.generateToken  :::  ");
		final String token = jwtTokenUtil.generateToken(userDetails);
		log.info(":::::: /authenticate - success  :::  ");
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			log.info(":::::: /authenticate - authenticate  :::  ");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			log.info(":::::: /authenticate - authenticated  :::  ");
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
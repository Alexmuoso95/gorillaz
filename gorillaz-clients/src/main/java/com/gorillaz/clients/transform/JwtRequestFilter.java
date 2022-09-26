package com.gorillaz.clients.transform;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gorillaz.clients.service.JwtUserDetailsService;
import com.gorillaz.clients.utils.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		log.info(":::::: SECURITY - doFilterInternal :::::::::::" );
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			log.info(":::::: SECURITY - contains Bearer  :::::::::::" );
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				log.error("Unable to get JWT Token");
				throw new RuntimeException("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				log.error("JWT Token has expired");
				throw new RuntimeException("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			log.info(":::::: SECURITY - user Authenticated :::::::::::" );
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
			// if token is valid configure Spring Security to manually set authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				log.info(":::::: SECURITY - user token validation :::::::::::" );
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				log.info(":::::: SECURITY - user token validated :::::::::::" );
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify that the current user is authenticated. So it passes the Spring Security Configurations successfully.
				log.info(":::::: SECURITY - setAuthentication :::::::::::" );
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		log.info(":::::: SECURITY - doFilter :::::::::::" );
		chain.doFilter(request, response);
	}

}
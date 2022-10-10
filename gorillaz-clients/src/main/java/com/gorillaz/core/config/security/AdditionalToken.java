package com.gorillaz.core.config.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.gorillaz.core.model.entity.UserDTO;
import com.gorillaz.core.service.UserServiceImpl;

@Component
public class AdditionalToken implements TokenEnhancer{

	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<>();
		UserDTO user = userService.findByName(authentication.getName());
		info.put("Additional-Information", user.getName());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
}

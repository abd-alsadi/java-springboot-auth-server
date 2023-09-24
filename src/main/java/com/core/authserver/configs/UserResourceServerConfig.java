package com.core.authserver.configs;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

public class UserResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	// private static final String RESOURCE_ID = "user-resource";
	
	// @Override
	// public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
	// 	resources.resourceId(RESOURCE_ID);
	// }

	@Override
	public void configure(HttpSecurity http) throws Exception {
		    http.csrf().disable().anonymous().disable().authorizeRequests().
            //antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
            antMatchers( "api**").authenticated().anyRequest().permitAll()
            .and()
            .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
			http.headers().frameOptions().disable();
	}

}
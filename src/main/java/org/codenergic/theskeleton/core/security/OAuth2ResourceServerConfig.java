/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codenergic.theskeleton.core.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
@ConditionalOnWebApplication
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
	private final AuthenticationManager authenticationManager;
	private final TokenStore tokenStore;

	public OAuth2ResourceServerConfig(AuthenticationManager authenticationManager, TokenStore tokenStore) {
		this.authenticationManager = authenticationManager;
		this.tokenStore = tokenStore;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.requestMatchers()
				.antMatchers("/api/**")
				.and()
			.authorizeRequests()
				.anyRequest().permitAll()
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.anonymous();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setAuthenticationManager(authenticationManager);
		tokenServices.setTokenStore(tokenStore);
		resources.tokenServices(tokenServices);
	}
}

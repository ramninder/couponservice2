package com.ramninder.springcloud.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

//@Configuration annotation indicates that a class declares one or more @Bean methods 
//and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.
@Configuration
//Convenience annotation for enabling an Authorization Server (i.e. an AuthorizationEndpoint and a TokenEndpoint) 
//in the current application context, which must be a DispatcherServlet context.
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String Resource_Id = "couponService";

	@Autowired
	private AuthenticationManager authmanager;

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// Use in memory Token Store.

		endpoints.tokenStore(new JdbcTokenStore(dataSource)).authenticationManager(authmanager)
				.userDetailsService(userDetailService);
	}

	// Configure CLient Details.
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("couponclientapp").secret(passwordEncoder.encode("9999"))
				.authorizedGrantTypes("password", "refresh_token").scopes("read", "write").resourceIds(Resource_Id);
	}

}

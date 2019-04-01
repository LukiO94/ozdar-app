package com.lozdarski.coachasistant.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
	DataSource dataSource;
	
	@Autowired 
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Bean
	public AuthenticationSuccessHandler  authenticationSuccessHandler() {
	    return new AuthenticationSuccessHandler();
	}
	
	@Bean
	public PasswordEncoder  encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT user_name, password, is_enabled FROM myschema.users WHERE user_name=?").passwordEncoder(encoder())
		.authoritiesByUsernameQuery("SELECT user_name_fk, role_name FROM myschema.roles WHERE user_name_fk=?");
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
	    .csrf().disable()
	    .exceptionHandling()
	    .authenticationEntryPoint(restAuthenticationEntryPoint)
	    .and()
	    	.authorizeRequests()
	    	.antMatchers("/users/all").authenticated()
	    	.antMatchers("/users/add").permitAll()
	    .and()
	    	.formLogin()
	    		.successHandler(authenticationSuccessHandler())
	    .and()
	    .logout()
	    	.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.ACCEPTED))
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
    }
}

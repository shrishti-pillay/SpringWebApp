package com.spring.project.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class StudentCourseConfiguration extends WebSecurityConfigurerAdapter {
	
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/resources/**");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/")
				.authenticated()
			.and()
			     .formLogin()
			     .loginPage("/login")
			     .permitAll()
	        .and().logout().permitAll();
		
		http.csrf().disable();
	}

}

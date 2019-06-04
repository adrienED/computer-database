package com.excilys.webapp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username, password, enabled from users where username=?")
        .authoritiesByUsernameQuery("select username, 'ROLE_USER' from authorities where username=?");

	}
	
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http.
	                authorizeRequests()
	                .antMatchers("/").permitAll()
	                .antMatchers("/dashboard").permitAll()
	                .antMatchers("/login").permitAll()
	                .antMatchers("/registration").permitAll()
	                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
	                .authenticated().and().csrf().disable().formLogin()
	                .loginPage("/login").failureUrl("/login?error=true")
	                .defaultSuccessUrl("/dashboard")
	                .usernameParameter("username")
	                .passwordParameter("password")
	                .and().logout()
	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                .logoutSuccessUrl("/").and().exceptionHandling()
	                .accessDeniedPage("/access-denied");
	}
	}

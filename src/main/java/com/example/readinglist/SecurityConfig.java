/**
 *
 */
package com.example.readinglist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author shuai.b.zhang
 *
 */

@Profile("development")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	@Autowired
	private ReaderRepository readerRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().access("hasRole('READER')")
            .and()
        .formLogin()
            .loginPage("/login").failureUrl("/login?error=true")
            .permitAll()
            .and()
        .logout()
            .permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				logger.info("User Name for login is: {}", username);
				return readerRepository.findOne(username);
			}
		});
	}
}

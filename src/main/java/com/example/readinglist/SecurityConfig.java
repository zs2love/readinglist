/**
 *
 */
package com.example.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.readinglist.service.ReaderInfoService;

/**
 * @author shuai.b.zhang
 *
 */

@Profile("development")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ReaderInfoService readerinfoService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().access("hasRole('READER')")
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login").defaultSuccessUrl("/loginuser").usernameParameter("username1")
            .failureUrl("/login?error=true")
            .permitAll()
            .and()
        .logout()
            .permitAll();
	}

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
        auth
            .userDetailsService(readerinfoService);
    }
}

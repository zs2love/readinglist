/**
 * 
 */
package com.example.readinglist.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.readinglist.ReaderRepository;

/**
 * @author shuai.b.zhang
 *
 */
@Service
public class ReaderInfoService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(ReaderInfoService.class);
	@Autowired
	private ReaderRepository readerRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		logger.info("User Name for login is: {}", username);
		return readerRepository.findOne(username);

	}

}

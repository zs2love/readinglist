/**
 * 
 */
package com.example.readinglist.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shuai.b.zhang
 *
 */
@RestController
public class LdapController {
	@RequestMapping("/ldap")  
    public String index() {  
        return "Welcome to the home page!";  
    }  
}

package com.harsha.rest.basic.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String ps = bCryptPasswordEncoder.encode("password"); 
		
		System.out.println(ps);
	}
	
}

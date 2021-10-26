package com.enesbayram.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		belirtilen css-js-images-webjars klasörleri altındaki her şeye erişebilme yetkisi veriyorum.
//		http.authorizeRequests().antMatchers("/**/favicon.ico","/css/**","/js/**","/images/**","/webjars/**").permitAll();
//		
//		herhangi bir istek yapıldığı zaman yetkilendirmeye tabii tutuyorum.
//		http.authorizeRequests().anyRequest().authenticated();
//		
//		 yetkilendirme için login sayfasına yönlediriyoruz.
//		http.formLogin();
		
//		http
//        .csrf().disable()
//        .authorizeRequests().anyRequest().authenticated()
//        .and()
//        .httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//        .withUser("enes")
//        .password("{noop}enes")
//        .roles("USER");
	}
	
	
	
}

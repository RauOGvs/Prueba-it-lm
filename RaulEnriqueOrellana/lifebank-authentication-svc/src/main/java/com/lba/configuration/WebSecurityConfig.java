package com.lba.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		
		http
        .authorizeRequests()
        .antMatchers("/auth/getInfo/**", "/auth/validate/**").permitAll()
//            .antMatchers("/validate/**", "/css/**", "/images/**", "/js/**", "/angularjs/**", "/ngModules/**", "/components/**", "/controllers/**").permitAll()
           .anyRequest().fullyAuthenticated();
//		
		http.csrf().ignoringAntMatchers("/auth/getInfo").disable();
	}

//
//	@Bean
//	public RequestDataValueProcessor requestDataValueProcessor() {
//		return new CsrfRequestDataValueProcessor();
//	}
//
	@Override
	public void configure(WebSecurity web) throws Exception {
//		String auth = env.getProperty("app-properties.qms-authentication-mapping");
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append(auth);
//		stringBuilder.append(env.getProperty("app-properties.login-mapping"));
//		String loginUrl = stringBuilder.toString();
//		stringBuilder = new StringBuilder();
//		stringBuilder.append(auth);
//		stringBuilder.append(env.getProperty("app-properties.validation-mapping"));
//		String validationUrl = stringBuilder.toString();
//
//		web.ignoring().antMatchers(HttpMethod.POST, loginUrl);
		web.ignoring().antMatchers(HttpMethod.POST, "/getInfo");
		web.ignoring().antMatchers(HttpMethod.GET, "/validate");
		web.ignoring().antMatchers(HttpMethod.GET, "/health");
//		web.ignoring().antMatchers(HttpMethod.POST, "/validate");
//		web.ignoring().antMatchers(HttpMethod.POST, validationUrl);
		
	}


}

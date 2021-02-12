package com.example.demo.config;

import com.example.demo.domain.Role;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MemberService memberService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService)
				.passwordEncoder(passwordEncoder);
	}
//
//	private PasswordEncoder passwordEncoder() {
//		PasswordEncoder encoder = new BCryptPasswordEncoder(); // or any other compatible encoder
//		return encoder;
//	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors().and()
				.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers("/api/**").permitAll()
				//.antMatchers("/api/**").hasAuthority(String.valueOf(Role.MEMBER))

				//.antMatchers("/api/v1/test/auth").authenticated()
				//.antMatchers("/**").authenticated()
				.anyRequest().permitAll()
				.and()
				.formLogin().disable();
//		http.antMatcher("/api/**")
//			.authorizeRequests()
//				.antMatchers("/api/login/**").permitAll()
//				.anyRequest().authenticated();

//				.and()
//				.formLogin();

//				.antMatchers("/member/**").authenticated()
//				.antMatchers("/admin/**").authenticated()
//				.antMatchers(HttpMethod.POST, "/**").permitAll()
//				.antMatchers(HttpMethod.GET, "/**").permitAll()
//				.hasRole(Role.MEMBER);
//		.anyRequest().permitAll();
//
//		http.formLogin()
//				.loginPage("/login")
//				.defaultSuccessUrl("/")
//				.permitAll();
//
//		http.logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				.logoutSuccessUrl("/login")
//				.invalidateHttpSession(true);
//
//		http.exceptionHandling()
//				.accessDeniedPage("/denied");
	}


//	@Override
//	public void configure(WebSecurity web) throws Exception {
//
//		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//
//	}
}


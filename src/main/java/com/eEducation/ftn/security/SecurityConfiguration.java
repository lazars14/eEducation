package com.eEducation.ftn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication(
			AuthenticationManagerBuilder authenticationManagerBuilder)
			throws Exception {
		
		authenticationManagerBuilder
				.userDetailsService(this.userDetailsService).passwordEncoder(
						passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean()
			throws Exception {
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter
				.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				.antMatchers("/index.html", "/api/login").permitAll() 
				.antMatchers("/api/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT");
					
//				.hasAuthority("ROLE_ADMIN") // admin api's
//				.antMatchers(HttpMethod.GET, "/api/courses/teacher/*/", "/api/course/*/colloquiums/byCourse", "/api/examPeriod/*/examTerms/byExamPeriod",
//							"/api/examPeriod/*/", "/api/grades/*/", "/api/examEntries/*/", "/api/examEntries/examTerms/*/teacher/*/",
//							"/api/notifications/*/", "/api/notifications/course/*/distinctMessages", "/api/course/*/colloquiums/byCourse",
//							"/api/course/*/colloquiums/*/", "/api/course/*/courseLessons/byCourse", "/api/course/*/courseLessons/*/",
//							"/api/courseFiles/*/", "/api/courseFiles/courseLessons/*/", "/api/courseFiles/courses/*/notifications",
//							"/api/colloquium/*/colloquiumResults/*/", "/api/colloquium/*/colloquiumResults/byColloquium")
//					.hasAuthority("ROLE_TEACHER")
//				.antMatchers(HttpMethod.POST, "/api/grades", "/api/notifications", "/api/notifications/batchAdd",
//						"/api/course/*/colloquiums/", "/api/course/*/courseLessons/", "/api/courseFiles/")
//					.hasAuthority("ROLE_TEACHER")
//				.antMatchers(HttpMethod.PUT, "/api/grades/*/", "/api/examEntries/*/", "/api/notifications/*/",
//							"/api/notifications/batchUpdate", "/api/notifications/batchDelete", "/api/course/*/colloquiums/*/",
//							"/api/course/*/courseLessons/*/", "/api/courseFiles/*/", "/api/colloquium/*/colloquiumResults/*/",
//							"/api/teachers/*/changeEmail", "/api/teachers/*/changePassword")
//					.hasAuthority("ROLE_TEACHER")
//				.antMatchers(HttpMethod.DELETE, "/api/grades/*/", "/api/notifications/*/", "/api/course/*/colloquiums/*/",
//							"/api/course/*/courseLessons/*/", "/api/courseFiles/*/")
//					.hasAuthority("ROLE_TEACHER")
//				.antMatchers(HttpMethod.GET, "/api/courses/student/*/", "/api/course/*/colloquiums/byCourse", "/api/examPeriod/*/examTerms/byExamPeriod",
//						"/api/examPeriod/*/", "/api/grades/students/*/", "/api/grades/*/", "/api/payments/students/*/", "/api/examEntries/*/",
//						"/api/examEntries/examTerms/*/student/*/", "/api/course/*/studentDocuments/*/", "/api/notifications/*/",
//						"/api/notifications/student/*/", "/api/notifications/course/*/student/*/", "/api/notifications/*/read",
//						"/api/course/*/colloquiums/byCourse", "/api/course/*/colloquiums/*/", "/api/course/*/courseLessons/byCourse",
//						"/api/course/*/courseLessons/*/", "/api/courseFiles/*/", "/api/courseFiles/courseLessons/*/",
//						"/api/courseFiles/courses/*/notifications", "/api/colloquium/*/colloquiumResults/*/",
//						"/api/colloquium/*/colloquiumResults/student/*/")
//					.hasAuthority("ROLE_STUDENT")
//				.antMatchers(HttpMethod.POST, "/api/examEntries", "/api/course/*/studentDocuments", 
//						"/api/colloquium/*/colloquiumResults/")
//					.hasAuthority("ROLE_STUDENT")
//				.antMatchers(HttpMethod.PUT, "/api/students/*/changeEmail", "/api/students/*/changePassword").hasAuthority("ROLE_STUDENT")
//				.antMatchers(HttpMethod.DELETE, "/api/examEntries/*/", "/api/course/*/studentDocuments/*/").hasAuthority("ROLE_STUDENT")
//				.anyRequest().authenticated();
		
		// Custom JWT based authentication
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
				UsernamePasswordAuthenticationFilter.class);
	}
}

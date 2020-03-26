package com.example.csrf.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	public static final String X_XSRF_TOKEN = "X-XSRF-TOKEN";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		serverConfiguration(http);
	}

	private void serverConfiguration(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll().and()
				.addFilterAfter(new CsrfGrantingFilter(), CsrfFilter.class).csrf()
				.requireCsrfProtectionMatcher(requestMatcher)
				.csrfTokenRepository(csrfTokenRepository());
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName(X_XSRF_TOKEN);
		return repository;
	}

	RequestMatcher requestMatcher = new RequestMatcher() {
		private AntPathRequestMatcher path = new AntPathRequestMatcher("/actuator/**");

		@Override
		public boolean matches(HttpServletRequest request) {
			System.out.println(request.getRequestURI());
			if (path.matches(request))
				return false;
			return true;
		}
	};

}

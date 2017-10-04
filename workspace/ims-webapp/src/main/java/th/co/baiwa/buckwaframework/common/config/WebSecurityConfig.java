package th.co.baiwa.buckwaframework.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.URL;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.co.baiwa.buckwaframework.security.rest.entrypoint.RestAuthenticationEntryPoint;
import th.co.baiwa.buckwaframework.security.rest.handler.RestAuthenticationSuccessHandler;
import th.co.baiwa.buckwaframework.security.rest.handler.RestLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	@Qualifier("jdbcAuthenticationProvider")
	private AuthenticationProvider jdbcAuthenticationProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(jdbcAuthenticationProvider);
	}
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	@Profile(value = {PROFILE.MOCK, PROFILE.UNITTEST})
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password(new BCryptPasswordEncoder().encode("password")).roles("USER").build());
		manager.createUser(User.withUsername("admin").password(new BCryptPasswordEncoder().encode("password")).roles("USER", "ADMIN").build());
		return manager;
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	/*
	 * Rest Login API
	 */
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api/**")
				.authorizeRequests().anyRequest().hasAnyRole(ROLE.USER)
			.and()
			.formLogin()
				.loginProcessingUrl(URL.LOGIN_REST)
				.successHandler(restAuthenticationSuccessHandler())
				.failureHandler(restAuthenticationFailureHandler())
				.usernameParameter(SecurityConstants.USERNAME_PARAM)
				.passwordParameter(SecurityConstants.PASSWORD_PARAM)
			.and()
			.logout()
				.permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher(URL.LOGIN_REST, HttpMethod.DELETE.toString()))
				.logoutSuccessHandler(restLogoutSuccessHandler())
			.and()
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
			.and()
			.requestCache().requestCache(new NullRequestCache());
			
			http.csrf().disable();
		}
		
		@Bean
		public RestAuthenticationSuccessHandler restAuthenticationSuccessHandler() {
			return new RestAuthenticationSuccessHandler();
		}
		
		@Bean
		public SimpleUrlAuthenticationFailureHandler restAuthenticationFailureHandler() {
			return new SimpleUrlAuthenticationFailureHandler();
		}
		
		@Bean
		public RestLogoutSuccessHandler restLogoutSuccessHandler() {
			return new RestLogoutSuccessHandler();
		}
		
		@Bean
		public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
			return new RestAuthenticationEntryPoint();
		}
		
	}
	
	/*
	 * From Login API
	 */
	@Configuration
	@Order(2)
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers(
					"/", "/index.htm", "/index.html",
					"/resources/**",
					"/app/**",
					"/assets/**"
				).permitAll()
				.antMatchers("/backend/**").hasAnyRole(ROLE.USER, ROLE.ADMIN)
				.anyRequest().hasRole(ROLE.USER)
			.and()
			.formLogin()
				.loginPage(URL.LOGIN_WEB)
				.defaultSuccessUrl(URL.LOGIN_WEB_SUCCESS)
				.failureUrl(URL.LOGIN_WEB_FAILURE)
				.usernameParameter(SecurityConstants.USERNAME_PARAM)
				.passwordParameter(SecurityConstants.PASSWORD_PARAM)
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout.htm"))
				//.logoutSuccessHandler(CustomLogoutSuccessHandler.getInstance("/login.htm"))
				.invalidateHttpSession(true)
			.and()
			.exceptionHandling().accessDeniedPage("/403.htm");
			
			http.csrf().disable();
		}
		
//		@Override
//		public void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth
//				.ldapAuthentication()
//					.userDnPatterns("uid={0},ou=people")
//					.groupSearchBase("ou=groups")
//					.contextSource()
//						.url("ldap://localhost:8389/dc=springframework,dc=org")
//						.and()
//					.passwordCompare()
//						.passwordEncoder(new LdapShaPasswordEncoder())
//						.passwordAttribute("userPassword");
//		}
		
	}
	
}
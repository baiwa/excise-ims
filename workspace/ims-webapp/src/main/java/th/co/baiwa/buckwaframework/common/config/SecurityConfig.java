package th.co.baiwa.buckwaframework.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.co.baiwa.buckwaframework.security.rest.entrypoint.RestAuthenticationEntryPoint;
import th.co.baiwa.buckwaframework.security.rest.handler.RestAuthenticationSuccessHandler;
import th.co.baiwa.buckwaframework.security.rest.handler.RestLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	@Autowired
//	@Qualifier("authenticationProvider")
//	private AuthenticationProvider authenticationProvider;
//	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider);
//	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers(
//			"/rest/**",
//			"/html/**",
//			"/ws/**"
//		);
//	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers(
//				"/", "/index.htm", "/index.html",
//				"/login.htm",
//				"/resources/**",
//				"/app/**",
//				"/assets/**"
//			).permitAll()
//			//.antMatchers("/admin/**").hasRole("ADMIN")
//			.anyRequest().hasRole("USER")
//		.and()
//		.formLogin()
//			.loginPage("/login.htm").permitAll()
//			.defaultSuccessUrl("/welcome.htm")
//			.failureUrl("/login.htm?error").permitAll()
//			.usernameParameter("username")
//			.passwordParameter("password")
//		.and()
//		.logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout.htm"))
//			//.logoutSuccessHandler(CustomLogoutSuccessHandler.getInstance("/login.htm"))
//			.invalidateHttpSession(true)
//		.and()
//		.exceptionHandling().accessDeniedPage("/403.htm");
//		
//		http.csrf().disable();
//	}
	
	/*
	 * Rest Login API
	 */
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/api/**")
				.hasAnyRole(ROLE.ROLE_USER)
			.and()
			.formLogin()
				.loginProcessingUrl(SecurityConstants.LOGIN_URL)
				.successHandler(restAuthenticationSuccessHandler())
				.failureHandler(restAuthenticationFailureHandler())
				.usernameParameter(SecurityConstants.USERNAME_PARAM)
				.passwordParameter(SecurityConstants.PASSWORD_PARAM)
			.and()
			.logout()
				.permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher(SecurityConstants.LOGIN_URL, HttpMethod.DELETE.toString()))
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
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers(
					"/", "/index.htm", "/index.html",
					"/login.htm",
					"/resources/**",
					"/app/**",
					"/assets/**"
				).permitAll()
				//.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().hasRole(ROLE.ROLE_USER)
			.and()
			.formLogin()
				.loginPage("/login.htm").permitAll()
				.defaultSuccessUrl("/welcome.htm")
				.failureUrl("/login.htm?error").permitAll()
				.usernameParameter("username")
				.passwordParameter("password")
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout.htm"))
				//.logoutSuccessHandler(CustomLogoutSuccessHandler.getInstance("/login.htm"))
				.invalidateHttpSession(true)
			.and()
			.exceptionHandling().accessDeniedPage("/403.htm");
			
			http.csrf().disable();
		}
		
	}
	
//	@Bean(name = "passwordEncoder")
//	public PasswordEncoder passwordEncoder() {
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		return encoder;
//	}
	
	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("password").roles("USER").build());
		manager.createUser(User.withUsername("admin").password("password").roles("USER", "ADMIN").build());
		return manager;
	}
	
}
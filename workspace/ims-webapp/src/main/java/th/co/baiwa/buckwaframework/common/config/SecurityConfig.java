package th.co.baiwa.buckwaframework.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
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
			.anyRequest().hasRole("USER")
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
	
//	@Bean(name = "passwordEncoder")
//	public PasswordEncoder passwordEncoder() {
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		return encoder;
//	}
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("jim").password("demo").roles("ADMIN").and()
			.withUser("bob").password("demo").roles("USER").and()
			.withUser("ted").password("demo").roles("USER", "ADMIN");
	}
	
}
package th.co.baiwa.buckwaframework.security.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.co.baiwa.buckwaframework.security.domain.UserDetails;

@Service("userDetailService")
public class UserDetailServiceTest implements org.springframework.security.core.userdetails.UserDetailsService {

	static Map<String, UserDetails> DB_BASED_USER_MAPPING;

	static {
		DB_BASED_USER_MAPPING = new LinkedHashMap<>();
	}

	@PostConstruct
	public void initialUser() {
		// Admin
		UserDetails userDetailsAdmin = new UserDetails("admin", "password", getGrantedAuthorities(ROLE.USER, ROLE.ADMIN, ROLE.IA, ROLE.TA));
		userDetailsAdmin.setOfficeCode("000000");
		DB_BASED_USER_MAPPING.put("admin", userDetailsAdmin);

		// Admin2
		UserDetails userDetailsAdmin2 = new UserDetails("admin2", "password", getGrantedAuthorities(ROLE.USER, ROLE.ADMIN, ROLE.IA, ROLE.TA));
		userDetailsAdmin2.setOfficeCode("000000");
		DB_BASED_USER_MAPPING.put("admin2", userDetailsAdmin2);
	}

	private static List<GrantedAuthority> getGrantedAuthorities(String... roles) {
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (DB_BASED_USER_MAPPING.containsKey(username)) {
			return DB_BASED_USER_MAPPING.get(username);
		}
		throw new UsernameNotFoundException("User " + username + " cannot be found");
	}

}

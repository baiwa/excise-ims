package co.th.baiwa.buckwaframework.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.th.baiwa.buckwaframework.security.domain.TMBPerson;
import co.th.baiwa.buckwaframework.security.domain.UserDetails;

public interface TmbUserDetailsService {

	public UserDetails loadUserByUsername(String username, TMBPerson tMBPerson) throws UsernameNotFoundException ;
	
}

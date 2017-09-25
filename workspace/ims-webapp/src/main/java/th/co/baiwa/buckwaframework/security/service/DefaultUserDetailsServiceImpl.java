//package th.co.baiwa.framework.security.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import th.co.baiwa.framework.admin.persistence.entity.User;
//import th.co.baiwa.framework.common.util.BooleanToStringConverter;
//import th.co.baiwa.framework.security.dao.UserDetailsDao;
//import th.co.baiwa.framework.security.model.UserDetails;
//
//@Service("userDetailsService")
//public class DefaultUserDetailsService implements UserDetailsService {
//
//	private static final Logger logger = LoggerFactory.getLogger(DefaultUserDetailsService.class);
//
//	@Autowired
//	private UserDetailsDao userDetailsDao;
//	
//	@Transactional(readOnly = true)
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		logger.info("loadUserByUsername username=" + username);
//		
//		User user = userDetailsDao.findByUsername(username);
//		// Initial Granted Authority
//		List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
//		// Add Role
//		grantedAuthorityList.addAll(userDetailsDao.findGrantedRoleByUserId(user.getUserId()));
//		// Add Operation
//		grantedAuthorityList.addAll(userDetailsDao.findGrantedOperationByUserId(user.getUserId()));
//		
//		UserDetails userDetails = new UserDetails(
//			user.getUsername(),
//			user.getPassword(),
//			BooleanToStringConverter.convertToBeanAttribute(user.getEnabled()),
//			BooleanToStringConverter.convertToBeanAttribute(user.getAccountNonExpired()),
//			BooleanToStringConverter.convertToBeanAttribute(user.getCredentialsNonExpired()),
//			BooleanToStringConverter.convertToBeanAttribute(user.getAccountNonLocked()),
//			grantedAuthorityList
//		);
//		userDetails.setUserId(user.getUserId());
//		
//		return userDetails;
//	}
//	
//}

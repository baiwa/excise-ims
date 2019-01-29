package co.th.baiwa.buckwaframework.security.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.th.baiwa.buckwaframework.security.constant.ADConstant;
import co.th.baiwa.buckwaframework.security.domain.TMBPerson;
import co.th.baiwa.buckwaframework.security.domain.UserDetails;

@Service
public class UserDetailsService
		implements org.springframework.security.core.userdetails.UserDetailsService, TmbUserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	@Autowired
//	private UserProfileDao userProfileDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loadUserByUsername username={}", username);

		// Initial Granted Authority
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
//		passwordEncoder.encode("password")
		UserDetails userDetails = new UserDetails(username, "", grantedAuthorityList);
		if ("ADMIN".equalsIgnoreCase(username)) {
			grantedAuthorityList.add(new SimpleGrantedAuthority(ADConstant.ROLE_ADMIN));
			userDetails.setFirstName("ผู้ดูแลระบบ");
			userDetails.setLastName("กรมสรรพสามิต");
			userDetails.setUserId("0001");
			userDetails.setBranchCode("001");
		}

		UserDetails rs = new UserDetails(username, passwordEncoder.encode("passwords"), grantedAuthorityList);
		rs.setUserId(userDetails.getUserId());
		rs.setFirstName(userDetails.getFirstName());
		rs.setLastName(userDetails.getLastName());
		rs.setBranchCode("001");

		List<String> roles = new ArrayList<>();
		for (GrantedAuthority g : grantedAuthorityList) {
			roles.add(g.toString());
		}

//		List<String> auths = userProfileDao.getAuthbyRole(roles);
		List<String> auths = new ArrayList<>();
		rs.setAuths(auths);
		return rs;
	}

	@Override
	public UserDetails loadUserByUsername(String username, TMBPerson tMBPerson) throws UsernameNotFoundException {
		logger.info("loadUserByUsername username={}", username);

		// Initial Granted Authority
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
		// Roles
		List<String> rolesInAd = tMBPerson.getMemberOfs();
		for (String role : rolesInAd) {
			// TODO debug if AD OK
			logger.debug("rolesInAd : {}", role); // TODO plz checked
			grantedAuthorityList.add(new SimpleGrantedAuthority(role));
		}

		UserDetails rs = new UserDetails(username, "", grantedAuthorityList);
		rs.setUserId(tMBPerson.getUserid());
		rs.setBranchCode(tMBPerson.getBranchCode());
		rs.setOfficeCode(tMBPerson.getOfficeCode());
		rs.setDepartment(tMBPerson.getDepartment());
		rs.setGroup(tMBPerson.getGroup());
		rs.setBelongto(tMBPerson.getBelongto());
		rs.setTelephoneNo(tMBPerson.getTelephoneNo());
		rs.setEmail(tMBPerson.getEmail());
		rs.setDepartmentCode(tMBPerson.getDepartmentCode());

		String fullName = tMBPerson.getName(); // firstname , lastname
		logger.debug("Full Name : {}", fullName); // TODO plz checked
		String[] spfullName = fullName.split(" ");

		rs.setFirstName(spfullName[0]);
		rs.setLastName(spfullName[1]);

		List<String> roles = new ArrayList<>();
		for (GrantedAuthority g : grantedAuthorityList) {
			roles.add(g.toString());
		}

//		List<String> auths = userProfileDao.getAuthbyRole(roles);
		List<String> auths = new ArrayList<>();
		rs.setAuths(auths);
		return rs;
	}

}

package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.UserRole;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.UserRoleRepository;

@Service
public class UserRoleService {

	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public UserRole findById(Long id) {
		return userRoleRepository.findById(id).get();
	}
	
	public List<UserRole> findAll(Long id) {
		return userRoleRepository.findAll();
	} 
	
	public UserRole save(UserRole userRole) {
		return userRoleRepository.save(userRole);
	} 
	
	public void delete(UserRole userRole) {
		userRoleRepository.delete(userRole);
	} 
}

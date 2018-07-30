package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.RoleRepository;

@Service
public class RoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> getRoleAll() {
		logger.info("getAllRole");
		return roleRepository.findAll();
	}

	public Role getRoleById(Long roleId) {
		logger.info("getRoleById");
		return roleRepository.findOne(roleId);
	}
	
	public long getRoleCount() {
		logger.info("getRoleCount");
		return roleRepository.count();
	}

	public Role createRole(Role role) {
		logger.info("createRole");
		roleRepository.save(role);
		return role;
	}

	public Role updateRole(Role role) {
		logger.info("updateRole");
		roleRepository.save(role);
		return role;
	}

	public void deleteRole(Long roleId) {
		logger.info("deleteRole");
		roleRepository.delete(roleId);
	}

}

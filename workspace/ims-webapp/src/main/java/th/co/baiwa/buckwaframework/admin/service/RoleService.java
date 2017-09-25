package th.co.baiwa.buckwaframework.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.admin.persistence.dao.RoleDao;
import th.co.baiwa.buckwaframework.admin.persistence.entity.Role;

@Service("roleService")
public class RoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	private RoleDao roleDao;
	
	public List<Role> getRoleAll() {
		logger.info("getAllRole");
		return roleDao.findAll();
	}

	public Role getRoleById(Long roleId) {
		logger.info("getRoleById");
		return roleDao.findById(roleId);
	}
	
	public int getRoleCount() {
		logger.info("getRoleCount");
		return roleDao.count();
	}

	public Role createRole(Role role) {
		logger.info("createRole");
		Long key = roleDao.insert(role);
		role.setRoleId(key);
		return role;
	}

	public Role updateRole(Role role) {
		logger.info("updateRole");
		roleDao.update(role);
		return role;
	}

	public void deleteRole(Long roleId) {
		logger.info("deleteRole");
		roleDao.delete(roleId);
	}

}

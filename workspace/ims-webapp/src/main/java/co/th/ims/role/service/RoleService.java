package co.th.ims.role.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.th.ims.role.dao.RoleDao;
import co.th.ims.role.domain.Role;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public List<Role> findAll() {
		List<Role> role = new ArrayList<Role>();
		role = roleDao.findAll();
		return role;
	}
}

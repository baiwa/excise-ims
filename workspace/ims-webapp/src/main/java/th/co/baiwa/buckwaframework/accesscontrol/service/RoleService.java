package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.RoleRepository;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleFormVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;

@Service
public class RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public DataTableAjax<Role> list(RoleFormVo request) {

		List<Role> data = roleRepository.findByCriteria(request);

		DataTableAjax<Role> dataTableAjax = new DataTableAjax<Role>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(roleRepository.countByCriteria(request));
		dataTableAjax.setRecordsFiltered(data.size());

		return dataTableAjax;
	}

	public List<Role> getRoleAll() {
		logger.info("getAllRole");
		return roleRepository.findAll();
	}

	public Role getRoleById(Long roleId) {
		logger.info("getRoleById");
		return roleRepository.findById(roleId).get();
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
		roleRepository.deleteById(roleId);
	}

}

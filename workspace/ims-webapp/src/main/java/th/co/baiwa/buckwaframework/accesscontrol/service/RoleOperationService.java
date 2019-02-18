package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.RoleOperation;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.RoleOperationRepository;

@Service
public class RoleOperationService {

	@Autowired
	private RoleOperationRepository roleOperationRepository;

	public RoleOperation findById(Long id) {
		return roleOperationRepository.findById(id).get();
	}

	public List<RoleOperation> findAll(Long id) {
		return roleOperationRepository.findAll();
	}

	public RoleOperation save(RoleOperation roleOperation) {
		return roleOperationRepository.save(roleOperation);
	}

	public void delete(RoleOperation roleOperation) {
		roleOperationRepository.delete(roleOperation);
	}
}

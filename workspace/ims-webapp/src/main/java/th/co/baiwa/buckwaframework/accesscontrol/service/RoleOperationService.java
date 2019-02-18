package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.AdmRoleOperation;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.AdmRoleOperationRepository;

@Service
public class AdmRoleOperationService {
	
	@Autowired
	private AdmRoleOperationRepository admRoleOperationRepository;
	
	public AdmRoleOperation findById(Long id) {
		return admRoleOperationRepository.findById(id).get();
	}
	
	public List<AdmRoleOperation> findAll(Long id) {
		return admRoleOperationRepository.findAll();
	} 
	
	public AdmRoleOperation save(AdmRoleOperation admRoleOperation) {
		return admRoleOperationRepository.save(admRoleOperation);
	} 
	
	public void delete(AdmRoleOperation admRoleOperation) {
		admRoleOperationRepository.delete(admRoleOperation);
	} 
}

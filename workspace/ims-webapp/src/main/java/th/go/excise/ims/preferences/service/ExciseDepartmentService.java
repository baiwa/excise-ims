package th.go.excise.ims.preferences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.ExciseDepartment;
import th.go.excise.ims.preferences.persistence.repository.ExciseDepartmentRepository;

@Service
public class ExciseDepartmentService {

	@Autowired
	private ExciseDepartmentRepository exciseDepartmentRepository;
	
	public List<ExciseDepartment> findSectorList(){
		return exciseDepartmentRepository.findExciseDepartmentListByOffice("__0000");
	}
	
	public List<ExciseDepartment> findAreaListByOfficeCode(String officeCode){
		return exciseDepartmentRepository.findExciseDepartmentListByOffice(officeCode.substring(0, 2)+"__00");
	}
	
	public List<ExciseDepartment> findBranchListByOfficeCode(String officeCode){
		return exciseDepartmentRepository.findExciseDepartmentListByOffice(officeCode.substring(0, 4)+"__");
	}
	
	public List<ExciseDepartment> findAll(){
		return exciseDepartmentRepository.findAll();
	}
}

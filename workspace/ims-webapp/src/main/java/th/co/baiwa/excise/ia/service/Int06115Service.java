package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.repository.DisbursementRequestRepository;

@Service
public class Int06115Service {
	
	@Autowired
	private DisbursementRequestRepository DRRepository;

	public void save(DisbursementRequest en) {
		// TODO Auto-generated method stub
		
	}
	

}

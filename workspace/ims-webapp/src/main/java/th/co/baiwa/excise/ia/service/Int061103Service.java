package th.co.baiwa.excise.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.repository.TuitionFeeChildRepository;
import th.co.baiwa.excise.ia.persistence.repository.TuitionFeeRepository;

@Service
public class Int061103Service {

	@Autowired
	private TuitionFeeRepository tuitionFeeRepository;
	
	@Autowired
	private TuitionFeeChildRepository childRepository;
	

}


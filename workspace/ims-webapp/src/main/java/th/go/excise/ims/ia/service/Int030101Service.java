package th.go.excise.ims.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.int030101JdbcRepository;
import th.go.excise.ims.ia.vo.Int030101FormVo;

@Service
public class Int030101Service {
	@Autowired
	private int030101JdbcRepository int030101JdbcRepository;
	
	public void saveFactors(Int030101FormVo form) {
		
		int030101JdbcRepository.saveFactors(form);
	}
}

package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0301JdbcRepository;
import th.go.excise.ims.ia.vo.Int0301FormVo;

@Service
public class Int0301Service {
	
	@Autowired
	private Int0301JdbcRepository int0301JdbcRepository;
	
	public List<IaRiskFactors> list(Int0301FormVo form) {
		List<IaRiskFactors> iaRiskFactorsList = new ArrayList<IaRiskFactors>();
		
		iaRiskFactorsList = int0301JdbcRepository.list(form);
		
		return iaRiskFactorsList;
		
	}
	
	public void saveRiskFactorsLevel(Int0301FormVo form) {
		int0301JdbcRepository.saveRiskFactorsLevel(form);
	}

}

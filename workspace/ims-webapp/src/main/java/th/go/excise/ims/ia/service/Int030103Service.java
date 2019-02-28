package th.go.excise.ims.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030103JdbcRepository;
import th.go.excise.ims.ia.vo.Int0301FormVo;

@Service
public class Int030103Service {

	@Autowired
	private Int030103JdbcRepository int030103JdbcRepository;

	
	public void updatePercent(Int0301FormVo form) {
		for (IaRiskFactorsConfig irfc : form.getIaRiskFactorsConfigList()) {
			int030103JdbcRepository.listUpdatePercent(irfc);			
		}
	}
}

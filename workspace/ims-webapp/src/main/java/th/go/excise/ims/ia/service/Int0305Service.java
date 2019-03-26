package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaRiskFactorsMaster2JdbcRepository;
import th.go.excise.ims.ia.vo.Int0305FormVo;
import th.go.excise.ims.ia.vo.Int0305Vo;

@Service
public class Int0305Service {

	@Autowired
	private IaRiskFactorsMaster2JdbcRepository iaRiskFactorsMaster2JdbcRepository;


	public List<Int0305Vo> list(Int0305FormVo form) {
		List<Int0305Vo> iaRiskFactorsMasterList = iaRiskFactorsMaster2JdbcRepository.list(form);
		return iaRiskFactorsMasterList;
	}

	public void delete(Int0305FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		iaRiskFactorsMaster2JdbcRepository.delete(form);
	}
}

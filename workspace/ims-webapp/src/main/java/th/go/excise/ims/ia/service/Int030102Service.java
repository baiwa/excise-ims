package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMasterRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030102JdbcRepository;
import th.go.excise.ims.ia.vo.Int030102FormVo;

@Service
public class Int030102Service {
	
	@Autowired
	private Int030102JdbcRepository int030102JdbcRepository;
	
	@Autowired
	private IaRiskFactorsMasterRepository iaRiskFactorsMasterRepository;
	
	public List<IaRiskFactorsMaster> list(Int030102FormVo form){
		List<IaRiskFactorsMaster> iaRiskFactorsMasterList = new ArrayList<IaRiskFactorsMaster>();
		
		iaRiskFactorsMasterList = int030102JdbcRepository.list(form);
		
		return iaRiskFactorsMasterList;
	}
	
	
	public void delete(Int030102FormVo form){
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		int030102JdbcRepository.delete(form);
	}
	
	public void editStatus(Int030102FormVo form){
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		int030102JdbcRepository.editStatus(form);
	}
}

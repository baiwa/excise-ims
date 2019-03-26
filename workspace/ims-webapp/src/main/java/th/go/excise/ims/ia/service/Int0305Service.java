package th.go.excise.ims.ia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster2;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMaster2Repository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaRiskFactorsMaster2JdbcRepository;
import th.go.excise.ims.ia.vo.Int0305FormVo;
import th.go.excise.ims.ia.vo.Int0305Vo;

@Service
public class Int0305Service {

	@Autowired
	private IaRiskFactorsMaster2JdbcRepository iaRiskFactorsMaster2JdbcRepository;
	
	@Autowired
	private IaRiskFactorsMaster2Repository iaRiskFactorsMaster2Repository;


	public List<Int0305Vo> list(Int0305FormVo form) {
		List<Int0305Vo> iaRiskFactorsMasterList = iaRiskFactorsMaster2JdbcRepository.list(form);
		return iaRiskFactorsMasterList;
	}

	public void delete(Int0305FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		iaRiskFactorsMaster2JdbcRepository.delete(form);
	}
	
	public void edit(Int0305FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		 IaRiskFactorsMaster2 entity = iaRiskFactorsMaster2Repository.findById(form.getId()).get();
		 entity.setRiskFactorsMaster(form.getRiskFactorsMaster());
		 entity.setSide(form.getSide());
		 iaRiskFactorsMaster2Repository.save(entity);
	}
	
	
	public void add(Int0305FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		 IaRiskFactorsMaster2 entity = new IaRiskFactorsMaster2();
		 
		 entity.setRiskFactorsMaster(form.getRiskFactorsMaster());
		 entity.setSide(form.getSide());
		 entity.setInspectionWork(form.getInspectionWork());
		 entity.setNotDelete("N");
		 entity.setDataEvaluate(IaConstants.IA_DATA_EVALUATE.NEW);
		 
		 iaRiskFactorsMaster2Repository.save(entity);
	}
}

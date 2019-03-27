package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster2;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMaster2Repository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMasterRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaRiskFactorsMaster2JdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030102JdbcRepository;
import th.go.excise.ims.ia.vo.Int030101FormVo;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int030102Vo;
import th.go.excise.ims.ia.vo.Int0305FormVo;

@Service
public class Int0305Service {

	@Autowired
	private IaRiskFactorsMaster2JdbcRepository iaRiskFactorsMaster2JdbcRepository;
	
	@Autowired
	private IaRiskFactorsMaster2Repository iaRiskFactorsMaster2Repository;
	
	@Autowired
	private IaRiskFactorsMasterRepository iaRiskFactorsMasterRepository;
	
	@Autowired
	private Int030101Service int030101Service;
	
	@Autowired
	private Int030102Service int030102Service;
	
	@Autowired
	private Int030102JdbcRepository int030102JdbcRepository;

	public List<Int030102Vo> list(Int0305FormVo form) {
//		List<Int0305Vo> iaRiskFactorsMasterList = iaRiskFactorsMaster2JdbcRepository.list(form);
		Int030102FormVo form030102 = new Int030102FormVo();
		form030102.setBudgetYear(form.getBudgetYear());
		form030102.setInspectionWork(form.getInspectionWork());
		List<Int030102Vo> iaRiskFactorsMasterList = int030102Service.list(form030102);
		
		return iaRiskFactorsMasterList;
	}

	public void delete(Int0305FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		int030102JdbcRepository.delete(form);
	}
	
	public void edit(Int0305FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
//		 IaRiskFactorsMaster2 entity = iaRiskFactorsMaster2Repository.findById(form.getId()).get();
//		 entity.setRiskFactorsMaster(form.getRiskFactorsMaster());
//		 entity.setSide(form.getSide());
//		 iaRiskFactorsMaster2Repository.save(entity);
		 
		 IaRiskFactorsMaster entity1 = iaRiskFactorsMasterRepository.findById(form.getId()).get();
		 entity1.setRiskFactorsMaster(form.getRiskFactorsMaster());
		 entity1.setSide(form.getSide());
		 iaRiskFactorsMasterRepository.save(entity1);
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
		 
		 Int030101FormVo form030101 = new Int030101FormVo();
		 form030101.setBudgetYear(form.getBudgetYear());
		 form030101.setInspectionWork(form.getInspectionWork());
		 form030101.setRiskFactorsMaster(form.getRiskFactorsMaster());
		 form030101.setSide(form.getSide());
		 int030101Service.saveFactors(form030101);
	}
	
	
	public void addRiskFactors(Int0305FormVo form) {
		Int030102FormVo form030102 = new Int030102FormVo();
		
		for (Int0305FormVo element : form.getInt0305FormVoList()) {
			
			form030102 = new Int030102FormVo();
			form030102.setId(element.getId());
			form030102.setBudgetYear(element.getBudgetYear());
			form030102.setInspectionWork(element.getInspectionWork());
			form030102.setStatus(element.getStatus());
			int030102Service.editStatus(form030102);
			int030102Service.save(form030102);
		}
		
	}
	
}

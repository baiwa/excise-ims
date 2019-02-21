package th.go.excise.ims.ia.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMasterRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030102JdbcRepository;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int030102Vo;

@Service
public class Int030102Service {

	@Autowired
	private Int030102JdbcRepository int030102JdbcRepository;

	@Autowired
	private IaRiskFactorsMasterRepository iaRiskFactorsMasterRepository;

	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;

	public List<Int030102Vo> list(Int030102FormVo form) {
//		checkAndInsertTableFactorsStatus(form);
		List<Int030102Vo> iaRiskFactorsMasterList = new ArrayList<Int030102Vo>();
		iaRiskFactorsMasterList = int030102JdbcRepository.list(form);
		return iaRiskFactorsMasterList;
	}

	public void listUpdateStatus(Int030102FormVo form) {
		
		int030102JdbcRepository.listUpdateStatus(form);
		
	}
	
	public void checkAndInsertTableFactorsStatus(Int030102FormVo form) {
		int count = int030102JdbcRepository.checkAndInsertTableFactorsStatus(form);
		if (count == 0) {
			List<IaRiskFactorsMaster> iaRiskFactorsMasterList = new ArrayList<IaRiskFactorsMaster>();
			iaRiskFactorsMasterList = iaRiskFactorsMasterRepository.findByBudgetYearByInspectionWork(form.getBudgetYear(), form.getInspectionWork());

		}
	}
	
	public void delete(Int030102FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		int030102JdbcRepository.delete(form);
	}

	public void editStatus(Int030102FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		int030102JdbcRepository.editStatus(form);
	}

	public void save(Int030102FormVo form) {

		List<IaRiskFactorsMaster> iaRiskFactorsMasterList = int030102JdbcRepository.listSaveFactors(form);
		int030102JdbcRepository.deleteOldFactors(form);

		for (IaRiskFactorsMaster iaRiskFactorsMaster : iaRiskFactorsMasterList) {
			IaRiskFactors data = new IaRiskFactors();
			data.setBudgetYear(iaRiskFactorsMaster.getBudgetYear());
			data.setRiskFactors(iaRiskFactorsMaster.getRiskFactorsMaster());
			data.setInspectionWork(iaRiskFactorsMaster.getInspectionWork());

			data.setSide("S");
			data.setStatusScreen("ยังไม่ได้กำหนด");
			data.setCreatedBy(UserLoginUtils.getCurrentUsername());
			data.setCreatedDate(LocalDateTime.now());
			iaRiskFactorsRepository.save(data);
		}

	}
}

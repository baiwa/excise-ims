package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsStatus;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMasterRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsStatusRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030102JdbcRepository;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int030102Vo;
import th.go.excise.ims.ia.vo.Int0301FormVo;

@Service
public class Int030102Service {

	@Autowired
	private Int030102JdbcRepository int030102JdbcRepository;

	@Autowired
	private IaRiskFactorsMasterRepository iaRiskFactorsMasterRepository;

	@Autowired
	private IaRiskFactorsStatusRepository iaRiskFactorsStatusRepository;

	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;

	public List<Int030102Vo> list(Int030102FormVo form) {
		checkAndInsertTableFactorsStatus(form);
		List<Int030102Vo> iaRiskFactorsMasterList = new ArrayList<Int030102Vo>();
		iaRiskFactorsMasterList = int030102JdbcRepository.list(form);
		int index = 0;
		for (Int030102Vo int030102Vo : iaRiskFactorsMasterList) {
			IaRiskFactorsConfig irfc = new IaRiskFactorsConfig();
			if (int030102Vo.getIdConfig() != null) {
				irfc = iaRiskFactorsConfigRepository.findById(int030102Vo.getIdConfig()).get();

				iaRiskFactorsMasterList.get(index).setIaRiskFactorsConfig(irfc);
			}

			index++;
		}

		return iaRiskFactorsMasterList;
	}

	public void checkAndInsertTableFactorsStatus(Int030102FormVo form) {
		int count = int030102JdbcRepository.checkAndInsertTableFactorsStatus(form);
		if (count == 0) {
			List<IaRiskFactorsMaster> iaRiskFactorsMasterList = new ArrayList<IaRiskFactorsMaster>();
			iaRiskFactorsMasterList = iaRiskFactorsMasterRepository.findByInspectionWork(form.getInspectionWork());

			for (IaRiskFactorsMaster iaRiskS : iaRiskFactorsMasterList) {
				IaRiskFactorsStatus data = new IaRiskFactorsStatus();

				data.setIdMaster(iaRiskS.getId());
				data.setBudgetYear(form.getBudgetYear());
				data.setStatus("N");
				data.setInspectionWork(iaRiskS.getInspectionWork());

				data.setCreatedBy(UserLoginUtils.getCurrentUsername());
				data.setCreatedDate(LocalDateTime.now());
				iaRiskFactorsStatusRepository.save(data);
			}
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

		List<IaRiskFactors> iaRiskFactorsList = iaRiskFactorsRepository
				.findByInspectionWorkByBudgetYear(form.getInspectionWork(), form.getBudgetYear());

		int030102JdbcRepository.updateFactorsIsDeleteY(form);

		for (IaRiskFactorsMaster iaRiskFactorsMaster : iaRiskFactorsMasterList) {
			IaRiskFactors data = new IaRiskFactors();
			String check = "Add";
			BigDecimal id = new BigDecimal(0);
			for (IaRiskFactors checkFactors : iaRiskFactorsList) {
				if (checkFactors.getIdMaster().equals(iaRiskFactorsMaster.getId())) {
					id = checkFactors.getId();
					check = "Update";
					break;
				}
			}

			if ("Add".equals(check)) {
				data.setIdMaster(iaRiskFactorsMaster.getId());
				data.setBudgetYear(iaRiskFactorsMaster.getBudgetYear());
				data.setRiskFactors(iaRiskFactorsMaster.getRiskFactorsMaster());
				data.setInspectionWork(iaRiskFactorsMaster.getInspectionWork());

				data.setSide("S");
				data.setStatusScreen("ยังไม่ได้กำหนด");
				data.setCreatedBy(UserLoginUtils.getCurrentUsername());
				data.setCreatedDate(LocalDateTime.now());
				IaRiskFactors resultIdFactors = iaRiskFactorsRepository.save(data);
				IaRiskFactorsConfig dataConfig = new IaRiskFactorsConfig();
				dataConfig.setIdFactors(resultIdFactors.getId());
				dataConfig.setFactorsLevel(new BigDecimal(3));

				dataConfig.setCreatedBy(UserLoginUtils.getCurrentUsername());
				dataConfig.setCreatedDate(LocalDateTime.now());
				iaRiskFactorsConfigRepository.save(dataConfig);

			} else if ("Update".equals(check)) {
				int030102JdbcRepository.updateIsDelete(id);
				// iaRiskFactorsRepository.rrrrrr(id);
			}

		}

	}

	public void saveRiskFactorsLevelAndUpdateStatus(Int030102FormVo form) {
		int030102JdbcRepository.listUpdateStatus(form);
		int030102JdbcRepository.saveRiskFactorsLevel(form);
	}
}

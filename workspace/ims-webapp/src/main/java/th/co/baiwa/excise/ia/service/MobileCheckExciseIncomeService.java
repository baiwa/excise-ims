package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.dao.RiskTaskCheckingDao;
import th.co.baiwa.excise.ia.persistence.dao.RiskTypeDao;
import th.co.baiwa.excise.ia.persistence.vo.DataUpdateStatus;
import th.co.baiwa.excise.ia.persistence.vo.RequestApiSaveIncome;
import th.co.baiwa.excise.ia.persistence.vo.ResponseApiSaveIncome;
import th.co.baiwa.excise.ia.persistence.vo.RisTaskChecking;
import th.co.baiwa.excise.ia.persistence.vo.RiskTaskChecking;
import th.co.baiwa.excise.ia.persistence.vo.RiskType;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class MobileCheckExciseIncomeService {
	
	@Autowired
	private RiskTypeDao riskTypeDao;
	
	@Autowired
	private RiskTaskCheckingDao riskTaskCheckingDao;
	
	public ResponseApiSaveIncome updateRiskTask(RequestApiSaveIncome requestApiSaveIncome) {
		ResponseApiSaveIncome responseApiSaveIncome = new ResponseApiSaveIncome();
		responseApiSaveIncome.setStatus("0");
		responseApiSaveIncome.setDesc("Success");
		try {
			List<DataUpdateStatus> datas = requestApiSaveIncome.getDatas();
			if(BeanUtils.isNotEmpty(datas)) {
				for (DataUpdateStatus dataUpdateStatus : datas) {
					RiskType riskType = new RiskType();
					riskType.setCheckId(dataUpdateStatus.getCheckId());
//					riskTaskCheckingDao.deleteRiskTaskChecking(dataUpdateStatus.getCheckId());
					riskTypeDao.deleteRiskTask(dataUpdateStatus.getCheckId());
					Long seq = riskTypeDao.getRiskTaskId().longValue();
					riskType.setId(seq);
					riskType.setRiskType(dataUpdateStatus.getRiskType());
					riskType.setRemark(dataUpdateStatus.getRemark());
					riskTypeDao.createRiskTask(riskType);
					List<RisTaskChecking> riskRisTaskCheckingList = dataUpdateStatus.getRisTaskChecking();
					if(BeanUtils.isNotEmpty(riskRisTaskCheckingList)) {
						for (RisTaskChecking risTaskChecking : riskRisTaskCheckingList) {
							risTaskChecking.setTaskId(seq);
							riskTaskCheckingDao.createRiskTaskChecking(risTaskChecking);
						}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseApiSaveIncome.setStatus("1");
			responseApiSaveIncome.setDesc("Error");
		}
		
		return responseApiSaveIncome;
		
	}
	
	
	

}

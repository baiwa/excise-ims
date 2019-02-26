package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.MessageContants.IA;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int020301JdbcRepository;
import th.go.excise.ims.ia.vo.Int020301DataVo;
import th.go.excise.ims.ia.vo.Int020301HeaderVo;
import th.go.excise.ims.ia.vo.Int020301InfoVo;

@Service
public class Int020301Service {

	@Autowired
	private Int020301JdbcRepository int020301JdbcRepository;

	public List<Int020301HeaderVo> findHeaderByIdSide(String idSideStr, String budgetYear) {
		BigDecimal idSide = new BigDecimal(idSideStr);
		return int020301JdbcRepository.findHeaderByIdSide(idSide, budgetYear);
	}

	public List<Int020301InfoVo> findInfoByIdSide(String idSideStr, String budgetYear) {
		BigDecimal idSide = new BigDecimal(idSideStr);
		List<Int020301InfoVo> datas = new ArrayList<>();
		datas = int020301JdbcRepository.findInfoByIdSide(idSide, budgetYear);
		for (Int020301InfoVo data : datas) {
			// Sides Data
			int passValue = 0;
			int failValue = 0;
			List<Int020301DataVo> sideDtls = int020301JdbcRepository.findDataByIdSide(idSide, budgetYear, data.getAreaName());
			for (Int020301DataVo sideDtl : sideDtls) {
				// Calculate RiskName
				if (sideDtl.getAcceptValue() != null && sideDtl.getDeclineValue() != null) {
					if (sideDtl.getAcceptValue().doubleValue() < sideDtl.getDeclineValue().doubleValue()) {
						sideDtl.setRiskName("สูง");
						sideDtl.setRisk("HIGH");
						failValue++;
					} else {
						sideDtl.setRiskName("ต่ำ");
						sideDtl.setRisk("LOW");
						passValue++;
					}
				} else if (sideDtl.getAcceptValue() != null) {
					sideDtl.setRiskName("ต่ำ");
					sideDtl.setRisk("LOW");
					sideDtl.setDeclineValue(new BigDecimal(0));
					passValue++;
				} else if (sideDtl.getDeclineValue() != null) {
					sideDtl.setRiskName("สูง");
					sideDtl.setRisk("HIGH");
					sideDtl.setAcceptValue(new BigDecimal(0));
					failValue++;
				} else {
					sideDtl.setRiskName("สูง");
					sideDtl.setRisk("HIGH");
					sideDtl.setAcceptValue(new BigDecimal(0));
					sideDtl.setDeclineValue(new BigDecimal(0));
					failValue++;
				}
			}
			data.setSideDtls(sideDtls);
			// RiskQuantity
			data.setRiskQuantity(new BigDecimal(sideDtls.size()));
			// Sum Data
			data.setPassValue(new BigDecimal(passValue));
			data.setFailValue(new BigDecimal(failValue));
			// Finding Sector and Area Name
			List<ExciseDept> exciseDepts = ApplicationCache.getExciseSectorList();
			ExciseDept exciseDepart = ApplicationCache.getExciseDept(data.getAreaName());
			String area = exciseDepart.getDeptName();
			data.setAreaName(area);
			data.setStatusText(IA.qtnStatus(data.getStatusText()));
			for (ExciseDept exciseDept : exciseDepts) {
				if (exciseDept.getOfficeCode().substring(0, 2).equals(data.getSectorName().substring(0, 2))) {
					data.setSectorName(exciseDept.getDeptName());
				}
			}

		}
		return datas;
	}
}

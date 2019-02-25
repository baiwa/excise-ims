package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.MessageContants;
import th.co.baiwa.buckwaframework.common.constant.MessageContants.IA;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int020301JdbcRepository;
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
		for(Int020301InfoVo data: datas) {
			// Finding Sector and Area Name
			List<ExciseDept> exciseDepts = ApplicationCache.getExciseSectorList();
			ExciseDept exciseDepart = ApplicationCache.getExciseDept(data.getAreaName());
			String area = exciseDepart.getDeptName();
			data.setAreaName(area);
			data.setStatusText(IA.qtnStatus(data.getStatusText()));
			for(ExciseDept exciseDept: exciseDepts) {
				if (exciseDept.getOfficeCode().substring(0, 2).equals(data.getSectorName().substring(0, 2))) {
					data.setSectorName(exciseDept.getDeptName());
				}
			}
		}
		return datas;
	}
}

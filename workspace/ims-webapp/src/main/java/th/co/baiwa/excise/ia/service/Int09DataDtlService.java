package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09111FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09TableDtlVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int09FormDtlVo;

@Service
public class Int09DataDtlService {
	private static Logger log = LoggerFactory.getLogger(Int09DataDtlService.class);
	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;

	
	public Int09TableDtlVo getDataDtl(Int09FormDtlVo formDtlVo) {
		Int09TableDtlVo vo = new Int09TableDtlVo();
		vo.setIdProcess(formDtlVo.getIdProcess());
		vo.setName(formDtlVo.getName()+" "+formDtlVo.getLastName());
		vo.setPosition(formDtlVo.getPosition());
//		vo.setFeedDay();
//		vo.setFeedMoney();
//		vo.setRoostDay();
//		vo.setRoostMoney();
		vo.setPassage(formDtlVo.getPassage());
		vo.setOtherExpenses(formDtlVo.getOtherExpenses());
//		vo.setTotalMoney(totalMoney);
		vo.setRemark(formDtlVo.getRemark());
		return vo;
	}

	public void saveDataDtl(Int09TableDtlVo vo) {
		iaTravelEstimatorDao.saveDataDtl(vo);
	}
}

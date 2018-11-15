package th.co.baiwa.excise.cop.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.excise.cop.persistence.dao.CopCheckFiscalYearDao;
import th.co.baiwa.excise.cop.persistence.vo.Cop064FormVo;

@Service
public class Cop092Service {

	@Autowired
	private CopCheckFiscalYearDao copCheckFiscalYearDao;

	public void updateFlag(Cop064FormVo formVo) {
		
		copCheckFiscalYearDao.updateStatusCopCheckFiscalYearDtlById(formVo.getId());
	}

}

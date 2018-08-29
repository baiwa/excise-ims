package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.excise.constant.IaConstant.VALUE.IAPROOF_OF_PAYMENT_DOCUMENT_TYPE;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaProofOfPaymentDao;
import th.co.baiwa.excise.ia.persistence.entity.Budget;
import th.co.baiwa.excise.ia.persistence.vo.Int09221FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09221Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int09222FormVo;

@Service
public class Int09222Service {	
	
	@Autowired
	private  IaProofOfPaymentDao iaProofOfPaymentDao;
	
	public Long save(Int09222FormVo vo){
		Long id = iaProofOfPaymentDao.saveInt9222(vo);
		return id;
    }

}

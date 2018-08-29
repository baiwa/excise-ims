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

@Service
public class Int09221Service {	
	
	@Autowired
	private  IaProofOfPaymentDao iaProofOfPaymentDao;
	
	public DataTableAjax<Int09221Vo> findAll(Int09221FormVo formVo){			
			
		//query data
		List<Int09221Vo> list = iaProofOfPaymentDao.findAll(formVo);
		Long count = iaProofOfPaymentDao.count(formVo);
		
		//set data table
		DataTableAjax<Int09221Vo> dataTableAjax = new DataTableAjax<>();
		
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			//dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}
				
		return dataTableAjax;		
	}
	
	//department dropdown
	public List<LabelValueBean> documentTypeDropdown(){
		List<LabelValueBean> type = new ArrayList<LabelValueBean>();

		type.add(new LabelValueBean(IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.ONE_DESC,IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.ONE_CODE));
		type.add(new LabelValueBean(IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.TWO_DESC,IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.TWO_CODE));
		type.add(new LabelValueBean(IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.THREE_DESC,IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.THREE_CODE));
		
		return type;
	}

	public void delete(List<Long> ids){
           if (!ids.isEmpty()){
        	   for (Long id : ids) {
				iaProofOfPaymentDao.delete(id);
        	   }
           }
    }

}

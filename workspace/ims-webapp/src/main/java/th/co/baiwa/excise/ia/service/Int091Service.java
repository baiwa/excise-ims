package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.IaConstant.VALUE.IAPROOF_OF_PAYMENT_DOCUMENT_TYPE;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;
import th.co.baiwa.excise.ia.persistence.entity.Budget;
import th.co.baiwa.excise.ia.persistence.vo.Int091FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int091Vo;

@Service
public class Int091Service {

	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;

	public DataTableAjax<Int091Vo> findAll(Int091FormVo formVo) {

		// query data
		List<Int091Vo> list = iaTravelEstimatorDao.findAll(formVo);
		Long count = iaTravelEstimatorDao.count(formVo);

		// set data table
		DataTableAjax<Int091Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public void add(Int091FormVo formVo) {
		formVo.setCreatedBy(UserLoginUtils.getCurrentUsername());
		iaTravelEstimatorDao.add091(formVo);
	}

	// department dropdown
	public List<LabelValueBean> documentTypeDropdown() {
		List<LabelValueBean> type = new ArrayList<LabelValueBean>();

		type.add(new LabelValueBean(IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.ONE_DESC,
				IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.ONE_CODE));
		type.add(new LabelValueBean(IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.TWO_DESC,
				IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.TWO_CODE));
		type.add(new LabelValueBean(IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.THREE_DESC,
				IAPROOF_OF_PAYMENT_DOCUMENT_TYPE.THREE_CODE));

		return type;
	}

	public void delete(Long id) {
		
			iaTravelEstimatorDao.delete(id);

		
	}

}

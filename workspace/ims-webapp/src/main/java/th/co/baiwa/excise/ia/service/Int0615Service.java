package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.CheckMoneyWithdrawaDao;
import th.co.baiwa.excise.ia.persistence.vo.Int0615FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0615Vo;

@Service
public class Int0615Service {

	@Autowired
	private CheckMoneyWithdrawaDao checkMoneyWithdrawaDao;

	public DataTableAjax<Int0615Vo> findAll(Int0615FormVo formVo) {

		DataTableAjax<Int0615Vo> dataTableAjax = new DataTableAjax<>();
		List<Int0615Vo> list = new ArrayList<>();
		Long count = 0l;
		
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			Date startDate = DateConstant.convertStrToDate(formVo.getStartDate(), "MM/yyyy", DateConstant.LOCAL_TH);
			Date endDate = DateConstant.convertStrToDate(formVo.getEndDate(), "MM/yyyy", DateConstant.LOCAL_TH);

			String strStartdate = DateConstant.convertDateToStr(startDate, "yyyyMM", DateConstant.LOCAL_EN);
			String strEnddate = DateConstant.convertDateToStr(endDate, "yyyyMM", DateConstant.LOCAL_EN);

			formVo.setStartDate(strStartdate);
			formVo.setEndDate(strEnddate);

			list = checkMoneyWithdrawaDao.findAll(formVo);
			count = checkMoneyWithdrawaDao.count(formVo);
		}

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}

}

package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.Int0614Dao;
import th.co.baiwa.excise.ia.persistence.vo.Int0614FormSearchVo;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Service
public class Int0614Service {
	@Autowired
	private Int0614Dao int0614Dao;

	public DataTableAjax<Int0614FormSearchVo> search(Int0614FormSearchVo form) {
		List<Int0614FormSearchVo> data = new ArrayList<Int0614FormSearchVo>();
		if(BeanUtils.isNotEmpty(form.getCombo1())) {
			String typeD1 = int0614Dao.getTypeDesc(form.getCombo1());
			// String typeD2 = int0614Dao.getTypeDesc(form.getCombo2());
			Date startDate = DateConstant.convertStrToDate(form.getDateStart(), "MM/yyyy", DateConstant.LOCAL_TH);
			Date endDate = DateConstant.convertStrToDate(form.getDateEnd(), "MM/yyyy", DateConstant.LOCAL_TH);

			String strStartdate = DateConstant.convertDateToStr(startDate, "yyyyMM", DateConstant.LOCAL_EN);
			String strEnddate = DateConstant.convertDateToStr(endDate, "yyyyMM", DateConstant.LOCAL_EN);
			
			form.setDateStart(strStartdate);
			form.setDateEnd(strEnddate);
			form.setSector(typeD1);
			data = int0614Dao.findInRentHouse(form);
		}
		

		DataTableAjax<Int0614FormSearchVo> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(data.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(data.size()));
		dataTableAjax.setData(data);
		return dataTableAjax;
	}

}

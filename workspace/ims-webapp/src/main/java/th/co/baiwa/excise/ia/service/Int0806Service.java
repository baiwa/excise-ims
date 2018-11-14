package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.Int0806Dao;
import th.co.baiwa.excise.ia.persistence.entity.MoneyCheck;
import th.co.baiwa.excise.ia.persistence.vo.Int0806FormSearchVo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int0806Service {
	@Autowired
	private Int0806Dao int0806Dao;
	
	public List<Lov> getValue1(Lov en) {	
		return int0806Dao.getValue1(en);
	}
	
	public DataTableAjax<MoneyCheck> search(Int0806FormSearchVo en) {
		List<MoneyCheck> dataList = new ArrayList<MoneyCheck>();
		if(BeanUtils.isNotEmpty(en.getCombo1()) || BeanUtils.isNotEmpty(en.getCombo2())) {
			//query find subtype
			if("0".equals(en.getCombo1())) {
				en.setOfficeCode("");
			}else {
				String s1 =  int0806Dao.getSubtype(en.getCombo1());
				String s2 =  int0806Dao.getSubtype(en.getCombo2());
				//set office code
				en.setOfficeCode(s1 + s2 + "00");
//				en.setOfficeCode(en.getCombo1() + en.getCombo2() + "00");
			}
			//filter data
			dataList = int0806Dao.queryByfilter(en);
			
			if(dataList.size() > 0) {
				for (MoneyCheck m : dataList) {
					if(m.getTrnDate().compareTo(m.getDepositDate()) <= 1) {
						m.setStatusDate("S");
						m.setDepositDateStr(DateConstant.convertDateToStrDDMMYYYY(m.getDepositDate()));
						m.setTrnDateStr(DateConstant.convertDateToStrDDMMYYYY(m.getTrnDate()));
					}else {
						m.setStatusDate("F");
						m.setDepositDateStr(DateConstant.convertDateToStrDDMMYYYY(m.getDepositDate()));
						m.setTrnDateStr(DateConstant.convertDateToStrDDMMYYYY(m.getTrnDate()));
					}
					if("0".equals(m.getNetlocAmount().subtract(m.getNettaxAmount()))) {
						m.setStatusMoney("S");
					}else {
						m.setStatusMoney("F");
					}
				}
			}
		}
	
		DataTableAjax<MoneyCheck> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(dataList.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(dataList.size()));
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}
	
}

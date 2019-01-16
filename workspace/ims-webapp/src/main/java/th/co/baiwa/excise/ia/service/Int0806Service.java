package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.Int0806Vo;
import th.co.baiwa.excise.ia.persistence.dao.Int0806Dao;
import th.co.baiwa.excise.ia.persistence.entity.MoneyCheck;
import th.co.baiwa.excise.ia.persistence.vo.Int0806FormSearchVo;

@Service
public class Int0806Service {
	@Autowired
	private Int0806Dao int0806Dao;

	public List<Lov> getValue1(Lov en) {
		return int0806Dao.getValue1(en);
	}

	public DataTableAjax<Int0806Vo> search(Int0806FormSearchVo en) {
		List<Int0806Vo> dataList = new ArrayList<Int0806Vo>();
		List<MoneyCheck> list = new ArrayList<MoneyCheck>();
		Int0806Vo obj;
		// query find subtype
		if ("S".equals(en.getFlag())) {
			if ("0".equals(en.getCombo1())) {
				en.setOfficeCode("");
			} else {
				String s1 = int0806Dao.getSubtype(en.getCombo1());
				String s2 = int0806Dao.getSubtype(en.getCombo2());
				// set office code
				en.setOfficeCode(s1 + s2 + "00");
				// en.setOfficeCode(en.getCombo1() + en.getCombo2() + "00");
			}
			// filter data
			list = int0806Dao.queryByfilter(en);
		}

		if (list.size() > 0) {
			for (MoneyCheck m : list) {
				obj = new Int0806Vo();
				long diffInMillies = Math.abs(m.getDepositDate().getTime() - m.getTrnDate().getTime());
				long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				if (diff < 2) {
					obj.setStatusDate("S");
				} else {
					obj.setStatusDate("F");
				}
				obj.setId(m.getId());
				obj.setReceiptNo(m.getReceiptNo());
				obj.setDepositDateStr(DateConstant.convertDateToStrDDMMYYYY(m.getDepositDate()));
				obj.setTrnDateStr(DateConstant.convertDateToStrDDMMYYYY(m.getTrnDate()));
				obj.setNetlocAmount(m.getNetlocAmount());
				obj.setNettaxAmount(m.getNettaxAmount());
				obj.setCreatedDateStr(DateConstant.convertDateToStrDDMMYYYY(m.getCreatedDate()));
				obj.setIncomeCode(m.getIncomeCode());

				//find type account from income code
				List<Lov> incomeFilter = new ArrayList<Lov>();
				 incomeFilter = int0806Dao.filerByIncomeCode("ACCOUNT", m.getIncomeCode());
				 if(incomeFilter.size() > 0) {
					 obj.setAccountType(incomeFilter.get(0).getValue1());
				 }
				
				/*
				 * bug
				 */
				// if(BigDecimal.ZERO.equals( m.getNetlocAmount().subtract(m.getNettaxAmount())
				// )) {
				
				Long sum = obj.getNetlocAmount().longValue() - obj.getNettaxAmount().longValue();
				if (sum == 0) {
					obj.setStatusMoney("S");
				} else {
					obj.setStatusMoney("F");
				}
				
				dataList.add(obj);
			}
		}
	
		DataTableAjax<Int0806Vo> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(dataList.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(dataList.size()));
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}

}

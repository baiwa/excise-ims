package th.co.baiwa.excise.epa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa032FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa032Vo;

@Service
public class Epa032Service {

	public DataTableAjax<Epa032Vo> search(Epa032FormVo epa032FormVo) {
		DataTableAjax<Epa032Vo> dataTableAjax = new DataTableAjax<Epa032Vo>();
		List<Epa032Vo> list = new ArrayList<>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa032FormVo.getSearchFlag())) {
			
			Epa032Vo vo = new Epa032Vo();
			vo.setTypeList("fuel");
			vo.setProductName("Diesel");
			vo.setModel("High Speed Diesel");
			vo.setSize("30000");
			vo.setAmount("1");
			vo.setPrice("870000");
			vo.setPricePerTax("60900");
			vo.setAmountPer("");
			vo.setTax("60900");
			list.add(vo);
			
			Epa032Vo vo2 = new Epa032Vo();
			vo2.setTypeList("fuel");
			vo2.setProductName("Diesel");
			vo2.setModel("Low Speed Diesel");
			vo2.setSize("90000");
			vo2.setAmount("1");
			vo2.setPrice("2610000");
			vo2.setPricePerTax("182700");
			vo2.setAmountPer("");
			vo2.setTax("182700");
			list.add(vo2);
			
			Epa032Vo vo3 = new Epa032Vo();
			vo3.setTypeList("fuel");
			vo3.setProductName("Diesel");
			vo3.setModel("regular");
			vo3.setSize("20000");
			vo3.setAmount("1");
			vo3.setPrice("580000");
			vo3.setPricePerTax("40600");
			vo3.setAmountPer("");
			vo3.setTax("40600");
			list.add(vo3);
			
			
			dataTableAjax.setDraw(1l);
			dataTableAjax.setRecordsTotal(3l);
			dataTableAjax.setRecordsFiltered(3l);
			dataTableAjax.setData(list);
		}
		
		return dataTableAjax;
	}

}

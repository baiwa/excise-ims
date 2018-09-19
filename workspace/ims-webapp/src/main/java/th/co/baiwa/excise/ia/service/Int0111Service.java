package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.IaTaxReceipt;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncomeList;
import th.co.baiwa.excise.ws.entity.response.incfri8020.ResponseData;

@Service
public class Int0111Service {
	
	@Autowired
	private WebServiceExciseService ws;
	
	public void wsPulling(IncFri8020 req) {
		IncFri8020 response = ws.IncFri8020("000300", req.getYearMonthFrom(), req.getYearMonthTo(), req.getDateType(), req.getPageNo(), req.getDataPerPage());
		ResponseData data = response.getResponseData();
		List<IaTaxReceipt> tax = new ArrayList<>();
		IaTaxReceipt ta;
		for(IncomeList list: data.getIncomeList()) {
			ta = new IaTaxReceipt();
			tax.add(ta);
		}
	}
	
}

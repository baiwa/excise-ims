package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.IaTaxReceipt;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncomeList;
import th.co.baiwa.excise.ws.entity.response.incfri8020.ResponseData;

@Service
public class Int0111Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WebServiceExciseService ws;

	public List<IaTaxReceipt> wsPulling(IncFri8020 req) {
		logger.info("000300 {} {} {} {} {}", req.getYearMonthFrom(), req.getYearMonthTo(), req.getDateType(),
				req.getPageNo(), req.getDataPerPage());
		IncFri8020 response = ws.IncFri8020("000300", req.getYearMonthFrom(), req.getYearMonthTo(), req.getDateType(),
				req.getPageNo(), req.getDataPerPage());
		ResponseData data = response.getResponseData();
		List<IaTaxReceipt> tax = new ArrayList<>();
		IaTaxReceipt ta;
		for (IncomeList list : data.getIncomeList()) {
			ta = new IaTaxReceipt();
			ta.setReceiptDate(list.getDepositDate());
			ta.setSendDate(list.getSendDate());
			ta.setIncomeName(list.getIncomeName());
			ta.setReceiptNo(list.getReceiptNo());
			if (BeanUtils.isNotEmpty(list.getNettaxAmount()))
				ta.setNetTaxAmount(BigDecimal.valueOf(Double.parseDouble(list.getNettaxAmount())));
			if (BeanUtils.isNotEmpty(list.getNetLocAmount()))
				ta.setNetLocAmount(BigDecimal.valueOf(Double.parseDouble(list.getNetLocAmount())));
			if (BeanUtils.isNotEmpty(list.getLocOthAmount()))
				ta.setLocOthAmount(BigDecimal.valueOf(Double.parseDouble(list.getLocOthAmount())));
			if (BeanUtils.isNotEmpty(list.getLocExpAmount()))
				ta.setLocExpAmount(BigDecimal.valueOf(Double.parseDouble(list.getLocExpAmount())));
			if (BeanUtils.isNotEmpty(list.getOlderFundAmount()))
				ta.setOlderFundAmount(BigDecimal.valueOf(Double.parseDouble(list.getOlderFundAmount())));
			if (BeanUtils.isNotEmpty(list.getTpbsFundAmount()))
				ta.setTpbsFundAmount(BigDecimal.valueOf(Double.parseDouble(list.getTpbsFundAmount())));
			if (BeanUtils.isNotEmpty(list.getSendAmount()))
				ta.setSendAmount(BigDecimal.valueOf(Double.parseDouble(list.getSendAmount())));
			if (BeanUtils.isNotEmpty(list.getStampAmount()))
				ta.setStampAmount(BigDecimal.valueOf(Double.parseDouble(list.getStampAmount())));
			if (BeanUtils.isNotEmpty(list.getCustomAmount()))
				ta.setCustomAmount(BigDecimal.valueOf(Double.parseDouble(list.getCustomAmount())));
			tax.add(ta);
		}
		return tax;
	}

}

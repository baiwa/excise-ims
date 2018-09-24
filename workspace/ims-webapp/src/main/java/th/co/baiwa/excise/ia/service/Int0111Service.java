package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceipt;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceiptReq;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceiptVo;
import th.co.baiwa.excise.ia.persistence.repository.tax.receipt.IaTaxReceiptRepository;
import th.co.baiwa.excise.ia.persistence.repository.tax.receipt.IaTaxReceiptRepositoryImpl;
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

	@Autowired
	private IaTaxReceiptRepository iaTaxReceipt;

	@Autowired
	private IaTaxReceiptRepositoryImpl iaTaxReceiptImpl;

	public List<IaTaxReceipt> wsPulling(IncFri8020 req) {

		String officeCode = req.getOfficeCode();
		String dateFrom = req.getYearMonthFrom();
		String dateTo = req.getYearMonthTo();
		String dateType = req.getDateType();
		logger.info("{} {} {} {} {} {}", officeCode, dateFrom, dateTo, dateType, req.getPageNo(), req.getDataPerPage());

		List<IaTaxReceipt> taxs = new ArrayList<IaTaxReceipt>();
		IaTaxReceiptVo tax = new IaTaxReceiptVo();
		tax.setOfficeCode(officeCode);
		tax.setDateFrom(dateFrom);
		tax.setDateTo(dateTo);
		tax.setDateType(dateType);
		taxs = iaTaxReceiptImpl.findByIaTaxReceipt(tax);
		int countFrom = iaTaxReceiptImpl.coundByDateFrom(officeCode, dateType, dateFrom);
        int countTo = iaTaxReceiptImpl.coundByDateTo(officeCode, dateType, dateTo);
		logger.info("dateFrom:{} ,dateTo:{}", countFrom, countTo);
		if (countFrom > 0 && countTo > 0 && taxs.size() > 0) {
			return taxs;
		} else {
			IncFri8020 response = ws.IncFri8020(officeCode, dateFrom.substring(0, 6), dateTo.substring(0, 6), dateType,
					req.getPageNo(), req.getDataPerPage());
			ResponseData data = response.getResponseData();
			IaTaxReceipt ta;
			for (IncomeList list : data.getIncomeList()) {
				ta = new IaTaxReceipt();
				ta.setReceiptDate(list.getReceiptDate());
				ta.setDepositDate(list.getDepositDate());
				ta.setSendDate(list.getSendDate());
				ta.setIncomeName(list.getIncomeName());
				ta.setReceiptNo(list.getReceiptNo());
				ta.setOfficeCode(officeCode);
				ta.setDateType(dateType);
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
				boolean exist = false;
				for(IaTaxReceipt li: taxs) {
					String noA = BeanUtils.isEmpty(list.getReceiptNo()) ? null : list.getReceiptNo();
					String noB = BeanUtils.isEmpty(li.getReceiptNo()) ? null : li.getReceiptNo();
					String daA = BeanUtils.isEmpty(list.getReceiptDate()) ? null : list.getReceiptDate();
					String daB = BeanUtils.isEmpty(li.getReceiptDate()) ? null : li.getReceiptDate();
					if (daA.equals(daB)) {
						if (BeanUtils.isEmpty(noA) && BeanUtils.isEmpty(noB)) {
							exist = true;
							logger.info("Check: wsNo[{}] dbNo[{}] wsDa[{}] dbDa[{}]", noA, noB, daA, daB);
						} else if (BeanUtils.isNotEmpty(noA) && noA.equals(noB)) {
							exist = true;
							logger.info("Check: wsNo[{}] dbNo[{}] wsDa[{}] dbDa[{}]", noA, noB, daA, daB);
						} else if (BeanUtils.isNotEmpty(noB) && noB.equals(noA)) {
							exist = true;
							logger.info("Check: wsNo[{}] dbNo[{}] wsDa[{}] dbDa[{}]", noA, noB, daA, daB);
						}
					}
				}
				if (!exist) {
					taxs.add(ta);
				}
			}
			iaTaxReceipt.save(taxs);
			taxs = iaTaxReceiptImpl.findByIaTaxReceipt(tax);
			return taxs;
		}
	}

	public CommonMessage<List<IaTaxReceipt>> save(IaTaxReceiptReq req) {
		CommonMessage<List<IaTaxReceipt>> com = new CommonMessage<List<IaTaxReceipt>>();
		try {
			com.setData((List<IaTaxReceipt>) iaTaxReceipt.save(req.getData()));
			com.setMsg(ApplicationCache.getMessage("MSG_00002"));
		} catch (Exception e) {
			e.printStackTrace();
			com.setData(req.getData());
			com.setMsg(ApplicationCache.getMessage("MSG_00003"));
		}
		return com;
	}
}

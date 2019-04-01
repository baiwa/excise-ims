package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0118Hdr;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0118DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0118HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0118Vo;

@Service
public class TaFormTS0118Service extends AbstractTaFormTSService<TaFormTS0118Vo, TaFormTs0118Hdr> {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0118Service.class);
	
	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0118HdrRepository taFormTs0118HdrRepository;
	@Autowired
	private TaFormTs0118DtlRepository taFormTs0118DtlRepository;
	
	@Override
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_18;
	}
	
	@Override
	public byte[] processFormTS(TaFormTS0118Vo formTS0118Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0118Vo);
		byte[] reportFile = generateReport(formTS0118Vo);

		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	@Override
	public void saveFormTS(TaFormTS0118Vo formTS0118Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0118Vo.getFormTsNumber());

		TaFormTs0118Hdr formTS0118 = null;
//		if (StringUtils.isNotEmpty(formTS0118Vo.getFormTsNumber())) {
//			formTS0118 = taFormTs0118HdrRepository.findByFormTsNumber(formTS0118Vo.getFormTsNumber());
//			toEntity(formTS0118, formTS0118Vo);
//		} else {
//			formTS0118 = new TaFormTs0118Hdr();
//			toEntity(formTS0118, formTS0118Vo);
//			formTS0118.setOfficeCode(officeCode);
//			formTS0118.setBudgetYear(budgetYear);
//			formTS0118.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
//		}
//		taFormTs0118HdrRepository.save(formTS0118);
	}
	
	@Override
	public byte[] generateReport(TaFormTS0118Vo formTs0118Vo) throws Exception, IOException {
		Map<String, Object> params = new HashMap<String, Object>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("formTsNumber", formTs0118Vo.getFormTsNumber());
		params.put("bookNumber1", formTs0118Vo.getBookNumber1());
		params.put("bookNumber2", formTs0118Vo.getBookNumber2());
		params.put("docDate", formTs0118Vo.getDocDate());
		params.put("ownerFullName", formTs0118Vo.getOwnerFullName());
		params.put("factoryType", formTs0118Vo.getFactoryType());
		params.put("factoryName", formTs0118Vo.getFactoryName());
		params.put("newRegId", formTs0118Vo.getNewRegId());
		params.put("factoryAddress", formTs0118Vo.getFactoryAddress());
		params.put("companyAddress", formTs0118Vo.getCompanyAddress());
		params.put("lawSection", formTs0118Vo.getLawSection());
		params.put("lawGroup", formTs0118Vo.getLawGroup());
		params.put("auditDateStart", formTs0118Vo.getAuditDateStart());
		params.put("auditDateEnd", formTs0118Vo.getAuditDateEnd());
		params.put("sumAllTaxAmt", formTs0118Vo.getSumAllTaxAmt());
		params.put("sumAllTaxText", formTs0118Vo.getSumAllTaxText());
		params.put("officeName", formTs0118Vo.getOfficeName());
		params.put("tableHeaderDutyType", formTs0118Vo.getTableHeaderDutyType());
		params.put("tableHeaderUnit", formTs0118Vo.getTableHeaderUnit());
		params.put("reasonText", formTs0118Vo.getReasonText());
		params.put("signOfficerFullName1", formTs0118Vo.getSignOfficerFullName1());
		params.put("signOfficerDate1", formTs0118Vo.getSignOfficerDate1());
		params.put("signOfficerFullName2", formTs0118Vo.getSignOfficerFullName2());
		params.put("signOfficerDate2", formTs0118Vo.getSignOfficerDate2());
		params.put("extraMoneyDate", formTs0118Vo.getExtraMoneyDate());
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(formTs0118Vo.getTaFormTS0118DtlVoList());
		
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_18 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	@Override
	public List<String> getFormTsNumberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaFormTS0118Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}

package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0119;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0119Repository;
import th.go.excise.ims.ta.vo.TaFormTS0119Vo;

@Service
public class TaFormTS0119Service extends AbstractTaFormTSService<TaFormTS0119Vo, TaFormTs0119> {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0119Service.class);
	
	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0119Repository taFormTs0119Repository;
	
	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS0119Vo formTS0119Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0119Vo);
		byte[] reportFile = generateReport(formTS0119Vo);

		return reportFile;
	}
	
	protected void saveFormTS(TaFormTS0119Vo formTS0119Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0119Vo.getFormTsNumber());

		TaFormTs0119 formTS0119 = null;
		if (StringUtils.isNotEmpty(formTS0119Vo.getFormTsNumber())) {
			formTS0119 = taFormTs0119Repository.findByFormTsNumber(formTS0119Vo.getFormTsNumber());
			toEntity(formTS0119, formTS0119Vo);
		} else {
			formTS0119 = new TaFormTs0119();
			toEntity(formTS0119, formTS0119Vo);
			formTS0119.setOfficeCode(officeCode);
			formTS0119.setBudgetYear(budgetYear);
			formTS0119.setFormTsNumber(taFormTSSequenceService.getFormTsNumber(officeCode, budgetYear));
		}
		taFormTs0119Repository.save(formTS0119);
	}
	
	public byte[] generateReport(TaFormTS0119Vo request) throws Exception, IOException {
		Map<String, Object> params = new HashMap<String, Object>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));

		params.put("bookNumber1", request.getBookNumber1());
		params.put("bookNumber2", request.getBookNumber2());
		params.put("docText1", request.getDocText1());
		params.put("docText2", request.getDocText2());
		params.put("docText3", request.getDocText3());
		params.put("docDear", request.getDocDear());
		params.put("companyName", request.getCompanyName());
		params.put("factoryType", request.getFactoryType());
		params.put("factoryName", request.getFactoryName());
		params.put("newRegId", request.getNewRegId());
		params.put("facAddrNo", request.getFacAddrNo());
		params.put("facMooNo", request.getFacMooNo());
		params.put("facSoiName", request.getFacSoiName());
		params.put("facThnName", request.getFacThnName());
		params.put("facTambolName", request.getFacTambolName());
		params.put("facAmphurName", request.getFacAmphurName());
		params.put("facProvinceName", request.getFacProvinceName());
		params.put("facZipCode", request.getFacZipCode());
		params.put("signOfficerFullName", request.getSignOfficerFullName());
		params.put("followTypeFlag1", request.getFollowTypeFlag1());
		params.put("followTypeFlag2", request.getFollowTypeFlag2());
		// params.put("docText3", request.getDocText3());
		// params.put("docText3", request.getDocText3());
		// params.put("docText3", request.getDocText3());
		// params.put("docText3", request.getDocText3());
		params.put("docDate", request.getDocDate());

		request.setRefBookDate(ConvertDateUtils.parseStringToDate(request.getRefBookDateStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("refBookDate", request.getRefBookDate());
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_19 + "." + FILE_EXTENSION.JASPER, params);
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
	public TaFormTS0119Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}

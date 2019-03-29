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
import th.go.excise.ims.ta.persistence.entity.TaFormTs01142Hdr;
import th.go.excise.ims.ta.persistence.repository.TaFormTs01142DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs01142HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS01142Vo;

@Service
public class TaFormTS01142Service  extends AbstractTaFormTSService<TaFormTS01142Vo, TaFormTs01142Hdr>{
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0114Service.class);
	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs01142DtlRepository TaFormTs01142DtlRepository;
	@Autowired
	private TaFormTs01142HdrRepository taFormTs01142HdrRepository;
	
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_14_2;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS01142Vo taFormTS01142Vo) throws Exception {
		logger.info("processFormTS");

		// saveFormTS(formTS0113Vo);
		byte[] reportFile = generateReport(taFormTS01142Vo);

		return reportFile;
	}

	public void saveFormTS(TaFormTS01142Vo formTS01142Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
	
	}

	public byte[] generateReport(TaFormTS01142Vo formTS01142Vo) throws Exception, IOException {
		logger.info("generateReport");

		// get data to report
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("ownerFullName",formTS01142Vo.getOwnerFullName());
		params.put("factoryType",formTS01142Vo.getFactoryType());
		params.put("factoryName",formTS01142Vo.getFactoryName());
		params.put("auditDateStart",formTS01142Vo.getAuditDateStart());
		params.put("auditDateEnd",formTS01142Vo.getAuditDateEnd());
		params.put("dutyTypeText",formTS01142Vo.getDutyTypeText());
		params.put("newRegId",formTS01142Vo.getNewRegId());
		params.put("extraAmtDate",formTS01142Vo.getExtraAmtDate());
		params.put("signOwnerFullName",formTS01142Vo.getSignOwnerFullName());
		params.put("signOfficerFullName",formTS01142Vo.getSignOfficerFullName());

        JRDataSource dataSource = new JRBeanCollectionDataSource(formTS01142Vo.getTaFormTS01142DtlVoList());
		
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_14_2 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);
		
		return reportFile;
	}






	@Override
	public List<String> getFormTsNumberList() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TaFormTS01142Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}

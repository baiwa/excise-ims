package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs01141;
import th.go.excise.ims.ta.persistence.repository.TaFormTs01141Repository;
import th.go.excise.ims.ta.vo.TaFormTS01141Vo;

@Service
public class TaFormTS01141Service extends AbstractTaFormTSService<TaFormTS01141Vo, TaFormTs01141> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS01141Service.class);

	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs01141Repository taFormTs01141Repository;
	
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_14_1;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS01141Vo formTS01141Vo) throws Exception {
		logger.info("processFormTS");
		saveFormTS(formTS01141Vo);
		byte[] reportFile = generateReport(formTS01141Vo);
		return reportFile;
	}

	public void saveFormTS(TaFormTS01141Vo formTS01141Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS01141Vo.getFormTsNumber());

	}

	public byte[] generateReport(TaFormTS01141Vo formTS01141Vo) throws Exception {
		logger.info("export TA_FORM_TS01_14_1");
		
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		JasperPrint jasperPrint = null;
		
		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("docDate", formTS01141Vo.getDocDate());
		params.put("docDear", formTS01141Vo.getDocDear());
		params.put("factoryName", formTS01141Vo.getFactoryName());
		params.put("factoryTypeText", formTS01141Vo.getFactoryTypeText());
		params.put("auditDateStart", formTS01141Vo.getAuditDateStart());
		params.put("auditDateEnd", formTS01141Vo.getAuditDateEnd());
		params.put("auditDesc", formTS01141Vo.getAuditDesc());
		
		jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_14_1 + "." + FILE_EXTENSION.JASPER, params);
		items.add(new SimpleExporterInputItem(jasperPrint));
		
		if (formTS01141Vo.getTaFormTS01141VoList() != null) {
			Map<String, Object> subParams = null;
			for (TaFormTS01141Vo subFormTS01141Vo : formTS01141Vo.getTaFormTS01141VoList()) {
				subParams = new HashMap<>();
				subParams.put("pageNo", subFormTS01141Vo.getPageNo());
				subParams.put("auditDesc", subFormTS01141Vo.getAuditDesc());
				jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_14_1P2 + "." + FILE_EXTENSION.JASPER, subParams);
				items.add(new SimpleExporterInputItem(jasperPrint));
			}
		}

		// set output
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(items));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		byte[] content = outputStream.toByteArray();

		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

	public List<String> getFormTsNumberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaFormTS01141Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}

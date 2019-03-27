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

	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS01141Vo formTS01141Vo) throws Exception {
		logger.info("processFormTS");
		saveFormTS(formTS01141Vo);
		byte[] reportFile = generateReport(formTS01141Vo);
		return reportFile;
	}

	protected void saveFormTS(TaFormTS01141Vo formTS01141Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS01141Vo.getFormTsNumber());

	}

	public byte[] generateReport(TaFormTS01141Vo formTS01141Vo) throws Exception {
		logger.info("export TA_FORM_TS01_14_1");
		Map<String, Object> params1 = new HashMap<>();
		params1.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params1.put("docDate", formTS01141Vo.getDocDate());
		params1.put("docDear", formTS01141Vo.getDocDear());
		params1.put("factoryName", formTS01141Vo.getFactoryName());
		params1.put("factoryTypeText", formTS01141Vo.getFactoryTypeText());
		params1.put("auditDateStart", formTS01141Vo.getAuditDateStart());
		params1.put("auditDateEnd", formTS01141Vo.getAuditDateEnd());
		params1.put("auditDesc", formTS01141Vo.getAuditDesc());

		logger.info("export TA_FORM_TS01_14_1P2");
		Map<String, Object> params2 = new HashMap<>();
		params2.put("pageNo", formTS01141Vo.getPageNo());
		params2.put("auditDesc", formTS01141Vo.getAuditDesc());

		// set output
		JasperPrint jasperPrint1 = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_14_1 + "." + FILE_EXTENSION.JASPER, params1);
		JasperPrint jasperPrint2 = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_14_1P2 + "." + FILE_EXTENSION.JASPER, params2);

		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(jasperPrint1));
		items.add(new SimpleExporterInputItem(jasperPrint2));

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(items));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		byte[] content = outputStream.toByteArray();

		ReportUtils.closeResourceFileInputStream(params1);
		ReportUtils.closeResourceFileInputStream(params2);

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

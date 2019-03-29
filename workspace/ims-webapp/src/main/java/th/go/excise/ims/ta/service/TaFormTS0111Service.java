package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0111Dtl;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0111Hdr;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0111DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0111HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0111Vo;

@Service
public class TaFormTS0111Service extends AbstractTaFormTSService<TaFormTS0111Vo, TaFormTs0111Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);

	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0111HdrRepository taFormTs0111HdrRepository;
	@Autowired
	private TaFormTs0111DtlRepository taFormTs0111DtlRepository;

	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_11;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS0111Vo formTS0111Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0111Vo);
		byte[] reportFile = generateReport(formTS0111Vo);

		return reportFile;
	}

	public void saveFormTS(TaFormTS0111Vo formTS0111Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0111Vo.getFormTsNumber());

		TaFormTs0111Hdr formTS0111Hdr = null;
		TaFormTs0111Dtl formTS0111Dtl = null;
		List<TaFormTs0111Dtl> formTS0111DtlList = new ArrayList<>();
	}

	public byte[] generateReport(TaFormTS0111Vo formTS0111Vo) throws IOException, JRException {
		logger.info("export TA_FORM_TS01_11");

		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("docPlace", formTS0111Vo.getDocPlace());
		params.put("docDate", formTS0111Vo.getDocDate());
		params.put("docTime", formTS0111Vo.getDocTime());
		params.put("officerFullName", formTS0111Vo.getOfficerFullName());
		params.put("officerPosition", formTS0111Vo.getOfficerPosition());
		params.put("factoryName", formTS0111Vo.getFactoryName());
		params.put("newRegId", formTS0111Vo.getNewRegId());
		params.put("factoryType", formTS0111Vo.getFactoryType());
		params.put("facAddrNo", formTS0111Vo.getFacAddrNo());
		params.put("facMooNo", formTS0111Vo.getFacMooNo());
		params.put("facSoiName", formTS0111Vo.getFacSoiName());
		params.put("facThnName", formTS0111Vo.getFacThnName());
		params.put("facTambolName", formTS0111Vo.getFacTambolName());
		params.put("facAmphurName", formTS0111Vo.getFacAmphurName());
		params.put("facProvinceName", formTS0111Vo.getFacProvinceName());
		params.put("facZipCode", formTS0111Vo.getFacZipCode());
		params.put("deliverFullName", formTS0111Vo.getDeliverFullName());
		params.put("deliverPosition", formTS0111Vo.getDeliverPosition());
		params.put("deliverOther", formTS0111Vo.getDeliverOther());
		params.put("refBookNumber1", formTS0111Vo.getRefBookNumber1());
		params.put("refBookNumber2", formTS0111Vo.getRefBookNumber2());
		params.put("refDocDate", formTS0111Vo.getRefDocDate());
		params.put("taFormTS0111DtlVoList", formTS0111Vo.getTaFormTS0111DtlVoList());
		params.put("signAuthFullName1", formTS0111Vo.getSignAuthFullName1());
		params.put("signWitnessFullName1", formTS0111Vo.getSignWitnessFullName1());
		params.put("signWitnessFullName2", formTS0111Vo.getSignWitnessFullName2());
		
		logger.info("export TA_FORM_TS01_11-2");
		params.put("authFullName1", formTS0111Vo.getAuthFullName1());
		params.put("signAuthFullName2", formTS0111Vo.getSignAuthFullName2());
		params.put("signWitnessFullName3", formTS0111Vo.getSignWitnessFullName3());
		params.put("signWitnessFullName4", formTS0111Vo.getSignWitnessFullName4());
		params.put("authFullName2", formTS0111Vo.getAuthFullName2());
		params.put("authPosition", formTS0111Vo.getAuthPosition());
		params.put("authPositionOther", formTS0111Vo.getAuthPositionOther());
		params.put("authFrom", formTS0111Vo.getAuthFrom());
		params.put("authDate", formTS0111Vo.getAuthDate());
		params.put("signAuthFullName3", formTS0111Vo.getSignAuthFullName3());
		params.put("signAuthFullName4", formTS0111Vo.getSignAuthFullName4());
		params.put("signWitnessFullName5", formTS0111Vo.getSignWitnessFullName5());
		params.put("signWitnessFullName6", formTS0111Vo.getSignWitnessFullName6());
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(formTS0111Vo.getTaFormTS0111DtlVoList());
		
		// set output
		JasperPrint jasperPrint1 = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_11 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		JasperPrint jasperPrint2 = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_11P2 + "." + FILE_EXTENSION.JASPER, params);
		
		List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
		items.add(new SimpleExporterInputItem(jasperPrint1));
		items.add(new SimpleExporterInputItem(jasperPrint2));
		
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(items));
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		byte[] content = outputStream.toByteArray();
		
//		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

//	public byte[] exportTaFormTS0111P2(TaFormTS0111Vo ts0111Vo) throws IOException, JRException {
//		logger.info("export TA_FORM_TS01_11-2");
//
//		Map<String, Object> params = new HashMap<>();
//		params.put("authFullName1", ts0111Vo.getAuthFullName1());
//		params.put("signAuthFullName2", ts0111Vo.getSignAuthFullName2());
//		params.put("signWitnessFullName3", ts0111Vo.getSignWitnessFullName3());
//		params.put("signWitnessFullName4", ts0111Vo.getSignWitnessFullName4());
//		params.put("authFullName2", ts0111Vo.getAuthFullName2());
//		params.put("authPosition", ts0111Vo.getAuthPosition());
//		params.put("authPositionOther", ts0111Vo.getAuthPositionOther());
//		params.put("authFrom", ts0111Vo.getAuthFrom());
//		params.put("signAuthFullName3", ts0111Vo.getSignAuthFullName3());
//		params.put("signAuthFullName4", ts0111Vo.getSignAuthFullName4());
//		params.put("signWitnessFullName5", ts0111Vo.getSignWitnessFullName5());
//		params.put("signWitnessFullName6", ts0111Vo.getSignWitnessFullName6());
//		// format string to LocalDate
//		Date localDate = ConvertDateUtils.parseStringToDate(ts0111Vo.getAuthDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
//		params.put("day", ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.DD, ConvertDateUtils.LOCAL_TH));
//		params.put("month", ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.MMMM, ConvertDateUtils.LOCAL_TH));
//		params.put("year", ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.YYYY));
//
//		// set output
//		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_11P2 + "." + FILE_EXTENSION.JASPER, params);
//		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
//		ReportUtils.closeResourceFileInputStream(params);
//
//		return content;
//	}

	@Override
	public List<String> getFormTsNumberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaFormTS0111Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}

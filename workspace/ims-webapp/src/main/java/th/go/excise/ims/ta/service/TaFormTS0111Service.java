package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS0111Vo;

@Service
public class TaFormTS0111Service {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);

	public byte[] exportTaFormTS0111(TaFormTS0111Vo ts0111Vo) throws IOException, JRException {
		logger.info("export TA_FORM_TS01_11");

		Map<String, Object> params = new HashMap<>();
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE+"."+FILE_EXTENSION.JPG));
		params.put("docPlace", ts0111Vo.getDocPlace());
		params.put("docDate", ts0111Vo.getDocDate());
		params.put("docTime", ts0111Vo.getDocTime());
		params.put("officerFullName", ts0111Vo.getOfficerFullName());
		params.put("officerPosition", ts0111Vo.getOfficerPosition());
		params.put("factoryName", ts0111Vo.getFactoryName());
		params.put("newRegId", ts0111Vo.getNewRegId());
		params.put("factoryType", ts0111Vo.getFactoryType());
		params.put("facAddrNo", ts0111Vo.getFacAddrNo());
		params.put("facMooNo", ts0111Vo.getFacMooNo());
		params.put("facSoiName", ts0111Vo.getFacSoiName());
		params.put("facThnName", ts0111Vo.getFacThnName());
		params.put("facTambolName", ts0111Vo.getFacTambolName());
		params.put("facAmphurName", ts0111Vo.getFacAmphurName());
		params.put("facProvinceName", ts0111Vo.getFacProvinceName());
		params.put("facZipCode", ts0111Vo.getFacZipCode());
		params.put("deliverFullName", ts0111Vo.getDeliverFullName());
		params.put("deliverPosition", ts0111Vo.getDeliverPosition());
		params.put("deliverOther", ts0111Vo.getDeliverOther());
		params.put("refBookNumber1", ts0111Vo.getRefBookNumber1());
		params.put("refBookNumber2", ts0111Vo.getRefBookNumber2());
		params.put("refDocDate", ts0111Vo.getRefDocDate());
		params.put("taFormTS0111DtlVoList", ts0111Vo.getTaFormTS0111DtlVoList());
		params.put("signAuthFullName1", ts0111Vo.getSignAuthFullName1());
		params.put("signWitnessFullName1", ts0111Vo.getSignWitnessFullName1());
		params.put("signWitnessFullName2", ts0111Vo.getSignWitnessFullName2());
		// format string to LocalDate
		Date localDate = ConvertDateUtils.parseStringToDate(ts0111Vo.getDocDate(), ConvertDateUtils.DD_MM_YYYY,
				ConvertDateUtils.LOCAL_EN);
		params.put("day",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.DD, ConvertDateUtils.LOCAL_TH));
		params.put("month",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.MMMM, ConvertDateUtils.LOCAL_TH));
		params.put("year",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.YYYY));

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_011 + "." + FILE_EXTENSION.JASPER,
				params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
	
	public byte[] exportTaFormTS01112(TaFormTS0111Vo ts0111Vo) throws IOException, JRException {
		logger.info("export TA_FORM_TS01_11-2");

		Map<String, Object> params = new HashMap<>();
		params.put("authFullName1", ts0111Vo.getAuthFullName1());
		params.put("signAuthFullName2", ts0111Vo.getSignAuthFullName2());
		params.put("signWitnessFullName3", ts0111Vo.getSignWitnessFullName3());
		params.put("signWitnessFullName4", ts0111Vo.getSignWitnessFullName4());
		params.put("authFullName2", ts0111Vo.getAuthFullName2());
		params.put("authPosition", ts0111Vo.getAuthPosition());
		params.put("authPositionOther", ts0111Vo.getAuthPositionOther());
		params.put("authFrom", ts0111Vo.getAuthFrom());
		params.put("signAuthFullName3", ts0111Vo.getSignAuthFullName3());
		params.put("signAuthFullName4", ts0111Vo.getSignAuthFullName4());
		params.put("signWitnessFullName5", ts0111Vo.getSignWitnessFullName5());
		params.put("signWitnessFullName6", ts0111Vo.getSignWitnessFullName6());
		// format string to LocalDate
		Date localDate = ConvertDateUtils.parseStringToDate(ts0111Vo.getAuthDate(), ConvertDateUtils.DD_MM_YYYY,
				ConvertDateUtils.LOCAL_EN);
		params.put("day",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.DD, ConvertDateUtils.LOCAL_TH));
		params.put("month",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.MMMM, ConvertDateUtils.LOCAL_TH));
		params.put("year",
				ConvertDateUtils.formatDateToString(localDate, ConvertDateUtils.YYYY));

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_011_2 + "." + FILE_EXTENSION.JASPER,
				params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
}

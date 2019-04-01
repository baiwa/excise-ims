package th.go.excise.ims.ta.service;

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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0115Hdr;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0115DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0115HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0115Vo;

@Service
public class TaFormTS0115Service extends AbstractTaFormTSService<TaFormTS0115Vo, TaFormTs0115Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0115Service.class);

	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;

	@Autowired
	private TaFormTs0115HdrRepository taFormTs0115HdrRepository;

	@Autowired
	private TaFormTs0115DtlRepository taFormTs0115DtlRepository;
	
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_15;
	}
	
	public byte[] processFormTS(TaFormTS0115Vo formTS0115Vo) throws Exception {
		logger.info("processFormTS");
		// saveFormTS(formTS0115Vo);
		byte[] reportFile = generateReport(formTS0115Vo);
		return reportFile;
	}

	@Transactional(rollbackOn = { Exception.class })
	public void saveFormTS(TaFormTS0115Vo formTS0115Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0115Vo.getFormTsNumber());
	}

	@Override
	public byte[] generateReport(TaFormTS0115Vo formTs0115Vo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("officeName", formTs0115Vo.getOfficeName());
		params.put("docDate", formTs0115Vo.getDocDate());
		params.put("ownerFullName", formTs0115Vo.getOwnerFullName());
		params.put("factoryType", formTs0115Vo.getFactoryType());
		params.put("factoryName", formTs0115Vo.getFactoryName());
		params.put("newRegId", formTs0115Vo.getNewRegId());
		params.put("facAddrNo", formTs0115Vo.getFacAddrNo());
		params.put("facSoiName", formTs0115Vo.getFacSoiName());
		params.put("facThnName", formTs0115Vo.getFacThnName());
		params.put("facTambolName", formTs0115Vo.getFacTambolName());
		params.put("facAmphurName", formTs0115Vo.getFacAmphurName());
		params.put("facProvinceName", formTs0115Vo.getFacProvinceName());
		params.put("facZipCode", formTs0115Vo.getFacZipCode());
		params.put("signOwnerFullName", formTs0115Vo.getSignOwnerFullName());
		params.put("signOfficerFullName", formTs0115Vo.getSignOfficerFullName());
		params.put("signWitnessFullName1", formTs0115Vo.getSignWitnessFullName1());
		params.put("signWitnessFullName2", formTs0115Vo.getSignWitnessFullName2());

		JRDataSource dataSource = new JRBeanCollectionDataSource(formTs0115Vo.getTaFormTS0115DtlVoList());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_15 + "." + FILE_EXTENSION.JASPER, params, dataSource);
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
	public TaFormTS0115Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}

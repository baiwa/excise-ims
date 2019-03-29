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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0108Hdr;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0108DtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaFormTs0108HdrRepository;
import th.go.excise.ims.ta.vo.TaFormTS0108Vo;

@Service
public class TaFormTS0108Service extends AbstractTaFormTSService<TaFormTS0108Vo, TaFormTs0108Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);
	
	@Autowired
	private TaFormTSSequenceService taFormTSSequenceService;
	@Autowired
	private TaFormTs0108HdrRepository taFormTs0108HdrRepository;
	@Autowired
	private TaFormTs0108DtlRepository taFormTs0108DtlRepository;
	
	public String getReportName() {
		return REPORT_NAME.TA_FORM_TS01_08;
	}
	
	@Transactional(rollbackOn = { Exception.class })
	public byte[] processFormTS(TaFormTS0108Vo formTS0108Vo) throws Exception {
		logger.info("processFormTS");

		saveFormTS(formTS0108Vo);
		byte[] reportFile = generateReport(formTS0108Vo);

		return reportFile;
	}

	public void saveFormTS(TaFormTS0108Vo formTS0108Vo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = ExciseUtils.getCurrentBudgetYear();
		logger.info("saveFormTS officeCode={}, formTsNumber={}", officeCode, formTS0108Vo.getFormTsNumber());
		
		TaFormTs0108Hdr formTs0108Hdr = null;
	}

	public byte[] generateReport(TaFormTS0108Vo formTS0108Vo) throws JRException, IOException {
		logger.info("generateReport");
		
		Map<String, Object> params = new HashMap<>();
		JRDataSource dataSource = new JRBeanCollectionDataSource(formTS0108Vo.getTaFormTS0108DtlVoList());
		
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_08 + "." + FILE_EXTENSION.JASPER, params, dataSource);
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
	public TaFormTS0108Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}

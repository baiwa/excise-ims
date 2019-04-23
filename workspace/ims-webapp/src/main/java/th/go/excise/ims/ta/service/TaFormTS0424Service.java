package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0424Hdr;
import th.go.excise.ims.ta.vo.TaFormTS0424Vo;

@Service
public class TaFormTS0424Service extends AbstractTaFormTSService<TaFormTS0424Vo, TaFormTs0424Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0424Service.class);

	@Override
	public byte[] generateReport(TaFormTS0424Vo taFormTS0424Vo) throws Exception, IOException {

		// get data to report
		Map<String, Object> params = new HashMap<>();

		params.put("formTsNumber", taFormTS0424Vo.getFormTsNumber());
		params.put("factoryName", taFormTS0424Vo.getFactoryName());
		params.put("auditMonthStart", taFormTS0424Vo.getAuditMonthStart());
		params.put("auditMonthEnd", taFormTS0424Vo.getAuditMonthEnd());
		params.put("auditYear", taFormTS0424Vo.getAuditYear());

		JRDataSource dataSource = new JRBeanCollectionDataSource(taFormTS0424Vo.getTaFormTS0424DtlVoList());

		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS04_24 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return reportFile;
	}

	@Override
	public String getReportName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] processFormTS(TaFormTS0424Vo vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFormTS(TaFormTS0424Vo vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getFormTsNumberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaFormTS0424Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}

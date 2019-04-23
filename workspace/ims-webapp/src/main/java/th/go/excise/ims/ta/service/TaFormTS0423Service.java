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
import th.go.excise.ims.ta.persistence.entity.TaFormTs0423Hdr;
import th.go.excise.ims.ta.vo.TaFormTS0423Vo;

@Service
public class TaFormTS0423Service extends AbstractTaFormTSService<TaFormTS0423Vo, TaFormTs0423Hdr> {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0423Service.class);
	@Override
	public byte[] generateReport(TaFormTS0423Vo taFormTS0423Vo) throws Exception, IOException {
		logger.info("generateReport");

		// get data to report
		Map<String, Object> params = new HashMap<>();
		
		params.put("formTsNumber", taFormTS0423Vo.getFormTsNumber());
		params.put("alphabet",taFormTS0423Vo.getAlphabet());
		params.put("factoryTypeText",taFormTS0423Vo.getFactoryTypeText());
		params.put("ownerName",taFormTS0423Vo.getOwnerName());
		params.put("newRegId",taFormTS0423Vo.getNewRegId());
		params.put("facAddrNo",taFormTS0423Vo.getFacAddrNo());
		params.put("facSoiName",taFormTS0423Vo.getFacSoiName());
		params.put("facThnName",taFormTS0423Vo.getFacThnName());
		params.put("facTambolName",taFormTS0423Vo.getFacTambolName());
		params.put("facAmphurName",taFormTS0423Vo.getFacAmphurName());
		params.put("facProvinceName",taFormTS0423Vo.getFacProvinceName());

        JRDataSource dataSource = new JRBeanCollectionDataSource(taFormTS0423Vo.getTaFormTS0423DtlVoList());

		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS04_23 + "." + FILE_EXTENSION.JASPER, params, dataSource);
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
	public byte[] processFormTS(TaFormTS0423Vo vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFormTS(TaFormTS0423Vo vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getFormTsNumberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaFormTS0423Vo getFormTS(String formTsNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}

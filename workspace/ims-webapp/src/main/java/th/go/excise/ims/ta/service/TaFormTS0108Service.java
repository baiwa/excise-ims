package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS0108DtlVo;

@Service	
public class TaFormTS0108Service {
	public byte[] export(TaFormTS0108DtlVo request) throws JRException, IOException {
		String reportName = "TA_FORM_TS0108.jasper";
		Map<String, Object> params = new HashMap<>();

		List<TaFormTS0108DtlVo> ts0108DtlList = new ArrayList<>();
		TaFormTS0108DtlVo ts0108DtlVo = null;
			for (int i = 0;i<10;i++) {
			
				ts0108DtlVo = new TaFormTS0108DtlVo();
				ts0108DtlVo.setRecNo(request.getRecNo());
				ts0108DtlVo.setAuditDate(request.getAuditDate());
				ts0108DtlVo.setOfficerFullName(request.getOfficerFullName());
				ts0108DtlVo.setOfficerPosition(request.getOfficerPosition());
				ts0108DtlVo.setAuditTime(request.getAuditTime());
				ts0108DtlVo.setAuditDest(request.getAuditDest());
				ts0108DtlVo.setAuditTopic(request.getAuditTopic());
				ts0108DtlVo.setApprovedAck(request.getApprovedAck());
				ts0108DtlVo.setAuditResultDate(request.getAuditResultDate());
				ts0108DtlVo.setAuditComment(request.getAuditComment());
				ts0108DtlList.add(ts0108DtlVo);
				
		
			}

			JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportName, params,
					new JRBeanCollectionDataSource(ts0108DtlList));

			byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);
			ReportUtils.closeResourceFileInputStream(params);

			return reportFile;
		}


	}


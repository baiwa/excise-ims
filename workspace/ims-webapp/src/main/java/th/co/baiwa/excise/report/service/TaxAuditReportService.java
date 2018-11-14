package th.co.baiwa.excise.report.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ta.persistence.entity.YearPlan;
import th.co.baiwa.excise.ta.persistence.repository.YearPlanRepository;

@Service
public class TaxAuditReportService {

	@Autowired
	private YearPlanRepository yearPlanRepository;

	public Message updateFlag(BigDecimal id) {
		Message message = null;
		try {
			if (id.intValue() != 0) {
				YearPlan data = yearPlanRepository.findOne(id);
				data.setFlag("2");
				yearPlanRepository.save(data);
				message = ApplicationCache.getMessage("MSG_00002");
			} else {
				message = ApplicationCache.getMessage("MSG_00003");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}

	/*
	 * public byte[] exciseTaxToPDF(ReportJsonBean reportJsonBean) throws
	 * IOException, JRException {
	 * 
	 * Gson gson = new Gson(); Map<String, Object> params = new HashMap<String,
	 * Object>(); params = (Map<String, Object>)
	 * gson.fromJson(reportJsonBean.getJson(), params.getClass());
	 * 
	 * //params.put("logo1", ReportUtils.getResourceFile(PATH.IMAGE_PATH,
	 * "logo1.jpg"));
	 * 
	 * JasperPrint jasperPrint1 = ReportUtils.exportReport("exciseTaxForm001",
	 * params, new JREmptyDataSource()); JasperPrint jasperPrint2 =
	 * ReportUtils.exportReport("exciseTaxForm002", params, new
	 * JREmptyDataSource());
	 * 
	 * List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();
	 * items.add(new SimpleExporterInputItem(jasperPrint1)); items.add(new
	 * SimpleExporterInputItem(jasperPrint2));
	 * 
	 * JRPdfExporter exporter = new JRPdfExporter(); exporter.setExporterInput(new
	 * SimpleExporterInput(items));
	 * 
	 * ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	 * exporter.setExporterOutput(new
	 * SimpleOutputStreamExporterOutput(outputStream)); exporter.exportReport();
	 * byte[] content = outputStream.toByteArray();
	 * 
	 * ReportUtils.closeResourceFileInputStream(params);
	 * 
	 * return content; }
	 */

}

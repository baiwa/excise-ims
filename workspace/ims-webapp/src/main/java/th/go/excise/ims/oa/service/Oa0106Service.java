package th.go.excise.ims.oa.service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbDtl;
import th.go.excise.ims.oa.persistence.repository.OaHydrocarbDtlRepository;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0106JdbcRepository;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa0106FormVo;
import th.go.excise.ims.oa.vo.Oa0106Vo;

@Service
public class Oa0106Service {

	@Autowired
	private Oa0106JdbcRepository oa0106JdbcRepo;

	@Autowired
	private OaHydrocarbDtlRepository oaHydrocarbDtlRepo;

	public DataTableAjax<Oa0106Vo> filterByCriteria(Oa0106FormVo request, String officeCode) {
		String offCode = OaOfficeCode.officeCodeLike(officeCode);
		List<Oa0106Vo> data = oa0106JdbcRepo.getData(request, offCode);
		int count = oa0106JdbcRepo.countData(request, offCode);
		DataTableAjax<Oa0106Vo> dataTableAjax = new DataTableAjax<Oa0106Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		return dataTableAjax;
	}

	public DataTableAjax<Oa0106Vo> filterHydByCriteria(Oa0106FormVo request, String officeCode) {
		String offCode = OaOfficeCode.officeCodeLike(officeCode);
		List<Oa0106Vo> data = oa0106JdbcRepo.getDataHyd(request, offCode);
		int count = oa0106JdbcRepo.countDataHyd(request, offCode);
		DataTableAjax<Oa0106Vo> dataTableAjax = new DataTableAjax<Oa0106Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		return dataTableAjax;
	}

	@SuppressWarnings("finally")
	public byte[] objectToPDF(String licenseId,String dtlId) {
		byte[] content = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params = setPrepareJasperOaOpe01(licenseId,dtlId);
			JasperPrint jasperPrint1 = ReportUtils.getJasperPrint("OA_OPE_01" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint2 = ReportUtils.getJasperPrint("OA_OPE_05" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint3 = ReportUtils.getJasperPrint("OA_OPE_07" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint4 = ReportUtils.getJasperPrint("OA_OPE_02" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint5 = ReportUtils.getJasperPrint("OA_OPE_03" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
			JasperPrint jasperPrint6 = ReportUtils.getJasperPrint("OA_OPE_04" + "." + FILE_EXTENSION.JASPER, params,
					new JREmptyDataSource());
//			JasperPrint jasperPrint7 = ReportUtils.getJasperPrint("Solvent-01", params, new JREmptyDataSource());
//			JasperPrint jasperPrint8 = ReportUtils.getJasperPrint("Solvent-02", params, new JREmptyDataSource());

			List<ExporterInputItem> items = new ArrayList<ExporterInputItem>();

			items.add(new SimpleExporterInputItem(jasperPrint1));
			items.add(new SimpleExporterInputItem(jasperPrint2));
			items.add(new SimpleExporterInputItem(jasperPrint3));
			items.add(new SimpleExporterInputItem(jasperPrint4));
			items.add(new SimpleExporterInputItem(jasperPrint5));
			items.add(new SimpleExporterInputItem(jasperPrint6));
//			items.add(new SimpleExporterInputItem(jasperPrint7));
//			items.add(new SimpleExporterInputItem(jasperPrint8));

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(items));

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			exporter.exportReport();
			content = outputStream.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {
			return content;
		}
	}

	public Map<String, Object> setPrepareJasperOaOpe01(String licenseId,String dtlId) {
		Map<String, Object> params = new HashMap<String, Object>();
		BigDecimal id = new BigDecimal(dtlId);
		Oa0106Vo license = oa0106JdbcRepo.getCustomerLicenseById(licenseId);
		Optional<OaHydrocarbDtl> oaHydrocabon = oaHydrocarbDtlRepo.findById(id);
		OaHydrocarbDtl data = oaHydrocabon.get();

		// OA_OPE_01
		params.put("licenseNo1", license.getLicenseNo());
		params.put("licenseNo2", "");
		params.put("licenseDate", "11");
		params.put("licenseMonth", "เมษายน");
		params.put("licenseYear", "2562");
		params.put("companyName", license.getCompanyName());
		params.put("identityCompany", license.getIdentifyNo());
		params.put("totalEmployee", data.getEmployeeTemporary().toString());
		params.put("permanentEmployee", data.getEmployeePermanent().toString());
		params.put("temporaryEmployee", data.getEmployeeTemporary().toString());
		params.put("warehouse", license.getWarehouseAddress());
		params.put("officeOwnType", data.getOfficePlaceOwner());
		params.put("workStartTime", data.getWorkingStartDate().toString());
		params.put("workEndTime", data.getWorkingEndDate().toString());
		params.put("workingDate", data.getWorkdayPermonth().toString());
		params.put("numberOfTank", data.getNumberOfTank().toString());
		params.put("tankCapacity", data.getTankCapacity());
		params.put("numberUtility", data.getNumberUtility());
		params.put("orderType", data.getOrderType());
		params.put("orderPayMethod", data.getOrderPayMethod());
		params.put("rentOfficePrice",data.getOfficeRentAmount() != null ? data.getOfficeRentAmount().toString():null);
		params.put("orderPayMethodOther", data.getPayMethodOther());

		// OA_OPE_05
		
		// OA_OPE_07
		
		// OA_OPE_02
		params.put("asaleToAgent", data.getASaleToAgent());
		params.put("asaleToUser", data.getASaleToUser());
		params.put("asaleAgentLicense", data.getASaleAgentLicense());
		params.put("asaleUserLicense", data.getASaleUserLicense());
		params.put("sentToAgent", data.getSentToAgent());
		params.put("sentToUser", data.getSentToUser());
		params.put("materail", data.getMaterail());
		params.put("document", data.getDocument());
		params.put("productProcess", data.getProductProcess());
		params.put("productNextime", data.getProductNextime());
		if (data.getUseStartDate() != null && data.getUseEndDate() != null) {
			params.put("useStartDate", ConvertDateUtils.formatDateToString(data.getUseStartDate(), ConvertDateUtils.DD_MM_YYYY));
			params.put("useEndDate", ConvertDateUtils.formatDateToString(data.getUseEndDate(), ConvertDateUtils.DD_MM_YYYY));
		}
		params.put("buyOverlimit", data.getBuyOverlimit());

		return params;
	}

}

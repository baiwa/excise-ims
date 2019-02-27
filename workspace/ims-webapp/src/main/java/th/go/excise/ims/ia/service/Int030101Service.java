package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.controller.Int030102Controller;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsStatus;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMasterRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsStatusRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.int030101JdbcRepository;
import th.go.excise.ims.ia.util.ExcalUtil;
import th.go.excise.ims.ia.vo.Int030101FormVo;
import th.go.excise.ims.ia.vo.Int030101Vo;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;

@Service
public class Int030101Service {
	private Logger logger = LoggerFactory.getLogger(Int030101Service.class);

	@Autowired
	private int030101JdbcRepository int030101JdbcRepository;

	@Autowired
	private IaRiskFactorsMasterRepository iaRiskFactorsMasterRepository;

	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;

	@Autowired
	private IaRiskFactorsStatusRepository iaRiskFactorsStatusRepository;

	@Autowired
	private Int0301Service int0301Service;

	@Autowired
	private ExcalUtil excalUtil;

	public Int030101Vo saveFactors(Int030101FormVo form) {
		// Save IaRiskFactorsMaster
		IaRiskFactorsMaster dataFactorsMaster = new IaRiskFactorsMaster();
		dataFactorsMaster.setBudgetYear(form.getBudgetYear());
		dataFactorsMaster.setRiskFactorsMaster(form.getRiskFactorsMaster());
		dataFactorsMaster.setInspectionWork(form.getInspectionWork());
		dataFactorsMaster.setNotDelete("N");
		IaRiskFactorsMaster dataFactorsMasterRes = iaRiskFactorsMasterRepository.save(dataFactorsMaster);

		// Save IaRiskFactorsStatus
		IaRiskFactorsStatus dataFactorsStatus = new IaRiskFactorsStatus();
		dataFactorsStatus.setIdMaster(dataFactorsMasterRes.getId());
		dataFactorsStatus.setBudgetYear(form.getBudgetYear());
		dataFactorsStatus.setStatus("Y");
		dataFactorsStatus.setInspectionWork(form.getInspectionWork());
		iaRiskFactorsStatusRepository.save(dataFactorsStatus);

		// Save IaRiskFactors
		IaRiskFactors dataFactors = new IaRiskFactors();
		dataFactors.setIdMaster(dataFactorsMasterRes.getId());
		dataFactors.setRiskFactors(form.getRiskFactorsMaster());
		dataFactors.setBudgetYear(form.getBudgetYear());
		dataFactors.setInspectionWork(form.getInspectionWork());
		dataFactors.setSide(form.getSide());
		dataFactors.setStatusScreen("ยังไม่ได้กำหนด");
		IaRiskFactors dataFactorsRes = iaRiskFactorsRepository.save(dataFactors);

		// Save IaRiskFactorsConfig
		IaRiskFactorsConfig dataFactorsConfig = new IaRiskFactorsConfig();
		dataFactorsConfig.setIdFactors(dataFactorsRes.getId());
		dataFactorsConfig.setRiskUnit(form.getRiskUnit());
		dataFactorsConfig
				.setStartDate(ConvertDateUtils.parseStringToDate(form.getDateFrom(), ConvertDateUtils.DD_MM_YYYY));// ,
																													// ConvertDateUtils.LOCAL_TH
		dataFactorsConfig.setEndDate(ConvertDateUtils.parseStringToDate(form.getDateTo(), ConvertDateUtils.DD_MM_YYYY));
//		dataFactorsConfig.setInfoUsedRiskDesc(form.getDataName());
		dataFactorsConfig.setInfoUsedRisk("1");
//
		Int0301FormVo form0301 = new Int0301FormVo();
		form0301.setBudgetYear(form.getBudgetYear());
		form0301.setInspectionWork(form.getInspectionWork());
		BigDecimal factorsLevels = new BigDecimal(3);
		List<Int0301Vo> int0301VoList = int0301Service.listdynamic(form0301);
		for (Int0301Vo int0301Vo : int0301VoList) {
			if (int0301Vo.getIaRiskFactorsConfig().getFactorsLevel() != null) {
				factorsLevels = int0301Vo.getIaRiskFactorsConfig().getFactorsLevel();
				break;
			}
		}
//		BigDecimal factorsLevels = int030101JdbcRepository.getFactorsLevel(form);
		dataFactorsConfig.setFactorsLevel(factorsLevels);
		IaRiskFactorsConfig dataFactorsFacRes = iaRiskFactorsConfigRepository.save(dataFactorsConfig);

		Int030101Vo res = new Int030101Vo();
		Int030101FormVo formVo = new Int030101FormVo();
		res.setIdFactor(dataFactorsRes.getId());
		formVo.setRiskFactorsMaster(form.getRiskFactorsMaster());
		formVo.setSide(form.getSide());
		formVo.setDateFrom(form.getDateFrom());
		formVo.setDateTo(form.getDateTo());
		formVo.setRiskUnit(form.getRiskUnit());

		res.setInt030101FormVo(formVo);
		return res;
	}

	public ByteArrayOutputStream exportInt030101() throws IOException {
		/* create spreadsheet */
		List<IaRiskFactorsMaster> dataFactorsMasterList = new ArrayList<IaRiskFactorsMaster>();
		XSSFWorkbook workbook = excalUtil.setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* Header */
		String[] tbTH1 = { "ลำดับ", "หน่วยงาน", "ค่าความเสี่ยง " };

		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(excalUtil.thStyle);
		}

		/* set sheet */

		// setColumnWidth
		for (int i = 1; i <= 2; i++) {
			if (i == 1 || i == 2) {
				sheet.setColumnWidth(i, 76 * 60);
			} else {
				sheet.setColumnWidth(i, 76 * 100);
			}

		}

		/* Detail */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<String> dataList = getdataList();
		for (String item : dataList) {
			row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(item);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}

		/* EndDetail */

		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}

	public List<String> getdataList() {
		List<String> dataList = new ArrayList<String>();
		dataList.add("แผนหลักเกณฑ์การประเมินผลการปฏิบัติราชการ	");
		dataList.add("โครงการตามยุทธศาตร์	");
		dataList.add("แผนบริหารความเสี่ยง	");
		return dataList;
	}

	public List<IaRiskFactorsData> readFileExcel(Int030101FormVo form) throws Exception {
		List<IaRiskFactorsData> dataUploadList = new ArrayList<IaRiskFactorsData>();
		MultipartFile file = form.getFile();
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		DataFormatter dataFormatter = new DataFormatter();

		for (Sheet sheet : workbook) {
			for (Row row : sheet) {

				if (row.getRowNum() == 0) {
					continue; // just skip the rows if row number is 0 (Header)
				}

				IaRiskFactorsData dataUpload = new IaRiskFactorsData();
//				logger.info("row : " , ());

				int columns = 1;
				// dataUpload.setIdFactors(new
				// BigDecimal(dataFormatter.formatCellValue(row.getCell(columns++))));
				// dataUpload.setBudgetYear(dataFormatter.formatCellValue(row.getCell(columns++)));
				dataUpload.setProject(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns++))));
				// dataUpload.setInspectionWork(new
				// BigDecimal(dataFormatter.formatCellValue(row.getCell(columns++))));

				dataUploadList.add(dataUpload);
			}
		}
		return dataUploadList;
	}

}

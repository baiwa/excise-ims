package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ExcelUtils;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.VerifyAccountDetil;
import th.co.baiwa.excise.ia.persistence.entity.VerifyAccountHeader;
import th.co.baiwa.excise.ia.persistence.repository.VerifyAccountDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.VerifyAccountHdRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int071ExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int071FormVo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int071Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private VerifyAccountHdRepository verifyAccountHdRepository;
	
	@Autowired
	private VerifyAccountDtlRepository verifyAccountDtlRepository;

	public DataTableAjax<Int071ExcelVo> readExcel(Int071FormVo formVo)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		List<Int071ExcelVo> excelVo = new ArrayList<Int071ExcelVo>();
		byte[] byt = formVo.getFileExcel().getBytes();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getLastRowNum();
		Map<String, Integer> map = new HashMap<String, Integer>();
		// set object value header columns
		VerifyAccountHeader objHeader = new VerifyAccountHeader();
		int countHeader = 0;
		long headerId = 0L;

		/* rows */

		for (int r = 0; r < totalRows; r++) {

			Row row = sheet.getRow(r);
			if (row != null) {
				short minColIx = row.getFirstCellNum();
				short maxColIx = row.getLastCellNum();

				boolean header = true;
				boolean detail = true;

				header = (minColIx == 0 && maxColIx == 12 ? false : true);
				detail = (minColIx == 1 && maxColIx == 11 ? false : true);

				// check header
				if (!header) {

					/* column */
					List<String> columns = new ArrayList<String>();
					for (short colIx = minColIx; colIx < maxColIx; colIx++) {

						Cell cell = row.getCell(colIx);
						if (cell != null) {
							map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
							columns.add(ExcelUtils.getCellValueAsString(cell));
						}
					}
					if (BeanUtils.isEmpty(objHeader.getDisbursementCode())
							|| BeanUtils.isEmpty(objHeader.getDisbursementName())
							|| BeanUtils.isEmpty(objHeader.getDepartment())
							|| BeanUtils.isEmpty(objHeader.getReportDate())
							|| BeanUtils.isEmpty(objHeader.getReportTime())) {
						headerId = addDataHeader(objHeader, columns, countHeader);
						countHeader++;
					}
				}

				// check detail
				if (!detail) {

					/* column */
					List<String> columns = new ArrayList<String>();
					for (short colIx = minColIx; colIx < maxColIx; colIx++) {

						Cell cell = row.getCell(colIx);
						if (cell != null) {
							map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
							columns.add(ExcelUtils.getCellValueAsString(cell));
						} else {
							columns.add("");
						}
					}
					if (columns.get(2) != "") {
						addData(excelVo, columns, r, headerId);
					}

				}
			}
		}

		DataTableAjax<Int071ExcelVo> dataTableAjax = new DataTableAjax<>();

		dataTableAjax.setRecordsTotal(Long.valueOf(excelVo.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(excelVo.size()));
		dataTableAjax.setData(excelVo);

		return dataTableAjax;
	}

//	public static String getStringValue(Cell cell) {
//
//		if (cell == null)
//			return "";
//
//		switch (cell.getCellTypeEnum()) {
//		case BOOLEAN:
//			return cell.getBooleanCellValue() ? "1" : "0";
//		case FORMULA:
//			return cell.getCellFormula();
//		case NUMERIC:
//			cell.setCellType(CellType.STRING);
//			return cell.getStringCellValue();
//		case STRING:
//			return cell.getStringCellValue();
//		default:
//			return "";
//		}
//	}

	public void addData(List<Int071ExcelVo> excelVo, List<String> data, int rowId, long headerId) {
		Int071ExcelVo vo = new Int071ExcelVo();
		try {
			if(headerId > 0) {
				vo.setVerifyAccountHeaderId(headerId);
			}
			vo.setColumId(rowId);
			vo.setColum0(StringUtils.trim(data.get(0)));
			vo.setColum1(StringUtils.trim(data.get(1)));
			vo.setColum2(StringUtils.trim(data.get(2)));
			vo.setColum3(StringUtils.trim(data.get(3)));
			vo.setColum4(StringUtils.trim(data.get(4)));
			vo.setColum5(StringUtils.trim(data.get(5)));
			vo.setColum6(StringUtils.trim(data.get(6)));
			vo.setColum7(StringUtils.trim(data.get(7)));
			vo.setColum8(StringUtils.trim(data.get(8)));
			vo.setColum9(StringUtils.trim(data.get(9)));
			vo.setColum10(StringUtils.trim(data.get(10)));
			vo.setColum11(StringUtils.trim(data.get(11)));

			excelVo.add(vo);
		} catch (Exception e) {
			excelVo.add(vo);
		}
	}

	public long addDataHeader(VerifyAccountHeader header, List<String> data, int countHeader) {
		String hd1 = data.get(2);
		String[] lineOne = hd1.split(" ");
		String disbursementCode = lineOne[1];
		String department = lineOne[2];
		String date = data.get(4);

		if (BeanUtils.isEmpty(header.getDisbursementCode())) {
			header.setDisbursementCode(disbursementCode);
		}

		if (BeanUtils.isEmpty(header.getDepartment())) {
			header.setDepartment(department);
		}

		if (BeanUtils.isEmpty(header.getReportDate())) {
			header.setReportDate(date);
		}

		if (countHeader == 1) {
			String hd2 = data.get(2);
			String[] lineTwo = hd2.split(" ");
			String disbursementName = lineTwo[1] + " " + lineTwo[2];
			String time = data.get(4);
			header.setDisbursementName(disbursementName);
			header.setReportTime(time);
		}

		long headerId = 0L;
		if (BeanUtils.isNotEmpty(header.getDisbursementCode()) && BeanUtils.isNotEmpty(header.getDisbursementName())
				&& BeanUtils.isNotEmpty(header.getDepartment()) && BeanUtils.isNotEmpty(header.getReportDate())
				&& BeanUtils.isNotEmpty(header.getReportTime())) {
			logger.info("SAVE HEADER!!!!!!!");
			VerifyAccountHeader headerData = verifyAccountHdRepository.save(header);

			// set headerId for Detail
			headerId = headerData.getVerifyAccountHeaderId();
		}

		return headerId;
	}

	public void save(List<VerifyAccountDetil> excelList) {
		logger.info("SAVE DETAIL!!!");
		verifyAccountDtlRepository.save(excelList);
	}

}

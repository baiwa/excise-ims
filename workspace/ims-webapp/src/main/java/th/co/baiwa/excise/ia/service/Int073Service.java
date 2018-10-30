package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ExcelUtils;
import th.co.baiwa.excise.ia.persistence.vo.Int073Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int076Vo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int073Service {

	private Logger logger = LoggerFactory.getLogger(Int073Service.class);
	DecimalFormat formatter = new DecimalFormat("#,###.00");
	
	public List<Int073Vo> readFileExcelTrialBalanceSheet(Int073Vo formVo)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		List<Int073Vo> excelTrialBalanceSheetList = new ArrayList<>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		logger.info("readFileExcelTrialBalanceSheet");
		System.out.println(formVo.getFileExel().getOriginalFilename());
		System.out.println(formVo.getFileExel().getContentType());
		byte[] byt = formVo.getFileExel().getBytes();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getLastRowNum();

		boolean header = false;
		boolean detail = false;

		// row header
		for (int r = 0; r <= totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				short startCellHeader = row.getFirstCellNum();
				Cell cellHeader = row.getCell(startCellHeader);
				header = ("บัญชีแยกประเภท".equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellHeader))) ? true
						: false);

				detail = (header ? true : false);
				if (detail) {
					r++;
					/* loop detail row */
					for (int rowCheckDetail = r; rowCheckDetail <= totalRows; rowCheckDetail++) {
						Row rowDetail = sheet.getRow(rowCheckDetail);
						boolean checkEndDetail = false;
						boolean checkEndDoc = false;

						List<String> culumns = new ArrayList<>();
						if (rowDetail != null) {
							/* loop detail column */
							for (short col = 1; col <= 11; col++) {
								Cell cell = rowDetail.getCell(col);

								short startCellDetail = rowDetail.getFirstCellNum();
								Cell cellCheck = rowDetail.getCell(startCellDetail);

								checkEndDetail = ("รายงานงบทดลองหน่วยเบิกจ่ายรายเดือน".equals(
										StringUtils.trim(ExcelUtils.getCellValueAsString(cellCheck))) ? true : false);

								if (checkEndDetail) {
									rowCheckDetail += 10;
									break;
								}
								String value = ExcelUtils.getCellValueAsString(cell);
								//System.out.println(value + " col::" + col + " row::" + rowCheckDetail);

								if (value != null) {
									map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
									culumns.add(ExcelUtils.getCellValueAsString(cell));
								} else {
									culumns.add("");
								}

							}
							addDataTrialBalanceSheet(excelTrialBalanceSheetList, culumns, rowCheckDetail);
						}
					}

				}
			}

		}

		return excelTrialBalanceSheetList;
	}

	public void addDataTrialBalanceSheet(List<Int073Vo> excelTrialBalanceSheetList, List<String> data, int rowId) {
		boolean checkData;
		checkData = (data.isEmpty() ? false : true);
		if (checkData) {
			Int073Vo TrialBalanceSheet = new Int073Vo();

			try {
				TrialBalanceSheet.setId(new Long(rowId));
				TrialBalanceSheet.setAccountNumber(StringUtils.trim(data.get(0)));
				if (BeanUtils.isNotEmpty(data.get(2))) {
					TrialBalanceSheet.setAccountName(StringUtils.trim(data.get(2)));
				} else {
					TrialBalanceSheet.setAccountName("รวม");
				}

				if ("0".equals(data.get(4))) {
					TrialBalanceSheet.setSummitTest(StringUtils.trim(data.get(4)));
				} else {
					TrialBalanceSheet.setSummitTest(formatter.format(new BigDecimal(data.get(4))));
				}
				
				if ("0".equals(data.get(8))) {
					TrialBalanceSheet.setDebitTest(StringUtils.trim(data.get(8)));
				} else {
					TrialBalanceSheet.setDebitTest(formatter.format(new BigDecimal(data.get(8))));
				}
				
				if ("0".equals(data.get(9))) {
					TrialBalanceSheet.setCreditTest(StringUtils.trim(data.get(9)));
				} else {
					TrialBalanceSheet.setCreditTest(formatter.format(new BigDecimal(data.get(9))));
				}
				
				if ("0".equals(data.get(10))) {
					TrialBalanceSheet.setLiftUpTest(StringUtils.trim(data.get(10)));
				} else {
					TrialBalanceSheet.setLiftUpTest(formatter.format(new BigDecimal(data.get(10))));
				}

				excelTrialBalanceSheetList.add(TrialBalanceSheet);
			} catch (Exception e) {
				excelTrialBalanceSheetList.add(TrialBalanceSheet);

			}

		}
	}

}

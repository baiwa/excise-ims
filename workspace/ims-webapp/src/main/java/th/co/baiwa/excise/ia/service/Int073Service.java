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
import th.co.baiwa.excise.ia.persistence.vo.Int073CheckDataVo;
import th.co.baiwa.excise.ia.persistence.vo.Int073Vo;

@Service
public class Int073Service {
	/* readFileExcelLedgerSheet */
	public static final String ACCOUNT_TEXT = "เลขที่บัญชี G/L";
	public static final String END_DATA = "*";
	public static final String END_BOX = "**";
	public static final String END_SHEET = "***";
	public static final String DEBIT_NO = "40";
	public static final String CREDIT_NO = "50";
	public static final String NUMBER_ZERO = "0";
	public static final String NUMBER_DOT_ZERO = "0.0";
	/* readFileExcelTrialBalanceSheet */
	public static final String ACCOUNT_TYPE = "บัญชีแยกประเภท";
	public static final String END_DATA_MON_UNIT = "รายงานงบทดลองหน่วยเบิกจ่ายรายเดือน";
	public static final String END_SHEET_AMOUNT = "Amount";
	/* checkData */
	public static final String TOTAL = "รวม";
	// public static final int NUMBER_TEST = 15;

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

		// loop row main
		for (int r = 0; r <= totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				short startCol = row.getFirstCellNum();
				/* set column */
				Cell cell = row.getCell(startCol);
				if (cell != null) {
					// END_SHEET
					if (END_SHEET_AMOUNT.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cell)))) {
						System.out.println("End Sheet  row ::" + r);
						break;
						// format data
					} else if (ACCOUNT_TYPE.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cell)))) {
						r += 1;
						while (r <= totalRows) {
							Row rowFormat1 = sheet.getRow(r);
							if (rowFormat1 != null) {

								short startColFormat1 = rowFormat1.getFirstCellNum();
								short stopColFormat1 = rowFormat1.getLastCellNum();

								Cell cellFormat1 = rowFormat1.getCell(startColFormat1);
								if (cellFormat1 != null) {
									// END_DATA
									if (END_DATA_MON_UNIT
											.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellFormat1)))
											|| END_SHEET_AMOUNT.equals(
													StringUtils.trim(ExcelUtils.getCellValueAsString(cellFormat1)))) {
										break;
										// READ_DATA
									} else {
										// System.out.println("row ::" + r);
										List<String> columns = new ArrayList<>();
										/* loop columns */
										for (short readCol = startColFormat1; readCol < stopColFormat1; readCol++) {
											Cell readcell = rowFormat1.getCell(readCol);
											if (readcell != null) {
												// System.out.println(StringUtils.trim(ExcelUtils.getCellValueAsString(readcell)));
												map.put(ExcelUtils.getCellValueAsString(readcell),
														readcell.getColumnIndex());
												columns.add(ExcelUtils.getCellValueAsString(readcell));
											}

										}
										addDataTrialBalanceSheet(excelTrialBalanceSheetList, columns, r);
									}
								}
							}
							r++;
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
			Int073Vo trialBalanceSheet = new Int073Vo();

			try {
				trialBalanceSheet.setId(new Long(rowId));
				trialBalanceSheet.setAccountNumber(StringUtils.trim(data.get(0)));
				trialBalanceSheet.setAccountName(StringUtils.trim(data.get(1)));
				trialBalanceSheet.setSummitTest(new BigDecimal(StringUtils.trim(data.get(2))));
				trialBalanceSheet.setDebitTest(new BigDecimal(StringUtils.trim(data.get(3))));
				trialBalanceSheet.setCreditTest(new BigDecimal(StringUtils.trim(data.get(4))));
				trialBalanceSheet.setLiftUpTest(new BigDecimal(StringUtils.trim(data.get(5))));

				excelTrialBalanceSheetList.add(trialBalanceSheet);
			} catch (Exception e) {
				excelTrialBalanceSheetList.add(trialBalanceSheet);

			}

		}
	}

	public List<Int073Vo> readFileExcelLedgerSheet(Int073Vo formVo)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		List<Int073Vo> readFileExcelLedgerSheetList = new ArrayList<>();
		Int073Vo data = new Int073Vo();
		logger.info("readFileExcelLedgerSheet");
		System.out.println(formVo.getFileExel().getOriginalFilename());
		System.out.println(formVo.getFileExel().getContentType());

		byte[] byt = formVo.getFileExel().getBytes();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getLastRowNum();

		BigDecimal debit = BigDecimal.ZERO;
		BigDecimal credit = BigDecimal.ZERO;
		BigDecimal liftUp = BigDecimal.ZERO;

		/*
		 * BigDecimal debit2 = BigDecimal.ZERO; BigDecimal debit3 = new BigDecimal("0");
		 * 
		 * debit3 = debit3.subtract(subtrahend) debit3.add(subtrahend)
		 */

		/* read loop row main */
		for (int r = 0; r <= totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				short startCol = row.getFirstCellNum();
				/* set column */
				Cell cell = row.getCell(startCol);
				if (cell != null) {
					// EndDATA
					if (ACCOUNT_TEXT.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cell)))
							&& END_DATA.equals(
									StringUtils.trim(ExcelUtils.getCellValueAsString(row.getCell(startCol + 3))))
							|| END_DATA.equals(
									StringUtils.trim(ExcelUtils.getCellValueAsString(row.getCell(startCol + 6))))) {
						System.out.println("EndDATA  condition 1 Row :: " + r);
						break;
						// format 1
					} else if (END_SHEET.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cell)))) {
						System.out.println("EndDATA  condition 2 Row :: " + r);
						break;
					} else if (ACCOUNT_TEXT.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cell)))
							&& StringUtils.isNotBlank(ExcelUtils.getCellValueAsString(row.getCell(startCol + 7)))) {
						// System.out.println("format1 Row :: "+r);
						/* read loop sub */
						while (r <= totalRows) {
							Row rowFormat1 = sheet.getRow(r);
							if (rowFormat1 != null) {
								short startColFormat1 = rowFormat1.getFirstCellNum();
								Cell cellFormat1 = rowFormat1.getCell(startColFormat1);
								if (cellFormat1 != null) {
									if (END_BOX
											.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellFormat1)))) {
										// add data to list
										data = new Int073Vo();
										data.setId(new Long(r));
										data.setAccountNumber(StringUtils
												.trim(ExcelUtils.getCellValueAsString(row.getCell(startCol + 2))));
										data.setAccountName(StringUtils
												.trim(ExcelUtils.getCellValueAsString(row.getCell(startCol + 7))));

										liftUp = debit.add(credit);

										data.setDebitType(debit);
										data.setCreditType(credit);
										data.setLiftUpType(liftUp);

										readFileExcelLedgerSheetList.add(data);

										debit = BigDecimal.ZERO;
										credit = BigDecimal.ZERO;
										liftUp = BigDecimal.ZERO;

										break;
									} else {
										if (DEBIT_NO.equals(StringUtils.trim(
												ExcelUtils.getCellValueAsString(rowFormat1.getCell(startCol + 15))))) {
											debit = debit.add(new BigDecimal(StringUtils.trim(ExcelUtils
													.getCellValueAsString(rowFormat1.getCell(startCol + 16)))));
										} else if (CREDIT_NO.equals(StringUtils.trim(
												ExcelUtils.getCellValueAsString(rowFormat1.getCell(startCol + 15))))) {
											credit = credit.add(new BigDecimal(StringUtils.trim(ExcelUtils
													.getCellValueAsString(rowFormat1.getCell(startCol + 16)))));
										}
									}
								}
							}
							r++;
						}
						// format 2
					} else if (ACCOUNT_TEXT.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cell)))
							&& StringUtils.isNotBlank(ExcelUtils.getCellValueAsString(row.getCell(startCol + 5)))) {
						// System.out.println("format2 Row :: "+r);
						while (r <= totalRows) {
							Row rowFormat2 = sheet.getRow(r);
							if (rowFormat2 != null) {
								short startColFormat2 = rowFormat2.getFirstCellNum();
								Cell cellFormat2 = rowFormat2.getCell(startColFormat2);
								if (cellFormat2 != null) {
									if (END_BOX
											.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellFormat2)))) {

										// add data to list
										data = new Int073Vo();
										data.setId(new Long(r));
										data.setAccountNumber(StringUtils
												.trim(ExcelUtils.getCellValueAsString(row.getCell(startCol + 3))));
										data.setAccountName(StringUtils
												.trim(ExcelUtils.getCellValueAsString(row.getCell(startCol + 5))));

										liftUp = debit.add(credit);

										data.setDebitType(debit);
										data.setCreditType(credit);
										data.setLiftUpType(liftUp);

										readFileExcelLedgerSheetList.add(data);

										debit = BigDecimal.ZERO;
										credit = BigDecimal.ZERO;
										liftUp = BigDecimal.ZERO;
										break;
									} else {
										if (DEBIT_NO.equals(StringUtils.trim(
												ExcelUtils.getCellValueAsString(rowFormat2.getCell(startCol + 15))))) {
											debit = debit.add(new BigDecimal(StringUtils.trim(ExcelUtils
													.getCellValueAsString(rowFormat2.getCell(startCol + 16)))));
										} else if (CREDIT_NO.equals(StringUtils.trim(
												ExcelUtils.getCellValueAsString(rowFormat2.getCell(startCol + 15))))) {
											credit = credit.add(new BigDecimal(StringUtils.trim(ExcelUtils
													.getCellValueAsString(rowFormat2.getCell(startCol + 16)))));
										}
									}
								}
							}
							r++;
						}
						// format 3
					} else if (ACCOUNT_TEXT.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cell)))
							&& StringUtils.isNotBlank(ExcelUtils.getCellValueAsString(row.getCell(startCol + 6)))) {
						// System.out.println("format3 Row :: "+r);
						while (r <= totalRows) {
							Row rowFormat3 = sheet.getRow(r);
							if (rowFormat3 != null) {
								short startColFormat3 = rowFormat3.getFirstCellNum();
								Cell cellFormat3 = rowFormat3.getCell(startColFormat3);
								if (cellFormat3 != null) {
									if (END_BOX
											.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellFormat3)))) {
										// add data to list
										data = new Int073Vo();
										data.setId(new Long(r));
										data.setAccountNumber(StringUtils
												.trim(ExcelUtils.getCellValueAsString(row.getCell(startCol + 3))));
										data.setAccountName(StringUtils
												.trim(ExcelUtils.getCellValueAsString(row.getCell(startCol + 6))));

										liftUp = debit.add(credit);

										data.setDebitType(debit);
										data.setCreditType(credit);
										data.setLiftUpType(liftUp);

										readFileExcelLedgerSheetList.add(data);

										debit = BigDecimal.ZERO;
										credit = BigDecimal.ZERO;
										liftUp = BigDecimal.ZERO;
										break;
									} else {
										if (DEBIT_NO.equals(StringUtils.trim(
												ExcelUtils.getCellValueAsString(rowFormat3.getCell(startCol + 15))))) {
											debit = debit.add(new BigDecimal(StringUtils.trim(ExcelUtils
													.getCellValueAsString(rowFormat3.getCell(startCol + 16)))));
										} else if (CREDIT_NO.equals(StringUtils.trim(
												ExcelUtils.getCellValueAsString(rowFormat3.getCell(startCol + 15))))) {
											credit = credit.add(new BigDecimal(StringUtils.trim(ExcelUtils
													.getCellValueAsString(rowFormat3.getCell(startCol + 16)))));
										}
									}
								}
							}
							r++;
						}
					}

				}
			}
		}

		return readFileExcelLedgerSheetList;
	}

	public List<Int073Vo> checkData(Int073CheckDataVo checkData) {

		BigDecimal difference = BigDecimal.ZERO;
		/* Total */
		BigDecimal summitTestTotal = BigDecimal.ZERO;
		BigDecimal debitTestTotal = BigDecimal.ZERO;
		BigDecimal creditTestTotal = BigDecimal.ZERO;
		BigDecimal liftUpTestTotal = BigDecimal.ZERO;
		BigDecimal debitTypeTotal = BigDecimal.ZERO;
		BigDecimal creditTypeTotal = BigDecimal.ZERO;
		BigDecimal liftUpTypeTotal = BigDecimal.ZERO;
		BigDecimal differenceTotal = BigDecimal.ZERO;

		try {
			/* COMPARETO ACCOUNTNUMBER */
			for (Int073Vo data : checkData.getDataTrialBalanceSheet()) {
				/* loop type */
				for (Int073Vo type : checkData.getDataLedgerSheet()) {
					// check AccNo for list.
					if (data.getAccountNumber().equals(type.getAccountNumber())) {
						/* difference for abs */
						difference = data.getLiftUpTest().abs().subtract(type.getLiftUpType().abs());

						if (difference.compareTo(BigDecimal.ZERO) != 0) {
							data.setCheckData("Y");
						}

						data.setDebitType(type.getDebitType());
						data.setCreditType(type.getCreditType());
						data.setLiftUpType(type.getLiftUpType());
						data.setDifference(difference);

						difference = BigDecimal.ZERO;
						break;
					}

				}

			}

			/* SUM_DATA */
			for (Int073Vo total : checkData.getDataTrialBalanceSheet()) {
				// SummitTestTotal
				if (total.getSummitTest() != null) {
					summitTestTotal = summitTestTotal.add(total.getSummitTest());
				}
				// debitTestTotal
				if (total.getDebitTest() != null) {
					debitTestTotal = debitTestTotal.add(total.getDebitTest());
				}
				// creditTestTotal
				if (total.getCreditTest() != null) {
					creditTestTotal = creditTestTotal.add(total.getCreditTest());
				}
				// liftUpTestTotal
				if (total.getLiftUpTest() != null) {
					liftUpTestTotal = liftUpTestTotal.add(total.getLiftUpTest());
				}
				// debitTypeTotal
				if (total.getDebitType() != null) {
					debitTypeTotal = debitTypeTotal.add(total.getDebitType());
				}
				// creditTypeTotal
				if (total.getCreditType() != null) {
					creditTypeTotal = creditTypeTotal.add(total.getCreditType());
				}
				// liftUpTypeTotal
				if (total.getLiftUpType() != null) {
					liftUpTypeTotal = liftUpTypeTotal.add(total.getLiftUpType());
				}
				// differenceTotal
				if (total.getDifference() != null) {
					differenceTotal = differenceTotal.add(total.getDifference());
				}
			}
			/* DATA SET_TOTAL */
			Int073Vo dataSet = new Int073Vo();
			dataSet.setAccountName(TOTAL);
			dataSet.setSummitTest(summitTestTotal);
			dataSet.setDebitTest(debitTestTotal);
			dataSet.setCreditTest(creditTestTotal);
			dataSet.setLiftUpTest(liftUpTestTotal);
			dataSet.setDebitType(debitTypeTotal);
			dataSet.setCreditType(creditTypeTotal);
			dataSet.setLiftUpType(liftUpTypeTotal);
			dataSet.setDifference(differenceTotal);
			dataSet.setCheckData("T");

			checkData.getDataTrialBalanceSheet().add(dataSet);

			return checkData.getDataTrialBalanceSheet();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return checkData.getDataTrialBalanceSheet();
		}

	}

}

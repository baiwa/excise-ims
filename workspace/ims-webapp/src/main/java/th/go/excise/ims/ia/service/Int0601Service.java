
package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD2;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD3;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD2Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD3Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0601JdbcRepository;
import th.go.excise.ims.ia.vo.IaAuditIncD1Vo;
import th.go.excise.ims.ia.vo.IaAuditIncD2Vo;
import th.go.excise.ims.ia.vo.IaAuditIncD3DatatableDtlVo;
import th.go.excise.ims.ia.vo.IaAuditIncD3Vo;
import th.go.excise.ims.ia.vo.Int0601RequestVo;
import th.go.excise.ims.ia.vo.Int0601SaveVo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

@Service
public class Int0601Service {

	private static final Logger logger = LoggerFactory.getLogger(Int0601Service.class);

	@Autowired
	private Int0601JdbcRepository int0601JdbcRepository;

	@Autowired
	private IaAuditIncHRepository iaAuditIncHRepository;

	@Autowired
	private IaAuditIncD1Repository iaAuditIncD1Repository;

	@Autowired
	private IaAuditIncD2Repository iaAuditIncD2Repository;

	@Autowired
	private IaAuditIncD3Repository iaAuditIncD3Repository;

	DecimalFormat df1 = new DecimalFormat("###,###,###");
	DecimalFormat df2 = new DecimalFormat("###,###,###.00");

	public List<WsIncfri8020Inc> findTab1ByCriteria(Int0601RequestVo int0601Vo) {
		logger.info("findByCriterai");
		return int0601JdbcRepository.findByCriteria(int0601Vo, "RECEIPT_NO");
	}

	public IaAuditIncH createIaAuditInc(Int0601SaveVo int0601SaveVo) throws IllegalAccessException, InvocationTargetException {
		logger.info("insert IaAuditIncH");
		IaAuditIncH iaAuditIncH = int0601SaveVo.getIaAuditIncH();
		String auditIncNo = "";
		if (iaAuditIncH != null && iaAuditIncH.getAuditIncSeq() != null) {
			iaAuditIncH = iaAuditIncHRepository.findById(int0601SaveVo.getIaAuditIncH().getAuditIncSeq()).get();
			auditIncNo = iaAuditIncH.getAuditIncNo();
		} else {
			auditIncNo = iaAuditIncH.getOfficeCode() + "/" + iaAuditIncHRepository.generateAuditIncNo();
			iaAuditIncH.setAuditIncNo(auditIncNo);
			iaAuditIncH = iaAuditIncHRepository.save(iaAuditIncH);
		}
		if (iaAuditIncH.getAuditIncSeq() != null) {
			logger.info("insert IaAuditIncH Completed ");
			if (int0601SaveVo.getIaAuditIncD1List() != null && int0601SaveVo.getIaAuditIncD1List().size() > 0) {
				logger.info("insert Drtail : 1 ");
				List<IaAuditIncD1> entitySaveList = new ArrayList<>();
				List<IaAuditIncD1> entityUpdateList = new ArrayList<>();
				IaAuditIncD1 d1 = null;
				for (IaAuditIncD1Vo vo : int0601SaveVo.getIaAuditIncD1List()) {
					d1 = new IaAuditIncD1();
					d1.setIaAuditIncDId(vo.getIaAuditIncDId());
					d1.setAuditIncNo(vo.getAuditIncNo());
					d1.setOfficeCode(vo.getOfficeCode());
					d1.setDocCtlNo(vo.getDocCtlNo());
					d1.setReceiptNo(vo.getReceiptNo());
					d1.setRunCheck(vo.getRunCheck());
					d1.setReceiptDate(ConvertDateUtils.parseStringToDate(vo.getReceiptDate(), ConvertDateUtils.YYYY_MM_DD, ConvertDateUtils.LOCAL_TH));
					d1.setTaxName(vo.getTaxName());
					d1.setTaxCode(vo.getTaxCode());
					d1.setAmount(vo.getAmount());
					d1.setRemark(vo.getRemark());

					// Tab 4
					d1.setCheckTax0307(vo.getCheckTax0307());
					d1.setCheckStamp(vo.getCheckStamp());
					d1.setCheckTax0307(vo.getCheckTax0307());
					d1.setRemarkTax(vo.getRemarkTax());

					if (d1.getIaAuditIncDId() == null) {
						entitySaveList.add(d1);
					} else {
						entityUpdateList.add(d1);
					}
				}
				iaAuditIncD1Repository.batchInsert(entitySaveList, auditIncNo);
			}
			if (int0601SaveVo.getIaAuditIncD2List() != null && int0601SaveVo.getIaAuditIncD2List().size() > 0) {
				logger.info("insert Drtail : 2 ");
				for (IaAuditIncD2Vo tab2Data : int0601SaveVo.getIaAuditIncD2List()) {
					if (StringUtils.isBlank(tab2Data.getAuditIncNo())) {
						tab2Data.setAuditIncNo(iaAuditIncH.getAuditIncNo());
					}
				}
				iaAuditIncD2Repository.batchInsert(int0601SaveVo.getIaAuditIncD2List());
			}
			if (int0601SaveVo.getIaAuditIncD3List() != null && int0601SaveVo.getIaAuditIncD3List().size() > 0) {
				logger.info("insert Drtail : 3 ");
				for (IaAuditIncD3Vo tab3Data : int0601SaveVo.getIaAuditIncD3List()) {
					if (StringUtils.isBlank(tab3Data.getAuditIncNo())) {
						tab3Data.setAuditIncNo(iaAuditIncH.getAuditIncNo());
					}
				}
				iaAuditIncD3Repository.batchInsert(int0601SaveVo.getIaAuditIncD3List());
			}
		} else {
			logger.info("insert IaAuditIncH incomplet ");
		}

		return iaAuditIncH;
	}

	public List<IaAuditIncH> findAllIaAuditIncH() {
		return iaAuditIncHRepository.findByIsDeletedOrderByAuditIncNoAsc(FLAG.N_FLAG);
	}

	public IaAuditIncH findIaAuditIncHByAuditIncNo(String auditIncNo) {
		return iaAuditIncHRepository.findByAuditIncNo(auditIncNo);
	}

	public List<IaAuditIncD2Vo> findIaAuditIncD2ByCriteria(Int0601RequestVo criteria) {
		return int0601JdbcRepository.findDataTab2(criteria);
	}

	public List<IaAuditIncD1> findIaAuditIncD1ByAuditIncNo(String auditIncNo) {
		return iaAuditIncD1Repository.findByAuditIncNoOrderByReceiptNo(auditIncNo);
	}

	public List<IaAuditIncD2> findIaAuditIncD2ByAuditIncNo(String auditIncNo) {
		return iaAuditIncD2Repository.findByAuditIncNoOrderByReceiptDate(auditIncNo);
	}

	public List<IaAuditIncD3> findIaAuditIncD3ByAuditIncNo(String auditIncNo) {
		return iaAuditIncD3Repository.findByAuditIncNoOrderByTaxCode(auditIncNo);
	}

	public List<IaAuditIncD3> findIaAuditIncD3ByCriteria(Int0601RequestVo criteria) {
		return int0601JdbcRepository.findDataTab3(criteria);
	}

	public IaAuditIncD3DatatableDtlVo findTab3Dtl(Int0601RequestVo criteria) {
		IaAuditIncD3DatatableDtlVo iaAuditIncD3DatatableDtlVo = new IaAuditIncD3DatatableDtlVo();
		List<WsIncfri8020Inc> wsIncfri8020IncList = int0601JdbcRepository.findByCriteria(criteria, "INCOME_CODE,RECEIPT_DATE");
		BigDecimal sumAmt = BigDecimal.ZERO;
		for (WsIncfri8020Inc wsIncfri8020Inc : wsIncfri8020IncList) {
			sumAmt = sumAmt.add(wsIncfri8020Inc.getNetTaxAmt());
		}
		iaAuditIncD3DatatableDtlVo.setWsIncfri8020Inc(wsIncfri8020IncList);
		iaAuditIncD3DatatableDtlVo.setSumAmt(sumAmt);
		return iaAuditIncD3DatatableDtlVo;
	}

	public byte[] export(String auditIncNo) {
		// hdr
		IaAuditIncH iaAuditIncH = findIaAuditIncHByAuditIncNo(auditIncNo);

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("การใช้ใบเสร็จรับเงิน");
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
		CellStyle wrapText = ExcelUtils.createWrapTextStyle(workbook);

		/* tbTH */
		String[] tbTH = { "ลำดับ", "เลขที่ควบคุมเอกสาร", "เลขที่ใบเสร็จ", "ตรวจสอบเลขที่แบบพิมพ์", "วันเดือนปี", "รายการภาษี ", "รหัสภาษี", "จำนวนเงิน", "หมายเหตุผลการตรวจ" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(thStyle);

		}

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;

		List<IaAuditIncD1> dataList = findIaAuditIncD1ByAuditIncNo(auditIncNo);
		for (IaAuditIncD1 data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDocCtlNo());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getReceiptNo());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getRunCheck() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(data.getRunCheck().toString());
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getReceiptDate() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(DateFormatUtils.format(data.getReceiptDate(), "dd/MM/yyyy", new Locale("th", "TH")));
			}
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxName());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxCode());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getAmount() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(df2.format(data.getAmount()));
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRemark());
			cell.setCellStyle(cellLeft);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}

		int rowNumD = 0;
		int cellNumD = 0;
		rowNumD = rowNum + 1;
		Row rowD1 = sheet.createRow(rowNumD);
		Cell cellD1 = rowD1.createCell(cellNumD);
		sheet.addMergedRegion(new CellRangeAddress(rowNumD, rowNumD, 0, 1));

		if ("1".equals(StringUtils.defaultString(iaAuditIncH.getD1AuditFlag()))) {
			cellD1.setCellValue("ตรวจสอบกับทะเบียนควบคุมใบเสร็จรับเงิน :  ถูกต้อง");
		} else if ("2".equals(StringUtils.defaultString(iaAuditIncH.getD1AuditFlag()))) {
			cellD1.setCellValue("ตรวจสอบกับทะเบียนควบคุมใบเสร็จรับเงิน :  ไม่ถูกต้อง");
		} else {
			cellD1.setCellValue("ตรวจสอบกับทะเบียนควบคุมใบเสร็จรับเงิน :  -");
		}

		rowNumD += 2;
		rowD1 = sheet.createRow(rowNumD);
		cellD1 = rowD1.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNumD, rowNumD, 0, 1));
		cellD1.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");

		rowNumD += 1;
		rowD1 = sheet.createRow(rowNumD);
		cellD1 = rowD1.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNumD, rowNumD + 3, 0, 3));
		cellD1.setCellValue(StringUtils.defaultString(iaAuditIncH.getD1ConditionText()));
		cellD1.setCellStyle(wrapText);

		rowNumD += 5;
		rowD1 = sheet.createRow(rowNumD);
		cellD1 = rowD1.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNumD, rowNumD, 0, 1));
		cellD1.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");

		rowNumD += 1;
		rowD1 = sheet.createRow(rowNumD);
		cellD1 = rowD1.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNumD, rowNumD + 3, 0, 3));
		cellD1.setCellValue(StringUtils.defaultString(iaAuditIncH.getD1CriteriaText()));
		cellD1.setCellStyle(wrapText);

		// sheet 2
		Sheet sheet2 = workbook.createSheet("สรุปรายวัน");
		int rowNum2 = 0;
		int cellNum2 = 0;
		Row row2 = sheet2.createRow(rowNum2);
		Cell cell2 = row2.createCell(cellNum2);
		String[] tbTH2 = { "ลำดับ", "วันที่", "จำนวนเงิน", "จำนวนแบบพิมพ์/วัน", "ผลการตรวจกับงบหลังสำเนาใบเสร็จ", "หมายเหตุ" };

		for (int i = 0; i < tbTH2.length; i++) {
			cell2 = row2.createCell(i);
			cell2.setCellValue(tbTH2[i]);
			cell2.setCellStyle(thStyle);

		}
		int colIndex2 = 0;
		sheet2.setColumnWidth(colIndex2++, 10 * 256);
		sheet2.setColumnWidth(colIndex2++, 38 * 256);
		sheet2.setColumnWidth(colIndex2++, 30 * 256);
		sheet2.setColumnWidth(colIndex2++, 30 * 256);
		sheet2.setColumnWidth(colIndex2++, 30 * 256);
		sheet2.setColumnWidth(colIndex2++, 30 * 256);

		/* set data */
		rowNum2 = 1;
		cellNum2 = 0;
		int no2 = 1;

		List<IaAuditIncD2> dataList2 = findIaAuditIncD2ByAuditIncNo(auditIncNo);
		for (IaAuditIncD2 data : dataList2) {
			row2 = sheet2.createRow(rowNum2);

			cell2 = row2.createCell(cellNum2);
			cell2.setCellValue(no2);
			cell2.setCellStyle(cellCenter);
			cellNum2++;

			cell2 = row2.createCell(cellNum2);
			if (data.getReceiptDate() == null) {
				cell2.setCellValue("");
			} else {
				cell2.setCellValue(DateFormatUtils.format(data.getReceiptDate(), "dd/MM/yyyy", new Locale("th", "TH")));
			}
			cell2.setCellStyle(cellCenter);
			cellNum2++;

			cell2 = row2.createCell(cellNum2);
			if (data.getAmount() == null) {
				cell2.setCellValue("");
			} else {
				cell2.setCellValue(df2.format(data.getAmount()));
			}
			cell2.setCellStyle(cellRight);
			cellNum2++;

			cell2 = row2.createCell(cellNum2);
			if (data.getPrintPerDay() == null) {
				cell2.setCellValue("");
			} else {
				cell2.setCellValue(df1.format(data.getPrintPerDay()));
			}
			cell2.setCellStyle(cellRight);
			cellNum2++;

			cell2 = row2.createCell(cellNum2);
			if ("1".equals(data.getAuditCheck())) {
				cell2.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getAuditCheck())) {
				cell2.setCellValue("ไม่ถูกต้อง");
			} else if ("3".equals(data.getAuditCheck())) {
				cell2.setCellValue("ไม่ได้งบหลังในเสร็จ");
			} else {
				cell2.setCellValue("");
			}
			cell2.setCellStyle(cellCenter);
			cellNum2++;

			cell2 = row2.createCell(cellNum2);
			cell2.setCellValue(data.getRemark());
			cell2.setCellStyle(cellLeft);
			cellNum2++;

			no2++;
			rowNum2++;
			cellNum2 = 0;
		}

		int rowNumD2 = 0;
		int cellNumD2 = 0;
		rowNumD2 = rowNum2 + 1;
		Row rowD2 = sheet2.createRow(rowNumD2);
		Cell cellD2 = rowD2.createCell(cellNumD2);
		sheet2.addMergedRegion(new CellRangeAddress(rowNumD2, rowNumD2, 0, 1));
		cellD2.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");

		rowNumD2 += 1;
		rowD2 = sheet2.createRow(rowNumD2);
		cellD2 = rowD2.createCell(0);
		sheet2.addMergedRegion(new CellRangeAddress(rowNumD2, rowNumD2 + 3, 0, 3));
		cellD2.setCellValue(StringUtils.defaultString(iaAuditIncH.getD2ConditionText()));
		cellD2.setCellStyle(wrapText);

		rowNumD2 += 5;
		rowD2 = sheet2.createRow(rowNumD2);
		cellD2 = rowD2.createCell(0);
		sheet2.addMergedRegion(new CellRangeAddress(rowNumD2, rowNumD2, 0, 1));
		cellD2.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");

		rowNumD2 += 1;
		rowD2 = sheet2.createRow(rowNumD2);
		cellD2 = rowD2.createCell(0);
		sheet2.addMergedRegion(new CellRangeAddress(rowNumD2, rowNumD2 + 3, 0, 3));
		cellD2.setCellValue(StringUtils.defaultString(iaAuditIncH.getD2CriteriaText()));
		cellD2.setCellStyle(wrapText);

		// sheet 3

		Sheet sheet3 = workbook.createSheet("ตรวจสอบยอดเงินค่าภาษี");
		int rowNum3 = 0;
		int cellNum3 = 0;
		Row row3 = sheet3.createRow(rowNum3);
		Cell cell3 = row3.createCell(cellNum3);
		String[] tbTH3 = { "ลำดับ", "รหัสภาษี", "รายการภาษี", "จำนวนเงิน", "จำนวนราย", "ผลการตรวจกับสรุปเงินค่าภาษี", "หมายเหตุ" };
		for (int i = 0; i < tbTH3.length; i++) {
			cell3 = row3.createCell(i);
			cell3.setCellValue(tbTH3[i]);
			cell3.setCellStyle(thStyle);

		}
		int colIndex3 = 0;
		sheet3.setColumnWidth(colIndex3++, 10 * 256);
		sheet3.setColumnWidth(colIndex3++, 38 * 256);
		sheet3.setColumnWidth(colIndex3++, 30 * 256);
		sheet3.setColumnWidth(colIndex3++, 30 * 256);
		sheet3.setColumnWidth(colIndex3++, 30 * 256);
		sheet3.setColumnWidth(colIndex3++, 30 * 256);
		sheet3.setColumnWidth(colIndex3++, 30 * 256);
		/* set data */
		rowNum3 = 1;
		cellNum3 = 0;
		int no3 = 1;

		List<IaAuditIncD3> dataList3 = findIaAuditIncD3ByAuditIncNo(auditIncNo);
		for (IaAuditIncD3 data : dataList3) {
			row3 = sheet3.createRow(rowNum3);

			cell3 = row3.createCell(cellNum3);
			cell3.setCellValue(no3);
			cell3.setCellStyle(cellCenter);
			cellNum3++;

			cell3 = row3.createCell(cellNum3);
			cell3.setCellValue(data.getTaxCode());
			cell3.setCellStyle(cellCenter);
			cellNum3++;

			cell3 = row3.createCell(cellNum3);
			cell3.setCellValue(data.getTaxName());
			cell3.setCellStyle(cellLeft);
			cellNum3++;

			cell3 = row3.createCell(cellNum3);
			if (data.getAmount() == null) {
				cell3.setCellValue("");
			} else {
				cell3.setCellValue(df2.format(data.getAmount()));
			}
			cell3.setCellStyle(cellRight);
			cellNum3++;

			cell3 = row3.createCell(cellNum3);
			if (data.getCountReceipt() == null) {
				cell3.setCellValue("");
			} else {
				cell3.setCellValue(df1.format(data.getCountReceipt()));
			}
			cell3.setCellStyle(cellRight);
			cellNum3++;

			cell3 = row3.createCell(cellNum3);
			if ("1".equals(data.getAuditCheck())) {
				cell3.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getAuditCheck())) {
				cell3.setCellValue("ไม่ถูกต้อง");
			} else {
				cell3.setCellValue("");
			}
			cell3.setCellStyle(cellCenter);
			cellNum3++;

			cell3 = row3.createCell(cellNum3);
			cell3.setCellValue(data.getRemark());
			cell3.setCellStyle(cellLeft);
			cellNum3++;

			no3++;
			rowNum3++;
			cellNum3 = 0;
		}

		int rowNumD3 = 0;
		int cellNumD3 = 0;
		rowNumD3 = rowNum3 + 1;
		Row rowD3 = sheet3.createRow(rowNumD3);
		Cell cellD3 = rowD3.createCell(cellNumD3);
		sheet3.addMergedRegion(new CellRangeAddress(rowNumD3, rowNumD3, 0, 1));
		cellD3.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");

		rowNumD3 += 1;
		rowD3 = sheet3.createRow(rowNumD3);
		cellD3 = rowD3.createCell(0);
		sheet3.addMergedRegion(new CellRangeAddress(rowNumD3, rowNumD3 + 3, 0, 3));
		cellD3.setCellValue(StringUtils.defaultString(iaAuditIncH.getD3ConditionText()));
		cellD3.setCellStyle(wrapText);

		rowNumD3 += 5;
		rowD3 = sheet3.createRow(rowNumD3);
		cellD3 = rowD3.createCell(0);
		sheet3.addMergedRegion(new CellRangeAddress(rowNumD3, rowNumD3, 0, 1));
		cellD3.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");

		rowNumD3 += 1;
		rowD3 = sheet3.createRow(rowNumD3);
		cellD3 = rowD3.createCell(0);
		sheet3.addMergedRegion(new CellRangeAddress(rowNumD3, rowNumD3 + 3, 0, 3));
		cellD3.setCellValue(StringUtils.defaultString(iaAuditIncH.getD3CriteriaText()));
		cellD3.setCellStyle(wrapText);

		// sheet 4

		Sheet sheet4 = workbook.createSheet("ตรวจสอบกับแบบรายการภาษี");
		int rowNum4 = 0;
		int cellNum4 = 0;
		Row row4 = sheet4.createRow(rowNum4);
		Cell cell4 = row4.createCell(cellNum4);

		String[] tbTH4 = { "ลำดับ", "เลขที่ควบคุมเอกสาร", "เลขที่ใบเสร็จ", "วันเดือนปี", "รายการภาษี", "รหัสภาษี", "จำนวนเงิน", "กรอกเลขที่ใบเสร็จในแบบ (ภส. 03-07)", "มีแบบคำขอร้องแสตมป์ สุรา ยา เครื่องดื่ม", "งบเดือน", "หมายเหตุ / ผลการตรวจ" };
		for (int i = 0; i < tbTH4.length; i++) {
			cell4 = row4.createCell(i);
			cell4.setCellValue(tbTH4[i]);
			cell4.setCellStyle(thStyle);

		}
		int colIndex4 = 0;
		sheet4.setColumnWidth(colIndex4++, 10 * 256);
		sheet4.setColumnWidth(colIndex4++, 38 * 256);
		sheet4.setColumnWidth(colIndex4++, 30 * 256);
		sheet4.setColumnWidth(colIndex4++, 30 * 256);
		sheet4.setColumnWidth(colIndex4++, 30 * 256);
		sheet4.setColumnWidth(colIndex4++, 30 * 256);
		sheet4.setColumnWidth(colIndex4++, 30 * 256);
		sheet4.setColumnWidth(colIndex4++, 30 * 256);
		sheet4.setColumnWidth(colIndex4++, 30 * 256);
		sheet4.setColumnWidth(colIndex4++, 30 * 256);
		sheet4.setColumnWidth(colIndex4++, 30 * 256);
		/* set data */
		rowNum4 = 1;
		cellNum4 = 0;
		int no4 = 1;

		List<IaAuditIncD1> dataList4 = findIaAuditIncD1ByAuditIncNo(auditIncNo);
		for (IaAuditIncD1 data : dataList4) {
			row4 = sheet4.createRow(rowNum4);

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(no4);
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getDocCtlNo());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getDocCtlNo());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			if (data.getReceiptDate() == null) {
				cell4.setCellValue("");
			} else {
				cell4.setCellValue(DateFormatUtils.format(data.getReceiptDate(), "dd/MM/yyyy", new Locale("th", "TH")));
			}
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getTaxName());
			cell4.setCellStyle(cellLeft);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getTaxCode());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			if (data.getAmount() == null) {
				cell4.setCellValue("");
			} else {
				cell4.setCellValue(df2.format(data.getAmount()));
			}
			cell4.setCellStyle(cellRight);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			if ("1".equals(data.getCheckTax0307())) {
				cell4.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getCheckTax0307())) {
				cell4.setCellValue("พบประเด็น");
			} else {
				cell4.setCellValue("");
			}
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			if ("1".equals(data.getCheckStamp())) {
				cell4.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getCheckStamp())) {
				cell4.setCellValue("พบประเด็น");
			} else {
				cell4.setCellValue("");
			}
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			if ("1".equals(data.getCheckTax0704())) {
				cell4.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getCheckTax0704())) {
				cell4.setCellValue("พบประเด็น");
			} else {
				cell4.setCellValue("");
			}
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getRemarkTax());
			cell4.setCellStyle(cellLeft);

			cellNum4++;
			no4++;
			rowNum4++;
			cellNum4 = 0;
		}

		int rowNumD4 = 0;
		int cellNumD4 = 0;
		rowNumD4 = rowNum4 + 1;
		Row rowD4 = sheet4.createRow(rowNumD4);
		Cell cellD4 = rowD4.createCell(cellNumD4);
		sheet4.addMergedRegion(new CellRangeAddress(rowNumD4, rowNumD4, 0, 1));
		cellD4.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");

		rowNumD4 += 1;
		rowD4 = sheet4.createRow(rowNumD4);
		cellD4 = rowD4.createCell(0);
		sheet4.addMergedRegion(new CellRangeAddress(rowNumD4, rowNumD4 + 3, 0, 3));
		cellD4.setCellValue(StringUtils.defaultString(iaAuditIncH.getD4ConditionText()));
		cellD4.setCellStyle(wrapText);

		rowNumD4 += 5;
		rowD4 = sheet4.createRow(rowNumD4);
		cellD4 = rowD4.createCell(0);
		sheet4.addMergedRegion(new CellRangeAddress(rowNumD4, rowNumD4, 0, 1));
		cellD4.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");

		rowNumD4 += 1;
		rowD4 = sheet4.createRow(rowNumD4);
		cellD4 = rowD4.createCell(0);
		sheet4.addMergedRegion(new CellRangeAddress(rowNumD4, rowNumD4 + 3, 0, 3));
		cellD4.setCellValue(StringUtils.defaultString(iaAuditIncH.getD4CriteriaText()));
		cellD4.setCellStyle(wrapText);

		// set output
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return content;
	}

}

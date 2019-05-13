
package th.go.excise.ims.ia.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
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
					if(StringUtils.isBlank(tab2Data.getAuditIncNo())) {
						tab2Data.setAuditIncNo(iaAuditIncH.getAuditIncNo());
					}
				}
				iaAuditIncD2Repository.batchInsert(int0601SaveVo.getIaAuditIncD2List());
			}
			if (int0601SaveVo.getIaAuditIncD3List() != null && int0601SaveVo.getIaAuditIncD3List().size() > 0) {
				logger.info("insert Drtail : 3 ");
				for (IaAuditIncD3Vo tab3Data : int0601SaveVo.getIaAuditIncD3List()) {
					if(StringUtils.isBlank(tab3Data.getAuditIncNo())) {
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
		List<WsIncfri8020Inc> wsIncfri8020IncList = int0601JdbcRepository.findByCriteria(criteria , "INCOME_CODE,RECEIPT_DATE");
		BigDecimal sumAmt = BigDecimal.ZERO;
		for (WsIncfri8020Inc wsIncfri8020Inc : wsIncfri8020IncList) {
			sumAmt = sumAmt.add(wsIncfri8020Inc.getNetTaxAmt());
		}
		iaAuditIncD3DatatableDtlVo.setWsIncfri8020Inc(wsIncfri8020IncList);
		iaAuditIncD3DatatableDtlVo.setSumAmt(sumAmt);
		return iaAuditIncD3DatatableDtlVo;
	}

	
	

	public List<IaAuditIncD1Vo> getData1() {
		logger.info("getDataProductPaperInputGoods");
		String desc = "ตรวจสอบการรับสินค้าสำเร็จรูป";
		List<IaAuditIncD1Vo> datalist = new ArrayList<IaAuditIncD1Vo>();
		IaAuditIncD1Vo data = null;
		for (int i = 0; i < 10; i++) {

			data = new IaAuditIncD1Vo();
			data.setAuditIncNo("1");
			data.setOfficeCode("23651");
			data.setDocCtlNo("C01020261/0000001");
			data.setReceiptNo("1");
			data.setRunCheck(new BigDecimal(123));
			data.setReceiptDate("02/06/2563");
			data.setTaxName("	ภาษีรถจักรยานยนต์");
			data.setTaxCode("201010");
			data.setAmount(new BigDecimal(56));
			data.setRemark("");
			data.setCheckTax0307("CheckTax0307");
			data.setCheckStamp("CheckStamp");
			data.setCheckTax0704("CheckTax0704");
			data.setRemarkTax("RemarkTax");
			datalist.add(data);
		}
		return datalist;
	}

	public List<IaAuditIncD2Vo> getData2() {
		logger.info("getDataProductPaperInputGoods");
		;
		List<IaAuditIncD2Vo> datalist = new ArrayList<IaAuditIncD2Vo>();
		IaAuditIncD2Vo data = null;
		for (int i = 0; i < 10; i++) {

			data = new IaAuditIncD2Vo();
			data.setIaAuditIncD2Id("1");
			data.setReceiptDate("02/01/2563");
			data.setAmount(new BigDecimal(563));
			data.setPrintPerDay(new BigDecimal(90));
			data.setAuditCheck("ผ่านการตรวจ");
			data.setRemark("ผ่านการตรวจ");
			datalist.add(data);
		}
		return datalist;
	}

	public List<IaAuditIncD3Vo> getData3() {
		logger.info("getDataProductPaperInputGoods");
		;
		List<IaAuditIncD3Vo> datalist = new ArrayList<IaAuditIncD3Vo>();
		IaAuditIncD3Vo data = null;
		for (int i = 0; i < 10; i++) {
			data = new IaAuditIncD3Vo();
			data.setTaxCode("23252");
			data.setTaxName("262254");
			data.setAmount(new BigDecimal(002));
			data.setCountReceipt(new BigDecimal(2356));
			data.setAuditCheck("testaudit");
			data.setRemark("TEST23");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] export() {

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("test");
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

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
		List<IaAuditIncD1Vo> dataList = getData1();
		for (IaAuditIncD1Vo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getOfficeCode());
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
			cell.setCellValue(data.getReceiptDate());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxName());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxCode());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAmount().toString());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRemark());
			cell.setCellStyle(cellCenter);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}
		// sheet 2

		Sheet sheet2 = workbook.createSheet("test2");
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

		List<IaAuditIncD2Vo> dataList2 = getData2();
		for (IaAuditIncD2Vo data : dataList2) {
			row2 = sheet2.createRow(rowNum2);

			cell2 = row2.createCell(cellNum2);
			cell2.setCellValue(no2);
			cell2.setCellStyle(cellCenter);
			cellNum2++;

			cell2 = row2.createCell(cellNum2);
			cell2.setCellValue(data.getReceiptDate());
			cell2.setCellStyle(cellCenter);
			cellNum2++;

			cell2 = row2.createCell(cellNum2);
			cell2.setCellValue(data.getAmount().toString());
			cell2.setCellStyle(cellCenter);
			cellNum2++;

			cell2 = row2.createCell(cellNum2);
			cell2.setCellValue(data.getPrintPerDay().toString());
			cell2.setCellStyle(cellCenter);
			cellNum2++;

			cell2 = row2.createCell(cellNum2);
			cell2.setCellValue(data.getAuditCheck());
			cell2.setCellStyle(cellCenter);
			cellNum2++;

			cell2 = row2.createCell(cellNum2);
			cell2.setCellValue(data.getRemark());
			cell2.setCellStyle(cellCenter);
			cellNum2++;

			no2++;
			rowNum2++;
			cellNum2 = 0;
		}

		// sheet 3

		Sheet sheet3 = workbook.createSheet("test3");
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

		List<IaAuditIncD3Vo> dataList3 = getData3();
		for (IaAuditIncD3Vo data : dataList3) {
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
			cell3.setCellStyle(cellCenter);
			cellNum3++;

			cell3 = row3.createCell(cellNum3);
			cell3.setCellValue(data.getAmount().toString());
			cell3.setCellStyle(cellCenter);
			cellNum3++;

			cell3 = row3.createCell(cellNum3);
			cell3.setCellValue(data.getAmount().toString());
			cell3.setCellStyle(cellCenter);
			cellNum3++;

			cell3 = row3.createCell(cellNum3);
			cell3.setCellValue(data.getAmount().toString());
			cell3.setCellStyle(cellCenter);
			cellNum3++;

			cell3 = row3.createCell(cellNum3);
			cell3.setCellValue(data.getRemark());
			cell3.setCellStyle(cellCenter);
			cellNum3++;

			no3++;
			rowNum3++;
			cellNum3 = 0;
		}
		// sheet 4

		Sheet sheet4 = workbook.createSheet("test4");
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

		List<IaAuditIncD1Vo> dataList4 = getData1();
		for (IaAuditIncD1Vo data : dataList4) {
			row4 = sheet4.createRow(rowNum4);

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(no4);
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getOfficeCode());
			cell4.setCellStyle(cellCenter);
			cellNum4++;


			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getDocCtlNo());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getReceiptDate());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getRunCheck().toString());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getTaxCode());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getAmount().toString());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getCheckTax0307());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getCheckStamp());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getCheckTax0704());
			cell4.setCellStyle(cellCenter);
			cellNum4++;

			cell4 = row4.createCell(cellNum4);
			cell4.setCellValue(data.getRemarkTax());
			cell4.setCellStyle(cellCenter);
			cellNum4++;
			no4++;
			rowNum4++;
			cellNum4 = 0;
		}
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

	public List<IaAuditIncD1Vo> setdata(IaAuditIncD1Vo request) {
		logger.info("readFileProductPaperInformPrice");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());

		List<IaAuditIncD1Vo> dataList = new ArrayList<>();
		IaAuditIncD1Vo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new IaAuditIncD1Vo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						// GoodsDesc
						data.setAuditIncNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						// InformPrice
						data.setOfficeCode(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// ExternalPrice
						data.setDocCtlNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// DeclarePrice
						data.setReceiptNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// RetailPrice
						data.setReceiptDate(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// TaxPrice
						data.setTaxName(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// DiffPrice
						data.setTaxCode(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 8) {
						// DiffPrice
						data.setRemark(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 9) {
						// DiffPrice
						data.setRemark(ExcelUtils.getCellValueAsString(cell));
					}

				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataList;
	}

	public List<IaAuditIncD2Vo> setdata(IaAuditIncD2Vo request) {
		logger.info("readFileProductPaperInformPrice");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());

		List<IaAuditIncD2Vo> dataList = new ArrayList<>();
		IaAuditIncD2Vo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(1);

			for (Row row : sheet) {
				data = new IaAuditIncD2Vo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						// GoodsDesc
						data.setIaAuditIncD2Id(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						// InformPrice
						data.setReceiptDate(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// ExternalPrice
						data.setReceiptDate(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// DeclarePrice
						data.setReceiptDate(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// RetailPrice
						data.setReceiptDate(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// RetailPrice
						data.setReceiptDate(ExcelUtils.getCellValueAsString(cell));
					}

				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataList;
	}

	public List<IaAuditIncD3Vo> setdata(IaAuditIncD3Vo request) {
		logger.info("readFileProductPaperInformPrice");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());

		List<IaAuditIncD3Vo> dataList = new ArrayList<>();
		IaAuditIncD3Vo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(1);

			for (Row row : sheet) {
				data = new IaAuditIncD3Vo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						// GoodsDesc
						data.setTaxCode(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						// InformPrice
						data.setTaxName(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// ExternalPrice
						data.setTaxName(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// DeclarePrice
						data.setTaxName(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// RetailPrice
						data.setAuditCheck(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// RetailPrice
						data.setRemark(ExcelUtils.getCellValueAsString(cell));
					}

				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataList;
	}
}

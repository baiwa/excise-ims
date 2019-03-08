package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CppCheckPriceVo;
import th.go.excise.ims.ta.vo.CppFinishedGoodsPaymentVo;
import th.go.excise.ims.ta.vo.CppFinishedGoodsReceiveVo;
import th.go.excise.ims.ta.vo.CppPayForeignFinishedGoodsVo;
import th.go.excise.ims.ta.vo.CppRawMaterialBalanceVo;
import th.go.excise.ims.ta.vo.CppRawMaterialFinishedGoodsRelationshipVo;
import th.go.excise.ims.ta.vo.CppRawMaterialPaymentVo;
import th.go.excise.ims.ta.vo.CppRawMaterialReceiveVo;
import th.go.excise.ims.ta.vo.CppRawMaterialTaxBreakVo;
import th.go.excise.ims.ta.vo.CppTaxVo;
import th.go.excise.ims.ta.vo.CppUnitPriceVo;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;

@Service
public class CreatePaperProductService {

	private static final Logger logger = LoggerFactory.getLogger(CreatePaperProductService.class);

	private static final Integer TOTAL = 17;
	/*-----TOPIC------*/
	private static final String RAW_MATERIAL_RECEIVE = "ตรวจสอบการรับวัตถุดิบ";
	private static final String RAW_MATERIAL_PAYMENT = "ตรวจสอบการจ่ายวัตถุดิบ";
	private static final String RAW_MATERIAL_BALANCE = "ตรวจนับวัตถุดิบคงเหลือ";
	private static final String RAW_MATERIAL_FINISHED_GOODS_RELATIONSHIP = "ความสัมพันธ์การเบิกใช้วัตถุดิบ";
	private static final String FINISHED_GOODS_RECEIVE = "รับสินค้าสำเร็จรูป";
	private static final String FINISHED_GOODS_PAYMENT = "ตรวจสอบการจ่ายสินค้าสำเร็จรูป";
	private static final String RAW_MATERIAL_TAX_BREAK = "วัตถุดิบที่ขอลดหย่อนภาษี";
	private static final String UNIT_PRICE = "ราคาต่อหน่วยสินค้า";
	private static final String CHECK_PRICE = "ตรวจสอบด้านราคา";
	private static final String PAY_FOREIGN_FINISHED_GOODS = "จ่ายสินค้าสำเร็จรูปต่างประเทศ";
	private static final String TAX = "คำนวณภาษีที่ต้องชำระเพิ่ม";

	// TODO MaterialReceive
	public DataTableAjax<CppRawMaterialReceiveVo> listRawMaterialReceive(CreatePaperFormVo request) {
		DataTableAjax<CppRawMaterialReceiveVo> dataTableAjax = new DataTableAjax<CppRawMaterialReceiveVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataRawMaterialReceive(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppRawMaterialReceiveVo> getDataRawMaterialReceive(int start, int length, int total) {
		logger.info("getDataRawMaterialReceive");
		String desc = "ตรวจสอบรับวัตถุดิบ";
		List<CppRawMaterialReceiveVo> datalist = new ArrayList<CppRawMaterialReceiveVo>();
		CppRawMaterialReceiveVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppRawMaterialReceiveVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
			data.setInvoices("13-05555-037" + (i + 1));
			data.setDailyAccount("130555503" + (i + 1));
			data.setMonthStatement("1,500.00");
			data.setExternalData("500.00");
			data.setMaxDiff("1,000.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportRawMaterialReceive() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(RAW_MATERIAL_RECEIVE);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle thCpColor = ExcelUtils.getThCpColor();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ใบกำกับภาษีซื้อ", "บัญชีประจำวัน ภส. ๐๗-๐๑", "งบเดือน (ภส. ๐๗-๐๔)",
				"ข้อมูลจากภายนอก", "ผลต่างสูงสุด" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i > 1 && i < 5) {
				cell.setCellStyle(thCpColor);
			} else {
				cell.setCellStyle(thStyle);
			}
		}

		/* set sheet */
		for (int i = 0; i <= 6; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 76);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 100);
			}
		}

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<CppRawMaterialReceiveVo> dataList = getDataRawMaterialReceive(0, TOTAL, TOTAL);
		for (CppRawMaterialReceiveVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getInvoices());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDailyAccount());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMonthStatement());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getExternalData());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaxDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			// set ++ and clear
			no++;
			rowNum++;
			cellNum = 0;
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

	// TODO MaterialPayment
	public DataTableAjax<CppRawMaterialPaymentVo> listRawMaterialPayment(CreatePaperFormVo request) {
		DataTableAjax<CppRawMaterialPaymentVo> dataTableAjax = new DataTableAjax<CppRawMaterialPaymentVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataRawMaterialPayment(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppRawMaterialPaymentVo> getDataRawMaterialPayment(int start, int length, int total) {
		logger.info("getDataRawMaterialPayment");
		String desc = "ตรวจสอบจ่ายวัตถุดิบ";
		List<CppRawMaterialPaymentVo> datalist = new ArrayList<CppRawMaterialPaymentVo>();
		CppRawMaterialPaymentVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppRawMaterialPaymentVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
			data.setRequisition("13-05555-037" + (i + 1));
			data.setDailyAccount("130555503" + (i + 1));
			data.setMonthStatement("1,500.00");
			data.setExternalData("500.00");
			data.setMaxDiff("1,000.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportRawMaterialPayment() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(RAW_MATERIAL_PAYMENT);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle thCpColor = ExcelUtils.getThCpColor();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ใบเบิกวัตถุดิบ	", "บัญชีประจำวัน ภส. ๐๗-๐๑", "งบเดือน (ภส. ๐๗-๐๔)",
				"ข้อมูลจากภายนอก" + "\n" + "(Monthly Report CKD Import CBU car)", "ผลต่างสูงสุด" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i > 1 && i < 5) {
				cell.setCellStyle(thCpColor);
			} else {
				cell.setCellStyle(thStyle);
			}
		}

		/* width */
		for (int i = 0; i < 7; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 100);
			}
		}

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<CppRawMaterialPaymentVo> dataList = getDataRawMaterialPayment(0, TOTAL, TOTAL);
		for (CppRawMaterialPaymentVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRequisition());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDailyAccount());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMonthStatement());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getExternalData());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaxDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
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

	// TODO MaterialBalance
	public DataTableAjax<CppRawMaterialBalanceVo> listRawMaterialBalance(CreatePaperFormVo request) {
		DataTableAjax<CppRawMaterialBalanceVo> dataTableAjax = new DataTableAjax<CppRawMaterialBalanceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataRawMaterialBalance(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppRawMaterialBalanceVo> getDataRawMaterialBalance(int start, int length, int total) {
		logger.info("getDataRawMaterialBalance");
		String desc = "ตรวจนับวัตถุดิบคงเหลือ";
		List<CppRawMaterialBalanceVo> datalist = new ArrayList<CppRawMaterialBalanceVo>();
		CppRawMaterialBalanceVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppRawMaterialBalanceVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
			data.setBalance("1,000.00");
			data.setBalanceCount("700.00");
			data.setMaxDiff("300.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportRawMaterialBalance() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(RAW_MATERIAL_BALANCE);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle thCpColor = ExcelUtils.getThCpColor();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ยอดคงเหลือ (ตามแบบ ภส. ๐๗-๐๑)", "ยอดคงเหลือจากการตรวจนับ	",
				"ผมต่างของข้อมูล" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH[i]);
			if (i == 2) {
				cell.setCellStyle(thCpColor);
			} else {
				cell.setCellStyle(thStyle);
			}

			cellNum++;
		}

		/* width */
		for (int i = 0; i < 5; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 100);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<CppRawMaterialBalanceVo> dataList = getDataRawMaterialBalance(0, TOTAL, TOTAL);
		for (CppRawMaterialBalanceVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBalance());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBalanceCount());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaxDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
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

	// TODO RawMaterialFinishedGoodsRelationship
	public DataTableAjax<CppRawMaterialFinishedGoodsRelationshipVo> listRawMaterialFinishedGoodsRelationship(
			CreatePaperFormVo request) {
		DataTableAjax<CppRawMaterialFinishedGoodsRelationshipVo> dataTableAjax = new DataTableAjax<CppRawMaterialFinishedGoodsRelationshipVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax
				.setData(getDataRawMaterialFinishedGoodsRelationship(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppRawMaterialFinishedGoodsRelationshipVo> getDataRawMaterialFinishedGoodsRelationship(int start,
			int length, int total) {
		logger.info("getDataRawMaterialFinishedGoodsRelationship");
		String desc = "การเบิกใช้วัตถุดิบกับการรับสินค้าสำเร็จรูป";
		List<CppRawMaterialFinishedGoodsRelationshipVo> datalist = new ArrayList<CppRawMaterialFinishedGoodsRelationshipVo>();
		CppRawMaterialFinishedGoodsRelationshipVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppRawMaterialFinishedGoodsRelationshipVo();
			data.setId(Long.valueOf(1));
			data.setCertificateNum("1001" + (i + 1));
			data.setList(desc + (i + 1));
			data.setAmountReceive("1,000.00");
			data.setFormulaProduction("E25+E15+E15");
			data.setFormulaWithdraw("700.00");
			data.setRealUseWithdraw("500.00");
			data.setRawMaterialDiff("200.00");
			data.setSplitRawMaterial("400.00");
			data.setAmountFinishedGoods("300.00");
			data.setFinishedGoodsDiff("100.00");
			data.setPercent("5%");
			data.setAmount("100.00");
			data.setBalance("600.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportRawMaterialFinishedGoodsRelationship() {

		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(RAW_MATERIAL_FINISHED_GOODS_RELATIONSHIP);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle thCpColor = ExcelUtils.getThCpColor();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "เลขที่ใบสำคัญ", "รายการ", "จำนวนรับ" + "\n" + "(ตามบัญชี ภส. ๐๗-๐๒)",
				"สูตรจากการผลิต", "เบิกตามสูตร", "เบิกใช้จริง", "ผลต่างวัตถุดิบ", "ผลิตได้ตามสูตร", "",
				"ผลต่างสินค้าสำเร็จรูป", "หักสูญเสีย", "", "คงเหลือ" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i != 3 && i != 4 && i != 5) {
				cell.setCellStyle(thStyle);
			} else {
				cell.setCellStyle(thCpColor);
			}

		}

		/* tbTH2 */
		String[] tbTH2 = { "", "", "", "", "", "", "", "", "แยกตามวัตถุดิบ", "จำนวนสินค้าสำเร็จรูป", "", "เปอร์เซ็นต์",
				"จำนวน" };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			if (i > 7 && i != 10) {
				cell = row.createCell(i);
				cell.setCellValue(tbTH2[i]);
				cell.setCellStyle(thStyle);
			}
		}

		/* width */
		for (int i = 0; i < 14; i++) {
			if (i > 2) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 60);
			} else if (i == 2) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 9));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 11, 12));

		for (int i = 0; i < 14; i++) {
			if (i != 8 && i != 9 && i != 11 && i != 12) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		List<CppRawMaterialFinishedGoodsRelationshipVo> dataList = getDataRawMaterialFinishedGoodsRelationship(0, TOTAL,
				TOTAL);
		for (CppRawMaterialFinishedGoodsRelationshipVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getCertificateNum());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAmountReceive());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getFormulaProduction());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getFormulaWithdraw());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRealUseWithdraw());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRawMaterialDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getSplitRawMaterial());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAmountFinishedGoods());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getFinishedGoodsDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPercent());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAmount());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBalance());
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
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

	// TODO FinishedGoodsReceive
	public DataTableAjax<CppFinishedGoodsReceiveVo> listFinishedGoodsReceive(CreatePaperFormVo request) {
		DataTableAjax<CppFinishedGoodsReceiveVo> dataTableAjax = new DataTableAjax<CppFinishedGoodsReceiveVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataFinishedGoodsReceive(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppFinishedGoodsReceiveVo> getDataFinishedGoodsReceive(int start, int length, int total) {
		logger.info("getDataFinishedGoodsReceive");
		String desc = "ตรวจสอบการรับสินค้าสำเร็จรูป";
		List<CppFinishedGoodsReceiveVo> datalist = new ArrayList<CppFinishedGoodsReceiveVo>();
		CppFinishedGoodsReceiveVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppFinishedGoodsReceiveVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
			data.setFinishedGoodsReceive("02-00-0" + (i + 1));
			data.setMonthStatement("1,000.00");
			data.setPs("500.00");
			data.setMaxDiff("500.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportFinishedGoodsReceive() {

		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(FINISHED_GOODS_RECEIVE);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle thCpColor = ExcelUtils.getThCpColor();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "รายการ", "ใบรับสินค้าสำเร็จรูป", "ปริมาณรับจากการผลิต", "", "ผลต่างสูงสุด" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i != 3 && i != 4) {
				cell.setCellStyle(thStyle);
			} else {
				cell.setCellStyle(thCpColor);
			}

		}

		/* tbTH2 */
		String[] tbTH2 = { "", "", "", "งบเดือน (ภส. ๐๗-๐๔)", "(ภส. ๐๗-๐๒)" };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			if (i > 2) {
				cell = row.createCell(i);
				cell.setCellValue(tbTH2[i]);
				cell.setCellStyle(thCpColor);
			}
		}

		/* width */
		for (int i = 0; i < 6; i++) {
			if (i > 2) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			} else if (i == 2) {
				sheet.setColumnWidth(i, 76 * 80);
			}
		}

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));

		for (int i = 0; i < 6; i++) {
			if (i != 3 && i != 4) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		List<CppFinishedGoodsReceiveVo> dataList = getDataFinishedGoodsReceive(0, TOTAL, TOTAL);
		for (CppFinishedGoodsReceiveVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getFinishedGoodsReceive());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMonthStatement());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPs());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaxDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
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

	// TODO FinishedGoodsPayment
	public DataTableAjax<CppFinishedGoodsPaymentVo> listFinishedGoodsPayment(CreatePaperFormVo request) {
		DataTableAjax<CppFinishedGoodsPaymentVo> dataTableAjax = new DataTableAjax<CppFinishedGoodsPaymentVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataFinishedGoodsPayment(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppFinishedGoodsPaymentVo> getDataFinishedGoodsPayment(int start, int length, int total) {
		logger.info("getDataFinishedGoodsPayment");
		String desc = "ตรวจสอบการจ่ายสินค้าสำเร็จรูป";
		List<CppFinishedGoodsPaymentVo> datalist = new ArrayList<CppFinishedGoodsPaymentVo>();
		CppFinishedGoodsPaymentVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppFinishedGoodsPaymentVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
			data.setqFinishedGoodsTaxInvoice("1,000.00");
			data.setqFinishedGoodsPayment1("1,000.00");
			data.setqFinishedgoodsMonthStatement("1,000.00");
			data.setqCheck("900.00");
			data.setqFinishedGoodsPayment2("800.00");
			data.setDiff("100.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportFinishedGoodsPayment() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(FINISHED_GOODS_PAYMENT);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle thCpColor = ExcelUtils.getThCpColor();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ปริมาณจ่ายสินค้าสำเร็จรูป" + "\n" + "ในใบกำกับภาษีขาย",
				"ปริมาณจ่ายสินค้าสำเร็จรูป" + "\n" + " (ภส. ๐๗-๐๒)",
				"ปริมาณจ่ายสินค้าสำเร็จรูป " + "\n" + "จากงบเดือน(ภส. ๐๗-๐๔)",
				"ปริมาณที่ได้จากการตรวจสอบ" + "\n" + "(1)", "ปริมาณจ่ายสินค้าสำเร็จรูป " + "\n" + "(ภส. ๐๓-๐๗ (02))",
				"ผลต่าง" + "\n" + " (1 - 2)" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i != 2 && i != 3) {
				cell.setCellStyle(thStyle);
			} else {
				cell.setCellStyle(thCpColor);
			}

		}

		/* width */
		for (int i = 0; i < 8; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 80);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<CppFinishedGoodsPaymentVo> dataList = getDataFinishedGoodsPayment(0, TOTAL, TOTAL);
		for (CppFinishedGoodsPaymentVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getqFinishedGoodsTaxInvoice());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getqFinishedGoodsPayment1());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getqFinishedgoodsMonthStatement());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getqCheck());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getqFinishedGoodsPayment2());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
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

//TODO RawMaterialTaxBreak
	public DataTableAjax<CppRawMaterialTaxBreakVo> listRawMaterialTaxBreak(CreatePaperFormVo request) {
		DataTableAjax<CppRawMaterialTaxBreakVo> dataTableAjax = new DataTableAjax<CppRawMaterialTaxBreakVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataRawMaterialTaxBreak(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppRawMaterialTaxBreakVo> getDataRawMaterialTaxBreak(int start, int length, int total) {
		logger.info("getDataRawMaterialTaxBreak");
		String desc = "วัตถุดิบที่ขอลดหย่อนภาษี";
		List<CppRawMaterialTaxBreakVo> datalist = new ArrayList<CppRawMaterialTaxBreakVo>();
		CppRawMaterialTaxBreakVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppRawMaterialTaxBreakVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
			data.setAmountTax("1,000.00");
			data.setQuantityUse("100.00");
			data.setTaxPerUnit1("15.00");
			data.setReceiptNum("100-23-" + (i + 1));
			data.setTotalTax("500.00");
			data.setQuantity("100.00");
			data.setTaxPerUnit2("200.00");
			data.setDiff("400.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportRawMaterialTaxBreak() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(RAW_MATERIAL_TAX_BREAK);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle thCpColor = ExcelUtils.getThCpColor();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "รายการวัตถุดิบ", "ขอลดหย่อนตามแบบ ภส. ๐๕-๐๓", "", "", "ใบเสร็จรับเงิน", "", "", "",
				"ผลต่าง" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i > 4 && i < 9) {
				cell.setCellStyle(thCpColor);
			} else {
				cell.setCellStyle(thStyle);
			}

		}

		/* tbTH2 */
		String[] tbTH2 = { "", "", "จำนวนภาษี", "ปริมาณที่ใช้", "ภาษีต่อหน่วย", "เลขที่ใบเสร็จ", "ภาษีรวม", "ปริมาณ",
				"ภาษีต่อหน่วย" };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			if (i > 1) {
				cell = row.createCell(i);
				cell.setCellValue(tbTH2[i]);
				if (i > 4 && i < 9) {
					cell.setCellStyle(thCpColor);
				} else {
					cell.setCellStyle(thStyle);
				}
			}
		}

		/* width */
		for (int i = 0; i < 10; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 8));

		for (int i = 0; i < 10; i++) {
			if (i < 2 || i > 8) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		List<CppRawMaterialTaxBreakVo> dataList = getDataRawMaterialTaxBreak(0, TOTAL, TOTAL);
		for (CppRawMaterialTaxBreakVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAmountTax());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getQuantityUse());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxPerUnit1());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getReceiptNum());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTotalTax());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getQuantity());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxPerUnit2());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
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

//TODO UnitPrice
	public DataTableAjax<CppUnitPriceVo> listUnitPrice(CreatePaperFormVo request) {
		DataTableAjax<CppUnitPriceVo> dataTableAjax = new DataTableAjax<CppUnitPriceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataUnitPrice(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppUnitPriceVo> getDataUnitPrice(int start, int length, int total) {
		logger.info("getDataUnitPrice");
		String desc = "ราคาต่อหน่วยสินค้าที่ขอลดหย่อนภาษี";
		List<CppUnitPriceVo> datalist = new ArrayList<CppUnitPriceVo>();
		CppUnitPriceVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppUnitPriceVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
			data.setTotalTax1("1,000.00");
			data.setQuantity1("100.00");
			data.setTaxPerUnit1("10.00");
			data.setReceiptNum("001-22-70" + (i + 1));
			data.setTotalTax2("1,000.00");
			data.setQuantity2("100.00");
			data.setTaxPerUnit2("10.00");
			data.setDiff("0.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportUnitPrice() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(UNIT_PRICE);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from Utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle thCpColor = ExcelUtils.getThCpColor();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "รายการ", "ขอลดหย่อนตามแบบ ภส. ๐๕-๐๓", "", "", "ใบเสร็จรับเงิน", "", "", "",
				"ผลต่าง" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i > 4 && i < 9) {
				cell.setCellStyle(thCpColor);
			} else {
				cell.setCellStyle(thStyle);
			}

		}

		/* tbTH2 */
		String[] tbTH2 = { "", "", "ภาษีรวม", "ปริมาณ", "ภาษีต่อหน่วย", "เลขที่ใบเสร็จ", "ภาษีรวม", "ปริมาณ",
				"ภาษีต่อหน่วย" };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			if (i > 1) {
				cell = row.createCell(i);
				cell.setCellValue(tbTH2[i]);
				if (i > 4 && i < 9) {
					cell.setCellStyle(thCpColor);
				} else {
					cell.setCellStyle(thStyle);
				}
			}
		}

		/* width */
		for (int i = 0; i < 10; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 8));

		for (int i = 0; i < 10; i++) {
			if (i < 2 || i > 8) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		List<CppUnitPriceVo> dataList = getDataUnitPrice(0, TOTAL, TOTAL);
		for (CppUnitPriceVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTotalTax1());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getQuantity1());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxPerUnit1());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getReceiptNum());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTotalTax2());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getQuantity2());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxPerUnit2());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
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

//TODO CheckPrice
	public DataTableAjax<CppCheckPriceVo> listCheckPrice(CreatePaperFormVo request) {
		DataTableAjax<CppCheckPriceVo> dataTableAjax = new DataTableAjax<CppCheckPriceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataCheckPrice(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppCheckPriceVo> getDataCheckPrice(int start, int length, int total) {
		logger.info("getDataCheckPrice");
		String desc = "ตรวจสอบด้านราคา";
		List<CppCheckPriceVo> datalist = new ArrayList<CppCheckPriceVo>();
		CppCheckPriceVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppCheckPriceVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
			data.setPriceNotiPs("1,000.00");
			data.setPriceDataEx("1,500.00");
			data.setPriceUnit("1,400.00");
			data.setPriceRetail("1,400.00");
			data.setTax("1,000.00");
			data.setDiff("100.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportCheckPrice() {

		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(CHECK_PRICE);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle thCpColor = ExcelUtils.getThCpColor();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ราคาตามแบบแจ้ง ภส. ๐๒-๐๑", "ราคาจากข้อมูลภายนอก",
				"ราคาต่อหน่วยตามประกาศกรม", "ราคาขายปลีกแนะนำจาก ภส. ๐๓-๐๗", "แบบรายการภาษี ภส. ๐๓-๐๗	", "ผลต่าง" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i > 2 && i < 6) {
				cell.setCellStyle(thCpColor);
			} else {
				cell.setCellStyle(thStyle);
			}

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

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<CppCheckPriceVo> dataList = getDataCheckPrice(0, TOTAL, TOTAL);
		for (CppCheckPriceVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPriceNotiPs());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPriceDataEx());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPriceUnit());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPriceUnit());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTax());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
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

//TODO PayForeignFinishedGoods
	public DataTableAjax<CppPayForeignFinishedGoodsVo> listPayForeignFinishedGoods(CreatePaperFormVo request) {
		DataTableAjax<CppPayForeignFinishedGoodsVo> dataTableAjax = new DataTableAjax<CppPayForeignFinishedGoodsVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataPayForeignFinishedGoods(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppPayForeignFinishedGoodsVo> getDataPayForeignFinishedGoods(int start, int length, int total) {
		logger.info("getDataPayForeignFinishedGoods");
		String desc = "จ่ายสินค้าสำเร็จรูปต่างประเทศ";
		List<CppPayForeignFinishedGoodsVo> datalist = new ArrayList<CppPayForeignFinishedGoodsVo>();
		CppPayForeignFinishedGoodsVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppPayForeignFinishedGoodsVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
			data.setTransportDoc("100-222-22" + (i + 1));
			data.setInv("GT-00" + (i + 1));
			data.setPs1("TS00" + (i + 1));
			data.setMonthStatement("1,000.00");
			data.setCheck("900.00");
			data.setPs2("TS00+G" + (i + 1));
			data.setDiff("100.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportPayForeignFinishedGoods() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(PAY_FOREIGN_FINISHED_GOODS);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle thCpColor = ExcelUtils.getThCpColor();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ใบขนสินค้า", "INV", "ภส. ๐๗-๐๒", "งบเดือน ภส. ๐๗-๐๔", "จากการตรวจสอบ	",
				"ภส. ๐๕-๐๑", "ผลต่าง" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i != 2 && i != 3 && i != 6) {
				cell.setCellStyle(thStyle);
			} else {
				cell.setCellStyle(thCpColor);
			}

		}

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);

		/* set tbTD */
		rowNum = 1;
		cellNum = 0;
		int no = 1;

		/* set data */
		List<CppPayForeignFinishedGoodsVo> dataList = getDataPayForeignFinishedGoods(0, TOTAL, TOTAL);
		for (CppPayForeignFinishedGoodsVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTransportDoc());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getInv());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPs1());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMonthStatement());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getCheck());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPs2());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiff());
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
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

	// TODO Tax
	public DataTableAjax<CppTaxVo> listTax(CreatePaperFormVo request) {
		DataTableAjax<CppTaxVo> dataTableAjax = new DataTableAjax<CppTaxVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataTax(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<CppTaxVo> getDataTax(int start, int length, int total) {
		logger.info("getDataTax");
		String desc = "คำนวณภาษีที่ต้องชำระเพิ่ม";
		List<CppTaxVo> datalist = new ArrayList<CppTaxVo>();
		CppTaxVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new CppTaxVo();
			data.setId(Long.valueOf(1));
			data.setList(desc + (i + 1));
			data.setQuantity("1,000.00");
			data.setPriceRetail("10,000.00");
			data.setCost("10,000.00");
			data.setTaxRate("10.00");
			data.setTaxAdditional("1,000.00");
			data.setFine("500.00");
			data.setExtraMoney("100.00");
			data.setTaxLocal("100.00");
			data.setTotal("22,710.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportTax() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		Sheet sheet = workbook.createSheet(TAX);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle cellCenter = ExcelUtils.getCellCenter();
		CellStyle cellLeft = ExcelUtils.getCellLeft();
		CellStyle cellRight = ExcelUtils.getCellRight();

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ปริมาณ", "ราคาขายปลีก", "มูลค่า", "อัตราภาษี (ร้อยละ)",
				"ภาษีที่ต้องชำระเพิ่มเติม", "เบี้ยปรับ", "เงินเพิ่ม", "ภาษีเพื่อราชการส่วนท้องถิ่น", "รวม" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(thStyle);
		}

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);



		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<CppTaxVo> dataList = getDataTax(0, TOTAL, TOTAL);
		for (CppTaxVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getList());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getQuantity());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPriceRetail());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getCost());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxRate());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxAdditional());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getFine());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getExtraMoney());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxLocal());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTotal());
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
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
}

package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ExcelUtils;
import th.co.baiwa.excise.ia.persistence.vo.Int076Vo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int076Service {

	// Set property Style Excel

	/* Set Color1 */
	private XSSFCellStyle CenterColor1;
	private XSSFCellStyle LeftColor1;
	private XSSFCellStyle RightColor1;

	/* Set Color2 */
	private XSSFCellStyle CenterColor2;
	private XSSFCellStyle LeftColor2;
	private XSSFCellStyle RightColor2;

	/* Set Color3 */
	private XSSFCellStyle CenterColor3;
	private XSSFCellStyle LeftColor3;
	private XSSFCellStyle RightColor3;

	/* Set Color4 */
	private XSSFCellStyle CenterColor4;
	private XSSFCellStyle LeftColor4;
	private XSSFCellStyle RightColor4;

	/* Set Color5 */
	private XSSFCellStyle CenterColor5;
	private XSSFCellStyle LeftColor5;
	private XSSFCellStyle RightColor5;

	/* Set Color6 */
	private XSSFCellStyle CenterColor6;
	private XSSFCellStyle LeftColor6;
	private XSSFCellStyle RightColor6;

	/* Set Color7 */
	private XSSFCellStyle CenterColor7;
	private XSSFCellStyle LeftColor7;
	private XSSFCellStyle RightColor7;

	/* Set Color8 */
	private XSSFCellStyle CenterColor8;
	private XSSFCellStyle LeftColor8;
	private XSSFCellStyle RightColor8;

	/* Set Color9 */
	private XSSFCellStyle CenterColor9;
	private XSSFCellStyle LeftColor9;
	private XSSFCellStyle RightColor9;

	/* Set Color10 */
	private XSSFCellStyle CenterColor10;
	private XSSFCellStyle LeftColor10;
	private XSSFCellStyle RightColor10;

	/* Set Color11 */
	private XSSFCellStyle CenterColor11;
	private XSSFCellStyle LeftColor11;
	private XSSFCellStyle RightColor11;

	/* Set Color12 */
	private XSSFCellStyle CenterColor12;
	private XSSFCellStyle LeftColor12;
	private XSSFCellStyle RightColor12;

	/* Set ColorError */
	private XSSFCellStyle CenterColorError;
	private XSSFCellStyle LeftColorError;
	private XSSFCellStyle RightColorError;

	private CellStyle thStyle;
	private CellStyle cellCenter;
	private CellStyle cellRight;
	private CellStyle cellLeft;
	private CellStyle bgRed;
	private CellStyle bgYellow;
	private CellStyle bgGreen;
	private CellStyle topCenter;
	private CellStyle topRight;
	private CellStyle topLeft;
	private Font fontHeader;

	// !Set property Style Excel

	private Logger logger = LoggerFactory.getLogger(Int076Service.class);

	DecimalFormat formatter = new DecimalFormat("#,##0.00");
	private String tmpDatePosted = "";

	public static final String START_DATA = "บัญชีเงินฝาก : 10778 ภาษีบำรุงองค์กรปกครองส่วนท้องถิ่นเพื่อรอจัดสรร";
	public static final String END_DATA = "รายงานแสดงการเคลื่อนไหวเงินฝากกระทรวงการคลัง";
	public static final String END_SHEET = "***** รวมบัญชีเงินฝาก : 10778 ภาษีบำรุงองค์กรปกครองส่วนท้องถิ่นเพื่อรอ";

	public List<Int076Vo> readFileExcelOPT(Int076Vo formVo)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		List<Int076Vo> optList = new ArrayList<>();

		Map<String, Integer> map = new HashMap<String, Integer>();
		logger.info("readFileExcelOPT");
		System.out.println(formVo.getFileExel().getOriginalFilename());
		System.out.println(formVo.getFileExel().getContentType());
		byte[] byt = formVo.getFileExel().getBytes();

		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getLastRowNum();

		int tmpRow = 0;
		boolean header = false;

		/* loop check header */
		for (int r = 0; r <= totalRows; r++) {
			Row row = sheet.getRow(r);
			/* check header */
			if (row != null) {
				short startCellHeader = row.getFirstCellNum();
				Cell cellHeader = row.getCell(startCellHeader);

				if (START_DATA.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellHeader)))) {
					header = true;
					tmpRow = r;
					break;
				}
			}

		}
		/* loop check Check Data */
		if (header) {
			tmpRow++; // start next one row
			/* loop row data */
			for (int rowDetail = tmpRow; rowDetail <= totalRows; rowDetail++) {
				Row rowData = sheet.getRow(rowDetail);

				boolean endData = false;
				boolean endSheet = false;
				List<String> columns = new ArrayList<>();

				if (rowData != null) {
					/* loop column data */
					for (short col = 1; col < 19; col++) {
						Cell cell = rowData.getCell(col);

						/* cellCheck is read cell !null */
						short startData = rowData.getFirstCellNum();
						Cell cellCheck = rowData.getCell(startData);

						endData = (END_DATA.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellCheck))) ? true
								: false);
						endSheet = (END_SHEET.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellCheck)))
								? true
								: false);
						/* END_DATA */
						if (endData) {
							rowDetail += 10;
							break;
							/* END_SHEET */
						} else if (endSheet) {
							break;
							/* READ_DATA */
						} else {
/*							String value = ExcelUtils.getCellValueAsString(cell);
							System.out.println(value + " col::" + col + " row::" + rowDetail);*/

							if (StringUtils.isNotBlank(ExcelUtils.getCellValueAsString(cell))) {
								map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
								columns.add(ExcelUtils.getCellValueAsString(cell));
							} else {
								columns.add("");
							}

						}

					}
					addData(optList, columns, rowDetail);
				}
				if (endSheet) {
					break;
				}
			}
		}

		return optList;
	}

	public void addData(List<Int076Vo> optList, List<String> data, int rowId) {

		boolean checkData;
		checkData = (data.isEmpty() ? false : true);

		if (checkData) {
			Int076Vo vo = new Int076Vo();

			try {

				vo.setId(new Long(rowId));

				if (StringUtils.isNotBlank(data.get(0))) {
					vo.setDatePosted(StringUtils.trim(data.get(0)));

					if (vo.getDatePosted().indexOf(".01.") != -1) {
						vo.setColor("1");
					} else if (vo.getDatePosted().indexOf(".02.") != -1) {
						vo.setColor("2");
					} else if (vo.getDatePosted().indexOf(".03.") != -1) {
						vo.setColor("3");
					} else if (vo.getDatePosted().indexOf(".04.") != -1) {
						vo.setColor("4");
					} else if (vo.getDatePosted().indexOf(".05.") != -1) {
						vo.setColor("5");
					} else if (vo.getDatePosted().indexOf(".06.") != -1) {
						vo.setColor("6");
					} else if (vo.getDatePosted().indexOf(".07.") != -1) {
						vo.setColor("7");
					} else if (vo.getDatePosted().indexOf(".08.") != -1) {
						vo.setColor("8");
					} else if (vo.getDatePosted().indexOf(".09.") != -1) {
						vo.setColor("9");
					} else if (vo.getDatePosted().indexOf(".10.") != -1) {
						vo.setColor("10");
					} else if (vo.getDatePosted().indexOf(".11.") != -1) {
						vo.setColor("11");
					} else if (vo.getDatePosted().indexOf(".12.") != -1) {
						vo.setColor("12");
					}

				} else {
					vo.setDatePosted(tmpDatePosted);

					if (vo.getDatePosted().indexOf(".01.") != -1) {
						vo.setColor("1");
					} else if (vo.getDatePosted().indexOf(".02.") != -1) {
						vo.setColor("2");
					} else if (vo.getDatePosted().indexOf(".03.") != -1) {
						vo.setColor("3");
					} else if (vo.getDatePosted().indexOf(".04.") != -1) {
						vo.setColor("4");
					} else if (vo.getDatePosted().indexOf(".05.") != -1) {
						vo.setColor("5");
					} else if (vo.getDatePosted().indexOf(".06.") != -1) {
						vo.setColor("6");
					} else if (vo.getDatePosted().indexOf(".07.") != -1) {
						vo.setColor("7");
					} else if (vo.getDatePosted().indexOf(".08.") != -1) {
						vo.setColor("8");
					} else if (vo.getDatePosted().indexOf(".09.") != -1) {
						vo.setColor("9");
					} else if (vo.getDatePosted().indexOf(".10.") != -1) {
						vo.setColor("10");
					} else if (vo.getDatePosted().indexOf(".11.") != -1) {
						vo.setColor("11");
					} else if (vo.getDatePosted().indexOf(".12.") != -1) {
						vo.setColor("12");
					}
				}
				// set temp date
				if (StringUtils.isNotBlank(data.get(0))) {
					tmpDatePosted = data.get(0);
				}
				// DocNumber
				if (StringUtils.isNotBlank(data.get(3))) {
					vo.setDocNumber(StringUtils.trim(data.get(3)));
				} else {
					vo.setDocNumber("");
				}
				// DocType
				if (StringUtils.isNotBlank(data.get(5))) {
					vo.setDocType(StringUtils.trim(data.get(5)));
				} else {
					vo.setDocType("");
				}
				// DocRefer
				if (StringUtils.isNotBlank(data.get(6))) {
					vo.setDocRefer(StringUtils.trim(data.get(6)));
				} else {
					vo.setDocRefer("");
				}
				// Actor
				if (StringUtils.isNotBlank(data.get(7))) {
					vo.setActor(StringUtils.trim(data.get(7)));
				} else {
					vo.setActor("");
				}
				// Determination
				if (StringUtils.isNotBlank(data.get(10))) {
					vo.setDetermination(StringUtils.trim(data.get(10)));
				} else {
					vo.setDetermination("");
				}
				// PayUnit
				if (StringUtils.isNotBlank(data.get(13))) {
					vo.setPayUnit(StringUtils.trim(data.get(13)));
				} else {
					vo.setPayUnit("");
				}
				// Debit
				if (StringUtils.isNotBlank(data.get(14))) {
					vo.setDebit(new BigDecimal(data.get(14)));
				} else {
					vo.setDebit(BigDecimal.ZERO);
				}
				// Credit
				if (StringUtils.isNotBlank(data.get(15))) {
					vo.setCredit(new BigDecimal(data.get(15)));
				} else {
					vo.setCredit(BigDecimal.ZERO);
				}

				if (StringUtils.isNotBlank(data.get(17))) {
					vo.setLiftUp(new BigDecimal(data.get(17)));
				} else {
					vo.setLiftUp(new BigDecimal(""));
				}

				optList.add(vo);
			} catch (Exception e) {
				optList.add(vo);

			}
		}

	}

	public List<Int076Vo> checkData(List<Int076Vo> dataList) {

		int monJ0;
		int yearJ0;
		String dateJ0 = "";

		ArrayList<BigDecimal> liftUpList = new ArrayList<BigDecimal>();

		for (Int076Vo dataJ0 : dataList) {

			/* check J0 */
			if ("J0".equals(dataJ0.getDocType())) {
				/* check dateJ0 */
				if ("01".equals(dataJ0.getDatePosted().substring(3, 5))) {
					monJ0 = 12;
					yearJ0 = Integer.parseInt(dataJ0.getDatePosted().substring(6, 10));
					yearJ0--;
					dateJ0 = monJ0 + "." + yearJ0;
				} else {
					monJ0 = Integer.parseInt(dataJ0.getDatePosted().substring(3, 5));
					monJ0--;
					yearJ0 = Integer.parseInt(dataJ0.getDatePosted().substring(6, 10));
					yearJ0--;
					if (monJ0 == 10 || monJ0 == 11) {
						dateJ0 = monJ0 + "." + yearJ0;
					} else {
						dateJ0 = "0" + monJ0 + "." + yearJ0;
					}

				}
				/* check IX */
				for (Int076Vo dataIX : dataList) {
					/* check dateIX */
					if ("IX".equals(dataIX.getDocType()) && dateJ0.equals(dataIX.getDatePosted().substring(3, 10))
							&& BeanUtils.isNotEmpty(dataIX.getLiftUp())) {

						liftUpList.add(dataIX.getLiftUp());

					}

				}
				/* check liftUp IX == Credit :: CheckData=Y */
				if (BeanUtils.isNotEmpty(liftUpList)
						&& liftUpList.get(liftUpList.size() - 1).equals(dataJ0.getCredit())) {
					dataJ0.setCheckData("Y");
				} else {
					dataJ0.setCheckData("N");
				}

				liftUpList = new ArrayList<BigDecimal>();

			}

		}

		return dataList;
	}

	private XSSFWorkbook setUpExcel() {
		XSSFWorkbook workbook = new XSSFWorkbook();
		/* Color1 */
		CenterColor1 = workbook.createCellStyle();
		CenterColor1.setFillForegroundColor(new XSSFColor(new java.awt.Color(242, 230, 222)));
		CenterColor1.setAlignment(HorizontalAlignment.CENTER);
		CenterColor1.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor1.setBorderBottom(BorderStyle.THIN);
		CenterColor1.setBorderLeft(BorderStyle.THIN);
		CenterColor1.setBorderRight(BorderStyle.THIN);
		CenterColor1.setBorderTop(BorderStyle.THIN);
		CenterColor1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor1 = workbook.createCellStyle();
		LeftColor1.setFillForegroundColor(new XSSFColor(new java.awt.Color(242, 230, 222)));
		LeftColor1.setAlignment(HorizontalAlignment.LEFT);
		LeftColor1.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor1.setBorderBottom(BorderStyle.THIN);
		LeftColor1.setBorderLeft(BorderStyle.THIN);
		LeftColor1.setBorderRight(BorderStyle.THIN);
		LeftColor1.setBorderTop(BorderStyle.THIN);
		LeftColor1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor1 = workbook.createCellStyle();
		RightColor1.setFillForegroundColor(new XSSFColor(new java.awt.Color(242, 230, 222)));
		RightColor1.setAlignment(HorizontalAlignment.RIGHT);
		RightColor1.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor1.setBorderBottom(BorderStyle.THIN);
		RightColor1.setBorderLeft(BorderStyle.THIN);
		RightColor1.setBorderRight(BorderStyle.THIN);
		RightColor1.setBorderTop(BorderStyle.THIN);
		RightColor1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color2 */
		CenterColor2 = workbook.createCellStyle();
		CenterColor2.setFillForegroundColor(new XSSFColor(new java.awt.Color(243, 237, 220)));
		CenterColor2.setAlignment(HorizontalAlignment.CENTER);
		CenterColor2.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor2.setBorderBottom(BorderStyle.THIN);
		CenterColor2.setBorderLeft(BorderStyle.THIN);
		CenterColor2.setBorderRight(BorderStyle.THIN);
		CenterColor2.setBorderTop(BorderStyle.THIN);
		CenterColor2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor2 = workbook.createCellStyle();
		LeftColor2.setFillForegroundColor(new XSSFColor(new java.awt.Color(243, 237, 220)));
		LeftColor2.setAlignment(HorizontalAlignment.LEFT);
		LeftColor2.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor2.setBorderBottom(BorderStyle.THIN);
		LeftColor2.setBorderLeft(BorderStyle.THIN);
		LeftColor2.setBorderRight(BorderStyle.THIN);
		LeftColor2.setBorderTop(BorderStyle.THIN);
		LeftColor2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor2 = workbook.createCellStyle();
		RightColor2.setFillForegroundColor(new XSSFColor(new java.awt.Color(243, 237, 220)));
		RightColor2.setAlignment(HorizontalAlignment.RIGHT);
		RightColor2.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor2.setBorderBottom(BorderStyle.THIN);
		RightColor2.setBorderLeft(BorderStyle.THIN);
		RightColor2.setBorderRight(BorderStyle.THIN);
		RightColor2.setBorderTop(BorderStyle.THIN);
		RightColor2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color3 */
		CenterColor3 = workbook.createCellStyle();
		CenterColor3.setFillForegroundColor(new XSSFColor(new java.awt.Color(239, 240, 229)));
		CenterColor3.setAlignment(HorizontalAlignment.CENTER);
		CenterColor3.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor3.setBorderBottom(BorderStyle.THIN);
		CenterColor3.setBorderLeft(BorderStyle.THIN);
		CenterColor3.setBorderRight(BorderStyle.THIN);
		CenterColor3.setBorderTop(BorderStyle.THIN);
		CenterColor3.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor3 = workbook.createCellStyle();
		LeftColor3.setFillForegroundColor(new XSSFColor(new java.awt.Color(239, 240, 229)));
		LeftColor3.setAlignment(HorizontalAlignment.LEFT);
		LeftColor3.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor3.setBorderBottom(BorderStyle.THIN);
		LeftColor3.setBorderLeft(BorderStyle.THIN);
		LeftColor3.setBorderRight(BorderStyle.THIN);
		LeftColor3.setBorderTop(BorderStyle.THIN);
		LeftColor3.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor3 = workbook.createCellStyle();
		RightColor3.setFillForegroundColor(new XSSFColor(new java.awt.Color(239, 240, 229)));
		RightColor3.setAlignment(HorizontalAlignment.RIGHT);
		RightColor3.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor3.setBorderBottom(BorderStyle.THIN);
		RightColor3.setBorderLeft(BorderStyle.THIN);
		RightColor3.setBorderRight(BorderStyle.THIN);
		RightColor3.setBorderTop(BorderStyle.THIN);
		RightColor3.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color4 */
		CenterColor4 = workbook.createCellStyle();
		CenterColor4.setFillForegroundColor(new XSSFColor(new java.awt.Color(225, 238, 228)));
		CenterColor4.setAlignment(HorizontalAlignment.CENTER);
		CenterColor4.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor4.setBorderBottom(BorderStyle.THIN);
		CenterColor4.setBorderLeft(BorderStyle.THIN);
		CenterColor4.setBorderRight(BorderStyle.THIN);
		CenterColor4.setBorderTop(BorderStyle.THIN);
		CenterColor4.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor4 = workbook.createCellStyle();
		LeftColor4.setFillForegroundColor(new XSSFColor(new java.awt.Color(225, 238, 228)));
		LeftColor4.setAlignment(HorizontalAlignment.LEFT);
		LeftColor4.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor4.setBorderBottom(BorderStyle.THIN);
		LeftColor4.setBorderLeft(BorderStyle.THIN);
		LeftColor4.setBorderRight(BorderStyle.THIN);
		LeftColor4.setBorderTop(BorderStyle.THIN);
		LeftColor4.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor4 = workbook.createCellStyle();
		RightColor4.setFillForegroundColor(new XSSFColor(new java.awt.Color(225, 238, 228)));
		RightColor4.setAlignment(HorizontalAlignment.RIGHT);
		RightColor4.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor4.setBorderBottom(BorderStyle.THIN);
		RightColor4.setBorderLeft(BorderStyle.THIN);
		RightColor4.setBorderRight(BorderStyle.THIN);
		RightColor4.setBorderTop(BorderStyle.THIN);
		RightColor4.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color5 */
		CenterColor5 = workbook.createCellStyle();
		CenterColor5.setFillForegroundColor(new XSSFColor(new java.awt.Color(225, 244, 244)));
		CenterColor5.setAlignment(HorizontalAlignment.CENTER);
		CenterColor5.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor5.setBorderBottom(BorderStyle.THIN);
		CenterColor5.setBorderLeft(BorderStyle.THIN);
		CenterColor5.setBorderRight(BorderStyle.THIN);
		CenterColor5.setBorderTop(BorderStyle.THIN);
		CenterColor5.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor5 = workbook.createCellStyle();
		LeftColor5.setFillForegroundColor(new XSSFColor(new java.awt.Color(225, 244, 244)));
		LeftColor5.setAlignment(HorizontalAlignment.LEFT);
		LeftColor5.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor5.setBorderBottom(BorderStyle.THIN);
		LeftColor5.setBorderLeft(BorderStyle.THIN);
		LeftColor5.setBorderRight(BorderStyle.THIN);
		LeftColor5.setBorderTop(BorderStyle.THIN);
		LeftColor5.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor5 = workbook.createCellStyle();
		RightColor5.setFillForegroundColor(new XSSFColor(new java.awt.Color(225, 244, 244)));
		RightColor5.setAlignment(HorizontalAlignment.RIGHT);
		RightColor5.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor5.setBorderBottom(BorderStyle.THIN);
		RightColor5.setBorderLeft(BorderStyle.THIN);
		RightColor5.setBorderRight(BorderStyle.THIN);
		RightColor5.setBorderTop(BorderStyle.THIN);
		RightColor5.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color6 */
		CenterColor6 = workbook.createCellStyle();
		CenterColor6.setFillForegroundColor(new XSSFColor(new java.awt.Color(223, 232, 239)));
		CenterColor6.setAlignment(HorizontalAlignment.CENTER);
		CenterColor6.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor6.setBorderBottom(BorderStyle.THIN);
		CenterColor6.setBorderLeft(BorderStyle.THIN);
		CenterColor6.setBorderRight(BorderStyle.THIN);
		CenterColor6.setBorderTop(BorderStyle.THIN);
		CenterColor6.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor6 = workbook.createCellStyle();
		LeftColor6.setFillForegroundColor(new XSSFColor(new java.awt.Color(223, 232, 239)));
		LeftColor6.setAlignment(HorizontalAlignment.LEFT);
		LeftColor6.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor6.setBorderBottom(BorderStyle.THIN);
		LeftColor6.setBorderLeft(BorderStyle.THIN);
		LeftColor6.setBorderRight(BorderStyle.THIN);
		LeftColor6.setBorderTop(BorderStyle.THIN);
		LeftColor6.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor6 = workbook.createCellStyle();
		RightColor6.setFillForegroundColor(new XSSFColor(new java.awt.Color(223, 232, 239)));
		RightColor6.setAlignment(HorizontalAlignment.RIGHT);
		RightColor6.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor6.setBorderBottom(BorderStyle.THIN);
		RightColor6.setBorderLeft(BorderStyle.THIN);
		RightColor6.setBorderRight(BorderStyle.THIN);
		RightColor6.setBorderTop(BorderStyle.THIN);
		RightColor6.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color7 */
		CenterColor7 = workbook.createCellStyle();
		CenterColor7.setFillForegroundColor(new XSSFColor(new java.awt.Color(237, 232, 246)));
		CenterColor7.setAlignment(HorizontalAlignment.CENTER);
		CenterColor7.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor7.setBorderBottom(BorderStyle.THIN);
		CenterColor7.setBorderLeft(BorderStyle.THIN);
		CenterColor7.setBorderRight(BorderStyle.THIN);
		CenterColor7.setBorderTop(BorderStyle.THIN);
		CenterColor7.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor7 = workbook.createCellStyle();
		LeftColor7.setFillForegroundColor(new XSSFColor(new java.awt.Color(237, 232, 246)));
		LeftColor7.setAlignment(HorizontalAlignment.LEFT);
		LeftColor7.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor7.setBorderBottom(BorderStyle.THIN);
		LeftColor7.setBorderLeft(BorderStyle.THIN);
		LeftColor7.setBorderRight(BorderStyle.THIN);
		LeftColor7.setBorderTop(BorderStyle.THIN);
		LeftColor7.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor7 = workbook.createCellStyle();
		RightColor7.setFillForegroundColor(new XSSFColor(new java.awt.Color(237, 232, 246)));
		RightColor7.setAlignment(HorizontalAlignment.RIGHT);
		RightColor7.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor7.setBorderBottom(BorderStyle.THIN);
		RightColor7.setBorderLeft(BorderStyle.THIN);
		RightColor7.setBorderRight(BorderStyle.THIN);
		RightColor7.setBorderTop(BorderStyle.THIN);
		RightColor7.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color8 */
		CenterColor8 = workbook.createCellStyle();
		CenterColor8.setFillForegroundColor(new XSSFColor(new java.awt.Color(241, 225, 234)));
		CenterColor8.setAlignment(HorizontalAlignment.CENTER);
		CenterColor8.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor8.setBorderBottom(BorderStyle.THIN);
		CenterColor8.setBorderLeft(BorderStyle.THIN);
		CenterColor8.setBorderRight(BorderStyle.THIN);
		CenterColor8.setBorderTop(BorderStyle.THIN);
		CenterColor8.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor8 = workbook.createCellStyle();
		LeftColor8.setFillForegroundColor(new XSSFColor(new java.awt.Color(241, 225, 234)));
		LeftColor8.setAlignment(HorizontalAlignment.LEFT);
		LeftColor8.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor8.setBorderBottom(BorderStyle.THIN);
		LeftColor8.setBorderLeft(BorderStyle.THIN);
		LeftColor8.setBorderRight(BorderStyle.THIN);
		LeftColor8.setBorderTop(BorderStyle.THIN);
		LeftColor8.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor8 = workbook.createCellStyle();
		RightColor8.setFillForegroundColor(new XSSFColor(new java.awt.Color(241, 225, 234)));
		RightColor8.setAlignment(HorizontalAlignment.RIGHT);
		RightColor8.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor8.setBorderBottom(BorderStyle.THIN);
		RightColor8.setBorderLeft(BorderStyle.THIN);
		RightColor8.setBorderRight(BorderStyle.THIN);
		RightColor8.setBorderTop(BorderStyle.THIN);
		RightColor8.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color9 */
		CenterColor9 = workbook.createCellStyle();
		CenterColor9.setFillForegroundColor(new XSSFColor(new java.awt.Color(231, 226, 222)));
		CenterColor9.setAlignment(HorizontalAlignment.CENTER);
		CenterColor9.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor9.setBorderBottom(BorderStyle.THIN);
		CenterColor9.setBorderLeft(BorderStyle.THIN);
		CenterColor9.setBorderRight(BorderStyle.THIN);
		CenterColor9.setBorderTop(BorderStyle.THIN);
		CenterColor9.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor9 = workbook.createCellStyle();
		LeftColor9.setFillForegroundColor(new XSSFColor(new java.awt.Color(231, 226, 222)));
		LeftColor9.setAlignment(HorizontalAlignment.LEFT);
		LeftColor9.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor9.setBorderBottom(BorderStyle.THIN);
		LeftColor9.setBorderLeft(BorderStyle.THIN);
		LeftColor9.setBorderRight(BorderStyle.THIN);
		LeftColor9.setBorderTop(BorderStyle.THIN);
		LeftColor9.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor9 = workbook.createCellStyle();
		RightColor9.setFillForegroundColor(new XSSFColor(new java.awt.Color(231, 226, 222)));
		RightColor9.setAlignment(HorizontalAlignment.RIGHT);
		RightColor9.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor9.setBorderBottom(BorderStyle.THIN);
		RightColor9.setBorderLeft(BorderStyle.THIN);
		RightColor9.setBorderRight(BorderStyle.THIN);
		RightColor9.setBorderTop(BorderStyle.THIN);
		RightColor9.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color10 */
		CenterColor10 = workbook.createCellStyle();
		CenterColor10.setFillForegroundColor(new XSSFColor(new java.awt.Color(235, 229, 225)));
		CenterColor10.setAlignment(HorizontalAlignment.CENTER);
		CenterColor10.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor10.setBorderBottom(BorderStyle.THIN);
		CenterColor10.setBorderLeft(BorderStyle.THIN);
		CenterColor10.setBorderRight(BorderStyle.THIN);
		CenterColor10.setBorderTop(BorderStyle.THIN);
		CenterColor10.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor10 = workbook.createCellStyle();
		LeftColor10.setFillForegroundColor(new XSSFColor(new java.awt.Color(235, 229, 225)));
		LeftColor10.setAlignment(HorizontalAlignment.LEFT);
		LeftColor10.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor10.setBorderBottom(BorderStyle.THIN);
		LeftColor10.setBorderLeft(BorderStyle.THIN);
		LeftColor10.setBorderRight(BorderStyle.THIN);
		LeftColor10.setBorderTop(BorderStyle.THIN);
		LeftColor10.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor10 = workbook.createCellStyle();
		RightColor10.setFillForegroundColor(new XSSFColor(new java.awt.Color(235, 229, 225)));
		RightColor10.setAlignment(HorizontalAlignment.RIGHT);
		RightColor10.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor10.setBorderBottom(BorderStyle.THIN);
		RightColor10.setBorderLeft(BorderStyle.THIN);
		RightColor10.setBorderRight(BorderStyle.THIN);
		RightColor10.setBorderTop(BorderStyle.THIN);
		RightColor10.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color11 */
		CenterColor11 = workbook.createCellStyle();
		CenterColor11.setFillForegroundColor(new XSSFColor(new java.awt.Color(233, 234, 231)));
		CenterColor11.setAlignment(HorizontalAlignment.CENTER);
		CenterColor11.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor11.setBorderBottom(BorderStyle.THIN);
		CenterColor11.setBorderLeft(BorderStyle.THIN);
		CenterColor11.setBorderRight(BorderStyle.THIN);
		CenterColor11.setBorderTop(BorderStyle.THIN);
		CenterColor11.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor11 = workbook.createCellStyle();
		LeftColor11.setFillForegroundColor(new XSSFColor(new java.awt.Color(233, 234, 231)));
		LeftColor11.setAlignment(HorizontalAlignment.LEFT);
		LeftColor11.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor11.setBorderBottom(BorderStyle.THIN);
		LeftColor11.setBorderLeft(BorderStyle.THIN);
		LeftColor11.setBorderRight(BorderStyle.THIN);
		LeftColor11.setBorderTop(BorderStyle.THIN);
		LeftColor11.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor11 = workbook.createCellStyle();
		RightColor11.setFillForegroundColor(new XSSFColor(new java.awt.Color(233, 234, 231)));
		RightColor11.setAlignment(HorizontalAlignment.RIGHT);
		RightColor11.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor11.setBorderBottom(BorderStyle.THIN);
		RightColor11.setBorderLeft(BorderStyle.THIN);
		RightColor11.setBorderRight(BorderStyle.THIN);
		RightColor11.setBorderTop(BorderStyle.THIN);
		RightColor11.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* Color12 */
		CenterColor12 = workbook.createCellStyle();
		CenterColor12.setFillForegroundColor(new XSSFColor(new java.awt.Color(234, 233, 233)));
		CenterColor12.setAlignment(HorizontalAlignment.CENTER);
		CenterColor12.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColor12.setBorderBottom(BorderStyle.THIN);
		CenterColor12.setBorderLeft(BorderStyle.THIN);
		CenterColor12.setBorderRight(BorderStyle.THIN);
		CenterColor12.setBorderTop(BorderStyle.THIN);
		CenterColor12.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColor12 = workbook.createCellStyle();
		LeftColor12.setFillForegroundColor(new XSSFColor(new java.awt.Color(234, 233, 233)));
		LeftColor12.setAlignment(HorizontalAlignment.LEFT);
		LeftColor12.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColor12.setBorderBottom(BorderStyle.THIN);
		LeftColor12.setBorderLeft(BorderStyle.THIN);
		LeftColor12.setBorderRight(BorderStyle.THIN);
		LeftColor12.setBorderTop(BorderStyle.THIN);
		LeftColor12.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColor12 = workbook.createCellStyle();
		RightColor12.setFillForegroundColor(new XSSFColor(new java.awt.Color(234, 233, 233)));
		RightColor12.setAlignment(HorizontalAlignment.RIGHT);
		RightColor12.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColor12.setBorderBottom(BorderStyle.THIN);
		RightColor12.setBorderLeft(BorderStyle.THIN);
		RightColor12.setBorderRight(BorderStyle.THIN);
		RightColor12.setBorderTop(BorderStyle.THIN);
		RightColor12.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* ColorError */
		CenterColorError = workbook.createCellStyle();
		CenterColorError.setFillForegroundColor(new XSSFColor(new java.awt.Color(238, 43, 40)));
		CenterColorError.setAlignment(HorizontalAlignment.CENTER);
		CenterColorError.setVerticalAlignment(VerticalAlignment.CENTER);
		CenterColorError.setBorderBottom(BorderStyle.THIN);
		CenterColorError.setBorderLeft(BorderStyle.THIN);
		CenterColorError.setBorderRight(BorderStyle.THIN);
		CenterColorError.setBorderTop(BorderStyle.THIN);
		CenterColorError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		LeftColorError = workbook.createCellStyle();
		LeftColorError.setFillForegroundColor(new XSSFColor(new java.awt.Color(238, 43, 40)));
		LeftColorError.setAlignment(HorizontalAlignment.LEFT);
		LeftColorError.setVerticalAlignment(VerticalAlignment.CENTER);
		LeftColorError.setBorderBottom(BorderStyle.THIN);
		LeftColorError.setBorderLeft(BorderStyle.THIN);
		LeftColorError.setBorderRight(BorderStyle.THIN);
		LeftColorError.setBorderTop(BorderStyle.THIN);
		LeftColorError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		RightColorError = workbook.createCellStyle();
		RightColorError.setFillForegroundColor(new XSSFColor(new java.awt.Color(238, 43, 40)));
		RightColorError.setAlignment(HorizontalAlignment.RIGHT);
		RightColorError.setVerticalAlignment(VerticalAlignment.CENTER);
		RightColorError.setBorderBottom(BorderStyle.THIN);
		RightColorError.setBorderLeft(BorderStyle.THIN);
		RightColorError.setBorderRight(BorderStyle.THIN);
		RightColorError.setBorderTop(BorderStyle.THIN);
		RightColorError.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		/* thStyle */
		thStyle = workbook.createCellStyle();
		thStyle.setAlignment(HorizontalAlignment.CENTER);
		thStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		thStyle.setBorderBottom(BorderStyle.THIN);
		thStyle.setBorderLeft(BorderStyle.THIN);
		thStyle.setBorderRight(BorderStyle.THIN);
		thStyle.setBorderTop(BorderStyle.THIN);
		thStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		thStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cellCenter = workbook.createCellStyle();
		cellCenter.setAlignment(HorizontalAlignment.CENTER);
		cellCenter.setBorderBottom(BorderStyle.THIN);
		cellCenter.setBorderLeft(BorderStyle.THIN);
		cellCenter.setBorderRight(BorderStyle.THIN);
		cellCenter.setBorderTop(BorderStyle.THIN);

		cellRight = workbook.createCellStyle();
		cellRight.setAlignment(HorizontalAlignment.RIGHT);
		cellRight.setBorderBottom(BorderStyle.THIN);
		cellRight.setBorderLeft(BorderStyle.THIN);
		cellRight.setBorderRight(BorderStyle.THIN);
		cellRight.setBorderTop(BorderStyle.THIN);

		cellLeft = workbook.createCellStyle();
		cellLeft.setAlignment(HorizontalAlignment.LEFT);
		cellLeft.setBorderBottom(BorderStyle.THIN);
		cellLeft.setBorderLeft(BorderStyle.THIN);
		cellLeft.setBorderRight(BorderStyle.THIN);
		cellLeft.setBorderTop(BorderStyle.THIN);

		bgRed = workbook.createCellStyle();
		bgRed.setAlignment(HorizontalAlignment.CENTER);
		bgRed.setBorderBottom(BorderStyle.THIN);
		bgRed.setBorderLeft(BorderStyle.THIN);
		bgRed.setBorderRight(BorderStyle.THIN);
		bgRed.setBorderTop(BorderStyle.THIN);
		bgRed.setFillForegroundColor(IndexedColors.RED.getIndex());
		bgRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		bgYellow = workbook.createCellStyle();
		bgYellow.setAlignment(HorizontalAlignment.CENTER);
		bgYellow.setBorderBottom(BorderStyle.THIN);
		bgYellow.setBorderLeft(BorderStyle.THIN);
		bgYellow.setBorderRight(BorderStyle.THIN);
		bgYellow.setBorderTop(BorderStyle.THIN);
		bgYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		bgYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		bgGreen = workbook.createCellStyle();
		bgGreen.setAlignment(HorizontalAlignment.CENTER);
		bgGreen.setBorderBottom(BorderStyle.THIN);
		bgGreen.setBorderLeft(BorderStyle.THIN);
		bgGreen.setBorderRight(BorderStyle.THIN);
		bgGreen.setBorderTop(BorderStyle.THIN);
		bgGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		bgGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		fontHeader = workbook.createFont();
		fontHeader.setBold(true);

		topCenter = workbook.createCellStyle();
		topCenter.setAlignment(HorizontalAlignment.CENTER);
		topCenter.setFont(fontHeader);

		topRight = workbook.createCellStyle();
		topRight.setAlignment(HorizontalAlignment.RIGHT);

		topLeft = workbook.createCellStyle();
		topLeft.setAlignment(HorizontalAlignment.LEFT);
		return workbook;
	}

	public ByteArrayOutputStream export(List<Int076Vo> dataList) throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		System.out.println("Creating excel");

		// Header
		cell = row.createCell(cellNum);
		cell.setCellValue("ตรวจสอบการนำส่งเงินบัญชีเจ้าหนี้ อปท.");
		cell.setCellStyle(topCenter);
		rowNum = 2;
		row = sheet.createRow(rowNum);
		String[] tbTH1 = { "วันที่ลงรายการ", "เลขที่เอกสาร", "ประเภทเอกสาร", "เอกสารอ้างอิง", "ผู้ทำแทน", "การกำหนด",
				"หน่วยเบิกจ่าย", "ผ่านรายการ", "", "ยกยอดไป" };
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		}
		;

		Row rowTH2 = null;
		Cell cellTH2 = null;
		rowTH2 = sheet.createRow(3);
		int cellTH2Num = 7;
		cellTH2 = rowTH2.createCell(cellTH2Num);
		cellTH2.setCellValue("เดบิต");
		cellTH2.setCellStyle(thStyle);
		cellTH2Num++;
		cellTH2 = rowTH2.createCell(cellTH2Num);
		cellTH2.setCellValue("เครดิต");
		cellTH2.setCellStyle(thStyle);
		cellTH2Num++;
		cellTH2 = rowTH2.createCell(cellTH2Num);
		cellTH2.setCellStyle(thStyle);

		cellTH2.setCellStyle(thStyle);
		for (cellNum = 0; cellNum < 6; cellNum++) {
			cell = rowTH2.createCell(cellNum);
			cell.setCellStyle(thStyle);
		}
		;

		/* End Header */

		/* set sheet */
		sheet.setColumnWidth(0, 76 * 45);
		sheet.setColumnWidth(1, 76 * 60);
		sheet.setColumnWidth(2, 76 * 45);
		sheet.setColumnWidth(3, 76 * 100);
		sheet.setColumnWidth(4, 76 * 100);
		sheet.setColumnWidth(5, 76 * 60);
		sheet.setColumnWidth(6, 76 * 60);
		sheet.setColumnWidth(7, 76 * 60);
		sheet.setColumnWidth(8, 76 * 60);
		sheet.setColumnWidth(9, 76 * 60);

		// merge(firstRow, lastRow, firstCol, lastCol)
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 7, 8));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
		for (int i = 0; i <= 6; i++) {
			sheet.addMergedRegion(new CellRangeAddress(2, 3, i, i));
		}
		sheet.addMergedRegion(new CellRangeAddress(2, 3, 9, 9));

		/* Detail */
		rowNum = 4;
		cellNum = 0;

		for (Int076Vo detail : dataList) {
			row = sheet.createRow(rowNum);
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getDatePosted());
			if ("1".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor1);
			} else if ("2".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor2);
			} else if ("3".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor3);
			} else if ("4".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor4);
			} else if ("5".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor5);
			} else if ("6".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor6);
			} else if ("7".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor7);
			} else if ("8".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor8);
			} else if ("9".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor9);
			} else if ("10".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor10);
			} else if ("11".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor11);
			} else if ("12".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor12);
			}
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getDocNumber());
			if ("1".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor1);
			} else if ("2".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor2);
			} else if ("3".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor3);
			} else if ("4".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor4);
			} else if ("5".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor5);
			} else if ("6".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor6);
			} else if ("7".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor7);
			} else if ("8".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor8);
			} else if ("9".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor9);
			} else if ("10".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor10);
			} else if ("11".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor11);
			} else if ("12".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor12);
			}
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getDocType());
			if ("1".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor1);
			} else if ("2".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor2);
			} else if ("3".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor3);
			} else if ("4".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor4);
			} else if ("5".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor5);
			} else if ("6".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor6);
			} else if ("7".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor7);
			} else if ("8".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor8);
			} else if ("9".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor9);
			} else if ("10".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor10);
			} else if ("11".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor11);
			} else if ("12".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor12);
			}
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getDocRefer());
			if ("1".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor1);
			} else if ("2".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor2);
			} else if ("3".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor3);
			} else if ("4".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor4);
			} else if ("5".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor5);
			} else if ("6".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor6);
			} else if ("7".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor7);
			} else if ("8".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor8);
			} else if ("9".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor9);
			} else if ("10".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor10);
			} else if ("11".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor11);
			} else if ("12".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor12);
			}
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getActor());
			if ("1".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor1);
			} else if ("2".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor2);
			} else if ("3".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor3);
			} else if ("4".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor4);
			} else if ("5".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor5);
			} else if ("6".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor6);
			} else if ("7".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor7);
			} else if ("8".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor8);
			} else if ("9".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor9);
			} else if ("10".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor10);
			} else if ("11".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor11);
			} else if ("12".equals(detail.getColor())) {
				cell.setCellStyle(LeftColor12);
			}
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getDetermination());
			if ("1".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor1);
			} else if ("2".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor2);
			} else if ("3".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor3);
			} else if ("4".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor4);
			} else if ("5".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor5);
			} else if ("6".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor6);
			} else if ("7".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor7);
			} else if ("8".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor8);
			} else if ("9".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor9);
			} else if ("10".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor10);
			} else if ("11".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor11);
			} else if ("12".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor12);
			}
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getPayUnit());
			if ("1".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor1);
			} else if ("2".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor2);
			} else if ("3".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor3);
			} else if ("4".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor4);
			} else if ("5".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor5);
			} else if ("6".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor6);
			} else if ("7".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor7);
			} else if ("8".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor8);
			} else if ("9".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor9);
			} else if ("10".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor10);
			} else if ("11".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor11);
			} else if ("12".equals(detail.getColor())) {
				cell.setCellStyle(CenterColor12);
			}
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(formatter.format(detail.getDebit()));
			if ("1".equals(detail.getColor())) {
				cell.setCellStyle(RightColor1);
			} else if ("2".equals(detail.getColor())) {
				cell.setCellStyle(RightColor2);
			} else if ("3".equals(detail.getColor())) {
				cell.setCellStyle(RightColor3);
			} else if ("4".equals(detail.getColor())) {
				cell.setCellStyle(RightColor4);
			} else if ("5".equals(detail.getColor())) {
				cell.setCellStyle(RightColor5);
			} else if ("6".equals(detail.getColor())) {
				cell.setCellStyle(RightColor6);
			} else if ("7".equals(detail.getColor())) {
				cell.setCellStyle(RightColor7);
			} else if ("8".equals(detail.getColor())) {
				cell.setCellStyle(RightColor8);
			} else if ("9".equals(detail.getColor())) {
				cell.setCellStyle(RightColor9);
			} else if ("10".equals(detail.getColor())) {
				cell.setCellStyle(RightColor10);
			} else if ("11".equals(detail.getColor())) {
				cell.setCellStyle(RightColor11);
			} else if ("12".equals(detail.getColor())) {
				cell.setCellStyle(RightColor12);
			}
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(formatter.format(detail.getCredit()));
			if ("1".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor1);
			} else if ("2".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor2);
			} else if ("3".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor3);
			} else if ("4".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor4);
			} else if ("5".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor5);
			} else if ("6".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor6);
			} else if ("7".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor7);
			} else if ("8".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor8);
			} else if ("9".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor9);
			} else if ("10".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor10);
			} else if ("11".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor11);
			} else if ("12".equals(detail.getColor()) && StringUtils.isBlank(detail.getCheckData())) {
				cell.setCellStyle(RightColor12);
			} else if ("N".equals(detail.getCheckData())) {
				cell.setCellStyle(RightColorError);
			}

			cellNum++;

			cell = row.createCell(cellNum);

			if (detail.getLiftUp() != null) {
				cell.setCellValue(formatter.format(detail.getLiftUp()));
			} else {
				cell.setCellValue("");
			}
			if ("1".equals(detail.getColor())) {
				cell.setCellStyle(RightColor1);
			} else if ("2".equals(detail.getColor())) {
				cell.setCellStyle(RightColor2);
			} else if ("3".equals(detail.getColor())) {
				cell.setCellStyle(RightColor3);
			} else if ("4".equals(detail.getColor())) {
				cell.setCellStyle(RightColor4);
			} else if ("5".equals(detail.getColor())) {
				cell.setCellStyle(RightColor5);
			} else if ("6".equals(detail.getColor())) {
				cell.setCellStyle(RightColor6);
			} else if ("7".equals(detail.getColor())) {
				cell.setCellStyle(RightColor7);
			} else if ("8".equals(detail.getColor())) {
				cell.setCellStyle(RightColor8);
			} else if ("9".equals(detail.getColor())) {
				cell.setCellStyle(RightColor9);
			} else if ("10".equals(detail.getColor())) {
				cell.setCellStyle(RightColor10);
			} else if ("11".equals(detail.getColor())) {
				cell.setCellStyle(RightColor11);
			} else if ("12".equals(detail.getColor())) {
				cell.setCellStyle(RightColor12);
			}
			cellNum++;

			rowNum++;
			cellNum = 0;

		}

		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}

}

package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.LeftInStockServiceVo;
import th.go.excise.ims.ta.vo.MemberStatusServiceVo;
import th.go.excise.ims.ta.vo.PriceServiceVo;
import th.go.excise.ims.ta.vo.QuantityServiceVo;

@Service
public class CreatePaperServiceService {
	private static final Logger logger = LoggerFactory.getLogger(CreatePaperServiceService.class);
	public static final String START_DATA = "บัญชีเงินฝาก : 10778 ภาษีบำรุงองค์กรปกครองส่วนท้องถิ่นเพื่อรอจัดสรร";
	public static final String END_DATA = "รายงานแสดงการเคลื่อนไหวเงินฝากกระทรวงการคลัง";
	public static final String END_SHEET = "***** รวมบัญชีเงินฝาก : 10778 ภาษีบำรุงองค์กรปกครองส่วนท้องถิ่นเพื่อรอ";

	
	//TODO
	public DataTableAjax<QuantityServiceVo> GetQuantityServiceVo(CreatePaperFormVo request) {
		 int total = 45;
		DataTableAjax<QuantityServiceVo> dataTableAjax = new DataTableAjax<QuantityServiceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listQuantityServiceVo(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}
	public List<QuantityServiceVo> listQuantityServiceVo(int start,int length,int total) {

		List<QuantityServiceVo> datalist = new ArrayList<QuantityServiceVo>();
		String excise = "กรมสรรพสามิตภาคที่";
		QuantityServiceVo data = null;
		for (int i = start; i<(start+length); i++) {
			if(i >= total){
				break;
			}
			data = new QuantityServiceVo();
			data.setList(excise+i);
			data.setServiceSlip("1500.00");
			data.setDailyAccount("3000.00");
			data.setPaymentSlip("4000.00");
			data.setFromExamination("5000.00");
			data.setTaxForm("6000.00");
			data.setDifference("7000.00");
			datalist.add(data);
		}
		return datalist;
	}
	public byte[] exportFileQuantityServiceVo() throws IOException {
		
		List<QuantityServiceVo> dataListexportFile = new ArrayList<QuantityServiceVo>();
		dataListexportFile = listQuantityServiceVo(0, 45, 45);
		 logger.info("Data list exportFileQuantityServiceVo {} row",dataListexportFile.size());
		 
			XSSFWorkbook workbook = new XSSFWorkbook();
	
			
			 /* call style from utils */
			  CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
			  CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
			  CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
			  CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
			  
			Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบด้านปริมาณ");
			int rowNum = 0;
			int cellNum = 0;
			
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			String[] tbTH1 = { "ลำดับ", "รายการ", "ใบกำกับภาษีซื้อ",
					"บัญชีประจำวัน ภส. ๐๗-๐๑", "งบเดือน (ภส. ๐๗-๐๔)","ข้อมูลจากภายนอก	","ผลต่างสูงสุด" };
			
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};
			
			rowNum++;
			cellNum = 0;
			int order = 1;
			for (QuantityServiceVo detail : dataListexportFile) {
				row = sheet.createRow(rowNum);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getList()))?detail.getList(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getServiceSlip()))?detail.getServiceSlip(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getDailyAccount()))?detail.getDailyAccount(): "" );
				
			
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getFromExamination()))?detail.getFromExamination(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getTaxForm()))?detail.getTaxForm(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getDifference()))?detail.getDifference(): "" );
				
				rowNum++;
				cellNum = 0;
			}
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, 10 * 256);
			sheet.setColumnWidth(colIndex++, 38 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			sheet.setColumnWidth(colIndex++, 55 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			sheet.setColumnWidth(colIndex++,25 * 256);
			sheet.setColumnWidth(colIndex++, 30 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			/*set	fileName*/		
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			byte [] cont = null;
		    workbook.write(outByteStream);
		    cont = outByteStream.toByteArray();
			return cont;
		
		}
	


//
//	public List<QuantityServiceVo> readFileExcelOPT(QuantityServiceVo formVo)
//			throws EncryptedDocumentException, InvalidFormatException, IOException {
//
//		List<QuantityServiceVo> optList = new ArrayList<>();
//
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		logger.info("readFileExcelOPT");
//		System.out.println(formVo.getFileExel().getOriginalFilename());
//		System.out.println(formVo.getFileExel().getContentType());
//		byte[] byt = formVo.getFileExel().getBytes();
//
//		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
//		Sheet sheet = workbook.getSheetAt(0);
//		int totalRows = sheet.getLastRowNum();
//
//		int tmpRow = 0;
//		boolean header = false;
//
//		/* loop check header */
//		for (int r = 0; r <= totalRows; r++) {
//			Row row = sheet.getRow(r);
//			/* check header */
//			if (row != null) {
//				short startCellHeader = row.getFirstCellNum();
//				Cell cellHeader = row.getCell(startCellHeader);
//
//				if (START_DATA.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellHeader)))) {
//					header = true;
//					tmpRow = r;
//					break;
//				}
//			}
//
//		}
//		/* loop check Check Data */
//		if (header) {
//			tmpRow++; // start next one row
//			/* loop row data */
//			for (int rowDetail = tmpRow; rowDetail <= totalRows; rowDetail++) {
//				Row rowData = sheet.getRow(rowDetail);
//
//				boolean endData = false;
//				boolean endSheet = false;
//				List<String> columns = new ArrayList<>();
//
//				if (rowData != null) {
//					/* loop column data */
//					for (short col = 1; col < 19; col++) {
//						Cell cell = rowData.getCell(col);
//
//						/* cellCheck is read cell !null */
//						short startData = rowData.getFirstCellNum();
//						Cell cellCheck = rowData.getCell(startData);
//
//						endData = (END_DATA.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellCheck))) ? true
//								: false);
//						endSheet = (END_SHEET.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellCheck)))
//								? true
//								: false);
//						/* END_DATA */
//						if (endData) {
//							rowDetail += 10;
//							break;
//							/* END_SHEET */
//						} else if (endSheet) {
//							break;
//							/* READ_DATA */
//						} else {
///*							String value = ExcelUtils.getCellValueAsString(cell);
//							System.out.println(value + " col::" + col + " row::" + rowDetail);*/
//
//							if (StringUtils.isNotBlank(ExcelUtils.getCellValueAsString(cell))) {
//								map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
//								columns.add(ExcelUtils.getCellValueAsString(cell));
//							} else {
//								columns.add("");
//							}
//
//						}
//
//					}
//					addData(optList, columns, rowDetail);
//				}
//				if (endSheet) {
//					break;
//				}
//			}
//		}
//
//		return optList;
//	}
////
//	public void addData(List<QuantityServiceVo> optList, List<String> data, int rowId) {
//
//		boolean checkData;
//		checkData = (data.isEmpty() ? false : true);
//
//		if (checkData) {
//			QuantityServiceVo vo = new QuantityServiceVo();
//
//			try {
//
//				vo.setId(new Long(rowId));
//
//				if (StringUtils.isNotBlank(data.get(0))) {
//					vo.setDatePosted(StringUtils.trim(data.get(0)));
//
//					if (vo.getDatePosted().indexOf(".01.") != -1) {
//						vo.setColor("1");
//					} else if (vo.getDatePosted().indexOf(".02.") != -1) {
//						vo.setColor("2");
//					} else if (vo.getDatePosted().indexOf(".03.") != -1) {
//						vo.setColor("3");
//					} else if (vo.getDatePosted().indexOf(".04.") != -1) {
//						vo.setColor("4");
//					} else if (vo.getDatePosted().indexOf(".05.") != -1) {
//						vo.setColor("5");
//					} else if (vo.getDatePosted().indexOf(".06.") != -1) {
//						vo.setColor("6");
//					} else if (vo.getDatePosted().indexOf(".07.") != -1) {
//						vo.setColor("7");
//					} else if (vo.getDatePosted().indexOf(".08.") != -1) {
//						vo.setColor("8");
//					} else if (vo.getDatePosted().indexOf(".09.") != -1) {
//						vo.setColor("9");
//					} else if (vo.getDatePosted().indexOf(".10.") != -1) {
//						vo.setColor("10");
//					} else if (vo.getDatePosted().indexOf(".11.") != -1) {
//						vo.setColor("11");
//					} else if (vo.getDatePosted().indexOf(".12.") != -1) {
//						vo.setColor("12");
//					}
//
//				} else {
//					vo.setDatePosted(tmpDatePosted);
//
//					if (vo.getDatePosted().indexOf(".01.") != -1) {
//						vo.setColor("1");
//					} else if (vo.getDatePosted().indexOf(".02.") != -1) {
//						vo.setColor("2");
//					} else if (vo.getDatePosted().indexOf(".03.") != -1) {
//						vo.setColor("3");
//					} else if (vo.getDatePosted().indexOf(".04.") != -1) {
//						vo.setColor("4");
//					} else if (vo.getDatePosted().indexOf(".05.") != -1) {
//						vo.setColor("5");
//					} else if (vo.getDatePosted().indexOf(".06.") != -1) {
//						vo.setColor("6");
//					} else if (vo.getDatePosted().indexOf(".07.") != -1) {
//						vo.setColor("7");
//					} else if (vo.getDatePosted().indexOf(".08.") != -1) {
//						vo.setColor("8");
//					} else if (vo.getDatePosted().indexOf(".09.") != -1) {
//						vo.setColor("9");
//					} else if (vo.getDatePosted().indexOf(".10.") != -1) {
//						vo.setColor("10");
//					} else if (vo.getDatePosted().indexOf(".11.") != -1) {
//						vo.setColor("11");
//					} else if (vo.getDatePosted().indexOf(".12.") != -1) {
//						vo.setColor("12");
//					}
//				}
//				// set temp date
//				if (StringUtils.isNotBlank(data.get(0))) {
//					tmpDatePosted = data.get(0);
//				}
//				// DocNumber
//				if (StringUtils.isNotBlank(data.get(3))) {
//					vo.setDocNumber(StringUtils.trim(data.get(3)));
//				} else {
//					vo.setDocNumber("");
//				}
//				// DocType
//				if (StringUtils.isNotBlank(data.get(5))) {
//					vo.setDocType(StringUtils.trim(data.get(5)));
//				} else {
//					vo.setDocType("");
//				}
//				// DocRefer
//				if (StringUtils.isNotBlank(data.get(6))) {
//					vo.setDocRefer(StringUtils.trim(data.get(6)));
//				} else {
//					vo.setDocRefer("");
//				}
//				// Actor
//				if (StringUtils.isNotBlank(data.get(7))) {
//					vo.setActor(StringUtils.trim(data.get(7)));
//				} else {
//					vo.setActor("");
//				}
//				// Determination
//				if (StringUtils.isNotBlank(data.get(10))) {
//					vo.setDetermination(StringUtils.trim(data.get(10)));
//				} else {
//					vo.setDetermination("");
//				}
//				// PayUnit
//				if (StringUtils.isNotBlank(data.get(13))) {
//					vo.setPayUnit(StringUtils.trim(data.get(13)));
//				} else {
//					vo.setPayUnit("");
//				}
//				// Debit
//				if (StringUtils.isNotBlank(data.get(14))) {
//					vo.setDebit(new BigDecimal(data.get(14)));
//				} else {
//					vo.setDebit(BigDecimal.ZERO);
//				}
//				// Credit
//				if (StringUtils.isNotBlank(data.get(15))) {
//					vo.setCredit(new BigDecimal(data.get(15)));
//				} else {
//					vo.setCredit(BigDecimal.ZERO);
//				}
//
//				if (StringUtils.isNotBlank(data.get(17))) {
//					vo.setLiftUp(new BigDecimal(data.get(17)));
//				} else {
//					vo.setLiftUp(new BigDecimal(""));
//				}
//
//				optList.add(vo);
//			} catch (Exception e) {
//				optList.add(vo);
//
//			}
//		}
//
//	}
//
//	public List<QuantityServiceVo> checkData(List<QuantityServiceVo> dataList) {
//
//		int monJ0;
//		int yearJ0;
//		String dateJ0 = "";
//
//		ArrayList<BigDecimal> liftUpList = new ArrayList<BigDecimal>();
//
//		for (QuantityServiceVo dataJ0 : dataList) {
//
//			/* check J0 */
//			if ("J0".equals(dataJ0.getDocType())) {
//				/* check dateJ0 */
//				if ("01".equals(dataJ0.getDatePosted().substring(3, 5))) {
//					monJ0 = 12;
//					yearJ0 = Integer.parseInt(dataJ0.getDatePosted().substring(6, 10));
//					yearJ0--;
//					dateJ0 = monJ0 + "." + yearJ0;
//				} else {
//					monJ0 = Integer.parseInt(dataJ0.getDatePosted().substring(3, 5));
//					monJ0--;
//					yearJ0 = Integer.parseInt(dataJ0.getDatePosted().substring(6, 10));
//					yearJ0--;
//					if (monJ0 == 10 || monJ0 == 11) {
//						dateJ0 = monJ0 + "." + yearJ0;
//					} else {
//						dateJ0 = "0" + monJ0 + "." + yearJ0;
//					}
//
//				}
//				/* check IX */
//				for (QuantityServiceVo dataIX : dataList) {
//					/* check dateIX */
//					if ("IX".equals(dataIX.getDocType()) && dateJ0.equals(dataIX.getDatePosted().substring(3, 10))
//							&& BeanUtils.isNotEmpty(dataIX.getLiftUp())) {
//
//						liftUpList.add(dataIX.getLiftUp());
//
//					}
//
//				}
//				/* check liftUp IX == Credit :: CheckData=Y */
//				if (BeanUtils.isNotEmpty(liftUpList)
//						&& liftUpList.get(liftUpList.size() - 1).equals(dataJ0.getCredit())) {
//					dataJ0.setCheckData("Y");
//				} else {
//					dataJ0.setCheckData("N");
//				}
//
//				liftUpList = new ArrayList<BigDecimal>();
//
//			}
//
//		}
//
//		return dataList;
//	}
//	
//	
	
	// MethodPriceServiceVo
	
	public DataTableAjax<PriceServiceVo> GetPriceServiceVo(CreatePaperFormVo request) {
		int total = 35;
		DataTableAjax<PriceServiceVo> dataTableAjax = new DataTableAjax<PriceServiceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listPriceServiceVo(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PriceServiceVo> listPriceServiceVo(int start,int length, int total) {
		String excise = "กรมสรรพสามิตภาคที่";
		List<PriceServiceVo> datalist = new ArrayList<PriceServiceVo>();

		PriceServiceVo data = null;
		for (int i = start;i<(start+length);i++) {
			if(i >= total){
				break;
			}
			data = new PriceServiceVo();
			data.setList(excise+i);
			data.setTaxInvoicePrice("3000.00");
			data.setNotificationService("2300.00");
			data.setFromExamination("6500.00");
			data.setTaxFilingPrice("7500.00");
			data.setDifference("8500.00");
			datalist.add(data);

		}
		return datalist;
	}
	public byte [] exportFilePriceServiceVo( ) throws IOException {
		
		List<PriceServiceVo> dataListexportFile = new ArrayList<PriceServiceVo>();
		dataListexportFile = listPriceServiceVo(0, 35, 35);
		 logger.info("Data list exportFilePriceServiceVo {} row",dataListexportFile.size());
			XSSFWorkbook workbook = new XSSFWorkbook();
			
		
			 /* call style from utils */
			  CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
			  CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
			  CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
			  CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
			  
			Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบด้านราคาต่อหน่วย");
			int rowNum = 0;
			int cellNum = 0;
			
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			String[] tbTH1 = { "ลำดับ", "รายการ", "ราคาตามใบกำกับภาษี", "ราคาบริการตามแบบแจ้ง",
					"จากการตรวจสอบ", "ราคาที่ยื่นชำระภาษี","ผลต่าง" };
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, 10 * 256);
			sheet.setColumnWidth(colIndex++, 38 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			sheet.setColumnWidth(colIndex++,25 * 256);
			sheet.setColumnWidth(colIndex++, 30 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			row = sheet.createRow(rowNum);
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};
			rowNum++;
			cellNum = 0;
			int order = 1;
			for (PriceServiceVo detail : dataListexportFile) {
				row = sheet.createRow(rowNum);
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getList()))?detail.getList(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getTaxInvoicePrice()))?detail.getTaxInvoicePrice(): "" );
			
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getNotificationService()))?detail.getNotificationService(): "" );
			
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getFromExamination()))?detail.getFromExamination(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getTaxFilingPrice()))?detail.getTaxFilingPrice(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getDifference()))?detail.getDifference(): "" );
				
				rowNum++;
				cellNum = 0;
			}
		
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			byte [] cont = null;
		    workbook.write(outByteStream);
		    cont = outByteStream.toByteArray();
			return cont;
		}
	//Done
	
	
	//methodMemberStatusServiceVo
	public DataTableAjax<MemberStatusServiceVo> GetMemberStatusServiceVo(CreatePaperFormVo request) {
		int total = 35;
		DataTableAjax<MemberStatusServiceVo> dataTableAjax = new DataTableAjax<MemberStatusServiceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listMemberStatusServiceVo(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<MemberStatusServiceVo> listMemberStatusServiceVo(int start,int length,int total) {
		String code = "1012520";
		List<MemberStatusServiceVo> datalist = new ArrayList<MemberStatusServiceVo>();
		MemberStatusServiceVo data = null;
		for(int i = start; i <(start+length);i++){
			if(i >= total){
				break;
			}
			data = new MemberStatusServiceVo();
			data.setMemberCode(code+i);
			data.setSurname("ธนพล ชัยภูมิ");
			data.setStartDate("15/02/2561");
			data.setExpiredDate("16/06/2563");
			data.setCoupon("05264");
			data.setServiceDate("01/05/2563");
			data.setMembershipStatus("VIP");
			datalist.add(data);
		}
		

		return datalist;
	}
	public byte[] exportFileMemberStatusServiceVo() throws IOException {
		
		List<MemberStatusServiceVo> dataListexportFile = new ArrayList<MemberStatusServiceVo>();
		dataListexportFile = listMemberStatusServiceVo(0, 35, 35);
		 logger.info("Data list exportFilePriceServiceVo {} row",dataListexportFile.size());
		 
			XSSFWorkbook workbook = new XSSFWorkbook();
			
		
			 /* call style from utils */
			  CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
			  CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
			  CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
			  CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
			  
			Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบสถานะสมาชิก");
			int rowNum = 0;
			int cellNum = 0;
			
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			String[] tbTH1 = { "ลำดับ", "รหัสสมาชิก", "ชื่อ-สกุล", "วันเริ่มต้น",
					"วันหมดอายุ	", "คูปอง","วันที่ใช้บริการ","สถานะการเป็นสมาชิก" };
			row = sheet.createRow(rowNum);
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, 10 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++,25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			
			
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};
			rowNum++;
			cellNum = 0;
			int order = 1;
			for (MemberStatusServiceVo detail : dataListexportFile) {
				row = sheet.createRow(rowNum);
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMemberCode()))?detail.getMemberCode(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getSurname()))?detail.getSurname(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getStartDate()))?detail.getStartDate(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getExpiredDate()))?detail.getExpiredDate(): "" );
				

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getCoupon()))?detail.getCoupon(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getServiceDate()))?detail.getServiceDate(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMembershipStatus()))?detail.getMembershipStatus(): "" );
				
				rowNum++;
				cellNum = 0;
			}
			

			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			byte [] cont = null;
		    workbook.write(outByteStream);
		    cont = outByteStream.toByteArray();
			return cont;
		}
	

	public DataTableAjax<LeftInStockServiceVo> GetLeftInStockServiceVo( CreatePaperFormVo request) {
		int total = 35;
		DataTableAjax<LeftInStockServiceVo> dataTableAjax = new DataTableAjax<LeftInStockServiceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listLeftInStockServiceVo(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<LeftInStockServiceVo> listLeftInStockServiceVo(int start,int length,int total) {
		String excise = "กรมสรรพสามิตภาคที่";
		
		List<LeftInStockServiceVo> datalist = new ArrayList<LeftInStockServiceVo>();
		LeftInStockServiceVo data = null;
		for(int i = start;i<(start+length);i++){
			if(i >= total){
				break;
			}
			data = new LeftInStockServiceVo();
			data.setList(excise+i);
			data.setAccountBalance("30000.00");
			data.setInventoryBalance("25000.00");
			data.setDifference("3000.00");
			datalist.add(data);
		}
		

		return datalist;
	}
	public byte[] exportFileLeftInStockServiceVo() throws IOException {
		
		List<LeftInStockServiceVo> dataListexportFile = new ArrayList<LeftInStockServiceVo>();
		dataListexportFile = listLeftInStockServiceVo(0, 35, 35);
		 logger.info("Data list exportFilePriceServiceVo {} row",dataListexportFile.size());
		 
			XSSFWorkbook workbook = new XSSFWorkbook();
		
			  CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
			  CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
			  CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
			  CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
			  
			Sheet sheet = workbook.createSheet("บันทึกผลการตรวจนับสินค้าคงเหลือ");
			int rowNum = 0;
			int cellNum = 0;

			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			String[] tbTH1 = { "ลำดับ", "รายการ", "ยอดคงเหลือตามบัญชี", "ยอดสินค้าคงเหลือจากการตรวจนับ",
					"ผลต่าง"};
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, 10 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 35 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			
			row = sheet.createRow(rowNum);
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};
			rowNum++;
			cellNum = 0;
			int order = 1;
			for (LeftInStockServiceVo detail : dataListexportFile) {
				row = sheet.createRow(rowNum);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getList()))?detail.getList(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getAccountBalance()))?detail.getAccountBalance(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getInventoryBalance()))?detail.getInventoryBalance(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getDifference()))?detail.getDifference(): "" );
				
				rowNum++;
				cellNum = 0;
			}
	
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			byte [] cont = null;
		    workbook.write(outByteStream);
		    cont = outByteStream.toByteArray();
			return cont;
		}
	

	

}
	
	
	
	



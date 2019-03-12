package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperQtyVo;

@Service
public class ServicePaperQtyService {
	private static final Logger logger = LoggerFactory.getLogger(ServicePaperQtyService.class);
	
	public DataTableAjax<ServicePaperQtyVo> GetQuantityServiceVo(CreatePaperFormVo request) {
		 int total = 45;
		DataTableAjax<ServicePaperQtyVo> dataTableAjax = new DataTableAjax<ServicePaperQtyVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listQuantityServiceVo(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}
	public List<ServicePaperQtyVo> listQuantityServiceVo(int start,int length,int total) {

		List<ServicePaperQtyVo> datalist = new ArrayList<ServicePaperQtyVo>();
		String excise = "กรมสรรพสามิตภาคที่";
		ServicePaperQtyVo data = null;
		for (int i = start; i<(start+length); i++) {
			if(i >= total){
				break;
			}
			data = new ServicePaperQtyVo();
			data.setGoodsDesc(excise+i);
			data.setServiceDocNo("1500.00");
			data.setIncomeDailyAccountAmt("3000.00");
			data.setPaymentDocNo("4000.00");
			data.setAuditAmt("5000.00");
			data.setTaxAmt("6000.00");
			data.setDiffAmt("7000.00");
			datalist.add(data);
		}
		return datalist;
	}
	public byte[] exportFileQuantityServiceVo() throws IOException {
		
		List<ServicePaperQtyVo> dataListexportFile = new ArrayList<ServicePaperQtyVo>();
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
			for (ServicePaperQtyVo detail : dataListexportFile) {
				row = sheet.createRow(rowNum);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsDesc()))?detail.getGoodsDesc(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getServiceDocNo()))?detail.getServiceDocNo(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getIncomeDailyAccountAmt()))?detail.getIncomeDailyAccountAmt(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getPaymentDocNo()))?detail.getPaymentDocNo(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getAuditAmt()))?detail.getAuditAmt(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getTaxAmt()))?detail.getTaxAmt(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getDiffAmt()))?detail.getDiffAmt(): "" );
				
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
	 public List<ServicePaperQtyVo> readFileQuantityServiceVo(ServicePaperQtyVo request) {
		  logger.info("readFileQuantityServiceVo");
		  logger.info("fileName "+request.getFile().getOriginalFilename());
		  logger.info("type "+request.getFile().getContentType());
		  List<ServicePaperQtyVo> dataList = new ArrayList<>();
		  
		  try(Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));){
				Sheet sheet = workbook.getSheetAt(0);
				
				   for (Row row : sheet) {
					   ServicePaperQtyVo pushdata = new ServicePaperQtyVo();
					    // Skip on first row
					    if (row.getRowNum() == 0) {
					     continue;
					    } 
					    for (Cell cell : row) {
					     if (cell.getColumnIndex() == 0) {
					      // Column No.
					    	 continue;
					     } else if (cell.getColumnIndex() == 1) {
					    	 pushdata.setGoodsDesc(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex()== 2){
					    	 pushdata.setServiceDocNo(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex()== 3){
					    	 pushdata.setIncomeDailyAccountAmt(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex() == 4 ){
					    	 pushdata.setPaymentDocNo(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex() == 5){
					    	 pushdata.setAuditAmt(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex() == 6){
					    	 pushdata.setTaxAmt(ExcelUtils.getCellValueAsString(cell));
					     }else if (cell.getColumnIndex() == 7){
					    	 pushdata.setDiffAmt(ExcelUtils.getCellValueAsString(cell));
					     }
					     
					    }
						   dataList.add(pushdata);
					   }
			
				 
		  }catch(Exception e){
			  logger.error(e.getMessage(),e);
		  }
		  return dataList;
		 }



}

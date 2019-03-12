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
import th.go.excise.ims.ta.vo.ServicePaperMemberVo;
import th.go.excise.ims.ta.vo.ServicePaperPricePerUnitVo;

@Service
public class ServicePaperMemberService {
	private static final Logger logger = LoggerFactory.getLogger(ServicePaperMemberService.class);
	
	public DataTableAjax<ServicePaperMemberVo> GetMemberStatusServiceVo(CreatePaperFormVo request) {
		int total = 35;
		DataTableAjax<ServicePaperMemberVo> dataTableAjax = new DataTableAjax<ServicePaperMemberVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listMemberStatusServiceVo(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<ServicePaperMemberVo> listMemberStatusServiceVo(int start,int length,int total) {
		String code = "1012520";
		List<ServicePaperMemberVo> datalist = new ArrayList<ServicePaperMemberVo>();
		ServicePaperMemberVo data = null;
		for(int i = start; i <(start+length);i++){
			if(i >= total){
				break;
			}
			data = new ServicePaperMemberVo();
			data.setMemberCode(code+i);
			data.setMemberFullName("ธนพล ชัยภูมิ");
			data.setMemberStartDate("15/02/2561");
			data.setMemberEndDate("16/06/2563");
			data.setMemberCoupon("05264");
			data.setMemberUsedDate("01/05/2563");
			data.setMemberStatus("VIP");
			datalist.add(data);
		}
		

		return datalist;
	}
	public byte[] exportFileMemberStatusServiceVo() throws IOException {
		
		List<ServicePaperMemberVo> dataListexportFile = new ArrayList<ServicePaperMemberVo>();
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
			for (ServicePaperMemberVo detail : dataListexportFile) {
				row = sheet.createRow(rowNum);
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMemberCode()))?detail.getMemberCode(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMemberFullName()))?detail.getMemberFullName(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMemberStartDate()))?detail.getMemberStartDate(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMemberEndDate()))?detail.getMemberEndDate(): "" );
				

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMemberCoupon()))?detail.getMemberCoupon(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMemberUsedDate()))?detail.getMemberUsedDate(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMemberStatus()))?detail.getMemberStatus(): "" );
				
				rowNum++;
				cellNum = 0;
			}
			

			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			byte [] cont = null;
		    workbook.write(outByteStream);
		    cont = outByteStream.toByteArray();
			return cont;
		}
	
	 public List<ServicePaperMemberVo> readFileServicePaperMemberVo(ServicePaperMemberVo request) {
		  logger.info("readFileServicePaperMemberVo");
		  logger.info("fileName "+request.getFile().getOriginalFilename());
		  logger.info("type "+request.getFile().getContentType());
		  List<ServicePaperMemberVo> dataList = new ArrayList<>();
		  
		  try(Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));){
				Sheet sheet = workbook.getSheetAt(0);
				
				   for (Row row : sheet) {
					   ServicePaperMemberVo pushdata = new ServicePaperMemberVo();
					    // Skip on first row
					    if (row.getRowNum() == 0) {
					     continue;
					    } 
					    for (Cell cell : row) {
					     if (cell.getColumnIndex() == 0) {
					      // Column No.
					    	 continue;
					     } else if (cell.getColumnIndex() == 1) {
					    	 pushdata.setMemberCode(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex()== 2){
					    	 pushdata.setMemberFullName(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex()== 3){
					    	 pushdata.setMemberStartDate(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex() == 4 ){
					    	 pushdata.setMemberEndDate(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex() == 5){
					    	 pushdata.setMemberCoupon(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex() == 6){
					    	 pushdata.setMemberUsedDate(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex() == 6){
					    	 pushdata.setMemberStatus(ExcelUtils.getCellValueAsString(cell));
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

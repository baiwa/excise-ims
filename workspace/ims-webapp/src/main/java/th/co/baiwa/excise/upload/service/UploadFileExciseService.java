package th.co.baiwa.excise.upload.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import th.co.baiwa.excise.domain.form.FormUpload;
import th.co.baiwa.excise.ta.service.ExciseDetailService;

@Service
//public class UploadFileExciseService {
//
//	private Logger logger = LoggerFactory.getLogger(ExciseDetailService.class);
//
//	public List<Object> readFileExcel(FormUpload multipartFile)
//			throws IOException, EncryptedDocumentException, InvalidFormatException {
//		logger.info("UploadFileExciseService.readFileExcel");
//
//		if ( multipartFile.getFileExel() != null) {
//			byte[] byt =  multipartFile.getFileExel().getBytes();
//			Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
//			// Get Sheet at index 0
//			Sheet sheet = workbook.getSheetAt(0);
//
//			List<Object> res = new ArrayList<Object>();
//			List<Object> li = new ArrayList<Object>(); 
//			Row row;
//			Cell cell;
//
//			for (int i = 0; i <= 6; i++) {
//				row = sheet.getRow(i);
//				li = new ArrayList<Object>();
//				for (int j = 2; j <= 5; j++) {
//					if(j != 4) {
//						cell = row.getCell(j);
//						li.add(cell.toString());
//					}
//				}
//				res.add(li);
//			}
//			
//			return res;
//		}
//
//		return null;
//	}
//
//}
public class UploadFileExciseService {
	
	private Logger logger = LoggerFactory.getLogger(ExciseDetailService.class);
	
	public List<String[]> readFileExcel(FormUpload multipartFile , List<String> headerNameList)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		logger.info("UploadFileExciseService.readFileExcel");
		Workbook workbook = WorkbookFactory.create(new FileInputStream("C:\\file.xlsx"));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows();

		Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
		Row row = sheet.getRow(0); //Get first row
		//following is boilerplate from the java doc
		short minColIx = row.getFirstCellNum(); //get the first column index for a row
		short maxColIx = row.getLastCellNum(); //get the last column index for a row
		for(short colIx=minColIx; colIx<maxColIx; colIx++) { //loop from first to last index
		Cell cell = row.getCell(colIx); //get the cell
 		map.put(cell.getStringCellValue(),cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
		}
		
		List<String[]> listOfDataFromReport = new ArrayList<String[]>();
		String[] rowData  = null;
		for(int x = 1; x<=totalRows; x++){
			FormUpload rr = new FormUpload(); //Data structure to hold the data from the xls file.
		 Row dataRow = sheet.getRow(x); //get row 1 to row n (rows containing data)
		 rowData = new String[headerNameList.size()];
		 if(dataRow != null) {
			 for(String data : headerNameList) {
				 int idn = map.get(data);
				 Cell cell = dataRow.getCell(idn); 
				 rowData[idn] = getCellValue(cell);
			 }
		 }
		 listOfDataFromReport.add(rowData);  
		}
		
		//Now you have a list of report rows
		for(int j = 0; j< listOfDataFromReport.size();j++){
		   System.out.println("column "+j+1+" Value: " +   listOfDataFromReport.get(j));
		//etc...    
		}
		return listOfDataFromReport;
	}
	
	private String getCellValue(Cell cell) {
		try {
			return cell.getStringCellValue();
		} catch (Exception e) {
			try {
				return cell.getNumericCellValue()+"";
			} catch (Exception ex) {
				
			}
		}
		return "";
	}
	
}

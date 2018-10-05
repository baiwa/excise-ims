package th.co.baiwa.excise.upload.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.util.ExcelUtils;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0611ExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0611FormVo;
import th.co.baiwa.excise.ta.service.ExciseDetailService;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class UploadFileExciseService {

	private Logger logger = LoggerFactory.getLogger(ExciseDetailService.class);

	public List<String[]> readFileExcel(MultipartFile fileExel) throws IOException, EncryptedDocumentException, InvalidFormatException {
		logger.info("UploadFileExciseService.readFileExcel");
		byte[] byt = fileExel.getBytes();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows();

		Map<String, Integer> map = new HashMap<String, Integer>(); // Create map
		Row row = sheet.getRow(0); // Get first row
		short minColIx = row.getFirstCellNum(); // get the first column index for a row
		short maxColIx = row.getLastCellNum(); // get the last column index for a row
		List<String> headerNameList = new ArrayList<String>();
		for (short colIx = minColIx; colIx < maxColIx; colIx++) { // loop from first to last index
			Cell cell = row.getCell(colIx); // get the cell
			if (BeanUtils.isNotEmpty(cell)) {
				map.put(cell.getStringCellValue(), cell.getColumnIndex()); // add the cell contents (name of column) and
				headerNameList.add(cell.getStringCellValue()); // cell index to the map
			}
		}

		List<String[]> listOfDataFromReport = new ArrayList<String[]>();
		String[] rowData = null;
		for (int x = 0; x < totalRows; x++) {
			Row dataRow = sheet.getRow(x); // get row 1 to row n (rows containing data)
			rowData = new String[headerNameList.size()];
			if (dataRow != null) {
				for (String data : headerNameList) {
					int idn = map.get(data);
					Cell cell = dataRow.getCell(idn);
					rowData[idn] = getCellValue(cell);
				}

			}
			if (rowData[0] != "") {
				listOfDataFromReport.add(rowData);
			}
		}

		return listOfDataFromReport;
	}

	private String getCellValue(Cell cell) {
		try {
			return cell.getStringCellValue();
		} catch (Exception e) {
			try {
				return cell.getNumericCellValue() + "";
			} catch (Exception ex) {

			}
		}
		return "";
	}
	
	
	public List<String[]> readFileExcelEx(MultipartFile fileExel) throws IOException, EncryptedDocumentException, InvalidFormatException {

		List<String[]> excelRowList = new ArrayList<String[]>();
		byte[] byt = fileExel.getBytes();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getLastRowNum();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		/* rows */
		int countHeader = 0;
		for (int r = 0; r < totalRows; r++) {

			Row row = sheet.getRow(r);
			if (row != null) {
				short minColIx = row.getFirstCellNum();
				short maxColIx = row.getLastCellNum();
				
				if (minColIx == 6) {
					countHeader = 0;
				} 
				
				countHeader++;
				boolean header = false;

				header = (minColIx == 6 || minColIx == 0 || minColIx == 2 || countHeader < 6 ?  true :  false);	
				
				if (!header) {
					
					/* column */
					List<String> culumns = new ArrayList<String>();
					for (short colIx = minColIx; colIx < maxColIx; colIx++) {

						Cell cell = row.getCell(colIx);
						if (cell != null) {
							map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
							culumns.add(ExcelUtils.getCellValueAsString(cell));
						}else {
							culumns.add("");
						}
					}
					String[] dat = {};
					excelRowList.add(culumns.toArray(dat));
				}				
			}
		}
		
		
		return excelRowList;
	}
	
	
}

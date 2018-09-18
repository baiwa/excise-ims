package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0611ExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0611FormVo;

@Service
public class Int0611Service {

	
	public DataTableAjax<Int0611ExcelVo> readFileExcel(Int0611FormVo formVo) throws IOException, EncryptedDocumentException, InvalidFormatException {

		List<Int0611ExcelVo> excelVo = new ArrayList<>();
		byte[] byt = formVo.getFileName().getBytes();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows();
		
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

				header = (minColIx == 6 || minColIx == 0 || countHeader < 6 ?  true :  false);	
				
				if (!header) {
					
					/* column */
					List<String> culumns = new ArrayList<String>();
					for (short colIx = minColIx; colIx < maxColIx; colIx++) {

						Cell cell = row.getCell(colIx);
						if (cell != null) {
							map.put(getStringValue(cell), cell.getColumnIndex());
							culumns.add(getStringValue(cell));
						}else {
							culumns.add("");
						}
					}
					
					addData(excelVo,culumns,r);	
				}				
			}
		}
		
		DataTableAjax<Int0611ExcelVo> dataTableAjax = new DataTableAjax<>();		
		
		dataTableAjax.setRecordsTotal(Long.valueOf(excelVo.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(excelVo.size()));
		dataTableAjax.setData(excelVo);
		
		return dataTableAjax;
	}

	public static String getStringValue(Cell cell) {

		if (cell == null)
			return "";

		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			return cell.getBooleanCellValue() ? "1" : "0";
		case FORMULA:
			return cell.getCellFormula();
		case NUMERIC:
			cell.setCellType(CellType.STRING);
			return cell.getStringCellValue();
		case STRING:
			return cell.getStringCellValue();
		default:
			return "";
		}
	}
	
	public void addData(List<Int0611ExcelVo> excelVo, List<String> data,int rowId) {
		
		Int0611ExcelVo vo = new Int0611ExcelVo();
		try {		
			vo.setColumId(rowId);
			vo.setColum0(StringUtils.trim(data.get(0)));
			vo.setColum1(StringUtils.trim(data.get(1)));
			vo.setColum2(StringUtils.trim(data.get(2)));
			vo.setColum3(StringUtils.trim(data.get(3)));
			vo.setColum4(StringUtils.trim(data.get(4)));
			vo.setColum5(StringUtils.trim(data.get(5)));
			vo.setColum6(StringUtils.trim(data.get(6)));
			vo.setColum7(StringUtils.trim(data.get(7)));
			vo.setColum8(StringUtils.trim(data.get(8)));
			vo.setColum9(StringUtils.trim(data.get(9)));
			vo.setColum10(StringUtils.trim(data.get(10)));
			vo.setColum11(StringUtils.trim(data.get(11)));
			
			excelVo.add(vo);
		} catch (Exception e) {
			excelVo.add(vo);
		}
			

	}
}

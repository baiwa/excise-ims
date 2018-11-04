package th.co.baiwa.buckwaframework.common.util.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class ExcelReaderUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ExcelReaderUtils.class);

	
	public static  <T> List<T> read(File f,ExcelRowMapper<T> rowmapper) throws Exception {
		
		logger.info("Read file : {} ", f.getAbsolutePath());
		
		XSSFWorkbook workbook = new XSSFWorkbook(f); // keep 100 rows in memory, exceeding rows will be flushed to disk
		ArrayList<T> res = new ArrayList<>();
		try {
		Sheet sheet = workbook.getSheetAt(0);
		
		int totalRows = sheet.getLastRowNum();
		
		logger.info("Total read row : {}", totalRows);
		
			for (int loop = 0; loop < totalRows ; loop++) {
				Row row = sheet.getRow(loop);
				if(row == null) continue;
	//			Cell cell = row.getCell(1);
	//			CellType t = cell.getCellTypeEnum();
	//			logger.info("Total last cell num : {} , {}", row.getLastCellNum());
				
				ExcelResultSet rs = new ExcelResultSet(row);
				T maprow = rowmapper.mapRow(rs, loop);
//				Assert.notNull(maprow, "method mapRow >> ExcelRowMapper can not return null.");
				if(maprow != null) {
					res.add(maprow);					
				}
	
			}
		
		}finally {
			try {
			workbook.close();	
			}catch (Exception e) {
				// TODO: skip
			}
		}
		
		return res;
		
	}
	
	

}

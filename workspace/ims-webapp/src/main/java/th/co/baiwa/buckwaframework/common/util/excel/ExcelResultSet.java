package th.co.baiwa.buckwaframework.common.util.excel;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExcelResultSet {
	
	
	private static Logger logger = LoggerFactory.getLogger(ExcelResultSet.class);

	private Row row;
	private int size;

	public ExcelResultSet(Row row) {
		this.row = row;
		
		size = row.getLastCellNum();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getString(int columnNumber) {
		Cell cell = this.getCellvalue(columnNumber);
		if(cell == null) return null;
		
		CellType t = cell.getCellTypeEnum();
		if(CellType.STRING == t) {
			return cell.getStringCellValue();			
		}else if ( CellType.NUMERIC == t) {
			DecimalFormat df = new DecimalFormat("#");
			df.setMaximumFractionDigits(0);
			return df.format(cell.getNumericCellValue());
		}else {
			logger.info("CellType : {}", t);
			logger.info("getString Error : INDEX [{}]", columnNumber);
			return null;
		}
	}

	public BigDecimal getNumber(int columnNumber) {

		Cell cell = this.getCellvalue(columnNumber);
		if(cell == null) return null;
		
		CellType t = cell.getCellTypeEnum();
		if(CellType.STRING == t) {
			String c =StringUtils.trimToNull(cell.getStringCellValue());
			if(c == null) {
				return null;
			}
			if(NumberUtils.isDigits(c)) {
				return new BigDecimal(c);
			}else {
				logger.info("CellType : {}", t);
				logger.info("This Cell is not number : {}", c);
				return null;
			}
		}

		return BigDecimal.valueOf(cell.getNumericCellValue());
	}
	
	private Cell getCellvalue(int columnNumber) {
		short lastcellnum = row.getLastCellNum();
		if(columnNumber > lastcellnum) return null;
		
		Cell cell = row.getCell(columnNumber);
		return cell == null ? null : cell;	
		
	}

}

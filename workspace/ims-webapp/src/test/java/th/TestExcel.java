package th;

import java.io.File;

import th.co.baiwa.buckwaframework.common.util.excel.ExcelReaderUtils;
import th.co.baiwa.buckwaframework.common.util.excel.ExcelResultSet;
import th.co.baiwa.buckwaframework.common.util.excel.ExcelRowMapper;

public class TestExcel {
	public static void main(String[] args) throws Exception {
			File f = new File("E:\\กรรมสรรพามิตร เอกสาร\\ตรวจสอบภายใน\\ตรวจสอบงบสรุปยอดเงินค่าภาษีกับงบทดลอง\\งบทดลอง 2017.xlsx");
			ExcelReaderUtils.read(f,new ExcelRowMapper<String>() {

				@Override
				public String mapRow(ExcelResultSet rs, int rowNum) throws Exception {
					
					System.out.println(rs.getString(7));
					return null;
				}
			});
	}
}

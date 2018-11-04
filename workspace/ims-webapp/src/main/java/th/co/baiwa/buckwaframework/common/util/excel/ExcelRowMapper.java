package th.co.baiwa.buckwaframework.common.util.excel;

public interface ExcelRowMapper<T> {

	T mapRow(ExcelResultSet rs, int rowNum) throws Exception;
	
}

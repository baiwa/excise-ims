package th.go.excise.ims.ta.util;

import org.junit.Test;

import th.go.excise.ims.common.constant.ProjectConstants.TAX_COMPARE_TYPE;

public class TaxAuditUtilsTest {
	
	@Test
	public void test_getWorksheetDateRangeVo() {
		String dateStart = "10/2560";
		String dateEnd = "10/2561";
		int dateRange = 26;
		String compType = TAX_COMPARE_TYPE.HALF;
		
		TaxAuditUtils.getWorksheetDateRangeVo(dateStart, dateEnd, dateRange, compType);
	}
	
}

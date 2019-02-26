package th.go.excise.ims.common.util;

import org.junit.Test;

public class ExciseUtilsTest {
	
	private String[] officeCodes = new String[] {
		"000000",
		"005000",
		"010000",
		"020000",
		"010100",
		"010201",
		"0000",
		"00",
		"",
		null
	};
	
	@Test
	public void test_isCentral() {
		for (String officeCode : officeCodes) {
			System.out.println("isCentral() : " + officeCode + " ==> " + ExciseUtils.isCentral(officeCode));
		}
	}

	@Test
	public void test_isSector() {
		for (String officeCode : officeCodes) {
			System.out.println("isSector() : " + officeCode + " ==> " + ExciseUtils.isSector(officeCode));
		}
	}

	@Test
	public void test_isArea() {
		for (String officeCode : officeCodes) {
			System.out.println("isArea() : " + officeCode + " ==> " + ExciseUtils.isArea(officeCode));
		}
	}

	@Test
	public void test_isBranch() {
		for (String officeCode : officeCodes) {
			System.out.println("isBranch() : " + officeCode + " ==> " + ExciseUtils.isBranch(officeCode));
		}
	}

	@Test
	public void test_whereInLocalOfficeCode() {
		for (String officeCode : officeCodes) {
			System.out.println("test_whereInLocalOfficeCode() : " + officeCode + " ==> " + ExciseUtils.whereInLocalOfficeCode(officeCode));
		}
	}

}

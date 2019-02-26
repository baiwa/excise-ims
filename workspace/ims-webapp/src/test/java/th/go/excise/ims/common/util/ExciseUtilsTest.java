package th.go.excise.ims.common.util;

import org.junit.Test;

public class ExciseUtilsTest {

	
	@Test
	public void isCentral() {
		System.out.println("isCentral : 020101 ==> "+ ExciseUtils.isCentral("020101"));
		System.out.println("isCentral : 010000 ==> "+ ExciseUtils.isCentral("010000"));
		System.out.println("isCentral : 02 ==> "+ ExciseUtils.isCentral("02"));
		System.out.println("isCentral : 000000 ==> "+ ExciseUtils.isCentral("000000"));
		System.out.println("isCentral : 000500 ==> "+ ExciseUtils.isCentral("000500"));
		System.out.println("isCentral : 010100 ==> "+ ExciseUtils.isCentral("010100"));
	}
	@Test
	public void isSector() {
		System.out.println("isSector : 020101 ==> "+ ExciseUtils.isSector("020101"));
		System.out.println("isSector : 010000 ==> "+ ExciseUtils.isSector("010000"));
		System.out.println("isSector : 02 ==> "+ ExciseUtils.isSector("02"));
		System.out.println("isSector : 0000001 ==> "+ ExciseUtils.isSector("0000001"));
		System.out.println("isSector : 010100 ==> "+ ExciseUtils.isSector("010100"));
	}
	@Test
	public void isArea() {
		System.out.println("isArea : 020101 ==> "+ ExciseUtils.isArea("020101"));
		System.out.println("isArea : 010000 ==> "+ ExciseUtils.isArea("010000"));
		System.out.println("isArea : 02 ==> "+ ExciseUtils.isArea("02"));
		System.out.println("isArea : 0000001 ==> "+ ExciseUtils.isArea("0000001"));
		System.out.println("isArea : 010100 ==> "+ ExciseUtils.isArea("010100"));
	}
	@Test
	public void isBranch() {
		System.out.println("isBranch : 020101 ==> "+ ExciseUtils.isBranch("020101"));
		System.out.println("isBranch : 010000 ==> "+ ExciseUtils.isBranch("010000"));
		System.out.println("isBranch : 02 ==> "+ ExciseUtils.isBranch("02"));
		System.out.println("isBranch : 0000001 ==> "+ ExciseUtils.isBranch("0000001"));
		System.out.println("isBranch : 010100 ==> "+ ExciseUtils.isBranch("010100"));
	}
	@Test
	public void whereInLocalOfficeCode() {
		
		System.out.println("whereInLocalOfficeCode : 020101 ==> "+ ExciseUtils.whereInLocalOfficeCode("020101"));
		System.out.println("whereInLocalOfficeCode : 010000 ==> "+ ExciseUtils.whereInLocalOfficeCode("010000"));
		System.out.println("whereInLocalOfficeCode : 02 ==> "+ ExciseUtils.whereInLocalOfficeCode("02"));
		System.out.println("whereInLocalOfficeCode : 0000001 ==> "+ ExciseUtils.whereInLocalOfficeCode("0000001"));
		System.out.println("whereInLocalOfficeCode : 010100 ==> "+ ExciseUtils.whereInLocalOfficeCode("010100"));
	}
	
}

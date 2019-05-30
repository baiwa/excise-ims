package th.co.baiwa.buckwaframework.common.util;

import java.math.BigDecimal;

import org.junit.Test;

public class NumberUtilsTest {
	
	@Test
	public void test_calculatePercent_minus_100() {
		BigDecimal baseBigDecimal = new BigDecimal("0.00");
		BigDecimal compareBigDecimal = new BigDecimal("356306105.00");
		BigDecimal percent = NumberUtils.calculatePercent(baseBigDecimal, compareBigDecimal);
		System.out.println(percent);
	}
	
	@Test
	public void test_calculatePercent_plus_100() {
		BigDecimal baseBigDecimal = new BigDecimal("356306105.00");
		BigDecimal compareBigDecimal = new BigDecimal("0.00");
		BigDecimal percent = NumberUtils.calculatePercent(baseBigDecimal, compareBigDecimal);
		System.out.println(percent);
	}
	
	@Test
	public void test_calculatePercent_zero() {
		BigDecimal baseBigDecimal = new BigDecimal("0.00");
		BigDecimal compareBigDecimal = new BigDecimal("0.00");
		BigDecimal percent = NumberUtils.calculatePercent(baseBigDecimal, compareBigDecimal);
		System.out.println(percent);
	}
	
	@Test
	public void test_calculatePercent_real() {
		BigDecimal baseBigDecimal = new BigDecimal("120");
		BigDecimal compareBigDecimal = new BigDecimal("245");
		BigDecimal percent = NumberUtils.calculatePercent(baseBigDecimal, compareBigDecimal);
		System.out.println(percent);
	}
	
}

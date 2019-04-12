package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ta.vo.TaFormTS0117Vo;

public class TaFormTS0117ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0117Service taFormTS0117Service = new TaFormTS0117Service();
		
		TaFormTS0117Vo formTS0117Vo = new TaFormTS0117Vo();
		formTS0117Vo.setFormTsNumber("000000-2562-000001");
		formTS0117Vo.setBookNumber1("23");
		formTS0117Vo.setBookNumber2("30");
		formTS0117Vo.setDocTopic("ตรวจสอบภาษี");
		formTS0117Vo.setDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0117Vo.setDocDear("ธนพล ชัยภูมิ");
		formTS0117Vo.setRefBookNumber1("525225");
		formTS0117Vo.setRefBookNumber2("554566");
		formTS0117Vo.setRefDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0117Vo.setAuditDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0117Vo.setCallBookNumber1("525225");
		formTS0117Vo.setCallBookNumber2("525225");
		formTS0117Vo.setCallBookDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0117Vo.setFactoryName("");
		formTS0117Vo.setNewRegId("2156651515485115");
		formTS0117Vo.setFacAddrNo("25/25");
		formTS0117Vo.setFacMooNo("3");
		formTS0117Vo.setFacSoiName("อุดมเกียรติ");
		formTS0117Vo.setFacThnName("สุจธิสานวินิจฉัย");
		formTS0117Vo.setFacTambolName("หนองนาคำ");
		formTS0117Vo.setFacAmphurName("ปริมณฑล");
		formTS0117Vo.setFacProvinceName("หนองบัวลำภู");
		formTS0117Vo.setFacZipCode("390000");
		formTS0117Vo.setOfficerFullName("ธนพล ชัยูมิ");
		formTS0117Vo.setOfficerPosition("ครวจวอบภาษี");
		formTS0117Vo.setTaxFormDateStart(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0117Vo.setTaxFormDateEnd(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0117Vo.setTestimonyDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0117Vo.setFactDesc("อธิบดีกรมสรรพสามิต เป็นประธานในการประชุม คณะกรรมการพิจารณาเกี่ยวกับการพัฒนามาตรฐานในการตรวจวิเคราะห์สินค้าสรรพสามิต");
		formTS0117Vo.setLawDesc(" รักษาการในตำแหน่งที่ปรึกษาด้านการพัฒนาและบริหารการจัดเก็บภาษี เป็นประธานการประชุมโครงการเพิ่มประสิทธิภาพการจัดเก็บภาษีสถานบริการไนต์คลับ");
		formTS0117Vo.setFactoryName2(" ");
		formTS0117Vo.setTaxAmt(new BigDecimal(253));
		formTS0117Vo.setFineAmt(new BigDecimal(253));
		formTS0117Vo.setExtraAmt(new BigDecimal(253));
		formTS0117Vo.setExciseTaxAmt(new BigDecimal(253));
		formTS0117Vo.setMoiAmt(new BigDecimal(253));
		formTS0117Vo.setSumAllTaxAmt(new BigDecimal(253));
		formTS0117Vo.setExtraDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0117Vo.setPaymentDest("");
		formTS0117Vo.setPaymentExciseTaxAmt(new BigDecimal(253525426));
		formTS0117Vo.setPaymentDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0117Vo.setOfficeDest("โรงผลิตเหล้ายโสธร");
		formTS0117Vo.setOfficeDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0117Vo.setOfficeTime("13.30");
		formTS0117Vo.setSignOfficerFullName("");
		formTS0117Vo.setSignOfficerPosition("");
		
		byte[] reportFile = taFormTS0117Service.generateReport(formTS0117Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_17))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0117Service taFormTS0117Service = new TaFormTS0117Service();
		
		TaFormTS0117Vo formTS0117Vo = new TaFormTS0117Vo();
		
		byte[] reportFile = taFormTS0117Service.generateReport(formTS0117Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_17 + "_blank"))));
	}
	
}

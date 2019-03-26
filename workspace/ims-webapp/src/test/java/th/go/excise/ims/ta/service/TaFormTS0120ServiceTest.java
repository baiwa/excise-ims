package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.vo.TaFormTS0120Vo;

public class TaFormTS0120ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_20 + "." + FILE_EXTENSION.PDF;
	
	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0120Service taFormTS0120Service = new TaFormTS0120Service();
		
		TaFormTS0120Vo formTS0120Vo = new TaFormTS0120Vo();
		formTS0120Vo.setFactoryName("FactoryName");
		formTS0120Vo.setDocDear("DocDear");
		formTS0120Vo.setBookNumber1("BookNumber1");
		formTS0120Vo.setBookDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setFactoryName2("FactoryName2");
		formTS0120Vo.setNewRegId("NewRegId");
		formTS0120Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setFacAddrNo("FacAddrNo");
		formTS0120Vo.setFacMooNo("FacMooNo");
		formTS0120Vo.setFacSoiName("FacSoiName");
		formTS0120Vo.setFacThnName("FacThnName");
		formTS0120Vo.setFacTambolName("FacTambolName");
		formTS0120Vo.setFacAmphurName("FacTambolName");
		formTS0120Vo.setFacProvinceName("FacProvinceName");
		formTS0120Vo.setFacZipCode("10123");
		formTS0120Vo.setExpandReason("ExpandReason ExpandReason ExpandReason");
		formTS0120Vo.setExpandFlag1("1");
		formTS0120Vo.setExpandFlag2("1");
		formTS0120Vo.setExpandNo("12345");
		formTS0120Vo.setExpandDateOld(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setExpandDateNew(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setSignOfficerFullName("SignOfficerFullName");
		formTS0120Vo.setSignOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setHeadOfficerComment("HeadOfficerComment");
		formTS0120Vo.setSignHeadOfficerFullName("SignHeadOfficerFullName");
		formTS0120Vo.setSignHeadOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setApproverComment("ApproverComment");
		formTS0120Vo.setApproveFlag("2");
		formTS0120Vo.setSignApproverFullName("SignApproverFullName");
		formTS0120Vo.setSignApproverDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		
		byte[] reportFile = taFormTS0120Service.generateReport(formTS0120Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}

}

package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ta.vo.TaFormTS0114DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0114Vo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0114ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_14 + "." + FILE_EXTENSION.PDF;

//	@Autowired
//	private TaFormTS0113Service taFormTS0113Service;

	@Test
	public void test_generateReport() throws Exception {		
		TaFormTS0114Service formTS0114Service = new TaFormTS0114Service();
		
		TaFormTS0114Vo vo = new TaFormTS0114Vo();
		vo.setFactoryName("ชื่อโรงอุตสาหกรรม");
		vo.setNewRegId("3498530945");
		vo.setFacAddrNo("34");
		vo.setFacSoiName("สุทธิสาร");
		vo.setFacThnName("ห้วยขวาง");
		vo.setFacTambolName("ห้วยขวาง");
		vo.setFacAmphurName("ห้วยขวาง");
		vo.setFacProvinceName("กรุงเทพมหานคร");
		vo.setFacZipCode("10310");
		vo.setFacTelNo("0979856904");
		vo.setFacFaxNumber("333448344");
		vo.setFactoryTypeText("ประเภทกิจการ");
		vo.setOfficerFullName("ชื่อผู้ตรวจสอบ");
		vo.setOfficerDept("หน่วยงานผู้ตรวจสอบ");
		vo.setAuditDate(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setBookNumber1("21223232");
		vo.setBookDate(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setAuditDateStart(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setAuditDateEnd(ConvertDateUtils.parseStringToDate("03/02/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setAuditSumMonth("");
		vo.setAuditSumDay("");
		vo.setAuditBookType("2");
		vo.setAuditBookTypeOther("อื่นๆ");
		vo.setAuditBookNumber("2");
		vo.setAuditBookDate(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setDocNum("3");
		vo.setDoc1Num("2");
		vo.setDoc1Date(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setDoc2Num("1");
		vo.setDoc2Date(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setDoc3Num("3");
		vo.setDoc3Date(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setDoc4Num("4");
		vo.setDoc5Num("5");
		vo.setDoc6Num("4");
		vo.setDoc7Num("3");
		vo.setDoc8Num("2");
		vo.setDoc9Num("1");
		vo.setAssReason("เหตุผลการประเมิน");		
		vo.setSignOfficerFullName("4");
		vo.setSignOfficerPosition("ตำแหน่ง");
		
		List<TaFormTS0114DtlVo> listItem = new ArrayList<>();
		TaFormTS0114DtlVo itemVo =new TaFormTS0114DtlVo();
		itemVo.setRecNo("1");
		itemVo.setTaxDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		itemVo.setDutyTypeText("1");
		itemVo.setTaxAmt(new BigDecimal(253));
		itemVo.setFineAmt(new BigDecimal(253));
		itemVo.setExtraAmt(new BigDecimal(253));
		itemVo.setMoiAmt(new BigDecimal(253));
		itemVo.setSumAmt(new BigDecimal(253));
		listItem.add(itemVo);
		
		vo.setTaFormTS0114DtlVoList(listItem);
		
		byte[] reportFile = formTS0114Service.generateReport(vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}
}

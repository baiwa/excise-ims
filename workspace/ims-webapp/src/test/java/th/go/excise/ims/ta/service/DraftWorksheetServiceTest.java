package th.go.excise.ims.ta.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001401", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class DraftWorksheetServiceTest {
	
	@Autowired
	private DraftWorksheetService draftWorksheetService;
	
	@Test
	public void test_getPreviewData() {
		long start = System.currentTimeMillis();
		
		// 2019 - 2562
		// 2018 - 2561
		// 2017 - 2560
		// 2016 - 2559
		// 2015 - 2558
		
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setDateStart("05/2558");
		formVo.setDateEnd("05/2558");
		formVo.setDateRange(2);
		formVo.setBudgetYear("2562");
		formVo.setCondNumber("001401-2562-01");
		formVo.setStart(0);
		formVo.setLength(10);
		
		formVo.setNewRegId("01005150424621001");
		
		TaxOperatorVo taxOperatorVo = draftWorksheetService.getPreviewData(formVo);
		System.out.println("count=" + taxOperatorVo.getCount());
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = taxOperatorVo.getDatas();
		taxOperatorDatatableVoList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE));
		});
		
		long end = System.currentTimeMillis();
		System.out.println("Process Success, using " + ((float) (end - start) / 1000F) + " seconds");
	}
	
//	@Test
	public void test_saveDraft() {
		long start = System.currentTimeMillis();
		
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setBudgetYear("2562");
		formVo.setOfficeCode("001401");
		formVo.setDateStart("01/2561");
		formVo.setDateEnd("12/2561");
		formVo.setDateRange(12);
		formVo.setStart(0);
		formVo.setLength(0);
		formVo.setCondNumber("001401-2562-03");
		formVo.setCondSub1(FLAG.N_FLAG);
		formVo.setCondSub2(FLAG.N_FLAG);
		formVo.setCondSub3(FLAG.N_FLAG);
		
		try {
			draftWorksheetService.saveDraftWorksheet(formVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Process Success, using " + ((float) (end - start) / 1000F) + " seconds");
	}

//	@Test
	public void test_findAllAnalysisNumber() {
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setBudgetYear("2562");
		
		List<String> draftNumberList = draftWorksheetService.findAllAnalysisNumber(formVo);
		draftNumberList.forEach(System.out::println);
	}
	
//	@Test
	public void test_getDraftWorksheet() {
		long start = System.currentTimeMillis();
		
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setBudgetYear("2562");
		formVo.setDraftNumber("000000-2562-000033");
		formVo.setOfficeCode("001402");
		formVo.setStart(0);
		formVo.setLength(10);
		
		TaxOperatorVo vo = draftWorksheetService.getDraftWorksheet(formVo);
		vo.getDatas().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		
		long end = System.currentTimeMillis();
		System.out.println("Process Success, using " + ((float) (end - start) / 1000F) + " seconds");
	}
	
}

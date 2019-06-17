package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;
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

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputMaterialVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)

public class ProductPaperOutputMaterialServiceTest {

	private static final String PRODUCT_PAPER_OUTPUT_MATERIAL = "product_paper_output_material";
	private static final String EXPORT_TYPE_CREATE = "001";

	@Autowired
	private ProductPaperOutputMaterialService productPaperOutputMaterialService;

	@Test
	public void test_inquiry() {
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");

		List<ProductPaperOutputMaterialVo> voList = productPaperOutputMaterialService.inquiryByWs(formVo);
		voList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SHORT_PREFIX_STYLE));
		});

	}

	@Test
	public void test_exportData() {

		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");

		List<ProductPaperOutputMaterialVo> voList = productPaperOutputMaterialService.inquiry(formVo);

		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH.TEST_PATH + PRODUCT_PAPER_OUTPUT_MATERIAL + "." + FILE_EXTENSION.XLSX)) {
			byte[] outArray = productPaperOutputMaterialService.exportData(voList, EXPORT_TYPE_CREATE);
			Output.write(outArray);
			System.out.println("Creating excel");
		} catch (IOException e) {
			System.out.println("false excel");
		}
	}

}

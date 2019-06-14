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

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperInputMaterialVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class ProductPaperInputMaterialServiceTest {

//	private static final String PATH = "/tmp/";
//	private static final String TYPE = ".xlsx";
//
//	private static final String PRODUCT_PAPER_INPUT_MATERIAL = "product_paper_input_material";
//
//	ProductPaperInputMaterialService productPaperInputMaterialService = new ProductPaperInputMaterialService();
//
//	@Test
//	public void test_exportProductPaperInputMaterial() {
//		// set output
//		try (FileOutputStream Output = new FileOutputStream(PATH + PRODUCT_PAPER_INPUT_MATERIAL + TYPE)) {
//			byte[] outArray = productPaperInputMaterialService.exportProductPaperInputMaterial();
//			Output.write(outArray);
//			System.out.println("Creating excel" + "\n" + PRODUCT_PAPER_INPUT_MATERIAL + "\n" + "Done" + "\n");
//		} catch (IOException e) {
//			System.out.println(PRODUCT_PAPER_INPUT_MATERIAL + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
//		}
//	}
	
	@Autowired
	private ProductPaperInputMaterialService productPaperInputMaterialService;
	
	@Test
	public void test_inquiry() {
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");
		
		List<ProductPaperInputMaterialVo> voList = productPaperInputMaterialService.inquiry(formVo);
		voList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SHORT_PREFIX_STYLE));
		});
	}
	
}

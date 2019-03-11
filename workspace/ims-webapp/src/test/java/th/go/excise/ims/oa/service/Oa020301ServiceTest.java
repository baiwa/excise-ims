package th.go.excise.ims.oa.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.oa.vo.Oa020301FormVo;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class Oa020301ServiceTest {
	
	@Autowired
	private Oa020301Service  oa020301Service;
	
	
	
	@Test
	public void test_getPreviewData() {
		long start = System.currentTimeMillis();
		Oa020301FormVo form = new Oa020301FormVo();
		
		form.setWarehouseAddress("1");
		form.setCompanyName("2");
		form.setIdentifyNo("3");
		form.setIdentifyType("3");
		form.setName("Test");
		form.setCompanyName("2");
		form.setMobile("0875027111");
		form.setOldCustomer("N");
		form.setAddress("3"); 

		oa020301Service.saveCustomer(form);
	  
	}
	
	
	
}

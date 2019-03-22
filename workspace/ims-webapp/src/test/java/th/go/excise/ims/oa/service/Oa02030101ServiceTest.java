package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenDetailRepository;
import th.go.excise.ims.oa.vo.Oa02030101FormVo;
import th.go.excise.ims.oa.vo.Oa020301FormVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class Oa02030101ServiceTest {
	
	@Autowired
	private Oa020301Service  oa020301Service;
	
	@Autowired
	private Oa02030101Service oa02030101Service;
	
	@Autowired
	private OaCustomerLicenDetailRepository oaCustomerLicenDetailRep;
	
	
//	@Test
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
	
	
	@Test
	public void test_InsertLicen() {
		Oa02030101FormVo form = new Oa02030101FormVo();
		Date date = new Date();
		
		form.setLicenseType("P"); 
		form.setLicenseNo("A001");
		form.setLicenseDate(date); 
		form.setOldLicenseYear("2011"); 
		form.setBankGuarantee("N");
		form.setOperateName("Jeng");
		form.setOperateRemark("Joon");
		form.setStartDate(date); 
		form.setEndDate(date);
		form.setOffCode("001");
 		
		oa02030101Service.saveCustomerLicenAll(form);
	}
	
	@Test
	public void test_InsertLicenDtl() {
		OaCustomerLicenDetail detail = new OaCustomerLicenDetail();
		detail.setOaCuslicenseId(new BigDecimal(1));
		detail.setAmount(new BigDecimal(0));
		detail.setType("TYPE");
		detail.setSeq(new BigDecimal(0));
		detail.setName("NAME");
		detail.setIsDeleted("N");
		detail.setVersion(1);
		detail.setCreatedBy("TEST");
		detail.setCreatedDate(LocalDateTime.now());
		detail.setLicenseNo("RANDOM_NUMBER");
		oaCustomerLicenDetailRep.save(detail);
	}
	
}

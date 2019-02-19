package th.co.baiwa.buckwaframework.support;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants.MESSAGE_LANG;
import th.co.baiwa.buckwaframework.support.domain.Message;
import th.co.baiwa.buckwaframework.support.domain.ParamGroup;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class ApplicationCacheTest {
	
	@Test
	public void test_getParameterGroupByCode() {
		System.out.println("- - - - - getParameterGroupByCode - - - - -");
		ParamGroup paramGroup = ApplicationCache.getParamGroupByCode("SYSTEM_CONFIG");
		System.out.println(paramGroup);
		Assert.assertNotNull(paramGroup);
	}
	
	@Test
	public void test_getParameterInfoByCode() {
		System.out.println("- - - - - getParameterInfoByCode - - - - -");
		ParamInfo paramInfo = ApplicationCache.getParamInfoByCode("SYSTEM_CONFIG", "LOGIN_ATTEMPTS");
		System.out.println(paramInfo);
		Assert.assertNotNull(paramInfo);
	}
	
	@Test
	public void test_getParameterInfoById() {
		System.out.println("- - - - - paramInfo - - - - -");
		ParamInfo paramInfo = ApplicationCache.getParamInfoById(1L);
		System.out.println(paramInfo);
		Assert.assertNotNull(paramInfo);
	}
	
	@Test
	public void test_getMessage() {
		System.out.println("- - - - - getMessage - - - - -");
		Message message = ApplicationCache.getMessage("S001");
		System.out.println(message);
		Assert.assertNotNull(message);
	}
	
	@Test
	public void test_getMessageByLang() {
		System.out.println("- - - - - getMessageByLang - - - - -");
		String message = ApplicationCache.getMessage("S001", MESSAGE_LANG.EN);
		System.out.println(message);
		Assert.assertNotNull(message);
	}
	
}

package th.co.baiwa.buckwaframework.security.rest.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = PROFILE.UNITTEST)
public class AuthenRestController {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
			.webAppContextSetup(this.wac)
			.addFilters(springSecurityFilterChain)
			.build();
	}
	
	@Test
	public void test_login_success() throws Exception {
		MvcResult result = this.mockMvc.perform(formLogin()
			.loginProcessingUrl(SecurityConstants.LOGIN_URL)
			.user("user")
			.password("password"))
		.andExpect(status().isOk())
		.andReturn();
		
		int status = result.getResponse().getStatus();
		System.out.println(status);
	}
	
	@Test
	public void test_login_fail() throws Exception {
		MvcResult result = this.mockMvc.perform(formLogin()
			.loginProcessingUrl(SecurityConstants.LOGIN_URL)
			.user("user")
			.password("worngpass"))
		.andExpect(status().isUnauthorized())
		.andReturn();
		
		int status = result.getResponse().getStatus();
		System.out.println(status);
	}
	
}

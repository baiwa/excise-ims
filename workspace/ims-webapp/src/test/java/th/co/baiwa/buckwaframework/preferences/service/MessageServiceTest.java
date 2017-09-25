package th.co.baiwa.buckwaframework.preferences.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants.MESSAGE_TYPE;
import th.co.baiwa.buckwaframework.preferences.model.Message;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class MessageServiceTest {
	
	@Autowired
	private MessageService messageService;
	
	@Test
	public void test_insert() {
		Message message = new Message();
		message.setMessageCode("003");
		message.setMessageEn("desc eng");
		message.setMessageTh("desc thai");
		message.setMessageType(MESSAGE_TYPE.INFO);
		messageService.insertMessage(message);
		
		Assert.assertNotNull(message.getMessageId());
	}
	
	@Test
	public void test_update() {
		Message message = new Message();
		message.setMessageCode("003");
		message.setMessageEn("desc eng");
		message.setMessageTh("desc thai");
		message.setMessageType(MESSAGE_TYPE.INFO);
		messageService.insertMessage(message);
		
		Assert.assertNotNull(message.getMessageId());
	}
	
}

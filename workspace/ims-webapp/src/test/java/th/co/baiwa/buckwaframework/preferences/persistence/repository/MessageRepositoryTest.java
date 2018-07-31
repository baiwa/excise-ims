package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.domain.DataTablesPageRequest;
import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants.MESSAGE_TYPE;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class MessageRepositoryTest {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<Message> messageList = (List<Message>) messageRepository.findAll();
		messageList.forEach(System.out::println);
	}
	
	@Test
	public void test_findOne() {
		System.out.println("- - - - - findOne - - - - -");
		Message message = messageRepository.findOne(1L);
		System.out.println(message);
		Assert.assertNotNull(message);
	}
	
	//@Test
	public void test_findByCriteria() {
		System.out.println("- - - - - findByCriteria - - - - -");
		
		Message message = new Message();
		Pageable pageable = new DataTablesPageRequest(0, 2);
		
		List<Message> messageList = messageRepository.findByCriteria(message, pageable);
		messageList.forEach(s -> System.out.println(ToStringBuilder.reflectionToString(s, ToStringStyle.JSON_STYLE)));
	}
	
	//@Test
	public void test_save_new() {
		System.out.println("- - - - - save_new - - - - -");
		Message message = new Message();
		message.setMessageCode("msg_code");
		message.setMessageEn("desc en");
		message.setMessageTh("desc th");
		message.setMessageType(MESSAGE_TYPE.INFO);
		messageRepository.save(message);
	}
	
	//@Test
	public void test_save_update() {
		System.out.println("- - - - - save_update - - - - -");
		Message message = messageRepository.findOne(61L);
		message.setMessageEn("desc en edit");
		messageRepository.save(message);
	}
	
	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		messageRepository.delete(61L);
	}
	
	//@Test
	public void test_deleteByCollection() {
		System.out.println("- - - - - deleteByCollection - - - - -");
		List<Long> ids = Arrays.asList(63L, 64L, 65L);
		messageRepository.delete(ids);
	}
	
}

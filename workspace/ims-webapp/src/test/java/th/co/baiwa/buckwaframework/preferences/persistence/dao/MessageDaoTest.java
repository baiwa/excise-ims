package th.co.baiwa.buckwaframework.preferences.persistence.dao;

import java.util.List;

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
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class MessageDaoTest {
	
	@Autowired
	private MessageDao messageDao;
	
	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<Message> messageList = messageDao.findAll();
		System.out.println(messageList);
		Assert.assertNotEquals(0, messageList.size());
	}
	
	@Test
	public void test_findById_Found() {
		System.out.println("- - - - - findById - - - - -");
		Message message = messageDao.findById(1L);
		System.out.println(message);
		Assert.assertNotNull(message);
	}
	
	@Test
	public void test_findById_NotFound() {
		System.out.println("- - - - - findById - - - - -");
		Message message = messageDao.findById(99L);
		System.out.println(message);
		Assert.assertNull(message);
	}
	
	@Test
	public void test_count() {
		System.out.println("- - - - - count - - - - -");
		int count = messageDao.count();
		System.out.println(count);
		Assert.assertNotEquals(0, count);
	}
	
	//@Test
	public void test_insert() {
		System.out.println("- - - - - insert - - - - -");
		Message message = new Message();
		message.setMessageCode("msg_code");
		message.setMessageEn("desc en");
		message.setMessageTh("desc th");
		message.setMessageType(MESSAGE_TYPE.INFO);
		messageDao.insert(message);
	}
	
	//@Test
	public void test_update() {
		System.out.println("- - - - - update - - - - -");
		Message message = new Message();
		message.setMessageEn("desc en edit");
		message.setMessageId(1L);
		int updateRow = messageDao.update(message);
		Assert.assertEquals(1, updateRow);
	}
	
	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		int updateRow = messageDao.delete(2L);
		Assert.assertEquals(1, updateRow);
	}
	
}

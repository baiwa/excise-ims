package th.co.baiwa.buckwaframework.preferences.rest.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.JsonStatus;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.service.MessageService;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

@RestController
@RequestMapping("/api/preferences/message")
public class MessageRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageRestController.class);
	
	@Autowired
	private MessageService messageService;
	
//	@GetMapping("getMessageType")
//	public ResponseEntity<?> getMessageType() {
//		logger.info("getMessageType");
//		ResponseData<Map<String, Message>> response = new ResponseData<>();
//		//response.setData(ApplicationCache.getP);
//	}
//	
	@GetMapping
	public ResponseEntity<?> getAll() {
		logger.info("getAll");
		
		ResponseData<Map<String, Message>> response = new ResponseData<>();
		response.setData(ApplicationCache.getMessages());
		
		return new ResponseEntity<ResponseData<Map<String, Message>>>(response, HttpStatus.OK);
	}
	
	@GetMapping("search")
	public ResponseEntity<?> search(
			@RequestParam(name="draw") Integer draw, 
			@RequestParam(name="start") Integer start, 
			@RequestParam(name="length") Integer length,
			String messageCode,
			String messageEn,
			String messageTh,
			String messageType
			) {
		
		Message message = new Message();
		message.setMessageCode(messageCode);
		message.setMessageEn(messageEn);
		message.setMessageTh(messageTh);
		message.setMessageType(messageType);
		logger.info("getAll for datatable" + message);
		
		List<Message> resultList = messageService.getMessageList(start, length,message);
		Integer recordsTotal = messageService.countMessage();
		
		ResponseDataTable<Message> response = new ResponseDataTable<Message>();
		response.setDraw(++draw);
		response.setStart(start);
		response.setLength(length);
		response.setData(resultList);
		response.setRecordsTotal(recordsTotal);
		response.setRecordsFiltered(recordsTotal);
		
		return new ResponseEntity<ResponseDataTable<Message>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseData<Message> getMessage(@PathVariable("id") long id) {
		logger.info("getMessage [id=" + id + "]");
		
		ResponseData<Message> response = new ResponseData<Message>();
		try{
			Message message = messageService.getMessageById(id);
			response.setData(message);
			response.setStatusCode(JsonStatus.SUCCESS);
		}catch (Exception e) {
			response.setStatusCode(JsonStatus.ERROR);
			response.setErrorMessage(e.getMessage());
		}
			
		return response;
	}
	
	@PostMapping
	public ResponseData<Message> create(@RequestBody Message message, UriComponentsBuilder ucBuilder) {
		logger.info("create [message=" + message + "]");
		ResponseData<Message> response = new ResponseData<Message>();
		try{
			Message newMessage = messageService.insertMessage(message);
			response.setData(newMessage);
			response.setStatusCode(JsonStatus.SUCCESS);
		}catch (Exception e) {
			response.setStatusCode(JsonStatus.ERROR);
			response.setErrorMessage(e.getMessage());
		}
			
		return response;
	}
	
	@PutMapping
	public ResponseData<Message>update(@RequestBody  Message message, UriComponentsBuilder ucBuilder) {
		ResponseData<Message> response = new ResponseData<Message>();

		try{
			messageService.updateMessage(message);
			response.setStatusCode(JsonStatus.SUCCESS);
		}catch (Exception e) {
			response.setStatusCode(JsonStatus.ERROR);
			response.setErrorMessage(e.getMessage());
		}
				
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseData<Message> delete(@PathVariable("id") List<Long> ids) {
		logger.info("delete [id=" + ids + "]");
		ResponseData<Message> response = new ResponseData<Message>();
		try{
			messageService.deleteMessage(ids);
			response.setStatusCode(JsonStatus.SUCCESS);
		}catch (Exception e) {
			response.setStatusCode(JsonStatus.ERROR);
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
	
}

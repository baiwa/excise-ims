package th.co.baiwa.buckwaframework.preferences.rest.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import th.co.baiwa.buckwaframework.admin.persistence.entity.Role;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.service.MessageService;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

@Controller
@RequestMapping("/api/preferences/message")
public class MessageRestController {

	private static final Logger logger = LoggerFactory.getLogger(MessageRestController.class);

	@Autowired
	private MessageService messageService;
	
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
			@RequestParam(name="length") Integer length) {

		logger.info("getAll for datatable");
		
		List<Message> resultList = messageService.getMessageList(start, length);
		Integer recordsTotal = messageService.countMessage();
		
		ResponseDataTable<Message> response = new ResponseDataTable<Message>();
		response.setDraw(draw);
		response.setStart(start);
		response.setLength(length);
		response.setData(resultList);
		response.setRecordsTotal(recordsTotal);
		response.setRecordsFiltered(recordsTotal);
		
		return new ResponseEntity<ResponseDataTable<Message>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMessage(@PathVariable("id") long id) {
		logger.info("getMessage [id=" + id + "]");
		
		Message message = messageService.getMessageById(id);
		ResponseData<Message> response = new ResponseData<Message>();
		response.setData(message);
		return new ResponseEntity<ResponseData<Message>>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Message message, UriComponentsBuilder ucBuilder) {
		logger.info("create [message=" + message + "]");
		
		Message newMessage = messageService.insertMessage(message);
		
		ResponseData<Message> response = new ResponseData<Message>();
		response.setData(newMessage);
		
		return new ResponseEntity<ResponseData<Message>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody  Message message, UriComponentsBuilder ucBuilder) {

		messageService.updateMessage(message);
		
		HttpHeaders headers = new HttpHeaders();
		
		return new ResponseEntity<String>(headers , HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		logger.info("delete [id=" + id + "]");
		
		messageService.deleteMessage(id);
		return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
	}
	
}

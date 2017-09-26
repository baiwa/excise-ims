package th.co.baiwa.buckwaframework.preferences.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.service.MessageService;

@Controller
@RequestMapping("/api/preferences/message")
public class MessageRestController {

	private static final Logger logger = LoggerFactory.getLogger(MessageRestController.class);

	@Autowired
	private MessageService messageService;
	
	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam("draw") Integer draw, @RequestParam("start") Integer start, @RequestParam("length") Integer length) {
		logger.info("getAll");
		
		List<Message> resultList = messageService.getMessageList();
		Integer recordsTotal = resultList.size();
		
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
	
}

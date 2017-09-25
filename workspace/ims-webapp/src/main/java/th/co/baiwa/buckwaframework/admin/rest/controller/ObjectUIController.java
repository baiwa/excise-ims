//package th.co.baiwa.buckwaframework.admin.rest.controller;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import co.th.baiwa.webpocback.common.persistence.dao.CommonJdbcDao;
//import co.th.baiwa.webpocback.model.ObjectUI;
//import co.th.baiwa.webpocback.model.ResponseData;
//import co.th.baiwa.webpocback.model.ResponseDataTable;
//
//@RestController
//public class ObjectUIController {
//
//	private static final Logger logger = LoggerFactory.getLogger(ObjectUIController.class);
//
//	@Autowired
//	private CommonJdbcDao commonJdbcDao;
//
//	@GetMapping("/api/object-ui")
//	public ResponseEntity<?> all(@RequestParam("draw") Integer draw, @RequestParam("start") Integer start,
//			@RequestParam("length") Integer length) {
//		logger.info("all");
//
//		List<ObjectUI> resultList = commonJdbcDao.executeQuery(
//				"SELECT ROWNUM(), * FROM (SELECT * FROM object_ui) where ROWNUM() <= ? and ROWNUM() >= ?",
//				new Object[] { start + length, start + 1 }, BeanPropertyRowMapper.newInstance(ObjectUI.class));
//
//		Integer recordsTotal = commonJdbcDao
//				.executeQueryForObject("SELECT COUNT(1) as \"recordsTotal\" FROM object_ui ou", Integer.class);
//
//		ResponseDataTable<ObjectUI> response = new ResponseDataTable<ObjectUI>();
//		response.setDraw(draw);
//		response.setStart(start);
//		response.setLength(length);
//		response.setData(resultList);
//		response.setRecordsTotal(recordsTotal);
//		response.setRecordsFiltered(recordsTotal);
//		return new ResponseEntity<ResponseDataTable<ObjectUI>>(response, HttpStatus.OK);
//	}
//
//	@GetMapping("/api/object-ui/{id}")
//	public ResponseEntity<?> getObjectUI(@PathVariable("id") long id) {
//		logger.info("getObjectUI [id=" + id + "]");
//
//		ObjectUI object_ui = commonJdbcDao.executeQueryForObject("SELECT * FROM object_ui WHERE id=?",
//				new Object[] { id }, BeanPropertyRowMapper.newInstance(ObjectUI.class));
//		ResponseData<ObjectUI> response = new ResponseData<ObjectUI>();
//		response.setData(object_ui);
//		return new ResponseEntity<ResponseData<ObjectUI>>(response, HttpStatus.OK);
//	}
//
//	@PostMapping("/api/object-ui/")
//	public ResponseEntity<?> create(@RequestBody ObjectUI objectUI, UriComponentsBuilder ucBuilder) {
//		logger.info("create [object_ui=" + objectUI + "]");
//
//		Long id = commonJdbcDao.executeInsertWithKeyHolder(
//				"INSERT INTO object_ui (name, description, html_id) VALUES (?,?,?)", objectUI.getName(),
//				objectUI.getDescription(), objectUI.getHtmlId());
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/api/object-ui/{id}").buildAndExpand(id).toUri());
//
//		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//	}
//
//	@PutMapping("/api/object-ui/")
//	public ResponseEntity<?> update(@RequestBody ObjectUI objectUI, UriComponentsBuilder ucBuilder) {
//		logger.info("update [object_ui=" + objectUI + "]");
//
//		commonJdbcDao.executeUpdate("UPDATE object_ui SET name=?, description=?, html_id=? WHERE id=?",
//				objectUI.getName(), objectUI.getDescription(), objectUI.getHtmlId(), objectUI.getId());
//		Long id = objectUI.getId();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/api/object-ui/{id}").buildAndExpand(id).toUri());
//
//		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//	}
//
//	@DeleteMapping("/api/object-ui/{id}")
//	public ResponseEntity<?> delete(@PathVariable("id") long id) {
//		logger.info("delete [id=" + id + "]");
//
//		commonJdbcDao.executeUpdate("DELETE FROM object_ui WHERE id=?", id);
//		return new ResponseEntity<ObjectUI>(HttpStatus.NO_CONTENT);
//	}
//}

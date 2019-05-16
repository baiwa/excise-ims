package th.go.excise.ims.preferences.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.preferences.service.ExcisePersonService;
import th.go.excise.ims.preferences.vo.ExcisePersonVoSelect;

@RestController
@RequestMapping("/api/person")
public class ExcisePersonController {
	

	private static final Logger logger = LoggerFactory.getLogger(ExcisePersonController.class);
	
	@Autowired
	private ExcisePersonService excisePersonService;
	
	
	@GetMapping("/person-list/{name}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Excise Sector List"
	)
	public ResponseData<List<ExcisePersonVoSelect>> getPersonList(@PathVariable("name") String name) {
		ResponseData<List<ExcisePersonVoSelect>> response = new ResponseData<>();
		List<ExcisePersonVoSelect> exciseSectorList = excisePersonService.findPersonByName(name);
		if (exciseSectorList.size() > 0) {
			response.setData(exciseSectorList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Excise Sector List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@GetMapping("/person-list-edlogin/{edlogin}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Excise Sector List"
	)
	public ResponseData<List<ExcisePersonVoSelect>> getPersonListByEdLogin(@PathVariable("edlogin") String edlogin) {
		ResponseData<List<ExcisePersonVoSelect>> response = new ResponseData<>();
		List<ExcisePersonVoSelect> exciseSectorList = excisePersonService.findPersonByName(edlogin);
		if (exciseSectorList.size() > 0) {
			response.setData(exciseSectorList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Excise Sector List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

}

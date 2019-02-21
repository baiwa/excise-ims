package th.go.excise.ims.preferences.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;

@RestController
@RequestMapping("/api/preferences/department")
public class ExciseDepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(ExciseDepartmentController.class);

	@PostMapping("/sector-list")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Excise Sector List"
	)
	public ResponseData<List<ExciseDept>> getAllSectorList() {
		logger.info("getAllSectorList");
		
		ResponseData<List<ExciseDept>> response = new ResponseData<>();
		List<ExciseDept> exciseSectorList = ApplicationCache.getExciseSectorList();
		if (!CollectionUtils.isEmpty(exciseSectorList)) {
			response.setData(exciseSectorList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Excise Sector List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

	@PostMapping("/area-list/{officeCode}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Excise Area List by Sector Code"
	)
	public ResponseData<List<ExciseDept>> getAreaListBySectorCode(@PathVariable("officeCode") String officeCode) {
		logger.info("getAreaListBySectorCode officeCode={}", officeCode);
		
		ResponseData<List<ExciseDept>> response = new ResponseData<>();
		List<ExciseDept> exciseAreaList = ApplicationCache.getExciseAreaList(officeCode);
		if (!CollectionUtils.isEmpty(exciseAreaList)) {
			response.setData(exciseAreaList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Excise Area List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

	@PostMapping("/branch-list/{officeCode}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Excise Branch List by Area Code"
	)
	public ResponseData<List<ExciseDept>> getBranchListByAreaCode(@PathVariable("officeCode") String officeCode) {
		logger.info("getBranchListByAreaCode officeCode={}", officeCode);
		
		ResponseData<List<ExciseDept>> response = new ResponseData<>();
		List<ExciseDept> exciseBranchList = ApplicationCache.getExciseBranchList(officeCode);
		if (!CollectionUtils.isEmpty(exciseBranchList)) {
			response.setData(exciseBranchList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Excise Area List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/dept-dtl/{officeCode}")
	@ApiOperation(
			tags = MODULE_NAME.PREFERENCES,
			value = "Get Excise Dept Dtl"
			)
	public ResponseData<ExciseDept> getExciseDept(@PathVariable("officeCode") String officeCode) {
		logger.info("getBranchListByAreaCode officeCode={}", officeCode);
		
		ResponseData<ExciseDept> response = new ResponseData<>();
		ExciseDept exciseDept = ApplicationCache.getExciseDept(officeCode);
		if (exciseDept != null ) {
			response.setData(exciseDept);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Office Code Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

}

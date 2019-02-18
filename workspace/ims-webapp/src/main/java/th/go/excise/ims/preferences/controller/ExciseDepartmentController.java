package th.go.excise.ims.preferences.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;

@RestController
@RequestMapping("/api/preferences/department")
public class ExciseDepartmentController {

	private static final Logger logger = LoggerFactory.getLogger(ExciseDepartmentController.class);

	@PostMapping("/sector-list")
	public ResponseData<List<ExciseDept>> getAllSectorList() {
		logger.info("findAllSectorList");
		ResponseData<List<ExciseDept>> response = new ResponseData<>();
		List<ExciseDept> exciseSectors = new ArrayList<>();
		try {
			exciseSectors = ApplicationCache.getExciseSectorList();
			response.setData(exciseSectors);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/area-list/{officeCode}")
	public ResponseData<List<ExciseDept>> getAreaListBySectorCode(@PathVariable("officeCode") String officeCode) {
		ResponseData<List<ExciseDept>> response = new ResponseData<>();
		List<ExciseDept> exciseAreaList = new ArrayList<>();
		try {
			exciseAreaList = ApplicationCache.getExciseAreaList(officeCode);
			response.setData(exciseAreaList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/branch-list/{officeCode}")
	public ResponseData<List<ExciseDept>> getBranchListByAreaCode(@PathVariable("officeCode") String officeCode) {
		ResponseData<List<ExciseDept>> response = new ResponseData<>();
		List<ExciseDept> exciseBranchList = new ArrayList<>();
		try {
			exciseBranchList = ApplicationCache.getExciseBranchList(officeCode);
			response.setData(exciseBranchList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}

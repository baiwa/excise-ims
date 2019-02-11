package th.go.excise.ims.common.controller;

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
import th.go.excise.ims.common.persistence.entity.ExciseDepartment;

@RestController
@RequestMapping("/api/excise/sectorareabranch")
public class SectorAreaBranchController {

	private static final Logger logger = LoggerFactory.getLogger(SectorAreaBranchController.class);
	
	@PostMapping("/sector-list")
	public ResponseData<List<ExciseDepartment>> findAllSectorList(){
		logger.info("findAllSectorList");
		ResponseData<List<ExciseDepartment>> response = new ResponseData<>();
		List<ExciseDepartment> exciseSectors = new ArrayList<ExciseDepartment>();
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
	public ResponseData<List<ExciseDepartment>> findAreaListBySectorId(@PathVariable("officeCode")String officeCode){
		ResponseData<List<ExciseDepartment>> response = new ResponseData<>();
		List<ExciseDepartment> exciseAreaList = new ArrayList<>();
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
	public ResponseData<List<ExciseDepartment>> findbranchListByAreaId(@PathVariable("officeCode") String officeCode){
		ResponseData<List<ExciseDepartment>> response = new ResponseData<>();
		List<ExciseDepartment> exciseBranchList = new ArrayList<ExciseDepartment>();
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
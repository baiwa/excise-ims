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
import th.go.excise.ims.preferences.persistence.entity.ExciseAmphur;
import th.go.excise.ims.preferences.persistence.entity.ExciseDistrict;
import th.go.excise.ims.preferences.persistence.entity.ExciseGeo;
import th.go.excise.ims.preferences.persistence.entity.ExciseProvice;


@RestController
@RequestMapping("/api/preferences/geography")
public class GeographyController {
	
	private static final Logger logger = LoggerFactory.getLogger(GeographyController.class);
	
	@PostMapping("/geo-list")
	public ResponseData<List<ExciseGeo>> findAllGeolList(){
		logger.info("findAllGeneralList");
		ResponseData<List<ExciseGeo>> response = new ResponseData<>();
		List<ExciseGeo> exciseGeos = new ArrayList<ExciseGeo>();
		try {
			exciseGeos = ApplicationCache.getExciseGeoList();
			response.setData(exciseGeos);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
//	@PostMapping("/provice-list/{proviceId}")
//	public ResponseData<List<ExciseProvice>> findProviceListByProviceId(@PathVariable("proviceId")String proviceId){
//		ResponseData<List<ExciseProvice>> response = new ResponseData<>();
//		List<ExciseProvice> exciseProviceList = new ArrayList<>();
// 		try {
// 			exciseProviceList = ApplicationCache.getExciseProviceList(proviceId);
//			response.setData(exciseProviceList);
//			response.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			response.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return response;
//	}
	
	@PostMapping("/amphur-list/{amphurId}")
	public ResponseData<List<ExciseAmphur>> findAmphurListByAmphurId(@PathVariable("amphurId")String amphurId){
		ResponseData<List<ExciseAmphur>> response = new ResponseData<>();
		List<ExciseAmphur> exciseAmphurList = new ArrayList<>();
 		try {
 			exciseAmphurList = ApplicationCache.getExciseAmphurList(amphurId);
			response.setData(exciseAmphurList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/district-list/{districtId}")
	public ResponseData<List<ExciseDistrict>> findDistrictListBydistrictId(@PathVariable("districtId")String districtId){
		ResponseData<List<ExciseDistrict>> response = new ResponseData<>();
		List<ExciseDistrict> exciseDistrictList = new ArrayList<>();
 		try {
 			exciseDistrictList = ApplicationCache.getExciseDistrictList(districtId);
			response.setData(exciseDistrictList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}

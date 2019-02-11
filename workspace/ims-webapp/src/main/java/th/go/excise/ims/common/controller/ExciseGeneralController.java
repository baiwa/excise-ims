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
import th.go.excise.ims.common.domain.ExciseAmphur;
import th.go.excise.ims.common.domain.ExciseDistrict;
import th.go.excise.ims.common.domain.ExciseGeo;
import th.go.excise.ims.common.domain.ExciseProvince;


@RestController
@RequestMapping("/api/excise/excise-general")
public class ExciseGeneralController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExciseGeneralController.class);
	
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
	
	@PostMapping("/provice-list/{proviceId}")
	public ResponseData<List<ExciseProvince>> findProviceListByProviceId(@PathVariable("proviceId")String proviceId){
		ResponseData<List<ExciseProvince>> response = new ResponseData<>();
		List<ExciseProvince> exciseProvinceList = new ArrayList<>();
 		try {
 			exciseProvinceList = ApplicationCache.getExciseProvinceList(proviceId);
			response.setData(exciseProvinceList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
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

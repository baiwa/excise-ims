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
import th.co.baiwa.buckwaframework.support.domain.ExciseAmphur;
import th.co.baiwa.buckwaframework.support.domain.ExciseDistrict;
import th.co.baiwa.buckwaframework.support.domain.ExciseGeo;
import th.co.baiwa.buckwaframework.support.domain.ExciseProvice;

@RestController
@RequestMapping("/api/preferences/geography")
public class GeographyController {
	
	private static final Logger logger = LoggerFactory.getLogger(GeographyController.class);
	
	@PostMapping("/geo-list")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Geography by Code"
	)
	public ResponseData<List<ExciseGeo>> getGeolList() {
		logger.info("getGeolList");
		
		ResponseData<List<ExciseGeo>> response = new ResponseData<>();
		List<ExciseGeo> exciseGeoList = ApplicationCache.getExciseGeoList();
		if (!CollectionUtils.isEmpty(exciseGeoList)) {
			response.setData(exciseGeoList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Geography List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/provice-list")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Province List"
	)
	public ResponseData<List<ExciseProvice>> getProviceList() {
		logger.info("getProviceList");
		
		ResponseData<List<ExciseProvice>> response = new ResponseData<>();
		List<ExciseProvice> exciseProviceList = ApplicationCache.getExciseProviceList();
		if (!CollectionUtils.isEmpty(exciseProviceList)) {
			response.setData(exciseProviceList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Province List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/provice-list/{geoId}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Province List by Geo Id"
	)
	public ResponseData<List<ExciseProvice>> getProviceListByGeoId(@PathVariable("geoId") String geoId) {
		logger.info("getProviceListByGeoCode geoId={}", geoId);
		
		ResponseData<List<ExciseProvice>> response = new ResponseData<>();
		List<ExciseProvice> exciseProviceList = ApplicationCache.getExciseProviceListByGeoId(geoId);
		if (!CollectionUtils.isEmpty(exciseProviceList)) {
			response.setData(exciseProviceList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Province List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/amphur-list/{provinceId}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Amphur List by Province Id"
	)
	public ResponseData<List<ExciseAmphur>> getAmphurListByProvinceId(@PathVariable("provinceId") String provinceId) {
		logger.info("getAmphurListByProvinceId provinceId={}", provinceId);
		
		ResponseData<List<ExciseAmphur>> response = new ResponseData<>();
		List<ExciseAmphur> exciseAmphurList = ApplicationCache.getExciseAmphurList(provinceId);
		if (!CollectionUtils.isEmpty(exciseAmphurList)) {
			response.setData(exciseAmphurList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Amphur List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

	@PostMapping("/district-list/{amphurId}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get District List by Amphur Id"
	)
	public ResponseData<List<ExciseDistrict>> getDistrictListByAmphurId(@PathVariable("amphurId") String amphurId) {
		logger.info("getDistrictListByAmphurId amphurId={}", amphurId);
		
		ResponseData<List<ExciseDistrict>> response = new ResponseData<>();
		List<ExciseDistrict> exciseDistrictList = ApplicationCache.getExciseDistrictList(amphurId);
		if (!CollectionUtils.isEmpty(exciseDistrictList)) {
			response.setData(exciseDistrictList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("District List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

}

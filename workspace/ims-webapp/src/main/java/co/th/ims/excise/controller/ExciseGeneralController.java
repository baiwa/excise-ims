//package co.th.ims.excise.controller;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import co.th.baiwa.buckwaframework.common.bean.ResponseData;
//import co.th.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
//import co.th.baiwa.buckwaframework.support.ApplicationCache;
//import co.th.ims.excise.domain.ExciseAmphur;
//import co.th.ims.excise.domain.ExciseDistrict;
//import co.th.ims.excise.domain.ExciseGeo;
//import co.th.ims.excise.domain.ExciseProvice;
//
//
//@RestController
//@RequestMapping("/app/excise/exciseGeneral")
//public class ExciseGeneralController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(ExciseGeneralController.class);
//	
//	@PostMapping("/GeolList")
//	public ResponseData<List<ExciseGeo>> findAllGeolList(){
//		logger.info("findAllGeneralList");
//		ResponseData<List<ExciseGeo>> response = new ResponseData<>();
//		List<ExciseGeo> exciseGeos = new ArrayList<ExciseGeo>();
//		try {
//			exciseGeos = ApplicationCache.getExciseGeoList(null);
//			response.setData(exciseGeos);
//			response.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			response.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return response;
//	}
//	
//	@PostMapping("/proviceList/{proviceId}")
//	public ResponseData<List<ExciseProvice>> findProviceListByProviceId(@PathVariable("proviceId")String proviceId){
//		ResponseData<List<ExciseProvice>> response = new ResponseData<>();
//		List<ExciseProvice> exciseProviceList = new ArrayList<>();
// 		try {
// 			exciseProviceList = ApplicationCache.getExciseProviceList(new BigDecimal(proviceId));
//			response.setData(exciseProviceList);
//			response.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			response.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return response;
//	}
//	
//	@PostMapping("/amphurList/{amphurId}")
//	public ResponseData<List<ExciseAmphur>> findAmphurListByAmphurId(@PathVariable("amphurId")String amphurId){
//		ResponseData<List<ExciseAmphur>> response = new ResponseData<>();
//		List<ExciseAmphur> exciseAmphurList = new ArrayList<>();
// 		try {
// 			exciseAmphurList = ApplicationCache.getExciseAmphurList(new BigDecimal(amphurId));
//			response.setData(exciseAmphurList);
//			response.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			response.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return response;
//	}
//	
//	@PostMapping("/districtList/{districtId}")
//	public ResponseData<List<ExciseDistrict>> findDistrictListBydistrictId(@PathVariable("districtId")String districtId){
//		ResponseData<List<ExciseDistrict>> response = new ResponseData<>();
//		List<ExciseDistrict> exciseDistrictList = new ArrayList<>();
// 		try {
// 			exciseDistrictList = ApplicationCache.getExciseDistrictList(new BigDecimal(districtId));
//			response.setData(exciseDistrictList);
//			response.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			response.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return response;
//	}
//}

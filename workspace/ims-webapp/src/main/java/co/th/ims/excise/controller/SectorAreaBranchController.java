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
//import co.th.ims.excise.domain.ExciseArea;
//import co.th.ims.excise.domain.ExciseBranch;
//import co.th.ims.excise.domain.ExciseSector;
//
//@RestController
//@RequestMapping("/app/excise/sectorareabranch")
//public class SectorAreaBranchController {
//
//	private static final Logger logger = LoggerFactory.getLogger(SectorAreaBranchController.class);
//	
//	@PostMapping("/sectorList")
//	public ResponseData<List<ExciseSector>> findAllSectorList(){
//		logger.info("findAllSectorList");
//		ResponseData<List<ExciseSector>> response = new ResponseData<>();
//		List<ExciseSector> exciseSectors = new ArrayList<ExciseSector>();
//		try {
//			exciseSectors = ApplicationCache.getExciseSectorList();
//			response.setData(exciseSectors);
//			response.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			response.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return response;
//	} 
//	
//	@PostMapping("/areaList/{sectorId}")
//	public ResponseData<List<ExciseArea>> findAreaListBySectorId(@PathVariable("sectorId")String sectorId){
//		ResponseData<List<ExciseArea>> response = new ResponseData<>();
//		List<ExciseArea> exciseAreaList = new ArrayList<>();
// 		try {
// 			exciseAreaList = ApplicationCache.getExciseAreaList(new BigDecimal(sectorId));
//			response.setData(exciseAreaList);
//			response.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			response.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return response;
//	}
//	
//	
//	@PostMapping("/branchList/{areaId}")
//	public ResponseData<List<ExciseBranch>> findbranchListByAreaId(@PathVariable("areaId") String areaId){
//		ResponseData<List<ExciseBranch>> response = new ResponseData<>();
//		List<ExciseBranch> exciseBranchList = new ArrayList<ExciseBranch>();
// 		try {
// 			exciseBranchList = ApplicationCache.getExciseBranchList(new BigDecimal(areaId));
//			response.setData(exciseBranchList);
//			response.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			response.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return response;
//	}
//}

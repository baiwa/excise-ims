package th.go.excise.ims.scheduler.service;

import java.io.IOException;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import th.go.excise.ims.ws.client.summit.systemunworking.model.RequestData;
import th.go.excise.ims.ws.client.summit.systemunworking.model.ResponseData;
import th.go.excise.ims.ws.client.summit.systemunworking.service.SystemUnworkingService;

public class SyncWsSystemUnworkingService {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncWsSystemUnworkingService.class);
	
//	@Autowired
//	private IaRiskSystemUnworkingRepository iaRiskSystemUnworkingRepository;
	
	@Autowired
	private SystemUnworkingService systemUnworkingService;
	
	public void syncData() throws IOException {
		logger.info("syncData SystemUnworking Start");
		long start = System.currentTimeMillis();
		
		ThaiBuddhistDate thaiBuddhistDate = ThaiBuddhistDate.now();
		String month = String.valueOf(thaiBuddhistDate.get(ChronoField.MONTH_OF_YEAR));
		String year = String.valueOf(thaiBuddhistDate.get(ChronoField.YEAR));
		
		RequestData requestData = new RequestData();
		requestData.setMonth(month);
		requestData.setYear(year);
		
		ResponseData responseData = systemUnworkingService.execute(requestData);
//		IaRiskSystemUnworking entity = new IaRiskSystemUnworking();
//		if(req!=null&&req.getData().size()>0) {
//			for (DataList element : req.getData()) {
//				entity = new IaRiskSystemUnworking();
//				
//				entity.setYear(year);
//				entity.setMonth(month);
//				entity.setStatus(req.getStatus());
//				
//				entity.setSystemCode(element.getSystemCode());
//				entity.setSystemName(element.getSystemName());
//				entity.setCountAll(element.getCountAll());
//				entity.setCountNormal(element.getCountNormal());
//				entity.setCountError(element.getCountError());
//				entity.setStartDate(element.getStartDate());
//				entity.setEndDate(element.getEndDate());
//				
//				
//				iaRiskSystemUnworkingRepository.save(entity);
//			}
//		}
		
		long end = System.currentTimeMillis();
		logger.info("syncData SystemUnworking Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}

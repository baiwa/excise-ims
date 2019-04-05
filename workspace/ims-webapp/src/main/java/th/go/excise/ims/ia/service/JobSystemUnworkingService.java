package th.go.excise.ims.ia.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.job.JobSystemUnworking;
import th.go.excise.ims.ia.persistence.entity.IaRiskSystemUnworking;
import th.go.excise.ims.ia.persistence.repository.IaRiskSystemUnworkingRepository;
import th.go.excise.ims.ws.client.pcc.wsSystemUnworking.oxm.DataList;
import th.go.excise.ims.ws.client.pcc.wsSystemUnworking.oxm.ResponseData;
import th.go.excise.ims.ws.client.pcc.wsSystemUnworking.service.WsSystemUnworkingService;

@Service
public class JobSystemUnworkingService {
	
	private static final Logger logger = LoggerFactory.getLogger(JobSystemUnworking.class);
	
	@Autowired
	private IaRiskSystemUnworkingRepository iaRiskSystemUnworkingRepository;
	
	@Autowired
	private WsSystemUnworkingService wsSystemUnworkingService;
	
	public void runBatchSystemUnworking(String budgetYear) throws IOException {
		logger.info("Run Batch SystemUnworking ...");
		
//		ResponseData req = wsSystemUnworkingService.getRestFul(budgetYear);
//		IaRiskSystemUnworking entity = new IaRiskSystemUnworking();
//		if(req!=null&&req.getData().size()>0) {
//			for (DataList element : req.getData()) {
//				entity = new IaRiskSystemUnworking();
//				entity.setBudgetYear(budgetYear);
//				entity.setSystemcode(element.getSystemCode());
//				entity.setSystemname(element.getSystemName());
//				iaRiskSystemUnworkingRepository.save(entity);
//			}
//		}
		
		
	}
	
}

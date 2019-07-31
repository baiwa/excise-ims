package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegDuty;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegMaster60;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.regfri4000.service.RegFri4000Service;

@Service
public class SyncTaWsReg4000Service {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncTaWsReg4000Service.class);
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData Regfri4000 to TaWsReg4000 Start");
		long start = System.currentTimeMillis();
		
		
		logger.info("Batch Merge WS_REGFRI4000 Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData Regfri4000 Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}

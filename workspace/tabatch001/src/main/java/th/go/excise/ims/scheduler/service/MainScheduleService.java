package th.go.excise.ims.scheduler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.constant.ProjectConstants.SCHEDULE_CODE;

@Service
public class MainScheduleService {
	
	private static final Logger logger = LoggerFactory.getLogger(MainScheduleService.class);
	
	private SyncWsIncfri8000Service syncWsIncfri8000Service;
	private SyncTaWsInc8000MService syncTaWsInc8000MService;
	private SyncWsRegfri4000Service syncWsRegfri4000Service;
	private SyncTaWsReg4000Service syncTaWsReg4000Service;
	
	@Autowired
	public MainScheduleService (
			SyncWsIncfri8000Service syncWsIncfri8000Service,
			SyncTaWsInc8000MService syncTaWsInc8000MService,
			SyncWsRegfri4000Service syncWsRegfri4000Service,
			SyncTaWsReg4000Service syncTaWsReg4000Service) {
		this.syncWsIncfri8000Service = syncWsIncfri8000Service;
		this.syncTaWsInc8000MService = syncTaWsInc8000MService;
		this.syncWsRegfri4000Service = syncWsRegfri4000Service;
		this.syncTaWsReg4000Service = syncTaWsReg4000Service;
	}
	
	public void run(String... args) {
		if (args.length > 0) {
			String scheduleCode = args[0];
			try {
				if (SCHEDULE_CODE.INCFRI8000.equals(scheduleCode)) {
					logger.info("Process INCFRI8000 - Start");
					syncWsIncfri8000Service.syncData(syncWsIncfri8000Service.buildRequestData(args));
					logger.info("Process INCFRI8000 - End");
				} else if (SCHEDULE_CODE.SUM8000M.equals(scheduleCode)) {
					logger.info("Process SUM8000M - Start");
					syncTaWsInc8000MService.syncData(syncTaWsInc8000MService.buildFormVo(args));
					logger.info("Process SUM8000M - End");
				} else if (SCHEDULE_CODE.REGFRI4000.equals(scheduleCode)) {
					logger.info("Process REGFRI4000 - Start");
					syncWsRegfri4000Service.syncData(syncWsRegfri4000Service.buildRequestData());
					logger.info("Process REGFRI4000 - End");
				} else if (SCHEDULE_CODE.VERREG4000.equals(scheduleCode)) {
					logger.info("Process VERREG4000 - Start");
					syncTaWsReg4000Service.syncData();
					logger.info("Process VERREG4000 - End");
				} else {
					logger.warn("Wrong Schedule Code");
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			logger.warn("Please specify Program Code");
		}
	}

}

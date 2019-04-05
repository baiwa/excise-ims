package th.go.excise.ims.ia.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.go.excise.ims.ia.service.JobSystemUnworkingService;

public class JobSystemUnworking implements Job {

	private static final Logger logger = LoggerFactory.getLogger(JobSystemUnworking.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
			try {
				logger.info("Job SystemUnworking Working ...");
				JobSystemUnworkingService interJobService = (JobSystemUnworkingService) dataMap.get("jobSystemUnworkingService");
				interJobService.runBatchSystemUnworking("2560");
				
			}catch (Exception e) {
				logger.error("Job SystemUnworking" , e);
			}
		
	}

		
}

package th.go.excise.ims.ia.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.service.JobLicenseListService;
import th.go.excise.ims.ia.service.JobSystemUnworkingService;

public class JobLicenseList implements Job {

	private static final Logger logger = LoggerFactory.getLogger(JobLicenseList.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
			try {
				logger.info("Job LicenseList Working ...");
				JobLicenseListService jobLicenseListJobService = (JobLicenseListService) dataMap.get("jobLicenseListService");
				
//				String date = ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
//				String month = date.split("/")[0];
//				String year = date.split("/")[1];
//				
//				logger.info("date : " + date + " month : " + month + " year : " + year);
//				
//				jobLicenseListJobService.runBatchLicenseList();
				
			}catch (Exception e) {
				logger.error("Job SystemUnworking" , e);
			}
		
	}

		
}

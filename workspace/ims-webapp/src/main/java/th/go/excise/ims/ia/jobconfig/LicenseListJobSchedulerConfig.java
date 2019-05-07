package th.go.excise.ims.ia.jobconfig;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import th.go.excise.ims.ia.job.JobLicenseList;
import th.go.excise.ims.ia.service.JobLicenseListService;

//@Configuration
//@ConditionalOnProperty(name="license.list.job.cronExpressions" , havingValue="" ,matchIfMissing=false)
public class LicenseListJobSchedulerConfig {

		
		private static final Logger log = LoggerFactory.getLogger(LicenseListJobSchedulerConfig.class);

		
		@Autowired
		private JobLicenseListService licenseListService;

		@Value("${license.list.job.cronExpressions}")
		private String cronExpressions;
		
		@PostConstruct
		public void init(){
			log.info("############################### LicenseListServiceBatchJobSchedulerConfig ###############################");
			log.info("###  ");
			log.info("###  ");
			log.info("###  cronEx : " + cronExpressions);
			log.info("###  ");
			log.info("###  ");
			log.info("###############################");
		}
		
		@Bean
		public JobDetail licenseListJobDetail() {
			JobDataMap newJobDataMap = new JobDataMap();
			newJobDataMap.put("jobLicenseListService", licenseListService);
			JobDetail job = JobBuilder.newJob(JobLicenseList.class)
				      .withIdentity("licenseListJobDetail", "group1") // name "myJob", group "group1"
				      .usingJobData(newJobDataMap )
				      .build();
			return job;
		}
		
		@Bean("licenseListCronTrigger")
		public Set<CronTrigger> licenseListCronTrigger() {
			Set<CronTrigger> cornsets = new HashSet<>();
			String[] corns = StringUtils.split(cronExpressions,",");
			int i=0;
			for (String corn : corns) {
				CronTrigger trigger = TriggerBuilder.newTrigger()
					    .withIdentity("licenseListCronTrigger" + i++, "group1")
					    .withSchedule(CronScheduleBuilder.cronSchedule(corn))
					    .build();
				cornsets.add(trigger);
			}

			return cornsets;
		}
		
		@Bean
		public SchedulerFactory  licenseListSchedulerFactory() throws SchedulerException {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			sched.scheduleJob(licenseListJobDetail(), licenseListCronTrigger(),true);
			sched.start();
			return sf;
		}
		
		@PreDestroy
		public void destroy() throws SchedulerException {
			log.info("licenseListBatchJobSchedulerConfig.. shutdown");
			licenseListSchedulerFactory().getScheduler().shutdown();
		}
	}

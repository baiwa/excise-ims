package th.go.excise.ims.scheduler.execute;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.preferences.vo.ExciseDepartment;
import th.go.excise.ims.scheduler.service.SyncWsLicfri6010Service;
import th.go.excise.ims.ws.client.pcc.licfri6010.model.RequestData;


public class ExecuteIaWs6010Service  implements Job {
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		
		

		
	    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		try {
			SyncWsLicfri6010Service systemUnworkingJobService = (SyncWsLicfri6010Service) dataMap.get("syncWsLicfri6010Service");

			List<String> offCodeList = new ArrayList<>();
			List<ExciseDepartment> sectorList  = ApplicationCache.getExciseSectorList();
			for (ExciseDepartment sector : sectorList) {
				offCodeList.add(sector.getOfficeCode());
				List<ExciseDepartment> areaList = ApplicationCache.getExciseAreaList(sector.getOfficeCode());
				for (ExciseDepartment area : areaList) {
					offCodeList.add(area.getOfficeCode());
					List<ExciseDepartment> branchList = ApplicationCache.getExciseBranchList(area.getOfficeCode());
					if( branchList != null && branchList.size() > 0) {
						for (ExciseDepartment branch : branchList) {
							offCodeList.add(branch.getOfficeCode());
						}
					}
				}
			}
			RequestData requestData = new RequestData();
			
				for (String offCode : offCodeList) {
					try {
						requestData.setOffcode(offCode);
						requestData.setYearMonthFrom("201501");
						requestData.setYearMonthTo("201905");
						systemUnworkingJobService.syncData(requestData);
					} catch (Exception e) {
						System.out.println(offCode);
					}
				}
		
			systemUnworkingJobService.syncWs6010ToIaWs6010();
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("*********************");
		System.out.println("*  ===============  *");
		System.out.println("*                   *");
		System.out.println("*  Job complete!!!  *");
		System.out.println("*                   *");
		System.out.println("*  ===============  *");
		System.out.println("*********************");

		
	}

}

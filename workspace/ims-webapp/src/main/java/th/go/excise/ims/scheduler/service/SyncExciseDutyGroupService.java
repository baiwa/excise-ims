package th.go.excise.ims.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.repository.ExciseDutyGroupRepository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquirydutygroup.model.DutyGroup;
import th.go.excise.ims.ws.client.pcc.inquirydutygroup.service.InquiryDutyGroupService;

@Service
public class SyncExciseDutyGroupService {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncExciseDutyGroupServiceTest.class);

	@Autowired
	private InquiryDutyGroupService inquiryDutyGroupService;
	
	@Autowired
	private ExciseDutyGroupRepository exciseDutyGroupRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData");
		
		DutyGroup requestData = new DutyGroup();
		List<DutyGroup> dutyGroupList = inquiryDutyGroupService.execute(requestData);
		
		exciseDutyGroupRepository.queryUpdateIsDeletedY();
		exciseDutyGroupRepository.batchUpdate(dutyGroupList);
	}
	
}

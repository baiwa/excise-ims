package th.go.excise.ims.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.repository.ExciseDuedatePs0112Repository;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.inquiryduedateps0112.model.DuedatePs0112;
import th.go.excise.ims.ws.client.pcc.inquiryduedateps0112.service.InquiryDuedatePs0112Service;

@Service
public class SyncExciseDuedatePs0112Service {

	private static final Logger logger = LoggerFactory.getLogger(SyncExciseDuedatePs0112Service.class);

	@Autowired
	private InquiryDuedatePs0112Service inquiryDuedatePs0112Service;

	@Autowired
	private ExciseDuedatePs0112Repository exciseDuedatePs0112Repository;

	@Transactional(rollbackOn = {Exception.class})
	public void syncData() throws PccRestfulException {
		logger.info("syncData InquiryDuedatePs0112");

		List<DuedatePs0112> duedatePs0112List = inquiryDuedatePs0112Service.execute(new Object());

		exciseDuedatePs0112Repository.queryUpdateIsDeletedY();
		exciseDuedatePs0112Repository.batchMerge(duedatePs0112List);
	}
}

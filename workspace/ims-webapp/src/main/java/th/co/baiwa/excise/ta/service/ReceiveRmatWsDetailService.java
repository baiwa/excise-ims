package th.co.baiwa.excise.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.form.AccMonth0407DTLVo;
import th.co.baiwa.excise.ta.persistence.dao.ReceiveRmatWsDetailDao;

@Service
public class ReceiveRmatWsDetailService {
	
	private Logger logger = LoggerFactory.getLogger(ReceiveRmatWsDetailService.class);

	@Autowired
	private ReceiveRmatWsDetailDao receiveRmatWsDetailDao;
	
	public void insertReceiveRmatWsDetailService(List<AccMonth0407DTLVo> dataSesion) {
		logger.info("ReceiveRmatWsDetailService.insertReceiveRmatWsDetailService");
		List<Object> objArrayOfList = new ArrayList<Object>();
 		for (AccMonth0407DTLVo value : dataSesion) {
			receiveRmatWsDetailDao.insertTableReceiveRmatWsDetail(value);
		}
		
	}

}

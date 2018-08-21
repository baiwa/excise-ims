package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.form.OPEDataTable;
import th.co.baiwa.excise.ta.persistence.dao.ReceiveRmatWsDetailDao;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class ReceiveRmatWsDetailService {
	
	private Logger logger = LoggerFactory.getLogger(ReceiveRmatWsDetailService.class);

	@Autowired
	private ReceiveRmatWsDetailDao receiveRmatWsDetailDao;
	
	public void insertReceiveRmatWsDetailService(List<OPEDataTable> allData) {
	logger.info("ReceiveRmatWsDetailService.insertReceiveRmatWsDetailService");
	
	if(BeanUtils.isNotEmpty(allData.get(0).getAnalysNumber())) {
		for (OPEDataTable valueHeader : allData) {
			receiveRmatWsDetailDao.insertTableReceiveRmatWsDetail(valueHeader);
		}
	}
	else {
		for (OPEDataTable value : allData) {
			receiveRmatWsDetailDao.insertTableReceiveRmatWsDetail(value);
		}
	}
	
}

}

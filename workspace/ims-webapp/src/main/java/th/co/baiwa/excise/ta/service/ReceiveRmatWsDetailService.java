package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ta.persistence.dao.ReceiveRmatWsDetailDao;
import th.co.baiwa.excise.ta.persistence.entity.Ope041DataTable;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class ReceiveRmatWsDetailService {

	private Logger logger = LoggerFactory.getLogger(ReceiveRmatWsDetailService.class);

	@Autowired
	private ReceiveRmatWsDetailDao receiveRmatWsDetailDao;

	public void insertReceiveRmatWsDetailService(List<Ope041DataTable> allData) {
		logger.info("ReceiveRmatWsDetailService.insertReceiveRmatWsDetailService");

		if (BeanUtils.isNotEmpty(allData)) {
			for (Ope041DataTable value : allData) {
				if (BeanUtils.isNotEmpty(value.getExciseId())) {
					receiveRmatWsDetailDao.insertTableReceiveRmatWsHeader(value);
				}

				if (BeanUtils.isEmpty(value.getExciseId())) {
					receiveRmatWsDetailDao.insertTableReceiveRmatWsDetail(value);
				}
			}
		}
	}
	
}

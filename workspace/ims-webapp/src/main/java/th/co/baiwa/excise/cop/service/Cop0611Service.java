package th.co.baiwa.excise.cop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.BeanUtils;
import th.co.baiwa.excise.cop.persistence.dao.ReportCheckOperationDao;
import th.co.baiwa.excise.ta.persistence.vo.Ope041DataTable;

@Service
public class Cop0611Service {
	private Logger logger = LoggerFactory.getLogger(Cop0611Service.class);
	
	@Autowired
	private ReportCheckOperationDao reportCheckOperationDao;
	
	public void cop0611Service(List<Ope041DataTable> allData) {
		
		Long idHead = reportCheckOperationDao.saveHeadCop0611(allData.get(0));
		
		logger.info("Save Cop0611");
		for (Ope041DataTable value : allData) {
			if(!BeanUtils.isEmpty(value.getNo())) {
				reportCheckOperationDao.saveDtlCop0611(value,idHead);
			}
	
		}
	}

}

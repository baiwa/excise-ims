package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.ia.persistence.dao.ConfigRentHouseDao;

public class Int061300Service {

	private Logger logger = LoggerFactory.getLogger(Int061300Service.class);
	
	@Autowired
	private ConfigRentHouseDao ConfigRentHouseDao;
	
	public List<Lov> findManagementType(){
		try {
			return ConfigRentHouseDao.findManagementType();
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return ConfigRentHouseDao.findManagementType();
		}
		
	}
	
	
	
	
}

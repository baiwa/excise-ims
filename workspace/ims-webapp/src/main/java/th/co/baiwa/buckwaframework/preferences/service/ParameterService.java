package th.co.baiwa.buckwaframework.preferences.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.dao.ParameterGroupDao;
import th.co.baiwa.buckwaframework.preferences.persistence.dao.ParameterInfoDao;

@Service("parameterService")
public class ParameterService {
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterService.class);
	
	@Autowired
	private ParameterGroupDao parameterGroupDao;
	
	@Autowired
	private ParameterInfoDao parameterInfoDao;

	

}

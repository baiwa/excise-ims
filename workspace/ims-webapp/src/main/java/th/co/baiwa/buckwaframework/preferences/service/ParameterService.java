package th.co.baiwa.buckwaframework.preferences.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.preferences.persistence.dao.ParameterGroupDao;
import th.co.baiwa.buckwaframework.preferences.persistence.dao.ParameterInfoDao;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysParameterGroup;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysParameterInfo;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Service("parameterService")
public class ParameterService {
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterService.class);
	
	@Autowired
	private ParameterGroupDao parameterGroupDao;
	
	@Autowired
	private ParameterInfoDao parameterInfoDao;

	

}

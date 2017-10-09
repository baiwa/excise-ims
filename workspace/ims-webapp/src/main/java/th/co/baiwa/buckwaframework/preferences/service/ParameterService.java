package th.co.baiwa.buckwaframework.preferences.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.dao.ParameterGroupDao;
import th.co.baiwa.buckwaframework.preferences.persistence.dao.ParameterInfoDao;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;

@Service("parameterService")
public class ParameterService {
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterService.class);
	
	@Autowired
	private ParameterGroupDao parameterGroupDao;
	
	@Autowired
	private ParameterInfoDao parameterInfoDao;

	public List<ParameterGroup> getParameterGroupList(Integer start, Integer length) {
		logger.info("getParameterGroupList");
		
		return parameterGroupDao.findAll();
	}
	
	public ParameterGroup getParameterGroupById(Long paramGroupId) {
		logger.info("getParameterGroupById parameterGroupId=?", paramGroupId);
		
		return parameterGroupDao.findById(paramGroupId);
	}
	
	public int countParameterGroup() {
		logger.info("countMessage");
		
		return parameterGroupDao.count();
	}
	
	public ParameterGroup insertParameterGroup(ParameterGroup parameterGroup) {
		logger.info("insertParameterGroup");
		
		Long paramGroupId = parameterGroupDao.insert(parameterGroup);
		parameterGroup.setParamGroupId(paramGroupId);
		
		return parameterGroup;
	}
	
	
	public void updateParameterGroup(ParameterGroup parameterGroup) {
		logger.info("updateParameterGroup");
		
		parameterGroupDao.update(parameterGroup);
	}
	
	public void deleteParameterGroup(Long paramGroupId) {
		logger.info("deleteParameterGroup");
		
		parameterGroupDao.delete(paramGroupId);
	}

	
	public List<ParameterInfo> getParameterInfoList(Integer start, Integer length) {
		logger.info("getParameterGroupList");
		
		return parameterInfoDao.findAll();
	}
	
	public ParameterInfo getParameterInfoById(Long paramGroupId) {
		logger.info("getParameterGroupById parameterGroupId=?", paramGroupId);
		
		return parameterInfoDao.findById(paramGroupId);
	}
	
	public int countParameterInfo() {
		logger.info("countMessage");
		
		return parameterInfoDao.count();
	}
	
	public ParameterInfo insertParameterInfo(ParameterInfo parameterInfo) {
		logger.info("insertParameterInfo");
		
		Long paramInfoId = parameterInfoDao.insert(parameterInfo);
		parameterInfo.setParamInfoId(paramInfoId);
		
		return parameterInfo;
	}
	
	
	public void updateParameterInfo(ParameterInfo parameterInfo) {
		logger.info("updateParameterInfo");
		
		parameterInfoDao.update(parameterInfo);
	}
	
	public void deleteParameterInfo(Long paramInfoId) {
		logger.info("deleteParameterInfo");
		
		parameterInfoDao.delete(paramInfoId);
	}
}

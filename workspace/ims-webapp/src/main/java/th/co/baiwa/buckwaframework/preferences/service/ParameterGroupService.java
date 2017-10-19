package th.co.baiwa.buckwaframework.preferences.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.dao.ParameterGroupDao;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup;

@Service("parameterGroupService")
public class ParameterGroupService {
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterGroupService.class);
	
	@Autowired
	private ParameterGroupDao parameterGroupDao;
	
	public List<ParameterGroup> getParameterGroupList(Integer start, Integer length) {
		logger.info("getParameterGroupList");
		
		return parameterGroupDao.findAll();
	}
	
	public ParameterGroup getParameterGroupById(Long paramGroupId) {
		logger.info("getParameterGroupById paramGroupId={}", paramGroupId);
		
		return parameterGroupDao.findById(paramGroupId);
	}
	
	public int countParameterGroup() {
		logger.info("countParameterGroup");
		
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
	
}

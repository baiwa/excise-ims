package th.co.baiwa.buckwaframework.preferences.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.dao.ParameterInfoDao;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;

@Service("parameterInfoService")
public class ParameterInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterInfoService.class);
	
	@Autowired
	private ParameterInfoDao parameterInfoDao;
	
	public List<ParameterInfo> getParameterInfoList(Integer start, Integer length) {
		logger.info("getParameterInfoList");
		
		return parameterInfoDao.findAll();
	}
	
	public ParameterInfo getParameterInfoById(Long paramInfoId) {
		logger.info("getParameterInfoById paramInfoId={}", paramInfoId);
		
		return parameterInfoDao.findById(paramInfoId);
	}
	
	public int countParameterInfo() {
		logger.info("countParameterInfo");
		
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

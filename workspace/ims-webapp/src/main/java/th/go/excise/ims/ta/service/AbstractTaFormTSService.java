package th.go.excise.ims.ta.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTaFormTSService<VO extends Object, ENTITY> {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractTaFormTSService.class);
	
	protected static final String NULL = "NULL";
	
	@SuppressWarnings("unchecked")
	public Class<VO> getVoClass() {
		return ((Class<VO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
	public abstract String getReportName();
	
	public abstract byte[] processFormTS(VO vo) throws Exception;

	public abstract void saveFormTS(VO vo);
	
	public abstract byte[] generateReport(VO vo) throws Exception;
	
	public abstract List<String> getFormTsNumberList();
	
	public abstract VO getFormTS(String formTsNumber);
	
	protected void toVo(VO vo, ENTITY entity) {
		try {
			BeanUtils.copyProperties(vo, entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}
	
	protected void toEntity(ENTITY entity, VO vo) {
		try {
			BeanUtils.copyProperties(entity, vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.warn(e.getMessage(), e);
		}
	}
	
}

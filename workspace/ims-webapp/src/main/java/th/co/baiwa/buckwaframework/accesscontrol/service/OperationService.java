package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Operation;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.OperationRepository;
import th.co.baiwa.buckwaframework.accesscontrol.vo.OperationFormVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.preferences.service.MessageService;
@Service
public class OperationService {

	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	private final OperationRepository operationRepository;
	
	
	@Autowired
	public OperationService(OperationRepository operationRepository) {
		this.operationRepository = operationRepository;
	}
	
	public DataTableAjax<Operation> list(OperationFormVo operationFormVo) {
		logger.info("searchByCriteria criteria={}", ToStringBuilder.reflectionToString(operationFormVo, ToStringStyle.JSON_STYLE));
		
	
		List<Operation> operationList = operationRepository.findByCriteria(operationFormVo);
		DataTableAjax<Operation> result = new DataTableAjax<Operation>();
		result.setDraw(operationFormVo.getDraw() + 1);
		result.setData(operationList);
		result.setRecordsTotal(operationRepository.countByCriteria(operationFormVo));
		result.setRecordsFiltered(operationList.size());


		
		return result;
	}
	public List<Operation> getOperationAll() {
		logger.info("getAllOperation");
		return operationRepository.findAll();
	}

	public Operation getRoleById(Long roleId) {
		logger.info("getOperationById");
		return operationRepository.findById(roleId).get();
	}

	public long getOperationCount() {
		logger.info("getOperationCount");
		return operationRepository.count();
	}

	public Operation createOperation(Operation operation) {
		logger.info("createOperation");
		operationRepository.save(operation);
		return operation;
	}

	public Operation updateOperation(Operation operation) {
		logger.info("updateOperation");
		operationRepository.save(operation);
		return operation;
	}

	public void deleteOperation(Long operationId) {
		logger.info("deleteOperation");
		operationRepository.deleteById(operationId);
	}
	
}

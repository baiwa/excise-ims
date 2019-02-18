package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int02Vo;

@Service
public class Int02Service {
	
	@Autowired
	private IaQuestionnaireHdrJdbcRepository iaQuestionnaireHdrJdbcRepository;
	
	@Autowired
	private IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;
	
	public List<IaQuestionnaireHdr> filterQtnHdr(Int02Vo request) {
		return iaQuestionnaireHdrJdbcRepository.getDataFilter(request);
	}
	
	public IaQuestionnaireHdr findOne(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		return iaQuestionnaireHdrJdbcRepository.findOne(id);
	}
	
	public IaQuestionnaireHdr save(IaQuestionnaireHdr request) {
		return iaQuestionnaireHdrRepository.save(request);
	}
	
	public IaQuestionnaireHdr update(String idStr, IaQuestionnaireHdr request) {
		BigDecimal id = new BigDecimal(idStr);
		IaQuestionnaireHdr data = iaQuestionnaireHdrJdbcRepository.findOne(id);
		data.setBudgetYear(request.getBudgetYear());
		data.setQtnHeaderName(request.getQtnHeaderName());
		data.setNote(request.getNote());
		return iaQuestionnaireHdrRepository.save(data);
	}

}

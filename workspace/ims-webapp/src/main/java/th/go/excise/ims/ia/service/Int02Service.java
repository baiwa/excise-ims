package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int02Vo;

@Service
public class Int02Service {
	
	@Autowired
	private IaQuestionnaireHdrJdbcRepository iaQuestionnaireHdrJdbcRepository;
	
	public List<IaQuestionnaireHdr> filterQtnHdr(Int02Vo request) {
		return iaQuestionnaireHdrJdbcRepository.getDataFilter(request);
	}

}

package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideJdbcRepository;
import th.go.excise.ims.ia.vo.Int020101Vo;

@Service
public class Int020101Service {

	@Autowired
	private IaQuestionnaireSideRepository iaQuestionnaireSideRep;
	
	@Autowired
	private IaQuestionnaireSideJdbcRepository iaQuestionnaireSideJdbcRep;
	
	public List<Int020101Vo> findAll() {
		return iaQuestionnaireSideJdbcRep.findAll();
	}
	
	public List<Int020101Vo> findByIdHead(String idHeadStr) {
		BigDecimal idHead = new BigDecimal(idHeadStr);
		return iaQuestionnaireSideJdbcRep.findByIdHead(idHead);
	}
	
}

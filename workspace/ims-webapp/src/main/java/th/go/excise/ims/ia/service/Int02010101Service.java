package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int02010101Vo;

@Service
public class Int02010101Service {
	
	@Autowired
	private IaQuestionnaireSideDtlJdbcRepository iaQuestionnaireSideDtlJdbcRepository;
	
	public List<Int02010101Vo> findByIdSide(String idSideStr) {
		BigDecimal idSide = new BigDecimal(idSideStr);
		List<Int02010101Vo> main = iaQuestionnaireSideDtlJdbcRepository.findByIdSide(idSide);
		List<Int02010101Vo> detail = new ArrayList<>();
		for(int i=0; i<main.toArray().length; i++) {
			BigDecimal seq = main.get(i).getSeq();
			detail = iaQuestionnaireSideDtlJdbcRepository.findDtlByIdSide(idSide, seq);
			main.get(i).setChildren(detail);
		}
		return main;
	}
}

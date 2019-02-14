package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideJdbcRepository;
import th.go.excise.ims.ia.vo.IaQuestionnaireSideVo;

@Service
public class IaQuestionnaireSideService {

	@Autowired
	private IaQuestionnaireSideRepository iaQuestionnaireSideRep;
	
	@Autowired
	private IaQuestionnaireSideJdbcRepository iaQuestionnaireSideJdbcRep;
	
	public List<IaQuestionnaireSideVo> findAll() {
		return iaQuestionnaireSideJdbcRep.findAll();
	}
	
}

package th.co.baiwa.excise.ia.persistence.repository.qtn.fin;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.vo.QtnScore;

public interface QtnFinalRepHeaderRepositoryCustom {

	public List<QtnScore> calScore(Long headerId);
	
}

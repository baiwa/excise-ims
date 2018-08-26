package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMinor;

public interface QuestionnaireMinorDetailRepository extends CommonJpaCrudRepository<QuestionnaireMinor, Long> {

	@Query(
		value = "SELECT M.* FROM IA_QUESTIONNAIRE_MINOR_DETAIL M WHERE M.IS_DELETED='N' AND M.MAIN_ID=?",
		nativeQuery = true
	)
	public List<QuestionnaireMinor> findByMainId(Long mainId);
	
}
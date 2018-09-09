package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireHeader;

public interface QuestionnaireHeaderRepository extends CommonJpaCrudRepository<QuestionnaireHeader, Long> {
	
	@Query(
			value = "SELECT * FROM IA_QUESTIONNAIRE_HEADER H WHERE H.IS_DELETED='"+FLAG.N_FLAG+"' ORDER BY H.QTN_HEADER_ID ASC",
			nativeQuery = true
	)
	public List<QuestionnaireHeader> oderByHeaderId();

}

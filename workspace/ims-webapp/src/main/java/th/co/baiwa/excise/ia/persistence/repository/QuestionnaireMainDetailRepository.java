package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMain;

public interface QuestionnaireMainDetailRepository extends CommonJpaCrudRepository<QuestionnaireMain, Long> {
	
	@Query("select d from QuestionnaireMain d where d.headerCode = ?"
			)
	public List<QuestionnaireMain> findByHeaderCode(String headerCode);

}
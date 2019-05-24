package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsPmAssessD;

public interface WsPmAssessDRepository extends CommonJpaCrudRepository<WsPmAssessD, Long>, WsPmAssessDRepositoryCustom {

	List<WsPmAssessD> findByFormCodeAndIsDeleted(String formCode, String isDeleted);

}

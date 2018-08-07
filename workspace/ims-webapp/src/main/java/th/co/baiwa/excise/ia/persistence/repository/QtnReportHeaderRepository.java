package th.co.baiwa.excise.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;

public interface QtnReportHeaderRepository extends CommonJpaCrudRepository<QtnReportHeader, Long>, QtnReportHeaderRepositoryCustom {

}

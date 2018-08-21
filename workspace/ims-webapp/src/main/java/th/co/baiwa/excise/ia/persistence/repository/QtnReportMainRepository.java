package th.co.baiwa.excise.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportMain;

public interface QtnReportMainRepository extends CommonJpaCrudRepository<QtnReportMain, Long>, QtnReportMainRepositoryCustom {

}

package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.IaIceReportHdr;
import th.co.baiwa.excise.oa.persistence.entity.IaIceReportHdrRepositoryCustom;

public interface IaIceReportHdrRepository extends CommonJpaCrudRepository<IaIceReportHdr, Long>  ,IaIceReportHdrRepositoryCustom{

		@Query(
			value = " SELECT * "
				  + " FROM ia_ice_report_hdr WHERE is_deleted = 'N' AND subsection_name = ?",
			nativeQuery = true
		)
		public List<IaIceReportHdr> findBySubSectionName(String subSectionName);
}

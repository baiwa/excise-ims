package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.excise.domain.QtnHdrConditionVo;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;

public interface QtnMasterRepositoryCustom {

	public List<QtnMaster> findData(String sector, String area, String year, String finished);
	public List<QtnHdrConditionVo> findRiskNameAndPoint(QtnMaster qtnMaster) ;
}

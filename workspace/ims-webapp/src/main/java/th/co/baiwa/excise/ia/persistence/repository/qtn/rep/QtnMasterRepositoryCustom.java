package th.co.baiwa.excise.ia.persistence.repository.qtn.rep;

import java.util.List;

import th.co.baiwa.excise.domain.QtnHdrConditionVo;
import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnMaster;

public interface QtnMasterRepositoryCustom {

	public List<QtnMaster> findData(String sector, String area, String year, String finished);
	public List<QtnHdrConditionVo> findRiskNameAndPoint(QtnMaster qtnMaster) ;
}

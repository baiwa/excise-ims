package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.vo.QtnReportDetailVo;

public interface QtnReportDetailRepositoryCustom {

	public List<QtnReportDetailVo> findJoinFinal(Long qtnReportManId, String qtnCreator);
	
}

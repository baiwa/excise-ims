package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.vo.QtnReportHeaderVo;

public interface QtnReportHeaderRepositoryCustom {

	public List<QtnReportHeaderVo> findJoinFinal(Long masterId, String user);
	
}

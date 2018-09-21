package th.co.baiwa.excise.ia.persistence.repository.qtn.rep;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.vo.QtnReportMainVo;

public interface QtnReportMainRepositoryCustom {
	
	public List<QtnReportMainVo> findJoinFinal(Long qtnReportHdrId);

	public List<QtnReportMainVo> findJoinFinal(Long qtnReportHdrId, String qtnCreator);
	
}

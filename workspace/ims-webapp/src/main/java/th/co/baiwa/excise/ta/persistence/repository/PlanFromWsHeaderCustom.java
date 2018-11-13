package th.co.baiwa.excise.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.excise.ta.persistence.entity.PlanFromWsHeader;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetHeaderDetail;

public interface PlanFromWsHeaderCustom {
	public List<PlanWorksheetHeaderDetail> findExciseIdOrderByPercenTax(List<String> monthLIst); 
	
	public List<PlanFromWsHeader> findHeaderFromTemp(List<String> monthLIst, String analysNumber); 
//	public List<PlanWorksheetDetail> findDetailFromTemp(List<String> monthLIst, String analysNumber) ; 
	
}

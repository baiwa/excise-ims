package th.go.excise.ims.ta.persistence.repository;

import th.go.excise.ims.ta.vo.YearMonthVo;

public interface TaWorksheetCondMainHdrRepositoryCustom {
	
	public YearMonthVo findMonthStartByDraftNumber(String analysisNumber);
	
	public YearMonthVo findMonthStartByAnalysisNumber(String analysisNumber);
	
}

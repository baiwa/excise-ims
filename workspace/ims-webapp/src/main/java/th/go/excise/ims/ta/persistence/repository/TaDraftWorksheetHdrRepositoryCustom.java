package th.go.excise.ims.ta.persistence.repository;

import th.go.excise.ims.ta.vo.YearMonthVo;

public interface TaDraftWorksheetHdrRepositoryCustom {
	
	public YearMonthVo findMonthStartByAnalysisNumber(String draftNumber);
	
}

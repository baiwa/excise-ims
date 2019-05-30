package th.go.excise.ims.ta.service;

import org.apache.commons.lang3.StringUtils;

import th.go.excise.ims.ta.vo.AnalysisFormVo;

public abstract class AbstractBasicAnalysisService<VO extends Object> {

	public VO inquiry(AnalysisFormVo formVo) {
		if (StringUtils.isEmpty(formVo.getPaperBaNumber())) {
			return inquiryByWs(formVo);
		} else {
			return inquiryByPaperBaNumber(formVo);
		}
	};

	protected abstract VO inquiryByWs(AnalysisFormVo formVo);

	protected abstract VO inquiryByPaperBaNumber(AnalysisFormVo formVo);

}

package th.go.excise.ims.ta.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;

public abstract class AbstractBasicAnalysisService<VO> {

	public List<VO> inquiry(BasicAnalysisFormVo formVo) {
		if (StringUtils.isEmpty(formVo.getPaperBaNumber())) {
			return inquiryByWs(formVo);
		} else {
			return inquiryByPaperBaNumber(formVo);
		}
	};

	protected abstract List<VO> inquiryByWs(BasicAnalysisFormVo formVo);

	protected abstract List<VO> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo);

	protected abstract void save(BasicAnalysisFormVo formVo);

}

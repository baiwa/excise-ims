package th.go.excise.ims.ta.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import th.go.excise.ims.ta.vo.ProductPaperFormVo;

public abstract class AbstractProductPaperService<VO> {
	
	protected static final String EXPORT_TYPE_CREATE = "001";
	protected static final String EXPORT_TYPE_PR_NUM = "002";
	
	public List<VO> inquiry(ProductPaperFormVo formVo) {
		if (StringUtils.isEmpty(formVo.getPaperPrNumber())) {
			return inquiryByWs(formVo);
		} else {
			return inquiryByPaperPrNumber(formVo);
		}
	};

	protected abstract List<VO> inquiryByWs(ProductPaperFormVo formVo);

	protected abstract List<VO> inquiryByPaperPrNumber(ProductPaperFormVo formVo);
	
	public byte[] export(ProductPaperFormVo formVo) {
		List<VO> voList = null;
		String exportType = null;
		if (StringUtils.isEmpty(formVo.getPaperPrNumber())) {
			voList = inquiryByWs(formVo);
			exportType = EXPORT_TYPE_CREATE;
		} else {
			voList = inquiryByPaperPrNumber(formVo);
			exportType = EXPORT_TYPE_PR_NUM;
		}
		return exportData(voList, exportType);
	}
	
	protected abstract byte[] exportData(List<VO> voList, String exportType);
	
//	protected abstract List<VO> upload();
	
//	protected abstract save();
	
}

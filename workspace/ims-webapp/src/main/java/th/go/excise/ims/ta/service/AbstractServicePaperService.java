package th.go.excise.ims.ta.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import th.go.excise.ims.ta.vo.ServicePaperFormVo;

public abstract class AbstractServicePaperService<VO> {
	
	protected static final String EXPORT_TYPE_CREATE = "001";
	protected static final String EXPORT_TYPE_SV_NUM = "002";
	
	public List<VO> inquiry(ServicePaperFormVo formVo) {
		if (StringUtils.isEmpty(formVo.getPaperSvNumber())) {
			return inquiryByWs(formVo);
		} else {
			return inquiryByPaperSvNumber(formVo);
		}
	};
	
	protected abstract List<VO> inquiryByWs(ServicePaperFormVo formVo);
	
	protected abstract List<VO> inquiryByPaperSvNumber(ServicePaperFormVo formVo);
	
	public byte[] export(ServicePaperFormVo formVo) {
		List<VO> voList = null;
		String exportType = null;
		if (StringUtils.isEmpty(formVo.getPaperSvNumber())) {
			voList = inquiryByWs(formVo);
			exportType = EXPORT_TYPE_CREATE;
		} else {
			voList = inquiryByPaperSvNumber(formVo);
			exportType = EXPORT_TYPE_SV_NUM;
		}
		return exportData(voList, exportType);
	}
	
	protected abstract byte[] exportData(List<VO> voList, String exportType);
	
	protected abstract List<VO> uploadData(ServicePaperFormVo formVo);
	
	protected abstract void saveData(ServicePaperFormVo formVo);
	
	protected abstract List<String> getPaperSvNumberList(ServicePaperFormVo formVo);
	
}

package th.go.excise.ims.ta.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.reflect.TypeToken;

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
	
	public abstract List<VO> upload(ProductPaperFormVo formVo);
	
	public abstract void save(ProductPaperFormVo formVo);
	
	public abstract List<String> getPaperPrNumberList(ProductPaperFormVo formVo);
	
	protected Type getListVoType() {
		Type voType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return TypeToken.getParameterized(List.class, voType).getType();
	}
	
	protected LocalDate toLocalDate(String inputDate) {
		return LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(inputDate.split("/")[1]), Integer.parseInt(inputDate.split("/")[0]), 1));
	}
	
}

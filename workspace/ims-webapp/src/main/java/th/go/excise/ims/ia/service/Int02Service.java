package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Service
public class Int02Service {

	@Autowired
	private IaQuestionnaireHdrJdbcRepository iaQuestionnaireHdrJdbcRepository;
	
	@Autowired
	private IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;

	public DataTableAjax<Int02Vo> filterQtnHdr(Int02FormVo request) {
		
		List<Int02Vo> data = iaQuestionnaireHdrJdbcRepository.getDataFilter(request);
		/* convert date to string */
		for (Int02Vo obj : data) {
			DateTimeFormatter d = DateTimeFormatter.ofPattern(ProjectConstant.SHORT_DATE_FORMAT, Locale.US);
			obj.getCreatedDate().format(d);
			
			if(obj.getCreatedDate() != null) {
				obj.setCreatedDateStr(obj.getCreatedDate().format(d));
			}
			if(obj.getUpdatedDate() != null) {
				obj.setUpdatedDateStr(obj.getUpdatedDate().format(d));
			}
		}
		
		DataTableAjax<Int02Vo> dataTableAjax = new DataTableAjax<Int02Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(iaQuestionnaireHdrJdbcRepository.countDatafilter(request));
		dataTableAjax.setRecordsFiltered(data.size());
		
		return dataTableAjax;
	}
	
	public IaQuestionnaireHdr findOne(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		return iaQuestionnaireHdrJdbcRepository.findOne(id);
	}
	
	public IaQuestionnaireHdr save(IaQuestionnaireHdr request) {
		return iaQuestionnaireHdrRepository.save(request);
	}
	
	public IaQuestionnaireHdr update(String idStr, IaQuestionnaireHdr request) {
		BigDecimal id = new BigDecimal(idStr);
		IaQuestionnaireHdr data = iaQuestionnaireHdrJdbcRepository.findOne(id);
		data.setBudgetYear(request.getBudgetYear());
		data.setQtnHeaderName(request.getQtnHeaderName());
		data.setNote(request.getNote());
		return iaQuestionnaireHdrRepository.save(data);
	}

}

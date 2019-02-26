package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.MessageContants;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
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
			/* to string status */
			obj.setStatus(MessageContants.IA.qtnStatus(obj.getStatus()));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ProjectConstant.SHORT_DATE_FORMAT);

			if (obj.getCreatedDate() != null) {
				Date createdDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setCreatedDateStr(ConvertDateUtils.formatDateToString(createdDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getUpdatedDate() != null) {
				Date updatedDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setUpdatedDateStr(ConvertDateUtils.formatDateToString(updatedDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getStartDate() != null) {
				Date startDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setStartDateStr(ConvertDateUtils.formatDateToString(startDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getEndDate() != null) {
				Date endDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setEndDateStr(ConvertDateUtils.formatDateToString(endDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
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
		request.setStatus("WAIT_HDR");
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

	public void updateStatus(String idStr) {
		Optional<IaQuestionnaireHdr> dataRes = iaQuestionnaireHdrRepository.findById(new BigDecimal(idStr));
		if (dataRes.isPresent()) {
			IaQuestionnaireHdr daraHdr = dataRes.get();
			daraHdr.setStatus("FAIL_HDR");
			iaQuestionnaireHdrRepository.save(daraHdr);
		}
	}

}

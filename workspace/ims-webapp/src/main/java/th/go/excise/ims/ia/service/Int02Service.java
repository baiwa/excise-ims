package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMade;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireHdrJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireMadeHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int020101SideVo;
import th.go.excise.ims.ia.vo.Int020101UpdateVo;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Service
public class Int02Service {

	@Autowired
	private IaQuestionnaireHdrJdbcRepository iaQuestionnaireHdrJdbcRepository;

	@Autowired
	private IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;

	@Autowired
	private IaQuestionnaireMadeHdrRepository iaQuestionnaireMadeHdrRepository;

	@Autowired
	private IaQuestionnaireMadeHdrJdbcRepository iaQuestionnaireMadeHdrJdbcRepository;

	@Autowired
	private IaQuestionnaireMadeRepository iaQuestionnaireMadeRepository;
	
	@Autowired
	private IaQuestionnaireSideRepository iaQuestionnaireSideRepository;

	public DataTableAjax<Int02Vo> filterQtnHdr(Int02FormVo request) {

		List<Int02Vo> data = iaQuestionnaireHdrJdbcRepository.getDataFilter(request);
		/* convert date to string */
		for (Int02Vo obj : data) {
			/* to string statusStr */
			obj.setStatusStr(ApplicationCache.getParamInfoByCode("IA_STATUS", obj.getStatus()).getValue1());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ProjectConstant.SHORT_DATE_FORMAT);

			if (obj.getCreatedDate() != null) {
				Date createdDate = ConvertDateUtils.parseStringToDate(obj.getCreatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setCreatedDateStr(ConvertDateUtils.formatDateToString(createdDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getUpdatedDate() != null) {
				Date updatedDate = ConvertDateUtils.parseStringToDate(obj.getUpdatedDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setUpdatedDateStr(ConvertDateUtils.formatDateToString(updatedDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getStartDate() != null) {
				Date startDate = ConvertDateUtils.parseStringToDate(obj.getStartDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setStartDateStr(ConvertDateUtils.formatDateToString(startDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
			if (obj.getEndDate() != null) {
				Date endDate = ConvertDateUtils.parseStringToDate(obj.getEndDate().format(formatter),
						ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN);
				obj.setEndDateStr(ConvertDateUtils.formatDateToString(endDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
			}
		}

		DataTableAjax<Int02Vo> dataTableAjax = new DataTableAjax<Int02Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(iaQuestionnaireHdrJdbcRepository.countDatafilterStatusTreeFour(request));
		dataTableAjax.setRecordsFiltered(iaQuestionnaireHdrJdbcRepository.countDatafilterStatusTreeFour(request));

		return dataTableAjax;
	}

	public IaQuestionnaireHdr findOne(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		IaQuestionnaireHdr hdr = iaQuestionnaireHdrJdbcRepository.findOne(id);
//		hdr.setStatus(ApplicationCache.getParamInfoByCode("IA_STATUS", hdr.getStatus()).getValue1());
		return hdr;
	}

	public IaQuestionnaireHdr save(IaQuestionnaireHdr request) {
		return iaQuestionnaireHdrRepository.save(request);
	}

	public IaQuestionnaireHdr update(String idStr, Int020101UpdateVo res) {
		updateSeq(idStr, res);
		IaQuestionnaireHdr request = new IaQuestionnaireHdr();

		request.setBudgetYear(res.getBudgetYear());
		request.setId(res.getId());
		request.setQtnHeaderName(res.getQtnHeaderName());
		request.setNote(res.getNote());
		request.setStartDate(res.getStartDate());
		request.setEndDate(res.getEndDate());
		request.setStatus(res.getStatus());

		BigDecimal id = new BigDecimal(idStr);
		IaQuestionnaireHdr data = iaQuestionnaireHdrJdbcRepository.findOne(id);
		data.setBudgetYear(request.getBudgetYear());
		data.setQtnHeaderName(request.getQtnHeaderName());
		data.setNote(request.getNote());

		/* update table Questionnaire-Made-Hdr */
//		List<IaQuestionnaireMadeHdr> dataMadeHdr = iaQuestionnaireMadeHdrRepository.findByIdHdr(request.getId());
//		for (IaQuestionnaireMadeHdr objMadeHdr : dataMadeHdr) {
//			if(!"FINISH".equals(objMadeHdr.getStatus())) {
//				objMadeHdr.setQtnHeaderName(request.getQtnHeaderName());
//				objMadeHdr.setNote(request.getNote());
//				iaQuestionnaireMadeHdrRepository.save(objMadeHdr);
//			}
//		}
		List<IaQuestionnaireMadeHdr> madeHdrList = iaQuestionnaireMadeHdrJdbcRepository.findMadeHdrByIdHdr(request,
				UserLoginUtils.getCurrentUserBean().getOfficeCode());
		for (IaQuestionnaireMadeHdr madeHdr : madeHdrList) {
			madeHdr.setQtnHeaderName(request.getQtnHeaderName());
			madeHdr.setNote(request.getNote());
			iaQuestionnaireMadeHdrRepository.save(madeHdr);
		}

		return iaQuestionnaireHdrRepository.save(data);
	}

	@Transactional
	public void updateSeq(String idStr, Int020101UpdateVo res) {
		List<Int020101SideVo> side = res.getSide();
		IaQuestionnaireSide sideData = null;
		for (Int020101SideVo sideVo : side) {
			sideData = new IaQuestionnaireSide();
			sideData = iaQuestionnaireSideRepository.findById(sideVo.getId()).get();
			sideData.setSeq(new BigDecimal(sideVo.getSeq()));
			iaQuestionnaireSideRepository.save(sideData);
		}
	}

	public void updateStatus(String idStr) {
		Optional<IaQuestionnaireHdr> dataRes = iaQuestionnaireHdrRepository.findById(new BigDecimal(idStr));
		if (dataRes.isPresent()) {
			IaQuestionnaireHdr daraHdr = dataRes.get();

			if (IaConstants.IA_STATUS.STATUS_4_CODE.equals(daraHdr.getStatus())) {
				/* update status */
//				daraHdr.setStatus("FAIL_SEND_QTN");
				iaQuestionnaireHdrRepository.save(daraHdr);

				List<IaQuestionnaireMadeHdr> dataMadeHdr = iaQuestionnaireMadeHdrRepository.findByIdHdrAndIsDeleted(daraHdr.getId(), "N");
				for (IaQuestionnaireMadeHdr madeHdr : dataMadeHdr) {
//					madeHdr.setStatus("FAIL_SEND_QTN");
					iaQuestionnaireMadeHdrRepository.save(madeHdr);
					/* update status questionnaire made dtl */
					List<IaQuestionnaireMade> madeDtlList = iaQuestionnaireMadeRepository.findByIdMadeHdrAndIsDeleted(madeHdr.getId(), "N");
					for (IaQuestionnaireMade madeDtl : madeDtlList) {
//						madeDtl.setStatus("FAIL_SEND_QTN");
						iaQuestionnaireMadeRepository.save(madeDtl);
					}
				}
			} else {
				iaQuestionnaireHdrRepository.deleteById(daraHdr.getId());
			}
		}
	}

}

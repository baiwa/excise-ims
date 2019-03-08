package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMade;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.entity.IaRiskQtnConfig;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskQtnConfigRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int02010101Vo;
import th.go.excise.ims.ia.vo.Int0201FormVo;
import th.go.excise.ims.ia.vo.Int0201FormVo2;
import th.go.excise.ims.ia.vo.Int0201Vo;

@Service
public class Int0201Service {
	private static final Logger logger = LoggerFactory.getLogger(Int0201Service.class);

	@Autowired
	private IaQuestionnaireSideRepository iaQuestionnaireSideRepository;

	@Autowired
	private IaQuestionnaireSideDtlJdbcRepository iaQuestionnaireSideDtlJdbcRepository;

	@Autowired
	private IaQuestionnaireMadeRepository iaQuestionnaireMadeRepository;

	@Autowired
	private IaQuestionnaireHdrRepository iaQuestionnaireHdrRepository;

	@Autowired
	private IaQuestionnaireMadeHdrRepository iaQuestionnaireMadeHdrRepository;

	@Autowired
	private IaRiskQtnConfigRepository iaRiskQtnConfigRepository;

	public List<IaQuestionnaireSide> findQtnSideById(Int0201FormVo request) {
		return iaQuestionnaireSideRepository.findByidHead(request.getId());
	}
	
	public IaQuestionnaireHdr findQtnHdrById(BigDecimal id) {
		return iaQuestionnaireHdrRepository.findById(id).get();
	}

	public Int0201Vo findQtnSideDtlById(Int0201FormVo2 request) {
		List<Int02010101Vo> dataHdr = null;
		List<Int02010101Vo> dataDtl = null;
		List<Int02010101Vo> dataDtls = null;
		List<List<Int02010101Vo>> dataRes = new ArrayList<List<Int02010101Vo>>();

		Int0201Vo response = new Int0201Vo();
		for (Int0201FormVo dataRequest : request.getRequest()) {
			dataHdr = new ArrayList<Int02010101Vo>();
			dataHdr = iaQuestionnaireSideDtlJdbcRepository.findByIdSide(dataRequest.getId());

			for (Int02010101Vo objHdr : dataHdr) {
				dataDtl = new ArrayList<Int02010101Vo>();
				dataDtl = iaQuestionnaireSideDtlJdbcRepository.findDtlByIdSide(objHdr.getIdSide(), objHdr.getSeq(),
						objHdr.getId());
				for (Int02010101Vo objDtl : dataDtl) {
					dataDtls = new ArrayList<Int02010101Vo>();
					dataDtls = iaQuestionnaireSideDtlJdbcRepository.findDtlsByIdSide(objDtl.getIdSide(),
							objDtl.getSeqDtl(), objDtl.getId());
					objDtl.setChildren(dataDtls);
				}
				objHdr.setChildren(dataDtl);
			}
			dataRes.add(dataHdr);
		}
		response.setData(dataRes);

		return response;
	}

	@Transactional
	public void sendQtnForm(Int0201FormVo request) {
		/* update Questionnaire Header */
		if (request.getIdHead() != null) {
			Optional<IaQuestionnaireHdr> hdrRes = iaQuestionnaireHdrRepository.findById(request.getIdHead());
			if (hdrRes.isPresent()) {
				IaQuestionnaireHdr dataHdr = hdrRes.get();
				dataHdr.setStartDate(ConvertDateUtils.parseStringToLocalDate(request.getStartDateSend(),
						ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setEndDate(ConvertDateUtils.parseStringToLocalDate(request.getEndDateSend(),
						ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setStatus("SUCCESS_HDR");
				iaQuestionnaireHdrRepository.save(dataHdr);
			}
		}

		/* check status for save or update or delete */
		if ("SUCCESS_HDR".equals(request.getStatus())) {
			logger.info("delete QtnMade by idSideDtl");
			/* find id made header from request */
			List<IaQuestionnaireMade> filterQtnMade = iaQuestionnaireMadeRepository
					.findByIdSideDtl(request.getQtnMadeList().get(0).getIdSideDtl());

			if (request.getQtnMadeList().size() > 0) {
				for (IaQuestionnaireMade sideDtl : request.getQtnMadeList()) {
					iaQuestionnaireMadeRepository.deleteByIdSideDtl(sideDtl.getIdSideDtl());
				}
			}
//			updateQtnMadeHdr(request);

			/* save Questionnaire Made */
			if (filterQtnMade.size() > 0) {
				for (IaQuestionnaireMade filter: filterQtnMade) {
					saveQtnMade(request, filter.getOfficeCode(), filter.getIdMadeHdr());
				}
			}
		} else {
			logger.info("save QtnMadeHdr");

			/* find id of Questionnaire Header */
			if (request.getIdHead() != null) {
				Optional<IaQuestionnaireHdr> hdrRes = iaQuestionnaireHdrRepository.findById(request.getIdHead());
				List<ExciseDept> sectors = ApplicationCache.getExciseSectorList();
				List<ExciseDept> areas = new ArrayList<>();
				List<String> officeCodes = new ArrayList<>();
				for(ExciseDept sector: sectors) {
					areas = ApplicationCache.getExciseAreaList(sector.getOfficeCode());
					officeCodes.add(sector.getOfficeCode());
					for(ExciseDept area: areas) {
						officeCodes.add(area.getOfficeCode());
					}
				}
				if (hdrRes.isPresent()) {
					IaQuestionnaireHdr dataHdr = hdrRes.get();
					IaQuestionnaireMadeHdr dataMadeHdr = null;
					for (String officeCode: officeCodes) {
						dataMadeHdr = new IaQuestionnaireMadeHdr();
						dataMadeHdr.setIdHdr(dataHdr.getId());
						dataMadeHdr.setBudgetYear(dataHdr.getBudgetYear());
						dataMadeHdr.setNote(dataHdr.getNote());
						dataMadeHdr.setQtnHeaderName(dataHdr.getQtnHeaderName());
						dataMadeHdr.setStartDate(ConvertDateUtils.parseStringToLocalDate(
								request.getStartDateSend(), ProjectConstant.SHORT_DATE_FORMAT));
						dataMadeHdr.setEndDate(ConvertDateUtils.parseStringToLocalDate(request.getEndDateSend(),
								ProjectConstant.SHORT_DATE_FORMAT));
						dataMadeHdr.setStatus("CREATED");
						dataMadeHdr.setOfficeCode(officeCode);
						IaQuestionnaireMadeHdr resMadeHdr = iaQuestionnaireMadeHdrRepository.save(dataMadeHdr);

						/* save Questionnaire Made */
						saveQtnMade(request, officeCode, resMadeHdr.getId());
					} // end loop 1
				}
			}
		}
	}

	/* update Questionnaire Made Header */
	@Transactional
	private void updateQtnMadeHdr(Int0201FormVo request) {
		logger.info("update QtnMadeHdr");

		/* find id of Questionnaire Made Header */
		if (request.getIdHead() != null) {
			List<IaQuestionnaireMadeHdr> filterQtnMadeHdr = iaQuestionnaireMadeHdrRepository
					.findByIdHdr(request.getIdHead());
			if (filterQtnMadeHdr.size() > 0) {
				/* find id of Questionnaire Header */
				Optional<IaQuestionnaireHdr> filterQtnHdr = iaQuestionnaireHdrRepository.findById(request.getIdHead());
				if (filterQtnHdr.isPresent()) {
					IaQuestionnaireHdr dataHdr = filterQtnHdr.get();
					int loopCount = 0;

					/* compare data QtnMadeHdr and QtnHdr */
					for (IaQuestionnaireMadeHdr objMadeHdr : filterQtnMadeHdr) {
						objMadeHdr.setBudgetYear(dataHdr.getBudgetYear());
						objMadeHdr.setNote(dataHdr.getNote());
						objMadeHdr.setQtnHeaderName(dataHdr.getQtnHeaderName());
						objMadeHdr.setStartDate(dataHdr.getStartDate());
						objMadeHdr.setEndDate(dataHdr.getEndDate());
						objMadeHdr.setUpdatedBy(dataHdr.getUpdatedBy());
						objMadeHdr.setUpdatedDate(dataHdr.getUpdatedDate());
						objMadeHdr.setOfficeCode("0" + loopCount + "0000");
						iaQuestionnaireMadeHdrRepository.save(objMadeHdr);
						loopCount++;

						/* update Questionnaire Made Detail */
						if (request.getQtnMadeList().size() > 0) {
							for (IaQuestionnaireMade madeDtl : request.getQtnMadeList()) {
								List<IaQuestionnaireMade> filterQtnMadeDtl = iaQuestionnaireMadeRepository
										.findByIdSideDtl(madeDtl.getIdSideDtl());
								if (filterQtnMadeDtl.size() > 0) {
									for (IaQuestionnaireMade objQtnMadeDtl : filterQtnMadeDtl) {
										objQtnMadeDtl.setUpdatedBy(UserLoginUtils.getCurrentUsername());
										iaQuestionnaireMadeRepository.save(objQtnMadeDtl);
									} // end loop 3
								}
							} // end loop 2
						}
					} // end loop 1
				}
			}
		}
	}

	/* save Questionnaire Made */
	@Transactional
	private void saveQtnMade(Int0201FormVo request, String officeCode, BigDecimal idMadeHdr) {
		logger.info("save QtnMadeHdr");
		IaQuestionnaireMade qtnMade = null;
		if (request.getQtnMadeList().size() > 0) {
			for (IaQuestionnaireMade objMade : request.getQtnMadeList()) {
				qtnMade = new IaQuestionnaireMade();
				qtnMade.setIdSideDtl(objMade.getIdSideDtl());
				qtnMade.setQtnLevel(objMade.getQtnLevel());
				qtnMade.setStatus("CREATED");
				qtnMade.setOfficeCode(officeCode);
				qtnMade.setIdMadeHdr(idMadeHdr);
				iaQuestionnaireMadeRepository.save(qtnMade);
			} // end loop 2
		}
	}

	/*
	 * ==================== == CONFIGS `START`== ====================
	 */
	public IaRiskQtnConfig findConfigByIdQtnHdr(String idQtnHdrStr) {
		BigDecimal idQtnHdr = new BigDecimal(idQtnHdrStr);
		// Find
		IaRiskQtnConfig risk = iaRiskQtnConfigRepository.findByIdQtnHdrAndIsDeleted(idQtnHdr, "N");
		return risk;
	}
	public IaRiskQtnConfig saveConfig(IaRiskQtnConfig request) throws Exception {
		// Save
		IaRiskQtnConfig risk = iaRiskQtnConfigRepository.save(request);
		if (risk != null) {
			// Response here
			return risk;
		}
		throw new Exception();
	}
	public IaRiskQtnConfig updateConfig(IaRiskQtnConfig request) throws Exception {
		// Find
		IaRiskQtnConfig risk = iaRiskQtnConfigRepository.findByIdQtnHdrAndIsDeleted(request.getIdQtnHdr(), "N");
		// Update here
		risk.setLow(request.getLow());
		risk.setLowStart(request.getLowStart());
		risk.setLowRating(request.getLowRating());
		risk.setLowColor(request.getLowColor());

		risk.setMedium(request.getMedium());
		risk.setMediumStart(request.getMediumStart());
		risk.setMediumEnd(request.getMediumEnd());
		risk.setMediumRating(request.getMediumRating());
		risk.setMediumColor(request.getMediumColor());
		risk.setMediumCondition(request.getMediumCondition());

		risk.setHigh(request.getHigh());
		risk.setHighStart(request.getHighStart());
		risk.setHighRating(request.getHighRating());
		risk.setHighColor(request.getHighColor());
		risk.setHighCondition(request.getHighCondition());

		risk = iaRiskQtnConfigRepository.save(risk);
		
		if (risk != null) {
			return risk;
		}
		throw new Exception();
	}
	/*
	 * ==================== == CONFIGS `END`== ====================
	 */

}

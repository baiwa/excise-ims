package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
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

	public void sendQtnForm(Int0201FormVo request) {
		/* update Questionnaire Header */
		if (request.getIdHead() != null) {
			Optional<IaQuestionnaireHdr> hdrRes = iaQuestionnaireHdrRepository.findById(request.getIdHead());
			if (hdrRes.isPresent()) {
				IaQuestionnaireHdr dataHdr = hdrRes.get();
				dataHdr.setStartDate(ConvertDateUtils.parseStringThaiDateToLocalDate(request.getStartDateSend(),
						ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setEndDate(ConvertDateUtils.parseStringThaiDateToLocalDate(request.getEndDateSend(),
						ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setStatus("SUCCESS_HDR");
				iaQuestionnaireHdrRepository.save(dataHdr);
			}
		}

		/* check status for save or update or delete */
		if ("ส่งแบบสอบถามเรียบร้อย".equals(request.getStatus())) {
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
				for (int i = 0; i < filterQtnMade.size(); i++) {
					saveQtnMade(request, i, filterQtnMade.get(i).getIdMadeHdr());
				}
			}
		} else {
			logger.info("save QtnMadeHdr");

			/* find id of Questionnaire Header */
			if (request.getIdHead() != null) {
				Optional<IaQuestionnaireHdr> hdrRes = iaQuestionnaireHdrRepository.findById(request.getIdHead());
				if (hdrRes.isPresent()) {
					IaQuestionnaireHdr dataHdr = hdrRes.get();
					IaQuestionnaireMadeHdr dataMadeHdr = null;
					int loopCount = 3;

					for (int i = 0; i < loopCount; i++) {
						dataMadeHdr = new IaQuestionnaireMadeHdr();
						dataMadeHdr.setIdHdr(dataHdr.getId());
						dataMadeHdr.setBudgetYear(dataHdr.getBudgetYear());
						dataMadeHdr.setNote(dataHdr.getNote());
						dataMadeHdr.setQtnHeaderName(dataHdr.getQtnHeaderName());
						dataMadeHdr.setStartDate(ConvertDateUtils.parseStringThaiDateToLocalDate(
								request.getStartDateSend(), ProjectConstant.SHORT_DATE_FORMAT));
						dataMadeHdr.setEndDate(ConvertDateUtils.parseStringThaiDateToLocalDate(request.getEndDateSend(),
								ProjectConstant.SHORT_DATE_FORMAT));
						dataMadeHdr.setStatus("CREATED");
						dataMadeHdr.setOfficeCode("0" + i + "0000");
						IaQuestionnaireMadeHdr resMadeHdr = iaQuestionnaireMadeHdrRepository.save(dataMadeHdr);

						/* save Questionnaire Made */
						saveQtnMade(request, i, resMadeHdr.getId());
					} // end loop 1
				}
			}
		}
	}

	/* update Questionnaire Made Header */
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
										objQtnMadeDtl.setCheckFlag("F");
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
	private void saveQtnMade(Int0201FormVo request, int index, BigDecimal idMadeHdr) {
		logger.info("save QtnMadeHdr");
		IaQuestionnaireMade qtnMade = null;
		if (request.getQtnMadeList().size() > 0) {
			for (IaQuestionnaireMade objMade : request.getQtnMadeList()) {
				qtnMade = new IaQuestionnaireMade();
				qtnMade.setIdSideDtl(objMade.getIdSideDtl());
				qtnMade.setQtnLevel(objMade.getQtnLevel());
				qtnMade.setStatus("CREATED");
				qtnMade.setOfficeCode("0" + index + "0000");
				qtnMade.setIdMadeHdr(idMadeHdr);
				qtnMade.setCheckFlag("F");
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
		Optional<IaRiskQtnConfig> risk = iaRiskQtnConfigRepository.findByIdQtnHdr(idQtnHdr);
		if (risk.isPresent()) {
			// Response here
			return risk.get();
		}
		return new IaRiskQtnConfig();
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
		Optional<IaRiskQtnConfig> riskOpt = iaRiskQtnConfigRepository.findById(request.getId());
		if (riskOpt.isPresent()) {
			// Update here
			IaRiskQtnConfig risk = iaRiskQtnConfigRepository.save(riskOpt.get());
			return risk;
		}
		throw new Exception();
	}
	/*
	 * ==================== == CONFIGS `END`== ====================
	 */

}

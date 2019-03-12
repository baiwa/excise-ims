package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMade;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireMadeJdbcRepository;
import th.go.excise.ims.ia.vo.Int020201DtlVo;
import th.go.excise.ims.ia.vo.Int020201JoinVo;
import th.go.excise.ims.ia.vo.Int020201SidesFormVo;
import th.go.excise.ims.ia.vo.Int020201Vo;

@Service
public class Int020201Service {

	@Autowired
	private IaQuestionnaireMadeJdbcRepository iaQuestionnaireMadeJdbcRepository;

	@Autowired
	private IaQuestionnaireSideRepository iaQuestionnaireSideRepository;

	@Autowired
	private IaQuestionnaireMadeRepository iaQuestionnaireMadeRepository;

	@Autowired
	private IaQuestionnaireMadeHdrRepository iaQuestionnaireMadeHdrRepository;

	public List<IaQuestionnaireSide> findQtnSideById(Int020201SidesFormVo request) {
		return iaQuestionnaireSideRepository.findByidHeadAndIsDeleted(request.getIdSide(), "N");
	}

	public IaQuestionnaireMadeHdr findQtnMadeHdrById(BigDecimal id) {
		return iaQuestionnaireMadeHdrRepository.findById(id).get();
	}

	public Int020201Vo findQtnSideDtlById(Int020201Vo dataQtn) {
		List<Int020201JoinVo> dataLVL1 = null;
		List<Int020201JoinVo> dataLVL2 = null;
		List<Int020201JoinVo> dataLVL3 = null;

		for (Int020201SidesFormVo requestSide : dataQtn.getHeader()) {
			/* initial variable 'checkNull' */
			Integer checkNull = 0;
			dataLVL1 = new ArrayList<Int020201JoinVo>();
			dataLVL1 = iaQuestionnaireMadeJdbcRepository.findLvl1ByIdMadeHdr(requestSide);

			for (Int020201JoinVo objLVL1 : dataLVL1) {
				dataLVL2 = new ArrayList<Int020201JoinVo>();
				dataLVL2 = iaQuestionnaireMadeJdbcRepository.findLvl2ByIdMadeHdr(objLVL1);
				objLVL1.setChildren(dataLVL2);
				/* check children level-1 */
				if (dataLVL2.size() > 0) {
					/* level 1 have children */
					for (Int020201JoinVo objLVL2 : dataLVL2) {
						dataLVL3 = new ArrayList<Int020201JoinVo>();
						dataLVL3 = iaQuestionnaireMadeJdbcRepository.findLvl3ByIdMadeHdr(objLVL2);
						objLVL2.setChildren(dataLVL3);

						if (dataLVL3.size() == 0) {
							/* level-2 not have children */
							checkNull += iaQuestionnaireMadeJdbcRepository.countCheckNull(objLVL2, 2);
						} else {
							/* check null level-3 */
							for (Int020201JoinVo objLVL3 : dataLVL3) {
								checkNull += iaQuestionnaireMadeJdbcRepository.countCheckNull(objLVL3, 3);
							}
						}
					}
				} else {
					/* level-1 not have children */
					checkNull += iaQuestionnaireMadeJdbcRepository.countCheckNull(objLVL1, 1);
				}
			}
			requestSide.setSides(dataLVL1);
			if (checkNull > 0) {
				requestSide.setStatusSides(false);
			} else {
				requestSide.setStatusSides(true);
			}
		}
		return dataQtn;
	}

	public void updateQtnMadeByRequest(Int020201DtlVo request) throws Exception {
		if (request.getQtnMadeList().size() > 0) {
			for (IaQuestionnaireMade objMadeDtl : request.getQtnMadeList()) {
				/* check data */
				Optional<IaQuestionnaireMade> resMadeDtl = iaQuestionnaireMadeRepository.findById(objMadeDtl.getId());
				if (resMadeDtl.isPresent()) {
					IaQuestionnaireMade madeDtl = resMadeDtl.get();
					madeDtl.setNote(objMadeDtl.getNote());
					madeDtl.setCheckFlag(objMadeDtl.getCheckFlag());

					if ("CREATED".equals(request.getStatus())) {
						madeDtl.setStatus("WAIT");
					}

					if (request.getFlagConfirm()) {
						madeDtl.setStatus("FINISH");
					}
					/* update 'status' questionnaire-made-header */
					updateStatusQtnMadeHdr(request);
					iaQuestionnaireMadeRepository.save(madeDtl);
				}
			}
		}
	}

	private void updateStatusQtnMadeHdr(Int020201DtlVo request) {
		Optional<IaQuestionnaireMadeHdr> resMadeHdr = iaQuestionnaireMadeHdrRepository.findById(request.getIdMadeHdr());
		if (resMadeHdr.isPresent()) {
			IaQuestionnaireMadeHdr madeHdr = resMadeHdr.get();
			if ("CREATED".equals(request.getStatus()) && !request.getFlagConfirm()) {
				madeHdr.setStatus("WAIT");
			}
			/* confirm send questionnaire form */
			if (request.getFlagConfirm()) {
				madeHdr.setStatus("FINISH");
			}
			iaQuestionnaireMadeHdrRepository.save(madeHdr);
		}
	}

}

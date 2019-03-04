package th.go.excise.ims.ia.service;

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
import th.go.excise.ims.ia.vo.Int020201SidesVo;
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

	public List<IaQuestionnaireSide> findQtnSideById(Int020201SidesVo request) {
		return iaQuestionnaireSideRepository.findByidHead(request.getIdSide());
	}

	public Int020201Vo findQtnSideDtlById(Int020201SidesVo request) {
		List<Int020201JoinVo> dataLVL1 = null;
		List<Int020201JoinVo> dataLVL2 = null;
		List<Int020201JoinVo> dataLVL3 = null;
		List<List<Int020201JoinVo>> dataRes = new ArrayList<List<Int020201JoinVo>>();
		Int020201Vo response = new Int020201Vo();

		for (Int020201SidesVo dataRequest : request.getRequest()) {
			dataLVL1 = new ArrayList<Int020201JoinVo>();
			dataLVL1 = iaQuestionnaireMadeJdbcRepository.findLvl1ByIdMadeHdr(dataRequest);

			for (Int020201JoinVo objLVL1 : dataLVL1) {
				dataLVL2 = new ArrayList<Int020201JoinVo>();
				dataLVL2 = iaQuestionnaireMadeJdbcRepository.findLvl2ByIdMadeHdr(objLVL1);
				for (Int020201JoinVo objLVL2 : dataLVL2) {
					dataLVL3 = new ArrayList<Int020201JoinVo>();
					dataLVL3 = iaQuestionnaireMadeJdbcRepository.findLvl3ByIdMadeHdr(objLVL2);
					objLVL2.setChildren(dataLVL3);
				}
				objLVL1.setChildren(dataLVL2);
			}
			dataRes.add(dataLVL1);
		}
		response.setHeader(dataRes);

		return response;
	}

	public void updateQtnMadeByRequest(Int020201DtlVo request) {
		if (request.getQtnMadeList().size() > 0) {
			for (IaQuestionnaireMade objMadeDtl : request.getQtnMadeList()) {
				/* check data */
				Optional<IaQuestionnaireMade> resMadeDtl = iaQuestionnaireMadeRepository.findById(objMadeDtl.getId());
				if (resMadeDtl.isPresent()) {
					IaQuestionnaireMade madeDtl = resMadeDtl.get();
					madeDtl.setNote(objMadeDtl.getNote());
					madeDtl.setCheckFlag(objMadeDtl.getCheckFlag());

					if ("ยังไม่ได้ทำแบบสอบถาม".equals(request.getStatus())) {
						madeDtl.setStatus("WAIT");
					}
					
					if(request.getFlagConfirm()) {
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
			if ("ยังไม่ได้ทำแบบสอบถาม".equals(request.getStatus()) && !request.getFlagConfirm()) {
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

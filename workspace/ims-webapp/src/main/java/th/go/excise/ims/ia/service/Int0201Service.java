package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMade;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeHdrRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireMadeRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int02010101Vo;
import th.go.excise.ims.ia.vo.Int0201FormVo;
import th.go.excise.ims.ia.vo.Int0201FormVo2;
import th.go.excise.ims.ia.vo.Int0201Vo;

@Service
public class Int0201Service {

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
				dataDtl = iaQuestionnaireSideDtlJdbcRepository.findDtlByIdSide(objHdr.getIdSide(), objHdr.getSeq(), objHdr.getId());
				for (Int02010101Vo objDtl : dataDtl) {
					dataDtls = new ArrayList<Int02010101Vo>();
					dataDtls = iaQuestionnaireSideDtlJdbcRepository.findDtlsByIdSide(objDtl.getIdSide(), objDtl.getSeqDtl(), objDtl.getId());
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
		if(request.getId() != null) {
			Optional<IaQuestionnaireHdr> hdrRes = iaQuestionnaireHdrRepository.findById(request.getId());
			if(hdrRes.isPresent()) {
				IaQuestionnaireHdr dataHdr = hdrRes.get();
				dataHdr.setStartDate(ConvertDateUtils.parseStringThaiDateToLocalDate(request.getStartDateSend(), ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setEndDate(ConvertDateUtils.parseStringThaiDateToLocalDate(request.getEndDateSend(), ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setStatus("SUCCESS");
				iaQuestionnaireHdrRepository.save(dataHdr);
			}
		}
		
		/* save Questionnaire Made Hdr */
		if(request.getIdSide() != null) {
			 Optional<IaQuestionnaireHdr> hdrRes = iaQuestionnaireHdrRepository.findById(request.getIdSide());
			 if(hdrRes.isPresent()) {
				 IaQuestionnaireHdr dataHdr = hdrRes.get();
				 IaQuestionnaireMadeHdr dataMadeHdr = null;
				 IaQuestionnaireMade qtnMade = null;
				 int loopCount = 3;
				 
				 for(int i = 0; i < loopCount; i++) {
					 dataMadeHdr = new IaQuestionnaireMadeHdr();
					 dataMadeHdr.setIdHdr(dataHdr.getId());
					 dataMadeHdr.setBudgetYear(dataHdr.getBudgetYear());
					 dataMadeHdr.setNote(dataHdr.getNote());
					 dataMadeHdr.setQtnHeaderName(dataHdr.getQtnHeaderName());
					 dataMadeHdr.setStartDate(ConvertDateUtils.parseStringThaiDateToLocalDate(request.getStartDateSend(), ProjectConstant.SHORT_DATE_FORMAT));
					 dataMadeHdr.setEndDate(ConvertDateUtils.parseStringThaiDateToLocalDate(request.getEndDateSend(), ProjectConstant.SHORT_DATE_FORMAT));
					 dataMadeHdr.setStatus("CREATED");
					 dataMadeHdr.setOfficeCode("0" + i + "0000");
					 IaQuestionnaireMadeHdr resMadeHdr = iaQuestionnaireMadeHdrRepository.save(dataMadeHdr);
					 
					 /* save Questionnaire Made */
						if(request.getQtnMadeList().size() > 0) {
							for (IaQuestionnaireMade objMade : request.getQtnMadeList()) {
								qtnMade = new IaQuestionnaireMade();
								qtnMade.setIdSideDtl(objMade.getIdSideDtl());
								qtnMade.setQtnLevel(objMade.getQtnLevel());
								qtnMade.setStatus("CREATED");
								qtnMade.setOfficeCode("0" + i + "0000");
								qtnMade.setIdMadeHdr(resMadeHdr.getId());
								qtnMade.setCheckFlag("F");
								iaQuestionnaireMadeRepository.save(qtnMade);
							} // end loop 2
					}
				 } // end loop 1
			}
		}
	}

}

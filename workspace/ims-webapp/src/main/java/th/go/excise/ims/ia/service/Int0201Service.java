package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.MessageContants;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMade;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireHdrRepository;
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

	public List<IaQuestionnaireSide> findQtnSideById(Int0201FormVo request) {
		return iaQuestionnaireSideRepository.findByidHead(request.getId());
	}
	
	public Int0201Vo findQtnSideDtlById(Int0201FormVo2 request) {
		List<Int02010101Vo> dataHdr = null;
		List<Int02010101Vo> dataDtl = null;
		List<List<Int02010101Vo>> dataRes = new ArrayList<>();

		Int0201Vo response = new Int0201Vo();
		for (Int0201FormVo dataRequest : request.getRequest()) {
			dataHdr = new ArrayList<Int02010101Vo>();
			dataHdr = iaQuestionnaireSideDtlJdbcRepository.findByIdSide(dataRequest.getId());
			
			for (Int02010101Vo objHdr : dataHdr) {
				dataDtl = new ArrayList<Int02010101Vo>();
				dataDtl = iaQuestionnaireSideDtlJdbcRepository.findDtlByIdSide(objHdr.getIdSide(), objHdr.getSeq(), objHdr.getIdHeading());
				objHdr.setChildren(dataDtl);
			}
			dataRes.add(dataHdr);
		}
		response.setData(dataRes);
		
		return response;
	}
	
	public void sendQtnForm(Int0201FormVo request) {
		/* save questionnaire Made */
		if(request.getQtnMadeList().size() > 0) {
			for (IaQuestionnaireMade qtnMade : request.getQtnMadeList()) {
				qtnMade.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
				iaQuestionnaireMadeRepository.save(qtnMade);
			}
		}
		
		/* update questionnaire header */
		if(request.getId() != null) {
			Optional<IaQuestionnaireHdr> hdrRes = iaQuestionnaireHdrRepository.findById(request.getId());
			if(hdrRes.isPresent()) {
				IaQuestionnaireHdr dataHdr = hdrRes.get();
				dataHdr.setStartDate(ConvertDateUtils.parseStringThaiDateToLocalDate(request.getStartDateSend(), ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setEndDate(ConvertDateUtils.parseStringThaiDateToLocalDate(request.getEndDateSend(), ProjectConstant.SHORT_DATE_FORMAT));
				dataHdr.setStatus(MessageContants.IA.SEND_QTN_SUCCES);
				iaQuestionnaireHdrRepository.save(dataHdr);
			}
		}
	}

}

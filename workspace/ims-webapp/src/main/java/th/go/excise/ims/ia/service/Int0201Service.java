package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
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
				dataDtl = iaQuestionnaireSideDtlJdbcRepository.findDtlByIdSide(objHdr.getIdSide(), objHdr.getSeq());
				objHdr.setChildren(dataDtl);
			}
			dataRes.add(dataHdr);
		}
		response.setData(dataRes);
		
		return response;
	}

}

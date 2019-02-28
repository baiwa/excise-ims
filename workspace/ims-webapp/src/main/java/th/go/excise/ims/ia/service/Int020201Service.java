package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireMadeJdbcRepository;
import th.go.excise.ims.ia.vo.Int02010101Vo;
import th.go.excise.ims.ia.vo.Int0201FormVo;
import th.go.excise.ims.ia.vo.Int0201FormVo2;
import th.go.excise.ims.ia.vo.Int0201Vo;
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

}

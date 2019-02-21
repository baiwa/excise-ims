package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSideDtl;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideDtlRepository;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.vo.Int0201FormVo;
import th.go.excise.ims.ia.vo.Int0201FormVo2;
import th.go.excise.ims.ia.vo.Int0201Vo;

@Service
public class Int0201Service {

	@Autowired
	private IaQuestionnaireSideRepository iaQuestionnaireSideRepository;

	@Autowired
	private IaQuestionnaireSideDtlRepository iaQuestionnaireSideDtlRepository;

	public List<IaQuestionnaireSide> findQtnSideById(Int0201FormVo request) {
		return iaQuestionnaireSideRepository.findByidHead(request.getId());
	}

	public List<Int0201Vo> findQtnSideDtlById(Int0201FormVo2 request) {

		List<IaQuestionnaireSideDtl> dataDtl = null;
		List<Int0201Vo> response = new ArrayList<Int0201Vo>();
		Int0201Vo data = null;

		for (Int0201FormVo obj : request.getRequest()) {
			dataDtl = new ArrayList<IaQuestionnaireSideDtl>();
			data = new Int0201Vo();

			dataDtl = iaQuestionnaireSideDtlRepository.findByidSideOrderBySeqDtlAsc(obj.getId());
			data.setId(obj.getId());
			data.setSideName(obj.getSideName());
			data.setDetail(dataDtl);
			response.add(data);
		}
		return response;
	}

}

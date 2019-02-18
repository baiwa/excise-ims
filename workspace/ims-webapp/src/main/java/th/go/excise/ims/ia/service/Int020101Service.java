package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideJdbcRepository;
import th.go.excise.ims.ia.vo.Int020101Vo;

@Service
public class Int020101Service {

	@Autowired
	private IaQuestionnaireSideJdbcRepository iaQtnSideJdbcRep;

	@Autowired
	private IaQuestionnaireSideRepository iaQtnSideRep;

	public List<Int020101Vo> findAll() {
		return iaQtnSideJdbcRep.findAll();
	}

	public List<Int020101Vo> findByIdHead(String idHeadStr) {
		BigDecimal idHead = new BigDecimal(idHeadStr);
		return iaQtnSideJdbcRep.findByIdHead(idHead);
	}

	public IaQuestionnaireSide save(IaQuestionnaireSide request) {
		return iaQtnSideRep.save(request);
	}
	
	public IaQuestionnaireSide update(String idStr, IaQuestionnaireSide request) {
		BigDecimal id = new BigDecimal(idStr);
		IaQuestionnaireSide data = iaQtnSideJdbcRep.findOne(id);
		data.setSideName(request.getSideName());
		return iaQtnSideRep.save(data);
	}

	public IaQuestionnaireSide delete(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		IaQuestionnaireSide data = iaQtnSideJdbcRep.findOne(id);
		iaQtnSideRep.deleteById(id);
		return data;
	}

}

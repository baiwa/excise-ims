package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSideDtl;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideDtlRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int02010101FormVo;
import th.go.excise.ims.ia.vo.Int02010101Vo;

@Service
public class Int02010101Service {

	@Autowired
	private IaQuestionnaireSideDtlJdbcRepository iaQuestionnaireSideDtlJdbcRepository;

	@Autowired
	private IaQuestionnaireSideDtlRepository iaQuestionnaireSideDtlRepository;

	public List<Int02010101Vo> findByIdSide(String idSideStr) {
		BigDecimal idSide = new BigDecimal(idSideStr);
		List<Int02010101Vo> main = iaQuestionnaireSideDtlJdbcRepository.findByIdSide(idSide);
		List<Int02010101Vo> detail = new ArrayList<>();
		for (int i = 0; i < main.toArray().length; i++) {
			BigDecimal seq = main.get(i).getSeq();
			detail = iaQuestionnaireSideDtlJdbcRepository.findDtlByIdSide(idSide, seq);
			main.get(i).setChildren(detail);
		}
		return main;
	}

	public List<IaQuestionnaireSideDtl> save(List<IaQuestionnaireSideDtl> request) {
		return (List<IaQuestionnaireSideDtl>) iaQuestionnaireSideDtlRepository.saveAll(request);
	}

	public Int02010101FormVo saveAll(Int02010101FormVo form) {
		// SAVE
		if (form.getSave().toArray().length > 0) {
			IaQuestionnaireSideDtl save;
			for (int i = 0; i < form.getSave().toArray().length; i++) {
				save = new IaQuestionnaireSideDtl();
				IaQuestionnaireSideDtl item = form.getSave().get(i);
				BigDecimal id = item.getId();
				if (item.getId() != null) {
					save = iaQuestionnaireSideDtlJdbcRepository.findById(id);
					save.setQtnLevel(item.getQtnLevel());
					save.setSideDtl(item.getSideDtl());
					save.setSeq(item.getSeq());
					save.setSeqDtl(item.getSeqDtl());
				} else {
					save = item;
				}
				form.getSave().set(i, iaQuestionnaireSideDtlRepository.save(save));
			}
		}
		// DELETE
		if (form.getDelete().toArray().length > 0) {
			List<IaQuestionnaireSideDtl> delete = form.getDelete();
			iaQuestionnaireSideDtlRepository.deleteAll(delete);
		}
		return form;
	}
}

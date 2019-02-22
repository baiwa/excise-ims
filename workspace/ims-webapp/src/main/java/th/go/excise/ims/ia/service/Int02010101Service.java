package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSideDtl;
import th.go.excise.ims.ia.persistence.repository.IaQuestionnaireSideDtlRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int02010101FormVo;
import th.go.excise.ims.ia.vo.Int02010101Vo;

@Service
public class Int02010101Service {

	private static final Logger logger = LoggerFactory.getLogger(Int02010101Service.class);
	
	@Autowired
	private IaQuestionnaireSideDtlJdbcRepository iaQuestionnaireSideDtlJdbcRepository;

	@Autowired
	private IaQuestionnaireSideDtlRepository iaQuestionnaireSideDtlRepository;

	public List<Int02010101Vo> findByIdSide(String idSideStr) {
		BigDecimal idSide = new BigDecimal(idSideStr);
		List<Int02010101Vo> main = iaQuestionnaireSideDtlJdbcRepository.findByIdSide(idSide);
		List<Int02010101Vo> detail = new ArrayList<>();
		List<Int02010101Vo> details = new ArrayList<>();
		for (int i = 0; i < main.toArray().length; i++) {
			BigDecimal seq = main.get(i).getSeq();
			detail = iaQuestionnaireSideDtlJdbcRepository.findDtlByIdSide(idSide, seq);
			for(int j= 0; j< detail.toArray().length; j++) {
				details = iaQuestionnaireSideDtlJdbcRepository.findDtlsByIdSide(idSide, seq);
				detail.get(j).setChildren(details);
			}
			main.get(i).setChildren(detail);
		}
		return main;
	}

	public List<IaQuestionnaireSideDtl> save(List<IaQuestionnaireSideDtl> request) {
		return (List<IaQuestionnaireSideDtl>) iaQuestionnaireSideDtlRepository.saveAll(request);
	}

	public Int02010101FormVo saveAll(Int02010101FormVo form) {
		// DELETE
		if (form.getDelete().toArray().length > 0) {
			List<IaQuestionnaireSideDtl> delete = form.getDelete();
			for (IaQuestionnaireSideDtl iaQuestionnaireSideDtl : delete) {
				iaQuestionnaireSideDtlRepository.deleteById(iaQuestionnaireSideDtl.getId());
			}
		}
		// SAVE
		if (form.getSave().toArray().length > 0) {
			IaQuestionnaireSideDtl save;
			for (int i = 0; i < form.getSave().toArray().length; i++) {
				save = new IaQuestionnaireSideDtl();
				IaQuestionnaireSideDtl item = form.getSave().get(i);
				if (item.getId() != null) {
					BigDecimal id = item.getId();
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
		return form;
	}
}

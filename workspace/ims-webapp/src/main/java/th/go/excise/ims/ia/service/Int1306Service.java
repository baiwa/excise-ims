package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmResult;
import th.go.excise.ims.ia.persistence.repository.IaAuditPmResultRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int1306JdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.IaAuditPmResultVo;
import th.go.excise.ims.ia.vo.Int1306DataVo;
import th.go.excise.ims.ia.vo.Int1306FormVo;
import th.go.excise.ims.ia.vo.Int1306Vo;

@Service
public class Int1306Service {

	private static final Logger logger = LoggerFactory.getLogger(Int1306Service.class);

	@Autowired
	private Int1306JdbcRepository int1306JdbcRepository;

	@Autowired
	private IaAuditPmResultRepository iaAuditPmResultRepository;

	@Autowired
	private IaCommonService iaCommonService;

	public Int1306Vo findCriteria(Int1306FormVo request) {
		Int1306Vo response = null;
		try {
			Int1306DataVo data1 = int1306JdbcRepository.findIaAuditPmassessHByCriteria(request);
			Int1306DataVo data2 = int1306JdbcRepository.findIaAuditPmQtHByCriteria(request);
			Int1306DataVo data3 = int1306JdbcRepository.findIaAuditPy1HCriteria(request);
			Int1306DataVo data4 = int1306JdbcRepository.findIaAuditPy2HCriteria(request);
			Int1306DataVo data5 = int1306JdbcRepository.findIaAuditPmCommitHCriteria(request);
			List<Int1306DataVo> int1306List = new ArrayList<Int1306DataVo>();
			int1306List.add(data1);
			int1306List.add(data2);
			int1306List.add(data3);
			int1306List.add(data4);
			int1306List.add(data5);
			// set response
			response = new Int1306Vo();
			response.setAuditPmassessNo(data1.getAuditNo());
			response.setAuditPmqtNo(data2.getAuditNo());
			response.setAuditPy1No(data3.getAuditNo());
			response.setAuditPy2No(data4.getAuditNo());
			response.setAuditPmcommitNo(data5.getAuditNo());
			response.setDataList(int1306List);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return response;
	}

	public List<IaAuditPmResultVo> findAuditPmResultList() {
		List<IaAuditPmResult> auditPmResultsList = iaAuditPmResultRepository.findIaAuditPmResultAllDataActive();

		IaAuditPmResultVo pmResultVo = null;
		List<IaAuditPmResultVo> pmResultVoList = new ArrayList<>();
		for (IaAuditPmResult data : auditPmResultsList) {
			pmResultVo = new IaAuditPmResultVo();
			try {
				pmResultVo.setAuditPmresultSeq(data.getAuditPmresultSeq());
				pmResultVo.setOfficeCode(data.getOfficeCode());
				pmResultVo.setAuditDateFrom(ConvertDateUtils.formatDateToString(data.getAuditDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				pmResultVo.setAuditDateTo(ConvertDateUtils.formatDateToString(data.getAuditDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				pmResultVo.setAuditPmresultNo(data.getAuditPmresultNo());
				pmResultVo.setBudgetYear(data.getBudgetYear());
				pmResultVo.setAuditPmassessNo(data.getAuditPmassessNo());
				pmResultVo.setAuditPmqtNo(data.getAuditPmqtNo());
				pmResultVo.setAuditPy1No(data.getAuditPy1No());
				pmResultVo.setAuditPy2No(data.getAuditPy2No());
				pmResultVo.setAuditPmcommitNo(data.getAuditPmcommitNo());
				pmResultVo.setDepAuditingSuggestion(data.getDepAuditingSuggestion());
				pmResultVo.setAuditSummary(data.getAuditSummary());
				pmResultVo.setAuditSuggestion(data.getAuditSuggestion());
				pmResultVo.setPersonAudity(data.getPersonAudity());
				pmResultVo.setPersonAudityPosition(data.getPersonAudityPosition());
				pmResultVo.setAuditer1(data.getAuditer1());
				pmResultVo.setAuditer1AudityPosition(data.getAuditer1AudityPosition());
				pmResultVo.setAuditer2(data.getAuditer2());
				pmResultVo.setAudite2AudityPosition(data.getAudite2AudityPosition());

				pmResultVoList.add(pmResultVo);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

		}
		return pmResultVoList;
	}

	public IaAuditPmResultVo save(IaAuditPmResultVo vo) {
		logger.info("saveLicListService : {} ", vo.getAuditPmresultNo());
		IaAuditPmResult pmResult = null;
		try {

			if (StringUtils.isNotBlank(vo.getAuditPmresultNo())) {
				pmResult = iaAuditPmResultRepository.findByAuditPmresultNo(vo.getAuditPmresultNo());
				pmResult.setDepAuditingSuggestion(vo.getDepAuditingSuggestion());
				pmResult.setAuditSummary(vo.getAuditSummary());
				pmResult.setAuditSuggestion(vo.getAuditSuggestion());
				pmResult.setPersonAudity(vo.getPersonAudity());
				pmResult.setPersonAudityPosition(vo.getPersonAudityPosition());
				pmResult.setAuditer1(vo.getAuditer1());
				pmResult.setAuditer1AudityPosition(vo.getAuditer1AudityPosition());
				pmResult.setAuditer2(vo.getAuditer2());
				pmResult.setAudite2AudityPosition(vo.getAudite2AudityPosition());

				pmResult = iaAuditPmResultRepository.save(pmResult);
				vo.setAuditPmresultNo(pmResult.getAuditPmresultNo());
			} else {
				pmResult = new IaAuditPmResult();
				pmResult.setOfficeCode(vo.getOfficeCode());
				pmResult.setAuditDateFrom(ConvertDateUtils.parseStringToDate(vo.getAuditDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				pmResult.setAuditDateTo(ConvertDateUtils.parseStringToDate(vo.getAuditDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				pmResult.setAuditPmresultNo(iaCommonService.autoGetRunAuditNoBySeqName("PMR", vo.getOfficeCode(), "AUDIT_PMRESULT_NO_SEQ", 8));
				pmResult.setBudgetYear(vo.getBudgetYear());
				pmResult.setAuditPmassessNo(vo.getAuditPmassessNo());
				pmResult.setAuditPmqtNo(vo.getAuditPmqtNo());
				pmResult.setAuditPy1No(vo.getAuditPy1No());
				pmResult.setAuditPy2No(vo.getAuditPy2No());
				pmResult.setAuditPmcommitNo(vo.getAuditPmcommitNo());
				pmResult.setDepAuditingSuggestion(vo.getDepAuditingSuggestion());
				pmResult.setAuditSummary(vo.getAuditSummary());
				pmResult.setAuditSuggestion(vo.getAuditSuggestion());
				pmResult.setPersonAudity(vo.getPersonAudity());
				pmResult.setPersonAudityPosition(vo.getPersonAudityPosition());
				pmResult.setAuditer1(vo.getAuditer1());
				pmResult.setAuditer1AudityPosition(vo.getAuditer1AudityPosition());
				pmResult.setAuditer2(vo.getAuditer2());
				pmResult.setAudite2AudityPosition(vo.getAudite2AudityPosition());

				pmResult = iaAuditPmResultRepository.save(pmResult);
				vo.setAuditPmresultNo(pmResult.getAuditPmresultNo());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return vo;
	}

	public IaAuditPmResultVo findByAuditPmResultNo(String auditPmresultNo) {
		IaAuditPmResultVo data = null;
		IaAuditPmResult h = null;
		ExciseDepartmentVo excise = null;
		h = iaAuditPmResultRepository.findByAuditPmresultNo(auditPmresultNo);
		try {
			data = new IaAuditPmResultVo();
			data.setOfficeCode(h.getOfficeCode());
			data.setAuditDateFrom(ConvertDateUtils.formatDateToString(h.getAuditDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			data.setAuditDateTo(ConvertDateUtils.formatDateToString(h.getAuditDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			data.setAuditPmresultNo(h.getAuditPmresultNo());
			data.setBudgetYear(h.getBudgetYear());
			data.setAuditPmassessNo(h.getAuditPmassessNo());
			data.setAuditPmqtNo(h.getAuditPmqtNo());
			data.setAuditPy1No(h.getAuditPy1No());
			data.setAuditPy2No(h.getAuditPy2No());
			data.setAuditPmcommitNo(h.getAuditPmcommitNo());
			data.setDepAuditingSuggestion(h.getDepAuditingSuggestion());
			data.setAuditSummary(h.getAuditSummary());
			data.setAuditSuggestion(h.getAuditSuggestion());
			data.setPersonAudity(h.getPersonAudity());
			data.setPersonAudityPosition(h.getPersonAudityPosition());
			data.setAuditer1(h.getAuditer1());
			data.setAuditer1AudityPosition(h.getAuditer1AudityPosition());
			data.setAuditer2(h.getAuditer2());
			data.setAudite2AudityPosition(h.getAudite2AudityPosition());

			excise = ExciseDepartmentUtil.getExciseDepartmentFull(h.getOfficeCode());
			data.setArea(excise.getArea());
			data.setSector(excise.getSector());
			data.setBranch(excise.getBranch());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return data;
	}
}

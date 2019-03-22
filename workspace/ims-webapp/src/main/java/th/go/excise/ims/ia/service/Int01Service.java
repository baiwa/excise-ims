package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaPlanDtl;
import th.go.excise.ims.ia.persistence.entity.IaPlanHdr;
import th.go.excise.ims.ia.persistence.repository.IaPlanDtlRepository;
import th.go.excise.ims.ia.persistence.repository.IaPlanHdrRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaPlanDtlJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaPlanHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int01DtlVo;
import th.go.excise.ims.ia.vo.Int01HdrVo;
import th.go.excise.ims.ia.vo.Int01TableVo;
import th.go.excise.ims.ia.vo.Int01Vo;

@Service
public class Int01Service {
	@Autowired
	private IaPlanHdrRepository iaPlanHdrRepository;

	@Autowired
	private IaPlanHdrJdbcRepository iaPlanHdrJdbcRepository;

	@Autowired
	private IaPlanDtlJdbcRepository iaPlanDtlJdbcRepository;

	@Autowired
	private IaPlanDtlRepository iaPlanDtlRepository;

	public Int01Vo findDataByBudgetYear(String budgetYear) throws Exception {
		Int01Vo response = new Int01Vo();
		List<Int01TableVo> detailList = new ArrayList<Int01TableVo>();

		/* header */
		Int01HdrVo header = iaPlanHdrJdbcRepository.getDataFilter(budgetYear);
		header.setStatusStr(ApplicationCache.getParamInfoByCode("IA_PLAN_HDR_STATUS", header.getStatus()).getValue1());
		response.setHeader(header);

		/* group by inspectionWork */
		List<IaPlanDtl> inspectionWorkList = iaPlanDtlJdbcRepository.findPlanDtlGroupByInspectionWork();
		if (inspectionWorkList.size() > 0) {
			IaPlanDtl iaPlanDtl = null;
			Int01TableVo detail = null;
			for (IaPlanDtl inspectionWork : inspectionWorkList) {
				/* initial data */
				iaPlanDtl = new IaPlanDtl();
				iaPlanDtl.setBudgetYear(budgetYear);
				iaPlanDtl.setInspectionWork(inspectionWork.getInspectionWork());
				List<Int01DtlVo> dataFilter = iaPlanDtlJdbcRepository.findByIaPlanDtl(iaPlanDtl);

				/* deetail */
				detail = new Int01TableVo();
				if (dataFilter.size() > 0) {
					detail.setInspectionWorkStr(ApplicationCache.getParamInfoByCode("IA_INSPECTION_WORK", inspectionWork.getInspectionWork()).getValue1());
					detail.setInspectionWork(inspectionWork.getInspectionWork());
					detail.setFrequency(new BigDecimal(iaPlanDtlJdbcRepository.countSumFrequencyByInspectionWorkAndBudgetYear(iaPlanDtl)));
					detail.setUnit(dataFilter.get(0).getUnit());
					detail.setDetail(dataFilter);
				}
				detailList.add(detail);
			}
			response.setTableVo(detailList);
		}
		return response;
	}

	public IaPlanHdr updateChoice(BigDecimal planHdrId, String flag) {
		IaPlanHdr dataHdr = null;
		IaPlanHdr response = null;
		
		if (planHdrId != null) {
			dataHdr = iaPlanHdrRepository.findById(planHdrId).get();
			if ("APPROVE".equals(flag)) {
				dataHdr.setStatus(IaConstants.PLAN_HDR_STATUS.STATUS_1_CODE);
			} else {
				dataHdr.setStatus(IaConstants.PLAN_HDR_STATUS.STATUS_2_CODE);
			}
			response = iaPlanHdrRepository.save(dataHdr);
		}
		return response;
	}
}

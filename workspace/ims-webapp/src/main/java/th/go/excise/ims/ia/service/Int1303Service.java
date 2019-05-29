package th.go.excise.ims.ia.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy2D;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy2H;
import th.go.excise.ims.ia.persistence.repository.IaAuditPy2DRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditPy2HRepository;
import th.go.excise.ims.ia.vo.Int1303FilterVo;
import th.go.excise.ims.ia.vo.Int1303Vo;
import th.go.excise.ims.ia.vo.WsPmPy2HVo;
import th.go.excise.ims.ws.persistence.entity.WsPmPy2DVo;
import th.go.excise.ims.ws.persistence.repository.WsPmPy2DRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmPy2HRepository;

@Service
public class Int1303Service {
	
	@Autowired
	private WsPmPy2DRepository wsPmPy2DRepository;
	
	@Autowired
	private WsPmPy2HRepository wsPmPy2HRepository;
	
	@Autowired
	private IaCommonService iaCommonService;
	
	@Autowired
	private IaAuditPy2HRepository iaAuditPy2HRepository;
	
	@Autowired
	private IaAuditPy2DRepository iaAuditPy2DRepository;

	public Int1303Vo getWsPmPy2(Int1303FilterVo request) {
		Int1303Vo response = new Int1303Vo();
		/* headers */
		List<WsPmPy2HVo> headers = wsPmPy2HRepository.filterWsPaPy2H(request);
		if (headers.size() > 0) {
			response.setHeaders(headers.get(0));
			/* details */
			List<WsPmPy2DVo> details = wsPmPy2DRepository.filterWsPaPy2D(headers.get(0).getFormCode(), headers.get(0).getOffCode());
			for (WsPmPy2DVo wsPmPy2DVo : details) {
				wsPmPy2DVo.setProcessDateStr(ConvertDateUtils.formatLocalDateToString(wsPmPy2DVo.getProcessDate(), ConvertDateUtils.DD_MM_YYYY));
			}
			response.setDetails(details);
		}
		return response;
	}

	public String savePmPy2(Int1303Vo request) throws Exception {
		IaAuditPy2H header = new IaAuditPy2H();
		IaAuditPy2D detail = null;
		String auditPy2No = iaCommonService.autoGetRunAuditNoBySeqName("PY2", request.getHeaders().getOffCode(), "AUDIT_PY2_NO_SEQ", 8);
		BeanUtils.copyProperties(header, request.getHeaders());
		header.setBudgetYear(request.getHeaders().getFormYear());
		header.setOfficeCode(request.getHeaders().getOffCode());
		header.setAuditPy2No(auditPy2No);
		iaAuditPy2HRepository.save(header);
		
		for (WsPmPy2DVo d : request.getDetails()) {
			detail = new IaAuditPy2D();
			BeanUtils.copyProperties(detail, d);
			detail.setAuditPy2No(auditPy2No);
			iaAuditPy2DRepository.save(detail);
		}
		return auditPy2No;
	}

	public List<IaAuditPy2H> findAuditPy2NoList() {
		return iaAuditPy2HRepository.getAuditPy2NoList();
	}

}

package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurH;
import th.go.excise.ims.ia.persistence.repository.IaAuditTxinsurD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditTxinsurHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int061402JdbcRepository;
import th.go.excise.ims.ia.vo.Int061402FilterVo;
import th.go.excise.ims.ia.vo.Int061402FormVo;
import th.go.excise.ims.ia.vo.Ws_Reg4000Vo;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;

@Service
public class Int061402Service {
	@Autowired
	private Int061402JdbcRepository int061402JdbcRepository;

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;

	@Autowired
	private IaAuditTxinsurD1Repository iaAuditTxinsurD1Repository;

	@Autowired
	private IaAuditTxinsurHRepository iaAuditTxinsurHRepository;

	public DataTableAjax<Ws_Reg4000Vo> filter(Int061402FilterVo formVo) {
		List<Ws_Reg4000Vo> data = new ArrayList<Ws_Reg4000Vo>();
		if ("Y".equals(formVo.getFlagSearch())) {
			data = int061402JdbcRepository.getDataFilter(formVo);
		}

		DataTableAjax<Ws_Reg4000Vo> dataTableAjax = new DataTableAjax<Ws_Reg4000Vo>();
		// dataTableAjax.setDraw(formVo.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(int061402JdbcRepository.countDatafilter(formVo));
		dataTableAjax.setRecordsFiltered(int061402JdbcRepository.countDatafilter(formVo));

		return dataTableAjax;
	}

	public void save(Int061402FormVo request) {
		IaAuditTxinsurH header = new IaAuditTxinsurH();
		IaAuditTxinsurD1 detail = null;

		/* get getSeqTxinsurNo */
		String seqTxinsurNo = StringUtils.leftPad(int061402JdbcRepository.getSeqTxinsurNo(), 8, "0");
//		String seqTxinsurNo = String.format("%08d", int061402JdbcRepository.getSeqTxinsurNo());

		/* save header */
		header.setOfficeCode(request.getHeader().getOfficeCode());
		header.setAuditTxinsurNo(request.getHeader().getOfficeCode() + "/" + seqTxinsurNo);
		header.setRegistDateStart(ConvertDateUtils.parseStringToDate(request.getHeader().getRegistDateStart(),
				ConvertDateUtils.DD_MM_YYYY));
		header.setRegistDateEnd(ConvertDateUtils.parseStringToDate(request.getHeader().getRegistDateEnd(),
				ConvertDateUtils.DD_MM_YYYY)); 
			iaAuditTxinsurHRepository.save(header);

		/* save detail */
		for (int i = 0; i < request.getDetail().size(); i++) {
			detail = new IaAuditTxinsurD1();
			detail.setAuditTxinsurNo(request.getHeader().getOfficeCode() + "/" + seqTxinsurNo);
			detail.setOfficeCode(request.getDetail().get(i).getOfficeCode());
			detail.setNewRegId(request.getDetail().get(i).getNewRegId());
			detail.setResultSeq(new java.math.BigDecimal(i).add(new java.math.BigDecimal(1)));

			iaAuditTxinsurD1Repository.save(detail);
		}
	}
	
	public IaAuditTxinsurH findHeader(String auditTxinsurNo) {
		return iaAuditTxinsurHRepository.findByAuditTxinsurNo(auditTxinsurNo);
	}

}

package th.co.baiwa.excise.epa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.dao.ExportCheckingDao;
import th.co.baiwa.excise.epa.persistence.vo.Epa011DtlVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011Vo;
import th.co.baiwa.excise.epa.persistence.vo.InvhdrFormVo;
import th.co.baiwa.excise.epa.persistence.vo.InvhdrVo;

@Service
public class Epa014Service {

	@Autowired
	private ExportCheckingDao exportCheckingDao;
	
	public Epa011Vo getDetail(Epa011FormVo epa011FormVo) {
		return exportCheckingDao.getHDR(epa011FormVo.getViewId());
	}
	
	public DataTableAjax<Epa011DtlVo> searchDetail(Epa011FormVo epa011FormVo) {
		DataTableAjax<Epa011DtlVo> dataTableAjax = new DataTableAjax<Epa011DtlVo>();

		if (epa011FormVo.getViewId() != null ) {
			List<Epa011DtlVo> list = exportCheckingDao.listDetailData(epa011FormVo);
			long count = exportCheckingDao.countDetail(epa011FormVo);

			dataTableAjax.setDraw(epa011FormVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public InvhdrFormVo getInvDetail(InvhdrFormVo invhdrFormVo) {

		Epa011Vo taxhdr = exportCheckingDao.getHDR(invhdrFormVo.getHdrId());
		invhdrFormVo.setHdrVo(taxhdr);
		
		Epa011DtlVo taxdtl = exportCheckingDao.getDTL(invhdrFormVo.getDtlId());
		invhdrFormVo.setDtlVo(taxdtl);
		
		
		InvhdrVo rightForm = exportCheckingDao.getINVHDR(invhdrFormVo.getDtlId(),"1");
		invhdrFormVo.setRightForm(rightForm);
		
		return invhdrFormVo;
	}


}

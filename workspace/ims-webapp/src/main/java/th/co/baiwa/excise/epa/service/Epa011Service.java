package th.co.baiwa.excise.epa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.BusinessException;
import th.co.baiwa.excise.constant.ExciseConstants.SEARCH_FLAG;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.dao.ExportCheckingDao;
import th.co.baiwa.excise.epa.persistence.vo.Epa011DtlVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011Vo;
import th.co.baiwa.excise.epa.persistence.vo.InvhdrFormVo;

@Service
public class Epa011Service {

	@Autowired
	private ExportCheckingDao exportCheckingDao;

	public DataTableAjax<Epa011Vo> search(Epa011FormVo epa011FormVo) {
		DataTableAjax<Epa011Vo> dataTableAjax = new DataTableAjax<Epa011Vo>();

		if (SEARCH_FLAG.TRUE.equalsIgnoreCase(epa011FormVo.getSearchFlag())) {
			List<Epa011Vo> list = exportCheckingDao.listExportCheckingData(epa011FormVo);
			long count = exportCheckingDao.countExportCheckingData(epa011FormVo);

			dataTableAjax.setDraw(epa011FormVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

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

	public InvhdrFormVo getInvDetail(InvhdrFormVo invhdrFormVo) throws Exception {

		Epa011Vo taxhdr = exportCheckingDao.getHDR(invhdrFormVo.getHdrId());
		invhdrFormVo.setHdrVo(taxhdr);
		
		Epa011DtlVo taxdtl = exportCheckingDao.getDTL(invhdrFormVo.getDtlId());
		invhdrFormVo.setDtlVo(taxdtl);
		

		
		return invhdrFormVo;
	}

	public void saveInv(InvhdrFormVo invhdrFormVo) {

		exportCheckingDao.clear(invhdrFormVo.getDtlId(),"1");
		exportCheckingDao.insertInvHDR(invhdrFormVo,"1");
		
	}

}

package th.co.baiwa.excise.epa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants.SEARCH_FLAG;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.dao.ExportCheckingDao;
import th.co.baiwa.excise.epa.persistence.vo.Epa011DtlVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011Vo;
import th.co.baiwa.excise.epa.persistence.vo.InvhdrFormVo;
import th.co.baiwa.excise.epa.persistence.vo.InvhdrVo;

@Service
public class Epa021Service {

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
			List<Epa011DtlVo> list = exportCheckingDao.listDetailDataFactory(epa011FormVo);
			long count = exportCheckingDao.countDetailFactory(epa011FormVo);

			dataTableAjax.setDraw(epa011FormVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public InvhdrFormVo getInvDetailFac(InvhdrFormVo invhdrFormVo) {

		Epa011Vo taxhdr = exportCheckingDao.getHDR(invhdrFormVo.getHdrId());
		invhdrFormVo.setHdrVo(taxhdr);
		
		Epa011DtlVo taxdtl = exportCheckingDao.getDTL(invhdrFormVo.getDtlId());
		invhdrFormVo.setDtlVo(taxdtl);
		
		InvhdrVo leftFrom = exportCheckingDao.getINVHDR(invhdrFormVo.getDtlId(),"1");
		invhdrFormVo.setLeftFrom(leftFrom);
		
		return invhdrFormVo;
	}

	public void saveInv(InvhdrFormVo invhdrFormVo) {
		exportCheckingDao.clear(invhdrFormVo.getDtlId(),"2");
		exportCheckingDao.insertInvHDR(invhdrFormVo,"2");
		
	}

	public InvhdrFormVo getInvDetailReport(InvhdrFormVo invhdrFormVo) {

		Epa011Vo taxhdr = exportCheckingDao.getHDR(invhdrFormVo.getHdrId());
		invhdrFormVo.setHdrVo(taxhdr);
		
		Epa011DtlVo taxdtl = exportCheckingDao.getDTL(invhdrFormVo.getDtlId());
		invhdrFormVo.setDtlVo(taxdtl);
		
		InvhdrVo leftFrom = exportCheckingDao.getINVHDR(invhdrFormVo.getDtlId(),"1");
		invhdrFormVo.setLeftFrom(leftFrom);

		InvhdrVo rightForm = exportCheckingDao.getINVHDR(invhdrFormVo.getDtlId(),"2");
		invhdrFormVo.setRightForm(rightForm);
		
		return invhdrFormVo;
	}


}

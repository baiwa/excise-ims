package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.TransferListDao;
import th.co.baiwa.excise.ia.persistence.entity.TransferList;
import th.co.baiwa.excise.ia.persistence.repository.TransferListRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int069FormVo;

@Service
public class Int069Service {
	
	@Autowired
	private TransferListRepository transferListRepository;
	
	@Autowired
	private TransferListDao transferListDao;

	public DataTableAjax<TransferList> filterByform(Int069FormVo en) {
		List<TransferList> listData = new ArrayList<TransferList>();
//		listData = transferListRepository.findByFilter(en.getTransferList(), en.getBudgetType(), en.getActivities());
		listData = transferListDao.queryByFilter(en);
//		, en.getStart(), en.getEnd()

		DataTableAjax<TransferList> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(listData.size());
		dataTableAjax.setRecordsFiltered(listData.size());
		dataTableAjax.setData(listData);
		return dataTableAjax;
	}

	public void delete(TransferList vo) {
//		TransferList xxx = transferListRepository.findOne(vo.getTransferId());
		transferListRepository.delete(vo.getTransferId());
	}

}

package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostDetailDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09213Vo;

@Service
public class Int09213Service {

	@Autowired
	private TravelCostDetailDao travelCostDetailDao;

	public DataTableAjax<Int09213Vo> findAll() {

		List<Int09213Vo> list = travelCostDetailDao.dataTravelCostWsDetail();
		Long count = travelCostDetailDao.count();

		DataTableAjax<Int09213Vo> dataTableAjax = new DataTableAjax<>();

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}

}

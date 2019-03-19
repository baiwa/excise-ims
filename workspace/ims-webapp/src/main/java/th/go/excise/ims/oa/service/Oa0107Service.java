package th.go.excise.ims.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.oa.persistence.entity.OaCustomer;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0107JdbcRepository;
import th.go.excise.ims.oa.vo.Oa0107Vo;

@Service
public class Oa0107Service {

	@Autowired
	private Oa0107JdbcRepository oa0107JdbcRep;
	
	public DataTableAjax<OaCustomer> filterByCriteria(Oa0107Vo request) {
		List<OaCustomer> data = oa0107JdbcRep.getDataFilter(request);
		DataTableAjax<OaCustomer> dataTableAjax = new DataTableAjax<OaCustomer>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(oa0107JdbcRep.countDatafilter(request));
		dataTableAjax.setRecordsFiltered(oa0107JdbcRep.countDatafilter(request));
		return dataTableAjax;
	}
	
}

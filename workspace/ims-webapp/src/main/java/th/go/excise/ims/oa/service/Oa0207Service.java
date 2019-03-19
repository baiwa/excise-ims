package th.go.excise.ims.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.oa.persistence.entity.OaCustomer;
import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0207JdbcRepository;
import th.go.excise.ims.oa.vo.Oa0207Vo;

@Service
public class Oa0207Service {

	@Autowired
	private Oa0207JdbcRepository oa0207JdbcRep;
	
	public DataTableAjax<OaCustomer> filterByCriteria(Oa0207Vo request) {
		List<OaCustomer> data = oa0207JdbcRep.getDataFilter(request);
		DataTableAjax<OaCustomer> dataTableAjax = new DataTableAjax<OaCustomer>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(oa0207JdbcRep.countDatafilter(request));
		dataTableAjax.setRecordsFiltered(oa0207JdbcRep.countDatafilter(request));
		return dataTableAjax;
	}
	
}

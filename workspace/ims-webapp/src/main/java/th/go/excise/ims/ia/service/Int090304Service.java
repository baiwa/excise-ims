package th.go.excise.ims.ia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int090304JdbcRepository;
import th.go.excise.ims.ia.vo.Int090304Vo;
import th.go.excise.ims.ia.vo.Int0903FormVo;

@Service
public class Int090304Service {

	private Logger logger = LoggerFactory.getLogger(Int090304Service.class);

	@Autowired
	Int090304JdbcRepository int090304JdbcRepository;

	public DataTableAjax<Int090304Vo> list(Int0903FormVo request) {
		List<Int090304Vo> data = int090304JdbcRepository.findByCriteria(request);
		Integer total = int090304JdbcRepository.countByCriteria(request);

		DataTableAjax<Int090304Vo> dataTableAjax = new DataTableAjax<Int090304Vo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<Int0903FormVo> budgetTypeDropdown() {
		List<Int0903FormVo> response = int090304JdbcRepository.budgetTypeDropdown();
		return response;
	}
}

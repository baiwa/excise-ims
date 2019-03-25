package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaConcludeFollowHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int11FormVo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Service
public class Int11Service {

	@Autowired
	private IaConcludeFollowHdrJdbcRepository iaConcludeFollowHdrJdbcRepository;

	public DataTableAjax<Int11Vo> list(Int11FormVo form) {
		List<Int11Vo> dataList = iaConcludeFollowHdrJdbcRepository.getData(form);
		
		DataTableAjax<Int11Vo> dataTableAjax = new DataTableAjax<Int11Vo>();
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}

}

package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int1107JdbcRepository;
import th.go.excise.ims.ia.vo.Int11FormVo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Service
public class Int1107Service {
	
	@Autowired
	private Int1107JdbcRepository int1107JdbcRepository;

	public DataTableAjax<Int11Vo> list(Int11FormVo form) {
		List<Int11Vo> dataList = int1107JdbcRepository.getData(form);
		for (Int11Vo int11Vo : dataList) {
			int11Vo.setApproveDateString(ConvertDateUtils.formatDateToString(int11Vo.getApproveDate(), ConvertDateUtils.DD_MM_YYYY ,ConvertDateUtils.LOCAL_EN));
			int11Vo.setDateFromString(ConvertDateUtils.formatDateToString(int11Vo.getDateFrom(), ConvertDateUtils.DD_MM_YYYY));
			int11Vo.setDateToString(ConvertDateUtils.formatDateToString(int11Vo.getDateTo(), ConvertDateUtils.DD_MM_YYYY));
		}
		DataTableAjax<Int11Vo> dataTableAjax = new DataTableAjax<Int11Vo>();
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}

}

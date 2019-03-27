package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaConcludeFollowHdrJdbcRepository;
import th.go.excise.ims.ia.vo.Int11FormVo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Service
public class Int11Service {

	@Autowired
	private IaConcludeFollowHdrJdbcRepository iaConcludeFollowHdrJdbcRepository;

	public DataTableAjax<Int11Vo> list(Int11FormVo form) {
		List<Int11Vo> dataList = iaConcludeFollowHdrJdbcRepository.getData(form);
		for (Int11Vo int11Vo : dataList) {
			int11Vo.setApproveDateString(ConvertDateUtils.formatDateToString(int11Vo.getApproveDate(),ConvertDateUtils.DD_MM_YYYY));
			int11Vo.setDateFromString(ConvertDateUtils.formatDateToString(int11Vo.getDateFrom(), ConvertDateUtils.DD_MM_YYYY));
			int11Vo.setDateToString(ConvertDateUtils.formatDateToString(int11Vo.getDateTo(), ConvertDateUtils.DD_MM_YYYY));
			int11Vo.setCreatedBy(null);
			int11Vo.setCreatedDate(null);
			int11Vo.setUpdatedBy(null);
			int11Vo.setUpdatedDate(null);
			int11Vo.setIsDeleted(null);
			int11Vo.setVersion(null);
			int11Vo.setApproveDate(null);;
		}
		
		DataTableAjax<Int11Vo> dataTableAjax = new DataTableAjax<Int11Vo>();
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}

}

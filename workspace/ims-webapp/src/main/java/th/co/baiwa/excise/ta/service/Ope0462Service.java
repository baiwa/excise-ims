package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.DisplayCreatePeperChrckerHeaderDao;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462Vo;

@Service
public class Ope0462Service {

		
	@Autowired
	private DisplayCreatePeperChrckerHeaderDao displayCreatePeperChrckerHeaderDao;


	public DataTableAjax<Ope0462Vo> findAll(Ope0462FormVo formVo) {

		DataTableAjax<Ope0462Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			List<Ope0462Vo> list = displayCreatePeperChrckerHeaderDao.findAll(formVo);
			Long count = displayCreatePeperChrckerHeaderDao.count(formVo);

			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}	
		return dataTableAjax;
	}

	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = displayCreatePeperChrckerHeaderDao.findExciseId();
		return dataList;
	}

	

}

package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.DisplayCreatePeperPayHeaderDao;
import th.co.baiwa.excise.ta.persistence.vo.Ope0451FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope045Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462Vo;

@Service
public class Ope0441Service {

		
	@Autowired
	private DisplayCreatePeperPayHeaderDao createPeperPayHeaderDao;


	public DataTableAjax<Ope0462Vo> findAll(Ope0451FormVo formVo) {

		DataTableAjax<Ope0462Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			List<Ope0462Vo> list = createPeperPayHeaderDao.findAll(formVo);
			Long count = createPeperPayHeaderDao.count(formVo);

			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}	
		return dataTableAjax;
	}

	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = createPeperPayHeaderDao.findExciseId();
		return dataList;
	}

	public DataTableAjax<Ope045Vo> findDetails(Ope0461FormVo formVo){
		DataTableAjax<Ope045Vo> dataTableAjax = new DataTableAjax<>();
		
			List<Ope045Vo> list = createPeperPayHeaderDao.findDetails(formVo);
			Long count = createPeperPayHeaderDao.countDetails(formVo);

			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);		
		return dataTableAjax;
	}
	

}

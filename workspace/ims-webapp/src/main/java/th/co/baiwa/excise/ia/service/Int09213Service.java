package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostDetailDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09213FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09213Vo;

@Service
public class Int09213Service {

	@Autowired
	private TravelCostDetailDao travelCostDetailDao;

	public DataTableAjax<Int09213Vo> findAll(List<Int09213Vo> dataTableSession) {

		Long count = Long.valueOf(dataTableSession.size());

		DataTableAjax<Int09213Vo> dataTableAjax = new DataTableAjax<>();

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(dataTableSession);

		return dataTableAjax;
	}
	
	public void addData(List<Int09213Vo> dataTableSession ,Int09213FormVo formVo) {
		
		//id
		Integer id = (int) (Math.random() * 50 + 1);
		
		Int09213Vo vo = new Int09213Vo();
		vo.setWorkSheetDetailId(id.toString());
		vo.setPrefix(formVo.getPrefix());
		vo.setName(formVo.getName());
		vo.setLastName(formVo.getLast());
		vo.setPosition(formVo.getPosition()); 		
		
		dataTableSession.add(vo);
	}
	
	public List<LabelValueBean> dropdownListType(Int09213FormVo formVo){
		
		return 	travelCostDetailDao.drodownList(formVo.getLovIdMaster());
	}

}

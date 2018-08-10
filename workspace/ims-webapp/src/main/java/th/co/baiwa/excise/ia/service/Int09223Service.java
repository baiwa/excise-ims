package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostDetailDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09213FormVo;

@Service
public class Int09223Service {

	@Autowired
	private TravelCostDetailDao travelCostDetailDao;

	// department dropdown
	public List<LabelValueBean> dropdownListType(Int09213FormVo formVo) {

		return travelCostDetailDao.drodownList(formVo.getLovIdMaster());
	}

}

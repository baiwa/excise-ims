package th.co.baiwa.excise.ia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.controller.Int09221Controller;
import th.co.baiwa.excise.ia.persistence.dao.TravelCostDetailDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09213FormVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class Int09223Service {
	
	private static Logger log = LoggerFactory.getLogger(Int09223Service.class);

	@Autowired
	private TravelCostDetailDao travelCostDetailDao;

	// department dropdown
	public List<LabelValueBean> dropdownListType(Int09213FormVo formVo) {

		return travelCostDetailDao.drodownList(formVo.getLovIdMaster());
	}
	
	public List<LabelValueBean> dropdownListType2(Int09213FormVo formVo) {
		List<LabelValueBean> lv = new ArrayList<>();
		String dataList = travelCostDetailDao.drodownList2(formVo.getLovIdMaster());
		String[] data = dataList.split(",");
		
        lv.add(new LabelValueBean(data[0],data[1]));
        lv.add(new LabelValueBean(data[2],data[3]));
        
        log.info("Value1 : {} Value2 : {}",data[0],data[1]);
        log.info("Value1 : {} Value2 : {}",data[2],data[3]);
        
		return lv;
	}

}

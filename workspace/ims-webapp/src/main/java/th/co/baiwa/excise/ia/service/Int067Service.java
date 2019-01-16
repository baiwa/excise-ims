package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.PublicUtilityDao;
import th.co.baiwa.excise.ia.persistence.entity.PublicUtility;
import th.co.baiwa.excise.ia.persistence.repository.PublicUtilityRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int068FormVo;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Service
public class Int067Service {
	
	@Autowired
	private PublicUtilityRepository publicUtilityRepository;
	
	@Autowired
	private PublicUtilityDao publicUtilityDao;
	
	public DataTableAjax<Int068FormVo> qeuryByFilter(PublicUtility pu) {
		List<Int068FormVo> listData = new ArrayList<Int068FormVo>();
		
		if(BeanUtils.isNotEmpty(pu)) {
			listData = publicUtilityDao.queryByFilter(pu);
		}
		
		DataTableAjax<Int068FormVo> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal((long) listData.size());
		dataTableAjax.setRecordsFiltered((long) listData.size());
		dataTableAjax.setData(listData);
		return dataTableAjax;
	}
}

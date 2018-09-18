package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurement;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurementList;
import th.co.baiwa.excise.ia.persistence.repository.IaProcurementListRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaProcurementRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int0541Vo;

@Service
public class Int054Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IaProcurementRepository iaPcmRepository;
	
	@Autowired
	private IaProcurementListRepository iaPcmListRepository;
	
	public ResponseDataTable<Int0541Vo> oderByfilter(IaProcurement pcm) {
		ResponseDataTable<Int0541Vo> data = new ResponseDataTable<>();
		List<IaProcurement> filterPcm = new ArrayList<IaProcurement>();
		int count = (int) iaPcmRepository.count();
		filterPcm = iaPcmRepository.oderByfilter(pcm.getBudgetYear(), pcm.getBudgetType(), pcm.getSupplyChoice());
		
		List<Int0541Vo> dataList = new ArrayList<Int0541Vo>();
		Int0541Vo obj;
		List<IaProcurementList> listObjId = new ArrayList<IaProcurementList>();
		if(filterPcm.size() > 0) {
			for (IaProcurement f : filterPcm) {
				obj = new Int0541Vo();
				listObjId = iaPcmListRepository.findByIdFilter(f.getProcurementId());
				
				Long totalPrice = 0L;
				for (IaProcurementList objId : listObjId) {
					if(listObjId.size() > 1) {
						totalPrice += objId.getPrice();
					}else {
						totalPrice = objId.getPrice();
					}	
				}
				obj.setPrice(totalPrice);
				obj.setProcurementId(f.getProcurementId());
				obj.setBudgetYear(f.getBudgetYear());
				obj.setPoNumber(f.getPoNumber());
				obj.setProjectCodeEgp(f.getProjectCodeEgp());
				obj.setProjectName(f.getProjectName());
				obj.setUpdatedDate(DateConstant.convertDateToStrDDMMYYYYHHmm(f.getUpdatedDate()));
				obj.setStatus(f.getStatus());
							
				dataList.add(obj);
			}
		}
		data.setData(dataList);
		data.setDraw(count);
		data.setLength(count);
		return data;
	}
	
	@Transactional
	public Message deletePcm(String idList) {
		try {
			String[] str = idList.split(",");
			List<Long> id = new ArrayList<Long>();
			List<IaProcurementList> listObjId = new ArrayList<IaProcurementList>();
			for (String value : str) {
				id.add(Long.valueOf(value));
//				iaPcmListRepository.deletePcmList(Long.valueOf(value));
				
				iaPcmRepository.delete(id);
				listObjId = iaPcmListRepository.findByIdFilter(Long.valueOf(value));
				for (IaProcurementList obj : listObjId) {
					obj.setIsDeleted("Y");
					iaPcmListRepository.save(obj);
				}
			}
//			iaPcmRepository.delete(id);
//			iaPcmListRepository.deletePcmList(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ApplicationCache.getMessage("MSG_00006");
		}
		return ApplicationCache.getMessage("MSG_00005");
	}
}

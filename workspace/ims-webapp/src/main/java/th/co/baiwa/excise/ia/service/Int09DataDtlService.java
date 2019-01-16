package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09FormDtlVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09TableDtlVo;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Service
public class Int09DataDtlService {
	private static Logger log = LoggerFactory.getLogger(Int09DataDtlService.class);
	
	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;
	
	@Autowired
	private LovRepository lovRepository;

	
	public Int09TableDtlVo getDataDtl(Int09FormDtlVo formDtlVo) throws ParseException {
		Int09TableDtlVo vo = new Int09TableDtlVo();
		
		Float totalMoney = 0f;
		vo.setIdProcess(formDtlVo.getIdProcess());
		vo.setName(formDtlVo.getName()+" "+formDtlVo.getLastName());
		vo.setPosition(formDtlVo.getPosition());
		
		vo.setFeedDay(formDtlVo.getNumberDateAllowance());
		
		Float feedMoney = formDtlVo.getAllowanceTotal().floatValue();
		vo.setFeedMoney(new BigDecimal(feedMoney));
		totalMoney+=vo.getFeedMoney().floatValue();
		
		vo.setRoostDay(formDtlVo.getNumberDateRoost());
		
		Float roostMoney =  formDtlVo.getRoostTotal().floatValue();
		vo.setRoostMoney(new BigDecimal(roostMoney));
		totalMoney+=vo.getRoostMoney().floatValue();
		
		vo.setPassage(formDtlVo.getPassage());
		totalMoney+=vo.getPassage().floatValue();
		
		vo.setOtherExpenses(formDtlVo.getOtherExpenses());
		totalMoney+=vo.getOtherExpenses().floatValue();
		
		vo.setTotalMoney(new BigDecimal(totalMoney));
		vo.setRemark(formDtlVo.getRemark());
		
		return vo;
	}

	public Long saveDataDtlAndDataFormDtl(Int09FormDtlVo formDtlVo,Int09TableDtlVo vo) {
		Long id = iaTravelEstimatorDao.saveDataDtl(vo);
		iaTravelEstimatorDao.saveDataFormDtl(id,formDtlVo);
		return id;
	}
	
	public void editDataDtlAndDataFormDtl(Int09FormDtlVo formDtlVo,Int09TableDtlVo vo) {
		
		log.info("editDataDtl ID : {}",vo.getId());
		iaTravelEstimatorDao.editDataDtl(vo);
		
		log.info("editDataFormDtl ID : {}",formDtlVo.getId());
		iaTravelEstimatorDao.editDataFormDtl(formDtlVo);
	}
	
//	public Float getFeedMoney(Int09FormDtlVo formDtlVo) {
//		Float feedMoney = 0f;
//		if("1183".equals(formDtlVo.getAllowance())) {
//			//ค่าเบี้ยเลี้ยง กรณีปกติ เอาระดับมาคิด
//			Lov data = lovRepository.findByTypeAndLovId("ACC_FEE",Long.parseLong(formDtlVo.getGrade()));
//			feedMoney = Float.parseFloat(data.getValue1());
//
//		}else {
//			//ค่าเบี้ยเลี้ยง กรณีฝึกอบรม เอากรณีฝึกอบรมมาคิด
//			Lov data = lovRepository.findByTypeAndLovId("ACC_FEE",Long.parseLong(formDtlVo.getTraining()));
//			feedMoney = Float.parseFloat(data.getValue2());
//			
//		}
//		return feedMoney;
//	}
	
//	public Float getRoostMoney(Int09FormDtlVo formDtlVo) {
//		Float roostMoney = 0f;
//		if("1186".equals(formDtlVo.getRoost())) {
//			//ค่าที่พัก กรณีปกติ เอาระดับมาคิด
//			Lov data = lovRepository.findByTypeAndLovId("ACC_FEE",Long.parseLong(formDtlVo.getGrade()));
//			
//			if("1191".equals(formDtlVo.getRoomType())) {
//				//ค่าที่พัก กรณีปกติ ห้องเดี่ยว
//				roostMoney = Float.parseFloat(data.getValue2());
//			}else {
//				//ค่าที่พัก กรณีปกติ ห้องคู่
//				roostMoney = Float.parseFloat(data.getValue3());
//			}
//
//		}else if("1187".equals(formDtlVo.getRoost())) {
//			//ค่าที่พัก กรณีเหมาจ่าย เอาระดับมาคิด
//			Lov data = lovRepository.findByTypeAndLovId("ACC_FEE",Long.parseLong(formDtlVo.getGrade()));
//			roostMoney = Float.parseFloat(data.getValue4());
//
//		}else{
//			//ค่าที่พัก กรณีฝึกอบรม เอาระดับมาคิด
//			Lov data = lovRepository.findByTypeAndLovId("ACC_FEE",Long.parseLong(formDtlVo.getTrainingType()));
//			
//			if("1191".equals(formDtlVo.getRoomType())) {
//				//ค่าที่พัก กรณีฝึกอบรม ห้องเดี่ยว
//				roostMoney = Float.parseFloat(data.getValue2());
//			}else {
//				//ค่าที่พัก กรณีฝึกอบรม ห้องคู่
//				roostMoney = Float.parseFloat(data.getValue4());
//			}
//		}
//		return roostMoney;
//	}
	
	public Float getNumberDateAllowance(String dateF1,String dateF2) throws ParseException {
		  Float feedDay = 0f;
		  Date date1 = DateConstant.convertStringDDMMYYYYHHmmToDate(dateF1);
		  Date date2 = DateConstant.convertStringDDMMYYYYHHmmToDate(dateF2);
		  
		  long diffInMillies = date2.getTime() - date1.getTime();
		  List<TimeUnit> units = new ArrayList<>(EnumSet.allOf(TimeUnit.class));
		  Collections.reverse(units);
		  Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
		  long milliesRest = diffInMillies;
		  
		  for ( TimeUnit unit : units ) {
		   long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
		   long diffInMilliesForUnit = unit.toMillis(diff);
		   milliesRest = milliesRest - diffInMilliesForUnit;
		   result.put(unit,diff);
		  }
		  
		  log.info("Date 1 : " + dateF1);
		  log.info("Date 2 : " + dateF2);
		  log.info("Number Date DD HH : {}:{}",result.get(TimeUnit.DAYS),result.get(TimeUnit.HOURS));
		  
		  feedDay = result.get(TimeUnit.DAYS).floatValue()+(result.get(TimeUnit.HOURS)*0.01f);
		  log.info("feedDay : " + feedDay);
		  return feedDay;
	}
	
}

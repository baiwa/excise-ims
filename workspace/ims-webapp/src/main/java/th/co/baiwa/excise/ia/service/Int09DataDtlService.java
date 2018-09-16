package th.co.baiwa.excise.ia.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.dao.IaTravelEstimatorDao;
import th.co.baiwa.excise.ia.persistence.vo.Int09FormDtlVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09TableDtlVo;

@Service
public class Int09DataDtlService {
	private static Logger log = LoggerFactory.getLogger(Int09DataDtlService.class);
	
	@Autowired
	private IaTravelEstimatorDao iaTravelEstimatorDao;
	
	@Autowired
	private LovRepository lovRepository;

	
	public Int09TableDtlVo getDataDtl(Int09FormDtlVo formDtlVo) throws ParseException {
		Int09TableDtlVo vo = new Int09TableDtlVo();
		long totalMoney = 0;
		vo.setIdProcess(formDtlVo.getIdProcess());
		vo.setName(formDtlVo.getName()+" "+formDtlVo.getLastName());
		vo.setPosition(formDtlVo.getPosition());
		
		Long feedDay = getFeedDay(formDtlVo.getDepartureDate(),formDtlVo.getReturnDate());
		vo.setFeedDay(feedDay);
		
		Long feedMoney = getFeedMoney(formDtlVo);
		vo.setFeedMoney(feedMoney*feedDay);
		totalMoney+=vo.getFeedMoney();
		
		vo.setRoostDay(Long.parseLong(formDtlVo.getNumberDate()));
		
		Long roostMoney = getRoostMoney(formDtlVo);
		vo.setRoostMoney(roostMoney*Long.parseLong(formDtlVo.getNumberDate()));
		totalMoney+=vo.getRoostMoney();
		
		vo.setPassage(formDtlVo.getPassage());
		totalMoney+=vo.getPassage();
		
		vo.setOtherExpenses(formDtlVo.getOtherExpenses());
		totalMoney+=vo.getOtherExpenses();
		
		vo.setTotalMoney(totalMoney);
		vo.setRemark(formDtlVo.getRemark());
		return vo;
	}

	public Long saveDataDtlAndDataFormDtl(Int09FormDtlVo formDtlVo,Int09TableDtlVo vo) {
		Long id = iaTravelEstimatorDao.saveDataDtl(vo);
		iaTravelEstimatorDao.saveDataFormDtl(id,formDtlVo);
		return id;
	}
	
	public Long getFeedMoney(Int09FormDtlVo formDtlVo) {
		long feedMoney = 0;
		if("1183".equals(formDtlVo.getAllowance())) {
			//ค่าเบี้ยเลี้ยง กรณีปกติ เอาระดับมาคิด
			Lov data = lovRepository.findByTypeAndLovId("ACC_FEE",Long.parseLong(formDtlVo.getGrade()));
			feedMoney = Long.parseLong(data.getValue1());

		}else {
			//ค่าเบี้ยเลี้ยง กรณีฝึกอบรม เอากรณีฝึกอบรมมาคิด
			Lov data = lovRepository.findByTypeAndLovId("ACC_FEE",Long.parseLong(formDtlVo.getTraining()));
			feedMoney = Long.parseLong(data.getValue2());
			
		}
		return feedMoney;
	}
	
	public Long getRoostMoney(Int09FormDtlVo formDtlVo) {
		long roostMoney = 0;
		if("1186".equals(formDtlVo.getRoost())) {
			//ค่าที่พัก กรณีปกติ เอาระดับมาคิด
			Lov data = lovRepository.findByTypeAndLovId("ACC_FEE",Long.parseLong(formDtlVo.getGrade()));
			
			if("1191".equals(formDtlVo.getRoomType())) {
				//ค่าที่พัก กรณีปกติ ห้องเดี่ยว
				roostMoney = Long.parseLong(data.getValue2());
			}else {
				//ค่าที่พัก กรณีปกติ ห้องคู่
				roostMoney = Long.parseLong(data.getValue3());
			}

		}else if("1187".equals(formDtlVo.getRoost())) {
			//ค่าที่พัก กรณีเหมาจ่าย เอาระดับมาคิด
			Lov data = lovRepository.findByTypeAndLovId("ACC_FEE",Long.parseLong(formDtlVo.getGrade()));
			roostMoney = Long.parseLong(data.getValue4());

		}else{
			//ค่าที่พัก กรณีฝึกอบรม เอาระดับมาคิด
			Lov data = lovRepository.findByTypeAndLovId("ACC_FEE",Long.parseLong(formDtlVo.getTrainingType()));
			
			if("1191".equals(formDtlVo.getRoomType())) {
				//ค่าที่พัก กรณีฝึกอบรม ห้องเดี่ยว
				roostMoney = Long.parseLong(data.getValue2());
			}else {
				//ค่าที่พัก กรณีฝึกอบรม ห้องคู่
				roostMoney = Long.parseLong(data.getValue4());
			}
		}
		return roostMoney;
	}
	
	public Long getFeedDay(String dateF1,String dateF2) throws ParseException {
		  long feedDay = 0;
		  Date date1 = DateUtils.parseDate(dateF1,"dd/MM/yyyy");
		  Date date2 = DateUtils.parseDate(dateF2,"dd/MM/yyyy");
		  
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
		  log.info("FeedDay : " + result.get(TimeUnit.DAYS));
		  
		  feedDay = result.get(TimeUnit.DAYS);
		  return feedDay;
	}
	
}

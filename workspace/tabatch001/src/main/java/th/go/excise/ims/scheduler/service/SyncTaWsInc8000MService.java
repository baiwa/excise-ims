package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.vo.Incfri8000MFormVo;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.persistence.repository.WsIncfri8000Repository;

@Service
public class SyncTaWsInc8000MService {

	private static final Logger logger = LoggerFactory.getLogger(SyncTaWsInc8000MService.class);
	
	private WsIncfri8000Repository wsIncfri8000Repository;
	private TaWsInc8000MRepository taWsInc8000MRepository;
	
	@Autowired
	public SyncTaWsInc8000MService(
			WsIncfri8000Repository wsIncfri8000Repository,
			TaWsInc8000MRepository taWsInc8000MRepository) {
		this.wsIncfri8000Repository = wsIncfri8000Repository;
		this.taWsInc8000MRepository = taWsInc8000MRepository;
	}
	
	public Incfri8000MFormVo buildFormVo(String... args) throws Exception {
		Incfri8000MFormVo formVo = new Incfri8000MFormVo();
		List<LocalDate> localDateList = new ArrayList<>();
		
		if (args.length == 1) {
			formVo.setDateType(WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME);
			localDateList.add(LocalDate.now());
		} else if (args.length == 2) {
			formVo.setDateType(args[1]);
			localDateList.add(LocalDate.now());
		} else if (args.length == 3) {
			formVo.setDateType(args[1]);
			LocalDate localDate = LocalDate.of(Integer.parseInt(args[2].substring(0, 4)), Integer.parseInt(args[2].substring(4, 6)), 1);
			localDateList.add(localDate);
		} else if (args.length == 4) {
			formVo.setDateType(args[1]);
			LocalDate localDateStart = LocalDate.of(Integer.parseInt(args[2].substring(0, 4)), Integer.parseInt(args[2].substring(4, 6)), 1);
			LocalDate localDateEnd = LocalDate.of(Integer.parseInt(args[3].substring(0, 4)), Integer.parseInt(args[3].substring(4, 6)), 1);
			localDateList.addAll(LocalDateUtils.getLocalDateRange(localDateStart, localDateEnd));
		} else {
			throw new Exception("SyncTaWsInc8000M - Wrong Parameter");
		}
		
		formVo.setLocalDateList(localDateList);
		
		return formVo;
	}
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(Incfri8000MFormVo formVo) throws PccRestfulException {
		logger.info("syncData TaWsInc8000M Start");
		long start = System.currentTimeMillis();
		
		String dateTypeCode = ExciseUtils.convertIncfri8000DateType(formVo.getDateType());
		String dateStart = null;
		String dateEnd = null;
		String taxYear = null;
		String taxMonth = null;
		
		List<TaWsInc8000M> taWsInc8000MList = null;
		for (LocalDate localDate : formVo.getLocalDateList()) {
			dateStart = localDate.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
			dateEnd = localDate.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
			taxYear = String.valueOf(localDate.getYear());
			taxMonth = StringUtils.leftPad(String.valueOf(localDate.getMonthValue()), 2, "0");
			taWsInc8000MRepository.forceDeleteByTaxYearAndTaxMonth(taxYear, taxMonth);
			
			taWsInc8000MList = wsIncfri8000Repository.findFor8000M(dateTypeCode, dateStart, dateEnd);
		}
		
		taWsInc8000MRepository.batchInsert(taWsInc8000MList);
		logger.info("Batch Insert TA_WS_INC8000_M Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData TaWsInc8000M Success, using {} seconds", (float) (end - start) / 1000F);
	}

}

package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.incfri8000.model.RequestData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsIncfri8000ServiceTest {
	
	@Autowired
	private SyncWsIncfri8000Service syncWsIncfri8000Service;
	
	@Test
	public void test_syncData() throws PccRestfulException {
		RequestData requestData = null;
		
		LocalDate dateStart = LocalDate.of(2017, 1, 1);
		LocalDate dateEnd = LocalDate.of(2019, 3, 1);
		
		List<LocalDate> dateList = getLocalDateRange(dateStart, dateEnd);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM", Locale.US);
		
		for (LocalDate localDate : dateList) {
			requestData = new RequestData();
			requestData.setDateType(WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME);
			requestData.setYearMonthFrom(formatter.format(localDate));
			requestData.setYearMonthTo(formatter.format(localDate));
			syncWsIncfri8000Service.syncData(requestData);
		}
	}
	
	private List<LocalDate> getLocalDateRange(LocalDate dateStart, LocalDate dateEnd) {
		long numOfDaysBetween = ChronoUnit.DAYS.between(dateStart, dateEnd);
		List<LocalDate> dateList = IntStream.iterate(0, i -> i + 1)
		    .limit(numOfDaysBetween + 1)
		    .mapToObj(i -> {
		        LocalDate nextLocalDate = dateStart.plusDays(i);
		        return LocalDate.of(nextLocalDate.getYear(), nextLocalDate.getMonth(), 1); 
		    })
		    .distinct()
		    .collect(Collectors.toList());
		//dList.forEach(System.out::println);
		return dateList;
	}
	
}

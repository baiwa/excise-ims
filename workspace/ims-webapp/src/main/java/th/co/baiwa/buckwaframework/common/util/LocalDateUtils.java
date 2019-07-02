package th.co.baiwa.buckwaframework.common.util;

import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LocalDateUtils {
	
	public static List<LocalDate> getLocalDateRange(LocalDate dateStart, LocalDate dateEnd) {
		long numOfDaysBetween = ChronoUnit.DAYS.between(dateStart, dateEnd);
		List<LocalDate> dateList = IntStream.iterate(0, i -> i + 1)
		    .limit(numOfDaysBetween + 1)
		    .mapToObj(i -> {
		        LocalDate nextLocalDate = dateStart.plusDays(i);
		        return LocalDate.of(nextLocalDate.getYear(), nextLocalDate.getMonth(), 1); 
		    })
		    .distinct()
		    .collect(Collectors.toList());
		return dateList;
	}
	
	public static boolean isRange(LocalDate dateStart, LocalDate dateEnd, LocalDate localDate) {
		return (localDate.isEqual(dateStart) || localDate.isAfter(dateStart))
			&& (localDate.isEqual(dateEnd) || localDate.isBefore(dateEnd));
	}
	
	public static LocalDate thaiMonthYear2LocalDate(String inputDate) {
		return LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(inputDate.split("/")[1]), Integer.parseInt(inputDate.split("/")[0]), 1));
	}
	
}

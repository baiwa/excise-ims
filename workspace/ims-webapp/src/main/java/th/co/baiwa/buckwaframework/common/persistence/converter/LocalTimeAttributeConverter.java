package th.co.baiwa.buckwaframework.common.persistence.converter;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time> {
	
	@Override
	public Time convertToDatabaseColumn(LocalTime localTime) {
		return Optional.ofNullable(localTime)
			.map(Time::valueOf)
			.orElse(null);
	}
	
	@Override
	public LocalTime convertToEntityAttribute(Time time) {
		return Optional.ofNullable(time)
			.map(Time::toLocalTime)
			.orElse(null);
	}
	
}

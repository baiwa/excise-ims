package th.co.baiwa.buckwaframework.common.rest.adapter;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class BigDecimalTypeAdapter extends TypeAdapter<BigDecimal> {
	
	private BigDecimalTypeAdapter() {
	}
	
	private static class SingletonHolder {
		private static final BigDecimalTypeAdapter instance = new BigDecimalTypeAdapter();
	}
	
	public static BigDecimalTypeAdapter getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public void write(JsonWriter writer, BigDecimal value) throws IOException {
		writer.value(value);
	}

	@Override
	public BigDecimal read(JsonReader reader) throws IOException {
		return StringUtils.isNumeric(reader.nextString()) ? new BigDecimal(reader.nextString()) : null;
	}

}

package th.co.baiwa.buckwaframework.common.rest.adapter;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;

public class DateThaiJsonDeserializer implements JsonDeserializer<Date> {
	
	private DateThaiJsonDeserializer() {
	}
	
	private static class SingletonHolder {
		private static final DateThaiJsonDeserializer instance = new DateThaiJsonDeserializer();
	}
	
	public static DateThaiJsonDeserializer getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return ConvertDateUtils.parseStringToDate(json.getAsString(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
	}

}

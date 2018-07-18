package th.co.baiwa.excise.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.exampleproject.ws.consumer.currentdate.operation.CurrentDate;

@Service
public class CurrentDateService {
	
	@Autowired
	private CurrentDate currentDateProxy;
	
	public Date exciseSystemDate() {
		String strExcideDate = currentDateProxy.ecxiseSystemDate();
		Date exciseDate = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss XXX", new Locale("en", "EN"));
		try {
			exciseDate = format.parse(strExcideDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return exciseDate;
	}
	
}

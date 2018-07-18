package th.co.baiwa.excise.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import th.co.baiwa.excise.ws.CurrentDateService;

@Component
public class ExciseCurrentDate {

	
	@Autowired
	private CurrentDateService currentDateService;
	public static CurrentDateService exciseProxy;

	
	@PostConstruct
	private void init() {
		exciseProxy = currentDateService;
	}
}

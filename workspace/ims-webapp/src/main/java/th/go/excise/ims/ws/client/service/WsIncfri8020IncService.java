package th.go.excise.ims.ws.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ws.client.persistence.entity.WsIncfri8020Inc;
import th.go.excise.ims.ws.client.persistence.repository.WsIncfri8020IncRepository;

@Service
public class WsIncfri8020IncService {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8020IncService.class);
	
	@Autowired
	private WsIncfri8020IncRepository wsIncfri8020IncRepository;
	
	public WsIncfri8020Inc saveWsIncfri8020Inc(WsIncfri8020Inc wsIncfri8020Inc) {
		logger.info("saveWsIncfri8020Inc");
		wsIncfri8020IncRepository.save(wsIncfri8020Inc);
		return wsIncfri8020Inc;
	}
	
	
}

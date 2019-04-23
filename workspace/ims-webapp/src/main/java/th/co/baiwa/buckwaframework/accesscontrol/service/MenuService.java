package th.co.baiwa.buckwaframework.accesscontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private  MenuRepository menuRepository;
	
	public void list() {
		
	}
	
	
}

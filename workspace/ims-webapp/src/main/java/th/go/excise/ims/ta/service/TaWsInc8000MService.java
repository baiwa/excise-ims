package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaWsInc8000;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000Repository;

@Service
public class TaWsInc8000MService {

	@Autowired
	private TaWsInc8000Repository taWsInc8000Repository;
	
	@Autowired
	private TaWsInc8000MRepository taWsInc8000MRepository;
	
	public void loadDataInc8000TInc8000M() {
		List<TaWsInc8000> taWsInc8000List = taWsInc8000Repository.findAll();
		Map<String,List<TaWsInc8000>> mapOf8000List = new HashMap<>();
		List<TaWsInc8000> incList = null;
		for (TaWsInc8000 taWsInc8000 : taWsInc8000List) {
			if(taWsInc8000.getRegId() != null) {
				incList = new ArrayList<>();
				incList = mapOf8000List.get(taWsInc8000.getRegId());
				if(incList == null) {
					incList = new ArrayList<>();
				}
				incList.add(taWsInc8000);
				mapOf8000List.put(taWsInc8000.getRegId(), incList);
			}
			
		}
		

	}
}

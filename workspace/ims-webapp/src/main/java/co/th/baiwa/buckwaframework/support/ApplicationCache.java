package co.th.baiwa.buckwaframework.support;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.th.ims.excise.domain.ExciseArea;
import co.th.ims.excise.domain.ExciseBranch;
import co.th.ims.excise.domain.ExciseSector;
import co.th.ims.excise.service.ExciseSectorService;

@Component
public class ApplicationCache {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationCache.class);
	// ListOfValue `ECERT_LISTOFVALUE`
	
	@Autowired
	public final ExciseSectorService exciseSectorService = null;

	
	private static final List<ExciseSector> EXCISE_SECTOR_LIST = new ArrayList<ExciseSector>();
	private static final ConcurrentHashMap<String, ExciseArea> EXCISE_AREA_MAPPER = new ConcurrentHashMap<String, ExciseArea>();
	private static final ConcurrentHashMap<String, ExciseBranch> EXCISE_BRANCH_MAPPER = new ConcurrentHashMap<String, ExciseBranch>();
	
	
	@Autowired
	public ApplicationCache() {
		super();
	}
	
	/** Reload */
	@PostConstruct
	public synchronized void reloadCache() {
		logger.info("ApplicationCache Reloading...");

	
		exciseSectorAreaBranchInitialData();
		logger.info("ApplicationCache Reloaded");
	}
	
	public void exciseSectorAreaBranchInitialData() {
		logger.info("exciseSectorAreaBranchInitialData");
		List<ExciseSector> exciseSectorList = exciseSectorService.findAllExciseSector();
		if(exciseSectorList != null && exciseSectorList.size() > 0) {
			for (ExciseSector exciseSector : exciseSectorList) {
				EXCISE_SECTOR_LIST.add(exciseSector);
				
			}
		}
//		EXCISE_AREA_MAPPER
		for (ExciseSector exciseSector : exciseSectorList) {
			
		}
	}
	
	
	
}

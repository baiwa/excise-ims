
package co.th.baiwa.buckwaframework.support;

import java.math.BigDecimal;
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
import co.th.ims.excise.service.ExciseAreaService;
import co.th.ims.excise.service.ExciseBranchService;
import co.th.ims.excise.service.ExciseSectorService;

@Component
public class ApplicationCache {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationCache.class);
	// ListOfValue `ECERT_LISTOFVALUE`
	
	@Autowired
	public final ExciseSectorService exciseSectorService = null;
	
	@Autowired
	public final ExciseAreaService exciseAreaService = null;
	
	@Autowired
	public final ExciseBranchService exciseBranchService = null;

	
	private static final List<ExciseSector> EXCISE_SECTOR_LIST = new ArrayList<ExciseSector>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseArea>> EXCISE_AREA_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseArea>>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseBranch>> EXCISE_BRANCH_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseBranch>>();
	
	
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
		List<ExciseArea> exciseAreaList = null;
		List<ExciseBranch> exciseBranchList = null;
		for (ExciseSector exciseSector : exciseSectorList) {
			exciseAreaList = new ArrayList<>();
			exciseAreaList = exciseAreaService.findAllExciseSector(exciseSector.getSectorId());
			if(exciseAreaList != null && exciseAreaList.size() >= 0) {
				EXCISE_AREA_MAPPER.put(exciseSector.getSectorId(),exciseAreaList);
				for (ExciseArea exciseArea : exciseAreaList) {
					exciseBranchList =  new ArrayList<ExciseBranch>();
					exciseBranchList = exciseBranchService.findBySectorId(exciseArea.getAreaId());
					if(exciseBranchList != null) {
						EXCISE_BRANCH_MAPPER.put(exciseArea.getAreaId(), exciseBranchList);
					}
				}
			}
		}
	}
	
	public static List<ExciseSector> getExciseSectorList(){
		return EXCISE_SECTOR_LIST;
	}
	
	public static List<ExciseArea> getExciseAreaList(BigDecimal sectorId){
		return EXCISE_AREA_MAPPER.get(sectorId);
	}
	
	public static List<ExciseBranch> getExciseBranchList(BigDecimal areaId){
		return EXCISE_BRANCH_MAPPER.get(areaId);
	}
	
	
	
	
	
}

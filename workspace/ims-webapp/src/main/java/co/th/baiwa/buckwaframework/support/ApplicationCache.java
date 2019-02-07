
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

import co.th.ims.excise.domain.ExciseAmphur;
import co.th.ims.excise.domain.ExciseArea;
import co.th.ims.excise.domain.ExciseBranch;
import co.th.ims.excise.domain.ExciseDistrict;
import co.th.ims.excise.domain.ExciseGeo;
import co.th.ims.excise.domain.ExciseProvice;
import co.th.ims.excise.domain.ExciseSector;
import co.th.ims.excise.service.ExciseAmphurService;
import co.th.ims.excise.service.ExciseAreaService;
import co.th.ims.excise.service.ExciseBranchService;
import co.th.ims.excise.service.ExciseDistrictService;
import co.th.ims.excise.service.ExciseGeoService;
import co.th.ims.excise.service.ExciseProviceService;
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
	
	@Autowired
	public final ExciseGeoService exciseGeoService = null;
	
	@Autowired
	public final ExciseProviceService exciseProviceService = null;
	
	@Autowired
	public final ExciseAmphurService exciseAmphurService = null;

	@Autowired
	public final ExciseDistrictService exciseDistrictService = null;
	
	private static final List<ExciseSector> EXCISE_SECTOR_LIST = new ArrayList<ExciseSector>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseArea>> EXCISE_AREA_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseArea>>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseBranch>> EXCISE_BRANCH_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseBranch>>();
	
	private static final List<ExciseGeo> EXCISE_GEO_LIST = new ArrayList<ExciseGeo>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseProvice>> EXCISE_PROVICE_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseProvice>>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseAmphur>> EXCISE_AMPHUR_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseAmphur>>();
	private static final ConcurrentHashMap<BigDecimal, List<ExciseDistrict>> EXCISE_DISTRICT_MAPPER = new ConcurrentHashMap<BigDecimal, List<ExciseDistrict>>();
	
	
	@Autowired
	public ApplicationCache() {
		super();
	}
	
	/** Reload */
	@PostConstruct
	public synchronized void reloadCache() {
		logger.info("ApplicationCache Reloading...");
		
		loadSectorAreaBranch();
		loadGioProviceAumhurDistrictAndMapping();
		
		logger.info("ApplicationCache Reloaded");
	}
	
	public void loadSectorAreaBranch() {
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
	
	public void loadGioProviceAumhurDistrictAndMapping() {
		List<ExciseGeo> ecExciseGeoList = exciseGeoService.findExciseGeoListByCriteria(null);
		List<ExciseAmphur> exciseAmphurList;
		ExciseProvice exciseProvice;
		ExciseAmphur exciseAmphur;
		ExciseDistrict exciseDistrict;
		List<ExciseProvice> exciseProviceList;
		List<ExciseDistrict> exciseDistricList;
		for (ExciseGeo exciseGeo : ecExciseGeoList) {
			EXCISE_GEO_LIST.add(exciseGeo);
			exciseProvice = new ExciseProvice();
			exciseProvice.setGeoId(exciseGeo.getGeoId());
			exciseProviceList = new ArrayList<>();
			exciseProviceList = exciseProviceService.findProviceByCriteria(exciseProvice);
			EXCISE_PROVICE_MAPPER.put(exciseGeo.getGeoId(), exciseProviceList);
			for (ExciseProvice provice : exciseProviceList) {
				exciseAmphur = new ExciseAmphur();
				exciseAmphur.setProvinceId(provice.getProviceId());
				exciseAmphurList = new ArrayList<ExciseAmphur>();
				exciseAmphurList = exciseAmphurService.findExciseAmphurListByCriteria(exciseAmphur);
				EXCISE_AMPHUR_MAPPER.put(provice.getProviceId(), exciseAmphurList);
				for (ExciseAmphur amphur : exciseAmphurList) {
					exciseDistrict = new ExciseDistrict();
					exciseDistrict.setAmphurId(amphur.getAmphurId());
					exciseDistricList = new ArrayList<ExciseDistrict>();
					exciseDistricList = exciseDistrictService.findExciseDistrictListByCriteria(exciseDistrict);
					EXCISE_DISTRICT_MAPPER.put(amphur.getAmphurId(), exciseDistricList);
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
	
	public static List<ExciseGeo> getExciseGeoList(BigDecimal geoId){
		return EXCISE_GEO_LIST;
	}
	
	public static List<ExciseProvice> getExciseProviceList(BigDecimal proviceId){
		return EXCISE_PROVICE_MAPPER.get(proviceId);
	}
	
	public static List<ExciseAmphur> getExciseAmphurList(BigDecimal amphurId){
		return EXCISE_AMPHUR_MAPPER.get(amphurId);
	}
	
	public static List<ExciseDistrict> getExciseDistrictList(BigDecimal districtId){
		return EXCISE_DISTRICT_MAPPER.get(districtId);
	}
	
	
	
	
}

package th.co.baiwa.excise.ia.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.domain.Int0533Vo;
import th.co.baiwa.excise.ia.persistence.entity.AssetBalance;
import th.co.baiwa.excise.ia.persistence.entity.AssetMaintenance;
import th.co.baiwa.excise.ia.service.AssetBalanceService;
import th.co.baiwa.excise.ia.service.AssetMaintenanceService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/ia/int0533")
public class Int0533Controller {
	private Logger logger = LoggerFactory.getLogger(Int0533Controller.class);

	@Autowired
	private AssetBalanceService assetBalanceService;

	@Autowired
	private AssetMaintenanceService assetMaintenanceService;

	@PostMapping("/addAssetBalance")
	@ResponseBody
	public Message addAssetBalance(@RequestBody Int0533Vo int0533Vo) {
		Message message = null;
		logger.info("addAssetBalance" + int0533Vo.getAssetBalance().getAssetType());
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeId();

		AssetBalance assetBalance = int0533Vo.getAssetBalance();
		assetBalance.setExciseDepartment(officeCode.substring(0, 2));
		assetBalance.setExciseDistrict(officeCode.substring(2, 4));
		assetBalance.setExciseRegion(officeCode.substring(4));
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate diffDate = int0533Vo.getAssetBalance().getDateOfManufacture().toInstant().atZone(defaultZoneId).toLocalDate();
		diffDate = diffDate.plusDays(int0533Vo.getDay()).plusMonths(int0533Vo.getMonth()).plusYears(int0533Vo.getYear());

		long daysBetween = ChronoUnit.DAYS.between(DateConstant.dateToLocalDadte(int0533Vo.getAssetBalance().getDateOfManufacture()), diffDate);
		assetBalance.setAccumulatedDepreciation(new BigDecimal(daysBetween));
		assetBalance = assetBalanceService.saveAssetBalance(assetBalance);
		if (BeanUtils.isNotEmpty(assetBalance.getAssetBalanceId())) {
			message = ApplicationCache.getMessage("MSG_00002");
		} else {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}

	@PostMapping("/dataTableAssetBalance")
	@ResponseBody
	public ResponseDataTable<AssetBalance> dataTableAssetBalance(DataTableRequest dataTableRequest, AssetBalance assetBalance) {
		logger.info("dataTableAssetBalance");
		ResponseDataTable<AssetBalance> responseDataTable = new ResponseDataTable<AssetBalance>();
		List<AssetBalance> assetList = assetBalanceService.findAssetBalanceByCriteria(assetBalance);
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(assetList);
		responseDataTable.setRecordsTotal(assetList.size());
		responseDataTable.setRecordsFiltered(assetList.size());
		return responseDataTable;
	}

	@PostMapping("/findAssetBalance")
	@ResponseBody
	public Int0533Vo findAssetBalance(@RequestBody AssetBalance assetBalance) {
		logger.info("findAssetBalance");
		assetBalance = assetBalanceService.findAssetBalanceById(assetBalance.getAssetBalanceId());
		Int0533Vo int0533Vo = new Int0533Vo();
		int0533Vo.setAssetBalance(assetBalance);
		int diffDay = assetBalance.getAccumulatedDepreciation().intValue();

		if (diffDay != 0) {
			LocalDate dateOfManufacture = DateConstant.dateToLocalDadte(assetBalance.getDateOfManufacture());
			LocalDate diffDate = dateOfManufacture.plusDays(diffDay);
			Period period = Period.between(dateOfManufacture, diffDate);
			int0533Vo.setYear((long) period.getYears());
			int0533Vo.setMonth((long) period.getMonths());
			int0533Vo.setDay((long) period.getDays());
		} else {
			Long zero = new Long(0);
			int0533Vo.setYear(zero);
			int0533Vo.setMonth(zero);
			int0533Vo.setDay(zero);
		}
		return int0533Vo;
	}

	@PostMapping("/addAssetMaintenance")
	@ResponseBody
	public Message addAssetMaintenance(@RequestBody Int0533Vo int0533Vo) {
		Message message = null;
		logger.info("addAssetMaintenance" + int0533Vo.getAssetMaintenance());
		AssetMaintenance assetMaintenance = assetMaintenanceService.saveAssetMaintenance(int0533Vo.getAssetMaintenance());
		if (BeanUtils.isNotEmpty(assetMaintenance.getMaintenanceId())) {
			message = ApplicationCache.getMessage("MSG_00002");
		} else {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}

	@PostMapping("/deleteAssetBalanceList")
	@ResponseBody
	public Message deleteAssetBalanceList(@RequestBody Int0533Vo int0533Vo) {
		Message message = null;
		logger.info("deleteAssetBalanceList");
		try {
			assetBalanceService.deleteAssetBalance(int0533Vo.getAssetBalanceIdList());
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}

	@PostMapping("/getCountAssetMaintenance")
	@ResponseBody
	public Integer getCountAssetMaintenance(@RequestBody Int0533Vo int0533Vo) {
		logger.info("getCountAssetMaintenance : {} ", int0533Vo.getAssetBalanceId());
		return assetMaintenanceService.getCountByAssetBalanceId(int0533Vo.getAssetBalanceId());
	}

	@PostMapping("/dataTableAssetMaintenance")
	@ResponseBody
	public ResponseDataTable<AssetMaintenance> dataTableAssetMaintenance(DataTableRequest dataTableRequest, AssetMaintenance assetMaintenance) {
		logger.info("dataTableAssetMaintenance");
		ResponseDataTable<AssetMaintenance> responseDataTable = new ResponseDataTable<AssetMaintenance>();
		List<AssetMaintenance> assetList = assetMaintenanceService.findByAssetBalanceId(assetMaintenance.getAssetBalanceId());
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(assetList);
		responseDataTable.setRecordsTotal(assetList.size());
		responseDataTable.setRecordsFiltered(assetList.size());
		return responseDataTable;
	}
}

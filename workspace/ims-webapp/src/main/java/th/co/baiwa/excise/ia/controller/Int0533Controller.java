package th.co.baiwa.excise.ia.controller;

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
		assetBalance = assetBalanceService.saveAssetBalance(assetBalance);
		if (BeanUtils.isNotEmpty(assetBalance.getAssetBalanceId())) {
			message = ApplicationCache.getMessage("MSG_00002");
			AssetMaintenance assetMaintenance = int0533Vo.getAssetMaintenance();
			if(BeanUtils.isNotEmpty(assetMaintenance.getMaintenanceAmount())) {
				assetMaintenance.setAssetBalanceId(assetBalance.getAssetBalanceId());
				assetMaintenance = assetMaintenanceService.saveAssetMaintenance(assetMaintenance);
				if (BeanUtils.isNotEmpty(assetMaintenance.getMaintenanceId())) {
					message = ApplicationCache.getMessage("MSG_00002");
				} else {
					message = ApplicationCache.getMessage("MSG_00003");
				}
			}
		} else {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}
	
	@PostMapping("/dataTableAssetBalance")
	@ResponseBody
	public ResponseDataTable<AssetBalance> dataTableAssetBalance(DataTableRequest dataTableRequest , AssetBalance assetBalance) {
		ResponseDataTable<AssetBalance> responseDataTable = new ResponseDataTable<AssetBalance>();
		List<AssetBalance> assetList = assetBalanceService.findAssetBalanceByCriteria(assetBalance);
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(assetList);
		responseDataTable.setRecordsTotal(assetList.size());
		responseDataTable.setRecordsFiltered(assetList.size());
		return responseDataTable;
	}

}

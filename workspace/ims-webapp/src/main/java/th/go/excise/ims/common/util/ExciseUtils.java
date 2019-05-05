package th.go.excise.ims.common.util;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegMaster60;

public class ExciseUtils {
	
	public static final Logger logger = LoggerFactory.getLogger(ExciseUtils.class);
	
	private static List<ParamInfo> paramInfoList = null;

	static {
		paramInfoList = new ArrayList<>();
		paramInfoList.addAll(ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.EXCISE_PRODUCT_TYPE));
		paramInfoList.addAll(ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.EXCISE_SERVICE_TYPE));
	}

	public static boolean isCentral(String officeCode) {
		return isValidOfficeCode(officeCode) && "00".equals(officeCode.substring(0, 2));
	}

	public static boolean isSector(String officeCode) {
		return isValidOfficeCode(officeCode) && Pattern.matches("(0[1-9]|10)0{4}", officeCode);
	}

	public static boolean isArea(String officeCode) {
		return isValidOfficeCode(officeCode) && Pattern.matches("(0[1-9]|10)(0[1-9]|[1-9][0-9])0{2}", officeCode);
	}

	public static boolean isBranch(String officeCode) {
		return isValidOfficeCode(officeCode) && Pattern.matches("(0[1-9]|10)(0[1-9]|[1-9][0-9])(0[1-9]|[1-9][0-9])", officeCode);
	}

	public static String whereInLocalOfficeCode(String officeCode) {
		if (isValidOfficeCode(officeCode)) {
			if (isCentral(officeCode)) {
				officeCode = "%";
			} else if (isSector(officeCode)) {
				officeCode = officeCode.substring(0, 2) + "____";
			} else if (isArea(officeCode)) {
				officeCode = officeCode.substring(0, 4) + "__";
			}
		}
		return officeCode;
	}

	private static boolean isValidOfficeCode(String officeCode) {
		return officeCode != null && officeCode.length() == 6;
	}

	public static String getCurrentBudgetYear() {
		return getBudgetYearByLocalDate(LocalDate.now());
	}

	public static String getBudgetYearByLocalDate(LocalDate localDate) {
		int budgetYear = ThaiBuddhistDate.from(localDate).get(ChronoField.YEAR);
		if (localDate.getMonthValue() >= 10) {
			budgetYear++;
		}
		return String.valueOf(budgetYear);
	}

	public static String getDutyDesc(String dutyCode) {
		for (ParamInfo paramInfo : paramInfoList) {
			if (paramInfo.getParamCode().equals(dutyCode)) {
				return paramInfo.getValue1();
			}
		}
		return null;
	}

	public static List<ParamInfo> getProductTypeAndServiceType() {
		return paramInfoList;
	}

	public static String buildCusAddress(RegMaster60 regMaster60) {
		return RegMaster60Utils.buildAddress(regMaster60, RegMaster60Utils.ADDRESS_TYPE_CUS);
	}
	
	public static String buildFacAddress(RegMaster60 regMaster60) {
		return RegMaster60Utils.buildAddress(regMaster60, RegMaster60Utils.ADDRESS_TYPE_FAC);
	}
	
	public static String getFactoryType(String newRegId) {
		String factoryType = null;
		if (StringUtils.isNotEmpty(newRegId) && newRegId.length() == 17) {
			factoryType = newRegId.substring(13, 14);
		}
		return factoryType;
	}
	
	static class RegMaster60Utils {
		
		private static final String ADDRESS_TYPE_CUS = "Cus";
		private static final String ADDRESS_TYPE_FAC = "Fac";
		
		private static String buildAddress(RegMaster60 regMaster60, String addressType) {
			StringBuilder fullAddress = new StringBuilder();
			try {
				// Address No
				if (StringUtils.isNotEmpty(getAddrnoValue(regMaster60, addressType))) {
					fullAddress.append(getAddrnoValue(regMaster60, addressType));
				}
				// Buildname
				if (StringUtils.isNotEmpty(getBuildnameValue(regMaster60, addressType))) {
					fullAddress.append(" อาคาร" + getBuildnameValue(regMaster60, addressType));
				}
				// Floorno
				if (StringUtils.isNotEmpty(getFloornoValue(regMaster60, addressType))) {
					fullAddress.append(" ชั้น " + getFloornoValue(regMaster60, addressType));
				}
				// Roomno
				if (StringUtils.isNotEmpty(getRoomnoValue(regMaster60, addressType))) {
					fullAddress.append(" ห้อง " + getRoomnoValue(regMaster60, addressType));
				}
				// Moono
				if (StringUtils.isNotEmpty(getMoonoValue(regMaster60, addressType))) {
					fullAddress.append(" หมู่ " + getMoonoValue(regMaster60, addressType));
				}
				// Village
				if (StringUtils.isNotEmpty(getVillageValue(regMaster60, addressType))) {
					fullAddress.append(" หมู่บ้าน" + getVillageValue(regMaster60, addressType));
				}
				// Soiname
				if (StringUtils.isNotEmpty(getSoinameValue(regMaster60, addressType))) {
					fullAddress.append(" ซ." + getSoinameValue(regMaster60, addressType));
				}
				// Thnname
				if (StringUtils.isNotEmpty(getThnnameValue(regMaster60, addressType))) {
					fullAddress.append(" ถ." + getThnnameValue(regMaster60, addressType));
				}
				// Tambolname
				if (StringUtils.isNotEmpty(getTambolnameValue(regMaster60, addressType))) {
					if (isBangkok(getProvincecodeValue(regMaster60, addressType))) {
						fullAddress.append(" แขวง");
					} else {
						fullAddress.append(" ต.");
					}
					fullAddress.append(getTambolnameValue(regMaster60, addressType));
				}
				// Amphurname
				if (StringUtils.isNotEmpty(getAmphurnameValue(regMaster60, addressType))) {
					if (isBangkok(getProvincecodeValue(regMaster60, addressType))) {
						fullAddress.append(" เขต");
					} else {
						fullAddress.append(" อ.");
					}
					fullAddress.append(getAmphurnameValue(regMaster60, addressType));
				}
				// Provincename
				if (StringUtils.isNotEmpty(getProvincenameValue(regMaster60, addressType))) {
					fullAddress.append(" จ." + getProvincenameValue(regMaster60, addressType));
				}
				// Zipcode
				if (StringUtils.isNotEmpty(getZipcodeValue(regMaster60, addressType))) {
					fullAddress.append(" " + getZipcodeValue(regMaster60, addressType));
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			
			return StringUtils.trimToEmpty(fullAddress.toString());
		}
		
		private static String getAddrnoValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Addrno");
		}
		
		private static String getBuildnameValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Buildname");
		}
		
		private static String getFloornoValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Floorno");
		}
		
		private static String getRoomnoValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Roomno");
		}
		
		private static String getMoonoValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Moono");
		}
		
		private static String getVillageValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Village");
		}
		
		private static String getSoinameValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Soiname");
		}
		
		private static String getThnnameValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Thnname");
		}
		
		private static String getTambolnameValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Tambolname");
		}
		
		private static String getAmphurnameValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Amphurname");
		}
		
		private static String getProvincecodeValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Provincecode");
		}
		
		private static String getProvincenameValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Provincename");
		}
		
		private static String getZipcodeValue(RegMaster60 regMaster60, String addressType) throws Exception {
			return getRegMaster60Value(regMaster60, "get" + addressType + "Zipcode");
		}
		
		private static String getRegMaster60Value(RegMaster60 regMaster60, String methodName) throws Exception {
			Method method = RegMaster60.class.getDeclaredMethod(methodName);
			return (String) method.invoke(regMaster60);
		}
		
		private static boolean isBangkok(String provinceCode) {
			return Pattern.matches("(10)0{4}", provinceCode);
		}
		
	}
	
	public static String convertIncfri8000DateType(String dateType) {
		if (WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME.equals(dateType)) {
			return WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME_CODE;
		} else if (WEB_SERVICE.INCFRI8000.DATE_TYPE_RECEIPT.equals(dateType)) {
			return WEB_SERVICE.INCFRI8000.DATE_TYPE_RECEIPT_CODE;
		} else {
			return null;
		}
	}
	
}

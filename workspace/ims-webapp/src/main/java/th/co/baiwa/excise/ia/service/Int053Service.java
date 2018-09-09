package th.co.baiwa.excise.ia.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.bean.BusinessException;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.MESSAGE_CODE;
import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants.MESSAGE_LANG;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.vo.Int053AssetWorkSheetVo;
import th.co.baiwa.excise.ia.persistence.vo.Int053Vo;

@Service
public class Int053Service {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Int053Vo upload(MultipartFile file) {
		Int053Vo vo = new Int053Vo();
		try {
			if (file.isEmpty()) {
				throw new BusinessException(MESSAGE_CODE.MSG00009, ApplicationCache.getMessage(MESSAGE_CODE.MSG00009, MESSAGE_LANG.TH));
			}	

			this.uploadAndValidateExcelFile(file, vo);
		} catch (BusinessException e) {
			vo.setErrorMessage(e.getErrorDesc());
			logger.error("Error Business Upload File : ", e);
		} catch (Exception e) {
			vo.setErrorMessage(ApplicationCache.getMessage(MESSAGE_CODE.MSG_SYSTEM, MESSAGE_LANG.TH));
			logger.error("Error Upload File", e.getMessage());
		}
		
		return vo;
	}
	
	private void uploadAndValidateExcelFile(MultipartFile file, Int053Vo vo) throws Exception {
		List<Int053AssetWorkSheetVo> uploadList = vo.getAssetWorkSheets();
		final String[] HEADER_FILE_COLUMNS = new String[] { "เลขที่สินทรัพย์", "เลขที่ย่อย", "วันที่โอนเป็นทุน", "คำอธิบายของสินทรัพย์", "มูลค่าการได้มา", "ค่าเสื่อมราคาสะสม", "มูลค่าตามบัญชี"};
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		DataFormatter dataFormatter = new DataFormatter();
		int line = 0;
		for (Sheet sheet : workbook) {
			row: for (Row row : sheet) {
				Int053AssetWorkSheetVo v = new Int053AssetWorkSheetVo();
				if (line == 0) {
					//validate header
					int columns = 0;
					for (String header : HEADER_FILE_COLUMNS) {
						if (!header.equals(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(columns))))) {
							throw new BusinessException(MESSAGE_CODE.MSG00010, ApplicationCache.getMessage(MESSAGE_CODE.MSG00010, MESSAGE_LANG.TH));
						}
						columns++;
					}
					line++;
					continue row;
				}
				// validate file
				v.setAssetNumber(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(0))));
				v.setAssetSubNumber(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(1))));
				
				String fundTransferDate = StringUtils.trim(dataFormatter.formatCellValue(row.getCell(2)));
				if (!validateDate(fundTransferDate)) {
					throw new BusinessException(MESSAGE_CODE.MSG00010, ApplicationCache.getMessage(MESSAGE_CODE.MSG00010, MESSAGE_LANG.TH));
				}
				
				v.setFundTransferDate(DateConstant.convertDateToStrDDMMYYYY(DateConstant.convertStrDDMMYYYYToDate(fundTransferDate)));
				
				v.setAssetDescription(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(3))));
				v.setAcquisitionValue(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(4))));
				v.setAccumulatedDepreciation(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(5))));
				v.setBookValue(StringUtils.trim(dataFormatter.formatCellValue(row.getCell(6))));
				
				uploadList.add(v);
			}
		}
	}
	
	private boolean validateDate(String dateStr) {
		Date date = null;
		date = DateConstant.convertStrDDMMYYYYToDate(dateStr);
		
		if (date == null) return false;
		
		return true;
	}

}

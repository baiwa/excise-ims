package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.util.ExcelUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;
import th.co.baiwa.excise.ia.persistence.entity.CwpScwdHdr;
import th.co.baiwa.excise.ia.persistence.entity.CwpTblDtl;
import th.co.baiwa.excise.ia.persistence.entity.CwpTblHdr;
import th.co.baiwa.excise.ia.persistence.entity.VerifyAccountHeader;
import th.co.baiwa.excise.ia.persistence.repository.CwpScwdDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.CwpScwdHdrRepository;
import th.co.baiwa.excise.ia.persistence.repository.CwpTblDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.CwpTblHdrRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int062ExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int062FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int071ExcelVo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int062Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CwpScwdHdrRepository cwpScwdHdrRepository;

	@Autowired
	private CwpScwdDtlRepository cwpScwdDtlRepository;
	
	@Autowired
	private CwpTblHdrRepository cwpTblHdrRepository;
	
	@Autowired
	private CwpTblDtlRepository cwpTblDtlRepository;

	public List<Int062ExcelVo> upload(Int062FormVo formVo)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		long headerId = 0L;
		headerId = readExcel1(formVo.getFileExcel1());
		//query *headerId*
		
		/*         */
		long detailId = 0L;
		detailId = readExcel2(formVo.getFileExcel2());
		

		return null;
	}

	//file excel 1
	@SuppressWarnings("unused")
	private long readExcel1(MultipartFile file1)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		byte[] byt = file1.getBytes();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getLastRowNum();
		Map<String, Integer> map = new HashMap<String, Integer>();
		// set object value header columns
		CwpScwdHdr objHeader = new CwpScwdHdr();
		List<CwpScwdDtl> datailList = new ArrayList<CwpScwdDtl>();
		long headerId = 0L;
		int countHeader = 0;

		/* rows */
		loopRow: for (int r = 0; r < totalRows; r++) {

			Row row = sheet.getRow(r);
			if (row != null) {
				short minColIx = row.getFirstCellNum();
				short maxColIx = row.getLastCellNum();

				boolean header = true;
				boolean detail = true;

				header = (minColIx == 0 && maxColIx == 3 ? false : true);
				detail = (minColIx == 0 && maxColIx == 13 ? false : true);

				// check header
				if (!header) {

					/* column */
					List<String> columns = new ArrayList<String>();
					for (short colIx = minColIx; colIx < maxColIx; colIx++) {

						Cell cell = row.getCell(colIx);
						if (cell != null) {
							map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
							columns.add(ExcelUtils.getCellValueAsString(cell));
						}
					}
					if (
							BeanUtils.isEmpty(objHeader.getDisbursementCode())
							|| BeanUtils.isEmpty(objHeader.getDisbursementName())
							|| BeanUtils.isEmpty(objHeader.getDepartment())
							|| !(BeanUtils.isNotEmpty(objHeader.getDateDocumentStart()))
							|| !(BeanUtils.isNotEmpty(objHeader.getDateDocumentEnd()))
							|| !(BeanUtils.isNotEmpty(objHeader.getDateReport()))
						) {
						headerId = addDataHeader(objHeader, columns, countHeader);
						countHeader++;
					}
				}

				// check detail
				if (!detail) {

					/* column */
					List<String> columns = new ArrayList<String>();
					for (short colIx = minColIx; colIx < maxColIx; colIx++) {
						Cell cell = row.getCell(colIx);
						if (cell != null) {
							map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
							columns.add(ExcelUtils.getCellValueAsString(cell));
						} else {
							columns.add("");
						}
					}
					//set data detail
					boolean checkDetail = addDataDetail(datailList, columns, headerId);
					// continue loop *if checkDetail = true*
					if (checkDetail) {
						continue loopRow;
					}

				}
			}
		}
		return headerId;

	}

	// add Data header
	private long addDataHeader(CwpScwdHdr objHeader, List<String> columns, int countHeader) {
		long headerId = 0L;
		if (BeanUtils.isEmpty(objHeader.getDisbursementCode()) && countHeader == 0) {
			objHeader.setDisbursementCode(columns.get(1));
		}
		if (BeanUtils.isEmpty(objHeader.getDisbursementName()) && countHeader == 1) {
			objHeader.setDisbursementName(columns.get(1));
		}
		if (BeanUtils.isEmpty(objHeader.getDepartment()) && countHeader == 2) {
			objHeader.setDepartment(columns.get(1));
		}
		if (!(BeanUtils.isNotEmpty(objHeader.getDateDocumentStart())) && !(BeanUtils.isNotEmpty(objHeader.getDateDocumentEnd()))
				&& countHeader == 3) {
			String[] dateSplit = (StringUtils.trim(columns.get(1))).split(" ");
			String startStr = dateSplit[0].replaceAll("\\.", "/");
			String endStr = dateSplit[2].replaceAll("\\.", "/");
			
			Date startDate = DateConstant.convertStrToDate(startStr, DateConstant.DD_MM_YYYY,DateConstant.LOCAL_EN);
			Date endDate = DateConstant.convertStrToDate(endStr, DateConstant.DD_MM_YYYY,DateConstant.LOCAL_EN);
			objHeader.setDateDocumentStart(startDate);
			objHeader.setDateDocumentEnd(endDate);
		}
		if (!(BeanUtils.isNotEmpty(objHeader.getDateReport())) && countHeader == 4) {
			Date dateReport = DateConstant.convertStrToDate(columns.get(1).replaceAll("\\.", "/"), DateConstant.DD_MM_YYYY,DateConstant.LOCAL_EN);
			objHeader.setDateReport(dateReport);
		}

		// check to save
		if (
				BeanUtils.isNotEmpty(objHeader.getDisbursementCode()) 
				&& BeanUtils.isNotEmpty(objHeader.getDisbursementName())
				&& BeanUtils.isNotEmpty(objHeader.getDepartment()) 
				&& BeanUtils.isNotEmpty(objHeader.getDateDocumentStart())
				&& BeanUtils.isNotEmpty(objHeader.getDateDocumentEnd()) 
				&& BeanUtils.isNotEmpty(objHeader.getDateReport())
			) {
			logger.info("SAVE HEADER!!!");
			CwpScwdHdr headerData = cwpScwdHdrRepository.save(objHeader);
			headerId = headerData.getCwpScwdHdrId();
		}
		return headerId;
	}
	
	public boolean addDataDetail(List<CwpScwdDtl> detailList, List<String> columns, long headerId) {
		CwpScwdDtl obj = new CwpScwdDtl();
		boolean checkDetail = false;
		if (columns.get(1) != "") {
			try {
				String recordStr = columns.get(0).replaceAll("\\.", "/");
				String postdStr = columns.get(1).replaceAll("\\.", "/");
				//check is not header table
				if(columns.get(0).equals(recordStr) && columns.get(1).equals(postdStr)) {
					checkDetail = true;
					return checkDetail;
				}
				Date recordDate = DateConstant.convertStrToDate(recordStr, DateConstant.DD_MM_YYYY,DateConstant.LOCAL_EN);
				
				Date postDate = DateConstant.convertStrToDate(postdStr, DateConstant.DD_MM_YYYY,DateConstant.LOCAL_EN);
				obj.setCwpScwdHdrId(headerId);
				obj.setRecordDate(recordDate);
				obj.setPostDate(postDate);
				obj.setTypeCode(columns.get(2));
				obj.setDucumentNumber(columns.get(3));
				obj.setSeller(columns.get(4));
				obj.setBankAccount(columns.get(5));
				obj.setReferenceNo(columns.get(6));
				obj.setBudgetCode(columns.get(7));
				obj.setWithdrawAmount(new BigDecimal(columns.get(8)));
				obj.setWithholdingTax(new BigDecimal(columns.get(9)));
				obj.setFines(new BigDecimal(columns.get(10)));
				obj.setFee(new BigDecimal(columns.get(11)));
				obj.setNetAmount(new BigDecimal(columns.get(12)));

				detailList.add(obj);
			} catch (Exception e) {
				checkDetail = true;
			}
		} else {
			cwpScwdDtlRepository.save(detailList);
		}
		return checkDetail;
	}
	
	//file excel 2
	private long readExcel2(MultipartFile file2) throws IOException, EncryptedDocumentException, InvalidFormatException {
		long detailId = 0L;
		
		byte[] byt = file2.getBytes();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getLastRowNum();
		Map<String, Integer> map = new HashMap<String, Integer>();
		// set object value header columns
		CwpTblHdr objHeader = new CwpTblHdr();
		List<CwpTblDtl> detailList = new ArrayList<CwpTblDtl>();
		int countHeader = 0;
		long headerId = 0L;

		/* rows */

		for (int r = 0; r < totalRows; r++) {

			Row row = sheet.getRow(r);
			if (row != null) {
				short minColIx = row.getFirstCellNum();
				short maxColIx = row.getLastCellNum();

				boolean header = true;
				boolean detail = true;

				header = (minColIx == 0 && maxColIx == 12 ? false : true);
				detail = (minColIx == 1 && maxColIx == 11 ? false : true);

				// check header
				if (!header) {

					/* column */
					List<String> columns = new ArrayList<String>();
					for (short colIx = minColIx; colIx < maxColIx; colIx++) {

						Cell cell = row.getCell(colIx);
						if (cell != null) {
							map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
							columns.add(ExcelUtils.getCellValueAsString(cell));
						}
					}
					if (BeanUtils.isEmpty(objHeader.getDisbursementCode())
							|| BeanUtils.isEmpty(objHeader.getDisbursementName())
							|| BeanUtils.isEmpty(objHeader.getDepartment())
							|| !BeanUtils.isNotEmpty(objHeader.getReportDate())
							|| BeanUtils.isEmpty(objHeader.getTrialBalanceType())) {
						headerId = addDataHeader2(objHeader, columns, countHeader);
						countHeader++;
					}
				}

				// check detail
				if (!detail) {

					/* column */
					List<String> columns = new ArrayList<String>();
					for (short colIx = minColIx; colIx < maxColIx; colIx++) {

						Cell cell = row.getCell(colIx);
						if (cell != null) {
							map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
							columns.add(ExcelUtils.getCellValueAsString(cell));
						} else {
							columns.add("");
						}
					}
					if (columns.get(2) != "") {
						addDataDetail2(detailList, columns, headerId);
					}

				}
			}
		}
		
		return detailId;
	}
	
	public long addDataHeader2(CwpTblHdr objHeader, List<String> data, int countHeader) {
		String hd1 = data.get(2);
		String[] lineOne = hd1.split(" ");
		String disbursementCode = lineOne[1];
		String department = lineOne[2];
		String date = data.get(4);

		if (BeanUtils.isEmpty(objHeader.getDisbursementCode())) {
			objHeader.setDisbursementCode(disbursementCode);
		}

		if (BeanUtils.isEmpty(objHeader.getDepartment())) {
			objHeader.setDepartment(department);
		}

		if (!BeanUtils.isNotEmpty(objHeader.getReportDate())) {
//			objHeader.setReportDate(date);
		}

		if (countHeader == 1) {
			String hd2 = data.get(2);
			String[] lineTwo = hd2.split(" ");
			String disbursementName = lineTwo[1] + " " + lineTwo[2];
			String time = data.get(4);
			String dateTimeStr = date + " " + time;
			Date dateTime = DateConstant.convertStrToDate(dateTimeStr, DateConstant.DD_MM_YYYY_HH_mm, DateConstant.LOCAL_EN);
			objHeader.setDisbursementName(disbursementName);
			objHeader.setReportDate(dateTime);
//			objHeader.setReportTime(time);
		}

		long headerId = 0L;
//		if (BeanUtils.isNotEmpty(header.getDisbursementCode()) && BeanUtils.isNotEmpty(header.getDisbursementName())
//				&& BeanUtils.isNotEmpty(header.getDepartment()) && BeanUtils.isNotEmpty(header.getReportDate())
//				&& BeanUtils.isNotEmpty(header.getReportTime())) {
//			logger.info("SAVE HEADER!!!!!!!");
////			VerifyAccountHeader headerData = verifyAccountHdRepository.save(header);
//
//			// set headerId for Detail
//			headerId = headerData.getVerifyAccountHeaderId();
//		}

		return headerId;
	}
	
	public void addDataDetail2(List<CwpTblDtl> detailList, List<String> data, long headerId) {
//		CwpTblDtl vo = new CwpTblDtl();
//		try {
//			if(headerId > 0) {
//				vo.setVerifyAccountHeaderId(headerId);
//			}
//			vo.setColum0(StringUtils.trim(data.get(0)));
//			vo.setColum1(StringUtils.trim(data.get(1)));
//			vo.setColum2(StringUtils.trim(data.get(2)));
//			vo.setColum3(StringUtils.trim(data.get(3)));
//			vo.setColum4(StringUtils.trim(data.get(4)));
//			vo.setColum5(StringUtils.trim(data.get(5)));
//			vo.setColum6(StringUtils.trim(data.get(6)));
//			vo.setColum7(StringUtils.trim(data.get(7)));
//			vo.setColum8(StringUtils.trim(data.get(8)));
//			vo.setColum9(StringUtils.trim(data.get(9)));
//			vo.setColum10(StringUtils.trim(data.get(10)));
//			vo.setColum11(StringUtils.trim(data.get(11)));
//
//			detailList.add(vo);
//		} catch (Exception e) {
//			detailList.add(vo);
//		}
	}

}

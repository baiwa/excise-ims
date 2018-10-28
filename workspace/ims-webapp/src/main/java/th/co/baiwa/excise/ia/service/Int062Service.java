package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
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
import th.co.baiwa.excise.ia.persistence.dao.CwpScwdDtlDao;
import th.co.baiwa.excise.ia.persistence.dao.CwpTblDtlDao;
import th.co.baiwa.excise.ia.persistence.dao.WithdrawalPersonsDao;
import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;
import th.co.baiwa.excise.ia.persistence.entity.CwpScwdHdr;
import th.co.baiwa.excise.ia.persistence.entity.CwpTblDtl;
import th.co.baiwa.excise.ia.persistence.entity.CwpTblHdr;
import th.co.baiwa.excise.ia.persistence.repository.CwpScwdDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.CwpScwdHdrRepository;
import th.co.baiwa.excise.ia.persistence.repository.CwpTblDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.CwpTblHdrRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int062AddFieldVo;
import th.co.baiwa.excise.ia.persistence.vo.Int062CwpDtlVo;
import th.co.baiwa.excise.ia.persistence.vo.Int062FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int062Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int062paymentInfoVo;
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

	@Autowired
	private CwpScwdDtlDao cwpScwdDtlDao;
	
	@Autowired
	private CwpTblDtlDao cwpTblDtlDao;
	
	@Autowired
	private WithdrawalPersonsDao withdrawalPersonsDao;

	public List<Int062Vo> upload(Int062FormVo formVo)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		long idFile1 = 0L;
		long idFile2 = 0L;
		long fileUploadID = System.currentTimeMillis();
		List<Int062Vo> responseList = new ArrayList<Int062Vo>();
		Int062Vo response = new Int062Vo();
		/* ----------------------------------------------------- */
		try {
			idFile1 = readExcel1(formVo.getFileExcel1(), fileUploadID);
			idFile2 = readExcel2(formVo.getFileExcel2(), fileUploadID);
			// order by 'budget code'
			if (formVo.getSortSystem() == 1322) {
				List<Int062CwpDtlVo> findDivideMonth;
				List<Int062CwpDtlVo> findByHDRId = cwpScwdDtlDao.findByHDRId(idFile1, formVo.getSortSystem());
				for (int i = 0; i < findByHDRId.size(); i++) {
					findDivideMonth = new ArrayList<Int062CwpDtlVo>();
					findDivideMonth = cwpScwdDtlDao.findDivideMonth(findByHDRId.get(i), formVo.getSortSystem());

					for (int j = 0; j < findDivideMonth.size(); j++) {
						response = new Int062Vo();
						Int062AddFieldVo cwp = new Int062AddFieldVo();
						response.setCwpScwdDtlList(
								cwpScwdDtlDao.findGroupMonth(findDivideMonth.get(j), formVo.getSortSystem()));
						cwp = new Int062AddFieldVo();

						for (CwpScwdDtl cwpScwdDtl : response.getCwpScwdDtlList()) {
							cwp.setFee(cwp.getFee().add(cwpScwdDtl.getFee()));
							cwp.setFines(cwp.getFines().add(cwpScwdDtl.getFines()));
							cwp.setNetAmount(cwp.getNetAmount().add(cwpScwdDtl.getNetAmount()));
							cwp.setWithdrawAmount(cwp.getWithdrawAmount().add(cwpScwdDtl.getWithdrawAmount()));
							cwp.setWithholdingTax(cwp.getWithholdingTax().add(cwpScwdDtl.getWithholdingTax()));

							// set idExcel1
							cwp.setCwpScwdHdrId(idFile1);
							// set idExcel2
							cwp.setIdExcel2(idFile2);
						}
						// //add data to response
						response.setCwpScwdDtl(cwp);
						response.setFileUploadID(fileUploadID);
						responseList.add(response);
					}
				}
			} else {
				// order by 'record date'
				List<Int062AddFieldVo> addFieldList;
				List<Int062CwpDtlVo> findByHDRId = cwpScwdDtlDao.findByHDRId(idFile1, formVo.getSortSystem());
				for (int i = 0; i < findByHDRId.size(); i++) {
					response = new Int062Vo();
					addFieldList = new ArrayList<Int062AddFieldVo>();
					Int062AddFieldVo totalMonthCal = new Int062AddFieldVo();
					
					response.setCwpScwdDtlList(cwpScwdDtlDao.findGroupMonth(findByHDRId.get(i), formVo.getSortSystem()));
					//join table *IA_WITHDRAWAL_LIST*
					addFieldList = cwpScwdDtlDao.addField();
					totalMonthCal = new Int062AddFieldVo();

					for (Int062AddFieldVo cwpScwdDtl : response.getCwpScwdDtlList()) {
						if(BeanUtils.isNotEmpty(addFieldList)) {
							for (Int062AddFieldVo obj : addFieldList) {
								if(cwpScwdDtl.getDucumentNumber().equals(obj.getWithdrawalDocNum())) {
									//set value from table *IA_WITHDRAWAL_LIST*
									cwpScwdDtl.setListName(obj.getListName());
									cwpScwdDtl.setWithdrawalAmount(obj.getWithdrawalAmount());
									cwpScwdDtl.setWithdrawalDocNum(obj.getWithdrawalDocNum());
									cwpScwdDtl.setReceivedAmount(obj.getReceivedAmount());
									cwpScwdDtl.setRefNum(obj.getRefNum());								
																		
									//set PaymentInfoVoList from table *IA_WITHDRAWAL_PERSONS*
									cwpScwdDtl.setPaymentInfoVoList(withdrawalPersonsDao.paymentInfo(obj.getWithdrawalId()));
								}
							}
						}
						//sum difference
						cwpScwdDtl.setDiffReceived(cwpScwdDtl.getNetAmount().subtract(cwpScwdDtl.getReceivedAmount()));
						cwpScwdDtl.setDiffWithdraw(cwpScwdDtl.getWithdrawAmount().subtract(cwpScwdDtl.getWithdrawalAmount()));
						
						//sum total month
						totalMonthCal.setWithdrawalAmount(totalMonthCal.getWithdrawalAmount().add(cwpScwdDtl.getWithdrawalAmount()));
						totalMonthCal.setReceivedAmount(totalMonthCal.getReceivedAmount().add(cwpScwdDtl.getReceivedAmount()));
						totalMonthCal.setTotalDiffReceived(totalMonthCal.getTotalDiffReceived().add(cwpScwdDtl.getDiffReceived()));
						totalMonthCal.setTotalDiffWithdraw(totalMonthCal.getTotalDiffWithdraw().add(cwpScwdDtl.getDiffWithdraw()));
						
						//sum total month
						totalMonthCal.setFee(totalMonthCal.getFee().add(cwpScwdDtl.getFee()));
						totalMonthCal.setFines(totalMonthCal.getFines().add(cwpScwdDtl.getFines()));
						totalMonthCal.setNetAmount(totalMonthCal.getNetAmount().add(cwpScwdDtl.getNetAmount()));
						totalMonthCal.setWithdrawAmount(totalMonthCal.getWithdrawAmount().add(cwpScwdDtl.getWithdrawAmount()));
						totalMonthCal.setWithholdingTax(totalMonthCal.getWithholdingTax().add(cwpScwdDtl.getWithholdingTax()));

						// set idExcel1
						totalMonthCal.setCwpScwdHdrId(idFile1);
						// set idExcel2
						totalMonthCal.setIdExcel2(idFile2);
					}
					// //add data to response
					response.setCwpScwdDtl(totalMonthCal);
					response.setFileUploadID(fileUploadID);
					responseList.add(response);
					// }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseList;
	}

	// read file excel 1
	@SuppressWarnings("unused")
	private long readExcel1(MultipartFile file1, long fileUploadID)
			throws IOException, EncryptedDocumentException, InvalidFormatException, SQLException {
		byte[] byt = file1.getBytes();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getLastRowNum();
		Map<String, Integer> map = new HashMap<String, Integer>();
		// set object header value columns
		CwpScwdHdr objHeader = new CwpScwdHdr();
		List<CwpScwdDtl> datailList = new ArrayList<CwpScwdDtl>();
		long headerId = 0L;
		int countHeader = 0;

		/* rows */
		loopRow: for (int r = 0; r <= totalRows; r++) {

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
					if (BeanUtils.isEmpty(objHeader.getDisbursementCode())
							|| BeanUtils.isEmpty(objHeader.getDisbursementName())
							|| BeanUtils.isEmpty(objHeader.getDepartment())
							|| !(BeanUtils.isNotEmpty(objHeader.getDateDocumentStart()))
							|| !(BeanUtils.isNotEmpty(objHeader.getDateDocumentEnd()))
							|| !(BeanUtils.isNotEmpty(objHeader.getDateReport()))) {
						headerId = addDataHeader(objHeader, columns, countHeader, fileUploadID);
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
					// set data detail
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
	private long addDataHeader(CwpScwdHdr objHeader, List<String> columns, int countHeader, long fileUploadID) {
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
		if (!(BeanUtils.isNotEmpty(objHeader.getDateDocumentStart()))
				&& !(BeanUtils.isNotEmpty(objHeader.getDateDocumentEnd())) && countHeader == 3) {
			String[] dateSplit = (StringUtils.trim(columns.get(1))).split(" ");
			String startStr = dateSplit[0].replaceAll("\\.", "/");
			String endStr = dateSplit[2].replaceAll("\\.", "/");

			Date startDate = DateConstant.convertStrToDate(startStr, DateConstant.DD_MM_YYYY, DateConstant.LOCAL_EN);
			Date endDate = DateConstant.convertStrToDate(endStr, DateConstant.DD_MM_YYYY, DateConstant.LOCAL_EN);
			objHeader.setDateDocumentStart(startDate);
			objHeader.setDateDocumentEnd(endDate);
		}
		if (!(BeanUtils.isNotEmpty(objHeader.getDateReport())) && countHeader == 4) {
			Date dateReport = DateConstant.convertStrToDate(columns.get(1).replaceAll("\\.", "/"),
					DateConstant.DD_MM_YYYY, DateConstant.LOCAL_EN);
			objHeader.setDateReport(dateReport);
		}

		// check to save
		if (BeanUtils.isNotEmpty(objHeader.getDisbursementCode())
				&& BeanUtils.isNotEmpty(objHeader.getDisbursementName())
				&& BeanUtils.isNotEmpty(objHeader.getDepartment())
				&& BeanUtils.isNotEmpty(objHeader.getDateDocumentStart())
				&& BeanUtils.isNotEmpty(objHeader.getDateDocumentEnd())
				&& BeanUtils.isNotEmpty(objHeader.getDateReport())) {
			objHeader.setFileUploadID(fileUploadID);
			logger.info("SAVE HEADER!!!");
			CwpScwdHdr headerData = cwpScwdHdrRepository.save(objHeader);
			headerId = headerData.getCwpScwdHdrId();
		}
		return headerId;
	}

	public boolean addDataDetail(List<CwpScwdDtl> detailList, List<String> columns, long headerId) throws SQLException {
		CwpScwdDtl obj = new CwpScwdDtl();
		boolean checkDetail = false;
		if (columns.get(1) != "") {
			try {
				String recordStr = columns.get(0).replaceAll("\\.", "/");
				String postdStr = columns.get(1).replaceAll("\\.", "/");
				// check is not header table
				if (columns.get(0).equals(recordStr) && columns.get(1).equals(postdStr)) {
					checkDetail = true;
					return checkDetail;
				}
				Date recordDate = DateConstant.convertStrToDate(recordStr, DateConstant.DD_MM_YYYY,
						DateConstant.LOCAL_EN);

				Date postDate = DateConstant.convertStrToDate(postdStr, DateConstant.DD_MM_YYYY, DateConstant.LOCAL_EN);
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
			//save detail
			int executeSize = 1000;
			cwpScwdDtlDao.cwpScwdDtlInsert(detailList, executeSize);
		}
		return checkDetail;
	}

	// file excel 2
	private long readExcel2(MultipartFile file2, long fileUploadID)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		// set object value header columns
		CwpTblHdr objHeader = new CwpTblHdr();
		List<CwpTblDtl> detailList = new ArrayList<CwpTblDtl>();
		int countHeader = 0;
		long headerId = 0L;
		ArrayList<String> dateStr = new ArrayList<String>(); // for save date-time

		/* read file excel */
		try {
			byte[] byt = file2.getBytes();
			Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
			Sheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getLastRowNum();
			Map<String, Integer> map = new HashMap<String, Integer>();

			/* rows */

			for (int r = 0; r <= totalRows; r++) {

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
								|| !BeanUtils.isNotEmpty(objHeader.getReportDate())) {
							headerId = addDataHeader2(objHeader, columns, countHeader, dateStr, fileUploadID);
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
			// save detail
			int executeSize = 1000;
			cwpTblDtlDao.cwpTblDtlInsert(detailList, executeSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return headerId;
	}

	public long addDataHeader2(CwpTblHdr objHeader, List<String> data, int countHeader, List<String> dateStr,
			long fileUploadID) {
		String hd1 = data.get(2);
		String[] lineOne = hd1.split(" ");
		String disbursementCode = lineOne[1];
		String department = lineOne[2];

		if (countHeader == 0) {

			dateStr.add(data.get(4).replaceAll("\\.", "/"));
			if (BeanUtils.isEmpty(objHeader.getDisbursementCode())) {
				objHeader.setDisbursementCode(disbursementCode);
			}

			if (BeanUtils.isEmpty(objHeader.getDepartment())) {
				objHeader.setDepartment(department);
			}
		}

		if (countHeader == 1) {
			String hd2 = data.get(2);
			String[] lineTwo = hd2.split(" ");
			String disbursementName = lineTwo[1] + " " + lineTwo[2];
			String time = data.get(4);
			String dateTimeStr = dateStr.get(0) + " " + time;
			Date dateTime = DateConstant.convertStrToDate(dateTimeStr, DateConstant.DD_MM_YYYY_HH_MM_SS,
					DateConstant.LOCAL_EN);
			objHeader.setDisbursementName(disbursementName);
			objHeader.setReportDate(dateTime);
		}

		long headerId = 0L;
		if (BeanUtils.isNotEmpty(objHeader.getDisbursementCode())
				&& BeanUtils.isNotEmpty(objHeader.getDisbursementName())
				&& BeanUtils.isNotEmpty(objHeader.getDepartment()) && BeanUtils.isNotEmpty(objHeader.getReportDate())) {
			logger.info("SAVE HEADER!!!!!!!");
			objHeader.setFileUploadID(fileUploadID);
			CwpTblHdr headerData = cwpTblHdrRepository.save(objHeader);

			// set headerId for Detail
			headerId = headerData.getCwpTblHdrId();
		}

		return headerId;
	}

	public void addDataDetail2(List<CwpTblDtl> detailList, List<String> data, long headerId) {
		CwpTblDtl en = new CwpTblDtl();
		try {
			en.setCwpTblHdrId(headerId);
			en.setLedgerAccountNumber(StringUtils.trim(data.get(0)));
			en.setLedgerAccountName(StringUtils.trim(data.get(2)));
			en.setBringForward(new BigDecimal(StringUtils.trim(data.get(4))));
			en.setDebit(new BigDecimal(StringUtils.trim(data.get(7))));
			en.setCredit(new BigDecimal(StringUtils.trim(data.get(8))));
			en.setCarryForward(new BigDecimal(StringUtils.trim(data.get(9))));

			detailList.add(en);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

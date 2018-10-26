package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ExcelUtils;
import th.co.baiwa.excise.ia.persistence.vo.Int076FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int076Vo;
import th.co.baiwa.excise.ta.service.ExciseDetailService;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int076Service {
	private String tmpDatePosted = "";
	private Logger logger = LoggerFactory.getLogger(ExciseDetailService.class);
	DecimalFormat formatter = new DecimalFormat("#,###.00");

	public List<Int076Vo> readFileExcel(Int076FormVo formVo)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		List<Int076Vo> excelList = new ArrayList<>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		logger.info("readFileExcel");
		System.out.println(formVo.getFileExel().getOriginalFilename());
		System.out.println(formVo.getFileExel().getContentType());
		byte[] byt = formVo.getFileExel().getBytes();
		Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getLastRowNum();

		boolean header = false;
		boolean detail = false;

		/* loop header row */
		for (int r = 0; r <= totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				/* check header */
				short startCellHeader = row.getFirstCellNum();
				Cell cellHeader = row.getCell(startCellHeader);
				header = ("บัญชีเงินฝาก : 10778 ภาษีบำรุงองค์กรปกครองส่วนท้องถิ่นเพื่อรอจัดสรร"
						.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellHeader))) ? true : false);
			}
			/* when header is true give start check detail */
			detail = (header ? true : false);

			if (detail) {
				r++; // start next one row

				/* loop detail row */
				for (int rowCheckDetail = r; rowCheckDetail <= totalRows; rowCheckDetail++) {
					Row rowDetail = sheet.getRow(rowCheckDetail);
					boolean checkEndDetail = false;
					boolean checkEndDoc = false;
					List<String> culumns = new ArrayList<>();
					if (rowDetail != null) {
						/* loop detail column */
						for (short col = 1; col < 19; col++) {
							/* cell is read data */
							Cell cell = rowDetail.getCell(col);

							/* cellCheck is read cell !null */
							short startCellDetail = rowDetail.getFirstCellNum();
							Cell cellCheck = rowDetail.getCell(startCellDetail);

							checkEndDetail = ("รายงานแสดงการเคลื่อนไหวเงินฝากกระทรวงการคลัง".equals(
									StringUtils.trim(ExcelUtils.getCellValueAsString(cellCheck))) ? true : false);
							checkEndDoc = ("***** รวมบัญชีเงินฝาก : 10778 ภาษีบำรุงองค์กรปกครองส่วนท้องถิ่นเพื่อรอ"
									.equals(StringUtils.trim(ExcelUtils.getCellValueAsString(cellCheck))) ? true
											: false);

							if (checkEndDetail) {
								rowCheckDetail += 10;
								break;
							} else if (checkEndDoc) {
								break;
							}

							String value = ExcelUtils.getCellValueAsString(cell);
							// System.out.println(value + " col::" + col + " row::" + rowCheckDetail);

							if (value != null) {
								map.put(ExcelUtils.getCellValueAsString(cell), cell.getColumnIndex());
								culumns.add(ExcelUtils.getCellValueAsString(cell));
							} else {
								culumns.add("");
							}

						}
						addData(excelList, culumns, rowCheckDetail);

					}
					if (checkEndDoc) {
						break;
					}
				}
			}

		}

		return excelList;

	}

	public void addData(List<Int076Vo> excelList, List<String> data, int rowId) {

		boolean checkData;
		checkData = (data.isEmpty() ? false : true);

		if (checkData) {
			Int076Vo vo = new Int076Vo();

			try {

				vo.setId(new Long(rowId));

				if (StringUtils.isNotBlank(data.get(0))) {
					vo.setDatePosted(StringUtils.trim(data.get(0)));

					if (vo.getDatePosted().indexOf(".01.") != -1) {
						vo.setColor("1");
					} else if (vo.getDatePosted().indexOf(".02.") != -1) {
						vo.setColor("2");
					} else if (vo.getDatePosted().indexOf(".03.") != -1) {
						vo.setColor("3");
					} else if (vo.getDatePosted().indexOf(".04.") != -1) {
						vo.setColor("4");
					} else if (vo.getDatePosted().indexOf(".05.") != -1) {
						vo.setColor("5");
					} else if (vo.getDatePosted().indexOf(".06.") != -1) {
						vo.setColor("6");
					} else if (vo.getDatePosted().indexOf(".07.") != -1) {
						vo.setColor("7");
					} else if (vo.getDatePosted().indexOf(".08.") != -1) {
						vo.setColor("8");
					} else if (vo.getDatePosted().indexOf(".09.") != -1) {
						vo.setColor("9");
					} else if (vo.getDatePosted().indexOf(".10.") != -1) {
						vo.setColor("10");
					} else if (vo.getDatePosted().indexOf(".11.") != -1) {
						vo.setColor("11");
					} else if (vo.getDatePosted().indexOf(".12.") != -1) {
						vo.setColor("12");
					}

				} else {
					vo.setDatePosted(tmpDatePosted);

					if (vo.getDatePosted().indexOf(".01.") != -1) {
						vo.setColor("1");
					} else if (vo.getDatePosted().indexOf(".02.") != -1) {
						vo.setColor("2");
					} else if (vo.getDatePosted().indexOf(".03.") != -1) {
						vo.setColor("3");
					} else if (vo.getDatePosted().indexOf(".04.") != -1) {
						vo.setColor("4");
					} else if (vo.getDatePosted().indexOf(".05.") != -1) {
						vo.setColor("5");
					} else if (vo.getDatePosted().indexOf(".06.") != -1) {
						vo.setColor("6");
					} else if (vo.getDatePosted().indexOf(".07.") != -1) {
						vo.setColor("7");
					} else if (vo.getDatePosted().indexOf(".08.") != -1) {
						vo.setColor("8");
					} else if (vo.getDatePosted().indexOf(".09.") != -1) {
						vo.setColor("9");
					} else if (vo.getDatePosted().indexOf(".10.") != -1) {
						vo.setColor("10");
					} else if (vo.getDatePosted().indexOf(".11.") != -1) {
						vo.setColor("11");
					} else if (vo.getDatePosted().indexOf(".12.") != -1) {
						vo.setColor("12");
					}
				}
				// set temp date
				if (StringUtils.isNotBlank(data.get(0))) {
					tmpDatePosted = data.get(0);
				}
				vo.setDocNumber(StringUtils.trim(data.get(3)));
				vo.setDocType(StringUtils.trim(data.get(5)));
				vo.setDocRefer(StringUtils.trim(data.get(6)));
				vo.setActor(StringUtils.trim(data.get(7)));
				vo.setDetermination(StringUtils.trim(data.get(10)));
				vo.setPayUnit(StringUtils.trim(data.get(13)));

				if ("0".equals(data.get(14))) {
					vo.setDebit(StringUtils.trim(data.get(14)));
				} else {
					vo.setDebit(formatter.format(new BigDecimal(data.get(14))));
				}
				if ("0".equals(data.get(15))) {
					vo.setCredit(StringUtils.trim(data.get(15)));
				} else {
					vo.setCredit(formatter.format(new BigDecimal(data.get(15))));
				}
				if ("0".equals(data.get(17))) {
					vo.setLiftUp(StringUtils.trim(data.get(17)));
				} else {
					vo.setLiftUp(formatter.format(new BigDecimal(data.get(17))));
				}

				excelList.add(vo);
			} catch (Exception e) {
				excelList.add(vo);

			}
		}

	}

	public List<Int076Vo> checkData(List<Int076Vo> dataList) {
		

		int monJ0;
		int yearJ0;
		String dateJ0 = "";

		ArrayList<String> liftUpList = new ArrayList<String>();

		for (Int076Vo dataJ0 : dataList) {

			/* check J0 */
			if ("J0".equals(dataJ0.getDocType())) {
				/* check dateJ0 */
				if ("01".equals(dataJ0.getDatePosted().substring(3, 5))) {
					monJ0 = 12;
					yearJ0 = Integer.parseInt(dataJ0.getDatePosted().substring(6, 10));
					yearJ0--;
					dateJ0 = monJ0 + "." + yearJ0;
				} else {
					monJ0 = Integer.parseInt(dataJ0.getDatePosted().substring(3, 5));
					monJ0--;
					yearJ0 = Integer.parseInt(dataJ0.getDatePosted().substring(6, 10));
					yearJ0--;
					if (monJ0 == 10 || monJ0 == 11) {
						dateJ0 = monJ0 + "." + yearJ0;
					} else {
						dateJ0 = "0" + monJ0 + "." + yearJ0;
					}

				}
				/* check IX */
				for (Int076Vo dataIX : dataList) {
					/* check dateIX */
					if ("IX".equals(dataIX.getDocType()) && dateJ0.equals(dataIX.getDatePosted().substring(3, 10))&&StringUtils.isNotBlank(dataIX.getLiftUp())) {

						liftUpList.add(dataIX.getLiftUp());

					}

				}
				/* check liftUp IX == Credit */
				if (BeanUtils.isNotEmpty(liftUpList) && liftUpList.get(liftUpList.size() - 1).equals(dataJ0.getCredit())) {
					dataJ0.setCheckData("Y");
				} else {
					dataJ0.setCheckData("N");
				}

				liftUpList = new ArrayList<String>();

			}

		}

		return dataList;
	}

}

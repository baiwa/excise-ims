package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr04D;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr04H;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr04DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr04HRepository;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperRelationProducedGoodsVo;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;
import th.go.excise.ims.ws.vo.WsOasfri0100FromVo;
import th.go.excise.ims.ws.vo.WsOasfri0100Vo;

@Service
public class ProductPaperRelationProducedGoodsService extends AbstractProductPaperService<ProductPaperRelationProducedGoodsVo> {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperRelationProducedGoodsService.class);

	private static final String PRODUCT_PAPER_RELATION_PRODUCED_GOODS = "ความสัมพันธ์การเบิกใช้วัตถุดิบ";

	@Autowired
	private TaPaperPr04HRepository taPaperPr04HRepository;
	@Autowired
	private TaPaperPr04DRepository taPaperPr04DRepository;

	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected List<ProductPaperRelationProducedGoodsVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");

		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());

		WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
		wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
		wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
		wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
		wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));

		List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
		List<ProductPaperRelationProducedGoodsVo> voList = new ArrayList<>();
		ProductPaperRelationProducedGoodsVo vo = null;
		for (WsOasfri0100Vo wsOasfri0100Vo : wsOasfri0100VoList) {
			vo = new ProductPaperRelationProducedGoodsVo();
			vo.setDocNo(NO_VALUE);
			vo.setMaterialDesc(NO_VALUE);
			vo.setInputMaterialQty(NO_VALUE);
			vo.setFormulaMaterialQty(NO_VALUE);
			vo.setUsedMaterialQty(NO_VALUE);
			vo.setRealUsedMaterialQty(NO_VALUE);
			vo.setDiffMaterialQty(NO_VALUE);
			vo.setMaterialQty(NO_VALUE);
			vo.setGoodsQty(NO_VALUE);
			vo.setDiffGoodsQty(NO_VALUE);
			vo.setWasteGoodsPnt(NO_VALUE);
			vo.setWasteGoodsQty(NO_VALUE);
			vo.setBalanceGoodsQty(NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<ProductPaperRelationProducedGoodsVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		logger.info("inquiryByPaperPrNumber paperPrNumber={}", formVo.getPaperPrNumber());

		List<TaPaperPr04D> entityList = taPaperPr04DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperRelationProducedGoodsVo> voList = new ArrayList<>();
		ProductPaperRelationProducedGoodsVo vo = null;
		for (TaPaperPr04D entity : entityList) {
			vo = new ProductPaperRelationProducedGoodsVo();
			vo.setDocNo(entity.getDocNo());
			vo.setMaterialDesc(entity.getMaterialDesc());
			vo.setInputMaterialQty(entity.getInputMaterialQty() != null ? entity.getInputMaterialQty().toString() : NO_VALUE);
			vo.setFormulaMaterialQty(entity.getFormulaMaterialQty() != null ? entity.getFormulaMaterialQty().toString() : NO_VALUE);
			vo.setUsedMaterialQty(entity.getUsedMaterialQty() != null ? entity.getUsedMaterialQty().toString() : NO_VALUE);
			vo.setRealUsedMaterialQty(entity.getRealUsedMaterialQty() != null ? entity.getRealUsedMaterialQty().toString() : NO_VALUE);
			vo.setDiffMaterialQty(entity.getDiffMaterialQty() != null ? entity.getDiffMaterialQty().toString() : NO_VALUE);
			vo.setMaterialQty(entity.getMaterialQty() != null ? entity.getMaterialQty().toString() : NO_VALUE);
			vo.setGoodsQty(entity.getGoodsQty() != null ? entity.getGoodsQty().toString() : NO_VALUE);
			vo.setDiffGoodsQty(entity.getDiffGoodsQty() != null ? entity.getDiffGoodsQty().toString() : NO_VALUE);
			vo.setWasteGoodsPnt(entity.getWasteGoodsPnt() != null ? entity.getWasteGoodsPnt().toString() : NO_VALUE);
			vo.setWasteGoodsQty(entity.getWasteGoodsQty() != null ? entity.getWasteGoodsQty().toString() : NO_VALUE);
			vo.setBalanceGoodsQty(entity.getBalanceGoodsQty() != null ? entity.getBalanceGoodsQty().toString() : NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected byte[] exportData(ProductPaperFormVo formVo, List<ProductPaperRelationProducedGoodsVo> voList, String exportType) {

		// set format money
		DecimalFormat df = new DecimalFormat("#,##0.00");

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_RELATION_PRODUCED_GOODS);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "เลขที่ใบสำคัญ", "รายการ", "จำนวนรับ" + "\n" + "(ตามบัญชี ภส. ๐๗-๐๒)", "สูตรจากการผลิต", "เบิกตามสูตร", "เบิกใช้จริง", "ผลต่างวัตถุดิบ", "ผลิตได้ตามสูตร", "", "ผลต่างสินค้าสำเร็จรูป", "หักสูญเสีย", "", "คงเหลือ" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i == 0) {
				cell.setCellStyle(thStyle);
			} else if (i >= 1 && i <= 4 || i == 6) {
				cell.setCellStyle(bgKeyIn);
			} else if (i == 5 || i == 7) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}
		}

		/* tbTH2 */
		String[] tbTH2 = { "", "", "", "", "", "", "", "", "แยกตามวัตถุดิบ", "จำนวนสินค้าสำเร็จรูป", "", "เปอร์เซ็นต์", "จำนวน" };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			if (i > 7 && i != 10) {
				cell = row.createCell(i);
				cell.setCellValue(tbTH2[i]);
				cell.setCellStyle(thStyle);
			}
		}

		/* width */
		for (int i = 0; i < 14; i++) {
			if (i > 2) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 60);
			} else if (i == 2) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 9));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 11, 12));

		for (int i = 0; i < 14; i++) {
			if (i != 8 && i != 9 && i != 11 && i != 12) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;

		for (ProductPaperRelationProducedGoodsVo data : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getDocNo
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getDocNo())) {
					cell.setCellValue(data.getDocNo());
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getMaterialDesc
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getMaterialDesc())) {
					cell.setCellValue(data.getMaterialDesc());
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getInputMaterialQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getInputMaterialQty()) && !NO_VALUE.equals(data.getInputMaterialQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getInputMaterialQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getFormulaMaterialQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getFormulaMaterialQty()) && !NO_VALUE.equals(data.getFormulaMaterialQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getFormulaMaterialQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getUsedMaterialQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getUsedMaterialQty()) && !NO_VALUE.equals(data.getUsedMaterialQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getUsedMaterialQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getRealUsedMaterialQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getRealUsedMaterialQty()) && !NO_VALUE.equals(data.getRealUsedMaterialQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getRealUsedMaterialQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getDiffMaterialQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getDiffMaterialQty()) && !NO_VALUE.equals(data.getDiffMaterialQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getDiffMaterialQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getGoodsQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getMaterialQty()) && !NO_VALUE.equals(data.getMaterialQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getMaterialQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getGoodsQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getGoodsQty()) && !NO_VALUE.equals(data.getGoodsQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getGoodsQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getDiffGoodsQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getDiffGoodsQty()) && !NO_VALUE.equals(data.getDiffGoodsQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getDiffGoodsQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getWasteGoodsPnt
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getWasteGoodsPnt()) && !NO_VALUE.equals(data.getWasteGoodsPnt())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getWasteGoodsPnt())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getWasteGoodsQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getWasteGoodsQty()) && !NO_VALUE.equals(data.getWasteGoodsQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getWasteGoodsQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getBalanceGoodsQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getBalanceGoodsQty()) && !NO_VALUE.equals(data.getBalanceGoodsQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getBalanceGoodsQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}

		// set output
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return content;

	}

	@Override
	public List<ProductPaperRelationProducedGoodsVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("uploadData readVo filename={}", formVo.getFile().getOriginalFilename());

		List<ProductPaperRelationProducedGoodsVo> dataList = new ArrayList<>();
		ProductPaperRelationProducedGoodsVo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()))) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new ProductPaperRelationProducedGoodsVo();
				// Skip on Second row
				if (row.getRowNum() < 2) {
					continue;
				}
				for (Cell cell : row) {

					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						// DocNo
						data.setDocNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						// MaterialDesc
						data.setMaterialDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// InputMaterialQty
						data.setInputMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// FormulaMaterialQty
						data.setFormulaMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// UsedMaterialQty
						data.setUsedMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// RealUsedMaterialQty
						data.setRealUsedMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// DiffMaterialQty
						data.setDiffMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 8) {
						// MaterialQty
						data.setMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 9) {
						// GoodsQty
						data.setGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 9) {
						// DiffGoodsQty
						data.setDiffGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 10) {
						// WasteGoodsPnt
						data.setWasteGoodsPnt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 11) {
						// WasteGoodsQty
						data.setWasteGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 12) {
						// BalanceGoodsQty
						data.setBalanceGoodsQty(ExcelUtils.getCellValueAsString(cell));
					}
				}
				dataList.add(data);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return dataList;
	}

	@Override
	public void save(ProductPaperFormVo formVo) {
		logger.info("save");

		TaPaperPr04H entityH = new TaPaperPr04H();
		prepareEntityH(formVo, entityH, TaPaperPr04H.class);
		taPaperPr04HRepository.save(entityH);

		List<ProductPaperRelationProducedGoodsVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperPr04D> entityDList = new ArrayList<>();
		TaPaperPr04D entityD = null;
		int i = 1;
		for (ProductPaperRelationProducedGoodsVo vo : voList) {
			entityD = new TaPaperPr04D();
			entityD.setPaperPrNumber(entityH.getPaperPrNumber());
			entityD.setSeqNo(i);
			entityD.setDocNo(vo.getDocNo());
			entityD.setMaterialDesc(vo.getMaterialDesc());
			entityD.setInputMaterialQty(!NO_VALUE.equals(vo.getInputMaterialQty()) ? NumberUtils.toBigDecimal(vo.getInputMaterialQty()) : null);
			entityD.setFormulaMaterialQty(!NO_VALUE.equals(vo.getFormulaMaterialQty()) ? NumberUtils.toBigDecimal(vo.getFormulaMaterialQty()) : null);
			entityD.setUsedMaterialQty(!NO_VALUE.equals(vo.getUsedMaterialQty()) ? NumberUtils.toBigDecimal(vo.getUsedMaterialQty()) : null);
			entityD.setRealUsedMaterialQty(!NO_VALUE.equals(vo.getRealUsedMaterialQty()) ? NumberUtils.toBigDecimal(vo.getRealUsedMaterialQty()) : null);
			entityD.setDiffMaterialQty(!NO_VALUE.equals(vo.getDiffMaterialQty()) ? NumberUtils.toBigDecimal(vo.getDiffMaterialQty()) : null);
			entityD.setMaterialQty(!NO_VALUE.equals(vo.getMaterialQty()) ? NumberUtils.toBigDecimal(vo.getMaterialQty()) : null);
			entityD.setGoodsQty(!NO_VALUE.equals(vo.getGoodsQty()) ? NumberUtils.toBigDecimal(vo.getGoodsQty()) : null);
			entityD.setDiffGoodsQty(!NO_VALUE.equals(vo.getDiffGoodsQty()) ? NumberUtils.toBigDecimal(vo.getDiffGoodsQty()) : null);
			entityD.setWasteGoodsPnt(!NO_VALUE.equals(vo.getWasteGoodsPnt()) ? NumberUtils.toBigDecimal(vo.getWasteGoodsPnt()) : null);
			entityD.setWasteGoodsQty(!NO_VALUE.equals(vo.getWasteGoodsQty()) ? NumberUtils.toBigDecimal(vo.getWasteGoodsQty()) : null);
			entityD.setBalanceGoodsQty(!NO_VALUE.equals(vo.getBalanceGoodsQty()) ? NumberUtils.toBigDecimal(vo.getBalanceGoodsQty()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperPr04DRepository.saveAll(entityDList);
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		return taPaperPr04HRepository.findPaperPrNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}

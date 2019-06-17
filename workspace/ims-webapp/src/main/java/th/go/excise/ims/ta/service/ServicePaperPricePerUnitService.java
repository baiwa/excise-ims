package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperPricePerUnitVo;
import th.go.excise.ims.ta.vo.ServicePaperQtyVo;

@Service
public class ServicePaperPricePerUnitService extends AbstractServicePaperService<ServicePaperPricePerUnitVo> {

	private static final Logger logger = LoggerFactory.getLogger(ServicePaperPricePerUnitService.class);

	// MethodPriceServiceVo

	public DataTableAjax<ServicePaperPricePerUnitVo> GetPriceServiceVo(CreatePaperFormVo request) {
		int total = 35;
		DataTableAjax<ServicePaperPricePerUnitVo> dataTableAjax = new DataTableAjax<ServicePaperPricePerUnitVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listPriceServiceVo(request.getStart(), request.getLength(), total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<ServicePaperPricePerUnitVo> listPriceServiceVo(int start, int length, int total) {
		String excise = "รายการบริการ";
		List<ServicePaperPricePerUnitVo> datalist = new ArrayList<ServicePaperPricePerUnitVo>();

		ServicePaperPricePerUnitVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ServicePaperPricePerUnitVo();
			data.setGoodsDesc(excise + i);
			data.setInvoicePrice("");
			data.setInformPrice("");
			data.setAuditPrice("");
			data.setTaxPrice("");
			data.setDiffPrice("");
			datalist.add(data);

		}
		return datalist;
	}

	public byte[] exportFilePriceServiceVo() throws IOException {

		List<ServicePaperPricePerUnitVo> dataListexportFile = new ArrayList<ServicePaperPricePerUnitVo>();
		dataListexportFile = listPriceServiceVo(0, 35, 35);
		logger.info("Data list exportFilePriceServiceVo {} row", dataListexportFile.size());
		XSSFWorkbook workbook = new XSSFWorkbook();
		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellRightBgStyle = ExcelUtils.createCellColorStyle(workbook, new XSSFColor(new java.awt.Color(192, 192, 192)), HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบด้านราคาต่อหน่วย");
		int rowNum = 0;
		int cellNum = 0;

		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "รายการ", "ราคาตามใบกำกับภาษี", "ราคาบริการตามแบบแจ้ง", "จากการตรวจสอบ", "ราคาที่ยื่นชำระภาษี" };
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);

		row = sheet.createRow(rowNum);
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		}
		;
		rowNum++;
		cellNum = 0;
		int order = 1;
		for (ServicePaperPricePerUnitVo detail : dataListexportFile) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue(String.valueOf(order++));

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsDesc())) ? detail.getGoodsDesc() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getInvoicePrice())) ? detail.getInvoicePrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getInformPrice())) ? detail.getInformPrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getAuditPrice())) ? detail.getAuditPrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getTaxPrice())) ? detail.getTaxPrice() : "");

			rowNum++;
			cellNum = 0;
		}

		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		byte[] cont = null;
		workbook.write(outByteStream);
		cont = outByteStream.toByteArray();
		return cont;
	}

	public List<ServicePaperPricePerUnitVo> readFileServicePaperPricePerUnitVo(ServicePaperPricePerUnitVo request) {
		logger.info("readFileQuantityServiceVo");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());
		List<ServicePaperPricePerUnitVo> dataList = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				ServicePaperPricePerUnitVo pushdata = new ServicePaperPricePerUnitVo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						pushdata.setGoodsDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						pushdata.setInvoicePrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						pushdata.setInformPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						pushdata.setAuditPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						pushdata.setTaxPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						pushdata.setDiffPrice(ExcelUtils.getCellValueAsString(cell));
					}

				}
				dataList.add(pushdata);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataList;
	}

	@Override
	protected List<ServicePaperPricePerUnitVo> inquiryByWs(ServicePaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		List<ServicePaperPricePerUnitVo> voList = new ArrayList<>();
		
		return voList;
	}

	@Override
	protected List<ServicePaperPricePerUnitVo> inquiryByPaperSvNumber(ServicePaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] exportData(List<ServicePaperPricePerUnitVo> voList, String exportType) {
		// TODO Auto-generated method stub
		return null;
	}

	// Done
}

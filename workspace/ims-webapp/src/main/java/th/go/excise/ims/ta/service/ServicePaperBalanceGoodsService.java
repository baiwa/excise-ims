package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperBalanceGoodsVo;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperQtyVo;

@Service
public class ServicePaperBalanceGoodsService extends AbstractServicePaperService<ServicePaperBalanceGoodsVo> {

	private static final Logger logger = LoggerFactory.getLogger(ServicePaperBalanceGoodsService.class);

	public DataTableAjax<ServicePaperBalanceGoodsVo> GetLeftInStockServiceVo(CreatePaperFormVo request) {
		int total = 35;
		DataTableAjax<ServicePaperBalanceGoodsVo> dataTableAjax = new DataTableAjax<ServicePaperBalanceGoodsVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listLeftInStockServiceVo(request.getStart(), request.getLength(), total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<ServicePaperBalanceGoodsVo> listLeftInStockServiceVo(int start, int length, int total) {
		String excise = "รายการสินค้า";

		List<ServicePaperBalanceGoodsVo> datalist = new ArrayList<ServicePaperBalanceGoodsVo>();
		ServicePaperBalanceGoodsVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ServicePaperBalanceGoodsVo();
			data.setGoodsDesc(excise + i);
			data.setBalanceGoodsQty("");
			data.setAuditBalanceGoodsQty("");
			data.setDiffBalanceGoodsQty("");
			datalist.add(data);
		}

		return datalist;
	}

	public byte[] exportFileLeftInStockServiceVo() throws IOException {

		List<ServicePaperBalanceGoodsVo> dataListexportFile = new ArrayList<ServicePaperBalanceGoodsVo>();
		dataListexportFile = listLeftInStockServiceVo(0, 35, 35);
		logger.info("Data list exportFilePriceServiceVo {} row", dataListexportFile.size());

		XSSFWorkbook workbook = new XSSFWorkbook();

		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		Sheet sheet = workbook.createSheet("บันทึกผลการตรวจนับสินค้าคงเหลือ");
		int rowNum = 0;
		int cellNum = 0;

		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "รายการ", "ยอดคงเหลือตามบัญชี", "ยอดสินค้าคงเหลือจากการตรวจนับ" };
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 35 * 256);

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
		for (ServicePaperBalanceGoodsVo detail : dataListexportFile) {
			row = sheet.createRow(rowNum);
			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue(String.valueOf(order++));

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsDesc())) ? detail.getGoodsDesc() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getBalanceGoodsQty())) ? detail.getBalanceGoodsQty() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getAuditBalanceGoodsQty())) ? detail.getAuditBalanceGoodsQty() : "");

			rowNum++;
			cellNum = 0;
		}

		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		byte[] cont = null;
		workbook.write(outByteStream);
		cont = outByteStream.toByteArray();
		return cont;
	}

	public List<ServicePaperBalanceGoodsVo> readFileServicePaperMemberVo(ServicePaperBalanceGoodsVo request) {
		logger.info("readFileServicePaperMemberVo");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());
		List<ServicePaperBalanceGoodsVo> dataList = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				ServicePaperBalanceGoodsVo pushdata = new ServicePaperBalanceGoodsVo();
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
						pushdata.setBalanceGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						pushdata.setAuditBalanceGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						pushdata.setDiffBalanceGoodsQty(ExcelUtils.getCellValueAsString(cell));
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
	protected List<ServicePaperBalanceGoodsVo> inquiryByWs(ServicePaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		List<ServicePaperBalanceGoodsVo> voList = new ArrayList<>();
		
		return voList;
	}

	@Override
	protected List<ServicePaperBalanceGoodsVo> inquiryByPaperSvNumber(ServicePaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] exportData(List<ServicePaperBalanceGoodsVo> voList, String exportType) {
		// TODO Auto-generated method stub
		return null;
	}

}

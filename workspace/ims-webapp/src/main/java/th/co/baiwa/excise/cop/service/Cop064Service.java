package th.co.baiwa.excise.cop.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.cop.persistence.dao.CopCheckFiscalYearDao;
import th.co.baiwa.excise.cop.persistence.dao.ReportCheckOperationDao;
import th.co.baiwa.excise.cop.persistence.repository.OaTaxReduceWsDtlRepository;
import th.co.baiwa.excise.cop.persistence.vo.Cop061FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop064ExcelVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop064FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop064Vo;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.oa.persistence.entity.OaTaxReduceWsDtl;

@Service
public class Cop064Service {

	@Autowired
	private ReportCheckOperationDao reportCheckOperationDao;
	
	@Autowired
	private CopCheckFiscalYearDao copCheckFiscalYearDao;
	
	@Autowired
	private OaTaxReduceWsDtlRepository oaTaxReduceWsDtlRepository;

	public DataTableAjax<Cop064Vo> findAll(Cop064FormVo formVo) {

		DataTableAjax<Cop064Vo> dataTableAjax = new DataTableAjax<>();
		if (StringUtils.isNotBlank(formVo.getExciseId())) {

			List<Cop064Vo> list = reportCheckOperationDao.findAll064(formVo);
			Long count = reportCheckOperationDao.count064(formVo);

			if (formVo.getDataExcel() != null) {
				mapData(list,formVo.getDataExcel());
			}
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}
	public void mapData(List<Cop064Vo> list,List<Cop064ExcelVo> dataExcel) {
		for (Cop064Vo vo : list) {
			for (Cop064ExcelVo excel : dataExcel) {
				if (vo.getTaExciseAcc0502DtlList().equals(excel.getColumn2())) {					
					vo.setReceiptNumber(excel.getColumn3());
					vo.setTaxNumber(excel.getColumn4());
					vo.setVolume(excel.getColumn5());
					vo.setUnit(new BigDecimal(excel.getColumn6()));
					excel.setFlag("Y");
				}
			}			
		}
		
		for (Cop064ExcelVo excel : dataExcel) {
			Cop064Vo vo =new Cop064Vo();
			if ("N".equalsIgnoreCase(excel.getFlag())) {
				vo.setTaExciseAcc0502DtlList(excel.getColumn2());
				vo.setReceiptNumber(excel.getColumn3());
				vo.setTaxNumber(excel.getColumn4());
				vo.setVolume(excel.getColumn5());				
				try {
					vo.setUnit(new BigDecimal(excel.getColumn6()));
					list.add(vo);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
	}
	
	public void save(Cop064FormVo formVo) {
		
		List<OaTaxReduceWsDtl> entityList = new ArrayList<>();

		Long idHead = reportCheckOperationDao.saveHeadCop064(formVo);
		
		for (Cop064Vo vo : formVo.getDataListVo()) {
			OaTaxReduceWsDtl entity = new OaTaxReduceWsDtl();
			
			//check
			
			vo.setTaxAmount((vo.getTaxAmount()!= null ? vo.getTaxAmount() : new BigDecimal(0)));
			vo.setAmount(vo.getAmount()!= null ? vo.getAmount() : new BigDecimal(0));
			vo.setTaxPerAmount(vo.getTaxPerAmount() != null ? vo.getTaxPerAmount() : new BigDecimal(0));
			vo.setUnit(vo.getUnit() != null ? vo.getUnit() : new BigDecimal(0));
			
			entity.setTaxReduceWsHdrId(idHead);
			entity.setList(vo.getTaExciseAcc0502DtlList());			
			entity.setTotalTax(vo.getTaxAmount());			
			entity.setProductAmount1(vo.getAmount().longValue());
			entity.setTaxPerProduct(vo.getTaxPerAmount());
			entity.setBillNo(vo.getReceiptNumber());
			entity.setTaxAmount(new BigDecimal(vo.getTaxNumber()));
			entity.setProductAmount2(Long.valueOf(vo.getVolume()));
			entity.setMaxValues(vo.getUnit().toString());
			
			BigDecimal result = vo.getTaxPerAmount().subtract(vo.getUnit());
			entity.setResult(result.toString());
			
			
			entityList.add(entity);
		}  
		oaTaxReduceWsDtlRepository.save(entityList);
		copCheckFiscalYearDao.updateStatusCopCheckFiscalYearDtl(formVo.getExciseId(),formVo.getFiscalYear());
		
	}
	
	public List<String> findExciseId(String fiscalYear) {
		List<String> dataList = reportCheckOperationDao.findExciseIdAll(fiscalYear);
		return dataList;
	}

	public Cop061FormVo findByExciseId(String exciseId) {
		Cop061FormVo data = new Cop061FormVo();
		List<Cop061FormVo> planWorksheetHeader = reportCheckOperationDao.findByExciseId(exciseId);
		if (!planWorksheetHeader.isEmpty()) {
			data = planWorksheetHeader.get(0);
		}
		return data; 
	}

	public List<Cop064ExcelVo> readFileExcel(Cop064FormVo formVo)
			throws IOException, EncryptedDocumentException, InvalidFormatException {

		List<Cop064ExcelVo> excelVo = new ArrayList<>();
		byte[] byt = formVo.getFileName().getBytes();
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

				/* column */
				List<String> columns = new ArrayList<>();
				for (short colIx = minColIx; colIx < maxColIx; colIx++) {

					Cell cell = row.getCell(colIx);
					if (cell != null) {
						map.put(getStringValue(cell), cell.getColumnIndex());
						columns.add(getStringValue(cell));
					} else {
						columns.add("");
					}
				}
				addData(excelVo,columns);
			}
		}

		
		return excelVo;
	}
	
	public void addData(List<Cop064ExcelVo> dataList,List<String> columns) {
		
			Cop064ExcelVo vo = new Cop064ExcelVo();			
			vo.setColumn1(StringUtils.trim(columns.get(0)));
			vo.setColumn2(StringUtils.trim(columns.get(1)));
			vo.setColumn3(StringUtils.trim(columns.get(2)));
			vo.setColumn4(StringUtils.trim(columns.get(3)));
			vo.setColumn5(StringUtils.trim(columns.get(4)));
			vo.setColumn6(StringUtils.trim(columns.get(5)));
			
			dataList.add(vo);		
	}

	public static String getStringValue(Cell cell) {

		if (cell == null)
			return "";

		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			return cell.getBooleanCellValue() ? "1" : "0";
		case FORMULA:
			return cell.getCellFormula();
		case NUMERIC:
			cell.setCellType(CellType.STRING);
			return cell.getStringCellValue();
		case STRING:
			return cell.getStringCellValue();
		default:
			return "";
		}
	}

}

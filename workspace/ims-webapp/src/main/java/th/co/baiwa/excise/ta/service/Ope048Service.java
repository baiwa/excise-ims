package th.co.baiwa.excise.ta.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.PriceChrckerDao;
import th.co.baiwa.excise.ta.persistence.vo.Ope048ExcelVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope048FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope048Vo;

@Service
public class Ope048Service {

	@Autowired
	private PriceChrckerDao priceChrckerDao;
	
	
	public DataTableAjax<Ope048Vo> findAll(Ope048FormVo formVo) {

		DataTableAjax<Ope048Vo> dataTableAjax = new DataTableAjax<>();
		if (StringUtils.isNotBlank(formVo.getExciseId())) {

			List<Ope048Vo> list = priceChrckerDao.findAll(formVo);
			Long count = priceChrckerDao.count(formVo);

			if (formVo.getDataExcel() != null) {
				mapData(list,formVo.getDataExcel());
			}
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}
	
	public void mapData(List<Ope048Vo> list,List<Ope048ExcelVo> dataExcel) {
		for (Ope048Vo vo : list) {
			for (Ope048ExcelVo excel : dataExcel) {
				if (vo.getTa_exciseAcc0307DtlId().equals(excel.getColumn2())) {					
					vo.setExcelPriceout(new BigDecimal(excel.getColumn5()));
					excel.setFlag("Y");
				}
			}
		}
		
		for (Ope048ExcelVo excel : dataExcel) {
			Ope048Vo vo =new Ope048Vo();
			if ("N".equalsIgnoreCase(excel.getFlag())) {
				vo.setTaExciseAcc0307List(excel.getColumn3());
						
				try {
					vo.setExcelPriceout(new BigDecimal(excel.getColumn5()));
					list.add(vo);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
	}
	
	
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = priceChrckerDao.findExciseIdAll();
		return dataList;
	}

	public Ope048FormVo findByExciseId(String exciseId) {
		Ope048FormVo data = new Ope048FormVo();
		List<Ope048FormVo> planWorksheetHeader = priceChrckerDao.findByExciseId(exciseId);
		if (!planWorksheetHeader.isEmpty()) {
			data = planWorksheetHeader.get(0);
		}
		return data; 
	}

	public List<Ope048ExcelVo> readFileExcel(Ope048FormVo formVo) throws IOException, EncryptedDocumentException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {

		List<Ope048ExcelVo> excelVo = new ArrayList<>();
		byte[] byt = formVo.getFileName().getBytes();
		 Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getLastRowNum();
		Map<String, Integer> map = new HashMap<String, Integer>();

		/* rows*/ 
		for (int r = 0; r <= totalRows; r++) {
			
			 Row row = sheet.getRow(r);
			if (row != null) {
				
				short minColIx = row.getFirstCellNum();
				short maxColIx = row.getLastCellNum();

				 /*column */
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
	
	public void addData(List<Ope048ExcelVo> dataList,List<String> columns) {
		
			Ope048ExcelVo vo = new Ope048ExcelVo();						
			vo.setColumn2(StringUtils.trim(columns.get(1)));
			vo.setColumn3(StringUtils.trim(columns.get(2)));
			vo.setColumn4(StringUtils.trim(columns.get(3)));
			vo.setColumn5(StringUtils.trim(columns.get(4)));
			
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

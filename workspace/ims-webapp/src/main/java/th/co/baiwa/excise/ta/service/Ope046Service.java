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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.CreatePeperChrckerDao;
import th.co.baiwa.excise.ta.persistence.entity.TaxReduceWsDtlS;
import th.co.baiwa.excise.ta.persistence.entity.TaxReduceWsHdr;
import th.co.baiwa.excise.ta.persistence.repository.TaxReduceWsDtlRepository;
import th.co.baiwa.excise.ta.persistence.repository.TaxReduceWsHdrRepository;
import th.co.baiwa.excise.ta.persistence.vo.Ope046ExcelVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046SumVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046Vo;

@Service
public class Ope046Service {

	@Autowired
	private CreatePeperChrckerDao createPeperChrckerDao;
	
	@Autowired
	private TaxReduceWsDtlRepository taxReduceWsDtlRepository;
	
	@Autowired
	private TaxReduceWsHdrRepository taxReduceWsHdrRepository;

	public DataTableAjax<Ope046Vo> findAll(Ope046FormVo formVo) {

		DataTableAjax<Ope046Vo> dataTableAjax = new DataTableAjax<>();
		if (StringUtils.isNotBlank(formVo.getExciseId())) {

			List<Ope046Vo> list = createPeperChrckerDao.findAll(formVo);
			Long count = createPeperChrckerDao.count(formVo);

			if (formVo.getDataExcel() != null) {
				mapData(list,formVo.getDataExcel());
			}
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}
	public void mapData(List<Ope046Vo> list,List<Ope046ExcelVo> dataExcel) {
		for (Ope046Vo vo : list) {
			for (Ope046ExcelVo excel : dataExcel) {
				if (vo.getTaExciseAcc0502DtlList().equals(excel.getColumn2())) {					
					vo.setReceiptNumber(excel.getColumn3());
					vo.setTaxNumber(excel.getColumn4());
					vo.setVolume(excel.getColumn5());
					vo.setUnit(new BigDecimal(excel.getColumn6()));
					excel.setFlag("Y");
				}
			}			
		}
		
		for (Ope046ExcelVo excel : dataExcel) {
			Ope046Vo vo =new Ope046Vo();
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
	
	public void save(Ope046SumVo sumVo) {
		
		List<TaxReduceWsDtlS> entityList = new ArrayList<>();
		
		/*save header*/
		Ope046FormVo form = sumVo.getForm();
		TaxReduceWsHdr header = new TaxReduceWsHdr();
		header.setExciseId(form.getExciseId());
		header.setTaAnalysisId(form.getAnlysisNumber());
		header.setTaxationId(form.getUserNumber());
		header.setStartDate(form.getDateFrom());
		header.setEndDate(form.getDateTo());
		//header.setPdtType(pdtType);
		header.setSubPdtType(form.getCoordinates());
		TaxReduceWsHdr headerId = taxReduceWsHdrRepository.save(header);
	
		
		/*save details*/
		List<Ope046Vo> vos = sumVo.getVoList();
		for (Ope046Vo vo : vos) {
			TaxReduceWsDtlS entity = new TaxReduceWsDtlS();
			
			//check
			vo.setTaxAmount((vo.getTaxAmount()!= null ? vo.getTaxAmount() : new BigDecimal(0)));
			vo.setAmount(vo.getAmount()!= null ? vo.getAmount() : new BigDecimal(0));
			vo.setTaxPerAmount(vo.getTaxPerAmount() != null ? vo.getTaxPerAmount() : new BigDecimal(0));
			vo.setUnit(vo.getUnit() != null ? vo.getUnit() : new BigDecimal(0));
			
			entity.setList(vo.getTaExciseAcc0502DtlList());			
			entity.setTotalTax(vo.getTaxAmount().toString());			
			entity.setPdtAmount1(vo.getAmount().toString());
			entity.setTaxPerPdt(vo.getTaxPerAmount().toString());
			entity.setBillNo(vo.getReceiptNumber());
			entity.setTaxAmount(vo.getTaxNumber());
			entity.setPdtAmount2(vo.getVolume());
			entity.setMaxValues(vo.getUnit().toString());
			entity.setTaTaxReduceWsHeaderId(headerId.getTaTaxReduceWsHdrId());
			
			BigDecimal result = vo.getTaxPerAmount().subtract(vo.getUnit());
			entity.setResult(result.toString());
			
			
			entityList.add(entity);
		}
		taxReduceWsDtlRepository.save(entityList);
	}
	
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = createPeperChrckerDao.findExciseIdAll();
		return dataList;
	}

	public Ope046FormVo findByExciseId(String exciseId) {
		Ope046FormVo data = new Ope046FormVo();
		List<Ope046FormVo> planWorksheetHeader = createPeperChrckerDao.findByExciseId(exciseId);
		if (!planWorksheetHeader.isEmpty()) {
			data = planWorksheetHeader.get(0);
		}
		return data; 
	}

	public List<Ope046ExcelVo> readFileExcel(Ope046FormVo formVo)
			throws IOException, EncryptedDocumentException, InvalidFormatException {

		List<Ope046ExcelVo> excelVo = new ArrayList<>();
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
	
	public void addData(List<Ope046ExcelVo> dataList,List<String> columns) {
		
			Ope046ExcelVo vo = new Ope046ExcelVo();			
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

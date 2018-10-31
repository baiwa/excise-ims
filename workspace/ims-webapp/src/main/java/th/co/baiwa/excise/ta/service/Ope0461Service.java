package th.co.baiwa.excise.ta.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
import th.co.baiwa.excise.ta.persistence.dao.DisplayCreatePeperChrckerDetailDao;
import th.co.baiwa.excise.ta.persistence.repository.TaxReduceWsDtlRepository;
import th.co.baiwa.excise.ta.persistence.repository.TaxReduceWsHdrRepository;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046ExcelVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046FormVo;

@Service
public class Ope0461Service {

	@Autowired
	private CreatePeperChrckerDao createPeperChrckerDao;

	@Autowired
	private TaxReduceWsDtlRepository taxReduceWsDtlRepository;

	@Autowired
	private TaxReduceWsHdrRepository taxReduceWsHdrRepository;

	@Autowired
	private DisplayCreatePeperChrckerDetailDao displayCreatePeperChrckerDao;

	public DataTableAjax<Ope0461Vo> findAll(Ope046FormVo formVo) {

		DataTableAjax<Ope0461Vo> dataTableAjax = new DataTableAjax<>();

		List<Ope0461Vo> list = displayCreatePeperChrckerDao.findAll(formVo);
		Long count = displayCreatePeperChrckerDao.count(formVo);

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
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
				addData(excelVo, columns);
			}
		}

		return excelVo;
	}

	public void addData(List<Ope046ExcelVo> dataList, List<String> columns) {

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

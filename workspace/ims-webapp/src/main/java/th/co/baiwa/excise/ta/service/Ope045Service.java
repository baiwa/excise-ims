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
import th.co.baiwa.excise.ta.persistence.dao.CreatePeperPayProductDao;
import th.co.baiwa.excise.ta.persistence.entity.TaPdtDrawingWsDtl;
import th.co.baiwa.excise.ta.persistence.entity.TaPdtDrawingWsHeader;
import th.co.baiwa.excise.ta.persistence.repository.TaPdtDrawingWsDtlRepository;
import th.co.baiwa.excise.ta.persistence.repository.TaPdtDrawingWsHeaderRepository;
import th.co.baiwa.excise.ta.persistence.vo.Ope045FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope045SumVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope045Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046ExcelVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046FormVo;

@Service
public class Ope045Service {

	@Autowired
	private CreatePeperPayProductDao createPeperPayProductDao;

	@Autowired
	private TaPdtDrawingWsHeaderRepository headerRepo;

	@Autowired
	private TaPdtDrawingWsDtlRepository detailsRepo;

	public DataTableAjax<Ope045Vo> findAll(Ope045FormVo formVo) {

		DataTableAjax<Ope045Vo> dataTableAjax = new DataTableAjax<>();

		if (StringUtils.isNotBlank(formVo.getExciseId())) {

			List<Ope045Vo> list = createPeperPayProductDao.findAll(formVo);
			Long count = createPeperPayProductDao.count(formVo);

			if (formVo.getDataExcel() != null) {
				mapData(list, formVo.getDataExcel());
			}
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}
		return dataTableAjax;
	}

	public void mapData(List<Ope045Vo> list, List<Ope046ExcelVo> dataExcel) {
		for (Ope045Vo vo : list) {
			for (Ope046ExcelVo excel : dataExcel) {
				if (vo.getOrder().equals(excel.getColumn2())) {
					vo.setAmount1Out(excel.getColumn3());
					vo.setAmount2Out(excel.getColumn4());
					excel.setFlag("Y");
				}
			}
		}

		for (Ope046ExcelVo excel : dataExcel) {
			Ope045Vo vo = new Ope045Vo();
			if ("N".equalsIgnoreCase(excel.getFlag())) {
				vo.setOrder(excel.getColumn2());
				vo.setAmount1Out(excel.getColumn3());
				vo.setAmount2Out(excel.getColumn4());
				try {
					new BigDecimal(vo.getAmount1Out());
					list.add(vo);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}
	}

	public List<LabelValueBean> exciseidList() {
		List<LabelValueBean> dataList = createPeperPayProductDao.exciseidList();
		return dataList;
	}

	public Ope045FormVo findByExciseId(String exciseId) {
		if (StringUtils.isNotBlank(exciseId)) {
			List<Ope045FormVo> list = createPeperPayProductDao.findByExciseId(exciseId);
			return list.get(0);
		}
		return new Ope045FormVo();
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

	public void save(Ope045SumVo sumVo) {
		// ==> Header
		Ope045FormVo formHeader = sumVo.getForm();
		TaPdtDrawingWsHeader entityHdr = new TaPdtDrawingWsHeader();
		entityHdr.setExciseId(formHeader.getExciseId());
		entityHdr.setStartDate(formHeader.getDateFrom());
		entityHdr.setEndDate(formHeader.getDateTo());
		entityHdr.setPdtType(formHeader.getType());
		entityHdr.setSubPdtType(formHeader.getCoordinates());
		entityHdr.setCompanyName(formHeader.getEntrepreneur());

		TaPdtDrawingWsHeader hdr = headerRepo.save(entityHdr);

		// ==> Details
		List<Ope045Vo> formDetails = sumVo.getVoList();
		List<TaPdtDrawingWsDtl> enDtls = new ArrayList<>();

		for (Ope045Vo vo : formDetails) {
			// ==> Set Detail
			TaPdtDrawingWsDtl entityDetail = new TaPdtDrawingWsDtl();
			entityDetail.setTaPdtDrawingWsHeaderId(hdr.getTaPdtDrawingWsHeaderId());
			entityDetail.setTaPdtDrawingWsDtlOrder(vo.getOrder());					
			entityDetail.setValuesInBill(vo.getAmount1Out());
			entityDetail.setValuesOfProduct(vo.getAmount2Out());
			entityDetail.setValuesOf0704(vo.getAmountTable2());
			entityDetail.setMaxValues(vo.getAmountMax());
			entityDetail.setValuesOfDrawing(vo.getAmountTable3());
			entityDetail.setResult(vo.getDiff());

			enDtls.add(entityDetail);
		}
		detailsRepo.save(enDtls);

	}

}

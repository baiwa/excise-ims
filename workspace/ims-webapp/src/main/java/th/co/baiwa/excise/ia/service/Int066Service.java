package th.co.baiwa.excise.ia.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaWithdrawalDao;
import th.co.baiwa.excise.ia.persistence.vo.Int065FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int065Vo;
import th.co.baiwa.excise.upload.service.ExcalService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@Service
public class Int066Service {
	
	@Autowired
	private ExcalService excalService;
	@Autowired
	private LovRepository lovRepository;

	@Autowired
	private IaWithdrawalDao iaWithdrawalDao;
	
	public DataTableAjax<Int065Vo> findAll(Int065FormVo formVo) {
		
		DataTableAjax<Int065Vo> dataTableAjax = new DataTableAjax<>();
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			
			String officeCode = mappingOfficeCode(formVo);
			formVo.setOfficeCode(officeCode);
			formVo.setDateFrom(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateFrom()));
			formVo.setDateTo(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateTo()));
			List<Int065Vo> list = iaWithdrawalDao.findAllKtb(formVo);
			Long count = iaWithdrawalDao.countKtb(formVo);			
			
			dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public List<Lov> sector() {
		List<Lov> lov = lovRepository.findByTypeAndLovIdMasterIsNullOrderBySubType("SECTOR_VALUE");
		return lov;
	}

	public List<Lov> area(Long idMaster) {
        return lovRepository.findByLovIdMasterOrderBySubType(idMaster);
    }
	
	public List<Lov> branch(Long idMaster) {
        return lovRepository.findByLovIdMasterOrderBySubType(idMaster);
    }

	public List<Lov> budgetType(){
		return lovRepository.findByTypeAndLovIdMasterIsNullOrderBySubType("BUDGET_TYPE");
	}
	
	public String mappingOfficeCode(Int065FormVo formVo) {
		Lov sectors = new Lov();
		Lov areas = new Lov();
		Lov branchs = new Lov();
		
		if (StringUtils.isNotBlank(formVo.getSector())) {
			sectors = lovRepository.findByLovId(Long.valueOf(formVo.getSector()));
			
			if (StringUtils.isNotBlank(formVo.getArea())) {
				areas = lovRepository.findByLovId(Long.valueOf(formVo.getArea()));
				
				if (StringUtils.isNotBlank(formVo.getBranch())) {
					branchs = lovRepository.findByLovId(Long.valueOf(formVo.getBranch()));
				}
			}
		}
		
		StringBuilder officeCode = new StringBuilder("");
		
		if (sectors != null) {
			if(StringUtils.isNoneBlank(sectors.getSubType())) {
				officeCode.append(sectors.getSubType());
			}
		}
		if (areas != null) {
			if(StringUtils.isNoneBlank(areas.getSubType())) {
				officeCode.append(areas.getSubType());
			}
		}
		if (branchs != null) {
			if(StringUtils.isNoneBlank(branchs.getSubType())) {
				officeCode.append(branchs.getSubType());
			}
		}
				
		return officeCode.toString();
	}
	
	
	public void exportFile(Int065FormVo formVo, HttpServletResponse response) throws IOException {
		
		/* create spreadsheet */
		XSSFWorkbook workbook = excalService.setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		System.out.println("Creating excel");
		
		String[] columns = {"ลำดับ", "วันที่สั่งจ่าย", "เช็คเล่มที่ ","จำนวนเงินสั่งจ่ายในเช็ค", "ประเภทงบประมาณ","รายการ","ผู้รับเงิน"};
		
		for (cellNum = 0; cellNum < columns.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(columns[cellNum]);
			cell.setCellStyle(excalService.thStyle);
		}
		
		String officeCode = mappingOfficeCode(formVo);
		formVo.setOfficeCode(officeCode);
		formVo.setDateFrom(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateFrom()));
		formVo.setDateTo(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateTo()));
		List<Int065Vo> list = iaWithdrawalDao.exportFile(formVo);

		rowNum = 2;
		cellNum = 0;
		int no = 1;
		
        // Create cells
        for(Int065Vo item : list) {
        	row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(excalService.cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(item.getPaymentDate());
			cell.setCellStyle(excalService.cellLeft);
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(item.getRefPayment());
			cell.setCellStyle(excalService.cellLeft);
			cellNum++;
		
			cell = row.createCell(cellNum);
			cell.setCellValue(item.getAmount());
			cell.setCellStyle(excalService.cellRight);
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(item.getBudgetType());
			cell.setCellStyle(excalService.cellLeft);
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(item.getItemDesc());
			cell.setCellStyle(excalService.cellLeft);
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(item.getPayee());
			cell.setCellStyle(excalService.cellLeft);
			cellNum++;
			
			no++;
			rowNum++;
			cellNum = 0;
        }
        
        for (int i = 0; i < columns.length; i++) {
        	   sheet.autoSizeColumn(i);
        	  }

		/*set	fileName*/		
		String fileName =" KTB-Corporate";
		System.out.println(fileName);
		
		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);
		byte [] outArray = outByteStream.toByteArray();
		response.setContentType("application/vnd.ms-excel");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
		
		System.out.println("Done");

	}

	
}

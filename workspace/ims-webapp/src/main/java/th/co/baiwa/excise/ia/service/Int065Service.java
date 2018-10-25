package th.co.baiwa.excise.ia.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

@Service
public class Int065Service {
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
			List<Int065Vo> list = iaWithdrawalDao.findAll(formVo);
			Long count = iaWithdrawalDao.count(formVo);

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

	public List<Lov> budgetType() {
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
			if (StringUtils.isNoneBlank(sectors.getSubType())) {
				officeCode.append(sectors.getSubType());
			}
		}
		if (areas != null) {
			if (StringUtils.isNoneBlank(areas.getSubType())) {
				officeCode.append(areas.getSubType());
			}
		}
		if (branchs != null) {
			if (StringUtils.isNoneBlank(branchs.getSubType())) {
				officeCode.append(branchs.getSubType());
			}
		}

		return officeCode.toString();
	}

	public void exportFile(Int065FormVo formVo, HttpServletResponse response) throws IOException {
		String[] columns = {"ลำดับ", "วันที่สั่งจ่าย", "เช็คเล่มที่ ", "จำนวนเงินสั่งจ่ายในเช็ค", "ประเภทงบประมาณ","รายการ","ผู้รับเงิน"};
		String officeCode = mappingOfficeCode(formVo);
		formVo.setOfficeCode(officeCode);
		formVo.setDateFrom(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateFrom()));
		formVo.setDateTo(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateTo()));
		List<Int065Vo> list = iaWithdrawalDao.findAll(formVo);

		
		 // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Employee");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        for(Int065Vo employee: list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0)
                    .setCellValue(employee.getAmount());

            row.createCell(1)
                    .setCellValue(employee.getBankName());
            row.createCell(2)
            .setCellValue(employee.getBudgetType());
            row.createCell(3)
            .setCellValue(employee.getPayee());
            row.createCell(4)
            .setCellValue(employee.getRefPayment());
            row.createCell(5)
            .setCellValue(employee.getItemDesc());
            row.createCell(6)
            .setCellValue(employee.getPaymentDate());

//            Cell dateOfBirthCell = row.createCell(2);
//            dateOfBirthCell.setCellValue(new Date());
//            dateOfBirthCell.setCellStyle(dateCellStyle);

           
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("D://poi-generated-file.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    
		
	
	}

}

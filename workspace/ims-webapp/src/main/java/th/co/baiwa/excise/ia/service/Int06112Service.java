package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.IaConstant;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int06112ExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06112FormVo;

@Service
public class Int06112Service {

    public DataTableAjax<Int06112ExcelVo> readFileExcel(Int06112FormVo formVo) throws IOException, EncryptedDocumentException, InvalidFormatException {

        byte[] byt = formVo.getFileName().getBytes();
        Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(byt));
        Sheet sheet = workbook.getSheetAt(0);
        int totalRows = sheet.getLastRowNum();

        /* row */
        boolean newObjec = false;
        boolean addData = false;
        int id = 0;
        List<Int06112ExcelVo> voList = new ArrayList<>();
        Int06112ExcelVo vo = new Int06112ExcelVo();

        for (int r = 0; r < totalRows; r++) {

            /*new data*/
            if (newObjec) {
                newObjec = false;
                vo = new Int06112ExcelVo();
            }

            Row row = sheet.getRow(r);
            if (row != null) {
                short minColIx = row.getFirstCellNum();

                /* column */
                Cell cell = row.getCell(minColIx);
                if (cell != null) {
                    if (IaConstant.IA_FILE_EXCEL.REVIEW_COST_DATA.ACCOUNT_TEXT.equals(StringUtils.trim(getStringValue(cell)))) {
                        vo.setId(id++);
                        vo.setAccountId(StringUtils.trim(getStringValue(row.getCell(minColIx + 5))));

                        String accountId = vo.getAccountId();
                        String result = accountId.substring(0, 1);
                        if ("5".equals(result)) {
                            addData = true;
                        }
                        vo.setAccountName(StringUtils.trim(getStringValue(row.getCell(minColIx + 7))));

                        continue;
                    }
                    if (IaConstant.IA_FILE_EXCEL.REVIEW_COST_DATA.BALANCE.equals(StringUtils.trim(getStringValue(cell)))) {

                        BigDecimal amount = new BigDecimal(StringUtils.trim(getStringValue(row.getCell(minColIx + 14))));
                        vo.setAmount(amount);
                        if (addData) {
                            voList.add(vo);
                            addData = false;
                        }
                        newObjec = true;
                        continue;
                    }
                }
            }
        }

        DataTableAjax<Int06112ExcelVo> dataTableAjax = new DataTableAjax<>();

        dataTableAjax.setRecordsTotal(Long.valueOf(voList.size()));
        dataTableAjax.setRecordsFiltered(Long.valueOf(voList.size()));
        dataTableAjax.setData(voList);

        return dataTableAjax;
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

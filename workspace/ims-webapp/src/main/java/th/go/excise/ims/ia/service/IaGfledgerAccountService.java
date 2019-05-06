package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitorjbl.xlsx.StreamingReader;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaGfledgerAccount;
import th.go.excise.ims.ia.persistence.repository.IaGfledgerAccountRepository;

@Service
public class IaGfledgerAccountService {

	@Autowired
	private IaGfledgerAccountRepository iaGfledgerAccountRepository;

	private final String[] KEY_FILTER = { "..รหัสหน่วยงาน", "เลขที่บัญชี G/L", "**" };

	public void addDataByExcel2(File file) {
		try {
			List<List<String>> ex = ExcelUtils.readExcel(file);
			for (List<String> list : ex) {
				for (int i = 0; i < list.size(); i++) {
					System.out.print(i + " : " + list.get(i) + "||");
				}
				if (list.get(0) != null) {

				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDataByExcel(File file) throws FileNotFoundException {
		List<IaGfledgerAccount> iaGfledgerAccountList = new ArrayList<>();
		IaGfledgerAccount iaGfledgerAccount = new IaGfledgerAccount();
		InputStream is = new FileInputStream(file);
		Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);
		String valueExc = "";
		for (Sheet sheet : workbook) {

			for (Row r : sheet) {
				iaGfledgerAccount = new IaGfledgerAccount();
				try {

					for (Cell c : r) {
						valueExc = ExcelUtils.getCellValueAsString(c) + " : " + c.getColumnIndex();
						System.out.print(valueExc +"||");
						if ("รหัส".indexOf(ExcelUtils.getCellValueAsString(r.getCell(7) ))== -1 && "*".indexOf(ExcelUtils.getCellValueAsString(r.getCell(7) ))== -1 && "สถานะ:".indexOf(ExcelUtils.getCellValueAsString(r.getCell(7) ))== -1  && StringUtils.isNoneBlank(ExcelUtils.getCellValueAsString(r.getCell(7)))) {
							switch (c.getColumnIndex()) {
							case 2:
								iaGfledgerAccount.setStCode(ExcelUtils.getCellValueAsString(c));
								break;
							case 3:
								iaGfledgerAccount.setDeterminaton(ExcelUtils.getCellValueAsString(c));
								break;
							case 5:
								iaGfledgerAccount.setDocNo(ExcelUtils.getCellValueAsString(c));
								break;
							case 7:
								iaGfledgerAccount.setCode(ExcelUtils.getCellValueAsString(c));
								break;
							case 8:
								iaGfledgerAccount.setType(ExcelUtils.getCellValueAsString(c));
								break;
							case 9:
								iaGfledgerAccount.setDocDate(ExcelUtils.getCellValueAsString(c));
								break;
							case 10:
								iaGfledgerAccount.setPkCode(ExcelUtils.getCellValueAsString(c));
								break;
							case 11:
								iaGfledgerAccount.setCurrAmt(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
								break;
							case 13:
								iaGfledgerAccount.setSourceMoney(ExcelUtils.getCellValueAsString(c));
								break;
							case 14:
								iaGfledgerAccount.setKeyRef3(ExcelUtils.getCellValueAsString(c));
								break;
							case 15:
								iaGfledgerAccount.setDepCode(ExcelUtils.getCellValueAsString(c));
								break;
							case 16:
								iaGfledgerAccount.setPostingDate(ConvertDateUtils.parseStringToDate(ExcelUtils.getCellValueAsString(c), ConvertDateUtils.DD_MM_YYYY_DOT));
								break;
							case 17:
								iaGfledgerAccount.setYearMonth(ExcelUtils.getCellValueAsString(c) != null ? ExcelUtils.getCellValueAsString(c).replace("/", "") : null);
								break;
							case 18:
								iaGfledgerAccount.setTaxAmt(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
								break;
							case 19:
								iaGfledgerAccount.setTaxExrmptAmt(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
								break;
							case 20:
								iaGfledgerAccount.setRefCode(ExcelUtils.getCellValueAsString(c));
								break;
							case 21:
								iaGfledgerAccount.setGlAcc(ExcelUtils.getCellValueAsString(c));
								break;
							case 22:
								iaGfledgerAccount.setForwardClearingList(ExcelUtils.getCellValueAsString(c));
								break;
							case 23:
								iaGfledgerAccount.setClgI(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
								break;
							case 24:
								iaGfledgerAccount.setBudgetCode(ExcelUtils.getCellValueAsString(c));
								break;
							case 25:
								iaGfledgerAccount.setKeyRef1(ExcelUtils.getCellValueAsString(c));
								break;
							case 26:
								iaGfledgerAccount.setKeyRef2(ExcelUtils.getCellValueAsString(c));
								break;
							case 27:
								iaGfledgerAccount.setDepositAcc(ExcelUtils.getCellValueAsString(c));
								break;
							case 28:
								iaGfledgerAccount.setSubAcc(ExcelUtils.getCellValueAsString(c));
								break;
							case 29:
								iaGfledgerAccount.setDepositName(ExcelUtils.getCellValueAsString(c));
								break;
							case 30:
								iaGfledgerAccount.setAccOwn(ExcelUtils.getCellValueAsString(c));
								break;
							case 31:
								iaGfledgerAccount.setDocHeaderMsg(ExcelUtils.getCellValueAsString(c));
								break;
							case 32:
								iaGfledgerAccount.setTxCode(ExcelUtils.getCellValueAsString(c));
								break;
							case 33:
								iaGfledgerAccount.setClrngDoc(ExcelUtils.getCellValueAsString(c));
								break;

							default:
								break;
							}
						}
					}
					System.out.println("");
					
					if(StringUtils.isNoneBlank(iaGfledgerAccount.getDocNo())) {
						iaGfledgerAccountList.add(iaGfledgerAccount);
						System.out.println(" iaGfledgerAccountList .size : " + iaGfledgerAccountList.size());
					}

				} catch (Exception e) {
					
				}
			}
		}
		try {
			iaGfledgerAccountRepository.insertBatch(iaGfledgerAccountList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

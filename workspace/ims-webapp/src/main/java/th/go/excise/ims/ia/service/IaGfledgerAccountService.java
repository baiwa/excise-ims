package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
						valueExc = ExcelUtils.getCellValueAsString(c);
						switch (c.getColumnIndex()) {
						case 0:
							iaGfledgerAccount.setStCode(ExcelUtils.getCellValueAsString(c));
							break;
						case 1:
							iaGfledgerAccount.setDeterminaton(ExcelUtils.getCellValueAsString(c));
							break;
						case 2:
							iaGfledgerAccount.setDocNo(ExcelUtils.getCellValueAsString(c));
							break;
						case 3:
							iaGfledgerAccount.setCode(ExcelUtils.getCellValueAsString(c));
							break;
						case 4:
							iaGfledgerAccount.setType(ExcelUtils.getCellValueAsString(c));
							break;
						case 5:
							iaGfledgerAccount.setDocDate(ExcelUtils.getCellValueAsString(c));
							break;
						case 6:
							iaGfledgerAccount.setPkCode(ExcelUtils.getCellValueAsString(c));
							break;
						case 7:
							iaGfledgerAccount.setCurrAmt(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
							break;
						case 8:
							iaGfledgerAccount.setSourceMoney(ExcelUtils.getCellValueAsString(c));
							break;
						case 9:
							iaGfledgerAccount.setKeyRef3(ExcelUtils.getCellValueAsString(c));
							break;
						case 10:
							iaGfledgerAccount.setDepCode(ExcelUtils.getCellValueAsString(c));
							break;
						case 11:
							iaGfledgerAccount.setPostingDate(ConvertDateUtils.parseStringToDate(ExcelUtils.getCellValueAsString(c), ConvertDateUtils.DD_MM_YYYY_DOT));
							break;
						case 12:
							iaGfledgerAccount.setYearMonth(ExcelUtils.getCellValueAsString(c));
							break;
						case 13:
							iaGfledgerAccount.setTaxAmt(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
							break;
						case 14:
							iaGfledgerAccount.setTaxExrmptAmt(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
							break;
						case 15:
							iaGfledgerAccount.setRefCode(ExcelUtils.getCellValueAsString(c));
							break;
						case 16:
							iaGfledgerAccount.setGlAcc(ExcelUtils.getCellValueAsString(c));
							break;
						case 17:
							iaGfledgerAccount.setForwardClearingList(ExcelUtils.getCellValueAsString(c));
							break;
						case 18:
							iaGfledgerAccount.setClgI(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
							break;
						case 19:
							iaGfledgerAccount.setBudgetCode(ExcelUtils.getCellValueAsString(c));
							break;
						case 20:
							iaGfledgerAccount.setKeyRef1(ExcelUtils.getCellValueAsString(c));
							break;
						case 21:
							iaGfledgerAccount.setKeyRef2(ExcelUtils.getCellValueAsString(c));
							break;
						case 22:
							iaGfledgerAccount.setDepositAcc(ExcelUtils.getCellValueAsString(c));
							break;
						case 23:
							iaGfledgerAccount.setSubAcc(ExcelUtils.getCellValueAsString(c));
							break;
						case 24:
							iaGfledgerAccount.setDepositName(ExcelUtils.getCellValueAsString(c));
							break;
						case 25:
							iaGfledgerAccount.setAccOwn(ExcelUtils.getCellValueAsString(c));
							break;
						case 26:
							iaGfledgerAccount.setDocHeaderMsg(ExcelUtils.getCellValueAsString(c));
							break;
						case 27:
							iaGfledgerAccount.setTxCode(ExcelUtils.getCellValueAsString(c));
							break;
						case 28:
							iaGfledgerAccount.setClrngDoc(ExcelUtils.getCellValueAsString(c));
							break;

						default:
							break;
						}

					}
					iaGfledgerAccountList.add(iaGfledgerAccount);

				} catch (Exception e) {
					System.out.println("######################################################");
					System.out.println("######################################################");
					System.out.println("######################################################");
					System.out.println("######################################################");
					System.out.println(valueExc);
					e.printStackTrace();
				}
			}
		}
		iaGfledgerAccountRepository.insertBatch(iaGfledgerAccountList);
	}
}

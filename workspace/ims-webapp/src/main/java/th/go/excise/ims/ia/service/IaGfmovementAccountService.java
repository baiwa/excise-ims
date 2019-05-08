package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.monitorjbl.xlsx.StreamingReader;

import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaGfmovementAccount;
import th.go.excise.ims.ia.persistence.repository.IaGfmovementAccountRepository;

public class IaGfmovementAccountService {

	@Autowired
	private IaGfmovementAccountRepository iaGfmovementAccountRepository;

	public final String KEY_FILTER[] = { "บัญชีแยกประเภท", "บัญชีเงินฝาก", "รายงานแสดงการเคลื่อนไหวเงินฝากกระทรวงการคลัง"
			, "Program name  :"
			, "User name     :"  
			, "ตั้งแต่"};

	public void addDataByExcel(File file) throws FileNotFoundException {
		List<IaGfmovementAccount> iaGfmovementAccountList = new ArrayList<>();
		IaGfmovementAccount iaGfmovementAccount = new IaGfmovementAccount();
		InputStream is = new FileInputStream(file);
		Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);
		String valueExc = "";
		for (Sheet sheet : workbook) {
			String accTypeNo = "";
			String accTypeName = "";
			String accNo = "";
			Date docDate = null;
			for (Row r : sheet) {
				iaGfmovementAccount = new IaGfmovementAccount();
				try {

					for (Cell c : r) {
						valueExc = "'" + ExcelUtils.getCellValueAsString(c) + "':" + c.getColumnIndex();
						String lineData = ExcelUtils.getCellValueAsString(c);
						if (c.getColumnIndex() == 0 && KEY_FILTER[0].equals(lineData.split(":")[0].trim())) {
							String data[] = lineData.split(":")[1].trim().split(" ");
							accTypeNo = data[0];
							accTypeName = data[1];
						} else if (c.getColumnIndex() == 3 && KEY_FILTER[1].equals(lineData.split(":")[0].trim())) {
							accNo = lineData.split(":")[1].trim().split(" ")[0];
						} else {
							
							if (!(KEY_FILTER[2].equals(ExcelUtils.getCellValueAsString(r.getCell(9)))) 
									&& !( KEY_FILTER[3].equals(ExcelUtils.getCellValueAsString(r.getCell(0))) )
									&&! (KEY_FILTER[4].equals(ExcelUtils.getCellValueAsString(r.getCell(0))))
 									&&!( r.getCell(0) != null  ? ExcelUtils.getCellValueAsString(r.getCell(0)).indexOf(KEY_FILTER[5]) >-1 : false
									)) {
								System.out.print(valueExc + "||");
							}else {
//								System.out.print(valueExc + "#");
//								
//							
//							switch (c.getColumnIndex()) {
//							case 2:
//								iaGfmovementAccount.setStCode(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 3:
//								iaGfmovementAccount.setDeterminaton(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 5:
//								iaGfmovementAccount.setDocNo(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 7:
//								iaGfmovementAccount.setCode(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 8:
//								iaGfmovementAccount.setType(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 9:
//								iaGfmovementAccount.setDocDate(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 10:
//								iaGfmovementAccount.setPkCode(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 11:
//								iaGfmovementAccount.setCurrAmt(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
//								break;
//							case 13:
//								iaGfmovementAccount.setSourceMoney(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 14:
//								iaGfmovementAccount.setKeyRef3(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 15:
//								iaGfmovementAccount.setDepCode(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 16:
//								iaGfmovementAccount.setPostingDate(ConvertDateUtils.parseStringToDate(ExcelUtils.getCellValueAsString(c), ConvertDateUtils.DD_MM_YYYY_DOT));
//								break;
//							case 17:
//								iaGfmovementAccount.setYearMonth(ExcelUtils.getCellValueAsString(c) != null ? ExcelUtils.getCellValueAsString(c).replace("/", "") : null);
//								break;
//							case 18:
//								iaGfmovementAccount.setTaxAmt(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
//								break;
//							case 19:
//								iaGfmovementAccount.setTaxExrmptAmt(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
//								break;
//							case 20:
//								iaGfmovementAccount.setRefCode(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 21:
//								iaGfmovementAccount.setGlAcc(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 22:
//								iaGfmovementAccount.setForwardClearingList(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 23:
//								iaGfmovementAccount.setClgI(NumberUtils.toBigDecimal(ExcelUtils.getCellValueAsString(c)));
//								break;
//							case 24:
//								iaGfmovementAccount.setBudgetCode(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 25:
//								iaGfmovementAccount.setKeyRef1(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 26:
//								iaGfmovementAccount.setKeyRef2(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 27:
//								iaGfmovementAccount.setDepositAcc(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 28:
//								iaGfmovementAccount.setSubAcc(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 29:
//								iaGfmovementAccount.setDepositName(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 30:
//								iaGfmovementAccount.setAccOwn(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 31:
//								iaGfmovementAccount.setDocHeaderMsg(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 32:
//								iaGfmovementAccount.setTxCode(ExcelUtils.getCellValueAsString(c));
//								break;
//							case 33:
//								iaGfmovementAccount.setClrngDoc(ExcelUtils.getCellValueAsString(c));
//								break;
//
//							default:
//								break;
//							}
							}
						}

					}
					System.out.println("");

				} catch (Exception e) {
//					System.out.print(valueExc + "|err|");
//					e.printStackTrace();
				}
			}
		}
		try {
//			iaGfmovementAccountRepository.insertBatch(iaGfmovementAccountList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class ProductPaperServiceTest {

	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";

	/*-----TOPIC------*/
	private static final String FINISHED_GOODS_PAYMENT = "finished_goods_payment";
	private static final String RAW_MATERIAL_TAX_BREAK = "raw_material_tax_break";
	private static final String UNIT_PRICE = "unit_price";
	private static final String CHECK_PRICE = "check_price";
	private static final String PAY_FOREIGN_FINISHED_GOODS = "pay_foreign_finished_goods";
	private static final String TAX = "tax";

/*	ProductPaperService createPaperProductService = new ProductPaperService();

	@Test
	public void test_finishedGoodsPayment() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + FINISHED_GOODS_PAYMENT + TYPE)) {
			byte[] outArray = createPaperProductService.exportFinishedGoodsPayment();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + FINISHED_GOODS_PAYMENT + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(FINISHED_GOODS_PAYMENT + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}

	@Test
	public void test_rawMaterialTaxBreak() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + RAW_MATERIAL_TAX_BREAK + TYPE)) {
			byte[] outArray = createPaperProductService.exportRawMaterialTaxBreak();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + RAW_MATERIAL_TAX_BREAK + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(RAW_MATERIAL_TAX_BREAK + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}

	@Test
	public void test_unitPrice() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + UNIT_PRICE + TYPE)) {
			byte[] outArray = createPaperProductService.exportUnitPrice();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + UNIT_PRICE + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(UNIT_PRICE + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}

	@Test
	public void test_checkPrice() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + CHECK_PRICE + TYPE)) {
			byte[] outArray = createPaperProductService.exportCheckPrice();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + CHECK_PRICE + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(CHECK_PRICE + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}

	@Test
	public void test_payForeignFinishedGoods() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + PAY_FOREIGN_FINISHED_GOODS + TYPE)) {
			byte[] outArray = createPaperProductService.exportPayForeignFinishedGoods();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + PAY_FOREIGN_FINISHED_GOODS + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(PAY_FOREIGN_FINISHED_GOODS + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}

	@Test
	public void test_tax() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + TAX + TYPE)) {
			byte[] outArray = createPaperProductService.exportTax();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + TAX + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(TAX + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}*/

}

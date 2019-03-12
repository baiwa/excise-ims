package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class ProductPaperBalanceMaterialServiceTest {
	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";

	private static final String PRODUCT_PAPER_BALANCE_MATERIAL = "product_paper_balance_material";

	ProductPaperBalanceMaterialService productPaperBalanceMaterialService = new ProductPaperBalanceMaterialService();

	@Test
	public void test_exportProductPaperBalanceMaterial() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + PRODUCT_PAPER_BALANCE_MATERIAL + TYPE)) {
			byte[] outArray = productPaperBalanceMaterialService.exportProductPaperBalanceMaterial();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + PRODUCT_PAPER_BALANCE_MATERIAL + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out
					.println(PRODUCT_PAPER_BALANCE_MATERIAL + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}
}

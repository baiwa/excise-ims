package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class ProductPaperInputGoodsServiceTest {
	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";

	private static final String PRODUCT_PAPER_INPUT_GOODS = "product_paper_input_goods";

	ProductPaperInputGoodsService paperInputGoodsService = new ProductPaperInputGoodsService();

	@Test
	public void test_exportProductPaperInputGoods() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + PRODUCT_PAPER_INPUT_GOODS + TYPE)) {
			byte[] outArray = paperInputGoodsService.exportProductPaperInputGoods();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + PRODUCT_PAPER_INPUT_GOODS + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(PRODUCT_PAPER_INPUT_GOODS + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}
}

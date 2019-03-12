package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class ProductPaperRelationProducedGoodsServiceTest {
	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";

	private static final String PRODUCT_PAPER_RELATION_PRODUCED_GOODS = "product_paper_relation_produced_goods";

	ProductPaperRelationProducedGoodsService productPaperRelationProducedGoodsService = new ProductPaperRelationProducedGoodsService();

	@Test
	public void test_exportProductPaperRelationProducedGoods() {
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + PRODUCT_PAPER_RELATION_PRODUCED_GOODS + TYPE)) {
			byte[] outArray = productPaperRelationProducedGoodsService.exportProductPaperRelationProducedGoods();
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + PRODUCT_PAPER_RELATION_PRODUCED_GOODS + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(
					PRODUCT_PAPER_RELATION_PRODUCED_GOODS + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}
}

package taxaudit;


import co.th.ims.taxaudit.service.TaxOperatorService;
import co.th.ims.taxaudit.vo.TaxOperatorVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxAuditTest {

    @Autowired
    private TaxOperatorService taxOperatorService;

    @Test
    public void getOperator(){
        TaxOperatorVo.TaxOperatorFormVo formVo = new TaxOperatorVo.TaxOperatorFormVo();

    }
}

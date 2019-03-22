package th.go.excise.ims.ta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import th.co.baiwa.buckwaframework.common.bean.ReportJsonBean;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaFormTS01101Vo;
import th.go.excise.ims.ta.vo.TaFormTS0113Vo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS01113ServiceTest {

    @Autowired
    private TaFormTS0113Service taFormTS0113Service;

    private static final String PATH = "/tmp/";
    private static final String NAME = "TaFormTS01_13.pdf";

    @Test
    public void test_exportTaFormTS01101() throws Throwable, IOException {

        TaFormTS0113Vo data = new TaFormTS0113Vo();

        data.setAuditDate("04/01/2562");
        data.setAuditFinishTime("09:00");
        data.setDocDate("03/02/2562");
        data.setDocPlace("กรมสรรพสามิต");
        data.setDocTime("09:00");
        data.setFacAddrNo("45");
        data.setFacAmphurName("คลองเตย");
        data.setFacProvinceName("กรุงเทพมหานคร");
        data.setFacSoiName("อุดมเกียรติ");
        data.setFacTambolName("คลองเตย");
        data.setFacThnName("ประชาชื่น");
        data.setFacZipCode("10800");
        data.setFactoryName("บริษัท สปอย จำกัด ");
        data.setFactoryName2("บริษัท สปอย จำกัด");
        data.setFactoryType("1");
        data.setFormTsNumber("test");
        data.setHeadOfficerFullName("ธีรวุฒิ กุลฤทธิชัย");
        data.setHeadOfficerPosition("สำนักงานสรรพสามิตส่วนกลาง");
        data.setNewRegId("01005150424621002");
        data.setOwnerFullName("ธีรวุฒิ กุลฤทธิชัย");
        data.setOwnerPosition("สำนักงานสรรพสามิตส่วนกลาง");
        data.setRefBookDate("01/05/2562");
        data.setRefBookNumber1("test");
        data.setSignOfficerFullName("test");
        data.setSignOwnerFullName("ธีรวุฒิ กุลฤทธิชัย");
        data.setSignWitnessFullName1("test");
        data.setSignWitnessFullName2("test");

        ObjectMapper Obj = new ObjectMapper();
        String strJson = Obj.writeValueAsString(data);


        ReportJsonBean reportJsonBean = new ReportJsonBean();
        reportJsonBean.setJson(strJson);
        byte[] reportFile = taFormTS0113Service.exportTaFormTS0113(data);
        IOUtils.write(reportFile, new FileOutputStream(new File(PATH + NAME)));
    }
}

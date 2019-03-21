package th.go.excise.ims.ws.client.pcc.regfri4000.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegFri4000Request;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegFri4000Response;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegMaster60List;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.ResponseData;

@Service
public class RegFri4000Service {

	@Value("${ws.excise.endpointRegFri4000}")
	private String endpoint;

	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;

	public List<RegMaster60List> postRestFul(RegFri4000Request regFri4000Request) throws IOException {
		String json = pccRequestHeaderService.postRestful(endpoint, regFri4000Request);
		Gson gson = new Gson();
		RegFri4000Response pccResponseHeader = gson.fromJson(json, RegFri4000Response.class);
		List<RegMaster60List> regMaster60Lists = new ArrayList<>();
		ResponseData regFri4000Response = (ResponseData) pccResponseHeader.getResponseData();
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			regMaster60Lists = regFri4000Response.getRegMaster60List();
		}
		return regMaster60Lists;
	}

 public List<RegMaster60List> getDetailsOperator(WsReg4000FormVo wsReg4000FormVo) throws IOException {
        RegFri4000Request regFri4000Request = new RegFri4000Request();
        regFri4000Request.setType("1");
        regFri4000Request.setNid("");
        regFri4000Request.setNewregId(wsReg4000FormVo.getNewregId());
        regFri4000Request.setActive("1");
        regFri4000Request.setPageNo("1");
        regFri4000Request.setDataPerPage("10");

        List<RegMaster60List> datas = postRestFul(regFri4000Request);
        for (RegMaster60List data : datas) {

            //==> Customer Address
            String cusAddress = "";
            if (checkAddressNotEmpty(data.getCusAddrno())) {
                cusAddress += data.getCusAddrno();
            }
            if (checkAddressNotEmpty(data.getCusMoono())) {
                cusAddress += " หมู่ที่ " + data.getCusMoono();
            }
            if (checkAddressNotEmpty(data.getCusVillage())) {
                cusAddress += " " + data.getCusVillage();
            }
            if (checkAddressNotEmpty(data.getCusSoiname())) {
                cusAddress += " ซ. " + data.getCusSoiname();
            }
            if (checkAddressNotEmpty(data.getCusThnname())) {
                cusAddress += " ถ. " + data.getCusThnname();
            }
            if (checkAddressNotEmpty(data.getCusTambolname())) {
                cusAddress += " ต. " + data.getCusTambolname();
            }
            if (checkAddressNotEmpty(data.getCusAmphurname())) {
                cusAddress += " อ. " + data.getCusAmphurname();
            }
            if (checkAddressNotEmpty(data.getCusProvincename())) {
                cusAddress += " จ. " + data.getCusProvincename();
            }
            if (checkAddressNotEmpty(data.getCusZipcode())) {
                cusAddress += " " + data.getCusZipcode();
            }

            //==> Fac Address
            String facAddress = "";
            if (checkAddressNotEmpty(data.getFacAddrno())) {
                facAddress += data.getFacAddrno();
            }
            if (checkAddressNotEmpty(data.getFacMoono())) {
                facAddress += " หมู่ที่ " + data.getFacMoono();
            }
            if (checkAddressNotEmpty(data.getFacVillage())) {
                facAddress += " " + data.getFacVillage();
            }
            if (checkAddressNotEmpty(data.getFacSoiname())) {
                facAddress += " ซ. " + data.getFacSoiname();
            }
            if (checkAddressNotEmpty(data.getFacThnname())) {
                facAddress += " ถ. " + data.getFacThnname();
            }
            if (checkAddressNotEmpty(data.getFacTambolname())) {
                facAddress += " ต. " + data.getFacTambolname();
            }
            if (checkAddressNotEmpty(data.getFacAmphurname())) {
                facAddress += " อ. " + data.getFacAmphurname();
            }
            if (checkAddressNotEmpty(data.getFacProvincename())) {
                facAddress += " จ. " + data.getFacProvincename();
            }
            if (checkAddressNotEmpty(data.getFacZipcode())) {
                facAddress += " " + data.getFacZipcode();
            }

            data.setCustomerAddress(cusAddress);
            data.setFacAddress(facAddress);
        }
        return datas;
    }

    private Boolean checkAddressNotEmpty(String value) {
        return StringUtils.isNotBlank(value) && !"-".equals(value);
    }

}

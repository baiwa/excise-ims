package th.go.excise.ims.ws.client.pcc.regfri4000.service;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import th.go.excise.ims.ws.client.pcc.common.PccServiceProperties;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.common.model.PccResponseHeader;
import th.go.excise.ims.ws.client.pcc.common.service.AbstractPccRestfulService;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RequestData;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@Service
public class RegFri4000Service extends AbstractPccRestfulService<RequestData, ResponseData> {

	@Autowired
	public RegFri4000Service(
			@Value("${ws.excise.endpoint.reg.regfri4000}") String url,
			PccServiceProperties pccServicePrpperties,
			RestfulClientService restfulClientService,
			Gson gson) {
		super.url = url;
		super.pccServicePrpperties = pccServicePrpperties;
		super.restfulClientService = restfulClientService;
		super.gson = gson;
	}

	@Override
	public ResponseData execute(RequestData requestData) throws PccRestfulException {
		return executePost(requestData);
	}

	@Override
	protected Type getResponseDataType() {
		return new TypeToken<PccResponseHeader<ResponseData>>(){}.getType();
	}
	
	// FIXME
//    public List<RegMaster60List> getDetailsOperator(WsReg4000FormVo wsReg4000FormVo) throws IOException {
//        RegFri4000Request regFri4000Request = new RegFri4000Request();
//        regFri4000Request.setType("1");
//        regFri4000Request.setNid("");
//        regFri4000Request.setNewregId(wsReg4000FormVo.getNewRegId());
//        regFri4000Request.setActive("1");
//        regFri4000Request.setPageNo("1");
//        regFri4000Request.setDataPerPage("10");
//
//        List<RegMaster60List> datas = postRestFul(regFri4000Request);
//        for (RegMaster60List data : datas) {
//
//            //==> Customer Address
//            String cusAddress = "";
//            if (checkAddressNotEmpty(data.getCusAddrno())) {
//                cusAddress += data.getCusAddrno();
//            }
//            if (checkAddressNotEmpty(data.getCusMoono())) {
//                cusAddress += " หมู่ที่ " + data.getCusMoono();
//            }
//            if (checkAddressNotEmpty(data.getCusVillage())) {
//                cusAddress += " " + data.getCusVillage();
//            }
//            if (checkAddressNotEmpty(data.getCusSoiname())) {
//                cusAddress += " ซ. " + data.getCusSoiname();
//            }
//            if (checkAddressNotEmpty(data.getCusThnname())) {
//                cusAddress += " ถ. " + data.getCusThnname();
//            }
//            if (checkAddressNotEmpty(data.getCusTambolname())) {
//                cusAddress += " ต. " + data.getCusTambolname();
//            }
//            if (checkAddressNotEmpty(data.getCusAmphurname())) {
//                cusAddress += " อ. " + data.getCusAmphurname();
//            }
//            if (checkAddressNotEmpty(data.getCusProvincename())) {
//                cusAddress += " จ. " + data.getCusProvincename();
//            }
//            if (checkAddressNotEmpty(data.getCusZipcode())) {
//                cusAddress += " " + data.getCusZipcode();
//            }
//
//            //==> Fac Address
//            String facAddress = "";
//            if (checkAddressNotEmpty(data.getFacAddrno())) {
//                facAddress += data.getFacAddrno();
//            }
//            if (checkAddressNotEmpty(data.getFacMoono())) {
//                facAddress += " หมู่ที่ " + data.getFacMoono();
//            }
//            if (checkAddressNotEmpty(data.getFacVillage())) {
//                facAddress += " " + data.getFacVillage();
//            }
//            if (checkAddressNotEmpty(data.getFacSoiname())) {
//                facAddress += " ซ. " + data.getFacSoiname();
//            }
//            if (checkAddressNotEmpty(data.getFacThnname())) {
//                facAddress += " ถ. " + data.getFacThnname();
//            }
//            if (checkAddressNotEmpty(data.getFacTambolname())) {
//                facAddress += " ต. " + data.getFacTambolname();
//            }
//            if (checkAddressNotEmpty(data.getFacAmphurname())) {
//                facAddress += " อ. " + data.getFacAmphurname();
//            }
//            if (checkAddressNotEmpty(data.getFacProvincename())) {
//                facAddress += " จ. " + data.getFacProvincename();
//            }
//            if (checkAddressNotEmpty(data.getFacZipcode())) {
//                facAddress += " " + data.getFacZipcode();
//            }
//
//            data.setCustomerAddress(cusAddress);
//            data.setFacAddress(facAddress);
//        }
//        return datas;
//    }
//
//    private Boolean checkAddressNotEmpty(String value) {
//        return StringUtils.isNotBlank(value) && !"-".equals(value);
//    }

}

package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.DataEntry;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.MaterialData;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.ProductData;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.RequestData;
import th.go.excise.ims.ws.client.pcc.oasfri0100.model.ResponseData2;
import th.go.excise.ims.ws.client.pcc.oasfri0100.service.OasFri0100Service;
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100D1;
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100D2;
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100H;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100D1Repository;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100D2Repository;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100HRepository;

@Service
public class SyncWsOasfri0100Service {
	
	private static final Logger logger = LoggerFactory.getLogger(SyncWsOasfri0100Service.class);
	
	@Autowired
	private OasFri0100Service oasFri0100Service;
	
	@Autowired
	private WsOasfri0100HRepository wsOasfri0100HRepository;
	
	@Autowired
	private WsOasfri0100D1Repository wsOasfri0100D1Repository;
	
	@Autowired
	private WsOasfri0100D2Repository wsOasfri0100D2Repository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(String newRegId, LocalDate localDate) throws PccRestfulException {
		logger.info("syncData OASFRI0100 Start");
		long start = System.currentTimeMillis();
		
		RequestData requestData = null;
		ResponseData2 responseData = null;
		ThaiBuddhistDate thaiDate = null;
		String taxYear = null;
		String taxMonth = null;
		WsOasfri0100H oasfri0100H = null;
		List<WsOasfri0100H> oasfri0100HList = new ArrayList<>();
		WsOasfri0100D1 oasfri0100D1 = null;
		List<WsOasfri0100D1> oasfri0100D1List = new ArrayList<>();
		WsOasfri0100D2 oasfri0100D2 = null;
		List<WsOasfri0100D2> oasfri0100D2List = new ArrayList<>();
		
		thaiDate = ThaiBuddhistDate.from(localDate);
		taxYear = String.valueOf(thaiDate.get(ChronoField.YEAR));
		taxMonth = String.valueOf(thaiDate.get(ChronoField.MONTH_OF_YEAR));
		
		requestData = new RequestData();
		requestData.setRegId(newRegId);
		requestData.setTaxYear(taxYear);
		requestData.setTaxMonth(taxMonth);
		responseData = oasFri0100Service.execute(requestData);
		
		// Header - Form
		oasfri0100H = new WsOasfri0100H();
		oasfri0100H.setNewRegId(newRegId);
		oasfri0100H.setTaxYear(taxYear);
		oasfri0100H.setTaxMonth(taxMonth);
		oasfri0100H.setFormdocRec0142No(responseData.getFormDoc().getRec0142No());
		oasfri0100H.setFormdocRec0142Date(StringUtils.isNotEmpty(responseData.getFormDoc().getRec0142Date()) ? LocalDate.parse(responseData.getFormDoc().getRec0142Date().substring(0, 10), DateTimeFormatter.ISO_DATE) : null);
		oasfri0100H.setFormdocRec0142By(responseData.getFormDoc().getRec0142By());
		oasfri0100H.setRcvdocSignBy(responseData.getReceiveDoc().getSignBy());
		oasfri0100H.setRcvdocSignDate(StringUtils.isNotEmpty(responseData.getReceiveDoc().getSignDate()) ? LocalDate.parse(responseData.getReceiveDoc().getSignDate().substring(0, 10), DateTimeFormatter.ISO_DATE) : null);
		oasfri0100H.setCreatedBy(SYSTEM_USER.BATCH);
		oasfri0100H.setCreatedDate(LocalDateTime.now());
		oasfri0100HList.add(oasfri0100H);
		// Material
		for (MaterialData materialData : responseData.getMaterialData()) {
			oasfri0100D1 = new WsOasfri0100D1();
			oasfri0100D1.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL_CODE);
			oasfri0100D1.setFormdocRec0142No(responseData.getFormDoc().getRec0142No());
			oasfri0100D1.setDataSeq(materialData.getMaterialSeq());
			oasfri0100D1.setDataId(materialData.getMaterialId());
			oasfri0100D1.setDataName(materialData.getMaterialName());
			oasfri0100D1.setBalBfQty(materialData.getBalanceBfQty());
			oasfri0100D1.setCreatedBy(SYSTEM_USER.BATCH);
			oasfri0100D1.setCreatedDate(LocalDateTime.now());
			oasfri0100D1List.add(oasfri0100D1);
			// MaterialEntry
			for (DataEntry materialEntry : materialData.getMaterialEntry()) {
				oasfri0100D2 = new WsOasfri0100D2();
				oasfri0100D2.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL_CODE);
				oasfri0100D2.setFormdocRec0142No(responseData.getFormDoc().getRec0142No());
				oasfri0100D2.setDataId(materialData.getMaterialId());
				oasfri0100D2.setSeqNo(materialEntry.getSeqNo());
				oasfri0100D2.setAccountName(materialEntry.getAccountName());
				oasfri0100D2.setInQty(materialEntry.getInQty());
				oasfri0100D2.setCreatedBy(SYSTEM_USER.BATCH);
				oasfri0100D2.setCreatedDate(LocalDateTime.now());
				oasfri0100D2List.add(oasfri0100D2);
			}
		}
		// Product
		for (ProductData productData : responseData.getProductData()) {
			oasfri0100D1 = new WsOasfri0100D1();
			oasfri0100D1.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_PRODUCT_CODE);
			oasfri0100D1.setFormdocRec0142No(responseData.getFormDoc().getRec0142No());
			oasfri0100D1.setDataSeq(productData.getProductSeq());
			oasfri0100D1.setDataId(productData.getProductId());
			oasfri0100D1.setDataName(productData.getProductName());
			oasfri0100D1.setBalBfQty(productData.getBalanceBfQty());
			oasfri0100D1.setCreatedBy(SYSTEM_USER.BATCH);
			oasfri0100D1.setCreatedDate(LocalDateTime.now());
			oasfri0100D1List.add(oasfri0100D1);
			// MaterialEntry
			for (DataEntry productEntry : productData.getProductEntry()) {
				oasfri0100D2 = new WsOasfri0100D2();
				oasfri0100D2.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_PRODUCT_CODE);
				oasfri0100D2.setFormdocRec0142No(responseData.getFormDoc().getRec0142No());
				oasfri0100D2.setDataId(productData.getProductId());
				oasfri0100D2.setSeqNo(productEntry.getSeqNo());
				oasfri0100D2.setAccountName(productEntry.getAccountName());
				oasfri0100D2.setInQty(productEntry.getInQty());
				oasfri0100D2.setCreatedBy(SYSTEM_USER.BATCH);
				oasfri0100D2.setCreatedDate(LocalDateTime.now());
				oasfri0100D2List.add(oasfri0100D2);
			}
		}
		
		wsOasfri0100HRepository.forceDeleteByDocNo(responseData.getFormDoc().getRec0142No());
		wsOasfri0100HRepository.batchInsert(oasfri0100HList);
		logger.info("Batch Merge WS_OASFRI0100_H Success");
		
		wsOasfri0100D1Repository.forceDeleteByDocNo(responseData.getFormDoc().getRec0142No());
		wsOasfri0100D1Repository.batchInsert(oasfri0100D1List);
		logger.info("Batch Merge WS_OASFRI0100_D1 Success");
		
		wsOasfri0100D2Repository.forceDeleteByDocNo(responseData.getFormDoc().getRec0142No());
		wsOasfri0100D2Repository.batchInsert(oasfri0100D2List);
		logger.info("Batch Merge WS_OASFRI0100_D2 Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData OASFRI0100 Success, using {} seconds", (float) (end - start) / 1000F);
	}
	
}

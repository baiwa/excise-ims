package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.repository.OaCustomerLicenRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsDtlRepository;
import th.go.excise.ims.oa.vo.Oa020106DtlVo;

@Service
public class Oa02010609Service {
	
	@Autowired
	private OaLubricantsDtlRepository oaLubricantsDtlRep;
	
	@Autowired
	private OaCustomerLicenRepository oaCustomerLicenRep;
	
	public Oa020106DtlVo findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		OaLubricantsDtl dtl = new OaLubricantsDtl();
		Oa020106DtlVo vo = new Oa020106DtlVo();
		if (oaLubricantsDtlOpt.isPresent()) {
			dtl = oaLubricantsDtlOpt.get();
			vo.setBuyAgentLicense(dtl.getBuyAgentLicense());
			vo.setBuyFromAgent(dtl.getBuyFromAgent());
			vo.setBuyFromImporter(dtl.getBuyFromImporter());
			vo.setBuyFromIndust(dtl.getBuyFromIndust());
			vo.setBuyIndustLicense(dtl.getBuyIndustLicense());
			vo.setBuyOverlimit(dtl.getBuyOverlimit());
			vo.setCustomers(null);
		}
		return vo;
	}
	
	public OaLubricantsDtl updateById(Oa020106DtlVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		OaLubricantsDtl lubricantsDtl = new OaLubricantsDtl();
		if (oaLubricantsDtlOpt.isPresent()) {
			lubricantsDtl = oaLubricantsDtlOpt.get();
			// TODO SET SOMETHING
			lubricantsDtl.setAgentStartDate(request.getAgentStartDate());
			lubricantsDtl.setAgentEndDate(request.getAgentEndDate());
			lubricantsDtl.setAgentOverlimit(request.getAgentOverlimit());
			
			// Buy
			lubricantsDtl.setABuyFromIndust(request.getABuyFromIndust());
			lubricantsDtl.setABuyIndustLicense(request.getABuyIndustLicense());
			lubricantsDtl.setABuyFromAgent(request.getABuyFromAgent());
			lubricantsDtl.setABuyAgentLicense(request.getABuyAgentLicense());
			// Sell
			lubricantsDtl.setASaleToAgent(request.getASaleToAgent());
			lubricantsDtl.setASaleAgentLicense(request.getASaleAgentLicense());
			lubricantsDtl.setASaleToUser(request.getASaleToUser());
			lubricantsDtl.setASaleUserLicense(request.getASaleUserLicense());
			// Sell Method
			lubricantsDtl.setSentToAgent(request.getSentToAgent());
			lubricantsDtl.setSentToUser(request.getSentToUser());
			lubricantsDtl.setAImporterLicense(request.getAImporterLicense());
			
			// TODO SAVE
			lubricantsDtl = oaLubricantsDtlRep.save(lubricantsDtl);
		}
		return lubricantsDtl;
	}
	
}

package th.go.excise.ims.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.build.Plugin.Engine.Summary;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsCompare;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsSummary;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsCompareRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsDtlRepository;
import th.go.excise.ims.oa.persistence.repository.OaLubricantsSummaryRepository;
import th.go.excise.ims.oa.vo.Oa02010604FormVo;

@Service
public class Oa02010604Service {
	
	@Autowired
	private OaLubricantsDtlRepository oaLubricantsDtlRep;
	
	@Autowired
	private OaLubricantsCompareRepository oaLubricantsCompareRepo;
	
	@Autowired
	private OaLubricantsSummaryRepository oaLubricantsSummaryRepository;
	
	public Oa02010604FormVo findDetailById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		List<OaLubricantsCompare> oaLubricantsCom = oaLubricantsCompareRepo.findByOaLubricantsIdAndIsDeleted(id,"N");
		List<OaLubricantsSummary> oaLubricantsSum = oaLubricantsSummaryRepository.findByOaLubricantsIdAndIsDeleted(id,"N");
		
		Oa02010604FormVo lubricats = new Oa02010604FormVo();
		
		if (oaLubricantsCom.size()>0) {
			lubricats.setListLubricantsCompare(oaLubricantsCom);
		}
		if (oaLubricantsSum.size()>0) {
			lubricats.setListOaLubricantsSummary(oaLubricantsSum);
		}
		
		return lubricats;
	}
	
	@Transactional
	public OaLubricantsDtl updateById(Oa02010604FormVo request, String idStr) {
		BigDecimal id = new BigDecimal(idStr);
//		Optional<OaLubricantsDtl> oaLubricantsDtlOpt = oaLubricantsDtlRep.findById(id);
		OaLubricantsDtl lubricantsDtl = new OaLubricantsDtl();
		List<OaLubricantsCompare> listLubricantsCompare = request.getListLubricantsCompare();
		List<OaLubricantsSummary> listOaLubricantsSummary = request.getListOaLubricantsSummary();
		
		
//		oaLubricantsSummaryRepository.deleteAll(listOaLubricantsSummary);
//		oaLubricantsCompareRepo.deleteAll(listLubricantsCompare);
		
		List<OaLubricantsCompare> oaLubricantsCom = oaLubricantsCompareRepo.findByOaLubricantsIdAndIsDeleted(id,"N");
		List<OaLubricantsSummary> oaLubricantsSum = oaLubricantsSummaryRepository.findByOaLubricantsIdAndIsDeleted(id,"N");
		
		for (OaLubricantsSummary summary : oaLubricantsSum) {
			if (summary.getOaLubSumaryId() != null ) {
				oaLubricantsSummaryRepository.deleteById(summary.getOaLubSumaryId());
			}

		}
		
		for (OaLubricantsCompare compaer : oaLubricantsCom) {
			if (compaer.getOaLubCompareId() != null ) {
				oaLubricantsCompareRepo.deleteById(compaer.getOaLubCompareId());
			}
		}
		
//		List<OaLubricantsCompare> oaLubricantsCom = oaLubricantsCompareRepo.findByOaLubricantsId(id);
//		List<OaLubricantsSummary> oaLubricantsSum = oaLubricantsSummaryRepository.findByOaLubricantsId(id);
//		
//		oaLubricantsCompareRepo.deleteByOaLubricantsId(id);
//		oaLubricantsSummaryRepository.deleteByOaLubricantsId(id);
		
//		List<OaLubricantsCompare> listCompare = new ArrayList<>(); 
//		List<OaLubricantsSummary> listSummary = new ArrayList<>(); 
		
//		for (OaLubricantsSummary summary : listOaLubricantsSummary) {
//			if (summary.getOaLubSumaryId()== null){
//				OaLubricantsSummary objSummary = new OaLubricantsSummary();
//				objSummary = summary;
//				oaLubricantsSummaryRepository.save(objSummary);
//			}else {
//				Optional<OaLubricantsSummary> objSummary = oaLubricantsSummaryRepository.findById(summary.getOaLubSumaryId());
//				OaLubricantsSummary lubSummary = objSummary.get();
//				lubSummary.setLubName(summary.getLubName());
//				lubSummary.setMonth(summary.getMonth());
//				lubSummary.setReceive(summary.getReceive());
//				lubSummary.setSending(summary.getSending());
//				lubSummary.setSeq(summary.getSeq());
//				lubSummary.setStockLatsMonth(summary.getStockLatsMonth());
//				lubSummary.setYear(summary.getYear());
//				oaLubricantsSummaryRepository.save(lubSummary);
//			}
//		}
//		
		
		
//		int i = 0;
//		for (OaLubricantsSummary oaLubricantsSummary : oaLubricantsSum) {
//			OaLubricantsSummary  summary = new OaLubricantsSummary();
//			summary.setOaLubSumaryId(oaLubricantsSum.get(i).getOaLubSumaryId());
//			summary.setLubName(oaLubricantsSummary.getLubName());
//			summary.setMonth(oaLubricantsSummary.getMonth());
//			summary.setReceive(oaLubricantsSummary.getReceive());
//			summary.setSending(oaLubricantsSummary.getSending());
//			summary.setSeq(oaLubricantsSummary.getSeq());
//			summary.setStockLatsMonth(oaLubricantsSummary.getStockLatsMonth());
//			summary.setYear(oaLubricantsSummary.getYear());
//			listSummary.add(summary);
//			i++;
//		}
//		i = 0;
//		for (OaLubricantsCompare oaLubricantsCompare : listLubricantsCompare) {
//			OaLubricantsCompare compare = new OaLubricantsCompare();
//			compare.setOaLubCompareId(oaLubricantsCom.get(i).getOaLubCompareId());
//			compare.setAuditDate(oaLubricantsCompare.getAuditDate());
//			compare.setAuditStock(oaLubricantsCompare.getAuditStock());
//			compare.setName(oaLubricantsCompare.getName());
//			compare.setOverRate(oaLubricantsCompare.getOverRate());
//			compare.setRemark(oaLubricantsCompare.getRemark());
//			compare.setSumaryDate(oaLubricantsCompare.getSumaryDate());
//			compare.setSeq(oaLubricantsCompare.getSeq());
//			compare.setSumaryStock(oaLubricantsCompare.getSumaryStock());
//			listCompare.add(compare);
//			i++;
//		}
		
		
		if (listLubricantsCompare.size() > 0) {
			List<OaLubricantsCompare> listLubricantsCompares = (List<OaLubricantsCompare>) oaLubricantsCompareRepo.saveAll(listLubricantsCompare);
		}
		if (listOaLubricantsSummary.size() >0 ) {
			List<OaLubricantsSummary> listOaLubricantsSummarys =   (List<OaLubricantsSummary>) oaLubricantsSummaryRepository.saveAll(listOaLubricantsSummary);
		}
		
		return lubricantsDtl;
	}

}

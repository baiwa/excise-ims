package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.dao.QuestionnaireMainDao;
import th.co.baiwa.excise.ia.persistence.dao.QuestionnaireMinorDao;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMain;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMinor;
import th.co.baiwa.excise.ia.persistence.repository.QuestionnaireMainDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.QuestionnaireMinorDetailRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int023Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int02m31FormVo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class QuestionnaireMainDetailService {
	private Logger logger = LoggerFactory.getLogger(QuestionnaireMainDetailService.class);
	
	@Autowired
	private QuestionnaireMainDetailRepository qtnMainDtlRepository;
	
	@Autowired
	private QuestionnaireMinorDetailRepository qtnMinorDtlRepository;
	
	@Autowired
	private QuestionnaireMainDao qtnMainDao;
	
	@Autowired
	private QuestionnaireMinorDao qtnMinorDao;
	
	public ResponseDataTable<Int023Vo<QuestionnaireMinor>> findByCriteria(DataTableRequest req) {
		ResponseDataTable<Int023Vo<QuestionnaireMinor>> resp = new ResponseDataTable<Int023Vo<QuestionnaireMinor>>();
		List<Int023Vo<QuestionnaireMinor>> int023 = new ArrayList<>();
		List<QuestionnaireMain> main = qtnMainDao.findForInt02m31(req);
		logger.info(" start: {} length: {} mainSize: {}", req.getStart(), req.getLength(), main.size());
		Long oldId = 0L;
		for(QuestionnaireMain ma: main) {
			if ((ma.getQtnMainDetailId() != oldId)) {
				logger.info("{}", oldId);
				oldId = ma.getQtnMainDetailId();
				Int023Vo<QuestionnaireMinor> in023 = new Int023Vo<QuestionnaireMinor>();
				QuestionnaireMinor min = new QuestionnaireMinor();
				List<QuestionnaireMinor> minor = new ArrayList<QuestionnaireMinor>();
				min.setMainId(oldId);
				minor = qtnMinorDao.findByCriteria(min, 0, 0);
				in023.setQtnReportManId(ma.getQtnMainDetailId());
				in023.setQtnMainDetail(ma.getQtnMainDetail());
				in023.setDetail(minor);
				int023.add(in023);
			}
		}
		resp.setData(int023);
		return resp;
	}
	
	public Message saveQtnDetail(List<Int02m31FormVo> listDetail) {
		Message msg;
		msg = ApplicationCache.getMessage("MSG_00003");
		
		QuestionnaireMain m;
		QuestionnaireMinor mi;
		QuestionnaireMain getId = new QuestionnaireMain();
		
		if(listDetail.size() != 0) {
			for (Int02m31FormVo vo : listDetail) {
				if( BeanUtils.isNotEmpty(vo.getHeaderCode()) && (BeanUtils.isNotEmpty(vo.getQtnMainDetail())) ) {
					m = new QuestionnaireMain();
					m.setHeaderCode(vo.getHeaderCode());
					m.setQtnMainDetail(vo.getQtnMainDetail());
					getId = qtnMainDtlRepository.save(m);
					msg = ApplicationCache.getMessage("MSG_00002");
				}
				if( BeanUtils.isNotEmpty(vo.getHeaderCode()) && (BeanUtils.isNotEmpty(vo.getQtnMinorDetail())) ) {
					mi = new QuestionnaireMinor();
					mi.setMainId(getId.getQtnMainDetailId());
					mi.setHeaderCode(vo.getHeaderCode());
					mi.setQtnMinorDetail(vo.getQtnMinorDetail());
					qtnMinorDtlRepository.save(mi);
					msg = ApplicationCache.getMessage("MSG_00002");
				}
			}
		}
		return msg;
	}

	public Message updateQtnDtl(String id, Int02m31FormVo vo) {
		Message msg;
		msg = ApplicationCache.getMessage("MSG_00003");
		
		if(vo.getFlag().equals("M")) {
			QuestionnaireMain dataById = qtnMainDtlRepository.findOne(Long.parseLong(id));
			dataById.setQtnMainDetail(vo.getQtnMainDetail());
			qtnMainDtlRepository.save(dataById);
			msg = ApplicationCache.getMessage("MSG_00002");
		}else {
			QuestionnaireMinor dataById = qtnMinorDtlRepository.findOne(Long.parseLong(id));
			dataById.setQtnMinorDetail(vo.getQtnMinorDetail());
			qtnMinorDtlRepository.save(dataById);
			msg = ApplicationCache.getMessage("MSG_00002");
		}
		return msg;
	}
	
	@Transactional
	public Message deleteMainDtl(String idList) {
		try {
			String[] str = idList.split(",");
			List<Long> id = new ArrayList<>();
			for (String value : str) {
				id.add(Long.valueOf(value));
			}
			qtnMainDtlRepository.delete(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ApplicationCache.getMessage("MSG_00006");
		}
		return ApplicationCache.getMessage("MSG_00005");
	}
	
	@Transactional
	public Message deleteMinorDtl(String idList) {
		try {
			String[] str = idList.split(",");
			List<Long> id = new ArrayList<>();
			for (String value : str) {
				id.add(Long.valueOf(value));
			}
			qtnMinorDtlRepository.delete(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ApplicationCache.getMessage("MSG_00006");
		}
		return ApplicationCache.getMessage("MSG_00005");
	}


}

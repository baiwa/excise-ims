package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.domain.QtnHdrConditionVo;
import th.co.baiwa.excise.domain.QtnMasterVo;
import th.co.baiwa.excise.ia.persistence.entity.Condition;
import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnMaster;
import th.co.baiwa.excise.ia.persistence.repository.qtn.rep.QtnMasterRepository;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@Service
public class QtnMasterService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QtnMasterRepository qtnMasterRepository;

	@Autowired
	private ConditionService conditionService;

	@Autowired
	private WebServiceExciseService webService;
	
	public ResponseDataTable<QtnMaster> findAllQtnMaster() {
		ResponseDataTable<QtnMaster> data = new ResponseDataTable<>();
		int count = (int) qtnMasterRepository.count();
		data.setData(qtnMasterRepository.findAll());
		data.setDraw(count);
		data.setLength(count);
		return data;
	}

	public QtnMaster findByIdQtnMaster(String id) {
		return qtnMasterRepository.findOne(Long.parseLong(id));
	}

	public CommonMessage<QtnMaster> updateQtnMaster(String id, @RequestBody QtnMaster qtnMaster) {
		Message msg;
		CommonMessage<QtnMaster> response = new CommonMessage<>();

		// QtnMaster
		QtnMaster qtnMasterOne = qtnMasterRepository.findOne(Long.parseLong(id));
		qtnMasterOne.setQtnFinished(qtnMaster.getQtnFinished());
		qtnMasterOne.setIsDeleted(qtnMaster.getIsDeleted());

		QtnMaster data = qtnMasterRepository.save(qtnMasterOne);
		if (data != null) {
			response.setData(data);
			if ("Y".equals(data.getQtnFinished()))
				msg = ApplicationCache.getMessage("MSG_00007");
			else
				msg = ApplicationCache.getMessage("MSG_00002");
		} else {
			response.setData(qtnMasterOne);
			if ("Y".equals(qtnMasterOne.getQtnFinished()))
				msg = ApplicationCache.getMessage("MSG_00008");
			else
				msg = ApplicationCache.getMessage("MSG_00003");
		}
		response.setMsg(msg);
		return response;
	}

	public CommonMessage<QtnMaster> saveQtnMaster(QtnMaster qtnMaster) {

		logger.info(qtnMaster.getQtnName());

		Message msg;

		CommonMessage<QtnMaster> response = new CommonMessage<>();
		String code = UserLoginUtils.getCurrentUserBean().getOfficeId(); // 010100
		String user = UserLoginUtils.getCurrentUserBean().getUsername();
		if (BeanUtils.isEmpty(code)) { // Pull UserBean from WebServiceLDAP
			code = webService.webServiceLdap(user, "password").getOfficeCode();
		}
		Date date = new Date();
		String sector = code.substring(0, 2);
		String year = DateConstant.convertDateToStr(date, "yyyy", ExciseConstants.LOCALE.TH);
		List<Lov> lov = ApplicationCache.getListOfValueByValueType("SECTOR_LIST", code);
		qtnMaster.setQtnName(lov.get(0).getSubTypeDescription());
		qtnMaster.setQtnYear(year);
		qtnMaster.setQtnSector(sector);
		qtnMaster.setQtnFinished("N");
		qtnMaster.setCreatedBy(user);
		qtnMaster.setCreatedDate(date);
		qtnMaster.setUpdatedBy(user);
		qtnMaster.setUpdatedDate(date);

		QtnMaster data = qtnMasterRepository.save(qtnMaster);

		if (data != null) {
			msg = ApplicationCache.getMessage("MSG_00002");
		} else {
			msg = ApplicationCache.getMessage("MSG_00003");
		}
		response.setMsg(msg);
		response.setData(data);
		return response;
	}

	public Message deleteQtnMaster(String id) {
		try {
			qtnMasterRepository.delete(Long.parseLong(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ApplicationCache.getMessage("MSG_00006");
		}
		return ApplicationCache.getMessage("MSG_00005");
	}

	public List<QtnMasterVo> calRiskPoint(String budgetYear) {
		List<QtnMasterVo> qtnMasterVoList = new ArrayList<QtnMasterVo>();
		QtnMaster qtnMaster = new QtnMaster();
		qtnMaster.setQtnYear(budgetYear);
		List<QtnHdrConditionVo> qtnHdrConditionVoList = qtnMasterRepository.findRiskNameAndPoint(qtnMaster);
		Map<Long, List<QtnHdrConditionVo>> groupMapping = new HashMap<Long, List<QtnHdrConditionVo>>();
		for (QtnHdrConditionVo qtnHdrConditionVo : qtnHdrConditionVoList) {
			List<QtnHdrConditionVo> objList = groupMapping.get(qtnHdrConditionVo.getQtnMasterId());
			if (BeanUtils.isEmpty(objList)) {
				List<QtnHdrConditionVo> hdrValueList = new ArrayList<QtnHdrConditionVo>();
				hdrValueList.add(qtnHdrConditionVo);
				groupMapping.put(qtnHdrConditionVo.getQtnMasterId(), hdrValueList);
			} else {
				objList.add(qtnHdrConditionVo);
				groupMapping.put(qtnHdrConditionVo.getQtnMasterId(), objList);
			}
		}
		QtnMasterVo init;
		for (Long key : groupMapping.keySet()) {
			init = new QtnMasterVo();
			List<QtnHdrConditionVo> thisMapping = groupMapping.get(key);
			if (BeanUtils.isNotEmpty(thisMapping)) {
				init.setQtnName(thisMapping.get(0).getQtnName());
				List<Condition> conditionHeaderList = conditionService.findConditionByParentId(thisMapping.get(0).getQtnHrdId(), "QTN_HEADER", "int02-3");
				long rlSum = 0;
				if (BeanUtils.isNotEmpty(conditionHeaderList)) {
					for (QtnHdrConditionVo qtnHdrConditionVo : thisMapping) {
						for (Condition condition : conditionHeaderList) {
							long value = qtnHdrConditionVo.getRiskPoint();
							if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
								qtnHdrConditionVo.setRl(condition.getValueRl());
								rlSum += Long.parseLong(condition.getValueRl());
							} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
								qtnHdrConditionVo.setRl(condition.getValueRl());
								rlSum += Long.parseLong(condition.getValueRl());
							} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
								qtnHdrConditionVo.setRl(condition.getValueRl());
								rlSum += Long.parseLong(condition.getValueRl());
							}

						}
					}
				}
				List<Condition> conditionMasterList = conditionService.findConditionByParentId(thisMapping.get(0).getQtnMasterId(), "QTN_MASTER", "int02-2");
				if (BeanUtils.isNotEmpty(conditionMasterList)) {
					for (Condition condition : conditionHeaderList) {
						long value = rlSum;
						if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
							init.setRl(condition.getValueRl());
							init.setColor(condition.getColor());
							init.setValueTranslation(condition.getConvertValue());
						} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
							init.setRl(condition.getValueRl());
							init.setColor(condition.getColor());
							init.setValueTranslation(condition.getConvertValue());
						} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
							init.setRl(condition.getValueRl());
							init.setColor(condition.getColor());
							init.setValueTranslation(condition.getConvertValue());
						}

					}
				}
				init.setRiskPointMaster(rlSum+"");
				init.setQtnHdrConditionVoList(qtnHdrConditionVoList);
			}
			qtnMasterVoList.add(init);

		}

		return qtnMasterVoList;
	}

}

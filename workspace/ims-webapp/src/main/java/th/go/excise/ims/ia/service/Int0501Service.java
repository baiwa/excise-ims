package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmqtH;
import th.go.excise.ims.ia.persistence.entity.IaEstimateExpD1;
import th.go.excise.ims.ia.persistence.entity.IaEstimateExpH;
import th.go.excise.ims.ia.persistence.repository.IaEstimateExpD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaEstimateExpHRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.IaAuditIncD1Vo;
import th.go.excise.ims.ia.vo.IaAuditIncHVo;
import th.go.excise.ims.ia.vo.IaEstimateD1VoType;
import th.go.excise.ims.ia.vo.IaEstimateExpD1Vo;
import th.go.excise.ims.ia.vo.IaEstimateExpHVo;
import th.go.excise.ims.ia.vo.Int0501FormVo;
import th.go.excise.ims.ia.vo.Int0501SaveVo;
import th.go.excise.ims.ia.vo.Int0501Vo;
import th.go.excise.ims.preferences.persistence.repository.jdbc.ExcisePersonJdbcRepository;

@Service
public class Int0501Service {
	
	private static final Logger logger = LoggerFactory.getLogger(Int0501Service.class);
	
	@Autowired
	private ExcisePersonJdbcRepository excisePersonJdbcRepository;
	
	@Autowired
	private IaEstimateExpHRepository iaEstimateExpHRepository;
	
	@Autowired
	private IaEstimateExpD1Repository iaEstimateExpD1Repository;
	
	@Autowired
	private IaCommonService iaCommonService;
	
	public List<Int0501Vo> listPerson(Int0501FormVo form) {
		List<Int0501Vo> personList = new ArrayList<Int0501Vo>();
		personList = excisePersonJdbcRepository.listPerson(form);
		return personList;
	}
	
	public IaEstimateExpHVo saveIaEstimateExp(Int0501SaveVo vo) {
		IaEstimateExpH estimateExpH = null;
		try {
			if (StringUtils.isNotBlank(vo.getIaEstimateExpHVo().getEstExpNo())) {
				estimateExpH = new IaEstimateExpH();
				estimateExpH = iaEstimateExpHRepository.findByEstExpNo(vo.getIaEstimateExpHVo().getEstExpNo());
				estimateExpH.setPersonResp(vo.getIaEstimateExpHVo().getPersonResp());
				estimateExpH.setRespDeptCode(vo.getIaEstimateExpHVo().getRespDeptCode());
				Date expReqDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getExpReqDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setExpReqDate(expReqDate);
				estimateExpH.setDestinationPlace(vo.getIaEstimateExpHVo().getDestinationPlace());
				Date workStDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getWorkStDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setWorkStDate(workStDate);
				Date workFhDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getWorkFhDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setWorkFhDate(workFhDate);
				estimateExpH = iaEstimateExpHRepository.save(estimateExpH);
				vo.getIaEstimateExpHVo().setEstExpNo(estimateExpH.getEstExpNo());
			} else {
				estimateExpH = new IaEstimateExpH();
				estimateExpH.setEstExpNo(iaCommonService.autoGetRunAuditNoBySeqName("EST", vo.getIaEstimateExpHVo().getDestinationPlace(), "ESTIMATE_EXP_NO_SEQ", 8));
				estimateExpH.setPersonResp(vo.getIaEstimateExpHVo().getPersonResp());
				estimateExpH.setRespDeptCode(vo.getIaEstimateExpHVo().getRespDeptCode());
				Date expReqDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getExpReqDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setExpReqDate(expReqDate);
				estimateExpH.setDestinationPlace(vo.getIaEstimateExpHVo().getDestinationPlace());
				Date workStDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getWorkStDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setWorkStDate(workStDate);
				Date workFhDate = ConvertDateUtils.parseStringToDate(vo.getIaEstimateExpHVo().getWorkFhDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
				estimateExpH.setWorkFhDate(workFhDate);
				estimateExpH = iaEstimateExpHRepository.save(estimateExpH);
				vo.getIaEstimateExpHVo().setEstExpNo(estimateExpH.getEstExpNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// D1
		if (vo.getIaEstimateExpD1Vo() != null && vo.getIaEstimateExpD1Vo().size() > 0) {
			IaEstimateExpD1 val1 = null;
			List<IaEstimateExpD1> iaEstimateExpD1List = new ArrayList<>();
			for (IaEstimateExpD1Vo data1 : vo.getIaEstimateExpD1Vo()) {
				val1 = new IaEstimateExpD1();
				if (data1.getEstimateExpD1Id() != null) {
					val1 = iaEstimateExpD1Repository.findById(data1.getEstimateExpD1Id()).get();
					try {
						val1.setSeqNo(data1.getSeqNo());
						val1.setPersonTeamCode(data1.getPersonTeamCode());
						val1.setPosition(data1.getPosition());
						BigDecimal allowancesDay = new BigDecimal(data1.getAllowancesDay());
						val1.setAllowancesDay(allowancesDay);
						BigDecimal allowancesHalfDay = new BigDecimal(data1.getAllowancesHalfDay());
						val1.setAllowancesHalfDay(allowancesHalfDay);
						BigDecimal accomFeePackages = new BigDecimal(data1.getAccomFeePackages());
						val1.setAccomFeePackages(accomFeePackages);
						BigDecimal accomFeePackagesDat = new BigDecimal(data1.getAccomFeePackagesDat());
						val1.setAccomFeePackagesDat(accomFeePackagesDat);
						BigDecimal tranCost = new BigDecimal(data1.getTranCost()); 
						val1.setTranCost(tranCost);
						BigDecimal otherExpenses = new BigDecimal(data1.getOtherExpenses());
						val1.setOtherExpenses(otherExpenses);
						BigDecimal sumAmt = new BigDecimal(data1.getSumAmt());
						val1.setSumAmt(sumAmt);
						BigDecimal sumAllowances = allowancesDay.multiply(allowancesHalfDay); 
						val1.setSumAllowances(sumAllowances);
						BigDecimal sumAccom = accomFeePackages.multiply(accomFeePackagesDat);
						val1.setSumAccom(sumAccom);
						val1.setRemark(data1.getRemark());
						val1 = iaEstimateExpD1Repository.save(val1);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				} else {
					try {
						val1.setEstExpNo(estimateExpH.getEstExpNo());
						val1.setSeqNo(data1.getSeqNo());
						val1.setPersonTeamCode(data1.getPersonTeamCode());
						val1.setPosition(data1.getPosition());
						BigDecimal allowancesDay = new BigDecimal(data1.getAllowancesDay());
						val1.setAllowancesDay(allowancesDay);
						BigDecimal allowancesHalfDay = new BigDecimal(data1.getAllowancesHalfDay());
						val1.setAllowancesHalfDay(allowancesHalfDay);
						BigDecimal accomFeePackages = new BigDecimal(data1.getAccomFeePackages());
						val1.setAccomFeePackages(accomFeePackages);
						BigDecimal accomFeePackagesDat = new BigDecimal(data1.getAccomFeePackagesDat());
						val1.setAccomFeePackagesDat(accomFeePackagesDat);
						BigDecimal tranCost = new BigDecimal(data1.getTranCost()); 
						val1.setTranCost(tranCost);
						BigDecimal otherExpenses = new BigDecimal(data1.getOtherExpenses());
						val1.setOtherExpenses(otherExpenses);
						BigDecimal sumAmt = new BigDecimal(data1.getSumAmt());
						val1.setSumAmt(sumAmt);
						BigDecimal sumAllowances = allowancesDay.multiply(allowancesHalfDay); 
						val1.setSumAllowances(sumAllowances);
						BigDecimal sumAccom = accomFeePackages.multiply(accomFeePackagesDat);
						val1.setSumAccom(sumAccom);
						val1.setRemark(data1.getRemark());
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}

					iaEstimateExpD1List.add(val1);
				}
			}
			iaEstimateExpD1Repository.saveAll(iaEstimateExpD1List);
		}
		return vo.getIaEstimateExpHVo();
	}
	
	public List<IaEstimateExpH> getDropdownEstimateNo() {
		return iaEstimateExpHRepository.getEstimateNoList();
	}
	
	public List<IaEstimateD1VoType> findIaEstimateD1ByestExpNo(String estExpNo) throws Exception {
		List<IaEstimateD1VoType> iaEstimateExpD1List = new ArrayList<>();
		IaEstimateD1VoType iaEstimateExpD1Vo = null;
		List<IaEstimateExpD1> iaEstimateExpDList = iaEstimateExpD1Repository.findIaEstimateD1ByestExpNo(estExpNo);
		for (IaEstimateExpD1 iaEstimateExpD1 : iaEstimateExpDList) {
			iaEstimateExpD1Vo = new IaEstimateD1VoType();
			iaEstimateExpD1Vo.setEstimateExpD1Id(iaEstimateExpD1.getEstimateExpD1Id());
			iaEstimateExpD1Vo.setEstExpNo(iaEstimateExpD1.getEstExpNo());
			iaEstimateExpD1Vo.setSeqNo(iaEstimateExpD1.getSeqNo());
			iaEstimateExpD1Vo.setPersonTeamCode(iaEstimateExpD1.getPersonTeamCode());
			iaEstimateExpD1Vo.setPosition(iaEstimateExpD1.getPosition());
			iaEstimateExpD1Vo.setAllowancesDay(iaEstimateExpD1.getAllowancesDay());
			iaEstimateExpD1Vo.setAllowancesHalfDay(iaEstimateExpD1.getAllowancesHalfDay());
			iaEstimateExpD1Vo.setAccomFeePackages(iaEstimateExpD1.getAccomFeePackages());
			iaEstimateExpD1Vo.setAccomFeePackagesDat(iaEstimateExpD1.getAccomFeePackagesDat());
			iaEstimateExpD1Vo.setTranCost(iaEstimateExpD1.getTranCost());
			iaEstimateExpD1Vo.setOtherExpenses(iaEstimateExpD1.getOtherExpenses());
			iaEstimateExpD1Vo.setSumAmt(iaEstimateExpD1.getSumAmt());
			iaEstimateExpD1Vo.setSumAllowances(iaEstimateExpD1.getSumAllowances());
			iaEstimateExpD1Vo.setSumAccom(iaEstimateExpD1.getSumAccom());
			iaEstimateExpD1Vo.setRemark(iaEstimateExpD1.getRemark());		
			iaEstimateExpD1List.add(iaEstimateExpD1Vo);
		}
		return iaEstimateExpD1List;
	}
	
	public IaEstimateExpHVo findIaEstimateHByestExpNo(String estExpNo) {
		IaEstimateExpHVo EstExpVo = null;
		IaEstimateExpH data = null;
		ExciseDepartmentVo excise = null;
		data = iaEstimateExpHRepository.findByEstExpNo(estExpNo);
		try {
			EstExpVo = new IaEstimateExpHVo();
			EstExpVo.setEstExpNo(data.getEstExpNo());
			EstExpVo.setPersonResp(data.getPersonResp());
			EstExpVo.setRespDeptCode(data.getRespDeptCode());
			EstExpVo.setExpReqDate(data.getExpReqDate().toString());
			EstExpVo.setDestinationPlace(data.getDestinationPlace());
			String workStDate = ConvertDateUtils.formatDateToString(data.getWorkStDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH);
			EstExpVo.setWorkStDate(workStDate);
			String workFhDate = ConvertDateUtils.formatDateToString(data.getWorkFhDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH);
			EstExpVo.setWorkFhDate(workFhDate);

			excise = ExciseDepartmentUtil.getExciseDepartmentFull(data.getDestinationPlace());
			EstExpVo.setArea(excise.getArea());
			EstExpVo.setSector(excise.getSector());
			EstExpVo.setBranch(excise.getBranch());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return EstExpVo;
	}
	
	
	
}

package th.go.excise.ims.preferences.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.ExciseOrgDepaccVo;
import th.go.excise.ims.ia.vo.ExciseOrgGfmisVo;
import th.go.excise.ims.ia.vo.Int1504FormVo;
import th.go.excise.ims.ia.vo.Int1504OrgFormVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgDepacc;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgGfmis;
import th.go.excise.ims.preferences.persistence.entity.ExcisePersonInfo;
import th.go.excise.ims.preferences.persistence.entity.ExcisePersonInfo1;
import th.go.excise.ims.preferences.persistence.entity.ExciseTitle;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonInfo1Repository;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonInfoRepository;
import th.go.excise.ims.preferences.persistence.repository.ExciseTitleRepository;
import th.go.excise.ims.preferences.vo.Ed04FormHeadVo;
import th.go.excise.ims.preferences.vo.Ed04FormSave;
import th.go.excise.ims.preferences.vo.ExcisePersonInfo1Vo;
import th.go.excise.ims.preferences.vo.ExcisePersonInfoVo;

@Service
public class Ed04Service {
	
	@Autowired
	private ExciseTitleRepository exciseTitleRepository;
	
	@Autowired
	private ExcisePersonInfoRepository excisePersonInfoRepository;
	
	@Autowired
	private ExcisePersonInfo1Repository excisePersonInfo1Repository;
	
	public List<ExciseTitle> listPersonThTitle() {
		List<ExciseTitle> dataList = new ArrayList<ExciseTitle>();
		dataList = exciseTitleRepository.listPersonThTitle();
		return dataList;
	}
	
	
	public ExcisePersonInfoVo savePerson(Ed04FormSave vo) {
		 ExcisePersonInfo excisePersonInfo = null;
		try {
			  excisePersonInfo = new ExcisePersonInfo();
			  excisePersonInfo.setPersonLogin(vo.getExcisePersonInfoVo().getPersonLogin());
			  excisePersonInfo.setPersonId(vo.getExcisePersonInfoVo().getPersonId());
			  excisePersonInfo.setPersonType(vo.getExcisePersonInfoVo().getPersonType());
			  excisePersonInfo.setPersonThTitle(vo.getExcisePersonInfoVo().getPersonThTitle());
			  excisePersonInfo.setPersonThName(vo.getExcisePersonInfoVo().getPersonThName());
			  excisePersonInfo.setPersonThSurname(vo.getExcisePersonInfoVo().getPersonThSurname());
			  excisePersonInfo.setPersonEnTitle(vo.getExcisePersonInfoVo().getPersonEnTitle());
			  excisePersonInfo.setPersonEnName(vo.getExcisePersonInfoVo().getPersonEnName());
			  excisePersonInfo.setPersonEnSurname(vo.getExcisePersonInfoVo().getPersonEnSurname());
			  excisePersonInfo.setUnderOffcode(vo.getExcisePersonInfoVo().getUnderOffcode());
			  excisePersonInfo.setUnderOffname(vo.getExcisePersonInfoVo().getUnderOffname());
			  excisePersonInfo.setUnderDeptcode(vo.getExcisePersonInfoVo().getUnderDeptcode());
			  excisePersonInfo.setUnderDeptname(vo.getExcisePersonInfoVo().getUnderDeptname());
			  excisePersonInfo.setWorkOffcode(vo.getExcisePersonInfoVo().getWorkOffcode());
			  excisePersonInfo.setWorkOffname(vo.getExcisePersonInfoVo().getWorkOffname());
			  excisePersonInfo.setWorkDeptcode(vo.getExcisePersonInfoVo().getWorkDeptcode());
			  excisePersonInfo.setLinePositionCode(vo.getExcisePersonInfoVo().getLinePositionCode());
			  excisePersonInfo.setLinePositionLevel(vo.getExcisePersonInfoVo().getLinePositionLevel());
			  excisePersonInfo.setLinePosition(vo.getExcisePersonInfoVo().getLinePosition());
			  excisePersonInfo.setExcPositionCode(vo.getExcisePersonInfoVo().getExcPositionCode());
			  excisePersonInfo.setExcPosition(vo.getExcisePersonInfoVo().getExcPosition());
			  excisePersonInfo.setActingExcpositionCode(vo.getExcisePersonInfoVo().getActingExcpositionCode());
			  excisePersonInfo.setActingExcposition(vo.getExcisePersonInfoVo().getActingExcposition());
			  excisePersonInfo.setEmailAddress(vo.getExcisePersonInfoVo().getEmailAddress());
			  excisePersonInfo.setDeptPhoneNo(vo.getExcisePersonInfoVo().getDeptPhoneNo());
			  excisePersonInfo.setPersonStatus(vo.getExcisePersonInfoVo().getPersonStatus());
//			  excisePersonInfo.setCoupleFullName(vo.getExcisePersonInfoVo().getCoupleFullName());
			  excisePersonInfo.setCouplePid(vo.getExcisePersonInfoVo().getCouplePid());
//			  excisePersonInfo.setFatherFullName(vo.getExcisePersonInfoVo().getFatherFullName());
			  excisePersonInfo.setFatherPid(vo.getExcisePersonInfoVo().getFatherPid());
//			  excisePersonInfo.setMotherFullName(vo.getExcisePersonInfoVo().getMotherFullName());
			  excisePersonInfo.setMotherPid(vo.getExcisePersonInfoVo().getMotherPid());
			  excisePersonInfo.setPersonAddrno(vo.getExcisePersonInfoVo().getPersonAddrno());
			  excisePersonInfo.setPersonMoono(vo.getExcisePersonInfoVo().getPersonMoono());
			  excisePersonInfo.setPersonVillagename(vo.getExcisePersonInfoVo().getPersonVillagename());
			  excisePersonInfo.setPersonSoiname(vo.getExcisePersonInfoVo().getPersonSoiname());
			  excisePersonInfo.setPersonRoadname(vo.getExcisePersonInfoVo().getPersonRoadname());
			  excisePersonInfo.setPersonTabbolCode(vo.getExcisePersonInfoVo().getPersonTabbolCode());
			  excisePersonInfo.setPersonAmphurCode(vo.getExcisePersonInfoVo().getPersonAmphurCode());
			  excisePersonInfo.setPersonProvinceCode(vo.getExcisePersonInfoVo().getPersonProvinceCode());		
			  excisePersonInfo = excisePersonInfoRepository.save(excisePersonInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// D1
		if (vo.getExcisePersonInfo1Vos() != null && vo.getExcisePersonInfo1Vos().size() > 0) {
			ExcisePersonInfo1 val1 = null;
			List<ExcisePersonInfo1> excisePersonInfo1s = new ArrayList<>();
			for (ExcisePersonInfo1Vo data1 : vo.getExcisePersonInfo1Vos()) {
				val1 = new ExcisePersonInfo1();		
					try {
						val1.setPersonLogin(data1.getPersonLogin());
						val1.setChildNo(data1.getChildNo());
//						val1.setChildFullName(data1.getChildFullName());
						val1.setChildPid(data1.getChildPid());
						val1.setChildBirthDate(ConvertDateUtils.parseStringToDate(data1.getChildBirthDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) );
						val1.setInstituteDesc(data1.getInstituteDesc());
						val1.setInstituteAmphurCode(data1.getInstituteAmphurCode());
						val1.setInstituteProvinceCode(data1.getInstituteProvinceCode());
					} catch (Exception e) {
						e.printStackTrace();
					}
					excisePersonInfo1s.add(val1);
			}
			excisePersonInfo1Repository.saveAll(excisePersonInfo1s);
		}
		return vo.getExcisePersonInfoVo();
	}
	
	public ExcisePersonInfo dataHead(Ed04FormHeadVo form) {
		ExcisePersonInfo dataList = excisePersonInfoRepository.dataHead(form.getPersonLogin());
		return dataList;
	}
	
	public List<ExcisePersonInfo1> listChild(Ed04FormHeadVo form) {
		List<ExcisePersonInfo1> dataList = new ArrayList<ExcisePersonInfo1>();
		dataList = excisePersonInfo1Repository.listChild(form.getPersonLogin());
		return dataList;
	}
	

}

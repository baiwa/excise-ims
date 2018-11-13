package th.co.baiwa.excise.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.security.domain.UserBean;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonHeader;
import th.co.baiwa.excise.ta.persistence.entity.analysis.PlanWorksheetHeader;
import th.co.baiwa.excise.ta.service.PlanWorksheetHeaderService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/restful")
public class RestfulController {

	private static final Logger logger = LoggerFactory.getLogger(RestfulController.class);

	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;

	@Value("${version.program.taxAudit}")
	private String taxAudit;

	@Value("${version.program.internalAudit}")
	private String internalAudit;

	@Value("${version.program.operationAudit}")
	private String operationAudit;

	@Value("${version.program.exportAudit}")
	private String exportAudit;

	@Value("${version.program.reportAudit}")
	private String reportAudit;

	@Value("${version.program}")
	private String program;

	private final String INT = "INT";
	private final String TAX = "TAX";
	private final String OPE = "OPE";
	private final String EXP = "EXP";
	private final String REP = "REP";
	private final String SECTOR_LIST = "SECTOR_LIST";

	@GetMapping("/versionProgramByPageCode")
	@ResponseBody
	public CommonHeader versionProgramByPageCode(@ModelAttribute CommonHeader commonHeader) {
		String pageCode = commonHeader.getPageCode();
		logger.info("versionProgramByPageCode {}", pageCode);
		commonHeader = userDetail();
		commonHeader.setVersionProgram(program);
		if (BeanUtils.isNotEmpty(UserLoginUtils.getCurrentUsername())) {
			if (pageCode.indexOf(INT) >= 0) {
				commonHeader.setVersionProgram(internalAudit);
			} else if (pageCode.indexOf(TAX) >= 0) {
				commonHeader.setVersionProgram(taxAudit);
			} else if (pageCode.indexOf(OPE) >= 0) {
				commonHeader.setVersionProgram(taxAudit);
			} else if (pageCode.indexOf(EXP) >= 0) {
				commonHeader.setVersionProgram(exportAudit);
			} else if (pageCode.indexOf(REP) >= 0) {
				commonHeader.setVersionProgram(reportAudit);
			} 
		}
		return commonHeader;
	}

	@GetMapping("/userDetail")
	@ResponseBody
	public CommonHeader userDetail() {
		logger.info("userDetail");
		CommonHeader commonHeader = new CommonHeader();
		StringBuilder detail = new StringBuilder();
		if (BeanUtils.isNotEmpty(UserLoginUtils.getCurrentUsername())) {
			UserBean userBean = UserLoginUtils.getCurrentUserBean();
			commonHeader.setFullName(userBean.getUserThaiName() + " " + userBean.getUserThaiSurname());
			commonHeader.setTitle(userBean.getTitle());
			commonHeader.setPosition(ApplicationCache.getListOfValueByValueType(SECTOR_LIST, userBean.getOfficeId()).get(0).getSubTypeDescription());
			commonHeader.setUserDetail(detail.toString());
			commonHeader.setVersionProgram(program);
		}
		return commonHeader;
	}

	@GetMapping("/exciseList")
	@ResponseBody
	public List<PlanWorksheetHeader> exciseList(@ModelAttribute PlanWorksheetHeader vo) {
		logger.debug("analysNumber : " + vo.getAnalysNumber());
		System.out.println(vo.getAnalysNumber());
		return planWorksheetHeaderService.queryPlanWorksheetHeaderCriteria(vo);
	}
}

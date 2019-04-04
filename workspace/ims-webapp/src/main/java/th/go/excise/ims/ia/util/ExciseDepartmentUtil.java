package th.go.excise.ims.ia.util;

import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;

@Component
public class ExciseDepartmentUtil {


	public static ExciseDepartmentVo getExciseDepartment(String officeCode) {
		
		ExciseDepartmentVo vo = new ExciseDepartmentVo();
		
		if (!"00".equals(officeCode.substring(0, 2))) {
			
			ExciseDept sector = ApplicationCache.getExciseDept(officeCode.substring(0, 2)+"0000");
			vo.setSector(sector.getDeptName());
			
			if(!"0000".equals(officeCode.substring(2, 6))) {
				
				ExciseDept area = ApplicationCache.getExciseDept(officeCode.substring(0, 4)+"00");
				vo.setArea(area.getDeptName());
				
				if(!"00".equals(officeCode.substring(4, 6))) {
					ExciseDept branch = ApplicationCache.getExciseDept(officeCode);
					vo.setBranch(branch.getDeptName().split(" ")[1]);
				}
					
			}
			vo.setOfficeCode(officeCode);
			ExciseDept offShortName = ApplicationCache.getExciseDept(officeCode);
			vo.setOffShortName(offShortName.getDeptShortName());
		}else {
			
			ExciseDept sector = ApplicationCache.getExciseDept(officeCode);
			vo.setSector(sector.getDeptName());
			vo.setOfficeCode(officeCode);
			ExciseDept offShortName = ApplicationCache.getExciseDept(officeCode);
			vo.setOffShortName(offShortName.getDeptShortName());
			
		}
	

		return vo;

	}

}


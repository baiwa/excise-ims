
package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int084Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int085FormVo;
import th.co.baiwa.excise.ia.service.Int085Service;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Controller
@RequestMapping("api/ia/int085")
public class Int085Controller {

	private Logger log = LoggerFactory.getLogger(Int085Controller.class);
	
	private static final String sessionDataInt084 = "sessionDataInt084";
	
	@Autowired
	private Int085Service int085Service;
	

	@SuppressWarnings("unchecked")
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int084Vo> list(@RequestBody Int085FormVo formVo,HttpServletRequest request){
		DataTableAjax<Int084Vo> list = new DataTableAjax<Int084Vo>();
		List<Int084Vo> list2 = new ArrayList<Int084Vo>();
		try {
			HttpSession session = request.getSession();
			list2 = (List<Int084Vo>)session.getAttribute(sessionDataInt084);
			if (!BeanUtils.isEmpty(list2)) {
				list.setData(list2);
			}
			
//			 list = int085Service.findAll(formVo);			 
		} catch (Exception e) {
			log.error("Error ! findAll",e);
		}
		
		return list;
	}

}

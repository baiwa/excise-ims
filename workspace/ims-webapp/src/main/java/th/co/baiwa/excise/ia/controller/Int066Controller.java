package th.co.baiwa.excise.ia.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int065FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int065Vo;
import th.co.baiwa.excise.ia.service.Int066Service;

@Controller
@RequestMapping("api/ia/int066")
public class Int066Controller {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Int066Service int066Service;
	
	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int065Vo> findAll(@RequestBody Int065FormVo formVo) {
		return int066Service.findAll(formVo);
	}

	@GetMapping("/sector")
	@ResponseBody
	public List<Lov> sector() {
		return int066Service.sector();
	}

	@PostMapping("/area")
    @ResponseBody
    public List<Lov> sector(@RequestBody Long idMaster){
        List<Lov> arae = int066Service.area(idMaster);
        return arae;
    }
	
	@PostMapping("/branch")
    @ResponseBody
    public List<Lov> branch(@RequestBody Long idMaster){
        List<Lov> branch = int066Service.branch(idMaster);
        return branch;
    }

	@GetMapping("/budgetType")
	@ResponseBody
	public List<Lov> budgetType(){
		return int066Service.budgetType();
	}
	
	 @GetMapping("/exportFile")
		@ResponseBody
		public  void exportFile(@ModelAttribute Int065FormVo formVo, HttpServletResponse response) throws Exception {
			try {
				int066Service.exportFile(formVo, response);
			} catch (Exception e) {
				log.error("Error ! ==> exportFile method exportFile",e);
			}
		}
}
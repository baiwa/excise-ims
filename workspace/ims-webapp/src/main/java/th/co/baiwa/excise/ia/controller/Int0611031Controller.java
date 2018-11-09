package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.ia.service.Int0611031Service;

@Controller
@RequestMapping("api/ia/int0611031")
public class Int0611031Controller {

	 @Autowired
	 private Int0611031Service int0611031Service;
	 
	 @GetMapping("/typeEducation")
	 @ResponseBody
	 public List<Lov> typeEducation(){
		  List<Lov> typeEducations = int0611031Service.typeEducation();
		 return typeEducations;
	 }
	 
	 @PostMapping("/subTypeEducation")
	 @ResponseBody
	 public List<Lov> subTypeEducation(@RequestBody String idMaster){
		 List<Lov> subTypeEducations = int0611031Service.subTypeEducation(idMaster);
		 return subTypeEducations;
	 }
	 
	 @PostMapping("/levelEducation")
	 @ResponseBody
	 public List<Lov> levelEducation(@RequestBody String idMaster){
		 List<Lov> subTypeEducations = int0611031Service.levelEducation(idMaster);
		 return subTypeEducations;
	 }
	 
	 @PostMapping("/typeSubject")
	 @ResponseBody
	 public List<Lov> typeSubject(@RequestBody String idMaster){
		 List<Lov> subTypeEducations = int0611031Service.typeSubject(idMaster);
		 return subTypeEducations;
	 }
	 
	 @PostMapping("/bursary")
	 @ResponseBody
	 public List<Lov> bursary(@RequestBody String idMaster){
		 List<Lov> subTypeEducations = int0611031Service.typeSubject(idMaster);
		 return subTypeEducations;
	 }
	 
	 @PostMapping("/checkMoney")
	 @ResponseBody
	 public Lov checkMoney(@RequestBody String idMaster){
		 Lov subTypeEducations = int0611031Service.checkMoney(idMaster);
		 return subTypeEducations;
	 }
}

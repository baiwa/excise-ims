import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
import { formatter, TextDateTH } from 'helpers/datepicker';
import { AjaxService } from 'services/ajax.service';


declare var $: any;
@Component({
  selector: 'app-int06-10-1',
  templateUrl: './int06-10-1.component.html',
  styleUrls: ['./int06-10-1.component.css']
})
export class Int06101Component implements OnInit {

  breadcrumb: BreadCrumb[]
  pmmethodList : any;
  activityList : any;
  budgetList : any;
  budged : any;
  category : any;
  list : any;

  constructor(
    private authService: AuthService,
    private ajax: AjaxService,) {
      this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ทะเบียนคุมการรับจ่ายเงิน", route: "#" },
      { label: "บันทึกรายการขอเบิก", route: "#" },
    ];
     }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06101');
    this.budgeDropdown();
    this.calenda();
    this.pmmethod();
    this.activity();
    this.budge();
  }

  calenda = () => {
    $("#datePersons").calendar({    
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
     formatter: formatter()
    });
    $("#dateWithdraw").calendar({    
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
     formatter: formatter()
    });
  }

  pmmethod = () => {
    let url = "ia/int06101/pmmethod"
    this.ajax.post(url,{}, res => {
      this.pmmethodList = res.json();
    })    
  }

  activity = () => {
    let url = "ia/int06101/activity"
    this.ajax.post(url,{}, res => {
      this.activityList = res.json();
    })    
  }

  budge = () => {
    let url = "ia/int06101/budge"
    this.ajax.post(url,{}, res => {
      this.budgetList = res.json();
    })  
  }

  budgeDropdown = () => {
    const URL = "ia/int06101/budged";
    this.ajax.post(URL,{}, res => {
      this.budged = res.json();
      console.log(this.budged);
    });
  }

  budgedOnchange = (e) => {
    $("#category").dropdown('restore defaults');
    const URL = "ia/int06101/category";  
    let params = e.target.value;
    if (params != "") {
      this.ajax.post(URL,{budgetId : params}, res => {
        console.log("Id : ", res.json());
        this.category = res.json();
      });
    }
  }

  categoryOnchange = (e) => {
    $("#list").dropdown('restore defaults');
    const URL = "ia/int06101/list";
    let params = e.target.value;
    if (params != "") {
      this.ajax.post(URL, {categoryId :params }, res => {
        console.log("Id : ", res.json());
        this.list = res.json();
      });
    }
  }



}

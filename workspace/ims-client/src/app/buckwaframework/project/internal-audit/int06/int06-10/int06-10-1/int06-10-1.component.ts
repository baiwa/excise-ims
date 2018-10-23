import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { BreadCrumb } from 'models/breadcrumb';
import { formatter, TextDateTH } from 'helpers/datepicker';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from 'services/message-bar.service';


declare var $: any;
@Component({
  selector: 'app-int06-10-1',
  templateUrl: './int06-10-1.component.html',
  styleUrls: ['./int06-10-1.component.css']
})
export class Int06101Component implements OnInit {
  num1: number[];
  num2: number[];
  percent1: string[];
  percent2: string[];
  percent3: string[];
  breadcrumb: BreadCrumb[]
  pmmethodList : any;
  activityList : any;
  budgetList : any;
  budged : any;
  listButton: any;
  numberButton: number;
  numbers: number[];
  category : any;
  list : any;

  constructor(
    private messageBarService: MessageBarService,
    private authService: AuthService,
    private ajax: AjaxService,) {
      this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ทะเบียนคุมการรับจ่ายเงิน", route: "#" },
      { label: "บันทึกรายการขอเบิก", route: "#" },
      
    ];
    this.listButton = [];
    this.numberButton = 1;
     }

  ngOnInit() {
    $(".ui.dropdown.ai").dropdown().css('width', '100%');
    this.listButton.push(this.numberButton);
    this.authService.reRenderVersionProgram('INT-06101');
    this.budgeDropdown();
    this.calenda();
    this.pmmethod();
    this.activity();
    this.budge();
    this.numbers = [];
    this.numbers.push(this.numbers.length+1);
  }


  
  onAddField = () => {
    let num = this.numbers.length;
    if (num < 30) {
      this.numbers.push(num + 1);
    } else {
      this.messageBarService.errorModal(
        "ไม่สามารถทำรายการได้",
        "เกิดข้อผิดพลาด"
      );
    }
  };

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

  
  onAddButton = () => {
    console.log("Add Button");
    this.listButton.push(++this.numberButton);
    console.log(this.listButton);
  }

  deleteButton = (e) => {
    console.log("Delete Button : ", e);
    let id = "#" + e;
    let idButton = "#delete" + e;
    $(id).remove();
    let index = this.listButton.findIndex(obj => obj == e);
    this.listButton.splice(index, 1);
  }

  onAddFile = () => {
    this.listButton.forEach(element => {
      var fileName = "#fileName" + element;
      var file = $(fileName)[0].files[0];

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

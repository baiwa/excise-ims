import { Component, OnInit } from '@angular/core';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { BreadCrumb } from 'models/breadcrumb';
import { Observable } from 'rxjs/Observable';
import { ComboBox } from 'models/combobox';
import { Int0615Service } from './int06-15.service';
import { AjaxService } from 'services/ajax.service';
declare var $: any;
const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
@Component({
  selector: 'app-int06-15',
  templateUrl: './int06-15.component.html',
  styleUrls: ['./int06-15.component.css'],
  providers: [Int0615Service]
})
export class Int0615Component implements OnInit {
  [x: string]: any;
  breadcrumb: BreadCrumb[];
  withdrawRequestList: Observable<ComboBox>;
  checkBtn1: boolean = false;
  checkBtn2: boolean = false;
  sectorList: any;
  areaList: any;
  submitted: boolean = false;
  departmentList: any;
  accounts: any;
  
  travelTo1List: any;
  travelTo2List: any;
  travelTo3List: any;

  dataTable: any;
  comboBox1: any;
  comboBox2: any;
  constructor( private selfService: Int0615Service,  private ajax: AjaxService, ) { 
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจสอบจำนวนเงินที่เบิกเกิน", route: "#" }
    ];
    this.withdrawRequestList = selfService.dropdown("WITHDRAW_REQUEST", null);  
    this.travelTo1Dropdown();
 
  
  }

  ngOnInit() {
    this.calenda();
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");


  }

  travelTo1Dropdown = () => {
    this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE" }, res => {
      this.travelTo1List = res.json();
    });
  }

  travelTo2Dropdown = e => {
    var id = e.target.value;
    if (id != "") {
      this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo2List = res.json();
      });
    }
  }

 


  disabledBtn = () => {
    this.checkBtn1 = false;
    this.checkBtn2 = false;
  };

  calenda = () => {
    $("#dateF").calendar({
      maxDate: new Date(),
      endCalendar: $("#dateT"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("ดป")
    });
    $("#dateT").calendar({
      maxDate: new Date(),
      startCalendar: $("#dateF"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("ดป")
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { Int0614Service } from './int06-14.service';
import { Observable } from 'rxjs/Observable';
import { ComboBox } from 'models/combobox';
import { Utils } from 'helpers/utils';

import { TextDateTH, formatter } from 'helpers/datepicker';
import { ComboBoxService } from 'helpers/comboBox';
import { AjaxService } from 'services/ajax.service';
declare var $: any;
const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
@Component({
  selector: 'app-int06-14',
  templateUrl: './int06-14.component.html',
  styleUrls: ['./int06-14.component.css'],
  providers: [Int0614Service]
})
export class Int0614Component implements OnInit {
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
  constructor( private selfService: Int0614Service,  private ajax: AjaxService, ) { 
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจสอบจำนวนครั้งในการเบิก", route: "#" }
    ];
    this.withdrawRequestList = selfService.dropdown("WITHDRAW_REQUEST", null);  
    this.travelTo1Dropdown();
 
  
  }

  ngOnInit() {
    this.calenda();
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");
    this.selfService.superdataTable();


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

  travelTo3Dropdown = e => {
    var id = e.target.value;
    if (id != "") {
      this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo3List = res.json();
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

import { Component, OnInit } from "@angular/core";


import { FormSearch } from "projects/internal-audit/int06/int06-5/from-search.model";
import { BreadCrumb } from "models/breadcrumb";
import { AuthService } from "services/auth.service";
import { AjaxService } from "services/ajax.service";
import { Utils } from "helpers/utils";
import { TextDateTH, formatter } from "helpers/datepicker";
import { Int052Service } from "./int05-2.service";


declare var $: any;
@Component({
  selector: "int05-2",
  templateUrl: "./int05-2.component.html",
  styleUrls: ["./int05-2.component.css"]  ,
  providers:[Int052Service]
})
export class Int052Component implements OnInit {
  
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบพัสดุ", route: "#" },
    { label: "ตรวจสอบแสตมป์", route: "#" },
    { label: "งบเดือนการรับ - จ่ายแสตมป์", route: "#" }
  ];
  form: FormSearch = new FormSearch();
  startDate: any = "";
  endDate: any = "";
  sectorList: any;
  offcode: any;
  yearMonthFrom: any;
  yearMonthTo: any;
  pageNo: any;
  dataPerPage: any;
  areaList: any;
  branchList: any;
  budgetTypeList: any;
  dataInTable: any = [];

  constructor(private int052Service: Int052Service ,private authService: AuthService, private ajax: AjaxService,) {
  }

  ngAfterViewInit() {
    this.calenda();

  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06500');
    $(".ui.dropdown").dropdown();
    this.sector();
    this.dataTable();
    this.budgetType();
  }

  sector = () => {
    this.int052Service.sector().then((res) => {
      this.sectorList = res;
    });
  }
  area = (e) => {
    $("#area").dropdown('restore defaults');
    $("#branch").dropdown('restore defaults');
    this.areaList = null;
    this.branchList = null
    if (Utils.isNotNull(e.target.value)) {
      this.int052Service.area(e.target.value).then((res) => {
        this.areaList = res;;
      });
    }
  }
  branch = (e) => {
    this.branchList = null
    $("#branch").dropdown('restore defaults');
    if (Utils.isNotNull(e.target.value)) {
      this.int052Service.branch(e.target.value).then((res) => {
        this.branchList = res;
      });
    }
  }

  budgetType = () => {
    this.int052Service.budgetType().then((res)=>{
      this.budgetTypeList = res;
    });
  }

  search = () => {
    $("#searchFlag").val("TRUE");
    this.int052Service.search();
    let promise1 = new Promise((resolve, reject) => {
      this.int052Service.search();
      resolve();
    });

    promise1.then(() => {
      console.log('resove success');
      setTimeout(() => {
        this.dataInTable = $('#dataTable').DataTable().data();
      console.log( this.dataInTable);
      console.log(this.dataInTable.length);
      }, 500);
      
    });
  }
  clear = () => {
    this.int052Service.clear();


  }

  exportFile=()=>{
    this.int052Service.exportFile();
  }


   dataTable = () => {
    let promise1 = new Promise((resolve, reject) => {
      this.int052Service.dataTable();
      resolve();
    });

    promise1.then(() => {
      this.dataInTable = $('#dataTable').DataTable().data();
    });
    
     
     
  }

  calenda = () => {
    $("#dateF").calendar({
      maxDate: new Date(),
      endCalendar: $("#dateT"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#dateT").calendar({
      maxDate: new Date(),
      startCalendar: $("#dateF"),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }


}

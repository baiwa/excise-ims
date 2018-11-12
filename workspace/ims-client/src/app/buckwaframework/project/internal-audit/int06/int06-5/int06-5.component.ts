import { Component, OnInit, AfterViewInit, ViewChild } from "@angular/core";
import { BreadCrumb } from "models/breadcrumb";
import { Int065Service } from "projects/internal-audit/int06/int06-5/int06-5.service";
import { FormSearch } from "projects/internal-audit/int06/int06-5/from-search.model";
import { TextDateTH, formatter } from "helpers/datepicker";
import { Utils } from "helpers/utils";
import { AuthService } from "services/auth.service";
import { AjaxService } from "services/ajax.service";


declare var $: any;
const URL = {
  export: "/ia/int065/exportFile"
}
@Component({
  selector: "app-int06-5",
  templateUrl: "./int06-5.component.html",
  styleUrls: ["./int06-5.component.css"],
  providers: [Int065Service]
})
export class Int065Component implements OnInit, AfterViewInit {

  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "ทะเบียนคุมการรับจ่ายเงิน", route: "#" },
    { label: "ค้นหารายการคุมเช็ค", route: "#" }
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
  dataTableV: any;

  constructor(private int065Service: Int065Service, private authService: AuthService, private ajax: AjaxService, ) {
  }

  ngAfterViewInit() {
    this.calenda();
    this.authService.reRenderVersionProgram('INT-06500');
    $(".ui.dropdown").dropdown();
    this.sector();
    this.dataTable();
    this.budgetType();
  }

  ngOnInit() {

  }

  sector = () => {
    this.int065Service.sector().then((res) => {
      this.sectorList = res;
    });
  }

  area = (e) => {
    $("#area").dropdown('restore defaults');
    $("#branch").dropdown('restore defaults');
    this.areaList = null;
    this.branchList = null
    if (Utils.isNotNull(e.target.value)) {
      this.int065Service.area(e.target.value).then((res) => {
        this.areaList = res;;
      });
    }
  }

  branch = (e) => {
    this.branchList = null
    $("#branch").dropdown('restore defaults');
    if (Utils.isNotNull(e.target.value)) {
      this.int065Service.branch(e.target.value).then((res) => {
        this.branchList = res;
      });
    }
  }

  budgetType = () => {
    this.int065Service.budgetType().then((res) => {
      this.budgetTypeList = res;
    });
  }

  search = () => {
    $("#searchFlag").val("TRUE");
    this.int065Service.search();
    setTimeout(() => {
      this.dataInTable = this.int065Service.getTable().data();
      // console.log(this.dataInTable);
      // console.log(this.dataInTable.length);
    }, 500);
  }
  clear = () => {
    this.int065Service.clear();
  }

  exportFile = () => {
    this.int065Service.exportFile();
  }

  dataTable = () => {
    this.int065Service.dataTable();
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

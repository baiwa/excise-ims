import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { FormSearch } from 'projects/internal-audit/int06/int06-6/form-search.model';
import { Int0521Service } from './int05-2-1.service';
import { AuthService } from 'services/auth.service';
import { AjaxService } from 'services/ajax.service';
import { Utils } from 'helpers/utils';
import { TextDateTH, formatter } from 'helpers/datepicker';
declare var $: any;
@Component({
  selector: 'app-int05-2-1',
  templateUrl: './int05-2-1.component.html',
  styleUrls: ['./int05-2-1.component.css'],
  providers:[Int0521Service]
})
export class Int0521Component implements OnInit {

  
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบพัสดุ", route: "#" },
    { label: "ตรวจสอบแสตมป์", route: "#" },
    { label: "บัญชีคุมการรับ - จ่ายแสตมป์", route: "#" }
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

  constructor(private int0521Service: Int0521Service ,private authService: AuthService, private ajax: AjaxService,) {
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
    this.int0521Service.sector().then((res) => {
      this.sectorList = res;
    });
  }
  area = (e) => {
    $("#area").dropdown('restore defaults');
    $("#branch").dropdown('restore defaults');
    this.areaList = null;
    this.branchList = null
    if (Utils.isNotNull(e.target.value)) {
      this.int0521Service.area(e.target.value).then((res) => {
        this.areaList = res;;
      });
    }
  }
  branch = (e) => {
    this.branchList = null
    $("#branch").dropdown('restore defaults');
    if (Utils.isNotNull(e.target.value)) {
      this.int0521Service.branch(e.target.value).then((res) => {
        this.branchList = res;
      });
    }
  }

  budgetType = () => {
    this.int0521Service.budgetType().then((res)=>{
      this.budgetTypeList = res;
    });
  }

  search = () => {
    $("#searchFlag").val("TRUE");
    this.int0521Service.search();
    let promise1 = new Promise((resolve, reject) => {
      this.int0521Service.search();
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
    this.int0521Service.clear();


  }

  exportFile=()=>{
    this.int0521Service.exportFile();
  }


   dataTable = () => {
    let promise1 = new Promise((resolve, reject) => {
      this.int0521Service.dataTable();
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
      type: "year",
      text: TextDateTH,
      formatter: formatter()
    });

  }


}

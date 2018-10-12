import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../../common/helper";
import { AjaxService, MessageBarService } from "../../../../../common/services";
import { Router, ActivatedRoute } from "@angular/router";
import { TravelService } from "../../../../../common/services/travel.service";
import { forEach } from "@angular/router/src/utils/collection";
declare var $: any;
const URL = {
  export:"/ia/int0161/exportFile"
}
@Component({
  selector: "app-int01-6-1",
  templateUrl: "./int01-6-1.component.html",
  styleUrls: ["./int01-6-1.component.css"]
})
export class Int0161Component implements OnInit {

  dataT: any[]= [];
  dataT2: any[]= [];
  loading: boolean = false;

  stringColumns: any;

  travelTo1List: any;
  travelTo2List: any;
  travelTo3List: any;

  trHtml1: any[] = [];
  trHtml2: any[] = [];

  yearForm: any=0;
  yearTo: any=0;

  offcode: any;
  yearMonthFrom: any;
  yearMonthTo: any;
  pageNo: any;
  dataPerPage: any;

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private travelService: TravelService
  ) { }

  ngOnInit() {
    this.calenda();
    this.travelTo1Dropdown();
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    
  }

  ngAfterViewInit() {
  }


  initDatatable() {

  }
  calenda = () => {
    $("#date1").calendar({
      endCalendar: $("#date2"),
      type: "year",
      text: TextDateTH,
      formatter: formatter('ป')

    });
    $("#date2").calendar({
      startCalendar: $("#date1"),
      type: "year",
      text: TextDateTH,
      formatter: formatter('ป')

    });
  }

  clickClear = function () {
    $("#searchFlag").val("FALSE");
    $('input[type=text]').val("");
    $('select').val("");
    $('input[type=calendar]').val("");
  }

 getdata = async () =>{
  this.loading = true;
  
  this.offcode="100300";
  this.yearMonthFrom=(parseInt(this.yearForm)-543)+"01";
  this.yearMonthTo=(parseInt(this.yearTo)-543)+"12";

	this.pageNo=1;
  this.dataPerPage="50";

  this.dataT= [];
  // this.dataT2= [];

  const URL = "ia/int0161/list";
  let licenseList6010List = [];
  // do {
    licenseList6010List= [];
    await this.ajax.post(URL, { 
        offcode: this.offcode, 
        yearMonthFrom: this.yearMonthFrom,
        yearMonthTo: this.yearMonthTo,
        pageNo:this.pageNo,
        dataPerPage: this.dataPerPage,
        yearForm:this.yearForm,
        yearTo:this.yearTo
      }, res => {
        licenseList6010List = res.json();

        setTimeout(() => {
          this.loading = false;
        },200);
        
      licenseList6010List.forEach(element => {
        this.dataT.push(element);
      });
      // console.log("pageNo : ",this.pageNo);
      // console.log("dataPerPage : ",licenseList6010List.length);
      // console.log("dataT2.length : ",this.dataT2.length);
      
      console.log("dataT2 : ",this.dataT);
      });
    //   this.pageNo++;
    // } while (licenseList6010List.length==1000);

    // await console.log("dataT2 all : ",this.dataT2);
 }
  createTrAndDataTable = async () => {

    this.yearForm = await parseInt($("#dateIn1").val());
    this.yearTo = await parseInt($("#dateIn2").val());
    var yearNo = await this.yearTo - this.yearForm;

    this.getdata();
    this.trHtml1 = [];
    this.trHtml2 = [];

    console.log("yearForm :", this.yearForm);
    console.log("yearTo :", this.yearTo);

    for (let i = this.yearForm; i <= this.yearTo; i++) {
       this.trHtml1.push(i);
       this.trHtml2.push("วันที่มีผลบังคับใช้");
       this.trHtml2.push("วันที่หมดอายุ");
    }
    
 
  }

  travelTo1Dropdown = () => {
    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: "SECTOR_VALUE" }, res => {
      this.travelTo1List = res.json();
    });
  }

  travelTo2Dropdown = e => {
    var id = e.target.value;
    if (id != "") {
      const URL = "combobox/controller/getDropByTypeAndParentId";
      this.ajax.post(URL, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo2List = res.json();
        this.setTravelTo(e);
      });
    }
  }

  travelTo3Dropdown = e => {
    var id = e.target.value;
    if (id != "") {
      const URL = "combobox/controller/getDropByTypeAndParentId";
      this.ajax.post(URL, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo3List = res.json();
        this.setTravelTo(e);
      });
    }
  }
  setTravelTo = e => {
    console.log(" e.target.value : ", e.target.value);
  }

  exportFile=()=>{
    console.log("exportFile");
    let param = "";

    param +="?offcode=" + this.offcode;
    param +="&yearMonthFrom=" + this.yearMonthFrom;
    param +="&yearMonthTo=" + this.yearMonthTo;
    param +="&pageNo=" + this.pageNo;
    param +="&dataPerPage=" + this.dataPerPage;
    param +="&yearForm=" + this.yearForm;
    param +="&yearTo=" + this.yearTo;
   
    this.ajax.download(URL.export+param);
  }
}

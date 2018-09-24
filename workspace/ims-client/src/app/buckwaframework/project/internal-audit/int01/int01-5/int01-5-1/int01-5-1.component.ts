import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../../common/helper";
import { AjaxService, MessageBarService } from "../../../../../common/services";
import { Router, ActivatedRoute } from "@angular/router";
import { TravelService } from "../../../../../common/services/travel.service";
import { forEach } from "@angular/router/src/utils/collection";
import { monthsToNumber } from "helpers/datepicker";

declare var $: any;
@Component({
  selector: "app-int01-5-1",
  templateUrl: "./int01-5-1.component.html",
  styleUrls: ["./int01-5-1.component.css"]
})
export class Int0151Component implements OnInit {

  dataT: any[]= [];
  loading: boolean = false;

  stringColumns: any;

  travelTo1List: any;
  travelTo2List: any;
  travelTo3List: any;

  trHtml1: any[] = [];
  trHtml2: any[] = [];

  yearForm: any=0;
  yearTo: any=0;

  monthForm: any=0;
  monthTo: any=0;

  nid: any;
  newregId: any;
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
  }

  ngAfterViewInit() {
  }


  initDatatable() {

  }
  calenda = () => {
    $("#date1").calendar({
      endCalendar: $("#date2"),
      type: "month",
      text: TextDateTH,
      formatter: formatter('ดป')

    });
    $("#date2").calendar({
      startCalendar: $("#date1"),
      type: "month",
      text: TextDateTH,
      formatter: formatter('ดป')

    });
  }

  clickClear = function () {
    $("#searchFlag").val("FALSE");
    $('input[type=text]').val("");
    $('select').val("");
    $('input[type=calendar]').val("");
  }

 getdata = () =>{
  this.loading = true;
  this.nid="0105555155742";
	this.newregId="";
	this.pageNo="0";
  this.dataPerPage="1000";
  this.dataT= [];
  const URL = "ia/int0161/list";
      this.ajax.post(URL, { 
        nid: this.nid, 
        newregId: this.newregId,
        pageNo: this.pageNo,
        dataPerPage: this.dataPerPage,
        yearForm:this.yearForm,
        yearTo:this.yearTo
      }, async res => {
        const licenseList6020List = await res.json();

        setTimeout(() => {
          this.loading = false;
        },200);
        
      licenseList6020List.forEach(element => {
        this.dataT.push(element);
      });
      console.log("dataT : ",this.dataT);
      });
 }
  createTrAndDataTable = async () => {

    this.yearForm = await parseInt($("#dateIn1").val().split(" ")[1]);
    this.yearTo = await parseInt($("#dateIn2").val().split(" ")[1]);
    var yearNo = await this.yearTo - this.yearForm;

    this.monthForm = await parseInt(monthsToNumber($("#dateIn1").val().split(" ")[0]));
    this.monthTo = await parseInt(monthsToNumber($("#dateIn2").val().split(" ")[0]));

    this.getdata();
    this.trHtml1 = [];
    this.trHtml2 = [];

    console.log("yearForm :", this.yearForm);
    console.log("yearTo :", this.yearTo);
    var monthNo = 0 ;
    if(this.yearTo>this.yearForm){
      monthNo = (12-this.monthForm)+(12*((this.yearTo-this.yearForm)-1))+this.monthTo;
    }else{
      monthNo = this.monthTo-this.monthForm;
    }
   
    console.log("monthNo :", monthNo);
    var monthArray = this.monthForm-1;
    for (let i = 0; i <= monthNo; i++) {
      if(monthArray<12){
        console.log("monthArray :", monthArray);
        console.log("TextDateTH.months[monthArray] :", TextDateTH.months[monthArray]);
        this.trHtml1.push(TextDateTH.months[monthArray]);
        this.trHtml2.push("วันที่ชำระ");
        this.trHtml2.push("ยอดภาษี");
      }else{
        monthArray=0;
        console.log("monthArray :", monthArray);
        console.log("TextDateTH.months[monthArray] :", TextDateTH.months[monthArray]);
        this.trHtml1.push(TextDateTH.months[monthArray]);
        this.trHtml2.push("วันที่ชำระ");
        this.trHtml2.push("ยอดภาษี");
        
      }
      monthArray++;
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
}

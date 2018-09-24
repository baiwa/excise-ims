import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../../common/helper";
import { AjaxService, MessageBarService } from "../../../../../common/services";
import { Router, ActivatedRoute } from "@angular/router";
import { TravelService } from "../../../../../common/services/travel.service";
import { forEach } from "@angular/router/src/utils/collection";
declare var $: any;
@Component({
  selector: "app-int01-6-1",
  templateUrl: "./int01-6-1.component.html",
  styleUrls: ["./int01-6-1.component.css"]
})
export class Int0161Component implements OnInit {

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
}

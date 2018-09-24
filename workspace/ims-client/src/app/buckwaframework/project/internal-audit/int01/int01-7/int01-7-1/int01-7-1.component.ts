import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../../common/helper";
import { AjaxService, MessageBarService } from "../../../../../common/services";
import { Router, ActivatedRoute } from "@angular/router";
import { TravelService } from "../../../../../common/services/travel.service";
import { forEach } from "@angular/router/src/utils/collection";
import { monthsToNumber } from "helpers/datepicker";
declare var $: any;
@Component({
  selector: "app-int01-7-1",
  templateUrl: "./int01-7-1.component.html",
  styleUrls: ["./int01-7-1.component.css"]
})
export class Int0171Component implements OnInit {

  travelTo1List: any;
  travelTo2List: any;
  travelTo3List: any;

  offcode: any;
  yearMonthFrom: any;
  yearMonthTo: any;
  pageNo: any;
  dataPerPage: any;
  searchFlag: any = "FALSE";

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
    this.dataTable();
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
chicksearch=()=>{
  let yearFrom = parseInt($("#dateIn1").val().split(" ")[1])-543;
  let yearTo =   parseInt($("#dateIn2").val().split(" ")[1])-543;

  this.offcode = "100300";
  this.yearMonthFrom = String(yearFrom)+monthsToNumber($("#dateIn1").val().split(" ")[0]);
  this.yearMonthTo =   String(yearTo)+monthsToNumber($("#dateIn2").val().split(" ")[0]);
  this.pageNo = '1';
  this.dataPerPage = '1000';
  this.searchFlag = "TRUE";
    $('#table').DataTable().ajax.reload();
 
}

clickClear = function () {
  this.searchFlag = "FALSE";
  $('input[type=text]').val("");
  $('select').val("");
  $('input[type=calendar]').val("");
  $('#table').DataTable().ajax.reload();
}
  

 dataTable = () =>{
  var table = $('#table').DataTable({
    "lengthChange": true,
    "serverSide": false,
    "searching": false,
    "ordering": false,
    "processing": true,
    "scrollX": true,
    "ajax": {
      "url": '/ims-webapp/api/ia/int0171/list',
      "contentType": "application/json",
      "type": "POST",
      "data": (d) => {
        return JSON.stringify($.extend({}, d, {
          searchFlag:this.searchFlag,
          offcode: this.offcode,
          yearMonthFrom: this.yearMonthFrom, 
          yearMonthTo: this.yearMonthTo,
          pageNo: this.pageNo,
          dataPerPage: this.dataPerPage
        }));
      },
    },
    "columns": [
     {
        "data": "CusFullName"
      }, {
        "data": "LicName"
      }, {
        "data": "LicCode"
      }, {
        "data": "LicType"
      }, {
        "data": "PrintCount"
      }
    ]
  });
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

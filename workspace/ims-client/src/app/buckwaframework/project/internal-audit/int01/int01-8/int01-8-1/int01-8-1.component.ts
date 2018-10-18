import { Component, OnInit } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { TextDateTH, formatter } from "helpers/datepicker";
import { Router, ActivatedRoute } from "@angular/router";
import { MessageBarService } from "services/message-bar.service";
import { AuthService } from "services/auth.service";
import { BreadCrumb } from "models/breadcrumb";
declare var $: any;
const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId",
  DATA_WS: "ia/int018/exciseWebService8020"
};
@Component({
  selector: "app-int01-8-1",
  templateUrl: "./int01-8-1.component.html",
  styleUrls: ["./int01-8-1.component.css"]
})


export class Int0181Component implements OnInit {
  private selectedProduct: string = "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก ";
  private productList: any[];

  travelTo1List: any;
  travelTo2List: any;
  travelTo3List: any;
  sector: string;
  area: string;
  local: string;
  startDate: string;
  endDate: string;
  breadcrumb: BreadCrumb[];
  comboboxType: string = 'SECTOR_VALUE';
  startDatePic: any;
  endDatePic: any;
  listWs8020List: any[];
  pageWs8020List: any[];
  pagesize: number = 1000;

  constructor(private router: Router,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
    private authService: AuthService) {

    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "การตรวจสอบรายได้", route: "#" },
      { label: "ตรวจสอบการนำฝาก/ส่งเงินรายได้", route: "#" },
    ];
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-01810');
    this.hideData();

    this.startDatePic = $("#calendar1").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {

        var dd = date.getDate() + "".length == 1 ? "0" + date.getDate() : date.getDate();
        console.log(dd);
        var mm = ((date.getMonth() + 1) + "").length == 1 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        var yyyy = date.getFullYear();
        this.startDate = yyyy + "" + mm + "" + dd;
        console.log(this.startDate);
      }

    });

    this.endDatePic = $("#calendar2").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {

        var dd = date.getDate() + "".length == 1 ? "0" + date.getDate() : date.getDate();
        console.log(dd);
        var mm = ((date.getMonth() + 1) + "").length == 1 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        var yyyy = date.getFullYear();
        this.endDate = yyyy + "" + mm + "" + dd;
        console.log(this.endDate);
      }
    });


    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.search").css("width", "100%");

    this.travelTo1Dropdown();
  }

  travelTo1Dropdown = () => {
    this.ajax.post(URL.DROPDOWN, { type: this.comboboxType }, res => {
      this.travelTo1List = res.json();
    });
  }

  travelTo2Dropdown = e => {
    this.area = '00';
    this.local = '00';
    var id = e.target.value;
    if (id != "") {
      this.ajax.post(URL.DROPDOWN, { type: this.comboboxType, lovIdMaster: id }, res => {
        this.travelTo2List = res.json();
        this.travelTo3List = [];
      });
    }
  }

  travelTo3Dropdown = e => {
    this.local = '00';
    var id = e.target.value;
    if (id != "") {
      this.ajax.post(URL.DROPDOWN, { type: this.comboboxType, lovIdMaster: id }, res => {
        this.travelTo3List = res.json();
      });
    }
  }
  hideData() {
    this.area = '00';
    this.local = '00';
    this.sector = '00';
    this.travelTo1List = [];
    this.travelTo2List = [];
    this.travelTo3List = [];
    $('calendar1').calendar('setDate', null);
    this.startDate = '';
    this.endDate = '';
    this.travelTo1Dropdown();
    $('#Int0181').hide();

  }
  async showData() {
    console.log();
    $('#Int0181').show();

    let officeCode = this.sector + this.area + this.local;
    this.listWs8020List = [];
    this.ajax.post(URL.DATA_WS, {
      officeCode: officeCode,
      yearMonthFrom: this.startDate,
      yearMonthTo: this.endDate,
      dateType: "Income"

    }, res => {
      this.listWs8020List = res.json();

    });

  }


}

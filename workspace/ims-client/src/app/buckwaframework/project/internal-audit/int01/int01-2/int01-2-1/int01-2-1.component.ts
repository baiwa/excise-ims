import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
import { BreadCrumb } from "models/breadcrumb";
declare var $: any;

const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
@Component({
  selector: "int01-2-1",
  templateUrl: "./int01-2-1.component.html",
  styleUrls: ["./int01-2-1.component.css"]
})
export class Int0121Component implements OnInit {
  private selectedProduct: string = "สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก ";
  private productList: any[];

  breadcrumb: BreadCrumb[];
  travelTo1List: any;
  travelTo2List: any;
  travelTo3List: any;

  code1: any = '00';
  code2: any = '00';
  code3: any = '00';

  startDate: any = '';
  endDate: any = '';
  constructor(private router: Router,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบรายได้", route: "#" },
      { label: "ตรวจสอบใบอนุญาต", route: "#" }
    ];
  }

  ngOnInit() {


    $("#calendar1").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {

        //var dd = date.day;
        var mm = ((date.getMonth() + 1) + "").length == 1 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        var yyyy = date.getFullYear();
        this.startDate = yyyy + "" + mm;
        console.log(this.startDate);
      }

    });

    $("#calendar2").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter(),
      onChange: (date, text) => {

        var mm = ((date.getMonth() + 1) + "").length == 1 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
        var yyyy = date.getFullYear();
        this.endDate = yyyy + "" + mm;
        console.log(this.endDate);
      }
    });

    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.search").css("width", "100%");

    this.travelTo1Dropdown();
  }

  travelTo1Dropdown = () => {
    this.code2 = '00';
    this.code3 = '00';
    this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE" }, res => {
      this.travelTo1List = res.json();
      //console.log(this.travelTo1List);
    });
  }

  travelTo2Dropdown = e => {
    this.code3 = '00';
    var id = e.target.value;
    this.travelTo3List = [];
    if (id != "" && id != "00") {
      this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo2List = res.json();
      });
    }
  }

  travelTo3Dropdown = e => {
    var id = e.target.value;
    if (id != "" && id != "00") {
      this.ajax.post(URL.DROPDOWN, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.travelTo3List = res.json();
      });
    }
  }

  searchWs() {
    let ofCode = this.getOfCode(this.code1, this.code2, this.code3);

    console.log("ofCode : " + ofCode);
    console.log("startDate : " + this.startDate);
    console.log("endDate : " + this.endDate);
    if (ofCode == '000000') {
      this.messageBarService.errorModal('กรุณาเลือกภาคพื่นที่ที่ตรวจสอบใบอนุญาติ', 'แจ้งเตือน');
      return;

    }

    if (this.startDate == null || this.startDate == undefined || this.startDate == '') {
      this.messageBarService.errorModal('กรุณากำหนดช่วงเวลาเริ่มต้นตรวจสอบใบอนุญาติ', 'แจ้งเตือน');
      return;
    }

    if (this.endDate == null || this.endDate == undefined || this.endDate == '') {
      this.messageBarService.errorModal('กรุณากำหนดช่วงเวลาสิ้นสุดตรวจสอบใบอนุญาติ', 'แจ้งเตือน');
      return;
    }

    this.router.navigate(["/int01/2/2"], {
      queryParams: {
        ofCode: ofCode,
        startDate: this.startDate,
        endDate: this.endDate
      }
    });
  }


  getOfCode(code1, code2, code3) {
    //console.log(code1);
    let ofCode = "";
    if (code1 == '00') {
      ofCode += '00';
    } else {
      for (let index = 0; index < this.travelTo1List.length; index++) {
        const element = this.travelTo1List[index];
        if (element.lovId == code1) {
          ofCode += element.subType;
        }
      }


    }
    //console.log(code2);
    if (code2 == '00') {
      ofCode += '00';
    } else {
      for (let index = 0; index < this.travelTo2List.length; index++) {
        const element = this.travelTo2List[index];
        if (element.lovId == code2) {
          ofCode += element.subType;
        }
      }
    }
    //console.log(code3);
    if (code3 == '00') {
      ofCode += '00';
    } else {
      for (let index = 0; index < this.travelTo3List.length; index++) {
        const element = this.travelTo3List[index];
        if (element.lovId == code3) {
          ofCode += element.subType;
        }
      }
    }
    //console.log(ofCode);
    return ofCode;

  }





}

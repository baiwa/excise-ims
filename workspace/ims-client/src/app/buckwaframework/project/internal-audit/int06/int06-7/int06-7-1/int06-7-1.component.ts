import { Component, OnInit, AfterViewInit, ViewChild } from "@angular/core";
import { TextDateTH, formatter } from "helpers/datepicker";
import { BreadCrumb } from "models/breadcrumb";
import { Int0671Service } from "./int06-7-1.service";
import { Observable } from "rxjs/Observable";
import { async } from "q";
import * as moment from "moment";

declare var $: any;
@Component({
  selector: "app-int06-7-1",
  templateUrl: "./int06-7-1.component.html",
  styleUrls: ["./int06-7-1.component.css"],
  providers: [Int0671Service]
})
export class Int0671Component implements OnInit, AfterViewInit {
  breadcrumb: BreadCrumb[] = [];
  startDateTime: any = "";
  endDateTime: any = "";
  dataList: Observable<any>;
  statusManage: any = "SAVE";
  timeSetId: any = "";

  constructor(private int0671Service: Int0671Service) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจการเบิกค่าสาธารณูปโภค", route: "#" }
    ];
    this.dataList = this.int0671Service.dataInit();
  }

  ngAfterViewInit() {
    // $("#showData").hide();
  }

  ngOnInit() {
    $("#start")
      .calendar({
        minDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.startDateTime = text;
        }
      })
      .css("width", "100%");

    $("#end")
      .calendar({
        minDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา"),
        onChange: (date, text, mode) => {
          this.endDateTime = text;
        }
      })
      .css("width", "100%");
  }

  onSave = () => {
    var compareDate = moment("15/02/2013", "DD/MM/YYYY");
    var startDate = moment("12/01/2013", "DD/MM/YYYY");
    var endDate = moment("15/01/2013", "DD/MM/YYYY");
    compareDate.isBetween(startDate, endDate);
    console.log(compareDate.isBetween(startDate, endDate));
    if (this.startDateTime != "" && this.endDateTime != "") {
      if (this.statusManage === "SAVE") {
        // let t = new Date();
        // console.log(t);
        this.dataList = this.int0671Service.saveTime(
          this.startDateTime,
          this.endDateTime
        );
        // $("#showData").show();
      } else if (this.statusManage === "EDIT") {
        this.dataList = this.int0671Service.updateTime(
          this.timeSetId,
          this.startDateTime,
          this.endDateTime
        );
        //re status default
        this.statusManage = "SAVE";
      }
    } else if (this.startDateTime === "" && this.endDateTime === "") {
      this.startDateTime = null;
      this.endDateTime = null;
    } else if (this.startDateTime === "") {
      this.startDateTime = null;
    } else {
      this.endDateTime = null;
    }
  };

  onEdit = (id, text) => {
    console.log(id + "/" + text);
    this.statusManage = text;
    this.timeSetId = id;
    this.int0671Service.setValueUpdate(id);
  };

  onDelete = (id, text) => {
    console.log(id + "/" + text);
    // this.statusManage = text;
    this.timeSetId = id;
    this.dataList = this.int0671Service.deleteTime(id);
  };
}

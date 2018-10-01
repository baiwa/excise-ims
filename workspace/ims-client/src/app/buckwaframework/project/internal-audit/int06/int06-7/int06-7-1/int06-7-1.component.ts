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
  dat: any;
  dataEdit: any;

  constructor(private int0671Service: Int0671Service) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจการเบิกค่าสาธารณูปโภค", route: "#" }
    ];
    // this.int0671Service.dataInit(this.getData);
    this.dataList = this.int0671Service.dataInit();
  }

  ngAfterViewInit() {
    // $("#showData").hide();
  }

  ngOnInit() {
    //set data on edit
    this.dataList.subscribe(value => {
      this.dataEdit = value;
    });

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

  // getData = args => {
  //   this.dat = args.data;
  // };

  onSave = () => {
    //set data on edit
    this.dataList.subscribe(value => {
      this.dataEdit = value;
    });
    if (this.startDateTime != "" && this.endDateTime != "") {
      if (this.statusManage === "SAVE") {
        // let t = new Date();
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

    let ob = this.dataEdit.filter(obj => obj.timeSetId == id);
    $("#startDateTime").val(ob[0].startDateTime);
    $("#endDateTime").val(ob[0].endDateTime);
  };

  onDelete = (id, text) => {
    console.log(id + "/" + text);
    this.timeSetId = id;
    this.dataList = this.int0671Service.deleteTime(id);
  };
}

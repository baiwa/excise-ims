import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "helpers/datepicker";
import { BreadCrumb } from "models/breadcrumb";

declare var $: any;
@Component({
  selector: "app-int06-7-1",
  templateUrl: "./int06-7-1.component.html",
  styleUrls: ["./int06-7-1.component.css"]
})
export class Int0671Component implements OnInit {
  breadcrumb: BreadCrumb[] = [];
  constructor() {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจการเบิกค่าสาธารณูปโภค", route: "#" }
    ];
  }

  ngOnInit() {
    $("#start")
      .calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
        // onChange: (date, text, mode) => {
        //   this.budgetYear = text;
        // }
      })
      .css("width", "100%");

    $("#end")
      .calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter("วดปเวลา")
        // onChange: (date, text, mode) => {
        //   this.budgetYear = text;
        // }
      })
      .css("width", "100%");
  }
}

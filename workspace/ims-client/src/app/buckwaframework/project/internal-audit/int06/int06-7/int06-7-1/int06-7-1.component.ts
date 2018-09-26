import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "helpers/datepicker";

declare var $: any;
@Component({
  selector: "app-int06-7-1",
  templateUrl: "./int06-7-1.component.html",
  styleUrls: ["./int06-7-1.component.css"]
})
export class Int0671Component implements OnInit {
  constructor() {}

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

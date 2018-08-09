import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../common/services/ajax.service";
import { Router } from "@angular/router";

import { TextDateTH, digit, formatter } from "../../../../common/helper/datepicker";
declare var jQuery: any;
declare var $: any;
@Component({
  selector: "create-trader",
  templateUrl: "./create-trader.component.html",
  styleUrls: []
})
export class CreateTraderComponent implements OnInit {
  month: any;
  formatter1: any;
  formatter2: any;
  constructor(private ajax: AjaxService, private router: Router) {
    this.formatter1 = formatter('ดป');
    this.formatter2 = formatter('ดป');
    this.formatter1.cell = (cell, date, cellOptions) => {
      console.log(cell, date, cellOptions)
      if(date.getMonth()%TextDateTH.months.findIndex(obj => obj == $("#calendarraw").val().split(" ")[0])
    )
      cell[0].className = "link disabled";
    }
    this.formatter2.cell = (cell, date, cellOptions) => {
      console.log(cell, date, cellOptions)
      cell[0].className = "link disabled";
    }
  }

  ngOnInit(): void {
    $("#calendar").calendar({
      endCalendar: $("#calendar1"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: this.formatter1,
      onChange: (date) => {
        if ($("#calendar1raw").val() !== null) {
          var st1 = $("#calendar1raw")
            .val()
            .split(" ");
          var d2 = new Date(st1[1] - 543, TextDateTH.months.findIndex(obj => obj == st1[0]), 1);
          var d1 = date;
          this.month = this.monthDiff(d1, d2)
        }
      }
    });
    $("#calendar1").calendar({
      startCalendar: $("#calendar"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: this.formatter2,
      onChange: (date) => {
        if ($("#calendarraw").val() !== null) {
          var st1 = $("#calendarraw")
            .val()
            .split(" ");
          var d1 = new Date(st1[1] - 543, TextDateTH.months.findIndex(obj => obj == st1[0]), 1);
          var d2 = date;
          this.month = this.monthDiff(d1, d2)
        }
      }
    });
  }
  monthDiff(d1, d2) {
    var months;
    months = (d2.getFullYear() - d1.getFullYear()) * 12;
    months -= d1.getMonth() + 1;
    months += d2.getMonth();
    return months <= 0 ? 1 : months + 1;
  }

  onSubmit = (event: any) => {
    event.preventDefault();
    const date = event.target["calendar1raw"].value;
    const date_split = date.split(" "); // ,,,, 2561
    date_split[0] = digit(TextDateTH.months.findIndex(obj => obj == date_split[0]) + 1);
    const date_str = date_split[0] + "/" + date_split[1];
    const num = this.month + 1;
    this.router.navigate(["/analyst-basic-data-trader"], {
      queryParams: { from: date_str, month: num }
    });
  };

  callFn() {
    this.ajax.get(
      "working/test/list?no1=55",
      res => console.log(res.json().data),
      error => console.error(error)
    );
  }
}

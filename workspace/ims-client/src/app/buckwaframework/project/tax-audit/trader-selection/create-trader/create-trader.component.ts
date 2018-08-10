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
  selectedStartMonth: any;
  selectedSEndMonth: any;
  selectStartDateObj: any;
  selectEndDateObj: any;
  constructor(private ajax: AjaxService, private router: Router) {
    this.selectedStartMonth = null;
    this.selectedSEndMonth = null;
    this.selectStartDateObj = null;
    this.selectEndDateObj = null;
    this.formatter1 = formatter('ดป');
    this.formatter2 = formatter('ดป');
    this.formatter1.cell = (cell, date, cellOptions) => {  
      if(date.getMonth()%TextDateTH.months.findIndex(obj => obj == $("#calendarraw").val().split(" ")[0])) {      
      }
    }
    this.formatter2.cell = (cell, date, cellOptions) => {
      if(date.valueOf() > new Date().valueOf()){
        cell[0].className = "link disabled";
        return;
      }  

      if(this.selectedStartMonth % 2 === 0 ){
        if((date.getMonth() % 2) === 1){
          cell[0].className = "link";
        }else{
          cellOptions.disabled = true;
          cell[0].className = "link disabled";
        }
      }  else if(this.selectedStartMonth % 2 === 1) {
        if((date.getMonth() % 2) === 0){
          cell[0].className = "link";
        }else{
          cell[0].className = "link disabled";
        }
      }
    }
  }

  ngOnInit(): void {

console.log("this.formatter1 :", this.formatter1.cell);

    $("#calendar").calendar({
      endCalendar: $("#calendar1"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: this.formatter1,
      onChange: (date) => {
        this.selectedStartMonth = date.getMonth();
        this.selectStartDateObj = date;   
      }
    });
    $("#calendar1").calendar({
      startCalendar: $("#calendar"),
      maxDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: this.formatter2,
      onChange: (date) => {
     this.selectedSEndMonth = date.getMonth();
     this.selectEndDateObj = date;
      }
    });
  }
  monthDiff(d1, d2) {
    var months;
    months = (d2.getFullYear() - d1.getFullYear()) * 12;
    months -=  d1.getMonth() + 1;
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
      queryParams: { from: date_str, month: this.monthDiff(this.selectStartDateObj , this.selectEndDateObj)+1}
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

import { Component, OnInit } from '@angular/core';
import { MessageBarService } from '../../../../common/services/message-bar.service';
import { Router } from '@angular/router';
import { ExciseService } from '../../../../common/services/excise.service';
import { AjaxService } from '../../../../common/services/ajax.service';

declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-create-working-paper-trader',
  templateUrl: './create-working-paper-trader.component.html',
  styleUrls: []
})
export class CreateWorkingPaperTraderComponent implements OnInit {

  private count: number = 1;
  numbers: number[];
  from: any;
  before: any;
  last: any;
  num1: number[];
  num2: number[];
  percent1: string[];
  percent2: string[];
  analysNumbers: any;
  month: any;
  analysNumber: any;

  constructor(
    private messageBarService: MessageBarService,
    private router: Router,
    private ex: ExciseService
  ) {

  }

  ngOnInit() {
    //call ExciseService
    //var { before, last, from, month } = this.ex.getformValues();

    const URL = AjaxService.CONTEXT_PATH + "/working/test/getAnalysNumber";
    $.post(URL,
      function (data) {
        this.analysNumbers = data;
        var optionList = "";
        for (var i = 0; i < this.analysNumbers.length; i++) {
          
          optionList += "<option value='"+this.analysNumbers[i]+"'>" + this.analysNumbers[i] + "</option>";
        }
        document.getElementById('analysNumber').innerHTML = optionList;
        console.log(this.analysNumbers[0]);
        (<HTMLInputElement>document.getElementById("analysNumber")).value = this.analysNumbers[0];
        const URL = AjaxService.CONTEXT_PATH + "filter/exise/getStartEndDate";
        var analysNumber = this.analysNumbers[0];
         console.log((<HTMLInputElement>document.getElementById("analysNumber")).value);
         return $.post(URL, { analysNumber : analysNumber },
          function (returnedData) {
            console.log(returnedData);
            (<HTMLInputElement>document.getElementById("before")).value = returnedData[0];
            (<HTMLInputElement>document.getElementById("last")).value = returnedData[1];
            (<HTMLInputElement>document.getElementById("fromData")).value = returnedData[2];
            (<HTMLInputElement>document.getElementById("monthData")).value = returnedData[3];

            //set attribute
            for(var i=0; i<10; i++){
              $("#num1" + i).attr({
                "min": 0
              });
    
              $("#num2" + i).attr({
                "max": returnedData[3]
              });
            }

            return returnedData;
           
          }).fail(function () {
            console.log("error");
    
          });
      }).fail(function () {
        console.log("error");
      });
    
    this.numbers = [1];
    this.num1 = [];
    this.num2 = [];
    this.percent1 = [];
    this.percent2 = [];
    for (let i = 0; i < 10; i++) {
      this.num1.push(0);
      this.num2.push(0);
      this.percent1.push('0.00');
      this.percent2.push('0.00');
    }
    
  }

  onKeyUpMax = (e, i) => {
    e.preventDefault();
    var key = e.target.value;

    $("#num1" + i).attr({
      "max": parseInt(key) - 1
    });
  }

  onKeyUpMin = (e, i) => {
    e.preventDefault();
    var key = e.target.value;

    $("#num2" + i).attr({
      "min": parseInt(key) + 1
    });
  }

  onSend = () => {
    this.before = (<HTMLInputElement>document.getElementById("before")).value;
    this.last = (<HTMLInputElement>document.getElementById("last")).value;
    this.from = (<HTMLInputElement>document.getElementById("fromData")).value;
    this.month = (<HTMLInputElement>document.getElementById("monthData")).value;
    console.log(this.month);
    var currDate = new Date();
    var currYear = currDate.getFullYear() + 543;
    var from_split = this.from.split("/");
    var year_before = from_split[1];
    var yy = parseInt(year_before);
    console.log(yy);

    //call ExciseService
    this.ex.setformNumber(this.num1, this.num2, this.percent1, this.percent2, (<HTMLInputElement>document.getElementById("analysNumber")).value);
    this.ex.setformValues(this.before, this.last, this.from, this.month, currYear, yy);
    this.messageBarService.successModal('สร้างกระดาษทำการเรียบร้อยแล้ว', 'สำเร็จ');
    this.router.navigate(['/working-paper-1-trader']);
  }

  changeAnalysNumber = () =>{
    const URL = AjaxService.CONTEXT_PATH + "filter/exise/getStartEndDate";
    var analysNumber = (<HTMLInputElement>document.getElementById("analysNumber")).value;
     console.log((<HTMLInputElement>document.getElementById("analysNumber")).value);
     $.post(URL, { analysNumber : analysNumber },
      function (returnedData) {
        console.log(returnedData);
        (<HTMLInputElement>document.getElementById("before")).value = returnedData[0];
        (<HTMLInputElement>document.getElementById("last")).value = returnedData[1];
        (<HTMLInputElement>document.getElementById("fromData")).value = returnedData[2];
        (<HTMLInputElement>document.getElementById("monthData")).value = returnedData[3];
        console.log(returnedData[2]);

        //set attribute
        for(var i=0; i<10; i++){
          $("#num1" + i).attr({
            "min": 0
          });

          $("#num2" + i).attr({
            "max": returnedData[3]
          });
        }
       
       return returnedData;
      }).fail(function () {
        console.log("error");

      });
  }

  onAddField = () => {
    let num = this.numbers.length;
    if (num < 10) {
      this.numbers.push(num + 1);
    } else {
      this.messageBarService.errorModal('ไม่สามารถทำรายการได้', 'เกิดข้อผิดพลาด');
    }
  };

  onDelField = index => {
    this.numbers.splice(index, 1);
  };
}

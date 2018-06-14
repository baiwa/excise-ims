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
    var { before, last, from, month } = this.ex.getformValues();

    //set values
    this.before = before;
    this.last = last;
    this.from = from;
    this.month = month;
    // console.log(this.before);
    // console.log(this.last);
    // console.log(this.from);
    // console.log(this.month);

    const URL = AjaxService.CONTEXT_PATH + "/working/test/getAnalysNumber";
    $.post(URL,
      function (data) {
        this.analysNumbers = data;
        var optionList = "";
        for (var i = 0; i < this.analysNumbers.length; i++) {
          optionList += "<option value='"+this.analysNumbers[i]+"'>" + this.analysNumbers[i] + "</option>";
        }
        document.getElementById('analysNumber').innerHTML = optionList;
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
    //call ExciseService
    this.ex.setformNumber(this.num1, this.num2, this.percent1, this.percent2, (<HTMLInputElement>document.getElementById("analysNumber")).value);
    this.messageBarService.successModal('สร้างกระดาษทำการเรียบร้อยแล้ว', 'สำเร็จ');
    this.router.navigate(['/working-paper-1-trader']);
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

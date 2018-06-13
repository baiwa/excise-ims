import { Component, OnInit } from '@angular/core';
import { MessageBarService } from '../../../../common/services/message-bar.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
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

  constructor(
    private messageBarService: MessageBarService,
    private route: ActivatedRoute,
    private router: Router,
    private ex: ExciseService
  ) {

    this.numbers = [1];
    this.num1 = [];
    this.num2 = [];
    this.percent1 = [];
    this.percent2 = [];
    for (let i = 0; i < 10; i++) {
      this.num1.push(0);
      this.num2.push(0);
      this.percent1.push(0);
      this.percent2.push(0);
    }
  }
  from: any;
  before: any;
  last: any;
  num_month: any;
  num1: number[];
  num2: number[];
  percent1: number[];
  percent2: number[];
  analysNumbers: any;
  month: any;
  analysNumber: any;


  ngOnInit() {
    //call service
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
          optionList += "<option>" + this.analysNumbers[i] + "</option>";
        }
        document.getElementById('analysNumber').innerHTML = optionList;
      }).fail(function () {
        console.log("error");
      });
  }

  onSend = () => {
    //call ExciseService
    this.ex.setformNumber(this.num1, this.num2, this.percent1, this.percent2, this.analysNumber);
    this.messageBarService.successModal('สร้างกระดาษทำการเรียบร้อยแล้ว', 'สำเร็จ');
    this.router.navigate(['/working-paper-1-trader'],
    { queryParams: { num_month: this.num_month } }
    );

    // var myJsonString = JSON.stringify(this.num1);
    // console.log(myJsonString);
    // console.log(this.num1);
    // console.log(this.num2);
    // console.log(this.percent1);
    // console.log(this.percent2);
    
    // var param1 = this.form1;
    // var param2 = this.form2;
    // var param3 = this.month;

    // const URL = AjaxService.CONTEXT_PATH + "/working/test/createWorkSheet";
    // $.post(URL, {  },
    //   function (data) {
    //     this.router.navigate(['/working-paper-1-trader']);
    //     { queryParams: { analysNumber: data } }
    //   );
    //   }).fail(function () {
    //     console.log("error");
    //   });
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

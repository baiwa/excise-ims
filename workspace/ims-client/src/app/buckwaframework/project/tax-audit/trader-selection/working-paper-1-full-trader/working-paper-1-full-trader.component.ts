import { Component, OnInit } from '@angular/core';
import { ExciseService } from '../../../../common/services';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-working-paper-1-full-trader',
  templateUrl: './working-paper-1-full-trader.component.html',
  styleUrls: ['./working-paper-1-full-trader.component.css']
})
export class WorkingPaper1FullTraderComponent implements OnInit {

  private listItem: any[];
  before: any;
  last: any;
  num1: any;
  num2: any;
  _num1: any;
  _num2: any;
  percent1: any;
  percent2: any;
  _percent1: any;
  _percent2: any;
  from: any;
  month: any;
  analysNumber: any;

  constructor(
    private route: ActivatedRoute,
    private ex: ExciseService
  ) {
    this._num1 = new Array();
    this._num2 = new Array();
    this._percent1 = new Array();
    this._percent2 = new Array();
  }

  ngOnInit() {
    //call service
    var { before, last, from, month } = this.ex.getformValues();
    var { num1, num2, percent1, percent2, analysNumber } = this.ex.getformNumber();

    //set values
    this.before = before;
    this.last = last;
    this.from = from;
    this.month = month;
    this.num1 = num1;
    this.num2 = num2;
    this.percent1 = percent1;
    this.percent2 = percent2;
    this.analysNumber = analysNumber;

    //check values in array == 0
    for (var i = 0; i < this.num1.length; i++) {
      if (this.num1[i] !== 0 && this.num2[i] !== 0) {
        this._num1.push(this.num1[i]);
        this._num2.push(this.num2[i]);
        this._percent1.push(this.percent1[i]);
        this._percent2.push(this.percent2[i]);
        // console.log(index);
        // break;
      }
    }

    this.listItem = ["น้ำมัน"
      , "เครื่องดื่ม"
      , "ยาสูบ"
      , "ไพ่"
      , "แก้วและเครื่องแก้ว"
      , "รถยนต์"
      , 'พรมและสิ่งทอปูพื้น'
      , "แบตเตอรี่"
      , "ไนท์คลับและดิสโกเธค"
      , "สถานอาบน้ำหรืออบตัวและนวด"
      , "สนามแข่งม้า"
      , 'สนามกอล์ฟ'
      , "รวม"];

  }

}

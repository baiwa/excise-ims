import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../../../../common/services/ajax.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { ExciseService } from '../../../../common/services/excise.service';

declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-working-paper-1-trader',
  templateUrl: './working-paper-1-trader.component.html',
  styleUrls: ['./working-paper-1-trader.component.css']
})
export class WorkingPaper1TraderComponent implements OnInit {

  userManagementDt: any;
  router: any;
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
    var { before, last, num1, num2, percent1, percent2 } = this.ex.getNumber();
    this.before = before;
    this.last = last;
    this.num1 = num1;
    this.num2 = num2;
    this.percent1 = percent1;
    this.percent2 = percent2;

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

    this.initDatatable();
  }

  ngAfterViewInit() {

  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "/working/test/list";
    this.userManagementDt = $('#userManagementDt').DataTable({
      "lengthChange": false,
      "searching": false,
      "ordering": false,
      "pageLength": 10,
      "processing": true,
      "serverSide": true,
      "paging": true,
      "pagingType": "full_numbers",

      "ajax": {
        "type": "POST",
        "url": URL

      },
      "columns": [

        { "data": "exciseRegisttionNumberId", "className": "center" },
        { "data": "exciseId", "className": "center" },
        { "data": "exciseOperatorName" },
        { "data": "exciseFacName" },
        { "data": "exciseArea" },
        { "data": "exciseFacAddress", "className": "center" },
        { "data": "exciseRegisCapital", "className": "center" },
        { "data": "change", "className": "center" },
        { "data": "payingtax", "className": "center" },
        { "data": "no1" },
        { "data": "no2" },
        { "data": "no3" },
        { "data": "coordinates" },
        { "data": "sector" },
        { "data": "industrialAddress" },
        { "data": "registeredCapital" },
        { "data": "status" },
        { "data": "exciseFirstTaxReceiveAmount1", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount2", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount3", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount4", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount5", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount6", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount7", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount8", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount9", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount10", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount11", "className": "center" },
        { "data": "exciseFirstTaxReceiveAmount12", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount1", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount2", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount3", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount4", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount5", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount6", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount7", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount8", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount9", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount10", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount11", "className": "center" },
        { "data": "exciseLatestTaxReceiveAmount12", "className": "center" }

      ],


    });
    let tableId = '#userManagementDt';
    let backgroundRowColor = (data, type, row, meta) => {
      if (!data) {
        let table = $(tableId).DataTable();
        let cell = table.cell(meta.row, meta.col).node();
        $(cell).addClass("bg-row-highlight");
      }

      return data;
    }

  }

}

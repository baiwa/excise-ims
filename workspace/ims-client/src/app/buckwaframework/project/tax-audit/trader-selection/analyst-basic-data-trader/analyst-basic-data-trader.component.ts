import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../../../../common/services/ajax.service';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { TextDateTH, digit } from '../../../../common/helper/datepicker';
import { window } from 'rxjs/operators';
declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-analyst-basic-data-trader',
  templateUrl: './analyst-basic-data-trader.component.html',
  styleUrls: ['./analyst-basic-data-trader.component.css']
})
export class AnalystBasicDataTraderComponent implements OnInit {
  listMenu: any[] = [];
  showmenu: boolean = true;
  userManagementDt: any;
  month: any;
  from: any;
  form1: any;
  form2: any;
  private listItem: any[];
  constructor(private route: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
    this.listMenu = ["น้ำมัน"
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

    // subscribe to router event
    this.from = this.route.snapshot.queryParams["from"];
    this.month = this.route.snapshot.queryParams["month"];
    //split function
    var from_split = this.from.split("/");
    var currDate = new Date();
    var currYear = currDate.getFullYear() + 543;
    //default month & year
    var month = from_split[0];
    var year_before = from_split[1];

    var m = parseInt(month) + 1;
    var mm = parseInt(this.month);
    var yy = parseInt(year_before);
    var trHeaderColumn = "";

    var items: string[] = [];
    for (var i = 1; i <= mm; i++) {
      m = m - 1;
      if (m == 0) {
        m = 12;
        yy = yy - 1;
      }
      items.push('<th style="text-align: center !important">' + TextDateTH.monthsShort[m - 1] + ' ' + (yy + "").substr(2) + '</th>');

    }
    for (var i = items.length - 1; i >= 0; i--) {
      trHeaderColumn += items[i];
    }
    document.getElementById('trDrinamic').innerHTML = '<tr><th rowspan="2" style="text-align: center !important">ลำดับ</th> '
      + '<th rowspan="2" style="text-align: center !important">ทะเบียนสรรพสามิต เดิม/ใหม่</th> '
      + '<th rowspan="2" style="text-align: center !important">ชื่อผู้ประกอบการ</th> '
      + '<th rowspan="2" style="text-align: center !important">ชื่อโรงอุตสาหกรรม/สถานบริการ</th> '
      + '<th rowspan="2" style="text-align: center !important">พื้นที่</th> '
      + '<th colspan="2" style="text-align: center !important">การชำระภาษีในสภาวะปกติ (บาท)</th> '
      + '<th rowspan="2" style="text-align: center !important">เปลี่ยนแปลง (ร้อยละ)</th> '
      + '<th rowspan="2" style="text-align: center !important">ชำระภาษี(เดือน)</th> '
      + '<th colspan="3" style="text-align: center !important">การตรวจสอบภาษีย้อนหลัง 3 ปีงบประมาณ</th> '
      + '<th rowspan="2" style="text-align: center !important">ภาค</th> '
      + '<th rowspan="2" style="text-align: center !important">พิกัด</th> '
      + '<th rowspan="2" style="text-align: center !important">ที่อยู่โรงอุตสาหกรรม/สถานบริการ</th> '
      + '<th rowspan="2" style="text-align: center !important">ทุนจดทะเบียน</th> '
      + '<th rowspan="2" style="text-align: center !important">สถานะ/วันที่</th> '
      + '<th colspan="' + (this.month / 2) + '" style="text-align: center !important">การชำระภาษี ' + (this.month / 2) + ' เดือนแรก</th> '
      + '<th colspan="' + (this.month / 2) + '" style="text-align: center !important">การชำระภาษี ' + (this.month / 2) + ' เดือนหลัง </th> '
      + '</tr>'
      + '<tr><th style="border-left: 1px solid rgba(34,36,38,.1);">' + this.month / 2 + ' เดือนแรก</th>'
      + '<th style="text-align: center !important">' + this.month / 2 + ' เดือนหลัง </th>'
      + '<th style="text-align: center !important">' + (currYear - 3) + '</th>'
      + '<th style="text-align: center !important">' + (currYear - 2) + '</th>'
      + '<th style="text-align: center !important">' + (currYear - 1) + '</th>'
      + trHeaderColumn + '</tr>';

    //show values
    var sum_month = TextDateTH.months[m - 1];
    this.form1 = sum_month + " " + yy;
    var sum_month2 = TextDateTH.months[parseInt(month) - 1];
    this.form2 = sum_month2 + " " + parseInt(year_before);
    var dataInDataTalbe = '';
    this.initDatatable();

  }

  onSend = () => {
    this.router.navigate(
      ['/create-working-paper-trader'],
      { queryParams: { before: this.form1, last: this.form2, num_month: this.month } }
    );
  }

  initDatatable(): void {
    var d = new Date();
    d.setFullYear(parseInt(this.from[1]));
    d.setMonth(parseInt(this.from[0]));
    const URL = AjaxService.CONTEXT_PATH + "/working/test/list";
    var json = "";
    json += ' { "lengthChange": false, ';
    json += ' "searching": false, ';
    json += ' "select": true, ';
    json += ' "ordering": true, ';
    json += ' "pageLength": 10, ';
    json += ' "processing": true, ';
    json += ' "serverSide": true, ';
    json += ' "paging": true, ';
    json += ' "pagingType": "full_numbers", ';
    json += ' ';
    json += ' "ajax": { ';
    json += ' "type": "POST", ';
    json += ' "url": "' + URL + '", ';
    json += ' "data": { ';
    json += ' "startBackDate": ' + d.getTime() + ', ';
    json += ' "month": ' + this.month + ' ';
    json += ' } ';
    json += ' }, ';
    json += ' "columns": [ ';
    json += ' { "data": "exciseRegisttionNumberId","className":"center" }, ';
    json += ' { "data": "exciseId","className":"center" }, ';
    json += ' { "data": "exciseOperatorName" }, ';
    json += ' { "data": "exciseFacName" }, ';
    json += ' { "data": "exciseArea" }, ';
    json += ' { "data": "exciseFacAddress" ,"className":"center" }, ';
    json += ' { "data": "exciseRegisCapital","className":"center" }, ';
    json += ' { "data": "change","className":"center" }, ';
    json += ' { "data": "payingtax" ,"className":"center"}, ';
    json += ' { "data": "no1" }, ';
    json += ' { "data": "no2" }, ';
    json += ' { "data": "no3" }, ';
    json += ' { "data": "coordinates" }, ';
    json += ' { "data": "sector" }, ';
    json += ' { "data": "industrialAddress" }, ';
    json += ' { "data": "registeredCapital" }, ';
    json += ' { "data": "status" }, ';

    for (var i = 0; i < this.month / 2; i++) {
      json += ' { "data": "exciseFirstTaxReceiveAmount' + (i + 1) + '" ,"className":"center"}, ';

    }
    for (var i = 0; i < this.month / 2; i++) {
      if (i != (this.month / 2) - 1) {
        json += ' { "data": "exciseLatestTaxReceiveAmount' + (i + 1) + '" ,"className":"center"}, ';
      } else {
        json += ' { "data": "exciseLatestTaxReceiveAmount' + (i + 1) + '" ,"className":"center"} ';
      }
    }

    json += '] } ';
    let jsonMaping = JSON.parse(json);
    this.userManagementDt = $('#userManagementDt').DataTable(jsonMaping);

    var table = $('#userManagementDt').DataTable();
    var pickedup;
    $('#userManagementDt tbody').on('click', 'tr', function () {
      (<HTMLInputElement>document.getElementById("exciseId")).value = table.row(this).data().exciseId;
      if (this.pickedup != null) {
        this.pickedup.css("background-color", "white");
      }
      $(this).css("background-color", "rgb(197,217,241)");
      pickedup = $(this);
    });
  }

}

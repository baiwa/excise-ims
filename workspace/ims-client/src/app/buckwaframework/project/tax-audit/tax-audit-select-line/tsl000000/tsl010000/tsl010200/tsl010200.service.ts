import { Injectable } from '@angular/core';
declare var $: any;
@Injectable()
export class Tsl010200Service {

  // ==>params
  data: any;
  json: any;
  jsonColumn: any;
  constructor(
  ) { }

  createHeader() {
    $("#trDrinamic").html(
      '<th>เลขทะเบียนสรรพสามิต</th>' +
      '<th>ชื่อผู้ประกอบการ/โรงอุตสาหกรรม</th>' +
      '<th>ที่อยู่สถานบริการ/โรงอุตสาหกรรม</th>' +
      '<th>พิกัดสินค้า/บริการ</th>' +
      '<th>สรรพสามิตภาค</th>' +
      '<th>สรรพสามิตพื้นที่</th>'

      // '<th colspan="' + this.month / 2 + '" style="text-align: center !important">การชำระภาษี ' + this.month / 2 + " เดือนแรก</th> " +
      // '<th colspan="' + this.month / 2 + '" style="text-align: center !important">การชำระภาษี ' + this.month / 2 + " เดือนหลัง </th> " +
      // '<th rowspan="2" style="text-align: center !important">พิกัดอื่นๆ</th> ' +
      // "</tr>" +
      // '<tr><th style="border-left: 1px solid rgba(34,36,38,.1);">' +
      // this.month / 2 +
      // " เดือนแรก</th>" +
      // '<th style="text-align: center !important">' +
      // this.month / 2 +
      // " เดือนหลัง </th>" +
      // '<th style="text-align: center !important">' +
      // (currYear - 3) +
      // "</th>" +
      // '<th style="text-align: center !important">' +
      // (currYear - 2) +
      // "</th>" +
      // '<th style="text-align: center !important">' +
      // (currYear - 1) +
      // "</th>" +
      // trHeaderColumn +
      // "</tr>"
    );

  }
  createJson() {
  }

  createJsonColumn() {

  }

  datatable() {
    $("tableOne").DataTableTh();
    $("tableTwo").DataTableTh();
  }
}

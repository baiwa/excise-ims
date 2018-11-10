import { Injectable } from '@angular/core';
import { Utils } from 'helpers/utils';
declare var $: any;
@Injectable()
export class Ope0442Service {
      //==> params
      table: any;
      datatable = (id: any) => {
          this.table = $("#dataTable").DataTableTh({
              "serverSide": false,
              "processing": true,
              "scrollX": true,
              "searching": true,
              "paging": true,
              "ajax": {
                  "url": '/ims-webapp/api/ta/opo0441/findDetails',
                  "contentType": "application/json",
                  "type": "POST",
                  "data": (d) => {
                      return JSON.stringify($.extend({}, d, {
                          "id": id,
                          // "searchFlag": this.searchFlag,
                          // "dataExcel": this.dataExcel
                      }));
                  },
              },
              "columns": [
                  {
                      "data": "order",
                      "render": function (data, type, row, meta) {
                          return meta.row + meta.settings._iDisplayStart + 1;
                      },
                      "className": "ui center aligned"
                  }, {
                      "data": "order",
                      "className": "ui left aligned"
                  }, {
                      "data": "amount1Out",
                      "className": "ui right aligned",
                      "render": (data) => {
                          return Utils.moneyFormatDecimal(data);
                      }
                  }, {
                      "data": "amount2Out",
                      "className": "ui right aligned",
                      "render": (data) => {
                          return Utils.moneyFormatDecimal(data);
                      }
                  }, {
                      "data": "amountTable2",
                      "className": "ui right aligned",
                      "render": (data) => {
                          return Utils.moneyFormatDecimal(data);
                      }
                  }, {
                      "data": "diff",
                      "className": "ui right aligned",
                      "render": (data) => {
                          if (data != 0) {
                              return '<span class="r-mark-tr">' + Utils.moneyFormatDecimal(data) + '</span>';
                          }
                          return '<span class="g-mark-tr">' + Utils.moneyFormatDecimal(data) + '</span>';
                      }
                  }
              ],
              "drawCallback": (settings) => {
                  $('.r-mark-tr').closest('td').addClass('background-color : red');
                  $('.g-mark-tr').closest('td').addClass('background-color : green');
              }
          });
          // this.table.clear().draw();
          // this.table.rows.add(this.data); // Add new data
          // this.table.columns.adjust().draw(); // Redraw the DataTable\
      }
}
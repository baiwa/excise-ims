import { Injectable } from '@angular/core';
import { Utils } from 'helpers/utils';
import { MessegeConstants } from 'helpers/messegeConstants';
declare var $: any;
@Injectable()
export class Int0616Service {

  // ==> params
  dataTable1: any;
  dataTable2: any;
  constructor() { }

  callDatatable1 = () => {
    this.dataTable1 = $("#dataTable1").DataTableTh({
      "serverSide": false,
      "processing": true,
      "scrollX": true,
      "paging": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int0616/list1',
        "contentType": "application/json",
        "type": "POST",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {

          }));
        },
      },
      columns: [
        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        },
        {
          data: "name",         
        },
        {
          data: "order",
          render : (data,row)=>{
            return this.checkType(data);
          }          
        },
        {
          data: "withdrawAmount",          
        }
      ],
      columnDefs: [
        {
          targets: [0],
          className: "center"
        },
        {
          targets: [3],
          className: "right"
        }
      ]

      // rowCallback: (row, data, index) => {
      //   console.log(data);
      // }
    });
  }
  callDatatable2 = () => {
    this.dataTable2 = $("#dataTable2").DataTableTh({
      "serverSide": false,
      "processing": true,
      "scrollX": true,
      "paging": true,
      "ajax": {
        "url": '/ims-webapp/api/ia/int0616/list2',
        "contentType": "application/json",
        "type": "POST",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {

          }));
        },
      },
      columns: [
        {
          data: "name",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "ui center aligned"
        },
        {
          data: "name",
          className: "ui left aligned"
        },
        {
          data: "position",
          className: "ui left aligned"
        }, {
          data: "flag",
          className: "ui left aligned",
          render: (data, row) => {
            return this.checkType(data);
          }
        }, {
          data: "money",
          className: "ui right aligned",
          render: (data, row) => {
            return Utils.moneyFormatDecimal(data);
          }
        },
      ]
    });
  }

  
  checkType(type) {
    var result = '';
    if (type == MessegeConstants.WIDTHDRAW.HOME_CODE) {
      result = MessegeConstants.WIDTHDRAW.HOME
    }
    if (type == MessegeConstants.WIDTHDRAW.MEDICAL_CODE) {
      result = MessegeConstants.WIDTHDRAW.MEDICAL
    }
    if (type == MessegeConstants.WIDTHDRAW.TUITO_CODE) {
      result = MessegeConstants.WIDTHDRAW.TUITO
    }
    return result;
  }
}

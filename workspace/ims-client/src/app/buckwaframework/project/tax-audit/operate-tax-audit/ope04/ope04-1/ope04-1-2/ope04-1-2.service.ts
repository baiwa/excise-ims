import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AjaxService } from 'services/ajax.service';
import { Utils } from 'helpers/utils';
declare var $: any;
@Injectable()
export class Ope0412Service {
   //==> params
   table: any;
   constructor() { }

   datatable = (id : any) => {
       this.table = $("#dataTable").DataTableTh({
           "serverSide": false,
           "processing": true,
           "scrollX": true,
           "searching": true,
           "paging": true,
           "ajax": {
               "url": '/ims-webapp/api/ta/ope0412/findAll',
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
               },{
                   "data": "order",
                   "className": "ui left aligned"
               },{
                   "data": "taxInv",
                   "className": "ui right aligned",
                   "render": (data) => {
                       return Utils.moneyFormatInt(data);
                   }
               },{
                   "data": "daybook",
                   "className": "ui right aligned",
                   "render": (data) => {
                       return Utils.moneyFormatInt(data);
                   }
               },{
                   "data": "monthBook",
                   "className": "ui right aligned",
                   "render": (data) => {
                       return Utils.moneyFormatInt(data);
                   }
               },{
                   "data": "externalData",
                   "className": "ui right aligned"
               },{
                   "data": "maxalues",
                   "className": "ui right aligned",
                   "render": (data) => {
                       return Utils.moneyFormatInt(data);
                   }
               },{
                   "data": "result",
                   "className": "ui right aligned",
                   "render": (data) => {
                       return Utils.moneyFormatInt(data);
                   }
               }
           ],
           "drawCallback": (settings) => {
               $('.r-mark-tr').closest('td').addClass('background-color : red');
               $('.g-mark-tr').closest('td').addClass('background-color : green');
           }
       });
   }
}
import { Injectable } from '@angular/core';
import { Utils } from 'helpers/utils';
declare var $: any;
@Injectable()
export class Ope0461Service {

    //==> params
    table: any;
    constructor() { }

    datatable = (taTaxReduceWsHdrId : any) => {
        this.table = $("#dataTable").DataTableTh({
            "serverSide": false,
            "processing": true,
            "scrollX": true,
            "searching": true,
            "paging": true,
            "ajax": {
                "url": '/ims-webapp/api/ta/opo0461/findAll',
                "contentType": "application/json",
                "type": "POST",
                "data": (d) => {
                    return JSON.stringify($.extend({}, d, {
                        "taTaxReduceWsHdrId": taTaxReduceWsHdrId,
                        // "searchFlag": this.searchFlag,
                        // "dataExcel": this.dataExcel
                    }));
                },
            },
            "columns": [
                {
                    "data": "list",
                    "render": function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    },
                    "className": "ui center aligned"
                },{
                    "data": "list",
                    "className": "ui left aligned"
                },{
                    "data": "totalTax",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },{
                    "data": "pdtAmount1",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },{
                    "data": "taxPerPdt",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                },{
                    "data": "billNo",
                    "className": "ui center aligned"
                },{
                    "data": "taxAmount",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },{
                    "data": "pdtSAmount2",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },{
                    "data": "maxValues",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                },{
                    "data": "result",
                    "className": "ui right aligned",
                    "render": (data) => {
                        if (data != 0) {
                            return '<span class="r-mark-tr">' + Utils.moneyFormatDecimal(data) + '</span>';
                        }
                        return '<span class="g-mark-tr">' + Utils.moneyFormatDecimal(data) + '</span>';                        
                    }
                },
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
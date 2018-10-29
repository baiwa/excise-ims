import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from 'services/message-bar.service';
import { Utils } from 'helpers/utils';
declare var $: any;

@Injectable()
export class Ope048Service {

    //==> params
    searchFlag: String = "FALSE";
    table: any;
    constructor(
        private ajax: AjaxService,
        private message: MessageBarService
    ) { }

    findExciseId = (): Promise<any> => {
        let url = "ta/opo048/exciseidList";
        return new Promise((resolve, reject) => {
            this.ajax.get(url, res => {
                resolve(res.json())
            })
        });
    }
    findByExciseId = (exciseId): Promise<any> => {
        let url = "ta/opo048/findByExciseId";
        return new Promise((resolve, reject) => {
            this.ajax.post(url, JSON.stringify(exciseId), res => {
                resolve(res.json())

            })
        });
    }

    search = () => {
        this.searchFlag = "TRUE";
        if (this.table != null) {
            this.table.destroy();
        }
        this.dataTable();
        // $("#dataTable").DataTableTh().ajax.reload();
    }

    claer = () => {
        $("#Dtable").hide()
        this.searchFlag = "FALSE";
        $("#exciseId").dropdown('restore defaults');
        $("#dateFrom").val('');
        $("#dateTo").val('');
        $("#fileName").val('');
        //this.dataExcel = null;
        if (this.table != null) {
            this.table.destroy();
        }
        this.dataTable();
    }
    dataTable = () => {
        this.table = $("#dataTable").DataTableTh({
            "serverSide": false,
            "processing": true,
            "scrollX": true,
            "paging": true,
            "ajax": {
                "url": '/ims-webapp/api/ta/opo048/findAll',
                "contentType": "application/json",
                "type": "POST",
                "data": (d) => {
                    return JSON.stringify($.extend({}, d, {
                        "exciseId": $("#exciseId").val(),
                        "searchFlag": this.searchFlag,
                        // "dataExcel": this.dataExcel
                    }));

                },
            },
            "columns": [
                {
                    "data": "taExciseAcc0502DtlId",
                    "render": function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    },
                    "className": "ui center aligned"
                },{
                    "data": "taExciseAcc0307List",
                    "className": "ui left aligned"
                },{
                    "data": "taExcisePrice",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },{
                    "data": "taExciseAcc0307Price",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },{
                    "data": "taExciseAcc0307Price",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                },{
                    "data": "taExciseAcc0307Price",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },{
                    "data": "taExciseAcc0307Price1",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },{
                    "data": "taExciseAcc0307Price1",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },
            ],
            // "drawCallback": (settings) => {
            //     $('.r-mark-tr').closest('td').addClass('background-color : red');
            //     $('.g-mark-tr').closest('td').addClass('background-color : green');
            // }
        });
    }
}
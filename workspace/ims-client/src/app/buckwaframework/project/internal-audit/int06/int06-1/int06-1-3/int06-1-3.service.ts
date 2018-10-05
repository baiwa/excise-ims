import { Injectable } from "@angular/core";
import { Form } from "@angular/forms";
import { From } from "projects/internal-audit/int06/int06-1/int06-1-3/form.model";
import { AjaxService } from "services/ajax.service";

declare var $: any;

@Injectable()

export class Int0613Service {

    table: any;
    form: From = new From();
    sectorList: any;
    araeList: any;
    constructor(
        private ajax: AjaxService

    ) { }

    sector = () => {
        let url = "ia/int0613/sector";
        this.ajax.get(url, res => {
            this.sectorList = res.json();
        });
        return this.sectorList;
    }

    area = () => {
        let url = "combobox/controller/sector";
    }

    search = () => {
        this.form.searchFlag = "TRUE";
        $("#dataTable").DataTable().ajax.reload();

    }

    dataTable = () => {
        this.table = $("#dataTable").DataTable({
            "serverSide": true,
            "searching": false,
            "ordering": false,
            "processing": true,
            "scrollX": true,
            "fixedColumns": {
                "leftColumns": 3
            },
            "ajax": {
                "url": '/ims-webapp/api/ia/int06121/findAll',
                "contentType": "application/json",
                "type": "POST",
                "data": (d) => {
                    return JSON.stringify($.extend({}, d, {
                        // "accountId": this.model.accountId,
                        // "accountName": this.model.accountName,
                        "searchFlag": this.form.searchFlag
                    }));
                },
            },
            "columns": [
                {
                    "data": "accountId",
                    "render": function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    },
                    "className": "ui center aligned"
                }, {
                    "data": "accountId"
                }, {
                    "data": "accountName",
                    "className": "ui center aligned",
                }, {
                    "data": "serviceReceive",
                    "className": "ui center aligned"
                }, {
                    "data": "suppressReceive",
                    "className": "ui center aligned"
                }, {
                    "data": "budgetReceive",
                    "className": "ui center aligned"
                }, {
                    "data": "sumReceive",
                    "className": "ui center aligned"
                }, {
                    "data": "serviceWithdraw",
                    "className": "ui center aligned"
                }, {
                    "data": "suppressWithdraw",
                    "className": "ui center aligned"
                }, {
                    "data": "budgetWithdraw",
                    "className": "ui center aligned"
                }, {
                    "data": "sumWithdraw",
                    "className": "ui center aligned"
                }, {
                    "data": "serviceBalance",
                    "className": "ui center aligned"
                }, {
                    "data": "suppressBalance",
                    "className": "ui center aligned"
                }, {
                    "data": "budgetBalance",
                    "className": "ui center aligned"
                }, {
                    "data": "sumBalance",
                    "className": "ui center aligned"
                }, {
                    "data": "averageCost",
                    "className": "ui center aligned"
                }, {
                    "data": "averageGive",
                    "className": "ui center aligned"
                }, {
                    "data": "averageFrom",
                    "className": "ui center aligned"
                }, {
                    "data": "averageComeCost",
                    "className": "ui center aligned"
                }, {
                    "data": "moneyBudget",
                    "className": "ui center aligned"
                }, {
                    "data": "moneyOut",
                    "className": "ui center aligned"
                }, {
                    "data": "note",
                    "className": "ui center aligned"
                }, {
                    "data": "note",
                    "render": function (data, type, row) {
                        var btn = '';
                        btn += '<button class="ui mini yellow button btn-edit">แก้ไข</button>';
                        btn += '<button class="ui mini red button btn-delete">ลบ</button>';
                        return btn;
                    },
                    "className": "ui center aligned"
                }
            ]
        });
    }
}
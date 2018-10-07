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

    sector = (sectorCb: Function) => {
        let url = "ia/int0613/sector";
        this.ajax.get(url, res => {
            sectorCb(res.json());
        });
    }

    area = (idMaster, areaCb: Function) => {
        let url = "ia/int0613/area";
        this.ajax.post(url, idMaster, res => {
            areaCb(res.json());
        });
    }

    year = (yearCb: Function) => {
        let url = "ia/int0613/year";
        this.ajax.get(url, res => {
            yearCb(res.json());
        });
    }
    search = () => {
        this.form.searchFlag = "TRUE";
        $("#dataTable").DataTable().ajax.reload();

    }
    clear = () => {
        this.form.searchFlag = "FALSE";
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
                "url": '/ims-webapp/api/ia/int0613/findAll',
                "contentType": "application/json",
                "type": "POST",
                "data": (d) => {
                    return JSON.stringify($.extend({}, d, {
                        "sector": $("#sector").val(), 
                        "area" : $("#area").val(),
                        "year": $("#year").val(),
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
                    "data": "experimentalBudget",
                    "className": "ui center aligned"
                }, {
                    "data": "differenceExperimentalBudget",
                    "className": "ui center aligned"
                }, {
                    "data": "ledger",
                    "className": "ui center aligned"
                }, {
                    "data": "differenceledger",
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
                }
            ]
        });
    }
}
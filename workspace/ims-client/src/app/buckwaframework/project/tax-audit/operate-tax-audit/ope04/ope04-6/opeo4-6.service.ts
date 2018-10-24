import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { Utils } from 'helpers/utils';
import { MessageBarService } from 'services/message-bar.service';


declare var $: any;

@Injectable()
export class Opeo46Service {

    // ==> params
    table: any;
    dataRespon: any;
    searchFlag: String = "FALSE";
    fileName: any;
    dataExcel: any = null;
    constructor(
        private ajax: AjaxService,
        private message: MessageBarService
    ) { }

    findExciseId = (): Promise<any> => {
        let url = "ta/opo046/exciseidList";
        return new Promise((resolve, reject) => {
            this.ajax.get(url, res => {
                resolve(res.json())
            })
        });
    }

    findByExciseId = (exciseId): Promise<any> => {
        let url = "ta/opo046/findByExciseId";
        return new Promise((resolve, reject) => {
            this.ajax.post(url, JSON.stringify(exciseId), res => {
                resolve(res.json())

            })
        });
    }

    onChangeUpload = async (event: any) => {
        if (event.target.files && event.target.files.length > 0) {
            let reader = new FileReader();
            reader.onload = (e: any) => {
                const f = {
                    name: event.target.files[0].name,
                    type: event.target.files[0].type,
                    value: e.target.result
                };
                this.fileName = [f];
                console.log(this.fileName);
            };
            reader.readAsDataURL(event.target.files[0]);
        }
    };

    upload = (form: any) => {
        let url = "ta/opo046/upload";
        this.ajax.upload(url,form, success => {            
            this.dataExcel = success.json();            
            if (this.dataExcel.data.length == 0) {
                this.message.errorModal('ไม่สามารถอัปโหลดไฟล์');
            }
        }, error => {
            console.log("Fail!");
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
        this.searchFlag = "FALSE";
        $("#exciseId").dropdown('restore defaults');
        $("#dataFrom").val('');
        $("#dateTo").val('');
    }
    dataTable = () => {
        this.table = $("#dataTable").DataTableTh({
            "serverSide": false,
            "processing": true,
            "scrollX": true,
            "paging": true,
            "ajax": {
                "url": '/ims-webapp/api/ta/opo046/findAll',
                "contentType": "application/json",
                "type": "POST",
                "data": (d) => {
                    return JSON.stringify($.extend({}, d, {
                        "exciseId": $("#exciseId").val(),
                        "searchFlag": this.searchFlag,
                        //"dataExcel" : this.dataExcel
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
                }, {
                    "data": "taExciseAcc0502DtlList",
                    "className": "ui left aligned"
                },
                {
                    "data": "taxAmount",
                    "className": "ui right aligned"
                },
                {
                    "data": "amount",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                },
                {
                    "data": "taxPerAmount",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                },
                {
                    "data": "receiptNumber",
                    "className": "ui center aligned"
                },
                {
                    "data": "taxNumber",
                    "className": "ui center aligned"
                },
                {
                    "data": "volume",
                    "className": "ui center aligned"
                },
                {
                    "data": "unit",
                    "className": "ui center aligned"
                },
                {
                    "data": "unit",
                    "className": "ui center aligned"
                },
            ]
        });
        // this.table.clear().draw();
        // this.table.rows.add(this.data); // Add new data
        // this.table.columns.adjust().draw(); // Redraw the DataTable\


    }
}

import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from 'services/message-bar.service';
import { Utils } from 'helpers/utils';
import { resolve, reject } from 'q';
declare var $: any;

@Injectable()
export class Ope048Service {

    //==> params
    fileName: any;
    dataExcel: any = null;
    searchFlag: String = "FALSE";
    table: any;
    uploadFlag: boolean = false;

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

    onChangeUpload = async (event: any): Promise<any> => {
        return new Promise((resolve, reject) => {
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
                resolve(this.fileName);
            }
        });
    };

    upload = (form: any) => {
        let url = "ta/opo048/upload";
        if (Utils.isNotNull) {
            this.ajax.upload(url, form, success => {
                this.dataExcel = success.json();
                this.uploadFlag = true;
                if (Utils.isNull(this.dataExcel)) {
                    this.message.errorModal('ไม่สามารถอัปโหลดไฟล์');
                }
            }).then(() => {
                this.table.ajax.reload();
            });
        }
    }

    search = (): Promise<any> => {
        return new Promise((resolve, reject) => {
            this.searchFlag = "TRUE";
            if (this.table != null) {
                this.table.destroy();
            }
            this.dataTable();
            resolve();
        });
        // $("#dataTable").DataTableTh().ajax.reload();
    }

    claer = () => {
        $("#Dtable").hide()
        this.searchFlag = "FALSE";
        $("#exciseId").dropdown('restore defaults');
        $("#dateFrom").val('');
        $("#dateTo").val('');
        $("#fileName").val('');
        this.uploadFlag = false;
        this.dataExcel = null;
        if (this.table != null) {
            this.table.destroy();
        }
        this.dataTable();
    }
    sort(value1, value2, value3) {
        (Utils.isNull(value1) ? 0 : value1);
        (Utils.isNull(value2) ? 0 : value2);
        (Utils.isNull(value3) ? 0 : value3);
        let temp = value1;
        if (temp < value2) temp = value2;
        if (temp < value3) temp = value3;

        return temp;
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
                        "dataExcel": this.dataExcel
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
                    "data": "taExciseAcc0307List",
                    "className": "ui left aligned"
                }, {
                    "data": "taExcisePrice",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                }, {
                    "data": "excelPriceout",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                }, {
                    "data": "taExciseAcc0307Price",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                }, {
                    "data": "taExciseAcc0307Price",
                    "className": "ui right aligned",
                    "render": (data, type, row, meta) => {
                        let result = this.sort(row.taExcisePrice, row.excelPriceout, row.taExciseAcc0307Price);
                        return Utils.moneyFormatDecimal(result);
                    }
                }, {
                    "data": "taExciseAcc0307Price1",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                }, {
                    "data": "taExciseAcc0307Price1",
                    "className": "ui right aligned",
                    "render": (data, type, row, meta) => {
                        let result = this.sort(row.taExcisePrice, row.excelPriceout, row.taExciseAcc0307Price);
                        let diff = result - data;
                        if(this.uploadFlag){
                            if (diff != 0) {
                                return '<span class="r-mark-tr">' + Utils.moneyFormatDecimal(diff) + '</span>';
                                //
                            }
                            return '<span class="g-mark-tr">' + Utils.moneyFormatDecimal(diff) + '</span>';
                        }
                       return "";
                    }
                },
            ],
            "drawCallback": (settings) => {
                $('.r-mark-tr').closest('td').addClass('background-color : red');
                $('.g-mark-tr').closest('td').addClass('background-color : green');
            }
        });
    }

    export = () =>{
        if(this.table.data().count()==0){
         this.message.alert("ไม่พบข้อมูล");
         return false;
        }
        const URL_DOWNLOAD = "ta/opo048/export";
        this.ajax.download(URL_DOWNLOAD);
       }
}
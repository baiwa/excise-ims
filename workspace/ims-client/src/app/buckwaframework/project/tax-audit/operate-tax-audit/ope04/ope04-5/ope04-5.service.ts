import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from 'services/message-bar.service';
import { Utils } from 'helpers/utils';
declare var $: any;
@Injectable()
export class Ope045Service {
    // ==> params
    table: any;
    dataRespon: any;
    searchFlag: String = "FALSE";
    fileName: any;
    dataExcel: any = null;
    dataList: any;
    constructor(
        private ajax: AjaxService,
        private message: MessageBarService
    ) {

    }

    findExciseId = (): Promise<any> => {
        let url = "ta/opo045/exciseidList";
        return new Promise((resolve, reject) => {
            this.ajax.get(url, res => {
                resolve(res.json())
            })
        });
    }

    findByExciseId = (exciseId): Promise<any> => {
        let url = "ta/opo045/findByExciseId";
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
                };
                reader.readAsDataURL(event.target.files[0]);
            }
            resolve();
        });
    };

    upload = (form: any): Promise<any> => {
        return new Promise((resolve, reject) => {
            let url = "ta/opo045/upload";
            this.ajax.upload(url, form, success => {
                this.dataExcel = success.json();
                if (Utils.isNull(this.dataExcel)) {
                    this.message.errorModal('ไม่สามารถอัปโหลดไฟล์');
                }
            }).then(() => {
                resolve();
                this.table.ajax.reload();
            });
        });
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
        $("#dataFrom").val('');
        $("#dateTo").val('');
        $("#fileName").val('');
        this.dataExcel = null;
        // if (this.table != null) {
        //     this.table.destroy();
        // }
        // this.dataTable();
        this.table.ajax.reload();
    }

    save = async (form: any): Promise<any> => {
        return new Promise(async (resolve, reject) => {
            let data = await this.table.data();
            let url = await "ta/opo045/save";

            let list = await [];
            for (let i = 0; i < data.length; i++) {
                let row = await this.table.row(i).data();
                let max = this.sort(row.amount1Out,row.amount2Out,row.amountTable2);
                let diff = max-row.amountTable3;
                row.amountMax = max;
                row.diff =diff;
                await list.push(row);
            }
            await console.log("list : ", list);
            if (data.length == 0) {
                await this.message.alert("ไม่มีข้อมูล");
                return;
            }
            let d = {
                "voList": list,
                "form": form
            }
            console.log("d : ", d);
            this.message.comfirm((res) => {
                if (res) {
                    return this.ajax.post(url, d, res => {
                        resolve(res.json());
                        this.message.successModal("บันทึกรายการ");
                    }, err => {
                        this.message.errorModal("บันทึกรายการไม่สำเร็จ");
                    });
                } else {
                    resolve("C");
                }
            }, "บันทึกรายการ");
        });
    }
    dataTable = () => {
        this.table = $("#dataTable").DataTableTh({
            "serverSide": false,
            "processing": true,
            "scrollX": true,
            "paging": true,
            "ajax": {
                "url": '/ims-webapp/api/ta/opo045/findAll',
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
                    "render": (data, type, row, meta) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                }, {
                    "data": "amount2Out",
                    "className": "ui right aligned",
                    "render": (data, type, row, meta) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                }, {
                    "data": "amountTable2",
                    "className": "ui right aligned",
                    "render": (data, type, row, meta) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                }, {
                    "data": "amountMax",
                    "className": "ui right aligned",
                    "render": (data, type, row, meta) => {                        
                        let max = this.sort(row.amount1Out, row.amount2Out, row.amountTable2);                       
                        return Utils.moneyFormatDecimal(max);
                    }
                }, {
                    "data": "amountTable3",
                    "className": "ui right aligned",
                    "render": (data, type, row, meta) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                }, {
                    "data": "diff",
                    "className": "ui right aligned",
                    "render": (data, type, row, meta) => {
                        let max = this.sort(row.amount1Out, row.amount2Out, row.amountTable2);
                        let result = max - row.amountTable3;
                        if (result == 0) {
                            return '<span class="g-mark-tr">' + Utils.moneyFormatDecimal(result) + '</span>';
                        } else {
                            return '<span class="r-mark-tr">' + Utils.moneyFormatDecimal(result) + '</span>';
                        }                        
                    }
                },
            ],
            "drawCallback": (settings) => {
                $('.r-mark-tr').closest('td').addClass('background-color : red');
                $('.g-mark-tr').closest('td').addClass('background-color : green');
            }

        });
    }

    getSummaryData() {
        let dataList = this.table.data();
        let dataArray = [];
        for (let i = 0; i < dataList.length; i++) {
            dataArray.push(dataList[i]);
        }
        return dataArray
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
}

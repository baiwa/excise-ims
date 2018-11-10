import { Injectable } from '@angular/core';

import { Utils } from 'helpers/utils';
import { MessageBarService } from 'services/message-bar.service';
import { AjaxService } from 'services/ajax.service';
import { resolve, reject } from 'q';



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
    ) {

    }

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
            }
            resolve();
        });
    };

    upload = (form: any): Promise<any> => {
        return new Promise((resolve, reject) => {
            let url = "ta/opo046/upload";
            this.ajax.upload(url, form, success => {
                this.dataExcel = success.json();
                if (Utils.isNull(this.dataExcel)) {
                    this.message.errorModal('ไม่สามารถอัปโหลดไฟล์');
                }
            }).then(() => {
                this.table.ajax.reload();
            });

            resolve();
        })
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
        if (this.table != null) {
            this.table.destroy();
        }
        this.dataTable();
    }

    save = async (form: any): Promise<any> => {
        return new Promise(async (resolve, reject) => {
            let data = await this.table.data();
            let url = await "ta/opo046/save";

            let list = await [];
            for (let i = 0; i < data.length; i++) {
                let row = await this.table.row(i).data();
                await list.push(row);
            }
            await console.log(list);
            if (data.length == 0) {
                await this.message.alert("ไม่มีข้อมูล");
                return;
            }
            let d = {
                "voList": list,
                "form": form
            }

            this.message.comfirm(async (res) => {
                if (res) {
                    return await this.ajax.post(url, d, async res => {
                        await resolve(res.json());
                        await this.message.successModal("บันทึกรายการ");
                    }, async err => {
                        await this.message.errorModal("บันทึกรายการไม่สำเร็จ");
                    });
                } else {
                    await resolve("C");
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
                "url": '/ims-webapp/api/ta/opo046/findAll',
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
                    "data": "taExciseAcc0502DtlList",
                    "className": "ui left aligned"
                },
                {
                    "data": "taxAmount",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },
                {
                    "data": "amount",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
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
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },
                {
                    "data": "volume",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatInt(data);
                    }
                },
                {
                    "data": "unit",
                    "className": "ui right aligned",
                    "render": (data) => {
                        return Utils.moneyFormatDecimal(data);
                    }
                },
                {
                    "data": "unit",
                    "className": "ui right aligned",
                    "render": (data, type, row, ) => {
                        if (Utils.isNotNull(row.unit)) {
                            let diff = row.taxPerAmount - row.unit;

                            if (diff != 0) {
                                return '<span class="r-mark-tr">' + Utils.moneyFormatDecimal(diff) + '</span>';

                            }
                            return '<span class="g-mark-tr">' + Utils.moneyFormatDecimal(diff) + '</span>';
                        } else {

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
        // this.table.clear().draw();
        // this.table.rows.add(this.data); // Add new data
        // this.table.columns.adjust().draw(); // Redraw the DataTable\


    }

    getSummaryData() {
        let dataList = this.table.data();
        let dataArray = [];
        for (let i = 0; i < dataList.length; i++) {
            dataArray.push(dataList[i]);
        }
        return dataArray
    }
}

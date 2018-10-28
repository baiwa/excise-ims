import { Injectable } from '@angular/core';

import { Utils } from 'helpers/utils';
import { MessageBarService } from 'services/message-bar.service';
import { AjaxService } from 'services/ajax.service';



declare var $: any;

@Injectable()
export class Cop068Service {

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
        let url = "cop/cop061/exciseidList";
        return new Promise((resolve, reject) => {
            this.ajax.get(url, res => {
                resolve(res.json())
            })
        });
    }

    findByExciseId = (exciseId): Promise<any> => {
        let url = "cop/cop061/findByExciseId";
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
        let url = "cop/cop061/upload";
        this.ajax.upload(url, form, success => {
            this.dataExcel = success.json();
            if (Utils.isNull(this.dataExcel)) {
                this.message.errorModal('ไม่สามารถอัปโหลดไฟล์');
            }
        }).then(() => {
            this.table.ajax.reload();
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
        $("#dataFrom").val('');
        $("#dateTo").val('');
        $("#fileName").val('');
        this.dataExcel = null;
        if (this.table != null) {
            this.table.destroy();
        }
        this.dataTable();
    }

    save = async (): Promise<any> => {
        return new Promise(async(resolve, reject) => {
        let data = await this.table.data();
        let url = await "cop/cop061/save";

        let list = await [];
        for (let i = 0; i < data.length; i++) {
            let row = await this.table.row(i).data();
            await console.log(row);
            await list.push(row);
        }
        await console.log(list);
        if (data.length == 0) {
            await this.message.alert("ไม่มีข้อมูล");
            return;
        }
        this.message.comfirm(async (res) => {
            if (res) {
                return await this.ajax.post(url, list, async res => {
                    await resolve(res.json());
                    await this.message.successModal("บันทึกรายการ");
                }, async err => {
                    await this.message.errorModal("บันทึกรายการไม่สำเร็จ");
                });
            }else{
                await resolve("C");
            }
        }, "", "ยืนยันการบันทึกข้อมูล");
        });
    }
    dataTable = () => {
        this.table = $("#dataTable").DataTableTh({
            "serverSide": false,
            "processing": true,
            "scrollX": true,
            "paging": true,
            "ajax": {
                "url": '/ims-webapp/api/cop/cop061/findAll',
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
                    "render" : (data)=>{
                        return Utils.moneyFormatInt(data);
                    }
                },
                {
                    "data": "amount",                    
                    "className": "ui right aligned",
                    "render" : (data)=>{
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
                    "render" : (data)=>{
                        return Utils.moneyFormatInt(data);
                    }
                },
                {
                    "data": "volume",
                    "className": "ui right aligned",
                    "render" : (data)=>{
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
}

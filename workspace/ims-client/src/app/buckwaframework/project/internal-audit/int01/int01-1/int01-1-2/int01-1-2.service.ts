import { Injectable } from "@angular/core";
import { Int011Service } from "../int01-1.services";
import { TaxReceipt } from "models/taxReceipt";
import { Observable } from "rxjs";
import { NgForm } from "@angular/forms";
import { MessageBarService, AjaxService } from "services/index";

declare var $: any;

const URL = {
    DATA: "ia/int0111/",
    SAVE: "ia/int0111/save"
};

@Injectable()
export class Int0112Service {

    private taxReceipt: TaxReceipt[];
    private totalReceipt: TaxReceipt;
    private table: any; // DataTable
    private loading: boolean;

    constructor(private ajax: AjaxService, private main: Int011Service, private msg: MessageBarService) {
        // TODO
        this.totalReceipt = new TaxReceipt();
        this.loading = true;
    }

    async taxReceiptFiltered() {
        let _data = this.main.getData() ? this.main.getData() : { travelTo1: '00', travelTo2: '00', travelTo3: '00', startDate: '01/09/2561', endDate: '21/09/2561' };
        const { travelTo1, travelTo2, travelTo3, startDate, endDate } = _data;
        const _start = startDate.split("/");
        const _end = endDate.split("/");
        const data = {
            "OfficeCode": travelTo1 + travelTo2 + travelTo3,
            "YearMonthFrom": `${parseInt(_start[2]) - 543}${_start[1]}${_start[0]}`,
            "YearMonthTo": `${parseInt(_end[2]) - 543}${_end[1]}${_end[0]}`,
            "DateType": "Income",
            "PageNo": "0",
            "DataPerPage": "0"
        };
        return this.ajax.post(URL.DATA, data, res => {
            this.taxReceipt = res.json();
            this.taxReceipt.forEach((obj, index) => {
                // Add Index
                obj.index = index;
                obj.status = (obj.checkedAmount && obj.checkedAmount != obj.netTaxAmount) ? "diff" : "equa";
                obj.portal = this.checkRunningReceiptNo(index);
                // Calculate Total
                this.totalReceipt.netTaxAmount = obj.netTaxAmount + this.totalReceipt.netTaxAmount;
                this.totalReceipt.checkedAmount = obj.checkedAmount + this.totalReceipt.checkedAmount;
                this.totalReceipt.netLocAmount = obj.netLocAmount + this.totalReceipt.netLocAmount;
                this.totalReceipt.locOthAmount = obj.locOthAmount + this.totalReceipt.locOthAmount;
                this.totalReceipt.locExpAmount = obj.locExpAmount + this.totalReceipt.locExpAmount;
                this.totalReceipt.olderFundAmount = obj.olderFundAmount + this.totalReceipt.olderFundAmount;
                this.totalReceipt.tpbsFundAmount = obj.tpbsFundAmount + this.totalReceipt.tpbsFundAmount;
                this.totalReceipt.sendAmount = obj.sendAmount + this.totalReceipt.sendAmount;
                this.totalReceipt.stampAmount = obj.stampAmount + this.totalReceipt.stampAmount;
                this.totalReceipt.customAmount = obj.customAmount + this.totalReceipt.customAmount;

                const { netTaxAmount, netLocAmount, locOthAmount, locExpAmount } = obj; // extract structure
                obj.sumAmount = netTaxAmount + netLocAmount + locOthAmount + locExpAmount; // sum
                this.totalReceipt.sum += obj.sumAmount;

            });
        });
    }

    checkRunningReceiptNo(i) {
        let rec = this.taxReceipt[i];
        if (rec.receiptNo != null && rec.receiptNo != undefined) {
            let recFull = rec.receiptNo.split('/');
            let recPre = recFull[0];
            let recSub = recFull[1];
            if (i > 0) {
                let topRec = this.taxReceipt[i - 1];
                if (topRec.receiptNo != null && topRec.receiptNo != undefined) {
                    let topFull = topRec.receiptNo.split('/');
                    let topPre = topFull[0];
                    let topSub = topFull[1];

                    if (recPre === topPre && (parseInt(recSub) != parseInt(topSub) + 1)) {
                        return "portal";
                    }
                }
            }
            let downRec = this.taxReceipt[i + 1];
            if (downRec != null && downRec != undefined && downRec.receiptNo != null && downRec.receiptNo != undefined) {
                let downFull = downRec.receiptNo.split('/');
                let downPre = downFull[0];
                let downSub = downFull[1];
                if (recPre === downPre && (parseInt(recSub) != parseInt(downSub) - 1)) {
                    return "portal";
                }
            }

        } else {
            return "portal"
        }



        return "";
    }

    setPrint(form: NgForm, func?: Function) {
        const { amount, permit_no, print_no } = form.controls;
        // Find Tax
        let find = new TaxReceipt();
        find.receiptNo = permit_no.value;
        console.log('setPrint', find.receiptNo);
        if (this.findByTax(find, 'receiptNo')) {
            // On Loading
            this.loading = true;
            // Get and Set Tax
            find = this.findByTax(find, 'receiptNo');
            find.taxPrintNo = print_no.value;
            find.checkedAmount = amount.value;
            if (amount.value == find.netTaxAmount) {
                find.status = "equa";
            } else {
                find.status = "diff";
            }
            this.update(find.index, find);
            //this.totalReceipt = new TaxReceipt();
            let sumCheckNewAmount = 0;
            this.taxReceipt.forEach((obj, index) => {

                sumCheckNewAmount = sumCheckNewAmount + (obj.checkedAmount == null || obj.checkedAmount == undefined ? 0 : parseFloat(obj.checkedAmount + ''));
                console.log(sumCheckNewAmount);

            });
            this.totalReceipt.checkedAmount = sumCheckNewAmount;
            // Clear Input
            form.resetForm();
            // Refresh DataTable
            setTimeout(() => {
                this.initDatatable();
                this.loading = false;
                if (func) {
                    func();
                }
            });
        } else {
            this.msg.errorModal("ไม่พบข้อมูล", "คำเตือน");
        }
    }

    taxReceiptObs(): Observable<TaxReceipt[]> {
        return new Observable<TaxReceipt[]>(obs => {
            this.taxReceiptFiltered().then(() => {
                obs.next(this.taxReceipt);
            }).then(() => {
                this.loading = false;
                setTimeout(() => {
                    this.initDatatable();
                }, 1000);
            });
        });
    }

    totalReceiptCal(): TaxReceipt {
        return this.totalReceipt;
    }

    findByTax(tax: TaxReceipt, by: string): TaxReceipt {
        switch (by) {
            case 'receiptNo': return this.taxReceipt.find(obj => obj.receiptNo == tax.receiptNo);
        }
    }

    get(index: number): TaxReceipt {
        return this.taxReceipt[index];
    }

    update(index: number, tax: TaxReceipt): void {
        this.taxReceipt[index] = tax;
    }

    save(): void {
        this.loading = true;
        this.ajax.post(URL.SAVE, { data: this.taxReceipt }, response => {
            const { msg, data } = response.json();
            if (msg.messageCode == "MSG_00002") {
                this.msg.successModal(msg.messageTh);
                this.taxReceipt = data;
                setTimeout(() => {
                    this.initDatatable();
                    this.loading = false;
                }, 1000);
            } else {
                this.msg.errorModal(msg.messageTh);
            }
        });
    }

    delete(index: number): void {
        // this.taxReceipt.splice(index, 1);
        this.msg.comfirm(flag => {
            if (flag) {
                this.loading = false;
                this.taxReceipt[index].taxPrintNo = null;
                this.taxReceipt[index].checkedAmount = 0.0;
                this.taxReceipt[index].status = "equa";
                setTimeout(() => {
                    this.initDatatable();
                    this.loading = false;
                }, 100);
            }
        }, "ต้องการลบเลขแบบพิมพ์จริงหรือไม่");
    }

    initDatatable() { // Initial Datatable
        if (this.table) {
            this.table.destroy();
        }
        this.table = $("#table").DataTableTh({
            scrollY: "410px",
            scrollCollapse: true,
            scrollX: true,
            searching: false,
            lengthChange: false,
            paging: false,
            order: [[0, 'asc']],

        });
    }

    onLoad(): Observable<boolean> {
        return new Observable<boolean>(obs => {
            obs.next(this.loading);
            setInterval(() => {
                obs.next(this.loading);
            }, 1000);
        });
    }

    setLoad(status: boolean) {
        this.loading = status;
    }

}
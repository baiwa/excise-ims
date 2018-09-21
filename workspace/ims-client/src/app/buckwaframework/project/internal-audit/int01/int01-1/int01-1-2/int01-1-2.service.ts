import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { Int011Service } from "../int01-1.services";
import { TaxReceipt } from "models/taxReceipt";
import { Observable } from "rxjs";

const URL = {
    WS: ""
};

@Injectable()
export class Int0112Service {

    taxReceipt: TaxReceipt[];

    constructor(private ajax: AjaxService, private main: Int011Service) {
        // TODO
    }

    async taxReceiptFiltered() {
        const { travelTo1, travelTo2, travelTo3, startDate, endDate } = this.main.getData();
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
        return this.ajax.post("ia/int0111/ws/pull", data, res => {
            this.taxReceipt = res.json();
        });
    }

    taxReceiptObs(): Observable<TaxReceipt[]> {
        return new Observable<TaxReceipt[]>( obs => {
            this.taxReceiptFiltered().then(() => {
                obs.next(this.taxReceipt);
            });
        });
    }
}
import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { Int0621Service } from 'projects/internal-audit/int06/int06-1/int06-2-1/int06-2-1.service';

@Component({
    selector: 'app-int06-2-1',
    templateUrl: './int06-2-1.component.html',
    styleUrls: ['./int06-2-1.component.css'],
    providers: [Int0621Service]
})
export class Int0621Component implements OnInit {

    breadcrumb: BreadCrumb[] = [
        { label: "ตรวจสอบภายใน", route: "#" },
        { label: "ตรวจสอบเบิกจ่าย", route: "#" },
        { label: "ตรวจสอบค่าใช้จ่าย", route: "#" },
    ];

    model : FormSearch;
    data: Data;
    constructor(
        private int0621Service: Int0621Service
    ) {
        this.model = new FormSearch();
        this.data = new Data();
    }

    ngOnInit() {
        this.dataTable();
    }

    search() {
        this.model.searchFlag="TRUE";
        this.int0621Service.search(this.model);
    }
    clear() {
        this.model = new FormSearch();
        this.int0621Service.search(this.model);
    }

    dataTable=()=> {
        this.int0621Service.dataTable();
    }

}

class FormSearch {
    accountId: string = "";
    accountName: string = "";
    searchFlag: string = "";
}

class Data {
    id: string = "";
    accountId: string = "";
    accountName: string = "";
    serviceReceive: string = "";
    serviceWithdraw: string = "";
    serviceBalance: string = "";
    suppressReceive: string = "";
    suppressWithdraw: string = "";
    suppressBalance: string = "";
    budgetReceive: string = "";
    budgetWithdraw: string = "";
    budgetBalance: string = "";
    sumReceive: string = "";
    sumWithdraw: string = "";
    sumBalance: string = "";
    moneyBudget: string = "";
    moneyOut: string = "";
    averageCost: string = "";
    averageGive: string = "";
    averageFrom: string = "";
    averateComeCost: string = "";
    createdBy: string = "";
    updatedBy: string = "";
    createdDate: string = "";
    updatedDate: string = "";
    note: string = "";
}

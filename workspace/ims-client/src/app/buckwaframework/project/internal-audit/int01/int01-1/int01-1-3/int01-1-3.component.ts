import { Component, OnInit, ViewChild } from "@angular/core";
import { BreadCrumb, TaxReceipt } from "models/index";
import { NgForm } from "@angular/forms";

import { Int011Service } from "../int01-1.services";
import { Int0112Service } from "../int01-1-2/int01-1-2.service";
import { AjaxService } from "services/ajax.service";
import { DecimalFormat } from "helpers/index";
import { Observable } from "rxjs";
import { MessageBarService } from "services/message-bar.service";
declare var $: any;
@Component({
  selector: 'app-int01-1-3',
  templateUrl: './int01-1-3.component.html',
  styleUrls: ['./int01-1-3.component.css']
})
export class Int0113Component implements OnInit {

  private selectedProduct: string = 'สำนักงานสรรพสามิตพื้นที่เมืองพิษณุโลก ';
  private productList: any[];
  private receipt: TaxReceipt[];
  private taxReceipt: TaxReceipt[];
  private moneyReceipt: TaxReceipt[];
  private tableTax: TaxReceipt[];
  private tableMonth: TaxReceipt[];

  constructor(private ajax: AjaxService, private main: Int011Service, private msg: MessageBarService) { }

  ngOnInit() {
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
    this.ajax.post("ia/int0111/", data, res => {
      this.receipt = res.json();
      this.receipt.forEach(element => {
        if (element.incomeName.indexOf('ภาษี') == 0) {
          this.taxReceipt.push(element)
        }

        if (element.incomeName.indexOf('เงินบำรุง') == 0) {
          this.moneyReceipt.push(element)
        }
      });
    });



  }

  initTableTax() {
    // tableTax
  }
  initTableMonth() {
    // tableMonth
  }

}

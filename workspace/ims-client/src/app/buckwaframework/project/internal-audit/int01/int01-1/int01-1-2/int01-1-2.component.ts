import { Component, OnInit, ViewChild } from "@angular/core";
import { BreadCrumb, TaxReceipt } from "models/index";
import { NgForm } from "@angular/forms";

import { Int011Service } from "../int01-1.services";
import { Int0112Service } from "./int01-1-2.service";
import { AjaxService } from "services/ajax.service";
import { DecimalFormat } from "helpers/index";
import { Observable } from "rxjs";
import { MessageBarService } from "services/message-bar.service";
import { AuthService } from "services/auth.service";

declare var $: any;

@Component({
  selector: "app-int01-1-2",
  templateUrl: "./int01-1-2.component.html",
  styleUrls: ["./int01-1-2.component.css"],
  providers: [Int0112Service]
})
export class Int0112Component implements OnInit {
  @ViewChild('f') form: NgForm;
  @ViewChild('fe') formEdit: NgForm;

  taxReceipt: Observable<TaxReceipt[]>; // For Table
  _taxReceipt: TaxReceipt; // For Edit
  totalReceipt: TaxReceipt; // For Footer Total

  breadcrumb: BreadCrumb[]; // Breadcrump navs
  loading: Observable<boolean>; // On Loading
  action: any;
  budgetYear: any;
  fix: any;
  keyC: any;
  constructor(
    private msg: MessageBarService,
    private main: Int011Service,
    
private authService: AuthService,
    private self: Int0112Service,
    private ajax: AjaxService) {
    // TODO
    this._taxReceipt = new TaxReceipt();
    this.totalReceipt = new TaxReceipt();
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบรายได้", route: "#" },
      { label: "ตรวจสอบใบเสร็จรับเงินภาษีสรรพสามิต", route: "int01/1/1" },
      { label: "เพิ่มเลขแบบพิมพ์", route: "#" }
    ];
  }

  async ngOnInit() {
    this.authService.reRenderVersionProgram('INT-01120');
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.loading = this.self.onLoad(); // On Load Service
    this.taxReceipt = this.self.taxReceiptObs(); // Table Service
    this.totalReceipt = this.self.totalReceiptCal();
    //this.self.initDatatable();



  }

  ngAfterViewInit() {
    this.setData();

  }

  addPrint(e: any) {
    e.preventDefault();
    if (this.form.valid) {

      this.form.controls.permit_no.setValue(this.action + this.form.controls.permit_no.value)
      this.self.setPrint(this.form);
      this.setData();
    }
  }

  updatePrint(e: any) {
    e.preventDefault();
    if (this.formEdit.valid) {
      this.self.setPrint(this.formEdit, () => {
        $("#edit").modal('hide');
      });
    }
  }

  selectValue(no: string): void {
    this.form.controls.permit_no.setValue(no);
  }

  edit(index: number): void {
    this._taxReceipt = this.self.get(index);
    const { taxPrintNo, receiptNo, checkedAmount } = this._taxReceipt;
    this.formEdit.controls.amount.setValue(checkedAmount);
    this.formEdit.controls.permit_no.setValue(receiptNo);
    this.formEdit.controls.print_no.setValue(taxPrintNo);
    $("#edit").modal('show');
  }

  del(index: number): void {
    this.self.delete(index);
  }

  df(what): string {
    const df = new DecimalFormat("###,###.00");
    return df.format(what);
  }

  round(num: number): string {
    return this.df(parseFloat(num.toString()).toFixed(2));
  }

  saveAll() {
    this.self.save();
  }

  dateFormat(date: string): string {
    let year = date.substring(0, 4);
    let month = date.substring(4, 6);
    let day = date.substring(6);
    return (date && date != null) ? `${day}/${month}/${year}` : "-";
  }

  keyDownC() {
    this.action = 'C';
  }

  keyDownN() {
    this.action = 'N';
  }

  setData() {
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
    this.budgetYear = '';
    var dateTime = new Date().toLocaleString("th-TH");
    if (new Date().getMonth() > 8) {
      this.budgetYear = parseInt(dateTime.split(' ')[0].split('/')[2].substr(2)) + 1;
    } else {
      this.budgetYear = dateTime.split(' ')[0].split('/')[2].substr(2);
    }
    console.log('budgetYear', data.OfficeCode + this.budgetYear + '/');
    //this.form.controls.permit_no.setValue();
    //this.budgetYear = data.OfficeCode + this.budgetYear + '/'
    $('#keyC').val(data.OfficeCode + this.budgetYear + '/000');
    $('#keyN').val(data.OfficeCode + this.budgetYear + '/000');
    this.keyC = data.OfficeCode + this.budgetYear + '/000';
    // this.fix = data.OfficeCode + this.budgetYear + '/000';

  }



}

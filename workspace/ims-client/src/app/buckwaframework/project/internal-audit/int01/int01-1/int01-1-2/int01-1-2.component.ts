import { Component, OnInit, ViewChild } from "@angular/core";
import { BreadCrumb, TaxReceipt } from "models/index";
import { NgForm } from "@angular/forms";

import { Int011Service } from "../int01-1.services";
import { Int0112Service } from "./int01-1-2.service";
import { AjaxService } from "services/ajax.service";
import { DecimalFormat } from "helpers/index";
import { Observable } from "rxjs";
import { MessageBarService } from "services/message-bar.service";

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

  constructor(
    private msg: MessageBarService,
    private main: Int011Service,
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
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.loading = this.self.onLoad(); // On Load Service
    this.taxReceipt = this.self.taxReceiptObs(); // Table Service
    this.totalReceipt = this.self.totalReceiptCal();
  }

  addPrint(e: any) {
    e.preventDefault();
    if (this.form.valid) {
      this.self.setPrint(this.form);
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

}

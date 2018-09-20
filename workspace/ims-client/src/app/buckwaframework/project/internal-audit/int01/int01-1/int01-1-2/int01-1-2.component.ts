import { Component, OnInit, ViewChild } from "@angular/core";
import { BreadCrumb, TaxReceipt } from "models/index";
import { NgForm } from "@angular/forms";

import { Int011Service } from "../int01-1.services";
import { Int0112Service } from "./int01-1-2.service";
import { AjaxService } from "services/ajax.service";
import { DecimalFormat } from "helpers/index";

declare var $: any;

@Component({
  selector: "app-int01-1-2",
  templateUrl: "./int01-1-2.component.html",
  styleUrls: ["./int01-1-2.component.css"],
  providers: [Int0112Service]
})
export class Int0112Component implements OnInit {
  @ViewChild('f') form: NgForm;
  taxReceipt: TaxReceipt[];
  totalReceipt: TaxReceipt = new TaxReceipt();
  breadcrumb: BreadCrumb[];
  loading: boolean = true;
  constructor(
    private main: Int011Service,
    private self: Int0112Service,
    private ajax: AjaxService) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบพัสดุ", route: "#" },
      { label: "ตรวจสอบพัสดุภาคพื้นที่", route: "int01/1/1" },
      { label: "เพิ่มเลขแบบพิมพ์", route: "#" }
    ];
  }

  async ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    await this.self.taxReceiptObs().subscribe(async res => {
      this.taxReceipt = await res;
      await this.taxReceipt.forEach(obj => {
        this.totalReceipt.netTaxAmount = obj.netTaxAmount + this.totalReceipt.netTaxAmount;
        this.totalReceipt.netLocAmount = obj.netLocAmount + this.totalReceipt.netLocAmount;
        this.totalReceipt.locOthAmount = obj.locOthAmount + this.totalReceipt.locOthAmount;
        this.totalReceipt.locExpAmount = obj.locExpAmount + this.totalReceipt.locExpAmount;
        this.totalReceipt.olderFundAmount = obj.olderFundAmount + this.totalReceipt.olderFundAmount;
        this.totalReceipt.tpbsFundAmount = obj.tpbsFundAmount + this.totalReceipt.tpbsFundAmount;
        this.totalReceipt.sendAmount = obj.sendAmount + this.totalReceipt.sendAmount;
        this.totalReceipt.stampAmount = obj.stampAmount + this.totalReceipt.stampAmount;
        this.totalReceipt.customAmount = obj.customAmount + this.totalReceipt.customAmount;
      });
      setTimeout(() => {
        this.initDatatable();
        this.loading = false;
      }, 1000);
    });
  }

  initDatatable() { // Initial Datatable
    $("#table").DataTable({
      scrollY: '500px',
      scrollX: true,
      ordering: false,
      searching: false,
      lengthChange: false,
      paging: false
    });
  }

  add(form: NgForm) {
    if (form.valid) {
      const { amount, permit_no, print_no } = form.controls;
    }
  }

  df(what) {
    const df = new DecimalFormat("###,###.00");
    return df.format(what);
  }

  round(num) {
    return this.df(num.toFixed(2));
  }

}

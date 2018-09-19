import { Component, OnInit, ViewChild } from "@angular/core";
import { BreadCrumb } from "models/index";
import { NgForm } from "@angular/forms";
import { Int011Service } from "../int01-1.services";

declare var $: any;

@Component({
  selector: "app-int01-1-2",
  templateUrl: "./int01-1-2.component.html",
  styleUrls: ["./int01-1-2.component.css"]
})
export class Int0112Component implements OnInit {
  private codeList: any[];
  private productList: any[];
  private printMonthList: any[];
  private showData: boolean = false;
  private selectedProduct: string = "เครื่องดื่ม";

  breadcrumb: BreadCrumb[];

  @ViewChild('f') form: NgForm;

  data: any = null;

  constructor(private main: Int011Service) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบพัสดุ", route: "#" },
      { label: "ตรวจสอบพัสดุภาคพื้นที่", route: "int01/1/1" },
      { label: "เพิ่มเลขแบบพิมพ์", route: "#" }
    ];
    this.data = this.main.getData();
    console.log(this.data);
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
  }

  onChange(newValue) {
    this.selectedProduct = newValue; // don't forget to update the model here
  }
  ngAfterViewInit() {
    this.initDatatable();
  }

  initDatatable() {
    $("#table").DataTable({
      scrollY: '500px',
      scrollX: true,
      ordering: false,
      searching: false,
      lengthChange: false,
      paging: false
    });
  }

  popupEditData() {
    $("#modalEditData").modal("show");
    $("#modalInt062").modal("show");
    $("#idint").show();
    $("#id").show();
    $("#selectTrading").show();
  }

  closePopupEdit() {
    $("#selectTrading").show();
    $("#modalEditData").modal("hide");
    $("#modalInt062").modal("hide");
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

  add(form: NgForm) {
    if (form.valid) {
      const { amount, permit_no, print_no } = form.controls;
    }
  }

}

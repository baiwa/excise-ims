import { Component, OnInit } from "@angular/core";
import { Int071Service } from "./int07-1.service";
import { BreadCrumb } from "models/breadcrumb";

declare var $: any;
@Component({
  selector: "int07-1",
  templateUrl: "./int07-1.component.html",
  styleUrls: ["./int07-1.component.css"],
  providers: [Int071Service]
})
export class Int071Component implements OnInit {
  breadcrumb: BreadCrumb[] = [];
  loading: boolean = false;
  loadingTable: boolean = false;
  checkSave: boolean = false;

  constructor(private selfService: Int071Service) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบบัญชี", route: "#" },
      { label: "ตรวจสอบข้อมูลบัญชี", route: "#" }
    ];
  }

  ngAfterViewInit() {
    this.hideData();
  }

  ngOnInit() {}

  onSave = () => {
    this.loadingTable = true;
    this.selfService.saveData(this.getLoading, this.getStatusSave);
  };

  changeUpload = (e: any) => {
    e.preventDefault;
    this.loading = true;
    this.selfService.onChangeUpload(e, this.getLoading);
  };

  uplodFile = (e: any) => {
    e.preventDefault;
    // this.loading = true;
    this.loadingTable = true;

    this.selfService.onUpload(e, this.getLoading);
  };

  onCheck() {
    this.selfService.onCheck();
    // [routerLink]="[ '/int07/2']
  }

  hideData() {
    $("#dataTable").hide();
  }

  getLoading = args => {
    this.loading = args;
    this.loadingTable = args;
  };

  getStatusSave = flg => {
    this.checkSave = flg;
  };
}

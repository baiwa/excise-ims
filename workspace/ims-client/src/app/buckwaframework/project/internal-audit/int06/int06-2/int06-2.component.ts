import { Component, OnInit } from "@angular/core";
import { AuthService } from "services/auth.service";
import { Int062Service } from "./int06-2.service";
import { BreadCrumb } from "models/breadcrumb";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { Router } from "@angular/router";

declare var $: any;
@Component({
  selector: "int06-2",
  templateUrl: "./int06-2.component.html",
  styleUrls: ["./int06-2.component.css"],
  providers: [Int062Service]
})
export class Int062Component implements OnInit {
  checkListOfwithdraw: FormGroup;
  breadcrumb: BreadCrumb[] = [];
  loading: boolean = false;
  submitted: boolean = false;
  comboBox: Observable<ComboBox[]>;
  comboBoxId: string = "";

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private selfService: Int062Service
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจสอบการเบิกและจ่ายเงิน", route: "#" }
    ];

    this.comboBox = this.selfService.dropdown("SORT_SYSTEM", null);
  }

  ngAfterViewInit() {
    $("#export .dropdown").dropdown({
      transition: "drop"
    });
    $("#showTable").hide();
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram("INT-06200");
    //set formbuilder
    this.checkListOfwithdraw = this.formBuilder.group({
      fileExcel1: ["", Validators.required],
      fileExcel2: ["", Validators.required],
      sortSystem: ["", Validators.required]
    });

    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
  }

  popupEditData() {
    $("#modalInt062").modal("show");
  }

  closePopupEdit() {
    $("#modalInt062").modal("hide");
  }

  uploadData(e: any) {
    e.preventDefault();
    this.submitted = true;
    // stop here if form is invalid
    if (this.checkListOfwithdraw.invalid) {
      return;
    }

    this.loading = true;
    this.selfService.onUpload().then(() => {
      this.loading = false;
    });
  }

  clearData() {
    $("#showTable").hide();
  }

  get f() {
    return this.checkListOfwithdraw.controls;
  }

  onChangeUpload(e: any) {
    this.loading = true;
    setTimeout(() => {
      this.loading = false;
    }, 500);
    // this.selfService.onChangeUpload(e, this.getLoading);
  }

  onChangeUpload2(e: any) {
    this.loading = true;
    setTimeout(() => {
      this.loading = false;
    }, 500);
    // this.selfService.onChangeUpload2(e, this.getLoading);
  }

  changeSortSystem(e) {
    // console.log(e.target.value);
    this.comboBoxId = e.target.value;
    console.log(this.comboBoxId);
  }

  compareTR() {
    this.selfService.compareTR(this.comboBoxId);
  }

  getLoading = args => {
    this.loading = args;
  };
}

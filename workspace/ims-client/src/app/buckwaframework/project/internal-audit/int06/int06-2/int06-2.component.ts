import { Component, OnInit } from "@angular/core";
import { AuthService } from "services/auth.service";
import { Int062Service } from "./int06-2.service";
import { BreadCrumb } from "models/breadcrumb";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";

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
  showData: boolean = false;
  loading: boolean = false;
  submitted: boolean = false;

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
  }

  ngAfterViewInit() {
    $("#export .dropdown").dropdown({
      transition: "drop"
    });
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram("INT-06200");
    //set formbuilder
    this.checkListOfwithdraw = this.formBuilder.group({
      fileExcel1: ["", Validators.required],
      fileExcel2: ["", Validators.required],
      systemSort: ["", Validators.required]
    });
    console.log(this.checkListOfwithdraw);

    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
  }

  popupEditData() {
    $("#modalInt062").modal("show");
  }

  closePopupEdit() {
    $("#modalInt062").modal("hide");
  }

  uploadData(event: any) {
    this.submitted = true;
    // stop here if form is invalid
    if (this.checkListOfwithdraw.invalid) {
      return;
    }
    this.loading = true;
    this.selfService.onUpload(event, this.getLoading).then(() => {
      this.loading = false;
    });
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

  get f() {
    return this.checkListOfwithdraw.controls;
  }

  // onChangeUpload(e: any) {
  //   this.loading = true;
  //   this.selfService.onChangeUpload(e, this.getLoading);
  // }

  // onChangeUpload2(e: any) {
  //   this.loading = true;
  //   this.selfService.onChangeUpload2(e, this.getLoading);
  // }

  getLoading = args => {
    this.loading = args;
  };
}

import { Component, OnInit } from "@angular/core";
import { AuthService } from "services/auth.service";
import { Int061101Service } from "./int06-11-1.service";
import { MessageBarService } from "services/message-bar.service";
import { ActivatedRoute } from "@angular/router";
import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { TextDateTH, formatter } from "helpers/datepicker";
import { Utils } from "helpers/utils";

declare var $: any;
@Component({
  selector: "app-int06-11-1",
  templateUrl: "./int06-11-1.component.html",
  styleUrls: ["./int06-11-1.component.css"],
  providers: [Int061101Service]
})
export class Int06111Component implements OnInit {
  rentHouseForm: FormGroup;
  submitted: boolean = false;
  titles: Observable<ComboBox>;
  auth: any;
  loadingUL: boolean = false;
  tableUpload: any;

  constructor(
    private selfService: Int061101Service,
    private authService: AuthService,
    private fb: FormBuilder,
    private msg: MessageBarService,
    private route: ActivatedRoute
  ) {
    this.setVariable();
  }

  ngOnInit() {
    this.calendar();
    this.authService.reRenderVersionProgram("INT-06111").then(obj => {
      this.rentHouseForm.patchValue({
        name: obj.fullName,
        position: obj.title,
        affiliation: obj.position
      });
    });
    // this.titles = this.selfService.dropdown("TITLE", null);
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");
  }

  setVariable() {
    this.rentHouseForm = this.fb.group({
      // title: ["", Validators.required],
      name: ["", Validators.required],
      position: ["", Validators.required],
      affiliation: ["", Validators.required],
      paymentCost: ["", Validators.required],
      paymentFor: ["", Validators.required],
      period: ["", Validators.required],
      refReceipts: ["", Validators.required],
      billAmount: ["", Validators.required],
      salary: ["", Validators.required],
      requestNo: ["", Validators.required],
      notOver: ["", Validators.required],
      periodWithdraw: ["", Validators.required],
      totalMonth: ["", Validators.required],
      totalWithdraw: ["", Validators.required],
      receipts: ["", Validators.required]
    });
  }

  calendar = function() {
    $("#periodCld").calendar({
      minDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: formatter("month"),
      onChange: (date, ddmmyyyy) => {
        this.rentHouseForm.patchValue({ period: ddmmyyyy });
      }
    });

    $("#periodWithdrawCld").calendar({
      minDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: formatter("month"),
      onChange: (date, ddmmyyyy) => {
        this.rentHouseForm.patchValue({ periodWithdraw: ddmmyyyy });
      }
    });
  };

  //func check validator
  validateField(value: string) {
    return this.submitted && this.rentHouseForm.get(value).errors;
  }

  save(e) {
    e.preventDefault();
    this.submitted = true;
    console.log(this.rentHouseForm.value);
    // stop here if form is invalid
    if (this.rentHouseForm.invalid) {
      this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ");
      return;
    }
    this.selfService.save(this.rentHouseForm.value);
  }

  typeNumber(e) {
    return Utils.onlyNumber(e);
  }

  // onChangeUpload(e) {
  //   this.loadingUL = true;
  //   this.selfService.onChangeUpload(e).then(() => {
  //     setTimeout(() => {
  //       this.loadingUL = false;
  //     }, 1000);
  //   });
  // }

  onUpload(e) {
    e.preventDefault();
    this.selfService.onUpload().then(data => {
      this.tableUpload = data;
    });
  }

  onDel(index: number) {
    this.selfService.onDel(index);
  }
}

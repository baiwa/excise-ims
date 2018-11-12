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
import { BreadCrumb } from "models/breadcrumb";
import * as moment from "moment";

declare var $: any;
@Component({
  selector: "app-int06-11-1",
  templateUrl: "./int06-11-1.component.html",
  styleUrls: ["./int06-11-1.component.css"],
  providers: [Int061101Service]
})
export class Int06111Component implements OnInit {
  rentHouseForm: FormGroup;
  breadcrumb: BreadCrumb[];
  submitted: boolean = false;
  titles: Observable<ComboBox>;
  auth: any;
  loadingUL: boolean = false;
  tableUpload: any = [];
  flgOnLoad: boolean = true;
  monthStart: any;

  constructor(
    private selfService: Int061101Service,
    private authService: AuthService,
    private fb: FormBuilder,
    private msg: MessageBarService,
    private route: ActivatedRoute
  ) {
    this.setVariable();
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "บันทึกคำขอเบิก", route: "#" },
      { label: "บันทึกคำขอเบิกเงินค่าเช่าบ้าน (แบบ 6006)", route: "#" }
    ];
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
      name: ["", Validators.required],
      position: ["", Validators.required],
      affiliation: ["", Validators.required],
      paymentCost: ["", Validators.required],
      paymentFor: ["", Validators.required],
      period: ["", Validators.required],
      refReceipts: ["", Validators.required],
      billAmount: ["", Validators.required],
      salary: ["", Validators.required],
      // requestNo: ["", Validators.required],
      notOver: ["", Validators.required],
      periodWithdraw: ["", Validators.required],
      periodWithdrawTo: ["", Validators.required],
      totalMonth: ["", Validators.required],
      totalWithdraw: ["", Validators.required],
      receipts: ["", Validators.required]
    });
  }

  calendar = function() {
    $("#periodCld").calendar({
      // minDate: new Date(),
      type: "month",
      text: TextDateTH,
      formatter: formatter("monthOnly"),
      onChange: (date, ddmmyyyy) => {
        this.rentHouseForm.patchValue({ period: ddmmyyyy });
      }
    });

    $("#periodWithdrawCldform").calendar({
      // minDate: new Date(),
      endCalendar: $("#periodWithdrawCldto"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("monthOnly"),
      onChange: (date, ddmmyyyy) => {
        this.monthStart = date;
        this.rentHouseForm.patchValue({
          periodWithdraw: ddmmyyyy
        });
      }
    });

    $("#periodWithdrawCldto").calendar({
      // minDate: new Date(),
      startCalendar: $("#periodWithdrawCldform"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("monthOnly"),
      onChange: (date, ddmmyyyy) => {
        let amountMonth =
          Math.round(moment(date).diff(this.monthStart, "months", true)) + 1;
        this.rentHouseForm.get("totalMonth").setValue(amountMonth);
        this.rentHouseForm.patchValue({
          periodWithdrawTo: ddmmyyyy
        });

        //sum totalWithdraw
        let salary = this.rentHouseForm.get("salary").value;
        // let totalMonth = this.rentHouseForm.get("totalMonth").value;
        this.rentHouseForm.get("totalWithdraw").setValue(salary * amountMonth);
      }
    });
  };

  //func check validator
  validateField(value: string) {
    return this.submitted && this.rentHouseForm.get(value).errors;
  }

  cbLoading = param => {
    this.loadingUL = param;
  };

  save(e) {
    e.preventDefault();
    this.msg.comfirm(confirm => {
      if (confirm) {
        this.submitted = true;
        // stop here if form is invalid
        if (this.rentHouseForm.invalid) {
          this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ");
          return;
        }
        this.loadingUL = true;
        this.selfService.save(this.rentHouseForm.value, this.cbLoading);
      }
    }, "ยืนยันการบันทึกข้อมูล");
  }

  typeNumber(e) {
    return Utils.onlyNumber(e);
  }

  onUpload(e) {
    e.preventDefault();
    if ($("#files").val() === "") {
      this.msg.errorModal("กรุณาเลือกไฟล์อัปโหลด");
    } else {
      this.selfService.onUpload().then(data => {
        this.tableUpload = data;
      });
    }
  }

  onChangeFile() {
    this.flgOnLoad = false;
  }

  onDel(index: number) {
    this.loadingUL = true;
    this.selfService.onDel(index);
    setTimeout(() => {
      this.loadingUL = false;
    }, 300);
  }

  showSalary(e) {
    this.rentHouseForm.get("notOver").setValue(e.target.value);
  }
}

import { Component, OnInit } from "@angular/core";
import { Int0806Service } from "./int08-6.service";
import { AuthService } from "services/auth.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { BreadCrumb } from "models/breadcrumb";
import { MessageBarService } from "services/message-bar.service";
import { ComboBoxService } from "helpers/comboBox";
import { TextDateTH, formatter } from "helpers/datepicker";
import { loadavg } from "os";

declare var $: any;
@Component({
  selector: "app-int08-6",
  templateUrl: "./int08-6.component.html",
  styleUrls: ["./int08-6.component.css"],
  providers: [Int0806Service, ComboBoxService]
})
export class Int086Component implements OnInit {
  breadcrumb: BreadCrumb[];
  searchForm: FormGroup;
  submitted: boolean = false;
  departmentList: any;
  accounts: any;

  constructor(
    private selfService: Int0806Service,
    private combobox: ComboBoxService,
    private authService: AuthService,
    private fb: FormBuilder,
    private msg: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบรายได้", route: "#" },
      { label: "การตรวจสอบการนำส่งเงินบัญชี", route: "#" }
    ];
    this.departmentList = this.selfService.LovGetValue1("SECTOR_VALUE");
    this.accounts = this.combobox.Lov("ACCOUNT", null);
  }
  setVariable() {
    this.searchForm = this.fb.group({
      startDate: ["", Validators.required],
      endDate: ["", Validators.required],
      department: ["", Validators.required],
      account: ["", Validators.required]
    });
  }

  //func check validator
  validateField(value: string) {
    return this.submitted && this.searchForm.get(value).errors;
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram("INT-0806");
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");
    this.setVariable();
    this.calendar();
  }

  calendar = function() {
    $("#calendar").calendar({
      endCalendar: $("#calendar1"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("ดป"),
      onChange: (date, mmyyyy) => {
        this.searchForm.get("startDate").setValue(mmyyyy);
      }
    });

    $("#calendar1").calendar({
      // minDate: new Date(),
      startCalendar: $("#calendar"),
      type: "month",
      text: TextDateTH,
      formatter: formatter("ดป"),
      onChange: (date, mmyyyy) => {
        this.searchForm.get("endDate").setValue(mmyyyy);
      }
    });
  };

  handleSearch(e) {
    e.preventDefault();
    this.submitted = true;
    // stop here if form is invalid
    if (this.searchForm.invalid) {
      this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ");
      return;
    }
  }
}

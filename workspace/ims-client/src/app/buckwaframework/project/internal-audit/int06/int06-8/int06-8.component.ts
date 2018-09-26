import { Component, OnInit, AfterViewInit, ViewChild } from "@angular/core";
import { TextDateTH, formatter } from "helpers/datepicker";
import { BreadCrumb } from "models/breadcrumb";
import { AjaxService } from "services/ajax.service";
import { Router } from "@angular/router";
import { MessageBarService } from "../../../../common/services";
import { NgForm } from "@angular/forms";
import { Int068Service } from "./int06-8.service";
import { Observable } from "rxjs";

declare var $: any;
@Component({
  selector: "app-int06-8",
  templateUrl: "./int06-8.component.html",
  styleUrls: ["./int06-8.component.css"],
  providers: [Int068Service]
})
export class Int068Component implements OnInit, AfterViewInit {
  @ViewChild("f")
  form: NgForm; // #f
  @ViewChild("f2")
  form2: NgForm; // #f

  breadcrumb: BreadCrumb[] = [];
  publicUtilityTypeList: string[];
  formPU: PU;

  constructor(private int068Service: Int068Service) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "บันทึกข้อมูลการเบิกค่าสาธารณูปโภค", route: "#" }
    ];

    this.publicUtilityTypeList = [
      "ค่าไฟฟ้า",
      "ค่าน้ำประปา",
      "ค่าโทรศัพท์สำนักงาน",
      "ค่าโทรศัพท์เคลื่อนที่",
      "ค่าไปรษณีย์โทรเลข",
      "ค่าบริการสื่อสารและโทรคมนาคม"
    ];
  }

  ngAfterViewInit() {
    $("#showForm1").hide();
    $("#showForm2").hide();
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    $("#monthInVoiceDate")
      .calendar({
        maxDate: new Date(),
        type: "month",
        text: TextDateTH,
        formatter: formatter("month"),
        onChange: (date, text, mode) => {
          this.form2.controls.monthInVoice.setValue(text);
        }
      })
      .css("width", "100%");

    $("#inVoice")
      .calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter(),
        onChange: (date, text, mode) => {
          this.form2.controls.inVoiceDate.setValue(text);
        }
      })
      .css("width", "100%");

    $("#withDrawal")
      .calendar({
        maxDate: new Date(),
        type: "date",
        text: TextDateTH,
        formatter: formatter(),
        onChange: (date, text, mode) => {
          this.form2.controls.withDrawalDate.setValue(text);
        }
      })
      .css("width", "100%");
  }

  onSave = (form: NgForm) => {
    if (form.valid) {
      const { allocatedBudget } = form.controls;
      console.log(allocatedBudget.value);
      this.int068Service.saveAlloclatedBudget(allocatedBudget.value);
    }
  };

  onAdd = (form2: NgForm) => {
    if (form2.valid) {
      const {
        publicUtilityType,
        monthInVoice,
        inVoiceNumber,
        inVoiceDate,
        withDrawalNumber,
        withDrawalDate,
        amount
      } = form2.controls;

      let setPU = new PU();
      setPU.publicUtilityType = publicUtilityType.value;
      setPU.monthInvoice = monthInVoice.value;
      setPU.invoiceNumber = inVoiceNumber.value;
      setPU.invoiceDate = inVoiceDate.value;
      setPU.withdrawalNumber = withDrawalNumber.value;
      setPU.withdrawalDate = withDrawalDate.value;
      setPU.amount = amount.value;
      setPU.chkFlag = "ADD";
      this.int068Service.check(setPU);
    }
  };

  onSaveTable = () => {
    let setPU = new PU();
    setPU.chkFlag = "SAVE";
    this.int068Service.check(setPU);
  };

  checkStatus() {}

  // confirmSend = () => {
  //   this.int068Service.checkConfirm();
  // };
}

class PU {
  allocatedBudgetId: any = null;
  publicUtilityType: any = null;
  monthInvoice: any = null;
  invoiceNumber: any = null;
  invoiceDate: any = null;
  withdrawalNumber: any = null;
  withdrawalDate: any = null;
  amount: any = null;
  chkFlag: any = "";
}

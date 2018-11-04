import { Component, OnInit, AfterViewInit } from "@angular/core";
import {
  MessageBarService,
  AuthService
} from "../../../../../../common/services";
import { Router, ActivatedRoute } from "@angular/router";

import { TravelService } from "../../../../../../common/services/travel.service";
import { BreadCrumb } from "models/index";
import { formatter, TextDateTH } from "helpers/datepicker";
import { Int09114Service } from "./Int09-1-1-4.service";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Utils } from "helpers/utils";

declare var $: any;
@Component({
  selector: "app-int09-1-1-4",
  templateUrl: "./int09-1-1-4.component.html",
  styleUrls: ["./int09-1-1-4.component.css"],
  providers: [Int09114Service]
})
export class Int09114Component implements OnInit, AfterViewInit {
  travelCostForm: FormGroup;
  idProcess: any;
  breadcrumb: BreadCrumb[];
  submitted: boolean = false;

  constructor(
    private selfService: Int09114Service,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService,
    private travelService: TravelService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ประมาณการค่าใช้จ่ายในการเดินทางไปราชการ", route: "#" },
      { label: "รายละเอียดเอกสาร", route: "#" },
      { label: "สร้างเอกสารใบเบิกค่าใช้จ่ายในการเดินทางไปราชการ", route: "#" }
    ];
  }

  ngAfterViewInit() {}

  ngOnInit() {
    this.authService.reRenderVersionProgram("INT-09114");
    this.setVariable();
    this.idProcess = this.route.snapshot.queryParams["idProcess"];
    console.log("idProcess : ", this.idProcess);
    this.calendar();
    // this.hideModal();
  }

  setVariable() {
    this.travelCostForm = this.formBuilder.group({
      idProcess: [""],
      orderLoan: ["", Validators.required],
      dateRefStr: ["", Validators.required],
      borrowerName: ["", Validators.required],
      amount: ["", Validators.required],
      dateCurrentStr: ["", Validators.required],
      subject: ["", Validators.required],
      invite: ["", Validators.required],
      saveTo: ["", Validators.required],
      dateSaveToStr: ["", Validators.required],
      approve: ["", Validators.required],
      position: ["", Validators.required],
      stack: ["", Validators.required],
      descTravel: ["", Validators.required],
      service: ["", Validators.required],
      day: ["", Validators.required],
      travelRadio: ["", Validators.required],
      datetravelRadioStr: ["", Validators.required],
      backToRadio: ["", Validators.required],
      dateBackToRadioStr: ["", Validators.required],
      withdrawRadio: ["", Validators.required],
      allowanceAmount: ["", Validators.required],
      allowance: ["", Validators.required],
      accommodationCostAmount: ["", Validators.required],
      accommodationCost: ["", Validators.required],
      otherCost: ["", Validators.required],
      travelCost: ["", Validators.required],
      totalCost: ["", Validators.required],
      totalCostStr: ["", Validators.required],
      docAmount: ["", Validators.required],
      payee: ["", Validators.required],
      payeePosition: ["", Validators.required],
      getTravelCost: ["", Validators.required],
      getTravelCostStr: ["", Validators.required],
      payee2: ["", Validators.required],
      payeePosition2: ["", Validators.required],
      datePayerStr: ["", Validators.required],
      descTravel2: ["", Validators.required]
    });
  }

  //form controls
  get f() {
    return this.travelCostForm.controls;
  }

  typeNumber(e) {
    return Utils.onlyNumber(e);
  }

  calendar = function() {
    $("#dateRefCld").calendar({
      minDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter("ว/ด/ป"),
      onChange: (date, ddmmyyyy) => {
        this.travelCostForm.patchValue({ dateRefStr: ddmmyyyy });
      }
    });
    $("#dateCurrentCld").calendar({
      minDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter("ว/ด/ป"),
      onChange: (date, ddmmyyyy) => {
        this.travelCostForm.patchValue({ dateCurrentStr: ddmmyyyy });
      }
    });
    $("#dateSaveToCld").calendar({
      minDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter("ว/ด/ป"),
      onChange: (date, ddmmyyyy) => {
        this.travelCostForm.patchValue({ dateSaveToStr: ddmmyyyy });
      }
    });
    $("#datetravelRadioCld").calendar({
      minDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter("ว/ด/ป"),
      onChange: (date, ddmmyyyy) => {
        this.travelCostForm.patchValue({
          datetravelRadioStr: ddmmyyyy
        });
      }
    });
    $("#dateBackToRadioCld").calendar({
      minDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter("ว/ด/ป"),
      onChange: (date, ddmmyyyy) => {
        this.travelCostForm.patchValue({
          dateBackToRadioStr: ddmmyyyy
        });
      }
    });
    $("#datePayerCld").calendar({
      minDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter("ว/ด/ป"),
      onChange: (date, ddmmyyyy) => {
        this.travelCostForm.patchValue({ datePayerStr: ddmmyyyy });
      }
    });
  };

  clickBack() {
    this.selfService.clickBack(this.idProcess);
  }

  save() {
    this.submitted = true;
    console.log(this.travelCostForm.value);
    // stop here if form is invalid
    if (this.travelCostForm.invalid) {
      this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ");
      return;
    }
    this.travelCostForm.patchValue({ idProcess: this.idProcess });
    this.selfService.save(this.travelCostForm.value);
  }
}

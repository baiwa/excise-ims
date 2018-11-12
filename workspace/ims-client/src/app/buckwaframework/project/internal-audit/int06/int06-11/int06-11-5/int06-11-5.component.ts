import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Int061105Service } from "./int06-11-5.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MessageBarService } from "services/message-bar.service";
import { BreadCrumb } from "models/breadcrumb";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { AuthService } from "services/auth.service";

declare var $: any;
@Component({
  selector: "app-int06-11-5",
  templateUrl: "./int06-11-5.component.html",
  styleUrls: ["./int06-11-5.component.css"],
  providers: [Int061105Service]
})
export class Int06115Component implements OnInit, AfterViewInit {
  // createWdRequest: FormGroup;
  searchForm: FormGroup;
  breadcrumb: BreadCrumb[];
  submitted: boolean = false;
  withdrawRequestList: Observable<ComboBox>;
  groupData: Object;
  createdBy: string;
  position: string;
  idNextval: any;
  affiliation: any;
  checkBtn1: boolean = false;
  checkBtn2: boolean = false;
  // statusList: Observable<ComboBox>;

  constructor(
    private authService: AuthService,
    private selfService: Int061105Service,
    private fb: FormBuilder,
    private msg: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจสอบหลักฐานการเบิก", route: "#" }
    ];

    this.withdrawRequestList = selfService.dropdown("WITHDRAW_REQUEST", null);
    // this.statusList = selfService.dropdown("WITHDRAW_STATUS", null);

    this.groupData = {
      id: "",
      billLading: "",
      createdBy: "",
      position: "",
      affiliation: "",
      createdDateStr: "",
      amount: ""
    };
  }

  setVariable() {
    this.searchForm = this.fb.group({
      withdrawRequest: ["", Validators.required]
      // status: ["", Validators.required]
    });
  }

  //func check validator
  validateField(value: string) {
    return this.submitted && this.searchForm.get(value).errors;
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram("INT-06115").then(data => {
      this.createdBy = data.fullName;
      this.position = data.title;
      this.affiliation = data.position;
    });
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");
    this.setVariable();
  }

  ngAfterViewInit(): void {
    $("#showTable").hide();
    this.selfService.Datatable();
    this.selfService.clickTdButton(); //click button in datatable
  }

  handleSearch(e) {
    e.preventDefault();
    this.submitted = true;
    // stop here if form is invalid
    if (this.searchForm.invalid) {
      this.msg.errorModal("กรุณากรอกข้อมูลให้ครบ");
      return;
    }
    this.checkBtn1 = true;
    this.selfService.search(this.searchForm.value);
  }

  total = e => {
    e.preventDefault();
    this.checkBtn2 = true;
    this.selfService
      .getNextval()
      .then(idNextval => {
        this.groupData = {
          id: idNextval,
          billLading: "",
          createdBy: this.createdBy,
          position: this.position,
          affiliation: this.affiliation,
          createdDateStr: new Date().toLocaleDateString(),
          amount:
            parseFloat($("#process").val()) +
            parseFloat($("#pass").val()) +
            parseFloat($("#notPass").val())
        };
      })
      .then(() => {
        $("#showTable").show();
      });
  };

  save = e => {
    e.preventDefault();
    this.selfService.save(this.groupData);
  };

  disabledBtn = () => {
    this.checkBtn1 = false;
    this.checkBtn2 = false;
  };
}

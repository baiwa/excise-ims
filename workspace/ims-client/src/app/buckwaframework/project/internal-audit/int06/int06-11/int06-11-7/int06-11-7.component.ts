import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Int061107Service } from "./int06-11-7.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MessageBarService } from "services/message-bar.service";
import { BreadCrumb } from "models/breadcrumb";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { AuthService } from "services/auth.service";
import { ActivatedRoute } from "@angular/router";

declare var $: any;
@Component({
  selector: "app-int06-11-7",
  templateUrl: "./int06-11-7.component.html",
  styleUrls: ["./int06-11-7.component.css"],
  providers: [Int061107Service]
})
export class Int06117Component implements OnInit, AfterViewInit {
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

  id:any;
  withdrawRequest:any;

  constructor(
    private authService: AuthService,
    private selfService: Int061107Service,
    private fb: FormBuilder,
    private route: ActivatedRoute,
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
    this.id = this.route.snapshot.queryParams["id"];
    this.withdrawRequest = this.route.snapshot.queryParams["withdrawRequest"];
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");
    this.setVariable();
    this.handleSearch();
  }

  ngAfterViewInit(): void {
    $("#showTable").hide();
    this.selfService.Datatable();
    this.selfService.clickTdButton(); //click button in datatable
  }

  handleSearch() {
    this.selfService.search(this.id,this.withdrawRequest);
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

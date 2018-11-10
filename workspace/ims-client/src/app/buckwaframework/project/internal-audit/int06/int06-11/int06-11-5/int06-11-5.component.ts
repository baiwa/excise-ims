import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Int061105Service } from "./int06-11-5.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MessageBarService } from "services/message-bar.service";
import { BreadCrumb } from "models/breadcrumb";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";

declare var $: any;
@Component({
  selector: "app-int06-11-5",
  templateUrl: "./int06-11-5.component.html",
  styleUrls: ["./int06-11-5.component.css"],
  providers: [Int061105Service]
})
export class Int06115Component implements OnInit, AfterViewInit {
  createWdRequest: FormGroup;
  searchForm: FormGroup;
  breadcrumb: BreadCrumb[];
  submitted: boolean = false;
  withdrawRequestList: Observable<ComboBox>;
  statusList: Observable<ComboBox>;

  constructor(
    private selfService: Int061105Service,
    private fb: FormBuilder,
    private msg: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "สร้างใบขอเบิก", route: "#" }
    ];

    this.withdrawRequestList = selfService.dropdown("WITHDRAW_REQUEST", null);
    this.statusList = selfService.dropdown("WITHDRAW_STATUS", null);
  }

  setVariable() {
    this.searchForm = this.fb.group({
      withdrawRequest: ["", Validators],
      status: ["", Validators]
    });
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown ai").css("width", "100%");
    this.setVariable();
  }

  ngAfterViewInit(): void {
    this.selfService.Datatable();
  }

  handleSearch(e) {
    e.preventDefault();
    console.log(this.searchForm.value);
    this.selfService.search(this.searchForm.value);
  }
}

import { Component, OnInit, AfterViewInit, ViewChild } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { Router } from "@angular/router";
import { MessageBarService, AuthService } from "../../../../common/services";
import { TextDateTH, formatter } from "helpers/datepicker";
import { BreadCrumb, ComboBox } from "models/index";
import { Int067Service } from "./int06-7.service";
import { Observable } from "rxjs";

declare var $: any;
@Component({
  selector: "app-int06-7",
  templateUrl: "./int06-7.component.html",
  styleUrls: ["./int06-7.component.css"],
  providers: [Int067Service]
})
export class Int067Component implements OnInit, AfterViewInit {
  breadcrumb: BreadCrumb[] = [];
  comboBox1: Observable<ComboBox[]>;
  comboBox: void;
  budgetYear: any = null;

  constructor(
    private int067Service: Int067Service,
    private ajax: AjaxService,
    private router: Router,
    private msg: MessageBarService,
    private authService: AuthService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจการเบิกค่าสาธารณูปโภค", route: "#" }
    ];
    this.comboBox1 = this.int067Service.pullComboBox(
      "SECTOR_VALUE",
      "comboBox1"
    );
  }

  ngAfterViewInit() {
    $("#showData").hide();
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06700');
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    $("#budgetYearCal")
      .calendar({
        maxDate: new Date(),
        type: "year",
        text: TextDateTH,
        formatter: formatter("year"),
        onChange: (date, text, mode) => {
          this.budgetYear = text - 543;
        }
      })
      .css("width", "100%");
  }

  onFilter = () => {
    $("#showData").show();
    let combo1 = $("#combo1 option:selected").text();
    let combo2 = $("#combo2 option:selected").text();
    let combo3 = $("#combo3 option:selected").text();

    if (combo1 === "เลือก") {
      combo1 = "";
    }
    if (combo2 === "เลือก") {
      combo2 = "";
    }
    if (combo3 === "เลือก") {
      combo3 = "";
    }
    this.int067Service.findByFilter(combo1, combo2, combo3, this.budgetYear);
  };

  clearFile = () => {
    $("#showData").hide();
    setTimeout(
      () => $("#budgetYear").calendar("clear"),
      $("#combo1").dropdown("restore defaults"),
      $("#combo2").dropdown("restore defaults"),
      $("#combo3").dropdown("restore defaults"),
      200
    );
  };

  setTime = () => {
    this.router.navigate(["/int06/7/1"]);
  };

  dropdown(e, combo: string) {
    e.preventDefault();
    this[combo] = this.int067Service.pullComboBox(
      "SECTOR_VALUE",
      combo,
      e.target.value
    );
  }

  
}

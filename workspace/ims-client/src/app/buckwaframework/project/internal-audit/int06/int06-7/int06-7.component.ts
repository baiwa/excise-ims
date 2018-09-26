import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { Router } from "@angular/router";
import { MessageBarService } from "../../../../common/services";
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

  constructor(
    private int067Service: Int067Service,
    private ajax: AjaxService,
    private router: Router,
    private msg: MessageBarService
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
    // this.comboBox = this.int067Service.pullComboBox();
  }

  ngAfterViewInit() {
    $("#showData").hide();
  }

  ngOnInit() {
    // this.int067Service.pullComboBox("SECTOR_VALUE", "comboBox1").subscribe();

    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    $("#budgetYear")
      .calendar({
        maxDate: new Date(),
        type: "year",
        text: TextDateTH,
        formatter: formatter("year")
        // onChange: (date, text, mode) => {
        //   this.budgetYear = text;
        // }
      })
      .css("width", "100%");
  }

  uploadFile = () => {
    $("#showData").show();
  };

  clearFile = () => {
    $("#showData").hide();
    $("#budgetYear").calendar("refresh");
    $("#supplyChoice").dropdown("restore defaults");
    $("#budgetType").dropdown("restore defaults");
  };

  setTime = () => {
    this.router.navigate(["/int06/7/1"]);
  };

  dropdown(e, combo: string) {
    console.log(e.target.value);
    e.preventDefault();
    this[combo] = this.int067Service.pullComboBox(
      "SECTOR_VALUE",
      combo,
      e.target.value
    );
  }
}

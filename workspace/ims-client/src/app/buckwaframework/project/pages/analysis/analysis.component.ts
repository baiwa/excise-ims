import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AnalysisService } from "projects/pages/analysis/analysis.service";
import { BreadCrumb } from "models/breadcrumb";
import { TextDateTH, formatter } from "helpers/datepicker";
import { FormGroup, FormBuilder, Validators, NgForm } from "@angular/forms";
import { AnalysisForm } from "projects/pages/analysis/analysisForm.model";
import { IaService } from "services/ia.service";

declare var $: any;
@Component({
  selector: "analysis",
  templateUrl: "analysis.component.html",
  providers: [AnalysisService]
})
export class AnalysisPage implements OnInit {
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภาษี", route: "#" },
    { label: "การตรวจสอบภาษี", route: "#" },
    { label: "การวิเคราะห์ข้อมูลเบื้องต้น", route: "#" },
  ];
  showSelectCoordinate: boolean = false;
  coordinateList: any[];

  productList: any[];
  serviceList: any[];
  loading: boolean = true;

  //data
  exciseIdList: any;
  form: AnalysisForm = new AnalysisForm();
  coordinates: string = "";
  constructor(
    private router: Router,
    private analysisService: AnalysisService,
    private modalService: IaService

  ) { }

  ngOnInit(): void {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");

    this.exciseIdLists();
    this.calenda();
  }
  // selectCatagory() {
  //   var value = $("#selectCatagory").val();
  //   if (value == 1) {
  //     this.showSelectCoordinate = true;
  //     this.coordinateList = this.productList;
  //   } else if (value == 2) {
  //     this.showSelectCoordinate = true;
  //     this.coordinateList = this.serviceList;
  //   } else {
  //     this.showSelectCoordinate = false;
  //   }
  // }

  goToAnalysisResult = (form) => {

    console.log(form.Validators);
    console.log(form.value);
    console.log("top");
    // this.form.dateFrom = $("#dateFrom").val();
    // this.form.dateTo = $("#dateTo").val();
    // this.modalService.setData(this.form);
    // this.router.navigate(["/result-analysis"]);
  }

  exciseIdLists = () => {
    this.analysisService.exciseIdList().then(res => {
      this.exciseIdList = res;
      this.loading = false;
    });
    console.log(this.exciseIdList);
  }

  changeExciseId = (event) => {
    let exciseId = event.target.value;

    this.analysisService.changeExciseId(exciseId).then(res => {

      this.form.coordinates = res.productType;

      //call function check typr
      this.form.type = this.checkType(exciseId.substring(14, 15));
    });
  }

  calenda = () => {
    $("#dateF").calendar({
      maxDate: new Date(),
      endCalendar: $("#dateT"),
      type: "month",
      text: TextDateTH,
      formatter: formatter('month-year')
    });
    $("#dateT").calendar({
      maxDate: new Date(),
      startCalendar: $("#dateF"),
      type: "month",
      text: TextDateTH,
      formatter: formatter('month-year')
    });
  }

  checkType(typeId) {
    switch (typeId) {
      case "1": { return "สินค้น"; }
      case "2": { return "บริการ"; }
      case "3": { return "นำเข้า"; }
      default: { return ""; }
    }
  }
}

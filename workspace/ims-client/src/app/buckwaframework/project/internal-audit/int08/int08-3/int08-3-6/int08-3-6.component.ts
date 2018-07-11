import { Component, OnInit } from "@angular/core";

declare var $: any;
@Component({
  selector: "int08-3-6",
  templateUrl: "./int08-3-6.component.html",
  styleUrls: ["./int08-3-6.component.css"]
})
export class Int0836Component implements OnInit {
  constructor() {}

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
  }

  ngAfterViewInit() {
    $("#selectCondition1").dropdown();
    $("#selectCondition2").dropdown();
    $("#selectCondition3").dropdown();
    $("#selectColor1").dropdown();
    $("#selectColor2").dropdown();
    $("#selectColor3").dropdown();
  }

  popupEditData() {
    $("#modalInt0836").modal("show");
  }

  closePopupEdit() {
    $("#modalInt0836").modal("hide");
  }

  popupWeightData() {
    $("#modalInt0836-weight-data").modal("show");
  }

  clearPopupWeightData() {
    $("#modalInt0836-weight-data").modal("hide");
  }
}

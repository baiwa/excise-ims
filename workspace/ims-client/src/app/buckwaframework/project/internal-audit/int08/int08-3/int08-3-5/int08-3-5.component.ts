import { Component, OnInit } from "@angular/core";

declare var $: any;
@Component({
  selector: "int08-3-5",
  templateUrl: "./int08-3-5.component.html",
  styleUrls: ["./int08-3-5.component.css"]
})
export class Int0835Component implements OnInit {
  showData: boolean = false;

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

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

  popupEditData() {
    $("#modalInt0835").modal("show");
  }

  closePopupEdit() {
    $("#modalInt0835").modal("hide");
  }
}

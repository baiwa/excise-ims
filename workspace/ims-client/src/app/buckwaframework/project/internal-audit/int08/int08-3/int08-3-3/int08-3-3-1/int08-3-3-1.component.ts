import { Component, OnInit } from "@angular/core";

declare var $: any;
@Component({
  selector: "int08-3-3-1",
  templateUrl: "./int08-3-3-1.component.html",
  styleUrls: ["./int08-3-3-1.component.css"]
})
export class Int08331Component implements OnInit {
  showData: boolean = false;

  constructor() {}

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
  }

  ngAfterViewInit() {
    $("#select1").hide();
    $("#select2").hide();
    $("#select3").hide();
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
    $("#select1").show();
    $("#select2").show();
    $("#select3").show();
    $("#modalInt08331").modal("show");
  }

  closePopupEdit() {
    $("#select1").hide();
    $("#select2").hide();
    $("#select3").hide();
    $("#modalInt08331").modal("hide");
  }
}

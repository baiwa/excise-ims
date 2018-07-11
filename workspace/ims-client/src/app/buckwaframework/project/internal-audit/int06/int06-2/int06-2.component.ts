import { Component, OnInit } from "@angular/core";
declare var $: any;
@Component({
  selector: "int06-2",
  templateUrl: "./int06-2.component.html",
  styleUrls: ["./int06-2.component.css"]
})
export class Int062Component implements OnInit {
  showData: boolean = false;

  constructor() {}

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
  }
  ngAfterViewInit() {
    $("#export .dropdown").dropdown({
      transition: "drop"
    });
  }
  popupEditData() {
    $("#modalInt062").modal("show");
  }

  closePopupEdit() {
    $("#modalInt062").modal("hide");
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }
}

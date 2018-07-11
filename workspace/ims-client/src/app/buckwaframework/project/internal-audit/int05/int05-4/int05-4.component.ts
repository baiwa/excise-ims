import { Component, OnInit } from "@angular/core";

declare var $: any;
@Component({
  selector: "int05-4",
  templateUrl: "./int05-4.component.html",
  styleUrls: ["./int05-4.component.css"]
})
export class Int054Component implements OnInit {
  showData: boolean = false;
  constructor() {}

  uploadFile() {
    this.showData = true;
  }
  clearFile() {
    this.showData = false;
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    $("#selectTrading").hide();
  }
  ngAfterViewInit() {
    $("#export .dropdown").dropdown({
      transition: "drop"
    });
  }

  addData() {
    $("#modalInt054").modal("show");
    $("#selectTrading").show();
  }

  editData() {
    $("#modalInt054").modal("show");
    $("#selectTrading").show();
  }

  closeModal() {
    $("#modalInt054").modal("hide");
  }
}

import { Component, OnInit } from "@angular/core";

declare var $: any;
@Component({
  selector: "app-int05-4-admin",
  templateUrl: "./int05-4-admin.component.html",
  styleUrls: ["./int05-4-admin.component.css"]
})
export class Int054AdminComponent implements OnInit {
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

import { Component, OnInit } from "@angular/core";
import { AuthService } from "services/auth.service";
declare var $: any;
@Component({
  selector: "int06-2",
  templateUrl: "./int06-2.component.html",
  styleUrls: ["./int06-2.component.css"]
})
export class Int062Component implements OnInit {
  showData: boolean = false;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06200');

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

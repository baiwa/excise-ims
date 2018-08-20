import { Component, OnInit } from '@angular/core';
declare var $: any;

@Component({
  selector: 'app-int08-2-6',
  templateUrl: './int08-2-6.component.html',
  styleUrls: ['./int08-2-6.component.css']
})
export class Int0826Component implements OnInit {
  showData: boolean = false;
 
  constructor() { }

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
    $("#modalInt082").modal("show");
  }

  closePopupEdit() {
    $("#select1").hide();
    $("#select2").hide();
    $("#select3").hide();
    $("#modalInt082").modal("hide");
  }
}

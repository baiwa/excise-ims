import { Component, OnInit, OnDestroy } from "@angular/core";

var jQuery: any;
declare var $: any;

@Component({
  selector: "app-int02-m3",
  templateUrl: "./int02-m3.component.html",
  styleUrls: ["./int02-m3.component.css"]
})
export class Int02M3Component implements OnInit, OnDestroy {
  constructor() {}

  ngOnDestroy() {
    $(".ui.modal.show").remove();
  }

  ngOnInit() {
    $("#showList").click(function() {
      $(".ui.modal.show").modal("show");
    });

    $("#closeList").click(function() {
      $(".ui.modal.show").modal("hide");
    });
  }
}

import { Component, OnInit } from "@angular/core";

declare var $: any;
@Component({
  selector: "app-int05-4-1",
  templateUrl: "./int05-4-1.component.html",
  styleUrls: ["./int05-4-1.component.css"]
})
export class Int0541Component implements OnInit {
  choice: string = "";
  constructor() {}

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
  }

  onChangeChoice = () => {
    console.log(this.choice);
  };
}

import { Component, OnInit } from "@angular/core";

var jQuery: any;
declare var $: any;

@Component({
  selector: "app-int02-m2",
  templateUrl: "./int02-m2.component.html",
  styleUrls: ["./int02-m2.component.css"]
})
export class Int02M2Component implements OnInit {
  DetailTest1: any[];
  constructor() {
    this.DetailTest1 = ["mamsodjio", "asdkop", "kaopsdksaof", "akofpkakspod"];
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
  }

  onSubmit = (e, i) => {
    e.preventDefault();
    var sector = (<HTMLInputElement>document.getElementById("sector")).value;
    var area = (<HTMLInputElement>document.getElementById("area")).value;
    var date = (<HTMLInputElement>document.getElementById("date")).value;
    var test = (<HTMLInputElement>document.getElementById("test")).value;
    var conclude = (<HTMLInputElement>document.getElementById("conclude"))
      .value;
    console.log(
      "sector: ",
      sector,
      "area: ",
      area,
      "test: ",
      test,
      "date: ",
      date,
      "conclude: ",
      conclude
    );
  };
}

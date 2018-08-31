import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../common/helper";

import { Questionnaire, _Questionnaire } from "./int02-m2.mock";

declare var $: any;

@Component({
  selector: "app-int02-m2",
  templateUrl: "./int02-m2.component.html",
  styleUrls: ["./int02-m2.component.css"]
})
export class Int02M2Component implements OnInit {

  questionnaire: Questionnaire[] = [];

  constructor() {
    // TODO
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    // $('.ui.accordion').accordion();
    $("#calendar").calendar({
      text: TextDateTH,
      type: "date",
      formatter: formatter()
    });
  }

  search = (e) => {
    e.preventDefault();
    this.questionnaire = _Questionnaire;
    setTimeout(() => {
      $('.ui.accordion').accordion();
    }, 200);
  }

}
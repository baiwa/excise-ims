import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "../../../../common/helper";

import { Questionnaire, _Questionnaire } from "./int02-m2.mock";
import { AjaxService } from "../../../../common/services";

declare var $: any;

const URL = {
  DATA: "ia/int02m2/"
};

@Component({
  selector: "app-int02-m2",
  templateUrl: "./int02-m2.component.html",
  styleUrls: ["./int02-m2.component.css"]
})
export class Int02M2Component implements OnInit {

  questionnaire: Questionnaire[] = [];

  constructor(private ajaxService: AjaxService) {
    // TODO
  }

  ngOnInit() {
    $(".ui.dropdown.ai").dropdown().css("width", "100%");
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
    // this.ajaxService.get(URL.DATA, res => {
    //   this.questionnaire = res.json();
    //   setTimeout(() => {
    //     $('.ui.accordion').accordion();
    //   }, 200);
    // });
  }

  save(event) {
    event.preventDefault();
    console.log(this.questionnaire);
  }

}
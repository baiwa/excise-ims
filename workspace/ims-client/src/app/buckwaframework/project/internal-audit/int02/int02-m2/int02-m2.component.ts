import { Component, OnInit } from "@angular/core";

import { Questionnaire, _Questionnaire } from "./int02-m2.mock";
import { AjaxService } from "../../../../common/services";

declare var $: any;

const URL = {
  DATA: "ia/int02m2/all",
  DATA2: "ia/int02m2/master",
  LOV_SECTOR: `combobox/controller/getDropByTypeAndParentId`
};

@Component({
  selector: "app-int02-m2",
  templateUrl: "./int02-m2.component.html",
  styleUrls: ["./int02-m2.component.css"]
})
export class Int02M2Component implements OnInit {

  questionnaire: Questionnaire[] = [];
  loading: boolean = true;
  constructor(private ajax: AjaxService) {
    // TODO
  }

  ngOnInit() {

    this.questionnaire = _Questionnaire;
    this.ajax.get(URL.DATA2, res => {
      this.questionnaire = res.json();
      this.loading = false;
      setTimeout(() => {
        // $('#acc').accordion();
        // $('.ui.checkbox').checkbox();
      });
    });

  }

  save(form) {
    form.preventDefault();
    let promise = new Promise((resolve, reject) => {
      let point = [];
      let conclusion = [];
      $.each(form.target, (index, value) => {
        if (value.id.split("_")[0] == "point") {
          point.push({id: value[1], value: value.value == "true" ? true : false});
          console.log(index, value.value == "true" ? true : false);
        }
        if (form.target.length-1 == index) {
          resolve();
        }
      });
    });
  }

  onSubmit(e) {
    e.preventDefault();
    console.log(e);
  }

}
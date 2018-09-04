import { Component, OnInit } from "@angular/core";

import { Questionnaire, _Questionnaire } from "./int02-m2.mock";
import { AjaxService, AuthService } from "../../../../common/services";

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

  constructor(
    private ajax: AjaxService,
    private auth: AuthService
  ) {
    // TODO
  }

  ngOnInit() {
    const data = {
      username: this.auth.getUser().username
    };
    this.questionnaire = _Questionnaire;
    this.ajax.get(URL.DATA2, res => {
      this.questionnaire = res.json();
      this.loading = false;
      setTimeout(() => {
        $('#acc').accordion();
      });
    });

  }

  save(event) {
    event.preventDefault();
    console.log(this.questionnaire);
  }

}
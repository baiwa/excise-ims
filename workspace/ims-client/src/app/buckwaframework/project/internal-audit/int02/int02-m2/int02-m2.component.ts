import { Component, OnInit, ViewChild } from "@angular/core";

import { Questionnaire, _Questionnaire } from "./int02-m2.mock";
import { AjaxService, AuthService } from "../../../../common/services";
import { NgForm } from "@angular/forms";

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

  @ViewChild("f") form: NgForm;
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
        $('#acc').accordion();
        $('.ui.checkbox').checkbox();
      });
    });

  }

  save(form: NgForm) {
    if (!form.valid) {
      let i = 0;
      $.each(form.controls, (index, value) => {
        if (!value.valid) {
          if (i < 5) {
            $("html, body").stop().animate({ scrollTop: 0 }, 500, 'swing');
          } else {
            $(`#${index}`).focus();
          }
          return;
        }
        i++;
      });
    } else {
      console.log(form);
    }
  }
  validClass(name, form) {
    const { controls, submitted } = form;
    return submitted && !controls[name].valid ? 'error' : '';
  }

}
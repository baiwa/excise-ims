import { Component, OnInit } from "@angular/core";

import { Questionnaire, _Questionnaire } from "./int02-m2.mock";
import { AjaxService } from "../../../../common/services";

declare var $: any;

const URL = {
    DATA: "ia/int02m2/data",
    SAVE: "ia/int02m2/save",
    DATA_MOCK: "ia/int02m2/data_mock"
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
    const data = {};
    this.ajax.post(URL.DATA, data, res => {
      this.questionnaire = res.json();
      this.loading = false;
      setTimeout(() => {
        // $('#acc').accordion();
        $('.ui.checkbox').checkbox();
      });
    });
  }

  onSubmit(form) {
    form.preventDefault();
    let promise = new Promise((resolve, reject) => {
      $.each(form.target, (index, val) => {
        const { id, value, checked } = val; // extract construct
        if (id.split("_")[0] == "point" && checked) {
          this.questionnaire.forEach(obj => {
            obj.detail.forEach(ob => {
              if (ob.detailId == parseInt(id.split("_")[1])) {
                ob.point = value;
              }
            })
          });
        }
        if (id.split("_")[0] == "conclusion") {
          this.questionnaire.forEach(obj => {
            if (obj.detail.length > 0 && obj.detail[0].headerId == id.split("_")[1]) {
              if (value.trim() != "") {
                obj.conclusion = value.trim();
              }
            }
          });
        }
        if (form.target.length - 1 == index) {
          resolve(true);
        }
      });
    }).then(boo => {
      if (boo) {
        this.ajax.post(URL.SAVE, { result: this.questionnaire }, res => {
          
        });
        console.log(this.questionnaire);
      }
    });
  }

}
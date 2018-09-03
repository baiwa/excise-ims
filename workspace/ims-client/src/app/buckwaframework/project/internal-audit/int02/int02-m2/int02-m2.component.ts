import { Component, OnInit, ViewChild } from "@angular/core";
import { TextDateTH, formatter } from "../../../../common/helper";

import { Questionnaire, _Questionnaire } from "./int02-m2.mock";
import { AjaxService } from "../../../../common/services";
import { NgForm } from "@angular/forms";
import { toDateLocale } from "../../../../common/helper/datepicker";

declare var $: any;

const URL = {
  DATA: "ia/int02m2/",
  LOV_SECTOR: `combobox/controller/getDropByTypeAndParentId`
};

@Component({
  selector: "app-int02-m2",
  templateUrl: "./int02-m2.component.html",
  styleUrls: ["./int02-m2.component.css"]
})
export class Int02M2Component implements OnInit {

  @ViewChild('f') form: NgForm;
  sectors: any[] = [];
  locates: any[] = [];
  questionnaire: Questionnaire[] = [];

  constructor(private ajax: AjaxService) {
    // TODO
  }

  ngOnInit() {

    $(".ui.dropdown.ai").dropdown().css("width", "100%");
    
    $("#calendar").calendar({
      maxDate: new Date(),
      type: "year",
      text: TextDateTH,
      formatter: formatter("year"),
      onChange: (date, text, mode) => {
        let _year = toDateLocale(date)[0].split("/")[2];
        this.form.value.calendar_data = _year;
      }
    });

    this.ajax.post(URL.LOV_SECTOR, { type: "SECTOR_VALUE"}, res => { // SECTOR
      this.sectors = res.json();
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

  checkValid(name, f: NgForm) {
    return f.value[name] == '' && !f.valid && f.submitted;
  }

  onSectorChange(e) {
    e.preventDefault();
    let id = this.sectors.find(obj => obj.value1 == e.target.value).lovId;
    if (id != "") {
      this.ajax.post(URL.LOV_SECTOR, { type: "SECTOR_VALUE", lovIdMaster: id }, res => {
        this.locates = res.json();
      });
    }
  }

  onSubmit = (form: NgForm) => {
    const { calendar_data, sector, locate } = form.value;
    if (calendar_data != "" && sector != "" && locate != "") {
      const data = {
        qtnName: `${sector} ${locate}`,
        qtnSector: sector,
        qtnLocate: locate,
        qtnYear: calendar_data
      };
      this.questionnaire = _Questionnaire;
      setTimeout(() => {
        $('.ui.accordion').accordion();
      }, 200);
    } else {
      console.log(form);
    }
  }

}
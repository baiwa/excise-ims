import { Component, OnInit, Output, EventEmitter } from "@angular/core";

import { TextDateTH, formatter } from "../../../../../common/helper/datepicker";
import { AjaxService } from "../../../../../common/services";
declare var jQuery: any;
declare var $: any;

@Component({
  selector: "app-ts01-05",
  templateUrl: "./ts01-05.component.html",
  styleUrls: ["./ts01-05.component.css"]
})
export class Ts0105Component implements OnInit {
  @Output() discard = new EventEmitter<any>();

  numbers: number[];

  obj: Ts0105;

  constructor(private ajax: AjaxService) {
    this.numbers = [1, 2, 3];
    this.obj = new Ts0105();
  }

  ngOnInit() {
    $("#begin_date").calendar({
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#end_date").calendar({
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#nut_date").calendar({
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
    $("#nut1_date").calendar({
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };

  onSubmit = e => {
    e.preventDefault();
    const url = "report/pdf/ts/mis_t_s_01_05";
    this.ajax.post(url, `'${JSON.stringify(this.obj).toString()}'`, res => {
      if (res.status == 200 && res.statusText == "OK") {
        window.open("/ims-webapp/api/report/pdf/mis_t_s_01_05/file");
      }
    });
  };
}

class Ts0105 {
  [x: string]: any;
  at1: string;
  at2: string;
  office: string;
  topic: string;
  day: string;
  month: string;
  year: string;
  sendTo: string;
  date1: string;
  date2: string;
  date3: string;
  time1: string;
  time2: string;
  item1: string;
  item2: string;
  item3: string;
  checker: string;
  position: string;
}

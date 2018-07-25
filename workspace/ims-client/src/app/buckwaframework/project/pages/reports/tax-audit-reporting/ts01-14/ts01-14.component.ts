import { Component, OnInit, Output, EventEmitter } from "@angular/core";

import { TextDateTH, formatter } from "../../../../../common/helper/datepicker";
import { AjaxService } from "../../../../../common/services";
declare var jQuery: any;
declare var $: any;

@Component({
  selector: "app-ts01-14",
  templateUrl: "./ts01-14.component.html",
  styleUrls: ["./ts01-14.component.css"]
})
export class Ts0114Component implements OnInit {
  @Output() discard = new EventEmitter<any>();

  numbers: number[];

  obj: Ts0114;
  beans: Bean[];

  constructor(private ajax: AjaxService) {
    this.obj = new Ts0114();
    this.beans = [new Bean()];
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

  onAddField = () => {
    this.beans.push(new Bean());
  };

  onDelField = index => {
    this.beans.splice(index, 1);
  };

  onSubmit = e => {
    e.preventDefault();
    this.obj.Bean = this.beans;
    const url = "report/pdf/ts/mis_t_s_01_14";
    this.ajax.post(url, `'${JSON.stringify(this.obj).toString()}'`, res => {
      if (res.status == 200 && res.statusText == "OK") {
        window.open("/ims-webapp/api/report/pdf/mis_t_s_01_14/file");
      }
    });
  };
}

class Ts0114 {
  Bean: Bean[];
}

class Bean {
  [x: string]: any;
  date: string;
  type: string;
  money: string;
  chip: string;
  extra: string;
  local: string;
  sum: string;
}

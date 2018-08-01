import { Component, OnInit, Output, EventEmitter } from "@angular/core";

import { TextDateTH, formatter } from "../../../../../common/helper/datepicker";
import { AjaxService } from "../../../../../common/services";
import { ThaiNumber } from "../../../../../common/helper";
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

  onKeyUp = (e: any, str: string) => {
    e.preventDefault();
    this.obj[str] = ThaiNumber(e.target.value.toString());
  };

  onKeyUpBean = (e: any, str: string, index) => {
    e.preventDefault();
    this.obj[index][str] = ThaiNumber(e.target.value.toString());
  };
}

class Ts0114 {
  [x: string]: any;
  logo: string = "logo.jpg";
  taxPrayer: string;
  exciseId: string;
  addressId: string;
  alley: string;
  street: string;
  district: string;
  amphoe: string;
  province: string;
  postcode: string;
  tel: string;
  tele: string;
  businessType: string;
  inspector: string;
  department: string;
  day1: string;
  day2: string;
  day3: string;
  month1: string;
  month2: string;
  month3: string;
  year1: string;
  year2: string;
  year3: string;
  kk: string;
  date1: string;
  date2: string;
  date3: string;
  from: string;
  to: string;
  toMonth: string;
  toDay: string;
  chk1: boolean = false;
  chk2: boolean = false;
  chk3: boolean = false;
  chk3Txt: string;
  where: string;
  doc1: string;
  doc2: string;
  doc3: string;
  doc4: string;
  doc5: string;
  doc6: string;
  doc7: string;
  doc8: string;
  doc9: string;
  times: string;
  resonTxt: string;
  checker: string;
  position: string;
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

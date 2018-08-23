import { TextDateTH, formatter } from './../../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-1-4-1',
  templateUrl: './int09-2-1-4-1.component.html',
})
export class Int092141Component implements OnInit {

  constructor() { }
  calenda = function () {
    $("#date").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
    });
  }
  ngOnInit() {
  }
  ngAfterViewInit() {
    this.calenda();

  }


}

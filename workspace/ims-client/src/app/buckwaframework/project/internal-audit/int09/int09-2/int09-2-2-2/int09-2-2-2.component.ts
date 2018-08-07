import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-2-2',
  templateUrl: './int09-2-2-2.component.html'
})
export class Int09222Component implements OnInit {

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

  ngAfterViewInit(){
    this.calenda();
  }

}

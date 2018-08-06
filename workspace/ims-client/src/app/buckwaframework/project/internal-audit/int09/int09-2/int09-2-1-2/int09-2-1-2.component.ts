import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';
import { format } from 'url';

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-1-2',
  templateUrl: './int09-2-1-2.component.html'
})
export class Int09212Component implements OnInit {

  constructor() { }

  calenda = function(){
    $("#startDate").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter : formatter()
    });
    $("#endDate").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter : formatter()
    });
  }
  ngOnInit() {}

  ngAfterViewInit(){
    this.calenda();
  }

}

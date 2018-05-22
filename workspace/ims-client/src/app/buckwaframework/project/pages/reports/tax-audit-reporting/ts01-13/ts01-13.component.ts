import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-ts01-13',
  templateUrl: './ts01-13.component.html',
})
export class Ts0113Component implements OnInit {

  @Output() discard = new EventEmitter<any>();

  numbers:number[];

  constructor() {
    this.numbers = [1,2,3];
  }

  ngOnInit() {
    $('#begin_date').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter
    });
    $('#end_date').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter
    });
    $('#nut_date').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter
    });
    $('#nut1_date').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter
    });
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };
  
}

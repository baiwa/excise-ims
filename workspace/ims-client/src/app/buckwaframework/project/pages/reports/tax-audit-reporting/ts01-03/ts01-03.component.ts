import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-ts01-03',
  templateUrl: './ts01-03.component.html',
  styleUrls: ['./ts01-03.component.css']
})
export class Ts0103Component implements OnInit {

  @Output() discard = new EventEmitter<any>();

  constructor() {}

  ngOnInit() {
    $('#begin_date').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter()
    });
    $('#end_date').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter()
    });
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };
  
}

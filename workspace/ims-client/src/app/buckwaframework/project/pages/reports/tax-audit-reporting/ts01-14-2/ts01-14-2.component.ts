import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-ts01-14-2',
  templateUrl: './ts01-14-2.component.html',
  styleUrls: ['./ts01-14-2.component.css']
})
export class Ts01142Component implements OnInit {

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

  onAddField = () => {
    let num = this.numbers[this.numbers.length-1];
    this.numbers.push(num+1);
    this.numbers.sort();
  };
  
  onDelField = index => {
    this.numbers.splice(index, 1);
    this.numbers.sort();
  };
  
}

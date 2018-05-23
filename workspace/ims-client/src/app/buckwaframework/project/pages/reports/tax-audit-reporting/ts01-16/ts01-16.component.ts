import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-ts01-16',
  templateUrl: './ts01-16.component.html',
})
export class Ts0116Component implements OnInit {

  @Output() discard = new EventEmitter<any>();

  numbers:number[];
  numbers2:number[];

  constructor() {
    this.numbers = [1,2,3];
    this.numbers2 = [1,2,3];
  }

  ngOnInit() {
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

  onAddField2 = () => {
    let num = this.numbers2[this.numbers2.length-1];
    this.numbers2.push(num+1);
    this.numbers2.sort();
  };
  
  onDelField2 = index => {
    this.numbers2.splice(index, 1);
    this.numbers2.sort();
  };
  
}

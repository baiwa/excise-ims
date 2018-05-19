import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-ts01-01',
  templateUrl: './ts01-01.component.html',
  styleUrls: ['./ts01-01.component.css']
})
export class Ts0101Component implements OnInit {

  @Output() discard = new EventEmitter<any>();

  numbers:number[];

  constructor() {
    this.numbers = [1,2,3];
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
    num < 5 && this.numbers.push(num+1);
  };
  
  onDelField = index => {
    this.numbers.splice(index, 1);
  };
}

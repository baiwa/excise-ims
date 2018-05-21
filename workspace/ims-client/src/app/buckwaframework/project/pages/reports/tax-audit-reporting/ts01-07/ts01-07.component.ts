import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-ts01-07',
  templateUrl: './ts01-07.component.html',
})
export class Ts0107Component implements OnInit {

  @Output() discard = new EventEmitter<any>();

  add: number;

  constructor() {
    this.add = 0;
  }

  ngOnInit() {
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };

  onAddField = () => {
    this.add++;
  };
}

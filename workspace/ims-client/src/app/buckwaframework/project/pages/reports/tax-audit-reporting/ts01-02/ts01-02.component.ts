import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-ts01-02',
  templateUrl: './ts01-02.component.html',
  styleUrls: ['./ts01-02.component.css']
})
export class Ts0102Component implements OnInit {

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

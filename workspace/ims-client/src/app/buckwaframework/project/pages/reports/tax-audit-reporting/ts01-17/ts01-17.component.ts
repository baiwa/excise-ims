import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-ts01-17',
  templateUrl: './ts01-17.component.html',
  styleUrls: ['./ts01-17.component.css']
})
export class Ts0117Component implements OnInit {

  @Output() discard = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };

}

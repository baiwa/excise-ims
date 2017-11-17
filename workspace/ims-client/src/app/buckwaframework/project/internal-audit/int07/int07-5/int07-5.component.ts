import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int07-5',
  templateUrl: './int07-5.component.html',
  styleUrls: ['./int07-5.component.css']
})
export class Int075Component implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  popupEditData() {
    $('#modalInt075').modal('show');
  }

  closePopupEdit() {
    $('#modalInt075').modal('hide');
  }
}

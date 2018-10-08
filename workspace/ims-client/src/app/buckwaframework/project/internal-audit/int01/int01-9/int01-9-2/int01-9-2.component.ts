import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'app-int01-9-2',
  templateUrl: './int01-9-2.component.html',
  styleUrls: ['./int01-9-2.component.css']
})
export class Int0192Component implements OnInit {

  constructor() { }

  ngOnInit() {

    
  }

  addEditData() {
    $('#modal').modal('show');
  }

  closePopupaddData() {
    $('#modal').modal('hide');
  }
  popupEditData() {
    $('#modal1').modal('show');
  }

  closePopupEdit() {
    $('#modal1').modal('hide');
  }
}

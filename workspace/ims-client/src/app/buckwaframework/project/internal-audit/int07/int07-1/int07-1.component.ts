import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int07-1',
  templateUrl: './int07-1.component.html',
  styleUrls: ['./int07-1.component.css']
})
export class Int071Component implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  popupAddData() {
    $('#modalInt071').modal('show');
  }

  closePopupAdd() {
    $('#modalInt071').modal('hide');
  }
}

import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int07-4',
  templateUrl: './int07-4.component.html',
  styleUrls: ['./int07-4.component.css']
})
export class Int074Component implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    $('#export .dropdown')
      .dropdown({
        transition: 'drop'
      });
      
  }

  popupEditData() {
    $('#modalInt074').modal('show');
  }

  closePopupEdit() {
    $('#modalInt074').modal('hide');
  }
}

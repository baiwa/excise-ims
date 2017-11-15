import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int06-4',
  templateUrl: './int06-4.component.html',
  styleUrls: ['./int06-4.component.css']
})
export class Int064Component implements OnInit {

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  clearData() {
    this.showData = false;
  }

  popupEditData() {
    $('#modalOtWithdrawal').modal('show');
  }

  closePopupEdit() {
    $('#modalOtWithdrawal').modal('hide');
  }

  editData() {
    this.showData = true;
  }

}

import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int06-5',
  templateUrl: './int06-5.component.html',
  styleUrls: ['./int06-5.component.css']
})
export class Int065Component implements OnInit {

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  clearData() {
    this.showData = false;
  }

  popupEditData() {
    $('#modalControllerWithdrawal').modal('show');
  }

  closePopupEdit() {
    $('#modalControllerWithdrawal').modal('hide');
  }

  editData() {
    this.showData = true;
  }

}

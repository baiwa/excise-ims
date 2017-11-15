import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'int06-3',
  templateUrl: './int06-3.component.html',
  styleUrls: ['./int06-3.component.css']
})
export class Int063Component implements OnInit {

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  clearData() {
    this.showData = false;
  }

  popupEditData() {
    $('#modalAllowanceWithdrawal').modal('show');
  }

  closePopupEdit() {
    $('#modalAllowanceWithdrawal').modal('hide');
  }

  editData() {
    this.showData = true;
  }
}

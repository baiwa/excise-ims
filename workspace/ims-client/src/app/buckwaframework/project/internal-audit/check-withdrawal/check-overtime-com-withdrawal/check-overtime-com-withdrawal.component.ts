import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'check-overtime-com-withdrawal',
  templateUrl: './check-overtime-com-withdrawal.component.html',
  styleUrls: ['./check-overtime-com-withdrawal.component.css']
})
export class CheckOvertimeComWithdrawalComponent implements OnInit {

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

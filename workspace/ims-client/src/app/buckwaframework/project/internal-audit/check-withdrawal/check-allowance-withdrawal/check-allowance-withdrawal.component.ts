import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'app-check-allowance-withdrawal',
  templateUrl: './check-allowance-withdrawal.component.html',
  styleUrls: ['./check-allowance-withdrawal.component.css']
})
export class CheckAllowanceWithdrawalComponent implements OnInit {

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

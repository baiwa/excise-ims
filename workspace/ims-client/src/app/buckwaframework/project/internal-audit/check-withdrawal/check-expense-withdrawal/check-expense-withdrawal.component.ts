import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-check-expense-withdrawal',
  templateUrl: './check-expense-withdrawal.component.html',
  styleUrls: ['./check-expense-withdrawal.component.css']
})
export class CheckExpenseWithdrawalComponent implements OnInit {

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}

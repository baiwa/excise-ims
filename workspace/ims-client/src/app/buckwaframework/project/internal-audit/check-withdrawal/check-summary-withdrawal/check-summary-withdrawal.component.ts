import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'check-summary-withdrawal',
  templateUrl: './check-summary-withdrawal.component.html',
  styleUrls: ['./check-summary-withdrawal.component.css']
})
export class CheckSummaryWithdrawalComponent implements OnInit {

  private showData: boolean = false;
  private showCompareData: boolean = false;
  private showSummaryCompareData: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
    this.showCompareData = false;
    this.showSummaryCompareData = false;
  }

  compareData() {
    this.showCompareData = true;
  }

  tabCompareClearData() {
    this.showSummaryCompareData = false;
  }

  tabCompareData() {
    this.showSummaryCompareData = true;
  }

}

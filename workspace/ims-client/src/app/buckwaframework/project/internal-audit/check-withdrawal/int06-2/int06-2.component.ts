import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'int06-2',
  templateUrl: './int06-2.component.html',
  styleUrls: ['./int06-2.component.css']
})
export class Int062Component implements OnInit {

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

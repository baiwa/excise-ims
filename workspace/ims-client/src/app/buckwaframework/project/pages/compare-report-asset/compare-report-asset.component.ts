import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'compare-report-asset',
  templateUrl: './compare-report-asset.component.html',
  styleUrls: ['./compare-report-asset.component.css']
})
export class CompareReportAssetComponent implements OnInit {

  private showData: boolean = false;
  constructor() { }

  ngOnInit() {
  }

  uploadFile() {
    this.showData = true;
  }

  clearFile() {
    this.showData = false;
  }
}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'check-stamp-yearly',
  templateUrl: './check-stamp-yearly.component.html',
  styleUrls: ['./check-stamp-yearly.component.css']
})
export class CheckStampYearlyComponent implements OnInit {

  private zoneList: any[];
  private areaList: any[];

  private showData: boolean = false;

  constructor() { }

  ngOnInit() {
    this.zoneList = [
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 1' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 2' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 3' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 4' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 5' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 6' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 7' }
    ];

    this.areaList = [
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรลำปาง' },
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรสาคร' },
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรปราการ' }
    ];
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }
}

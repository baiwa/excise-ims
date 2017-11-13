import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'check-stamp-branch',
  templateUrl: './check-stamp-branch.component.html',
  styleUrls: ['./check-stamp-branch.component.css']
})
export class CheckStampBranchComponent implements OnInit {

  private zoneList: any[];
  private areaList: any[];
  private branchList: any[];

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

    this.branchList = [
      { 'value': 'สำนักงานสรรพสามิตพื้นที่ลำปางสาขาเมืองลำปาง' },
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรสาครสาขาเมืองสมุทรสาคร' },
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรปราการสาขาเมืองสมุทรปราการ' }
    ];
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }


  editData() {
    $('#modalCheckStampBranch').modal('show');
  }

  closeModal() {
    $('#modalCheckStampBranch').modal('hide');
  }
}

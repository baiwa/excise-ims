import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-int01-4-1',
  templateUrl: './int01-4-1.component.html',
  styleUrls: ['./int01-4-1.component.css']
})
export class Int0141Component implements OnInit {

  private selectZone: any[];
  private selectArea: any[];

  constructor() { }

  ngOnInit() {
    this.selectZone = [
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 1' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 2' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 3' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 4' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 5' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 6' },
      { 'value': 'สำนักงานสรรพสามิตภาคที่ 7' }
    ];

    this.selectArea = [
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรสาคร' },
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรสงคราม' },
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรปราการ' }
    ];
  }

  ngAfterViewInit() {
    //$("#selectZone").dropdown();
    //$("#selectArea").dropdown();
  }
}

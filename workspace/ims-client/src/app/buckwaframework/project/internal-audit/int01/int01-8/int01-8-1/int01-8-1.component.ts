import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-int01-8-1',
  templateUrl: './int01-8-1.component.html',
  styleUrls: ['./int01-8-1.component.css']
})
export class Int0181Component implements OnInit {

  private zoneList: any[];
  private areaList: any[];
  private subAreaList: any[];
  private companyList: any[];

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
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรสาคร' },
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรสงคราม' },
      { 'value': 'สำนักงานสรรพสามิตพื้นที่สมุทรปราการ' }
    ];

    this.subAreaList = [
      { 'value': 'ตรวจสอบรายได้' },
      { 'value': 'ตรวจสอบพัสดุ' },
      { 'value': 'ตรวจเบิกจ่าย' },
      { 'value': 'ตรวจสอบบัญชี' },
      { 'value': 'ตรวจประเมิณความเสี่ยง' },
     
    ];

    this.companyList = [
      { 'value': 'ทั้งหมด' },
      { 'value': 'บ. ฮอนด้า มอเตอร์ ประเทศไทย' },
      { 'value': 'บ. โตโยต้า มอเตอร์ ประเทศไทย' },
      { 'value': 'บ. นิสสัน มอเตอร์ ประเทศไทย' },
    ];
  }

  
}

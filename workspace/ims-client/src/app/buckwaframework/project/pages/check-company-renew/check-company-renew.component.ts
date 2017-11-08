import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
  selector: 'check-company-renew',
  templateUrl: './check-company-renew.component.html',
  styleUrls: ['./check-company-renew.component.css']
})
export class CheckCompanyRenewComponent implements OnInit {

  private zoneList: any[];
  private areaList: any[];
  private subAreaList: any[];
  private companyList: any[];

  private showChart: boolean = false;

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
      { 'value': 'สาขาเมือง 1' },
      { 'value': 'สาขาเมือง 2' },
      { 'value': 'สาขาเมือง 3' },
      { 'value': 'สาขาเมือง 4' }
    ];

    this.companyList = [
      { 'value': 'ทั้งหมด' },
      { 'value': 'บ. ฮอนด้า มอเตอร์ ประเทศไทย' },
      { 'value': 'บ. โตโยต้า มอเตอร์ ประเทศไทย' },
      { 'value': 'บ. นิสสัน มอเตอร์ ประเทศไทย' },
    ];
  }

  ngAfterViewInit() {
    this.initDatatable();
  }

  initDatatable() {
    let tableMock = [
      {
        '1': 'A', '2': 'สร.ป2.VAT', '3': '05/09/60', '4': '05/10/60', '5': '05/11/60', '6': '05/12/60'
      },
      {
        '1': 'A', '2': 'ยส.ป2.ผลิตภัณฑ์', '3': '05/09/60', '4': '', '5': '', '6': '05/12/60'
      },
      {
        '1': 'A', '2': 'ไพ่ ป2. VAT', '3': '05/09/60', '4': '05/10/60', '5': '', '6': ''
      },
      {
        '1': 'B', '2': 'สร.ป2.VAT', '3': '05/09/60', '4': '', '5': '', '6': ''
      },
      {
        '1': 'B', '2': 'ยส.ป2.ผลิตภัณฑ์', '3': '05/09/60', '4': '05/10/60', '5': '05/11/60', '6': ''
      },
      {
        '1': 'B', '2': 'ไพ่ ป2. VAT', '3': '05/09/60', '4': '', '5': '05/11/60', '6': ''
      }
    ];

    let tableId = '#table1';

    let backgroundRowColor = (data, type, row, meta) => {
      if (!data) {
        let table = $(tableId).DataTable();
        let cell = table.cell(meta.row, meta.col).node();
        $(cell).addClass("bg-row-highlight");
      }
      return data;
    };

    $('#table1').DataTable({
      "lengthChange": false,
      "searching": false,
      "ordering": false,
      "pageLength": 10,
      "processing": true,
      "serverSide": false,
      "paging": false,
      "info": false,
      "pagingType": "full_numbers",
      "data": tableMock,
      "columns": [
        {
          "data": "1"
        },
        {
          "data": "2",
          "render": backgroundRowColor
        },
        {
          "data": "3",
          "render": backgroundRowColor,
          "className": "center"
        },
        {
          "data": "4",
          "render": backgroundRowColor,
          "className": "center"
        },
        {
          "data": "5",
          "render": backgroundRowColor,
          "className": "center"
        },
        {
          "data": "6",
          "render": backgroundRowColor,
          "className": "center"
        }
      ]
    });
  }

  onClickShowChart(): void {
    this.showChart = true;
  }

  clearData(): void {
    this.showChart = false;
  }
}

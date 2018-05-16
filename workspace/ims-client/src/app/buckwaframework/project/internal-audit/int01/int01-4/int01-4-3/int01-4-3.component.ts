import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-int01-4-3',
  templateUrl: './int01-4-3.component.html',
  styleUrls: ['./int01-4-3.component.css']
})
export class Int0143Component implements OnInit {

 
  public listDatas: any[] = [];
  constructor() { }

  ngOnInit() {
    this.listDatas = [
      ['4102020103', 'รายได้ภาษีสุรา', '0.00', '0.00', '-659764.50', '203010', 'ภาษีสุรา', '159000.50', '400764.00', '100000.00', '659764.50', '0.00'],
      ['4102020109', 'รายได้ภาษีรถยนต์', '0.00', '0.00', '-25000.00', '250010', 'ภาษีีแบตเตอรี่', '10000.00', '7500.00', '7500.00', '25000.00', '0.00'],
      ['4201020103', 'ร/ดค่าปรับคดี', '0.00', '0.00', '-3262238.00', '804203', 'ค่าปรับเปรียบเทียบคดีสุรา', '3000238.00', '260000.00', '0.00', '3262238.00', '2000.00'],
      ['4201010199', 'ร/ดอนุญาตอื่น', '0.00', '0.00', '-7200.00', '410020', 'ค่าอนุญาติจำหน่ายสุรา ป2.', '6000.00', '200.00', '0.00', '6200.00', '1000.00'],
      ['4202030105', 'ร/ดค่าของเบ็ดเตล็ด', '0.00', '0.00', '-3000.00', '830099', 'รายได้เบ็ดเตล็ดอื่นๆ', '3000.00', '0.00', '0.00', '3000.00', '0.00']
    ];
  }
  ngAfterViewInit() {
    this.initDatatable();
  }

  initDatatable() {

    let tableMock = [
      {
        '1': '4102020103<br/>รายได้ภาษีสุรา', '2': '0.00', '3': '0.00', '4': '-659764.50', '5': '-659764.50',
        '6': '203010', '7': 'ภาษีสุรา', '8': '159000.50', '9': '400764.00', '10': '100000.00', '11': '659764.50', '12': '0.00'
      },
      {
        '1': '4102020109<br/>รายได้ภาษีรถยนต์', '2': '0.00', '3': '0.00', '4': '-25000.00','5': '-25000.00',
        '6': '250010', '7': 'ภาษีีแบตเตอรี่', '8': '10000.00', '9': '7500.00', '10': '7500.00', '11': '25000.00', '12': '0.00'
      },
      {
        '1': '4201020103<br/>ร/ดค่าปรับคดี', '2': '0.00', '3': '0.00', '4': '-3262238.00','5': '-3262238.00',
        '6': '804203', '7': 'ค่าปรับเปรียบเทียบคดีสุรา', '8': '3000238.00', '9': '260000.00', '10': '0.00', '11': '3262238.00', '12': '2000.00'
      },
      {
        '1': '4201010199<br/>ร/ดอนุญาตอื่น', '2': '0.00', '3': '0.00', '4': '-7200.00','5': '-7200.00',
        '6': '410020', '7': 'ค่าอนุญาติจำหน่ายสุรา ป2.', '8': '6000.00', '9': '200.00', '10': '0.00', '11': '6200.00', '12': '1000.00'
      },
      {
        '1': '4202030105<br/>ร/ดค่าของเบ็ดเตล็ด', '2': '0.00', '3': '0.00', '4': '-3000.00','5': '-3000.00',
        '6': '830099', '7': 'รายได้เบ็ดเตล็ดอื่นๆ', '8': '3000.00', '9': '0.00', '10': '0.00', '11': '3000.00', '12': '0.00'
      }
    ];

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
          "data": "1",
          "render": function (data, type, row) {
            return `<span style="float: left;">${data}</span>`;
          }
        },
        {
          "data": "2",
          "render": function (data, type, row) {
            return `<span>${data}</span>`;
          }
        },
        {
          "data": "3",
          "render": function (data, type, row) {
            return `<span style="float: right;">${data}</span>`;
          }
        },
        {
          "data": "4",
          "render": function (data, type, row) {
            return `<span style="float: right;">${data}</span>`;
          }
        },
        {
          "data": "5",
          "render": function (data, type, row) {
            return `<span style="float: right;">${data}</span>`;
          }
        },
        {
          "data": "6",
          "render": function (data, type, row) {
            return `<span style="float: left;">${data}</span>`;
          }
        },
        {
          "data": "7",
          "render": function (data, type, row) {
            return `<span>${data}</span>`;
          }
        },
        {
          "data": "8",
          "render": function (data, type, row) {
            return `<span style="float: right;">${data}</span>`;
          }
        },
        {
          "data": "9",
          "render": function (data, type, row) {
            return `<span style="float: right;">${data}</span>`;
          }
        },
        {
          "data": "10",
          "render": function (data, type, row) {
            return `<span style="float: right;">${data}</span>`;
          }
        },
        {
          "data": "11",
          "render": function (data, type, row) {
            return `<span style="float: right;">${data}</span>`;
          }
        },
        {
          "data": "12",
          "render": function (data, type, row) {
            if (parseFloat(data) == 0) {
              return `<span class="ui green horizontal label right aligned" style="float: right;">${data}</span>`;
            } else {
              return `<span class="ui red horizontal label right aligned" style="float: right;">${data}</span>`;
            }

          }
        },
      ]
    });
  }
}

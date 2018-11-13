import { Injectable } from '@angular/core';
import { resolve, reject } from 'q';
declare var $: any;
@Injectable()
export class Tsl010200Service {

  // ==>params  
  jsonColumn: any = "";
  month: any = [
    'ม.ค.',
    'ก.พ.',
    'มี.ค.',
    'เม.ย.',
    'พ.ค.',
    'มิ.ย.',
    'ก.ค.',
    'ส.ค.',
    'ก.ย.',
    'ต.ค.',
    'พ.ย.',
    'ธ.ค.',
  ]
  data: any = [
    {
      exciseId: "0107544000108-1-013",
      conpanyName: "บริษัท ปตท. จำกัด (มหาชน)",
      address: "555 หมู่ที่1 ซอย- ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร จังหวัดกรุงเทพมหานคร 10900",
      subProduct: "น้ำมัน",
      sector: "สรรพสามิตภาคที่ 1",
      area: "สรรพสามิตพื้นที่ลพบุรี",
      month0: "19014498.75",
      month1: "18289133.85",
      month2: "20482213.7",
      month3: "22445482.8",
      month4: "-",
      month5: "-",
      month6: "-",
      pack0: "-3.97",
      pack1: "10.7",
      pack2: "9.85",
      pack3: "-",
      pack4: "-",
      pack5: "-",
    },
    {
      exciseId: "0107547000711-1-002",
      conpanyName: "บริษัท ไทยออยล์ จำกัด (มหาชน)",
      address: "555/1 อาคารศูนย์เอนเนอร์ยี่คอมเพล็กซ์ ชั้น11 ซอย- ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร จังหวัดกรุงเทพมหานคร 10900",
      subProduct: "น้ำมัน",
      sector: "สรรพสามิตภาคที่ 1",
      area: "สรรพสามิตพื้นที่ลพบุรี",
      month0: "4093170506.62",
      month1: "3439326365.7",
      month2: "4677643829.55",
      month3: "4082285656.72",
      month4: "-",
      month5: "-",
      month6: "-",
      pack0: "-19.01",
      pack1: "26.47",
      pack2: "-14.58",
      pack3: "-",
      pack4: "-",
      pack5: "-",
    }
  ];
  constructor(
  ) { }

  createTableHeader(month): Promise<any> {
    return new Promise((resolve, reject) => {
      let head = '<tr>' +
        '<th class="text-center" rowspan="2" >เลขทะเบียนสรรพสามิต</th>' +
        '<th class="text-center" rowspan="2" >ชื่อผู้ประกอบการ/โรงอุตสาหกรรม</th>' +
        '<th class="text-center" rowspan="2" >ที่อยู่สถานบริการ/โรงอุตสาหกรรม</th>' +
        '<th class="text-center" rowspan="2" >พิกัดสินค้า/บริการ</th>' +
        '<th class="text-center" rowspan="2" >สรรพสามิตภาค</th>' +
        '<th class="text-center" rowspan="2" >สรรพสามิตพื้นที่</th>';

      if (month != 0) {
        head += '<th class="text-center" colspan="' + month + '">สรรพสามิตพื้นที่</th>';
        head += '<th class="text-center" colspan="' + (month - 1) + '">ส่วนต่างการชำระภาษีระหว่างเดือน (เปอร์เซ็น)</th>';
        head += '</tr> <tr>';
      }

      for (let index = 0; index < month; index++) {
        head += '<th>' + this.month[index] + '</th>'
      }

      for (let i = 0; i < month - 1; i++) {
        head += `<th> เดือนที่ ${i + 1} และเดือนที่ ${i + 2}</th>`
      }

      head += '</tr>';
      $("#tableOne").html(head);
      console.log(head);
      resolve(head);
    })
  }
  createTableBody(month: number): Promise<any> {
    return new Promise((resolve, reject) => {
      this.jsonColumn += '[';
      this.jsonColumn += '      {                           ';
      this.jsonColumn += '          "data": "exciseId"      ';
      this.jsonColumn += '      }, {                        ';
      this.jsonColumn += '          "data": "conpanyName"   ';
      this.jsonColumn += '      },                          ';
      this.jsonColumn += '      {                           ';
      this.jsonColumn += '          "data": "address"       ';
      this.jsonColumn += '      },                          ';
      this.jsonColumn += '      {                           ';
      this.jsonColumn += '          "data": "subProduct"    ';
      this.jsonColumn += '      },                          ';
      this.jsonColumn += '      {                           ';
      this.jsonColumn += '          "data": "sector"        ';
      this.jsonColumn += '      },                          ';
      this.jsonColumn += '      {                           ';
      this.jsonColumn += '          "data": "area"          ';
      this.jsonColumn += '      }                           ';

      for (let index = 0; index < month; index++) {
        this.jsonColumn += `,{ "data" : "month${index}"`;
        this.jsonColumn += ' }';
        // if (index != month - 1) this.jsonColumn += ', '
      }
      for (let index = 0; index < month - 1; index++) {
        this.jsonColumn += ',{ "data" : "pack' + index + '" }';
        // if (index != month - 1) this.jsonColumn += ', '
      }
      this.jsonColumn += ']';
      resolve(this.jsonColumn);
    });
  }

  datatable() {
    console.log("jsonColumn : ", this.jsonColumn);
    console.log("data : ", this.data);
    $("#datatable1").DataTableTh({
      "serverSide": false,
      "processing": false,
      "scrollX": true,
      "paging": true,
      "data": this.data,
      // "ajax": {
      //     "url": '/ims-webapp/api/ta/opo046/findAll',
      //     "contentType": "application/json",
      //     "type": "POST",
      //     "data": (d) => {
      //         return JSON.stringify($.extend({}, d, {
      //             //"exciseId": $("#exciseId").val(),
      //             //"searchFlag": this.searchFlag,
      //             //"dataExcel": this.dataExcel
      //         }));

      //     },
      // },
      "columns": JSON.parse(this.jsonColumn),
      "drawCallback": (settings) => {

      }

    });

  }
}

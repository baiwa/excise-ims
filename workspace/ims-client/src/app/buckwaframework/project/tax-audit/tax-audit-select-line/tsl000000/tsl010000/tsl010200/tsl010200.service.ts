import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { reject } from 'q';
declare var $: any;
@Injectable()
export class Tsl010200Service {

  dataList: any[] = [];
  // ==>params  
  table: any;
  table2: any;
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
  dataSelect: any;
  data: any = [
    // {
    //   exciseId: "",
    //   conpanyName: "",
    //   address: "",
    //   subProduct: "",
    //   sector: "",
    //   area: "",
    //   month0: "",
    //   month1: "",
    //   month2: "",
    //   month3: "",
    //   month4: "",
    //   month5: "",
    //   month6: ""
    // },
    {
      exciseId: "0107544000108-1-013",
      conpanyName: "บริษัท ปตท. จำกัด (มหาชน)",
      address: "555 หมู่ที่1 ซอย- ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร จังหวัดกรุงเทพมหานคร 10900",
      subProduct: "น้ำมัน",
      sector: "สรรพสามิตภาคที่ 1",
      area: "สรรพสามิตพื้นที่ลพบุรี",
      month0: "19,014,498.75",
      month1: "18,289,133.85",
      month2: "20,482,213.7",
      month3: "22,445,482.8",
      month4: "-",
      month5: "-",
    },
    // {
    //   exciseId: "0107547000711-1-002",
    //   conpanyName: "บริษัท ปตท. จำกัด (มหาชน)",
    //   address: "555/1 อาคารศูนย์เอนเนอร์ยี่คอมเพล็กซ์ ชั้น11 ซอย- ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร จังหวัดกรุงเทพมหานคร 10900",
    //   subProduct: "น้ำมัน",
    //   sector: "สรรพสามิตภาคที่ 1",
    //   area: "สรรพสามิตพื้นที่ลพบุรี",
    //   month0: "4,093,170,506.62",
    //   month1: "3,439,326,365.7",
    //   month2: "4,677,643,829.55",
    //   month3: "2,082,285,656.72",
    //   month4: "-",
    //   month5: "-",
    //   month6: "-"
    // },
    // {
    //   exciseId: "0107547000711-1-123",
    //   conpanyName: "บริษัท ไทยออยล์ จำกัด (มหาชน)",
    //   address: "555/1 อาคารศูนย์เอนเนอร์ยี่คอมเพล็กซ์ ชั้น11 ซอย- ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร จังหวัดกรุงเทพมหานคร 10900",
    //   subProduct: "น้ำมัน",
    //   sector: "สรรพสามิตภาคที่ 1",
    //   area: "สรรพสามิตพื้นที่ลพบุรี",
    //   month0: "4,093,170,506.62",
    //   month1: "3,439,326,365.7",
    //   month2: "4,677,643,829.55",
    //   month3: "6,082,285,656.72",
    //   month4: "-",
    //   month5: "-",
    //   month6: "-",
    //   pack0: "-19.01",
    //   pack1: "26.47",
    //   pack2: "-14.58",
    //   pack3: "-",
    //   pack4: "-",
    //   pack5: "-",
    // },
    // {
    //   exciseId: "0107544000108-1-013",
    //   conpanyName: "บริษัท ปตท. จำกัด (มหาชน)",
    //   address: "555 หมู่ที่1 ซอย- ถนนวิภาวดีรังสิต แขวงจตุจักร เขตจตุจักร จังหวัดกรุงเทพมหานคร 10900",
    //   subProduct: "น้ำมัน",
    //   sector: "สรรพสามิตภาคที่ 1",
    //   area: "สรรพสามิตพื้นที่หนองบัวลำภู",
    //   month0: "19,014,498.75",
    //   month1: "18,289,133.85",
    //   month2: "20,482,213.7",
    //   month3: "22,445,482.8",
    //   month4: "-",
    //   month5: "-",
    //   month6: "-",
    //   pack0: "-3.97",
    //   pack1: "10.7",
    //   pack2: "9.85",
    //   pack3: "-",
    //   pack4: "-",
    //   pack5: "-",
    // },
  ];
  constructor(
    private ajax: AjaxService
  ) { }

  createTableHeader(form): Promise<any> {
    let dateForm = form.dateFrom;
    let dateTo = form.dateTo;
    dateForm = parseInt(dateForm.substr(0, 2));
    dateTo = parseInt(dateTo.substr(0, 2));
    let month = 0
    month = (dateForm > dateTo ? dateForm - dateTo : dateTo - dateForm) + 1;
    console.log("month : ", month);
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
        //head += '<th class="text-center" colspan="' + (month - 1) + '">ส่วนต่างการชำระภาษีระหว่างเดือน (เปอร์เซ็น)</th>';
        head += '</tr> <tr>';
      }

      for (let i = 0; i < month; i++) {
        head += '<th>' + this.month[i] + '</th>'
      }

      // for (let i = 0; i < month - 1; i++) {
      //   head += `<th> เดือนที่ ${i + 1} และเดือนที่ ${i + 2}</th>`
      // }

      head += '</tr>';
      $("#tableOne").html(head);
      console.log(head);
      resolve(month);
    })
  }
  createTableBody(month: number): Promise<any> {
    return new Promise((resolve, reject) => {
      this.jsonColumn += '     [ {                           ';
      this.jsonColumn += `          "data": "exciseId" `;
      this.jsonColumn += '      }, {                        ';
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

      for (let index = 1; index <= month; index++) {
        this.jsonColumn += `,{ "data" : "month${index}",`;
        this.jsonColumn += '   "className" : "text-right"}';
      }
      // for (let index = 0; index < month - 1; index++) {
      //   this.jsonColumn += ',{ "data" : "pack' + index + '" ,';
      //   this.jsonColumn += '"className" : "text-right" }';
      //   // if (index != month - 1) this.jsonColumn += ', '
      // }
      this.jsonColumn += ']';
      resolve(this.jsonColumn);
    });
  }

  save():Promise<any>{
    let  url = "taxAudit/selectList/saveCondition1";
    return new Promise((resolve,reject)=>{
      this.ajax.post(url,JSON.stringify({dataList : this.dataList}),res=>{
        console.log("Data List  : ",this.dataList);
        resolve(res.json());
      });
    });
  }
  datatable = () => {
    this.table = $("#datatable1").DataTableTh({
      "serverSide": false,
      "processing": false,
      "scrollX": true,
      "paging": true,
      "data": this.data,
      // "ajax": {
      //     "url": '/ims-webapp/api/taxAudit/selectList/findCondition1',
      //     "contentType": "application/json",
      //     "type": "POST",
      //     "data": (d) => {
      //         return JSON.stringify($.extend({}, d, {
      //             "dateFrom": form.dateFrom,
      //             "dateTo": form.dateTo,
      //             "monthNonPay": form.monthNonPay
      //         }));
      //     },
      // },
      "columns": [
        {
          render: function (data, type, full, meta) {
            return '<div class="ui checkbox tableDt"><input name="checkDelId" value="' +
              data +
              '" type="checkbox"><label></label></div>';
          },
          className: "center"
        }, {
          "data": "exciseId"
        },
        {
          "data": "conpanyName"
        },
        {
          "data": "address"
        },
        {
          "data": "subProduct"
        },
        {
          "data": "sector"
        },
        {
          "data": "area"
        },
        {
          "data": "month1",
          "className": "text-right"
        },
        {
          "data": "month2",
          "className": "text-right"
        },
        {
          "data": "month3",
          "className": "text-right"
        },
        {
          "data": "month4",
          "className": "text-right"
        },
        {
          "data": "month5",
          "className": "text-right"
        },
        {
          "data": "month6",
          "className": "text-right"
        },
        {
          "data": "month7",
          "className": "text-right"
        },
        {
          "data": "month8",
          "className": "text-right"
        },
        {
          "data": "month9",
          "className": "text-right"
        },
        {
          "data": "month10",
          "className": "text-right"
        }, {
          "data": "month11",
          "className": "text-right"
        }, {
          "data": "month12",
          "className": "text-right"
        },
      ],//JSON.parse(this.jsonColumn),
      // "fixedColumns": {
      //   leftColumns: 2
      // },
      "drawCallback": (settings) => {

      }, rowCallback: (row, data, index) => {
        $("td > .tableDt", row).bind("change", () => {
          let isDelete = false;
          for (let index = 0; index < this.dataList.length; index++) {
            const element = this.dataList[index];
            if (element == data) {
              isDelete = true;

              this.dataList.splice(index, 1);

            }
          }
          if (!isDelete) {
            this.dataList.push(data);
          }
          console.log(this.dataList);
          this.table2.clear().draw();
          this.table2.rows.add(this.dataList); // Add new data
          this.table2.columns.adjust().draw(); // Redraw the DataTable\
        })

        // if(this.table2 != null){
        //   this.table2.destroy();
        //   this.datatable2();
        // }

      }


    });
    $("#select-all").on("click", function () {
      // Check/uncheck all checkboxes in the table
      var rows = $("#datatable1").DataTable().rows({ search: "applied" }).nodes();
      $('input[type="checkbox"]', rows).prop("checked", this.checked);
    });
  }

  datatable2 = () => {
    this.table2 = $("#datatable2").DataTableTh({
      "serverSide": false,
      "processing": false,
      "scrollX": true,
      "paging": true,
      "data": this.dataList,
      // "ajax": {
      //     "url": '/ims-webapp/api/taxAudit/selectList/findCondition1',
      //     "contentType": "application/json",
      //     "type": "POST",
      //     "data": (d) => {
      //         return JSON.stringify($.extend({}, d, {
      //             "dateFrom": form.dateFrom,
      //             "dateTo": form.dateTo,
      //             "monthNonPay": form.monthNonPay
      //         }));
      //     },
      // },
      "columns": [
        {
          "data": "exciseId"
        },
        {
          "data": "conpanyName"
        },
        {
          "data": "address"
        },
        {
          "data": "subProduct"
        },
        {
          "data": "sector"
        },
        {
          "data": "area"
        },
        {
          "data": "month1",
          "className": "text-right"
        },
        {
          "data": "month2",
          "className": "text-right"
        },
        {
          "data": "month3",
          "className": "text-right"
        },
        {
          "data": "month4",
          "className": "text-right"
        },
        {
          "data": "month5",
          "className": "text-right"
        },
        {
          "data": "month6",
          "className": "text-right"
        },
        {
          "data": "month7",
          "className": "text-right"
        },
        {
          "data": "month8",
          "className": "text-right"
        },
        {
          "data": "month9",
          "className": "text-right"
        },
        {
          "data": "month10",
          "className": "text-right"
        }, {
          "data": "month11",
          "className": "text-right"
        }, {
          "data": "month12",
          "className": "text-right"
        },
      ],//JSON.parse(this.jsonColumn),
      // "fixedColumns": {
      //   leftColumns: 2
      // },
      "drawCallback": (settings) => {

      }, rowCallback: (row, data, index) => {
        $("td > .tableDt", row).bind("change", () => {
          let isDelete = false;
          for (let index = 0; index < this.dataList.length; index++) {
            const element = this.dataList[index];
            if (element == data) {
              isDelete = true;

              this.dataList.splice(index, 1);

            }
          }
          if (!isDelete) {
            this.dataList.push(data);
          }
          console.log(this.dataList);

        })



      }


    });
    $("#select-all").on("click", function () {
      // Check/uncheck all checkboxes in the table
      var rows = $("#datatable1").DataTable().rows({ search: "applied" }).nodes();
      $('input[type="checkbox"]', rows).prop("checked", this.checked);
    });
  }

  dataTaxNonpay(form): Promise<any> {
    let url = "taxAudit/selectList/findCondition1";
    return new Promise((resolve, reject) => {
      this.ajax.post(url, JSON.stringify(form), (res) => {
        this.data = res.json();
        resolve(res.json());
      })
    });

  }

  select = () => {
    var data = $("#datatable1").DataTable().rows().data();
    console.log(data);
    for (let i = 0; i < data.length; i++) {
      if ((<HTMLInputElement>document.getElementById(`chk${i}`)).checked) {
        this.dataSelect.push(data[i]);
      }
    } //end for loops
    console.log("data : ", this.dataSelect);
  }
}



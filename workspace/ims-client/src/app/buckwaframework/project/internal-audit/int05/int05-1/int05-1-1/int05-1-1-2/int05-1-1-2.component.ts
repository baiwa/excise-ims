import { Component, OnInit } from '@angular/core';
import { MessageBarService } from 'app/buckwaframework/common/services';
import { BreadCrumb } from 'models/breadcrumb';
import { Utils } from 'helpers/utils';
declare var $: any;

@Component({
  selector: 'app-int05-1-1-2',
  templateUrl: './int05-1-1-2.component.html',
  styleUrls: ['./int05-1-1-2.component.css']
})
export class Int05112Component implements OnInit {

  table: any;
  data: FormModal[];
  breadcrumb: BreadCrumb[];
  constructor(
    private message: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบพัสดุ", route: "#" },
      { label: "ตรวจสอบแสตมป์", route: "int05/1/1" },
      { label: "สรุปแสตมป์รายปีงบประมาณ", route: "#" },
    ];
    this.data = []
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
  }
  ngAfterViewInit() {
    this.dataTable();
  }

  spilt = (req) => {
    return req.split(".");
  }

  dataTable = () => {
    this.table = $("#dataTable").DataTable({
      "pageLength": 25,
      "serverSide": false,
      "searching": false,
      "ordering": false,
      "processing": true,
      "scrollX": true,
      "scrollCollapse": true,
      "fixedColumns": {
        "leftColumns": 2
      },
      "ajax": {
        "url": '/ims-webapp/api/ia/int05112/findAll',
        "contentType": "application/json",
        "type": "POST",
        "data": (d) => {
          return JSON.stringify($.extend({}, d, {
          }));
        },
      },
      "drawCallback": () => {
        $('.mark-th').closest('tr').addClass('active');
      },
      "columns": [
        {
          "data": "order",
          "render": function (data, type, row, meta) {
            if (data.split(".").length == 1) {
              $(row).addClass('active');
              return '<span class="mark-th">' + data + '</span>';
            }
            return data;
          },
          "className": "ui center aligned"
        },
        { "data": "stampType" },

        { "data": "branchLastYeatNumberOfStamp", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, "className": "ui right aligned" },
        { "data": "branchLastYeatMoneyOfStamp", 
          "render": function (data, type, row, meta) { 
            if (row.order.split(".").length == 1) return ''; 
            return Utils.moneyFormat(data);
           }, 
          "className": "ui right aligned" 
        },

        { "data": "octoberRecieve", "className": "ui right aligned", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },
        { "data": "octoberPay", "className": "ui right aligned", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; },  },

        { "data": "novemberRecieve", "className": "ui right aligned", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; },  },
        { "data": "novemberPay", "className": "ui right aligned", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; },  },

        { "data": "decemberRecieve", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },
        { "data": "decemberPay", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },

        { "data": "januaryRecieve", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },
        { "data": "januaryPay", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },

        { "data": "februaryRecieve", "className": "ui right aligned", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; },  },
        { "data": "februaryPay", "className": "ui right aligned", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; },  },

        { "data": "marchRecieve", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },
        { "data": "marchPay", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },

        { "data": "aprilRecieve", "className": "ui right aligned", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; },  },
        { "data": "aprilPay", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },

        { "data": "mayRecieve", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },
        { "data": "mayPay", "className": "ui right aligned", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; },  },

        { "data": "juneRecieve", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },
        { "data": "junePay", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },

        { "data": "julyRecieve", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },
        { "data": "julyPay", "className": "ui right aligned", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; },  },

        { "data": "augustRecieve", "className": "ui right aligned", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; },  },
        { "data": "augustPay", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },

        { "data": "septemberRecieve", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },
        { "data": "septemberPay", "className": "ui right aligned" , "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, },

        { "data": "summaryYearRecieve", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, "className": "ui right aligned" },
        { "data": "summaryYearMoneyRecieve", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return Utils.moneyFormat(data);  }, "className": "ui right aligned" },
        { "data": "summaryYearPay", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, "className": "ui right aligned" },
        { "data": "summaryYearMoneyPay", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return Utils.moneyFormat(data);  }, "className": "ui right aligned" },

        { "data": "summaryTotalRecieve", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, "className": "ui right aligned" },
        { "data": "summaryTotalMoneyRecieve", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return Utils.moneyFormat(data);  }, "className": "ui right aligned" },
        { "data": "summaryTotalPay", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, "className": "ui right aligned" },
        { "data": "summaryTotalMoneyPay", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return Utils.moneyFormat(data);  }, "className": "ui right aligned" },

        { "data": "branchUpToDateNumberOfStamp", "render": function (data, type, row, meta) { if (row.order.split(".").length == 1) return ''; return data; }, "className": "ui right aligned" },
        { "data": "branchUpToDateMoneyOfStamp",
          "render": function (data, type, row, meta) { 
            if (row.order.split(".").length == 1) return ''; 
            return Utils.moneyFormat(data); 
            },
            "className": "ui right aligned" 
        },


      ]
    });
  }
}
class FormModal {
  workSheetDetailId: string = null;
  exciseDepartment: string = null;
  exciseRegion: string = null;
  exciseDistrict: string = null;
  dateOfPay: string = null;
  status: string = null;
  departmentName: string = null;
  bookNumberWithdrawStamp: string = null;
  dateWithdrawStamp: string = null;
  bookNumberDeliverStamp: string = null;
  dateDeliverStamp: string = null;
  fivePartNumber: string = null;
  fivePartDate: string = null;
  stampCheckDate: string = null;
  stampChecker: string = null;
  stampType: string = null;
  stampBrand: string = null;
  numberOfBook: string = null;
  numberOfStamp: string = null;
  valueOfStampPrinted: string = null;
  sumOfValue: string = null;
  serialNumber: string = null;
  taxStamp: string = null;
  stampCodeStart: string = null;
  stampCodeEnd: string = null;
  note: string = null;
  createdDate: string = null;
  fileName: [any];
  idRandom: number = 0;
  file: File[];
}
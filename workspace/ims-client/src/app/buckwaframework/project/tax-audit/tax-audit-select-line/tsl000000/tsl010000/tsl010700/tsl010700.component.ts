import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { AjaxService } from 'services/ajax.service';
import { Router, ActivatedRoute } from '@angular/router';
import { IaService } from 'services/ia.service';

declare var $: any;

@Component({
  selector: 'app-tsl010700',
  templateUrl: './tsl010700.component.html',
  styleUrls: ['./tsl010700.component.css']
})
export class Tsl010700Component implements OnInit {

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การคัดเลือกราย', route: '#' },
    { label: 'ตารางผลการคัดเลือกรายประจำปี', route: '#' },
    { label: 'ตรวจสอบข้อมูลด้านภาษีสรรพาสามิต', route: '#' },
  ];

  searchFlag: string = "FALSE";
  entrepreneurTable: any;
  getRawTable: any;
  payRawTable: any;
  dataRecord: any;
  dateCalendar: string = "";

  constructor(
    private dataService: IaService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.dataRecord = this.dataService.getData();
    this.dateCalendar = this.route.snapshot.queryParams['dateCalendar'];
    this.searchFlag = this.route.snapshot.queryParams['searchFlag'];
  }

  ngOnInit() { }

  ngAfterViewInit() {
    this.initGetRawDatatable();
    this.initPayRawDatatable();
  }

  initGetRawDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "taxAudit/checkDisplay/search";
    this.getRawTable = $("#tableGetRawMaterial").DataTableTh({
      serverSide: true,
      searching: false,
      processing: true,
      ordering: false,
      scrollX: true,
      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag": this.searchFlag
          }));
        }
      },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "list",
          className: "ui left aligned"
        }, {
          data: "unit",
          className: "ui left aligned"
        }, {
          data: "stock",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "receive",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "receiveTotal",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }
      ],
    });
  };

  initPayRawDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "taxAudit/checkDisplay/search";
    this.payRawTable = $("#tablePayRawMaterial").DataTableTh({
      serverSide: true,
      searching: false,
      processing: true,
      ordering: false,
      scrollX: true,
      ajax: {
        type: "POST",
        url: URL,
        contentType: "application/json",
        data: (d) => {
          return JSON.stringify($.extend({}, d, {
            "searchFlag": this.searchFlag
          }));
        }
      },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "list",
          className: "ui left aligned"
        }, {
          data: "unit",
          className: "ui left aligned"
        }, {
          data: "productInline",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "productOutline",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "corrupt",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }, {
          data: "other",
          className: "ui right aligned",
          render: function (data, type, row) {
            if ($.trim(data) == "") {
              return "-";
            }
            return data;
          }
        }
      ],
    });
  };

  onClickBack() {
    this.router.navigate(["/tax-audit-select-line/tsl0106-00"], {
      queryParams: {}
    });
  }

}

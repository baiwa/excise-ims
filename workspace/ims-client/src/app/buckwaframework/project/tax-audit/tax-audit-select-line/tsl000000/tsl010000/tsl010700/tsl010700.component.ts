import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { AjaxService } from 'services/ajax.service';

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

  entrepreneurTable: any;
  getRawTable: any;
  payRawTable: any;

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.initEntrepreneurDatatable();
    this.initGetRawDatatable();
    this.initPayRawDatatable();
  }

  initEntrepreneurDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "taxAudit/checkDisplay/searchEntrepreneur";
    this.entrepreneurTable = $("#tableEntrepreneur").DataTableTh({
      serverSide: false,
      searching: false,
      processing: false,
      ordering: false,
      scrollX: true,
      // ajax: {
      //   type: "POST",
      //   url: URL,
      //   contentType: "application/json",
      //   data: (d) => {
      //     return JSON.stringify($.extend({}, d, {
      //     }));
      //   }
      // },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "cusName",
          className: "ui center aligned"
        }, {
          data: "checkPointDest",
          className: "ui center aligned"
        }, {
          data: "dateOutDisplay",
          className: "ui center aligned"
        }, {
          data: "dateInDisplay",
          className: "ui center aligned"
        }, {
          data: "id",
          className: "ui center aligned"
        }, {
          data: "id",
          className: "ui center aligned"
        }
      ],
    });
  };

  initGetRawDatatable = () => {
    const URL2 = AjaxService.CONTEXT_PATH + "taxAudit/checkDisplay/searchGetRaw";
    this.getRawTable = $("#tableGetRawMaterial").DataTableTh({
      serverSide: false,
      searching: false,
      processing: false,
      ordering: false,
      scrollX: true,
      // ajax: {
      //   type: "POST",
      //   url: URL,
      //   contentType: "application/json",
      //   data: (d) => {
      //     return JSON.stringify($.extend({}, d, {
      //     }));
      //   }
      // },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "cusName",
          className: "ui center aligned"
        }, {
          data: "checkPointDest",
          className: "ui center aligned"
        }, {
          data: "dateOutDisplay",
          className: "ui center aligned"
        }, {
          data: "dateInDisplay",
          className: "ui center aligned"
        }, {
          data: "id",
          className: "ui center aligned"
        }
      ],
    });
  };

  initPayRawDatatable = () => {
    const URL3 = AjaxService.CONTEXT_PATH + "taxAudit/checkDisplay/searchPayRaw";
    this.payRawTable = $("#tablePayRawMaterial").DataTableTh({
      serverSide: false,
      searching: false,
      processing: true,
      ordering: false,
      scrollX: true,
      // ajax: {
      //   type: "POST",
      //   url: URL,
      //   contentType: "application/json",
      //   data: (d) => {
      //     return JSON.stringify($.extend({}, d, {
      //     }));
      //   }
      // },
      columns: [
        {
          className: "ui center aligned",
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }, {
          data: "cusName",
          className: "ui center aligned"
        }, {
          data: "checkPointDest",
          className: "ui center aligned"
        }, {
          data: "dateOutDisplay",
          className: "ui center aligned"
        }, {
          data: "dateInDisplay",
          className: "ui center aligned"
        }, {
          data: "id",
          className: "ui center aligned"
        }
      ],
    });
  };

}

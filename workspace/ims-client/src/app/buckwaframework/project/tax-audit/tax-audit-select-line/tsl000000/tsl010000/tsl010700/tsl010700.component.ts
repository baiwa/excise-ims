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
    this.dataRecord = dataService.getData();
    this.dateCalendar = this.route.snapshot.queryParams['dateCalendar'];
    console.log(this.dataRecord);
    console.log(this.dateCalendar);
  }

  ngOnInit() { }

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

  onClickBack() {
    this.router.navigate(["/tax-audit-select-line/tsl0106-00"], {
      queryParams: {}
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { AjaxService } from '../../../../common/services/ajax.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ExportCheckingModel } from 'models/exportCheckingModel';

declare var $: any;

@Component({
  selector: 'app-epa01-4',
  templateUrl: './epa01-4.component.html',
  styleUrls: ['./epa01-4.component.css']
})
export class Epa014Component implements OnInit {

  datatable: any;
  $form: any;
  $page: any;
  exciseId: string = "";
  exciseName: string = "";
  searchFlag: string = "FALSE";
  taxStampNo = [""];
  factoryStampNo = [""];
  datas: ExportCheckingModel = new ExportCheckingModel();
  saveDatas: ExportCheckingModel = new ExportCheckingModel();
  exportType: string = "";

  constructor(
    private authService: AuthService,
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.initDatatable();
  }

  initDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "epa/epa014/search";
    this.datatable = $("#dataTable").DataTableTh({
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
            "exciseId": this.exciseId,
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
          data: "typeList2",
          className: "ui center aligned",
        }, {
          data: "productName2",
          className: "ui center aligned",
        }, {
          data: "model2",
          className: "ui center aligned",
        }, {
          data: "size12",
          className: "ui center aligned",
        }, {
          data: "amount2",
          className: "ui center aligned",
        }, {
          data: "price2",
          className: "ui center aligned",
        }, {
          data: "pricePer2",
          className: "ui center aligned",
        }, {
          data: "amountPer2",
          className: "ui center aligned",
        }, {
          data: "tax2",
          className: "ui center aligned",
        }, {
          data: "tax2",
          className: "ui center aligned",
        }, {
          data: "tax2",
          className: "ui center aligned",
          render: function (data, row) {
            return '<button type="button" class="ui mini primary button checking-button"><i class="edit icon"></i>รายงานการตรวจสอบ</button>';
          }
        }
      ]
    });
  }

}

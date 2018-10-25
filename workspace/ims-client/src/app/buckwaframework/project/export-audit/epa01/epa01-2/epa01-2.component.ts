import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { AjaxService } from '../../../../common/services/ajax.service';
import { ActivatedRoute } from '@angular/router';

declare var $: any;

@Component({
  selector: 'app-epa01-2',
  templateUrl: './epa01-2.component.html',
  styleUrls: ['./epa01-2.component.css']
})
export class Epa012Component implements OnInit {

  datatable: any;
  exciseId: string = "";
  searchFlag: string = "FALSE";

  constructor(
    private authService: AuthService,
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('EXP-01200');
    this.exciseId = this.route.snapshot.queryParams["exciseId"];
    console.log(this.exciseId);
  }

  ngAfterViewInit(): void {
    this.initDatatable();
  }

  initDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "epa/epa012/search";
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
            "exciseId": this.exciseId
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
          data: "typeList",
          className: "ui center aligned",
        }, {
          data: "productName",
          className: "ui center aligned",
        }, {
          data: "model",
          className: "ui center aligned",
        }, {
          data: "size",
          className: "ui center aligned",
        }, {
          data: "amount",
          className: "ui center aligned",
        }, {
          data: "price",
          className: "ui center aligned",
        }, {
          data: "pricePerTax",
          className: "ui center aligned",
        }, {
          data: "amountPer",
          className: "ui center aligned",
        }, {
          data: "tax",
          className: "ui center aligned",
        },
      ]
    });
  };

}

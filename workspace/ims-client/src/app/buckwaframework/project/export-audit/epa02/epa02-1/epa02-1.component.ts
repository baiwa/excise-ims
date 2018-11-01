import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { TextDateTH, formatter } from 'helpers/datepicker';
import { AjaxService } from 'services/ajax.service';
import { ActivatedRoute, Router } from '@angular/router';

declare var $: any;

@Component({
  selector: 'app-epa02-1',
  templateUrl: './epa02-1.component.html',
  styleUrls: ['./epa02-1.component.css']
})
export class Epa021Component implements OnInit {

  datatable: any;
  startDate: string = "";
  searchFlag: string = "FALSE";

  constructor(
    private authService: AuthService,
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('EXP-02100');
    this.calenda();
  }

  ngAfterViewInit() {
    this.initDatatable();
  }

  initDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "epa/epa021/search";
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
          data: "startDate",
          className: "ui center aligned",
        }, {
          data: "startDate",
          className: "ui center aligned",
        }, {
          data: "startDate",
          className: "ui center aligned",
        }, {
          data: "startDate",
          className: "ui center aligned",
        }, {
          data: "startDate",
          className: "ui center aligned",
          render: function (data, row) {
            return '<button type="button" class="ui mini primary button checking-button"><i class="edit icon"></i>ตรวจสอบ</button>';
          }
        },
      ]
    });
  }

  calenda = () => {
    let date = new Date();
    $("#date").calendar({
      type: "date",
      text: TextDateTH,
      formatter: formatter('day-month-year'),
      onChange: (date, text) => {
        this.startDate = text;
      }
    });
  }

  onClickSearch() {
    alert("You've clicked search!");
  }

  onClickClear() {
    alert("You've clicked clear!");
  }

}

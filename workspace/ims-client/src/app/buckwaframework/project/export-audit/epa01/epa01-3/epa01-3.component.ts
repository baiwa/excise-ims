import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { AjaxService } from '../../../../common/services/ajax.service';
import { ActivatedRoute, Router } from '@angular/router';

declare var $: any;

@Component({
  selector: 'app-epa01-3',
  templateUrl: './epa01-3.component.html',
  styleUrls: ['./epa01-3.component.css']
})
export class Epa013Component implements OnInit {

  datatable: any;
  exciseId: string = "";
  exciseName: string = "";
  searchFlag: string = "FALSE";

  constructor(
    private authService: AuthService,
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('EXP-01300');
    this.exciseId = this.route.snapshot.queryParams["exciseId"];
    this.searchFlag = this.route.snapshot.queryParams["searchFlag"];
  }

  ngAfterViewInit(): void {
    this.initDatatable();
  }

  initDatatable = () => {
    const URL = AjaxService.CONTEXT_PATH + "epa/epa013/search";
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
          data: "exciseName",
          className: "ui center aligned",
        }, {
          data: "destination",
          className: "ui center aligned",
        }, {
          data: "dateDestination",
          className: "ui center aligned",
        }, {
          data: "dateDestination",
          className: "ui center aligned",
        }, {
          data: "dateDestination",
          className: "ui center aligned",
          render: function (data, row) {
            return '<button type="button" class="ui mini primary button checking-button"><i class="edit icon"></i>ตรวจสอบ</button>';
          }
        }
      ],
      rowCallback: (row, data, index) => {
        $("td > .checking-button", row).bind("click", () => {
          this.router.navigate(["/epa01/4"], {
            queryParams: {
              exciseId: data.exciseId,
              exciseName: data.exciseName,
              searchFlag: "TRUE"
            }
          });
        });
      }
    });

  }

  onClickSearch() {
    this.searchFlag = "TRUE";
    this.datatable.ajax.reload();
  };

  onClickClear() {
    this.exciseId = "";
    this.exciseName = "";
    this.searchFlag = "FALSE";
    this.datatable.ajax.reload();
  };

}

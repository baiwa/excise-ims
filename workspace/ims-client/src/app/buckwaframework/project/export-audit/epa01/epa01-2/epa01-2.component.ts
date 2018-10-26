import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { AjaxService } from '../../../../common/services/ajax.service';
import { ActivatedRoute, Router } from '@angular/router';

declare var $: any;

@Component({
  selector: 'app-epa01-2',
  templateUrl: './epa01-2.component.html',
  styleUrls: ['./epa01-2.component.css']
})
export class Epa012Component implements OnInit {

  datatable: any;
  exciseId: string = "";
  exciseName: string = "";
  searchFlag: string = "FALSE";

  constructor(
    private authService: AuthService,
    private ajaxService: AjaxService,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('EXP-01200');
    this.exciseId = this.route.snapshot.queryParams["exciseId"];
    this.exciseName = this.route.snapshot.queryParams["exciseName"];
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
        }, {
          data: "tax",
          className: "ui center aligned",
          render: function (data, row) {
            return '<button type="button" class="ui mini primary button checking-button"><i class="edit icon"></i>ตรวจสอบ</button>';
          }
        }
      ],
      rowCallback: (row, data, index) => {
        $("td > .checking-button", row).bind("click", () => {
          console.log("[Data]: ", data);
          
          /* Modal Here */
          $('#ModalCheck').modal('show');
        });
      }
    });
  };

  onClickBack () {
    this.router.navigate(["/epa01/1"], {
      queryParams: { 
        exciseId: this.exciseId,
        exciseName: this.exciseName,
        searchFlag: "TRUE"
      }
    });
  };

}

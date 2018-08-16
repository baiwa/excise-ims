import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location, NgIf } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int08-2-3',
  templateUrl: './int08-2-3.component.html',
  styleUrls: ['./int08-2-3.component.css']
})
export class Int0823Component implements OnInit {
  datatable: any;

  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.initDatatable();
  }


  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int082/dataTableWebService";
    console.log(URL);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: false,
      ajax: {
        type: "POST",
        url: URL,
        data: {}
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "riskAssInfDtlName" },
        { data: "jan", render: $.fn.dataTable.render.number(',') },
        { data: "feb", render: $.fn.dataTable.render.number(',') },
        { data: "mar", render: $.fn.dataTable.render.number(',') },
        { data: "april", render: $.fn.dataTable.render.number(',') },
        { data: "may", render: $.fn.dataTable.render.number(',') },
        { data: "jun", render: $.fn.dataTable.render.number(',') },
        { data: "jul", render: $.fn.dataTable.render.number(',') },
        { data: "aug", render: $.fn.dataTable.render.number(',') },
        { data: "september", render: $.fn.dataTable.render.number(',') },
        { data: "oct", render: $.fn.dataTable.render.number(',') },
        { data: "nov", render: $.fn.dataTable.render.number(',') },
        { data: "dec", render: $.fn.dataTable.render.number(',') },
        { data: "total", render: $.fn.dataTable.render.number(',') },
        { data: "rl" },
        { data: "valueTranslation" }

      ],
      columnDefs: [
        { targets: [0, 16], className: "center aligned" },
        { targets: [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }

}

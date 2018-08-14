import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int08-1-5',
  templateUrl: './int08-1-5.component.html',
  styleUrls: ['./int08-1-5.component.css']
})
export class Int0815Component implements OnInit {
  riskHrdPaperName: any;
  buggetYear: any;
  datatable: any;
  id: any;
  constructor(private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute, ) { }

  ngOnInit() {
    this.id = this.route.snapshot.queryParams["id"];
    this.initDatatable();
  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/dataTableWebService1";
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
        { data: "projectBase" },
        { data: "department" },
        { data: "budget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "localBudget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "otherMoney", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "approveBudget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "sumMonth", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "rl" },
        { data: "valueTranslation" }

      ],
      columnDefs: [
        { targets: [0, 2], className: "center aligned" },
        { targets: [3, 4, 5, 6, 7], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }


  saveRiskAssRiskWsDtl(): void {
    console.log(this.id);
    console.log(this.riskHrdPaperName);
    console.log(this.buggetYear);
    console.log(this.datatable.data());
    var url = "ia/int08/saveRiskAssRiskWsDtl";

    this.ajax.post(url, this.datatable.data(), res => {
      console.log(res.json());
      var message = res.json();
      console.log(message.messageType);
      if (message.messageType == 'E') {
        this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
      } else {
        this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
      }
    });
  }

}

import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: 'app-int08-1-4',
  templateUrl: './int08-1-4.component.html',
  styleUrls: ['./int08-1-4.component.css']
})

export class Int0814Component implements OnInit, AfterViewInit {

  riskHdrName: any;
  datatable: any;

  constructor(private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private _location: Location) { }

  ngOnInit() {
    this.riskHdrName = "";
    //this.initDatatable();
  }
  ngAfterViewInit() {
    this.initDatatable();
  }


  addRiskAssRiskWsHdr() {
    console.log(this.riskHdrName);
    const URL = "ia/int08/addRiskAssRiskWsHdr";

    this.ajax.post(URL, { riskHdrName: this.riskHdrName, active: 'Y' }, res => {
      var message = res.json();
      this.messageBarService.successModal(message.messageTh, "สำเร็จ");
      this.riskHdrName = "";
      this.datatable.destroy();
      this.initDatatable();
    }, errRes => {
      var message = errRes.json();
      this.messageBarService.errorModal(message.messageTh);

    });

  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/searchRiskAssRiskWsHdr";
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
        { data: "riskHdrName" },
        { data: "createdBy" },
        { data: "createdDate" },
        { data: "active" },
        {
          data: "riskHdrId",
          render: function () {
            return '<button type="button" class="ui mini button dtl"><i class="pencil icon"></i> รายละเอียด</button>'
              + '<button type="button" class="ui mini button del"><i class="pencil icon"></i> ลบ</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [1, 2, 3, 4, 5], className: "center aligned" }
      ],
      rowCallback: (row, data, index) => {
        $("td > .dtl", row).bind("click", () => {
          console.log("dtl");
          console.log(data.riskHrdId);



        })
        $("td > .del", row).bind("click", () => {
          console.log("del");
          console.log(data.riskHrdId);

          const URL = "ia/int08/deleteRiskAssRiskWsHdr";

          this.ajax.post(URL, { riskHrdId: data.riskHrdId }, res => {
            var message = res.json();
            this.riskHdrName = "";
            this.messageBarService.successModal(message.messageTh, "สำเร็จ");
            this.datatable.destroy();
            this.initDatatable();
          }, errRes => {
            var message = errRes.json();

            this.messageBarService.errorModal(message.messageTh);


          });
        })
          ;
      }
    });
  }



}

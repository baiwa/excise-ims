import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int08-2-2',
  templateUrl: './int08-2-2.component.html',
  styleUrls: ['./int08-2-2.component.css']
})
export class Int0822Component implements OnInit , AfterViewInit{
  
  riskAssInfHdrName: any;
  datatable: any;
  buggetYear: any;
  
  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private _location: Location) 
    { }

  ngOnInit() {
     this.riskAssInfHdrName = "";
  }
  ngAfterViewInit() {
    this.initDatatable();
  }

  


  addRiskAssInfHdr() {

    if( this.riskAssInfHdrName == null || this.riskAssInfHdrName == undefined || this.riskAssInfHdrName=="" ){
      this.messageBarService.errorModal("กรุณากรอกปัจจัยเสี่ยง");
    }else{
      this.riskAssInfHdrName.trim();
      
      console.log(this.riskAssInfHdrName);
      const URL = "ia/int082/addRiskInfHdr";

      this.ajax.post(URL, { riskAssInfHdrName: this.riskAssInfHdrName, buggetYear: this.buggetYear, active: 'Y' }, res => {
        var message = res.json();
        this.messageBarService.successModal(message.messageTh, "สำเร็จ");
        this.riskAssInfHdrName = "";
        this.datatable.destroy();
        this.initDatatable();
      }, errRes => {
        var message = errRes.json();

        this.messageBarService.errorModal(message.messageTh);

      });

    }
  }


  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int082/searchRiskInfHdr";
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
        { data: "riskAssInfHdrName" },
        { data: "budgetYear" },
        { data: "createdDate" },
        { data: "createdBy" },
        { data: "active" },
        {
          data: "riskAssInfHdrId",
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
          console.log(data.riskAssInfHdrId);
          this.router.navigate(["/int08/2/3"], {
            queryParams: { id: data.riskAssInfHdrId }
          });


        })

        $("td > .del", row).bind("click", () => {
          console.log("del");
          console.log(data.riskAssInfHdrId);

          const URL = "ia/int082/deleteRiskInfHdr";

          this.ajax.post(URL, { riskAssInfHdrId: data.riskAssInfHdrId }, res => {
            var message = res.json();
            this.riskAssInfHdrName = "";
            this.messageBarService.successModal(message.messageTh, "สำเร็จ");
            this.datatable.destroy();
            this.initDatatable();
          }, errRes => {
            var message = errRes.json();

            this.messageBarService.errorModal(message.messageTh);
          });
        });

      }
    });
  }

}

import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: "int08-3-2",
  templateUrl: "./int08-3-2.component.html",
  styleUrls: ["./int08-3-2.component.css"]
})
export class Int0832Component implements OnInit {

  riskHdrName: any;
  datatable: any;
  budgetYear: any;
  yearList: any[];
  wsRiskList: any[];

  constructor(private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private messageBarService: MessageBarService
  ) { }

  ngOnInit() {
    this.riskHdrName = "";
    this.budgetYear = "";
    this.wsRiskList = ["ปัจจัยเสี่ยงความถี่การเข้าตรวจสอบ", "ปัจจัยเสี่ยงผลการจัดเก็บรายได้", "ปัจจัยเสี่ยงผลการปราบปราม", "ปัจจัยเสี่ยงผลการปราบปรามด้านค่าปรับคดี", "ปัจจัยเสี่ยงการเงินและบัญชี", "ปัจจัยเสี่ยงระบบการควบคุมภายใน", "ปัจจัยเสี่ยงการส่งเงินเกิน 3 วัน", "ปัจจัยเสี่ยงแบบสอบทานระบบการควบคุมภายใน"];
    //this.initDatatable();
  }
  ngAfterViewInit() {
    this.budgetYear = this.route.snapshot.queryParams["budgetYear"];
    this.initDatatable();
  }


  addRiskAssRiskWsHdr() {
    console.log(this.budgetYear);
    const URL = "ia/int083/addRiskAssExcAreaHdr";

    this.ajax.post(URL, { riskHdrName: this.riskHdrName, budgetYear: this.budgetYear, active: 'Y' }, res => {
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
    const URL = AjaxService.CONTEXT_PATH + "ia/int083/searchRiskAssExcAreaHdr";
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
        data: { budgetYear: this.budgetYear }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "riskHdrName" },
        { data: "budgetYear" },
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
          console.log(data.riskHdrName);
          console.log(this.wsRiskList.indexOf(data.riskHdrName));
          if (this.wsRiskList.indexOf(data.riskHdrName) >= 0) {
            this.router.navigate(["/int08/3/3"], {
              queryParams: { id: data.riskHrdId }
            });
          } else {
            this.router.navigate(["/int08/3/4"], {
              queryParams: { id: data.riskHrdId }
            });
          }

        })
        $("td > .del", row).bind("click", () => {
          console.log("del");
          console.log(data.riskHrdId);

          const URL = "ia/int083/deleteRiskAssExcAreaHdr";

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

  getYearBackCount() {
    console.log(this.budgetYear);
    const URL = "combobox/controller/getYearBackCount";

    this.ajax.post(URL, {}, res => {
      console.log("res.json()");
      this.yearList = res.json();


    });
  }
  cancelFlow() {
    this.messageBarService.comfirm(foo => {
      // let msg = "";
      if (foo) {
        this.router.navigate(["/int08/3/1"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }
    }, "คุณต้องการยกเลิกการทำงานใช่หรือไม่ ? ");
  }

  configAllCondition() {
    this.router.navigate(["/int08/3/5"], {
      queryParams: { budgetYear: this.budgetYear }
    });
  }



}

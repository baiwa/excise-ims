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
    this.wsRiskList = ["ปัจจัยเสี่ยงงบประมาณที่ใช้ดำเนินงานโครงการ", "ปัจจัยเสี่ยงประสิทธิภาพในการดำเนินงานโครงการ"];
    //this.initDatatable();
  }
  ngAfterViewInit() {
    this.budgetYear = this.route.snapshot.queryParams["budgetYear"];
    this.initDatatable();
  }


  addRiskAssRiskWsHdr() {
    console.log(this.budgetYear);
    const URL = "ia/int08/addRiskAssRiskWsHdr";

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
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/searchRiskAssRiskWsHdr";
    console.log(URL);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: true,
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
        {
          render: function (data, type, row, meta) {
            console.log("data :", row.createdDate)
            if (row.createdDate != null && row.createdDate != undefined && row.createdDate != '') {
              var dateTime = new Date(row.createdDate).toLocaleString("th-TH");
              console.log(dateTime);
              return dateTime.split(' ')[0];
            } else {
              return row.createdDate;
            }
          },
          className: "center"
        },
        { data: "createdBy" },
        {
          data: "active",
          render: function (data, type, row, meta) {
            return '<button type="button" class="ui mini button ' + (data == "Y" ? "green" : "orange") + ' chk"><i class="power off icon"></i>' + (data == "Y" ? "เปิด" : "ปิด") + '</button>';
          }
        },
        {
          data: "riskHrdId",
          render: function () {
            return '<button type="button" class="ui mini button primary dtl"><i class="table icon"></i> รายละเอียด</button>'
              + '<button type="button" class="ui mini button del"><i class="trash alternate icon"></i> ลบ</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [0, 2, 3, 4, 5, 6], className: "center aligned" },
        { targets: [1], className: "left aligned" }
      ],
      rowCallback: (row, data, index) => {
        $("td > .dtl", row).bind("click", () => {
          console.log("dtl");
          console.log(data.riskHdrName);
          console.log(this.wsRiskList.indexOf(data.riskHdrName));
          if (this.wsRiskList.indexOf(data.riskHdrName) >= 0) {
            this.router.navigate(["/int08/1/5"], {
              queryParams: { id: data.riskHrdId }
            });
          } else {
            this.router.navigate(["/int08/1/6"], {
              queryParams: { id: data.riskHrdId }
            });
          }

        });
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
        });

        $("td > .chk", row).bind("click", () => {
          console.log("chk");
          console.log(row);
          console.log(data);
          console.log(index);

          const URL = "ia/int08/updateStatusRisk";
          var newActive = data.active == 'N' ? 'Y' : 'N';
          this.ajax.post(URL, { riskHrdId: data.riskHrdId, active: newActive }, res => {
            //console.log(res.json());
            this.datatable.destroy();
            this.initDatatable();
          });
        });

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
        this.router.navigate(["/int08/1/1"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }
    }, "คุณต้องการยกเลิกการทำงานใช่หรือไม่ ? ");
  }

  configAllCondition() {
    this.router.navigate(["/int08/1/7"], {
      queryParams: { budgetYear: this.budgetYear }
    });
  }



}

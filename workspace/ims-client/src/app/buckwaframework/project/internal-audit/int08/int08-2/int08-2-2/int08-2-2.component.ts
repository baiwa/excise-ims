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
export class Int0822Component implements OnInit, AfterViewInit {

  riskAssInfHdrName: any;
  datatable: any;
  budgetYear: any;
  yearList: any[];
  infRiskList: any[];

  active: any;
  sw: boolean;

  riskType: any;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private messageBarService: MessageBarService) { }

  ngOnInit() {
    this.riskAssInfHdrName = "";
    this.budgetYear = "";
    this.infRiskList = ["ปัจจัยเสี่ยงจำนวนครั้งการใช้งานไม่ได้ของระบบ"];

  }
  ngAfterViewInit() {
    this.budgetYear = this.route.snapshot.queryParams["budgetYear"];

    this.initDatatable();
  }




  addRiskAssInfHdr() {

    if (this.riskAssInfHdrName == null || this.riskAssInfHdrName == undefined || this.riskAssInfHdrName == "") {
      this.messageBarService.errorModal("กรุณากรอกปัจจัยเสี่ยง");
    } else {
      this.riskAssInfHdrName.trim();

      console.log(this.riskAssInfHdrName);
      const URL = "ia/int082/addRiskInfHdr";

      this.ajax.post(URL, { riskAssInfHdrName: this.riskAssInfHdrName, budgetYear: this.budgetYear, active: 'Y' }, res => {
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
    // console.log(URL);
    // console.log(this.budgetYear);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      scrollY: true,
      scrollX: true,
      scrollCollapse: true,
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
        { data: "riskAssInfHdrName" },
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
          data: "riskAssInfHdrId",
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
          console.log(data.riskAssInfHdrId);

          if (this.infRiskList.indexOf(data.riskAssInfHdrName) >= 0) {
            this.router.navigate(["/int08/2/3"], {
              queryParams: { id: data.riskAssInfHdrId }
            });
          } else {
            this.router.navigate(["/int08/2/4"], {
              queryParams: { id: data.riskAssInfHdrId }
            });
          }
        });
        $("td > .chk", row).bind("click", () => {
          console.log("chk");
          console.log(row);
          console.log(data);
          console.log(index);

          const URL = "ia/int082/updateStatusRisk";
          var newActive = data.active == 'N' ? 'Y' : 'N';
          this.ajax.post(URL, { riskAssInfHdrId: data.riskAssInfHdrId, active: newActive }, res => {
            //console.log(res.json());
            this.datatable.destroy();
            this.initDatatable();
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
      this.riskType ="0";
      if (foo) {
        this.router.navigate(["/int08/2/1"], {
          queryParams: { budgetYear: this.budgetYear ,riskType: this.riskType }
        }
        );
      }
    }, "คุณต้องการยกเลิกการทำงานใช่หรือไม่ ? ");
  }


  configAllCondition() {
    this.router.navigate(["/int08/2/5"], {
      queryParams: { budgetYear: this.budgetYear }
    });
  }


}

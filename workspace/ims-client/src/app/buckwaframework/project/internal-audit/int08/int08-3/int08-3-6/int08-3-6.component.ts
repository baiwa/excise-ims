import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute } from "@angular/router";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: "int08-3-6",
  templateUrl: "./int08-3-6.component.html",
  styleUrls: ["./int08-3-6.component.css"]
})
export class Int0836Component implements OnInit {
  riskHrdPaperName: any;
  budgetYear: any;
  datatable: any;
  id: any;
  riskAssRiskWsHdr: any;

  userCheck: any;

  isConditionShow: any;
  constructor(private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute) { }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.id = this.route.snapshot.queryParams["id"];
    this.findRiskById();
    this.initDatatable();
  }

  findRiskById() {
    let url = "ia/int083/findRiskById"
    this.ajax.post(url, { riskHrdId: this.id }, res => {

      this.riskAssRiskWsHdr = res.json();
      console.log(this.riskAssRiskWsHdr);
      this.riskHrdPaperName = this.riskAssRiskWsHdr.riskHrdPaperName;
      this.budgetYear = this.riskAssRiskWsHdr.budgetYear;
      this.userCheck = this.riskAssRiskWsHdr.userCheck;
    });
  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int083/dataTableWebService2";
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
        data: { riskHrdId: this.id }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "departmentName" },
        { data: "resultsIncome", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "budgetIncome", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "budgetDiff", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "percenDiff", render: $.fn.dataTable.render.number(',', '.', 2, '') },

        { data: "rl" },
        { data: "valueTranslation" }

      ],
      columnDefs: [
        { targets: [0, 6, 7], className: "center aligned" },
        { targets: [1], className: "left aligned" },
        { targets: [2, 3, 4, 5], className: "right aligned" }

      ], createdRow: function (row, data, dataIndex) {
        console.log("row");
        console.log("data", data.color);
        console.log("dataIndex", dataIndex);
        if (data.color == 'แดง') {
          $(row).find('td:eq(7)').addClass('bg-c-red');
          $(row).find('td:eq(6)').addClass('bg-c-red');
        } else if (data.color == 'เขียว') {
          $(row).find('td:eq(7)').addClass('bg-c-green');
          $(row).find('td:eq(6)').addClass('bg-c-green');
        } else if (data.color == 'เหลือง') {
          $(row).find('td:eq(7)').addClass('bg-c-yellow');
          $(row).find('td:eq(6)').addClass('bg-c-yellow');
        }

      }

    });
  }


  saveRiskAssRiskWsDtl(): void {
    this.riskAssRiskWsHdr.riskHrdPaperName = this.riskHrdPaperName;
    this.riskAssRiskWsHdr.userCheck = this.userCheck;
    console.log(this.datatable.data());
    var msgMessage = "";

    if (this.userCheck == null || this.userCheck == undefined || this.userCheck == "") {
      msgMessage = "กรุณากรอก \"ผู้ตรวจ\" ";
    }

    if (this.riskHrdPaperName == null || this.riskHrdPaperName == undefined || this.riskHrdPaperName == "") {
      msgMessage = "กรุณากรอก \"ชื่อกระดาษทำการ\" ";
    }

    var url = "ia/condition/findConditionByParentId";
    this.ajax.post(url, { parentId: this.id, riskType: 'MAIN', page: 'int08-3-6' }, res => {
      var conditionList = res.json();
      if (conditionList.length == 0) {
        msgMessage = "กรุณากำหนดเงื่อนไข RL";
      }
      if (msgMessage == "") {
        var url = "ia/int083/updateRiskAssExcRecDtl";

        this.ajax.post(url, this.riskAssRiskWsHdr, res => {
          console.log(res.json());
          var message = res.json();
          console.log(message.messageType);
          if (message.messageType == 'E') {
            this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
          } else {
            this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
            this.router.navigate(["/int08/3/2"], {
              queryParams: { budgetYear: this.budgetYear }
            });
          }

        }, errRes => {
          var message = errRes.json();
          console.log(message);
          this.messageBarService.errorModal(message.messageTh);

        });
      } else {
        this.messageBarService.errorModal(msgMessage);
      }

    });


  }

  cancelFlow() {
    this.messageBarService.comfirm(foo => {
      // let msg = "";
      if (foo) {
        this.router.navigate(["/int08/3/2"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }
    }, "คุณต้องการยกเลิกการทำงานใช่หรือไม่ ? ");
  }
  getConditionShow() {
    return this.isConditionShow;
  }
  modalConditionRL() {
    this.isConditionShow = true;
  }

  closeConditionRL(e) {
    this.isConditionShow = false;
  }


}

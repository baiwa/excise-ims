import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int08-1-5',
  templateUrl: './int08-1-5.component.html',
  styleUrls: ['./int08-1-5.component.css']
})
export class Int0815Component implements OnInit {
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
    let url = "ia/int08/findRiskById"
    this.ajax.post(url, { riskHrdId: this.id }, res => {

      this.riskAssRiskWsHdr = res.json();
      console.log(this.riskAssRiskWsHdr);
      this.riskHrdPaperName = this.riskAssRiskWsHdr.riskHrdPaperName;
      this.budgetYear = this.riskAssRiskWsHdr.budgetYear;
      this.userCheck = this.riskAssRiskWsHdr.userCheck;
    });
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
      scrollY:true,
      scrollX: true,
      scrollCollapse: true,
      paging: true,
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
        { data: "projectBase" },
        { data: "departmentName" },
        { data: "budget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "localBudget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "otherMoney", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "approveBudget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "sumMonth", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "rl" },
        { data: "valueTranslation" }

      ],createdRow: function (row, data, dataIndex) {
        console.log("row");
        console.log("data", data.valueTranslation);
        console.log("dataIndex", dataIndex);
        if (data.color == 'แดง') {
          $(row).find('td:eq(8)').addClass('bg-c-red');
          $(row).find('td:eq(9)').addClass('bg-c-red');
        } else if (data.color == 'เขียว') {
          $(row).find('td:eq(8)').addClass('bg-c-green');
          $(row).find('td:eq(9)').addClass('bg-c-green');
        } else if (data.color == 'เหลือง') {
          $(row).find('td:eq(8)').addClass('bg-c-yellow');
          $(row).find('td:eq(9)').addClass('bg-c-yellow');
        }

      },
      columnDefs: [
        { targets: [0, 1,2,8,9], className: "center aligned" },
        { targets: [3, 4, 5, 6,7], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

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

    if (msgMessage == "") {
      var url = "ia/int08/updateRiskAssRiskWsHdr";

      this.ajax.post(url, this.riskAssRiskWsHdr, res => {
        console.log(res.json());
        var message = res.json();
        console.log(message.messageType);
        if (message.messageType == 'E') {
          this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
        } else {
          this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
          this.router.navigate(["/int08/1/4"], {
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

  }

  cancelFlow() {
    this.messageBarService.comfirm(foo => {
      // let msg = "";
      if (foo) {
        this.router.navigate(["/int08/1/4"], {
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

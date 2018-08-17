import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location, NgIf } from "@angular/common";
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
  budgetYear: any;
  datatable: any;
  id: any;
  riskAssRiskWsHdr: any;

  userCheck: any;

  constructor(private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute, ) { }

  ngOnInit() {
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
        { data: "departmentName" },
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
    console.log(this.budgetYear);
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

}

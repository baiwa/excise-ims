import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { AuthService } from "services/auth.service";
import { BreadCrumb } from "models/breadcrumb";
import { RiskAssRiskWsHdr } from "models/RiskAssRiskWsHdr";

declare var jQuery: any;
declare var $: any;
const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
@Component({
  selector: 'app-int08-1-5',
  templateUrl: './int08-1-5.component.html',
  styleUrls: ['./int08-1-5.component.css']
})
export class Int0815Component implements OnInit {

  datatable: any;
  id: any;
  riskAssRiskWsHdr: RiskAssRiskWsHdr;
  isConditionShow: any;
  breadcrumb: BreadCrumb[];

  titleList: any[] = [];

  constructor(private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute,
    private authService: AuthService) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "การประเมินความเสี่ยง ", route: "#" },
      { label: "ประเมินความเสี่ยงโครงการยุทธศาสตร์ของกรมสรรพสามิต", route: "#" },
      { label: "รายละเอียดปัจจัยเสี่ยงงบประมาณที่ใช้ดำเนินงานโครงการ", route: "#" },
    ];

    this.authService.reRenderVersionProgram('INT-08150');

  }

  ngOnInit() {
    this.riskAssRiskWsHdr = new RiskAssRiskWsHdr();
    this.riskAssRiskWsHdr.checkPosition = 'ผู้อำนวยการกลุ่มตรวจสอบภายใน';

    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.ajax.post(URL.DROPDOWN, { type: 'TITLE' }, res => {
      this.titleList = res.json();
    });

    this.id = this.route.snapshot.queryParams["id"];
    this.findRiskById();


  }

  findRiskById() {
    let url = "ia/int08/findRiskById"
    this.ajax.post(url, { riskHrdId: this.id }, res => {

      this.riskAssRiskWsHdr = res.json();
      console.log(this.riskAssRiskWsHdr);
      this.riskAssRiskWsHdr.checkPosition = 'ผู้อำนวยการกลุ่มตรวจสอบภายใน';
      this.initDatatable();
    });
  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/dataTableWebService1";
    console.log(URL);
    this.datatable = $("#dataTable").DataTableTh({
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
        data: { riskHrdId: this.id, budgetYear: Number(this.riskAssRiskWsHdr.budgetYear) - 543 }
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
        { data: "sumMoney", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "rl" },
        { data: "valueTranslation" }

      ], createdRow: function (row, data, dataIndex) {

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
        { targets: [0, 2, 8, 9], className: "center aligned" },
        { targets: [3, 4, 5, 6, 7], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }


  saveRiskAssRiskWsDtl(): void {



    let msgMessage = "";



    if (this.riskAssRiskWsHdr.checkLastName == null || this.riskAssRiskWsHdr.checkLastName == undefined || this.riskAssRiskWsHdr.checkLastName == "") {
      msgMessage = "กรุณากรอก \"นามสกุลผู้ตรวจ\" ";
    }
    if (this.riskAssRiskWsHdr.checkUserName == null || this.riskAssRiskWsHdr.checkUserName == undefined || this.riskAssRiskWsHdr.checkUserName == "") {
      msgMessage = "กรุณากรอก \"ชื่อผู้ตรวจ\" ";
    }
    if (this.riskAssRiskWsHdr.checkUserTitle == null || this.riskAssRiskWsHdr.checkUserTitle == undefined || this.riskAssRiskWsHdr.checkUserTitle == "") {
      msgMessage = "กรุณากรอก \"คำนำหน้าชื่อผู้ตรวจ\" ";
    }
    if (this.riskAssRiskWsHdr.createPosition == null || this.riskAssRiskWsHdr.createPosition == undefined || this.riskAssRiskWsHdr.createPosition == "") {
      msgMessage = "กรุณากรอก \"ตำแหน่งผู้จัดทำ\" ";
    }
    if (this.riskAssRiskWsHdr.createLastName == null || this.riskAssRiskWsHdr.createLastName == undefined || this.riskAssRiskWsHdr.createLastName == "") {
      msgMessage = "กรุณากรอก \"นามสกุลผู้จัดทำ\" ";
    }
    if (this.riskAssRiskWsHdr.createUserName == null || this.riskAssRiskWsHdr.createUserName == undefined || this.riskAssRiskWsHdr.createUserName == "") {
      msgMessage = "กรุณากรอก \"ชื่อผู้จัดทำ\" ";
    }
    if (this.riskAssRiskWsHdr.createUserTitle == null || this.riskAssRiskWsHdr.createUserTitle == undefined || this.riskAssRiskWsHdr.createUserTitle == "") {
      msgMessage = "กรุณากรอก \"คำนำหน้าชื่อผู้จัดทำ\" ";
    }
    if (this.riskAssRiskWsHdr.riskHrdPaperName == null || this.riskAssRiskWsHdr.riskHrdPaperName == undefined || this.riskAssRiskWsHdr.riskHrdPaperName == "") {
      msgMessage = "กรุณากรอก \"ชื่อกระดาษทำการ\" ";
    }

    if (msgMessage == "") {
      var url = "ia/int08/updateRiskAssRiskWsHdr";

      this.ajax.post(url, this.riskAssRiskWsHdr, res => {

        var message = res.json();

        if (message.messageType == 'E') {
          this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
        } else {
          this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
          this.router.navigate(["/int08/1/4"], {
            queryParams: { budgetYear: this.riskAssRiskWsHdr.budgetYear }
          });
        }

      }, errRes => {
        var message = errRes.json();

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
          queryParams: { budgetYear: this.riskAssRiskWsHdr.budgetYear }
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

import { Component, OnInit, AfterViewInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { AuthService } from "services/auth.service";
import { BreadCrumb } from "models/breadcrumb";
import { RiskAssInfHdr } from "models/RiskAssInfHdr";

declare var jQuery: any;
declare var $: any;
const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId"
};
@Component({
  selector: 'app-int08-2-3',
  templateUrl: './int08-2-3.component.html',
  styleUrls: ['./int08-2-3.component.css']
})
export class Int0823Component implements OnInit {
  // BreadCrumb
  breadcrumb: BreadCrumb[];
  id: any;
  riskAssInfHdr: RiskAssInfHdr;
  isConditionShow: any;
  datatable: any;
  titleList: any[];

  constructor(
    private authService: AuthService,
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute
  ) {
    this.riskAssInfHdr = new RiskAssInfHdr();
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "การประเมินความเสี่ยง", route: "#" },
      { label: "ประเมินความเสี่ยงระบบสารสนเทศฯ ของกรมสรรพสามิต", route: "#" },
      { label: "รายละเอียดปัจจัยเสี่ยงจำนวนครั้งการใช้งานไม่ได้ของระบบ", route: "#" },
    ];
  }

  ngOnInit() {
    this.riskAssInfHdr = new RiskAssInfHdr();
    this.riskAssInfHdr.checkPosition = 'ผู้อำนวยการกลุ่มตรวจสอบภายใน';
    this.authService.reRenderVersionProgram('INT-08230');
    this.id = this.route.snapshot.queryParams["id"];
    this.ajax.post(URL.DROPDOWN, { type: 'TITLE' }, res => {
      this.titleList = res.json();

    });
    this.findRiskById();

    this.initDatatable();
  }

  findRiskById() {
    let url = "ia/int082/findRiskById"
    this.ajax.post(url, { riskAssInfHdrId: this.id }, res => {
      this.riskAssInfHdr = res.json();
      this.riskAssInfHdr.checkPosition = 'ผู้อำนวยการกลุ่มตรวจสอบภายใน';

      //console.log(this.riskAssInfHdr);

    });
  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int082/dataTableWebService";
    //console.log(URL);
    this.datatable = $("#dataTable").DataTableTh({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      scrollY: true,
      scrollX: true,
      scrollCollapse: true,
      paging: true,
      ajax: {
        type: "POST",
        url: URL,
        data: { riskAssInfHdrId: this.id }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "infName" },
        { data: "jan", render: $.fn.dataTable.render.number(',') },
        { data: "feb", render: $.fn.dataTable.render.number(',') },
        { data: "mar", render: $.fn.dataTable.render.number(',') },
        { data: "april", render: $.fn.dataTable.render.number(',') },
        { data: "may", render: $.fn.dataTable.render.number(',') },
        { data: "jun", render: $.fn.dataTable.render.number(',') },
        { data: "jul", render: $.fn.dataTable.render.number(',') },
        { data: "aug", render: $.fn.dataTable.render.number(',') },
        { data: "sep", render: $.fn.dataTable.render.number(',') },
        { data: "oct", render: $.fn.dataTable.render.number(',') },
        { data: "nov", render: $.fn.dataTable.render.number(',') },
        { data: "dec", render: $.fn.dataTable.render.number(',') },
        { data: "total", render: $.fn.dataTable.render.number(',') },
        { data: "rl" },
        { data: "valueTranslation" }

      ],
      createdRow: function (row, data, dataIndex) {
        //console.log("row");
        //console.log("data", data.valueTranslation);
        //console.log("dataIndex", dataIndex);
        if (data.color == 'แดง') {
          $(row).find('td:eq(15)').addClass('bg-c-red');
          $(row).find('td:eq(16)').addClass('bg-c-red');
        } else if (data.color == 'เขียว') {
          $(row).find('td:eq(15)').addClass('bg-c-green');
          $(row).find('td:eq(16)').addClass('bg-c-green');
        } else if (data.color == 'เหลือง') {
          $(row).find('td:eq(15)').addClass('bg-c-yellow');
          $(row).find('td:eq(16)').addClass('bg-c-yellow');
        }

      },
      columnDefs: [
        { targets: [0, 15, 16], className: "center aligned" },
        { targets: [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }

  saveRiskAssInfDtl(): void {


    //console.log(this.datatable.data());
    var msgMessage = "";
    if (this.riskAssInfHdr.checkLastName == null || this.riskAssInfHdr.checkLastName == undefined || this.riskAssInfHdr.checkLastName == "") {
      msgMessage = "กรุณากรอก \"นามสกุลผู้ตรวจ\" ";
    }
    if (this.riskAssInfHdr.checkUserName == null || this.riskAssInfHdr.checkUserName == undefined || this.riskAssInfHdr.checkUserName == "") {
      msgMessage = "กรุณากรอก \"ชื่อผู้ตรวจ\" ";
    }
    if (this.riskAssInfHdr.checkUserTitle == null || this.riskAssInfHdr.checkUserTitle == undefined || this.riskAssInfHdr.checkUserTitle == "") {
      msgMessage = "กรุณากรอก \"คำนำหน้าชื่อผู้ตรวจ\" ";
    }
    if (this.riskAssInfHdr.createPosition == null || this.riskAssInfHdr.createPosition == undefined || this.riskAssInfHdr.createPosition == "") {
      msgMessage = "กรุณากรอก \"ตำแหน่งผู้จัดทำ\" ";
    }
    if (this.riskAssInfHdr.createLastName == null || this.riskAssInfHdr.createLastName == undefined || this.riskAssInfHdr.createLastName == "") {
      msgMessage = "กรุณากรอก \"นามสกุลผู้จัดทำ\" ";
    }
    if (this.riskAssInfHdr.createUserName == null || this.riskAssInfHdr.createUserName == undefined || this.riskAssInfHdr.createUserName == "") {
      msgMessage = "กรุณากรอก \"ชื่อผู้จัดทำ\" ";
    }
    if (this.riskAssInfHdr.createUserTitle == null || this.riskAssInfHdr.createUserTitle == undefined || this.riskAssInfHdr.createUserTitle == "") {
      msgMessage = "กรุณากรอก \"คำนำหน้าชื่อผู้จัดทำ\" ";
    }
    if (this.riskAssInfHdr.riskInfPaperName == null || this.riskAssInfHdr.riskInfPaperName == undefined || this.riskAssInfHdr.riskInfPaperName == "") {
      msgMessage = "กรุณากรอก \"ชื่อกระดาษทำการ\" ";
    }


    if (msgMessage == "") {
      var url = "ia/int082/updateRiskAssInfHdr";

      this.ajax.post(url, this.riskAssInfHdr, res => {
        //console.log(res.json());
        var message = res.json();
        //console.log(message.messageType);
        if (message.messageType == 'E') {
          this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
        } else {
          this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
          this.router.navigate(["/int08/2/2"], {
            queryParams: { budgetYear: this.riskAssInfHdr.budgetYear }
          });
        }
      }, errRes => {
        var message = errRes.json();
        //console.log(message);
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
        this.router.navigate(["/int08/2/2"], {
          queryParams: { budgetYear: this.riskAssInfHdr.budgetYear }
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

  ExportWebService() {
    const URL = "ia/int082/exportInfWebService?riskAssInfHdrId=" + this.id;
    //console.log("id", this.id);
    this.ajax.download(URL);
  }

}

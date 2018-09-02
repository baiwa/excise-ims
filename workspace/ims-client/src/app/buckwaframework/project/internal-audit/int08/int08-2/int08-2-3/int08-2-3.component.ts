import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location, NgIf } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Alert } from "../../../../../../../../node_modules/@types/selenium-webdriver";

declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int08-2-3',
  templateUrl: './int08-2-3.component.html',
  styleUrls: ['./int08-2-3.component.css']
})
export class Int0823Component implements OnInit {

  riskInfPaperName: any;
  budgetYear: any;
  
  id: any;
  userCheck: any;
  riskAssInfHdr: any;

  isConditionShow: any;

  datatable: any;

  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.queryParams["id"];
    this.findRiskById();
    this.initDatatable();
  }

  findRiskById() {
    let url = "ia/int082/findRiskById"
    this.ajax.post(url, { riskAssInfHdrId: this.id }, res => {
      this.riskAssInfHdr = res.json();


      console.log(this.riskAssInfHdr);
      this.riskInfPaperName = this.riskAssInfHdr.riskInfPaperName;
      this.budgetYear = this.riskAssInfHdr.budgetYear;
      this.userCheck = this.riskAssInfHdr.userCheck;
    });
  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int082/dataTableWebService";
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
      columnDefs: [
        { targets: [0, 16], className: "center aligned" },
        { targets: [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }

  saveRiskAssInfDtl(): void {

    this.riskAssInfHdr.riskInfPaperName = this.riskInfPaperName;
    this.riskAssInfHdr.userCheck = this.userCheck;
    console.log(this.datatable.data());
    var msgMessage = "";

    if (this.userCheck == null || this.userCheck == undefined || this.userCheck == "") {
      msgMessage = "กรุณากรอก \"ผู้ตรวจ\" ";
    }

    if (this.riskInfPaperName == null || this.riskInfPaperName == undefined || this.riskInfPaperName == "") {
      msgMessage = "กรุณากรอก \"ชื่อกระดาษทำการ\" ";
    }

    if (msgMessage == "") {
      var url = "ia/int082/updateRiskAssInfHdr";

      this.ajax.post(url, this.riskAssInfHdr, res => {
        console.log(res.json());
        var message = res.json();
        console.log(message.messageType);
        if (message.messageType == 'E') {
          this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
        } else {
          this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
          this.router.navigate(["/int08/2/2"], {
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
        this.router.navigate(["/int08/2/2"], {
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

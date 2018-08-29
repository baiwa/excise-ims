import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";

import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
declare var $: any;
@Component({
  selector: "int08-1-1",
  templateUrl: "./int08-1-1.component.html",
  styleUrls: ["./int08-1-1.component.css"]
})
export class Int0811Component implements OnInit {
  showData: boolean = false;
  data: String[];
  budgetYear: any;
  riskList: any[];
  datatable: any;
  isSearch: any = false;
  riskYear: any;
  wsRiskList: any[];
  openForm1: any;
  openForm2: any;
  dataTableF1: any;
  riskAssRiskWsHdr: any;
  condition: any;
  riskHrdId: any;
  dataTableF2: any;
  constructor(private router: Router,
    private ajax: AjaxService,
    private route: ActivatedRoute,
    private messageBarService: MessageBarService,
    private _location: Location) {
    this.data = [
      "ประเมินความเสี่ยงโครงการ - งบประมาณ",
      "ประเมินความเสี่ยงโครงการ - ประสิทธิภาพ",
      "ประเมินความเสี่ยงโครงการ - รวม",
      "ประเมินความเสี่ยงสารสนเทศ - จำนวนครั้งใช้งานไม่ได้",
      "ประเมินความเสี่ยงสารสนเทศ - ร้อยละความเห็น",
      "ประเมินความเสี่ยงภาคพื้นที่ - ความถี่การตรวจ",
      "ประเมินความเสี่ยงภาคพื้นที่ - ผลการจัดเก็บรายได้",
      "ประเมินความเสี่ยงภาคพื้นที่ - ผลการปราบปราม (ค่าปรับ)",
      "ประเมินความเสี่ยงภาคพื้นที่ - ผลการปราบปราม (คดี)",
      "ประเมินความเสี่ยงภาคพื้นที่ - ผลการปราบปราม (รวม)",
      "ประเมินความเสี่ยงภาคพื้นที่ - การเงินและบัญชี",
      "ประเมินความเสี่ยงภาคพื้นที่ - ควบคุมภายใน",
      "ประเมินความเสี่ยงภาคพื้นที่ - รวม"
    ];
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.budgetYear = this.route.snapshot.queryParams["budgetYear"];
    if (this.budgetYear == null || this.budgetYear == undefined) {
      this.budgetYear == 'xxxx';
    }
    $('#year').calendar({
      type: 'year',
      text: TextDateTH,
      formatter: formatter('ป'),
      onChange: (date) => {
        console.log(date.getFullYear())
        this.changeYear(date.getFullYear() + 543);
      }
    });
    this.openForm1 = false;
    this.openForm2 = false;
    this.wsRiskList = ["ปัจจัยเสี่ยงงบประมาณที่ใช้ดำเนินงานโครงการ", "ปัจจัยเสี่ยงประสิทธิภาพในการดำเนินงานโครงการ"];
    this.initDatatable();
  }


  uploadData() {
    //this.showData = true;

  }

  changeYear(year) {
    this.budgetYear = year;
    console.log(this.budgetYear);
    const URL = "combobox/controller/findByBudgetYear";
    this.ajax.post(URL, { budgetYear: this.budgetYear }, res => {

      this.riskList = res.json();
      console.log(this.riskList);


    });
  }


  createBudgetYear() {
    this.budgetYear = $('#budgetYear').val().trim();
    if (this.budgetYear != null && this.budgetYear != undefined && this.budgetYear != '') {
      console.log(this.budgetYear);
      const URL = "ia/int08/createBudgetYear";

      this.ajax.post(URL, { budgetYear: this.budgetYear }, res => {
        // var message = res.json();
        // this.messageBarService.successModal(message.messageTh, "สำเร็จ");

        this.router.navigate(["/int08/1/4"], {
          queryParams: { budgetYear: this.budgetYear }
        });
      }, errRes => {
        var message = errRes.json();
        this.messageBarService.errorModal(message.messageTh);

      });
    } else {
      this.messageBarService.errorModal('กรุณาเลือก ปีงบประมาณ');
    }
  }

  searchDataTable() {
    this.budgetYear = $('#budgetYear').val().trim();
    if (this.budgetYear != null && this.budgetYear != undefined && this.budgetYear != '') {
      this.isSearch = true;
      this.initDatatable();
    } else {
      this.messageBarService.errorModal('กรุณาเลือก ปีงบประมาณ');
    }
  }


  initDatatable(): void {
    console.log(55);
    if (this.datatable != null) {
      this.datatable.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/searchRiskAssRiskWsHdr";
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
          data: "riskHrdId",
          render: function (data, type, full, meta) {
            return (
              '<div class="ui checkbox tableDt"><input name="checkRiskHrdId" value="' +
              data +
              '" type="checkbox"><label></label></div>'
            );
          }
        },
        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "riskHdrName" },
        { data: "riskHrdPaperName" },
        { data: "budgetYear" },

        { data: "createdDate" },
        { data: "createdBy" },
        { data: "active" },
        {
          data: "riskHdrId",
          render: function () {
            return '<button type="button" class="ui mini button dtl"><i class="pencil icon"></i> รายละเอียด</button>'
              + '<button type="button" class="ui mini button export"><i class="pencil icon"></i> Export</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [0, 1, 4, 5, 7, 8], className: "center aligned" }
      ],
      rowCallback: (row, data, index) => {
        $("td > .dtl", row).bind("click", () => {

          if (this.wsRiskList.indexOf(data.riskHdrName) >= 0) {
            this.riskHrdId = data.riskHrdId;
            this.renderForm1(data.riskHrdId);
          } else {
            this.riskHrdId = data.riskHrdId;
            this.renderForm2(data.riskHrdId);
          }


        })
        $("td > .export", row).bind("click", () => {


        });
      }
    });
  }



  renderForm1(riskHrdId) {

    console.log("riskHrdId", riskHrdId);
    const URLHRD = "ia/int08/findRiskById";
    this.ajax.post(URLHRD, { riskHrdId: riskHrdId }, res => {
      this.riskAssRiskWsHdr = res.json();
      this.openForm1 = true;
      this.openForm2 = false;
      console.log("riskAssRiskWsHdr", this.riskAssRiskWsHdr);
      const URL = "ia/condition/findConditionByParentId";
      this.ajax.post(URL, { parentId: riskHrdId, riskType: 'MAIN', page: 'int08-1-5' }, res => {
        this.condition = res.json();
        console.log("condition", this.condition);


        this.initDatatableF1();
      });

    });

  }

  initDatatableF1(): void {
    if (this.dataTableF1 != null) {
      this.dataTableF1.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/dataTableWebService1";
    console.log(URL);
    this.dataTableF1 = $("#dataTableF1").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      ajax: {
        type: "POST",
        url: URL,
        data: { riskHrdId: this.riskHrdId }
      },
      columns: [

        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "projectBase" },
        { data: "budget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "localBudget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "otherMoney", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "approveBudget", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "sumMonth", render: $.fn.dataTable.render.number(',', '.', 2, '') },
        { data: "rl" },
        { data: "valueTranslation" }

      ], createdRow: function (row, data, dataIndex) {
        console.log("row");
        console.log("data", data.valueTranslation);
        console.log("dataIndex", dataIndex);
        if (data.color == 'แดง') {
          $(row).find('td:eq(8)').addClass('red');
          $(row).find('td:eq(7)').addClass('red');
        } else if (data.color == 'เขียว') {
          $(row).find('td:eq(8)').addClass('green');
          $(row).find('td:eq(7)').addClass('green');
        } else if (data.color == 'เหลือง') {
          $(row).find('td:eq(8)').addClass('yellow');
          $(row).find('td:eq(7)').addClass('yellow');
        }

      },
      columnDefs: [
        { targets: [0, 2], className: "center aligned" },
        { targets: [3, 4, 5, 6, 7], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }



  initDatatableF2(): void {
    if (this.dataTableF1 != null) {
      this.dataTableF2.destroy();
    }
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/findRiskAssOtherDtlByHeaderId";
    console.log(URL);
    this.dataTableF2 = $("#dataTableF2").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      ajax: {
        type: "POST",
        url: URL,
        data: { riskHrdId: this.riskHrdId }
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
        { data: "riskCost" },
        { data: "rl" },
        { data: "valueTranslation" }

      ], createdRow: function (row, data, dataIndex) {
        console.log("row");
        console.log("data", data.color);
        console.log("dataIndex", dataIndex);
        if (data.color == 'แดง') {
          $(row).find('td:eq(4)').addClass('red');
          $(row).find('td:eq(5)').addClass('red');
        } else if (data.color == 'เขียว') {
          $(row).find('td:eq(4)').addClass('green');
          $(row).find('td:eq(5)').addClass('green');
        } else if (data.color == 'เหลือง') {
          $(row).find('td:eq(4)').addClass('yellow');
          $(row).find('td:eq(5)').addClass('yellow');
        }

      },
      columnDefs: [
        { targets: [0, 2, 4, 5], className: "center aligned" },
        { targets: [3], className: "right aligned" },
        { targets: [1], className: "left aligned" }
      ]

    });
  }

  renderForm2(riskHrdId) {
    console.log("riskHrdId", riskHrdId);
    const URLHRD = "ia/int08/findRiskById";
    this.ajax.post(URLHRD, { riskHrdId: riskHrdId }, res => {
      this.riskAssRiskWsHdr = res.json();
      this.openForm1 = false;
      this.openForm2 = true;
      console.log("riskAssRiskWsHdr", this.riskAssRiskWsHdr);
      const URL = "ia/condition/findConditionByParentId";
      this.ajax.post(URL, { parentId: riskHrdId, riskType: 'OTHER', page: 'int08-1-6' }, res => {
        this.condition = res.json();
        console.log("condition", this.condition);
        this.initDatatableF2();
      });
    });
  }

  clearData() {
    this.showData = false;
  }

  changebudgetYear = event => {

    console.log(event)

  }

  popupEditData() {
    $("#select1").show();
    $("#select2").show();
    $("#select3").show();
    $("#modalInt0811").modal("show");
  }

  closePopupEdit() {
    $("#select1").hide();
    $("#select2").hide();
    $("#select3").hide();
    $("#modalInt0811").modal("hide");
  }
}

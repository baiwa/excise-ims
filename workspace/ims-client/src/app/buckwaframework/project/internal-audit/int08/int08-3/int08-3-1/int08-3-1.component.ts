import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";

import { TextDateTH, formatter } from '../../../../../common/helper/datepicker';
declare var $: any;
@Component({
  selector: "int08-3-1",
  templateUrl: "./int08-3-1.component.html",
  styleUrls: ["./int08-3-1.component.css"]
})
export class Int0831Component implements OnInit {
  showData: boolean = false;
  data: String[];
  budgetYear: any;
  riskList: any[];
  datatable: any;
  isSearch: any = false;

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
    $('#year').calendar({
      type: 'year',
      text: TextDateTH,
      formatter: formatter('ป'),
      onChange: (date) => {
        console.log(date.getFullYear())
        this.changeYear(date.getFullYear() + 543);
      }
    });
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
      const URL = "ia/int083/createBudgetYear";

      this.ajax.post(URL, { budgetYear: this.budgetYear }, res => {
        // var message = res.json();
        // this.messageBarService.successModal(message.messageTh, "สำเร็จ");

        this.router.navigate(["/int08/3/2"], {
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
        { data: "createdBy" },
        { data: "createdDate" },
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
        { targets: [1, 2, 3, 4, 5], className: "center aligned" }
      ],
      rowCallback: (row, data, index) => {
        $("td > .dtl", row).bind("click", () => {


        })
        $("td > .export", row).bind("click", () => {


        });
      }
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

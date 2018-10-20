import { Component, OnInit } from "@angular/core";
import { TextDateTH, formatter } from "helpers/datepicker";
import { AjaxService } from "services/ajax.service";
import { Router } from "@angular/router";
import { MessageBarService } from "../../../../common/services";
import { BreadCrumb } from "models/breadcrumb";

const URL = {
  INIT_DATATABLE: AjaxService.CONTEXT_PATH + "ia/int054/filterFindIaPcm"
};

declare var $: any;
@Component({
  selector: "app-int05-4-admin",
  templateUrl: "./int05-4-admin.component.html",
  styleUrls: ["./int05-4-admin.component.css"]
})
export class Int054AdminComponent implements OnInit {
  showData: boolean = false;
  budgetYear: string = "";
  supplyChoice: string = "";
  supplyChoiceList: string[];
  budgetTypeList: string[];
  dataTable: any;
  budgetType: any;
  breadcrumb: BreadCrumb[] = [];

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private msg: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบพัสดุ", route: "#" },
      { label: "ตรวจสอบผลการจัดซื้อจัดจ้าง", route: "#" }
    ];

    this.budgetTypeList = [
      "งบบุคลากร",
      "งบดำเนินงาน (โครงการ)",
      "งบดำเนินงาน (ขั้นต่ำ/ประจำ)",
      "งบลงทุน",
      "งบอุดหนุน",
      "งบรายจ่ายอื่น"
    ];

    this.supplyChoiceList = [
      "วิธีตกลงราคา",
      "วิธีสอบราคา",
      "วิธีประกวดราคา",
      "วิธีพิเศษ",
      "วิธีกรณีพิเศษ",
      "วิธีประกวดราคาทางอิเลคทรอนิกส์",
      "การจ้างที่ปรึกษา",
      "การจ้างออกแบบ"
    ];
  }

  uploadFile() {
    $("#showData").show();
    this.DATATABLE();
  }
  clearFile() {
    $("#showData").hide();
    this.dataTable.clear().draw();
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    $("#selectTrading").hide();

    $("#budgetYear")
      .calendar({
        maxDate: new Date(),
        type: "year",
        text: TextDateTH,
        formatter: formatter("year"),
        onChange: (date, text, mode) => {
          this.budgetYear = text;
        }
      })
      .css("width", "100%");
  }
  ngAfterViewInit() {
    $("#export .dropdown").dropdown({
      transition: "drop"
    });
    $("#showData").hide();
  }

  DATATABLE(): void {
    if (this.dataTable != null && this.dataTable != undefined) {
      this.dataTable.destroy();
    }

    const data = {
      budgetYear: this.budgetYear,
      budgetType: this.budgetType,
      supplyChoice: this.supplyChoice
    };

    this.dataTable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      processing: true,
      serverSide: false,
      paging: false,

      ajax: {
        type: "POST",
        url: URL.INIT_DATATABLE,
        data: data
      },
      columns: [
        {
          data: "budgetYear",
          className: "center"
        },
        {
          data: "poNumber",
          className: "right"
        },
        {
          data: "projectCodeEgp",
          className: "right"
        },
        {
          data: "projectName",
          className: "center"
        },
        {
          data: "price",
          render: $.fn.dataTable.render.number(",", ".", 0, ""),
          className: "right"
        },
        {
          render: function(data, type, full, meta) {
            return full.status === "N"
              ? "ยังไม่เริ่มดำเนินงาน"
              : "เสร็จสิ้นการดำเนินงาน";
          },
          className: "center"
        },
        {
          data: "updatedDate",
          className: "right"
        },
        {
          className: "center",
          render: function(data, type, full, meta) {
            return `<button class="ui mini blue button" type="button" id="detail-${
              full.qtnHeaderId
            }" value="${full.qtnHeaderCode +
              "," +
              full.qtnHeaderName}"> <i class="search icon"></i> รายละเอียด</button>`;
          }
        }
      ]
    });
  }
}

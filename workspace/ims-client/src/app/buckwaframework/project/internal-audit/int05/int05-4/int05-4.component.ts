import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Router } from "@angular/router";
import { MessageBarService, AjaxService } from "../../../../common/services";
import { TextDateTH, formatter, Utils } from "../../../../common/helper";
import { BreadCrumb } from "models/breadcrumb";
import { Int054Service } from "./int05-4.service";

const URL = {
  INIT_DATATABLE: AjaxService.CONTEXT_PATH + "ia/int054/filterFindIaPcm",
  DELETE: "ia/int054/delete/"
};

declare var $: any;
@Component({
  selector: "app-int05-4",
  templateUrl: "./int05-4.component.html",
  styleUrls: ["./int05-4.component.css"],
  providers: [Int054Service]
})
export class Int054Component implements OnInit, AfterViewInit {
  breadcrumb: BreadCrumb[] = [];
  showData: boolean = false;
  budgetYear: string = "";
  budgetType: string = "";
  supplyChoice: string = "";
  budgetTypeList: string[];
  supplyChoiceList: string[];
  manageDataExternal: any;
  dataTable: any;
  idSelect: any;
  sectorList: any;
  areaList: any;
  branchList: any;

  constructor(
    private selfService: Int054Service,
    private ajax: AjaxService,
    private router: Router,
    private msg: MessageBarService
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบพัสดุ", route: "#" },
      { label: "บันทึกผลการจัดซื้อจัดจ้าง", route: "#" }
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
      "วิธีเฉพาะเจาะจง",
      "วิธีคัดเลือก",
      "วิธีสอบราคา",
      "วิธี E - Market",
      "วิธี E - Bidding"
    ];
  }

  ngOnInit() {
    $(".ui.checkbox").checkbox();
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.sectorL();
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

  uploadFile = () => {
    $("#showData").show();
    this.DATATABLE();

    // Edit or Read detail
    $("#dataTable tbody").on("click", "button", e => {
      const { id } = e.currentTarget;
      let idSplit = id.split("-");
      this.idSelect = idSplit[1];
      this.dataTable.row($(e).parents("tr")).data();

      if ("edit" == id.split("-")[0]) {
        this.router.navigate(["/int05/4-1"], {
          queryParams: {
            procurementId: this.idSelect,
            status: "UPDATE",
            head: "แก้ไข"
          }
        });
      }
    });
  };

  clearFile = () => {
    this.budgetYear = "";
    $("#showData").hide();
    $("#budgetYear").calendar("refresh");
    $("#supplyChoice").dropdown("restore defaults");
    $("#budgetType").dropdown("restore defaults");
    $(".off").dropdown("restore defaults");
    if (this.dataTable != null || this.dataTable != undefined) {
      this.dataTable.clear().draw();
    }
  };

  sectorL = () => {
    this.selfService.sector().then(res => {
      this.sectorList = res;
    });
  };
  areaL = e => {
    $("#area").dropdown("restore defaults");
    $("#branch").dropdown("restore defaults");
    this.areaList = null;
    this.branchList = null;
    if (Utils.isNotNull(e.target.value)) {
      this.selfService.area(e.target.value).then(res => {
        this.areaList = res;
      });
    }
  };
  branchL = e => {
    this.branchList = null;
    $("#branch").dropdown("restore defaults");
    if (Utils.isNotNull(e.target.value)) {
      this.selfService.branch(e.target.value).then(res => {
        this.branchList = res;
      });
    }
  };

  addData = () => {
    this.router.navigate(["/int05/4-1"], {
      queryParams: {
        status: "SAVE",
        head: "เพิ่ม"
      }
    });
  };

  DATATABLE(): void {
    if (this.dataTable != null && this.dataTable != undefined) {
      this.dataTable.destroy();
    }

    let data = {
      exciseDepartment:
        $("#sector").dropdown("get text") == "กรุณาเลือก"
          ? ""
          : $("#sector").dropdown("get text"),
      exciseDistrict:
        $("#area").dropdown("get text") == "กรุณาเลือก"
          ? ""
          : $("#area").dropdown("get text"),
      exciseRegion:
        $("#branch").dropdown("get text") == "กรุณาเลือก"
          ? ""
          : $("#branch").dropdown("get text"),
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
          render: function(data, type, full, meta) {
            return `<input class="ui checkbox" type="checkbox" name="chk-${
              meta.row
            }" id="chk-${meta.row}">`;
          },
          className: "center"
        },
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
            return `<button class="ui mini blue button" type="button"> <i class="eye icon"></i> รายละเอียด</button>`;
          }
        },
        {
          className: "center",
          render: function(data, type, full, meta) {
            return `<button class="ui mini yellow button" type="button" id="edit-${
              full.procurementId
            }"> <i class="edit icon"></i> แก้ไข</button>`;
          }
        }
      ]
    });
  }

  onDelete = () => {
    if (
      $("#dataTable")
        .DataTable()
        .rows()
        .count() == 0
    ) {
      this.msg.alert("ไม่มีข้อมูล");
      return false;
    }

    if (!$('input[type="checkbox"]').is(":checked")) {
      this.msg.alert("กรุณาเลือกรายการ");
      return false;
    }

    let listId = [];
    let node = $("#dataTable")
      .DataTable()
      .rows()
      .nodes();
    $.each(node, function(index, value) {
      if (
        $(this)
          .find("input[type=checkbox]")
          .is(":checked")
      ) {
        let data = $("#dataTable")
          .DataTable()
          .rows()
          .data()[index];
        listId.push(parseInt(data.procurementId));
      }
    });
    this.msg.comfirm(res => {
      if (res) {
        this.ajax.delete(URL.DELETE + listId.join(), res => {
          const msg = res.json();
          if (msg.messageType == "C") {
            this.msg.successModal(msg.messageTh);
          } else {
            this.msg.errorModal(msg.messageTh);
          }
          $("#dataTable")
            .DataTable()
            .ajax.reload();
        });
      }
    }, "ลบรายการ");
  };

  clickCheckAll = event => {
    if (event.target.checked) {
      var node = $("#dataTable")
        .DataTable()
        .rows()
        .nodes();
      $.each(node, function(index, value) {
        $(this)
          .find("input")
          .prop("checked", true);
      });
    } else {
      var node = $("#dataTable")
        .DataTable()
        .rows()
        .nodes();
      $.each(node, function(index, value) {
        $(this)
          .find("input")
          .prop("checked", false);
      });
    }
  };
}

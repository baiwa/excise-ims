import { Component, OnInit, AfterViewInit } from "@angular/core";
import { Router } from "@angular/router";
import { MessageBarService, AjaxService } from "../../../../common/services";
import { TextDateTH, formatter } from "../../../../common/helper";

const URL = {
  INIT_DATATABLE: AjaxService.CONTEXT_PATH + "ia/int054/filterFindIaPcm",
  DELETE: "ia/int054/delete/"
};

declare var $: any;
@Component({
  selector: "app-int05-4",
  templateUrl: "./int05-4.component.html",
  styleUrls: ["./int05-4.component.css"]
})
export class Int054Component implements OnInit, AfterViewInit {
  showData: boolean = false;
  budgetYear: string = "";
  budgetType: string = "";
  supplyChoice: string = "";
  budgetTypeList: string[];
  supplyChoiceList: string[];
  manageDataExternal: any;
  dataTable: any;

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private msg: MessageBarService
  ) {
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

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    // $("#selectTrading").hide();
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
  };

  clearFile = () => {
    $("#showData").hide();
    this.dataTable.clear().draw();
  };

  addData = () => {
    // $("#modalInt054").modal("show");
    // $("#selectTrading").show();
    this.router.navigate(["/int05/4-1"], {
      queryParams: {
        // qtnHeaderCode: this.sideExternal[0],
        // qtnHeaderName: this.sideExternal[1]
      }
    });
  };

  editData() {
    $("#modalInt054").modal("show");
    // $("#selectTrading").show();
  }

  closeModal() {
    $("#modalInt054").modal("hide");
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
          render: function(data, type, full, meta) {
            return `<input type="checkbox" name="chk-${meta.row}" id="chk-${
              meta.row
            }">`;
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
            return `<button class="ui mini blue button" type="button" id="detail-${
              full.qtnHeaderId
            }" value="${full.qtnHeaderCode +
              "," +
              full.qtnHeaderName}"> <i class="search icon"></i> รายละเอียด</button>`;
          }
        },
        {
          className: "center",
          render: function(data, type, full, meta) {
            return `<button class="ui mini orange button" type="button" id="edit-${
              full.qtnHeaderId
            }" value="${
              full.qtnHeaderName
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
    console.log(listId);
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

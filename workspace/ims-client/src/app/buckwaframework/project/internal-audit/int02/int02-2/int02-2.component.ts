import { Component, OnInit } from "@angular/core";
import { Location } from "@angular/common";
import { AjaxService } from "../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../common/services/message-bar.service";
import { Router, ActivatedRoute, Params } from "@angular/router";

declare var jQuery: any;
declare var $: any;
@Component({
  selector: "app-int02-2",
  templateUrl: "./int02-2.component.html",
  styleUrls: ["./int02-2.component.css"]
})
export class Int022Component implements OnInit {
  departmentNameArr: any;
  departmentName: any;
  departmentNameNew: any;
  datatable: any;
  datas: Condition[];
  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private _location: Location
  ) {
    this.datas = [];
    for(let i=0; i<3; i++) {
      this.datas.push(new Condition());
    }
  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.departmentNameArr = "";
    const URL = "combobox/controller/comboboxHeaderQuestionnaire";

    this.ajax.post(URL, {}, res => {
      console.log(res.json());
      this.departmentNameArr = res.json();
    });
    this.initDatatable();
  }

  addHeaderQuestionnaire() {
    console.log(this.departmentNameNew);
    console.log(this.departmentName);
    var departmentValue = "";
    if (
      this.departmentName != null &&
      this.departmentName != undefined &&
      this.departmentName != ""
    ) {
      departmentValue = this.departmentName;
    }
    if (
      this.departmentNameNew != null &&
      this.departmentNameNew != undefined &&
      this.departmentNameNew != ""
    ) {
      if (departmentValue != "") {
        this.messageBarService.errorModal(
          "ระบบสามารถเพิ่มข้อมูลได้เพียงหนึ่งช่องทาง กรุณาเพิ่มข้อมูลใหม่",
          "แจ้งเตือน"
        );
        return "";
      } else {
        departmentValue = this.departmentNameNew;
      }
    }
    const URL = "ia/int02/addHeaderQuestionnaire";
    this.ajax.post(URL, { qtnReportHdrName: departmentValue }, res => {
      console.log(res.json());
      var message = res.json();
      console.log(message.messageType);
      if (message.messageType == "E") {
        this.messageBarService.errorModal(message.messageTh, "แจ้งเตือน");
      } else {
        this.messageBarService.successModal(
          message.messageTh,
          "บันทึกข้อมูลสำเร็จ"
        );
      }
    });
    this.datatable.destroy();
    this.initDatatable();
  }

  initDatatable(): void {
    var router = this.router;
    console.log("initDatatable");
    const URL =
      AjaxService.CONTEXT_PATH + "ia/int02/queryQtnReportHeaderByCriteria";

    this.datatable = $("#datatable").DataTable({
      lengthChange: false,
      searching: false,
      select: true,
      ordering: true,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: true,
      pagingType: "full_numbers",
      ajax: {
        type: "POST",
        url: URL,
        data: {}
      },
      columns: [
        {
          data: "qtnReportHdrId",
          className: "center"
        },
        {
          data: "qtnReportHdrName",
          className: "center"
        },
        {
          data: "creator",
          className: "center"
        },
        {
          render: function(data, type, full, meta) {
            return `<button class="ui icon yellow mini button" id="edit-${
              full.qtnReportHdrId
            }" value="edit-${
              full.qtnReportHdrId
            }"><i class="edit icon"></i></button>`;
          },
          className: "center"
        }
      ]
    });
    var table = this.datatable;
    $("#datatable tbody").on("click", "button", function() {
      var data = table.row($(this).parents("tr")).data();
      console.log(table.row($(this).parents("tr")).row());
      console.log(data.qtnReportHdrId);
      if ("edit" == this.id.split("-")[0]) {
        router.navigate(["/int02/3"], {
          queryParams: { id: this.id.split("-")[1] }
        });
      } else {
        //delete case
        // this.messageBarService.comfirm(res => {
        //   if (!res) return false;
        //   const deleteURL = `preferences/userManagement/`;
        //   this.ajaxService.delete(
        //     deleteURL,
        //     (success: Response) => {
        //       let body: any = success.json();
        //       this.messageBarService.success("ลบข้อมูลสำเร็จ.");
        //       this.search();
        //     },
        //     (error: Response) => {
        //       let body: any = error.json();
        //       this.messageBarService.error(body.error);
        //     }
        //   );
        // }, "ยืนยันการลบ.");
      }
    });

    $("#datatable tbody tr").css({
      "background-color": "white",
      cursor: "pointer"
    });

    //on click row
    $("#datatable tbody").on("click", "tr", function(e) {
      $("#exciseBtn").prop("disabled", false);
      $("#datatable tbody tr").css({
        "background-color": "white",
        cursor: "pointer"
      });

      $(this).css("background-color", "rgb(197,217,241)");
    });
  }

  deleteData() {}

  linkToDetail(headerId) {
    this.router.navigate(["/int02/3"], {
      queryParams: {
        id: headerId
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/int02/1']);
  }

  popupEditData() {
    $("#modalEditData").modal("show");
  }

  closePopupEdit() {
    $("#modalEditData").modal("hide");
  }

  addRow() {
    this.datas.length < 5 && this.datas.push(new Condition());
  }

  delRow(index) {
    this.datas.splice(index, 1);
  }
}

class Condition {
  [x: string]: any;
  seq: any;
  operator: any;
  value1: any;
  value2: any;
  risk: any;
  score: any;
}
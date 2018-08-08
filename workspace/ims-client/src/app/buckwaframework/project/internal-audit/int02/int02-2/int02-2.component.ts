import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../common/services/message-bar.service";
import { Router } from "@angular/router";

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
  chk: any;
  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService
  ) {
    this.datas = [];
    this.chk = [];
    for (let i = 0; i < 3; i++) {
      this.datas.push(new Condition());
    }
  }

  ngOnInit(): void {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");
    this.departmentNameArr = "";
    const URL = "combobox/controller/comboboxHeaderQuestionnaire";
    this.ajax.post(URL, {}, res => {
      this.departmentNameArr = res.json();
    });
    this.initDatatable();
    // Edited or Added ???
    $("#datatable tbody").on("click", "button", e => {
      const { id } = e.currentTarget;
      this.datatable.row($(e).parents("tr")).data();
      if ("edit" == id.split("-")[0]) {
        this.router.navigate(["/int02/3"], {
          queryParams: { id: id.split("-")[1] }
        });
      } else {
        console.log("Added ???");
      }
    });

    // Checked ???
    $("#datatable tbody").on("click", "input", e => {
      const { id, checked } = e.target;
      if ("chk" == id.split("-")[0] && checked) {
        this.chk.push(id.split("-")[1]);
      } else {
        this.chk.splice(this.chk.findIndex(obj => id.split("-")[1] == obj), 1);
      }
    });
  }

  isNotNull(variables): boolean {
    return variables != null && variables != undefined && variables != "" && variables != 0;
  }

  addHeaderQuestionnaire(event: any): void {
    event.preventDefault();
    console.log(event);
    var departmentValue = "";
    if (this.isNotNull(this.departmentName) || this.isNotNull(this.departmentNameNew)) {
      if (this.isNotNull(this.departmentNameNew)) {
        departmentValue = this.departmentNameNew;
      }
      if (this.isNotNull(this.departmentName)) {
        departmentValue = this.departmentName;
      }
      const URL = "ia/int02/save_qtn_report_header";
      this.ajax.post(URL, { qtnReportHdrName: departmentValue }, res => {
        var message = res.json();
        if (message.messageType == "E") {
          this.messageBarService.errorModal(message.messageTh, "เกิดข้อผิดพลาด");
        } else {
          // Alert
          this.messageBarService.successModal(message.messageTh, "สำเร็จ");
          this.reTable();
          // departmentName
          this.departmentNameNew = "";
          this.departmentName = 0;
        }
      });
    } else {
      alert("ระบบสามารถเพิ่มข้อมูลได้เพียงหนึ่งช่องทาง กรุณาเพิ่มข้อมูลใหม่");
    }
  }

  initDatatable(): void {
    const URL = `${AjaxService.CONTEXT_PATH}ia/int02/queryQtnReportHeaderByCriteria`;
    this.datatable = $("#datatable").DataTable({
      lengthChange: false,
      searching: false,
      select: true,
      ordering: false,
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
          render: (data, type, full, meta) => {
            return `<input type="checkbox" name="chk-${
              full.qtnReportHdrId
              }" id="chk-${
              full.qtnReportHdrId
              }">`;
          },
          className: "center"
        },
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
          render: (data, type, full, meta) => {
            return `<button class="ui icon yellow mini button" id="edit-${full.qtnReportHdrId}" value="edit-${full.qtnReportHdrId}"><i class="edit icon"></i></button>`;
          },
          className: "center"
        }
      ]
    });
  }

  deleteHeaderQuestionnaire(): void {
    const URL = "ia/int02/delete_qtn_report_header";
    this.messageBarService.comfirm(foo => {
      let msg = "";
      if (foo) {
        console.log(`${URL}/${this.chk.toString()}`);
        this.ajax.delete(`${URL}/${this.chk.toString()}`,
          res => {
            console.log("Response", res.json());
            msg = res.json().messageTh;
            this.messageBarService.successModal(msg, "สำเร็จ");
            this.reTable();
          },
          err => {
            console.log("Error" ,err.json());
            //msg = err.json().message.messageTh;
          }
        );
      }
    }, "ต้องลบหรือไม่ ? ");
  }

  reTable = () => {
    this.chk = [];
    $("#chk").prop('checked', false);
    this.datatable.destroy();
    this.initDatatable();
  }

  clickChkAll = event => {
    var node = $('#datatable').DataTable().rows().nodes();
    if (event.target.checked) {
      $.each(node, (index, value) => {
        const id = $(value).find('input')[0].id;
        this.chk.push(id.split("-")[1]);
        $(value).find('input')[0].checked = true;
      });
    } else {
      $.each(node, (index, value) => {
        const id = $(value).find('input')[0].id;
        this.chk.splice(this.chk.findIndex(obj => id.split("-")[1] == obj), 1);
        $(value).find('input')[0].checked = false;
      });
    }
  }

  linkToDetail(headerId): void {
    this.router.navigate(["/int02/3"], {
      queryParams: {
        id: headerId
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/int02/1']);
  }

  popupEditData(): void {
    $("#modalEditData").modal("show");
  }

  closePopupEdit(): void {
    $("#modalEditData").modal("hide");
  }

  addRow(): void {
    this.datas.length < 5 && this.datas.push(new Condition());
  }

  delRow(index): void {
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
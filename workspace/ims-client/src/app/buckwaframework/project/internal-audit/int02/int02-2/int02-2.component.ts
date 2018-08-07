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
  chk: any;
  self: this;
  constructor(
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private _location: Location
  ) {
    this.self = this;
    this.datas = [];
    this.chk = [];
    for (let i = 0; i < 3; i++) {
      this.datas.push(new Condition());
    }
  }

  ngOnInit() {
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
      const { id } = e.target;
      this.datatable.row($(this).parents("tr")).data();
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
      console.log(this.chk);
    });
  }

  isNotNull(variables) {
    return variables != null && variables != undefined && variables != "";
  }

  addHeaderQuestionnaire(event: any) {
    event.preventDefault();
    var departmentValue = "";
    if (this.isNotNull(this.departmentName) || this.isNotNull(this.departmentNameNew)) {
      if (this.isNotNull(this.departmentNameNew)) {
        departmentValue = this.departmentNameNew;
      }
      if (this.isNotNull(this.departmentName)) {
        departmentValue = this.departmentName;
      }
      const URL = "ia/int02/addHeaderQuestionnaire";
      this.ajax.post(URL, { qtnReportHdrName: departmentValue }, res => {
        var message = res.json();
        if (message.messageType == "E") {
          alert(message.messageTh);
        } else {
          alert(message.messageTh);
          this.reTable();
        }
      });
    } else {
      alert("ระบบสามารถเพิ่มข้อมูลได้เพียงหนึ่งช่องทาง กรุณาเพิ่มข้อมูลใหม่");
    }
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
          orderable: false,
          render: function (data, type, full, meta) {
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
          render: function (data, type, full, meta) {
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

    $("#datatable tbody tr").css({
      "background-color": "white",
      cursor: "pointer"
    });

    //on click row
    // $("#datatable tbody").on("click", "tr", function (e) {
    //   $("#exciseBtn").prop("disabled", false);
    //   $("#datatable tbody tr").css({
    //     "background-color": "white",
    //     cursor: "pointer"
    //   });

    //   $(this).css("background-color", "rgb(197,217,241)");
    // });
  }

  deleteHeaderQuestionnaire() {
    const URL = "ia/int02/delHeaderQuestionnaire";
    console.log(`${URL}/${this.chk.toString()}`);
    this.ajax.delete(`${URL}/${this.chk.toString()}`,
      res => {
        console.log("Response", res);
        this.reTable();
      }
    );
  }

  reTable = () => {
    this.chk = [];
    $("#chk").prop('checked', false);
    this.datatable.destroy();
    this.initDatatable();
  }

  clickChkAll = event => {
    if (event.target.checked) {
      var node = $('#datatable').DataTable().rows().nodes();
      $.each(node, (index, value) => {
        const id = $(value).find('input')[0].id;
        this.chk.push(id.split("-")[1]);
        $(value).find('input')[0].checked = true;
      });
    } else {
      var node = $('#datatable').DataTable().rows().nodes();
      $.each(node, (index, value) => {
        const id = $(value).find('input')[0].id;
        this.chk.splice(this.chk.findIndex(obj => id.split("-")[1] == obj), 1);
        $(value).find('input')[0].checked = false;
      });
    }
    console.log(this.chk);
  }

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
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { AjaxService } from "services/ajax.service";
import { Utils } from "helpers/utils";
import { utils } from "protractor";
import { MessageBarService } from "services/message-bar.service";
import { resolve } from "url";
import { reject } from "q";
import { Router } from "@angular/router";

const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId",
  SEARCH: AjaxService.CONTEXT_PATH + "ia/int061105/search",
  COMMENT: "ia/int061105/comment",
  SAVE: "ia/int061105/save",
  GET_NEXTVAL: "ia/int061105/getNextval"
};

declare var $: any;
@Injectable()
export class Int061105Service {
  dataTable: any;
  formData: any;
  idToWithdrawRequest: any;
  withdrawRequests = ["2058", "2059", "2060"];
  status = ["2048", "2055", "2056", "2057"];
  process: number = 0;
  pass: number = 0;
  notPass: number = 0;

  constructor(
    private ajax: AjaxService,
    private msg: MessageBarService,
    private router: Router
  ) {
    this.formData = {
      // status: "",
      withdrawRequest: ""
    };
  }

  dropdown = (type: string, id?: number): Observable<any> => {
    const DATA = { type: type, lovIdMaster: id || null };
    return new Observable<ComboBox[]>(obs => {
      this.ajax
        .post(URL.DROPDOWN, DATA, res => {
          this[type] = res.json();
        })
        .then(() => {
          obs.next(this[type]);
        });
    });
  };

  search(formData: any) {
    this.formData = {
      // status: "",
      withdrawRequest: formData.withdrawRequest
    };
    this.idToWithdrawRequest = formData.withdrawRequest;
    this.Datatable();
  }

  Datatable(): void {
    if (this.dataTable != null || this.dataTable != undefined) {
      this.dataTable.destroy();
    }
    //set default variable
    this.process = 0;
    this.pass = 0;
    this.notPass = 0;

    //render check number is null or empty
    let renderNumber = function(data, type, row, meta) {
      return Utils.isNull($.trim(data))
        ? "-"
        : $.fn.dataTable.render.number(",", ".", 2, "").display(data);
    };

    //render check string is null or empty
    let renderString = function(data, type, row, meta) {
      if (Utils.isNull(data)) {
        data = "-";
      }
      return data;
    };

    this.dataTable = $("#dataTable").DataTableTh({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      ajax: {
        type: "POST",
        url: URL.SEARCH,
        data: this.formData
      },
      columns: [
        {
          data: "createdBy",
          render: renderString
        },
        {
          data: "position",
          render: renderString
        },
        {
          data: "affiliation",
          render: renderString
        },
        { data: "createdDateStr" , className:"center"},
        {
          data: "amount",
          render: renderNumber,
          className:"right"
        },
        {
          data: "status",
          render: function(status) {
            if (status === "2048") {
              status = "รอดำเนินการ";
            } else if (status === "2055") {
              status = "ผ่าน";
            } else if (status === "2056") {
              status = "ไม่ผ่าน";
            } else if (status === "2057") {
              status = "สำเร็จ";
            } else {
              status = "-";
            }
            return status;
          }
        },
        {
          render: function(data, type, full, meta) {
            return (
              // // `<button type="button" class="ui mini button blue" id="dtl-${
              // //   full.id
              // // }"><i class="eye icon"></i>รายละเอียด</button>` 
              // +
              `<button type="button" class="ui mini button green ${
                full.status === "2055" ? "hidden" : ""
              }" id="approve-${
                full.id
              }"><i class="check icon"></i>ผ่าน</button>` +
              `<button type="button" class="ui mini button red ${
                full.status === "2056" ? "hidden" : ""
              }" id="reject-${
                full.id
              }"><i class="close icon"></i>ไม่ผ่าน</button>`
            );
          }
        }
      ],
      columnDefs: [
        {
          targets: [3, 5, 6],
          className: "center"

          // render: function(data, type, row, meta) {
          //   return meta.row + meta.settings._iDisplayStart + 1;
          // }
        },
        { targets: [4], className: "right" }
      ],
      rowCallback: (row, data) => {
        if (data.status === "2048") {
          // status = "รอดำเนินการ";
          this.process += data.amount;
        } else if (data.status === "2055") {
          // status = "ผ่าน";
          this.pass += data.amount;
        } else if (data.status === "2056") {
          // status = "ไม่ผ่าน";
          this.notPass += data.amount;
        }
        // else if (data.status === "2057") {
        //   // status = "สำเร็จ";
        // }
        else {
          status = "-";
        }
      },
      initComplete: (settings, json) => {
        $("#process").val(this.process);
        $("#pass").val(this.pass);
        $("#notPass").val(this.notPass);
      }
    });
  }

  clickTdButton() {
    $("#dataTable tbody").on("click", "button", e => {
      this.dataTable.row($(e).parents("tr")).data();
      const { id } = e.currentTarget;
      console.log("id: ", id);
      let typeId = id.split("-")[0];
      console.log("typeId: ", typeId);
      let idSelect = id.split("-")[1];
      console.log("idSelect: ", idSelect);

      if ("approve" == typeId) {
        this.comment(idSelect, "APPROVE");
      } else if ("reject" == typeId) {
        this.comment(idSelect, "REJECT");
      } else {
        console.log(idSelect);
      }
    });
  }

  //approve or reject
  comment(id: number, comment: string) {
    let count = 0;

    this.withdrawRequests.forEach(topic => {
      if (count == 0) {
        if (topic === this.idToWithdrawRequest) {
          this.ajax.post(
            URL.COMMENT,
            {
              idSelect: id,
              withdrawRequest: this.idToWithdrawRequest,
              comment: comment
            },
            success => {
              this.Datatable();
            }
          ),
            error => {
              this.msg.errorModal("ไม่สามารถอัปเดทข้อมูลได้");
            };
          count++;
        }
      }
    });
  }

  getNextval() {
    return new Promise<any>((resolve, reject) => {
      this.ajax.post(URL.GET_NEXTVAL, null, res => {
        resolve(res.json());
      }),
        error => {
          reject(error);
        };
    });
  }

  save(saveData: any) {
    let DATA = {
      withdrawRequest: this.idToWithdrawRequest,
      disbursementRequest: {
        affiliation: saveData.affiliation,
        amount: $("#pass").val(),
        id: saveData.id,
        billLading: saveData.billLading,
        position: saveData.position,
        createdDateStr: saveData.createdDateStr,
        createdBy: saveData.createdBy,
        requestType: this.idToWithdrawRequest
      }
    };
    this.ajax.post(URL.SAVE, DATA, res => {
      this.Datatable();
      this.msg.successModal(res.json().messageTh);
    }),
      error => {
        this.msg.errorModal(error.json().messageTh);
      };
  }
}

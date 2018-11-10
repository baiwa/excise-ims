import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ComboBox } from "models/combobox";
import { AjaxService } from "services/ajax.service";
import { Utils } from "helpers/utils";
import { utils } from "protractor";
import { MessageBarService } from "services/message-bar.service";

const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId",
  SEARCH: AjaxService.CONTEXT_PATH + "ia/int061105/search",
  APPROVE: "ia/int061105/approve"
};

declare var $: any;
@Injectable()
export class Int061105Service {
  dataTable: any;
  formData: any;
  idToWithdrawRequest: any;

  constructor(private ajax: AjaxService, private msg: MessageBarService) {
    this.formData = {
      status: "",
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
      status: formData.status,
      withdrawRequest: formData.withdrawRequest
    };
    this.idToWithdrawRequest = formData.withdrawRequest;
    this.Datatable();
  }

  Datatable(): void {
    if (this.dataTable != null || this.dataTable != undefined) {
      this.dataTable.destroy();
    }

    //render check number is null or empty
    let renderNumber = function(data, type, row, meta) {
      return Utils.isNull($.trim(data))
        ? "-"
        : $.fn.dataTable.render.number(",", ".", 2, "").display(data);
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
        { data: "createdBy" },
        { data: "position" },
        { data: "affiliation" },
        { data: "createdDateStr" },
        {
          data: "amount",
          render: renderNumber
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
              `<button type="button" class="ui mini button green" id="approve-${
                full.id
              }"><i class="check icon"></i>ผ่าน</button>` +
              `<button type="button" class="ui mini button red" id="reject-${
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
      ]
    });
    this.clickTdButton();
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
        this.approve(idSelect);
      } else {
        this.reject(idSelect);
      }
    });
  }

  approve(id: number) {
    if (this.idToWithdrawRequest === "2058") {
      /**
       * แบบขอเบิกเงินค่าเช่าบ้าน (แบบ 6006)
       */
      this.ajax.post(
        URL.APPROVE,
        {
          idSelect: id,
          withdrawRequest: this.idToWithdrawRequest
        },
        success => {
          this.Datatable();
          console.log(success.json());
        }
      ),
        error => {
          this.msg.errorModal("ไม่สามารถอัปเดทข้อมูลได้");
        };
    } else if (this.idToWithdrawRequest === "2059") {
      /**
       * ใบเบิกเงินสวัสดิการเกี่ยวกับการรักษาพยาบาล (แบบ 7131)
       */
    } else if (this.idToWithdrawRequest === "2060") {
      /**
       * ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร (แบบ 7223)
       */
    } else {
      /**
       * not thing
       */
      this.msg.errorModal("ไม่สามารถอัปเดทข้อมูลได้");
    }
  }
  reject(id: number) {}
}

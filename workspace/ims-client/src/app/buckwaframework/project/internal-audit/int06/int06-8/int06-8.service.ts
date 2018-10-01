import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { Router } from "@angular/router";
import { MessageBarService } from "../../../../common/services";
import { Observable } from "rxjs";

const URL = {
  SAVE_AB: "/ia/int068/saveAB",
  SAVE_PU: "/ia/int068/savePU",
  QUERY_TIME: "/ia/int068/checkRangeTime"
};

declare var $: any;

@Injectable()
export class Int068Service {
  getIdAB: any = null;
  dataTable: any;
  showDataTable: any = [];
  confirm: boolean = false;
  checkEdit: boolean = false;
  index: any;
  editID: any;
  isConfirm: any = false;
  dataList: any;
  constructor(
    private ajax: AjaxService,
    private router: Router,
    private msg: MessageBarService
  ) {
    // TODO
  }

  checkRangeTime = (): Observable<any> => {
    return new Observable<any>(obs => {
      this.ajax
        .post(URL.QUERY_TIME, {}, res => {
          this.dataList = res.json();
        })
        .then(() => obs.next(this.dataList));
    });
  };

  saveAlloclatedBudget = (allocatedBudget: number) => {
    const DATA = { allocatedBudget: allocatedBudget };
    this.ajax.post(URL.SAVE_AB, DATA, res => {
      const data = res.json();
      if (data.msg.messageType === "C") {
        this.getIdAB = data.data.allocatedBudgetId;
        // this.msg.successModal(data.msg.messageTh);
        $("#showForm1").show();
      } else {
        this.msg.errorModal(data.msg.messageTh);
      }
    });
  };

  check(getPU: PU) {
    if (getPU.chkFlag === "ADD" && this.checkEdit == false) {
      $("#showForm2").show();
      getPU.allocatedBudgetId = this.getIdAB;
      this.showDataTable.push(getPU);

      this.DATATABLE();
    } else if (getPU.chkFlag === "SAVE" && this.checkEdit == false) {
      this.ajax.post(URL.SAVE_PU, this.showDataTable, res => {
        const data = res.json();
        if (data.msg.messageType === "C") {
          this.msg.successModal(data.msg.messageTh);
        } else {
          this.msg.errorModal(data.msg.messageTh);
        }
      });
    } else {
      getPU.allocatedBudgetId = this.editID;
      this.showDataTable.splice(this.index, 1, getPU);
      this.DATATABLE();
      this.checkEdit = false;
    }
  }

  DATATABLE(): void {
    if (this.dataTable != null && this.dataTable != undefined) {
      this.dataTable.destroy();
    }
    let isConfirm = this.isConfirm;
    this.dataTable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      processing: true,
      serverSide: false,
      paging: false,
      data: this.showDataTable,

      columns: [
        {
          render: function(data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        {
          data: "publicUtilityType",
          className: "left"
        },
        {
          data: "monthInvoice",
          className: "center"
        },
        {
          data: "invoiceNumber",
          className: "right"
        },
        {
          data: "invoiceDate",
          className: "center"
        },
        {
          data: "withdrawalNumber",
          // render: $.fn.dataTable.render.number(",", ".", 0, ""),
          className: "right"
        },
        {
          data: "withdrawalDate",
          className: "center"
        },
        {
          data: "amount",
          render: $.fn.dataTable.render.number(",", ".", 2, ""),
          className: "right"
        },
        {
          className: "center",
          render: function(data, type, full, meta) {
            return `<button class="ui ${
              isConfirm == true ? "disabled" : ""
            } mini blue button ED" type="button" id="edit-${
              meta.row
            }"> <i class="search icon"></i> แก้ไข</button>
            <button class="ui ${
              isConfirm == true ? "disabled" : ""
            } mini orange button DL" type="button" id="delete-${
              meta.row
            }"> <i class="edit icon"></i> ลบ</button>`;
          }
        }
      ],
      rowCallback: (row, data, index) => {
        $("td > .ED", row).bind("click", () => {
          let edit = this.showDataTable[index];
          $("#publicUtilityType").val(edit.publicUtilityType);
          $("#monthInVoice").val(edit.monthInvoice);
          $("#inVoiceNumber").val(edit.invoiceNumber);
          $("#inVoiceDate").val(edit.invoiceDate);
          $("#withDrawalDate").val(edit.withdrawalDate);
          $("#withDrawalNumber").val(edit.withdrawalNumber);
          $("#amount").val(edit.amount);

          this.checkEdit = true;
          this.index = index;
          this.editID = data.allocatedBudgetId;
        });
        $("td > .DL", row).bind("click", () => {
          this.showDataTable.splice(index, 1);
          this.DATATABLE();
        });
      }
    });
  }
  checkConfirm() {
    this.isConfirm = true;
    this.DATATABLE();
  }

  // checkConfirm = () => {
  //   this.confirm = true;
  //   $("#confirm1").hide();
  //   $("#confirm2").hide();
  // };
}

class PU {
  allocatedBudgetId: any = null;
  publicUtilityType: any = null;
  monthInvoice: any = null;
  invoiceNumber: any = null;
  invoiceDate: any = null;
  withdrawalNumber: any = null;
  withdrawalDate: any = null;
  amount: any = null;
  chkFlag: any = "";
}

import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "services/message-bar.service";
import { promise } from "protractor";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";

const URL = {
  // DATATABLE: AjaxService.CONTEXT_PATH + "/ia/int072/datatable"
  DATATABLE: "/ia/int072/datatable"
};

declare var $: any;

@Injectable()
export class Int072Service {
  fileExcel: File[];
  datatable: any;
  dataReadExcel: any;
  loading: boolean = false;
  loadingTable: boolean = false;
  checkSave: boolean = false;
  verifyAccountHeaderId: any;
  resData: any;
  checkLastRow: any;

  constructor(
    private ajax: AjaxService,
    private msg: MessageBarService,
    private router: Router
  ) {}

  async onInitDataTable(id: any): Promise<any> {
    let promise = await new Promise((resolve, reject) => {
      this.verifyAccountHeaderId = id;
      this.initDatatable();
      setTimeout(() => {
        resolve(this.checkLastRow);
      }, 1000);
    });
    return promise;
  }

  initDatatable(): void {
    if (this.datatable != null || this.datatable != undefined) {
      this.datatable.destroy();
    }

    this.resData = [];
    let DATA = { verifyAccountHeaderId: this.verifyAccountHeaderId };
    this.ajax.post(URL.DATATABLE, DATA, async res => {
      const datatable = await res.json();
      this.resData = datatable.data;

      this.checkLastRow = {
        ledgerAccountNumber: "",
        ledgerAccountName: "ผลรวม",
        bringForward: 0.0,
        debit: 0,
        credit: 0,
        carryForward: 0.0,
        loadingTable: false
      };
      //count total result
      await this.resData.forEach(obj => {
        this.checkLastRow.debit += obj.debit;
        this.checkLastRow.credit += obj.credit;
      });

      this.datatable = $("#dataTable").DataTable({
        lengthChange: false,
        searching: false,
        ordering: false,
        pageLength: 10,
        processing: true,
        serverSide: false,
        paging: false,
        data: this.resData,
        columns: [
          { data: "ledgerAccountNumber" },
          { data: "ledgerAccountName" },
          { data: "bringForward" },
          { data: "debit" },
          { data: "credit" },
          { data: "carryForward" }
        ],
        columnDefs: [
          { targets: [0], className: "center aligned" },
          { targets: [2, 3, 4, 5], className: "right aligned" },
          { targets: [1], className: "left aligned" },
          {
            targets: [2, 3, 4, 5],
            render: $.fn.dataTable.render.number(",", ".", 2, "")
          }
        ],
        rowCallback: (row, data, index) => {
          if (data.ledgerAccountName === "ผลรวม") {
            $(row).css("background-color", "#cce2ff");
          }

          //check condition
          let ledgerAccountNumber = data.ledgerAccountNumber;
          let checkId = data.ledgerAccountNumber.charAt(0);
          let debit = data.debit;
          let credit = data.credit;
          let carryForward = data.carryForward; //ยกยอดไป

          //check group 1
          //check number(+, -)
          if (checkId == 1 || checkId == 5) {
            //debit must be number +
            if (debit < 0) {
              $(row).addClass("bg-c-red");
            }
          }
          if (checkId == 2 || checkId == 3 || checkId == 4) {
            //credit must be number -
            if (credit > 0) {
              $(row).addClass("bg-c-red");
            }
          }

          //check group 2
          if (
            ledgerAccountNumber == 1101010110 ||
            ledgerAccountNumber == 1101010112 ||
            ledgerAccountNumber == 1101010113 ||
            ledgerAccountNumber == 2101020106 ||
            ledgerAccountNumber == 2102040103 ||
            ledgerAccountNumber == 2102040104 ||
            ledgerAccountNumber == 5301010101 ||
            ledgerAccountNumber == 5301010103 ||
            ledgerAccountNumber == 5210010111 ||
            ledgerAccountNumber == 5210010112 ||
            ledgerAccountNumber == 5210010114
          ) {
            //carryForward == 0 only
            if (carryForward != 0) {
              // $(row).addClass("negative");
              $(row).addClass("bg-c-red");
            }
          }

          //check group 3
          if (
            ledgerAccountNumber == 1205010103 ||
            ledgerAccountNumber == 1205020103 ||
            ledgerAccountNumber == 1205040103
          ) {
            //carryForward must be number -
            if (carryForward > 0) {
              $(row).addClass("bg-c-red");
            }
          }

          if (
            ledgerAccountNumber == 4104010101 ||
            ledgerAccountNumber == 4104010104
          ) {
            //debit must be number +
            if (debit < 0) {
              $(row).addClass("bg-c-red");
            }
          }

          if (
            ledgerAccountNumber == 3101010101 ||
            ledgerAccountNumber == 3102010101
          ) {
            //debit must be number + or -
            $(row).addClass("");
          }
        }
      });
    });
  }
}

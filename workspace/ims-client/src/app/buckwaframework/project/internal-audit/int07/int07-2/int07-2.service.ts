import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "services/message-bar.service";
import { promise } from "protractor";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";

const URL = {
  DATATABLE: AjaxService.CONTEXT_PATH + "/ia/int072/datatable"
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

  constructor(
    private ajax: AjaxService,
    private msg: MessageBarService,
    private router: Router
  ) {
    // TODO
  }

  async onInitDataTable(id: any, loading: Function) {
    this.verifyAccountHeaderId = id;
    await this.initDatatable();
    await setTimeout(() => {
      this.loadingTable = false;
      loading(this.loadingTable);
    }, 1000);
  }

  initDatatable(): void {
    if (this.datatable != null || this.datatable != undefined) {
      this.datatable.destroy();
    }

    let DATA = { verifyAccountHeaderId: this.verifyAccountHeaderId };
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: false,
      ajax: {
        type: "POST",
        url: URL.DATATABLE,
        data: DATA
      },
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
        //check condition
        // {
        //   targets: [0],
        //   render: $.fn.dataTable.render.number(",", ".", 2, "")
        // }
      ],
      rowCallback: (row, data, index) => {
        // console.log("data: ", data);
        // console.log("row: ", row);
        // console.log("index: ", index);
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
          ledgerAccountNumber == 2102040104
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
      },
      footerCallback: function(row, data, start, end, display) {
        // var table = $("#dataTable").DataTable();
        // var column = table.column();
        // console.log($("#dataTable").get(0));

        var table = $("#dataTable").DataTable();

        table.columns(".sum").every(function() {
          var sum = this.data().reduce(function(a, b) {
            return a + b;
          });
          console.log("sum: ", sum);
          alert("asdmioasudhi");

          $(this.footer()).html("Sum: " + sum);
        });
      }
    });
  }
}

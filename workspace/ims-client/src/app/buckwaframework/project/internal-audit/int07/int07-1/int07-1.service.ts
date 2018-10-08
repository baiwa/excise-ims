import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "services/message-bar.service";
import { promise } from "protractor";
import { Observable } from "rxjs/Observable";
import { Router } from "@angular/router";

const URL = {
  UPLOAD_EXCEL: "/ia/int071/upload",
  SAVE: "/ia/int071/save"
};

declare var $: any;

@Injectable()
export class Int071Service {
  fileExcel: File[];
  datatable: any;
  dataReadExcel: any;
  loading: boolean = false;
  loadingTable: boolean = false;
  checkSave: boolean = false;

  constructor(
    private ajax: AjaxService,
    private msg: MessageBarService,
    private router: Router
  ) {
    // TODO
  }
  onChangeUpload = (event: any, loadingTable: Function) => {
    if (event.target.files && event.target.files.length > 0) {
      let reader = new FileReader();

      reader.onload = (e: any) => {
        const f = {
          name: event.target.files[0].name,
          type: event.target.files[0].type,
          value: e.target.result
        };
        this.fileExcel = [f];
        console.log(this.fileExcel);
      };
      reader.readAsDataURL(event.target.files[0]);
    }
    setTimeout(() => {
      this.loading = false;
      loadingTable(this.loading);
    }, 1000);
  };

  onUpload = (event: any, loadingTable: Function) => {
    event.preventDefault();
    $("#dataTable").show();

    console.log("UPLOAD Excel!!!!!!");
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);

    this.ajax.upload(URL.UPLOAD_EXCEL, formBody, res => {
      this.dataReadExcel = res.json().data;
      this.loadingTable = false;

      setTimeout(() => {
        loadingTable(this.loadingTable);
        this.initDatatable();
      }, 700);
    });
  };

  async saveData(loadingToSave: Function, statusSave: Function) {
    let DATA = [];
    await this.dataReadExcel.forEach(ex => {
      let obj = new verifyAccountDtl();
      obj.verifyAccountHeaderId = ex.verifyAccountHeaderId;
      obj.ledgerAccountNumber = ex.colum0;
      obj.ledgerAccountName = ex.colum2;
      obj.bringForward = ex.colum4;
      obj.carryForward = ex.colum9;
      obj.debit = ex.colum7;
      obj.credit = ex.colum8;

      DATA.push(obj);
    });

    await this.ajax.post(URL.SAVE, DATA, async res => {
      const msg = res.json();
      console.log("msg: ", msg);
      if (msg.messageType === "C") {
        await this.msg.successModal(msg.messageTh);
        this.checkSave = true;
        await statusSave(this.checkSave);
        this.loadingTable = false;
        await loadingToSave(this.loadingTable);
      } else {
        await this.msg.errorModal(msg.messageTh);
        this.loadingTable = false;
        await loadingToSave(this.loadingTable);
      }
    });
  }

  onCheck = () => {
    this.router.navigate(["/int07/2"], {
      queryParams: {
        verifyAccountHeaderId: this.dataReadExcel[0].verifyAccountHeaderId
      }
    });
  };

  initDatatable(): void {
    if (this.datatable != null || this.datatable != undefined) {
      this.datatable.destroy();
    }

    this.datatable = $("#dataTableExcel").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      data: this.dataReadExcel,
      columns: [
        { data: "colum0" },
        { data: "colum2" },
        { data: "colum4" },
        { data: "colum7" },
        { data: "colum8" },
        { data: "colum9" }
        // {
        //   render: function() {
        //     return (
        //       '<button type="button" class="ui mini button orange edit"><i class="edit icon"></i> แก้ไข </button>' +
        //       '<button type="button" class="ui mini button red del"><i class="trash alternate icon"></i> ลบ</button>'
        //     );
        //   }
        // }
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
        $("td > .edit", row).bind("click", () => {
          console.log(data);
          console.log(row);
          console.log(index);

          $("#modalInt071").modal("show");
        });

        $("td > .del", row).bind("click", () => {
          let id = this.dataReadExcel.findIndex(
            obj => obj.columId == data.columId
          );
          this.dataReadExcel.splice(id, 1);
          console.log(this.dataReadExcel);

          this.initDatatable();
        });
      }
    });
  }
}

class File {
  [x: string]: any;
  name: string;
  type: string;
  value: any;
}

class verifyAccountDtl {
  [x: string]: any;
  verifyAccountHeaderId: number;
  ledgerAccountName: string;
  ledgerAccountNumber: string;
  bringForward: number;
  carryForward: number;
  credit: number;
  debit: number;
}

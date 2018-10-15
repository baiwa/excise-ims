import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AjaxService } from "services/ajax.service";
import { ComboBox } from "models/combobox";
import { Router, ActivatedRoute } from "@angular/router";
import { MessageBarService } from "../../../../common/services";

const URL = {
  DROPDOWN: "combobox/controller/getDropByTypeAndParentId",
  FILTER: AjaxService.CONTEXT_PATH + "/ia/int069/filter",
  DELETE: "/ia/int069/delete"
};

declare var $: any;

@Injectable()
export class Int069Service {
  dataTable: any;
  response: any;
  datatable: any;
  dataFilter: any;

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService
  ) {
    // TODO
  }

  dropdown = (type: string, id?: number): Observable<any> => {
    const DATA = { type: type, lovIdMaster: id || null };
    return new Observable<ComboBox[]>(obs => {
      this.ajax
        .post(URL.DROPDOWN, DATA, res => {
          // const response = res.json();
          this[type] = res.json();
          console.log("service: ", this[type]);
        })
        .then(() => obs.next(this[type]));
    });
  };

  filterDropdrown(DATA) {
    console.log(DATA);
    this.dataFilter = DATA;
    this.DATATABLE();
  }

  DATATABLE(): void {
    if (this.datatable != null || this.datatable != undefined) {
      this.datatable.destroy();
    }

    this.datatable = $("#datatable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: false,
      ajax: {
        type: "POST",
        url: URL.FILTER,
        data: this.dataFilter
      },
      columns: [
        {},
        { data: "mofNum" },
        { data: "refNum" },
        { data: "refDateStr" },
        { data: "transferList" },
        { data: "budgetType" },
        { data: "budgetCode" },
        { data: "activities" },
        { data: "budget" },
        { data: "ctgBudget" },
        { data: "subCtgBudget" },
        { data: "descriptionList" },
        { data: "amount" },
        { data: "note" },
        {
          render: function() {
            return (
              '<button type="button" class="ui mini button orange edit"><i class="edit icon"></i> แก้ไข </button>' +
              '<button type="button" class="ui mini button red del"><i class="trash alternate icon"></i> ลบ</button>'
            );
          }
        }
      ],
      columnDefs: [
        {
          targets: [0, 14],
          className: "center aligned",
          render: function(data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          }
        }
        // { targets: [2, 3, 4, 5], className: "right aligned" },
        // { targets: [1], className: "left aligned" },
        // {
        //   targets: [2, 3, 4, 5],
        //   render: $.fn.dataTable.render.number(",", ".", 2, "")
        // }
      ],
      rowCallback: (row, data, index) => {
        $("td > .edit", row).bind("click", () => {
          console.log(data);
          console.log(row);
          console.log(index);

          this.router.navigate(["int06/9/1"], {
            queryParams: {
              transferId: data.transferId,
              flag: "EDIT"
            }
          });
        });

        $("td > .del", row).bind("click", () => {
          console.log(data);

          this.ajax.post(URL.DELETE, { transferId: data.transferId }, res => {
            let msg = res.json();
            console.log("msg: ", msg);
          });

          this.DATATABLE();
        });
      }
    });
  }
}

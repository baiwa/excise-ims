import { Component, OnInit } from '@angular/core';
import { AjaxService, IaService } from '../../../../common/services';
import { TextDateTH, formatter } from '../../../../common/helper';
import { Router } from '@angular/router';
import { ManageReq, Message } from '../../../../common/models';

declare var $: any;

const URL = {
  SAVE_MASTER: "ia/int02/save_qtn_master",
  DATATABLE: `${AjaxService.CONTEXT_PATH}ia/int02/qtn_master/datatable`
}

@Component({
  selector: 'app-int02-1',
  templateUrl: './int02-1.component.html',
  styleUrls: ['./int02-1.component.css']
})
export class Int021Component implements OnInit {

  datatable: any;
  qtnName: string;
  qtnYear: string;
  typeOfSubmit: string;

  constructor(private ajax: AjaxService, private router: Router, private iaService: IaService) {
    this.typeOfSubmit = null;
    this.qtnName = null;
    this.qtnYear = null;
  }

  ngOnInit() {

    this.initDatatable();

    $("#date").calendar({
      maxDate: new Date(),
      type: "year",
      text: TextDateTH,
      formatter: formatter("year")
    });

    // Edited or Added ???
    $("#datatable tbody").on("click", "button", e => {
      const { id } = e.currentTarget;
      this.datatable.row($(e).parents("tr")).data();
      if ("edit" == id.split("-")[0]) {
        this.router.navigate(["/int02/2"], {
          queryParams: { id: id.split("-")[1] }
        });
      } else {
        console.log("Other ???");
      }
    });
  }

  initDatatable(): void {
    this.datatable = $("#datatable").DataTable({
      lengthChange: false,
      searching: false,
      select: true,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: false,
      paging: true,
      pagingType: "full_numbers",
      ajax: {
        type: "POST",
        url: URL.DATATABLE,
        data: {}
      },
      columns: [
        {
          data: "qtnMasterId",
          className: "center"
        },
        {
          data: "qtnName",
          className: "center"
        },
        {
          data: "qtnYear",
          className: "center"
        },
        {
          render: (data, type, full, meta) => {
            let str = "";
            if (full.qtnFinished == "Y") {
              str = "";
            } else {
              str = `<button class="ui icon yellow mini button" id="edit-${full.qtnMasterId}" value="edit-${full.qtnMasterId}"><i class="edit icon"></i></button>`;
            }
            return str;
          },
          className: "center"
        }
      ]
    });
  }

  reDatatable = () => {
    this.datatable.destroy();
    this.initDatatable();
  }

  onSubmit = e => {
    e.preventDefault();
    const { qtnName, qtnYear } = e.target;
    const data = {
      qtnName: qtnName.value,
      qtnYear: qtnYear.value
    };
    if (this.typeOfSubmit === 'S') {
      this.iaService.setData(data);
      this.router.navigate(['/int02/2']);
    } else {
      console.log('Searching... !?');
    }
  }

}

import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../../../../common/services';
declare var $: any;
@Component({
  selector: 'app-int02-1',
  templateUrl: './int02-1.component.html',
  styleUrls: ['./int02-1.component.css']
})
export class Int021Component implements OnInit {

  datatable: any;

  sideName: string;
  sideNameArr: any;

  constructor(private ajax: AjaxService) {
    this.sideNameArr = [];
  }

  ngOnInit() {
    // const URL = 'combobox/controller/comboboxHeaderQuestionnaire';
    // this.ajax.post(URL, {}, res => {
    //   this.sideNameArr = res.json();
    // });
    this.initDatatable();
  }

  initDatatable(): void {
    const URL = `${AjaxService.CONTEXT_PATH}ia/int02/qtn_master/datatable`;
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
            return `<button class="ui icon yellow mini button" id="edit-${full.qtnMasterId}" value="edit-${full.qtnMasterId}"><i class="edit icon"></i></button>`;
          },
          className: "center"
        }
      ]
    });
  }

}

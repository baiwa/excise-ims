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
    const URL = 'combobox/controller/comboboxHeaderQuestionnaire';
    this.ajax.post(URL, {}, res => {
      this.sideNameArr = res.json();
    });
    this.initialTable();
  }

  initialTable() {
    this.datatable = $("#datatable").DataTable({
      lengthChange: false,
      searching: false,
      select: true,
      ordering: true,
      pageLength: 5,
      // processing: true,
      // serverSide: true,
      paging: true,
      pagingType: "full_numbers"
    });
  }

}

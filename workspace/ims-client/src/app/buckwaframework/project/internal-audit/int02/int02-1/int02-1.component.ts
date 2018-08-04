import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'app-int02-1',
  templateUrl: './int02-1.component.html',
  styleUrls: ['./int02-1.component.css']
})
export class Int021Component implements OnInit {

  datatable: any;

  constructor() { }

  ngOnInit() {
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

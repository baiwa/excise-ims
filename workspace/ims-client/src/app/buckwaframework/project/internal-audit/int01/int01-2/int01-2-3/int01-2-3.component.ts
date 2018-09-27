import { Component, OnInit } from '@angular/core';
declare var $: any;
@Component({
  selector: 'int01-2-3',
  templateUrl: './int01-2-3.component.html',
  styleUrls: ['./int01-2-3.component.css']
})
export class Int0123Component implements OnInit {
  datatable : any;

  constructor() { }

  ngOnInit() {
    this.initdatatable();
  }
  initdatatable(){
    this.datatable = $("#datatable").DataTable({
      columnDefs: [
       // { targets: [], className: "center aligned" },
       // { targets: [1,2], className: "left aligned" }

      ]

    });
  }
}

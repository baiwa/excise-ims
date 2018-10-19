import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { AuthService } from 'services/auth.service';
import { AjaxService } from 'services/ajax.service';
declare var $: any;
@Component({
  selector: 'app-int02-m5-1-2',
  templateUrl: './int02-m5-1-2.component.html',
  styleUrls: ['./int02-m5-1-2.component.css']
})
export class Int02M512Component implements OnInit {

  breadcrumb: BreadCrumb[];
  datatable: any;
  budgetYear: any;
  wsRiskList: any;
  router: any;
  pageMapping: any;
  ajax: any;
  riskHdrName: string;
  messageBarService: any;
  constructor( private authService: AuthService) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
            { label: "ระบบควบคุมภายใน", route: "#" },
            { label: "แบบประเมินระบบควบคุมภายใน", route: "#" },
            { label: "รายการใหม่", route: "#" },
    ];

	
   }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-05120');

  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "ia/int08/searchRiskAssRiskWsHdr";
    console.log(URL);
    this.datatable = $("#dataTable").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: true,
      ajax: {
        type: "POST",
        url: URL,
        data: { budgetYear: this.budgetYear }
      },
      columns: [
        {
          render: function (data, type, row, meta) {
            return meta.row + meta.settings._iDisplayStart + 1;
          },
          className: "center"
        },
        { data: "intCtrlAssName" },
        { data: "budgetYesr" },         
        {
          data: "active",
          render: function (data, type, row, meta) {     
            return '<button type="button" class="ui mini button></button>';
          }
        },
  
      ],

    });
  }
0

}

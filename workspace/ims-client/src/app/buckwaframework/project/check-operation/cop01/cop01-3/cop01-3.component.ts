import { Component, OnInit } from '@angular/core';
import { AjaxService } from "../../../../common/services/ajax.service";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { MessageBarService } from "../../../../common/services/message-bar.service";


declare var jQuery: any;
declare var $: any;

const URL = {
  //DATATABLE: `${AjaxService.CONTEXT_PATH}oa/cop013/oa_oper_plan/datatable`
  DATATABLE: `${AjaxService.CONTEXT_PATH}oa/cop013/searchOaOperPlan`
}

@Component({
  selector: 'app-cop01-3',
  templateUrl: './cop01-3.component.html',
  styleUrls: ['./cop01-3.component.css']
})
export class Cop013Component implements OnInit {
  datatable: any;
  planStart: any;
  planName: any;

  constructor(private router: Router, 
    private route: ActivatedRoute, 
    private ajax: AjaxService, 
    private messageBarService: MessageBarService
  ) {
    
  }

  ngOnInit() {
    this.planStart = this.route.snapshot.queryParams["planStart"];
    this.initDatatable();
 }


  initDatatable(): void {
    this.datatable = $("#dataTable").DataTable({
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
        data: {planStart: this.planStart}
      },
      columns: [
        { data: "createdDate" },
        { data: "planStart" },
        { data: "planName" },
        
        {
          render: function () {
            return '<button type="button" class="ui mini primary button ">เเสดงบันทึกข้อความ</button>';
          }
        } 
      ],
      columnDefs: [
        { targets: [1, 2, 3], className: "center aligned" }
      ]
    });
  }

  // reDatatable = () => {
     
  //   this.datatable.destroy();
  //   this.initDatatable();
  // }

}
